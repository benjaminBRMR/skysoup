package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.casino.inventories.CasinoInventory;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.utils.teleport.TeleportCooldown;
import eu.skysoup.skypvp.utils.teleport.TeleportCooldownCategory;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/**
 * Created: 07.02.2023 15:05
 *
 * @author thvf
 */
public class CommandCasino implements CommandExecutor {

    @Getter
    private static final TeleportCooldownCategory TELEPORT_COUNTDOWN = new TeleportCooldownCategory();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            final CasinoController casinoController = Data.getCasinoController().getUser(player);
            TeleportCooldown teleportCountdown = Data.getTeleportCooldowenController().get(TELEPORT_COUNTDOWN, player);


            if (!casinoController.hasPlayerCasinoPass()) {
                Data.getMessageUtil().sendMessage(player, "§cDu besitzt aktuell keine Casino-Mitgliedschaft!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                return true;
            }

            if (!player.getWorld().getName().equals("Casino")) {

                if (player.hasPermission(Permissions.TEAM.getPermission())) {
                    if (Data.getLocation().existsLocation("casino"))
                        player.teleport(Data.getLocation().getLocation("casino"));
                    Data.getMessageUtil().sendMessage(player, "§7Du wurdest erfolgreich zum §eCasino §7teleportiert§8.");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 0.5F);
                    return true;
                }

                if (teleportCountdown == null) {
                    teleportCountdown = Data.getTeleportCooldowenController().create(TELEPORT_COUNTDOWN, player, 5, TimeUnit.SECONDS)
                            .whenStarted(() -> {
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

                            })
                            .whenCompleted(() -> {
                                if (Data.getLocation().existsLocation("casino"))
                                    player.teleport(Data.getLocation().getLocation("casino"));
                                Data.getMessageUtil().sendMessage(player, "§7Du wurdest erfolgreich zum §eCasino §7teleportiert§8.");
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 0.5F);
                            });

                    teleportCountdown.start();
                }
                return true;
            }
            new CasinoInventory(player).openGUI();
        }
        return false;
    }


}
