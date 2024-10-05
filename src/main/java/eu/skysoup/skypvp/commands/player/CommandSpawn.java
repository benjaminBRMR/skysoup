package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.utils.teleport.TeleportCooldown;
import eu.skysoup.skypvp.utils.teleport.TeleportCooldownCategory;
import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

/**
 * Created: 06.02.2023 12:06
 *
 * @author thvf
 */
public class CommandSpawn implements CommandExecutor {

    @Getter
    private static final TeleportCooldownCategory TELEPORT_COUNTDOWN = new TeleportCooldownCategory();

    private BukkitTask particleTask;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            TeleportCooldown teleportCountdown = Data.getTeleportCooldowenController().get(TELEPORT_COUNTDOWN, player);


            if (player.hasPermission(Permissions.TEAM.getPermission())) {
                if (Data.getLocation().existsLocation("spawn"))
                    player.teleport(Data.getLocation().getLocation("spawn"));
                Data.getMessageUtil().sendMessage(player, "§7Du wurdest erfolgreich zum §eSpawn §7teleportiert§8.");
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 0.5F);
                return true;
            }

            if (teleportCountdown == null) {

                teleportCountdown = Data.getTeleportCooldowenController().create(TELEPORT_COUNTDOWN, player, 5, TimeUnit.SECONDS)
                        .whenStarted(() -> {

                            particleTask = new BukkitRunnable() {
                                double phi = 0;

                                @Override
                                public void run() {
                                    phi += Math.PI / 8;
                                    double x;
                                    double y;
                                    double z;
                                    Location loc = player.getLocation();
                                    for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16) {
                                        for (double i = 0; i <= 1; i++) {
                                            x = 0.3 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
                                            y = 0.5 * t;
                                            z = 0.3 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
                                            loc.add(x, y, z);
                                            World world = loc.getWorld();
                                            world.playEffect(loc, Effect.COLOURED_DUST, 1);
                                            loc.subtract(x, y, z);
                                        }
                                    }
                                }
                            }.runTaskTimer(SkyPvP.getINSTANCE(), 0, 20);


                            Data.getMessageUtil().sendMessage(player, "§7Du wirst in §e5 §7Sekunden teleportiert§8.");
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1F, 1.1F);

                        })
                        .addStep(1, () -> {
                            Data.getMessageUtil().sendMessage(player, "§7Du wirst in §e4 §7Sekunden teleportiert§8.");
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1F, 1.1F);
                        })
                        .addStep(2, () -> {
                            Data.getMessageUtil().sendMessage(player, "§7Du wirst in §e3 §7Sekunden teleportiert§8.");
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1F, 1.1F);
                        })
                        .addStep(3, () -> {
                            Data.getMessageUtil().sendMessage(player, "§7Du wirst in §e2 §7Sekunden teleportiert§8.");
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1F, 1.1F);
                        })
                        .addStep(4, () -> {
                            Data.getMessageUtil().sendMessage(player, "§7Du wirst in §eeiner §7Sekunde teleportiert§8.");
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1F, 1.1F);
                        })
                        .whenCancelled(() -> {
                            Data.getMessageUtil().sendMessage(player, "§cDu hast dich bewegt, die Teleportierung wurde unterbrochen!");
                            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1F, 0.1F);
                            particleTask.cancel();
                        })
                        .whenCompleted(() -> {
                            if (Data.getLocation().existsLocation("spawn"))
                                player.teleport(Data.getLocation().getLocation("spawn"));
                            Data.getMessageUtil().sendMessage(player, "§7Du wurdest erfolgreich zum §eSpawn §7teleportiert§8.");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 0.5F);
                            particleTask.cancel();
                        });

                teleportCountdown.start();
            }
        }
        return false;
    }
}
