package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created: 31.01.2023 23:49
 *
 * @author thvf
 */
public class KillListener implements Listener {

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {

        event.setDeathMessage(null);

        final Player player = event.getEntity().getPlayer();
        final Player attacker = event.getEntity().getKiller();
        UserController userController;


        if (attacker != null) {


            Data.getMessageUtil().sendMessage(attacker, "§cDu hast §4" + player.getName() + " §celiminiert!");
            Data.getMessageUtil().sendMessage(player, "§cDu wurdest von §4" + attacker.getName() + " §celiminiert!");

            Data.getCombatController().getCombatLog().remove(player);

            userController = new UserController().getUserByUUID(attacker.getUniqueId());
            userController.setStatisticFromUser(StatisticTypes.KILLSTREAK, userController.getStatisticByType(StatisticTypes.KILLSTREAK) + 1);
            userController.setStatisticFromUser(StatisticTypes.KILLS, userController.getStatisticByType(StatisticTypes.KILLS) + 1);
            userController.setStatisticFromUser(StatisticTypes.TROPHYS, userController.getStatisticByType(StatisticTypes.TROPHYS) + 2);
            userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) + 10);


            userController = new UserController().getUserByUUID(player.getUniqueId());
            if (Data.getToggleController().isToggled(ToggleController.Types.KILLHOLO) && attacker.getWorld().getName().equals("PvP")) {
                Data.getHologramController().spawnTempHologram(6, player.getLocation(),
                        "§8§m--§7§m--§r §c§lELIMINIERUNG §7§m--§8§m--§r",
                        "%empty%",
                        "%empty%",
                        "§a§l✓ §8┃ §7Gewinner§8: §a" + attacker.getName(),
                        "§c§l✕ §8┃ §7Verlierer§8: §c" + player.getName(),
                        "§6§l❂ §8┃ §7Kopfgeld§8: " + (userController.getBountyController().hasBounty() ? "§6$§e" + ValueUtil.format2(userController.getBountyController().getBounty()) : "§c§lKEIN KOPFGELD"),
                        "§8§m--§7§m--§r §c§lELIMINIERUNG §7§m--§8§m--§r"
                );
                player.getLocation().getWorld().playEffect(player.getLocation().add(0, 1, 0), Effect.ENDER_SIGNAL, 30);
                player.getLocation().getWorld().playEffect(player.getLocation().add(0, 1, 0), Effect.EXPLOSION, 1);
                player.getLocation().getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), ItemSkull.getSkull("§8» §7Rune des Todes", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ2MjBlNGUzZDNhYmZlZDZhZDgxYTU4YTU2YmNkMDg1ZDllOWVmYzgwM2NhYmIyMWZhNmM5ZTM5NjllMmQyZSJ9fX0=",
                        "§8§oDiese Rune hast Du durch eine",
                        "§8§oSeele eines toten Spielers erhalten.",
                        "§8§oRechtsklick, um diese zu benutzen!",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ));
            }
        }


        userController = new UserController().getUserByUUID(player.getUniqueId());

        if (userController.getBountyController().hasBounty()) {

            player.getLocation().getWorld().dropItemNaturally(player.getLocation(), userController.getBountyController().getBountyItem(userController.getBountyController().getBounty()));

            for (Player all : player.getWorld().getPlayers()) {
                Data.getMessageUtil().sendMessage(all, "§c" + player.getName() + "§8'§cs §7Kopfgeldmarke §8(§6$§e" + NumberFormat.getInstance().format(userController.getBountyController().getBounty()) + "§8) §7wurde fallengelassen§8.");
            }
        }


        if (userController.getStatisticByType(StatisticTypes.KILLSTREAK) > 0)
            Data.getMessageUtil().sendMessage(player, "§cDeine Killstreak wurde beendet.");

        userController.setStatisticFromUser(StatisticTypes.BOUNTY, 0);
        userController.setStatisticFromUser(StatisticTypes.KILLSTREAK, 0);
        userController.setStatisticFromUser(StatisticTypes.DEATHS, userController.getStatisticByType(StatisticTypes.DEATHS) + 1);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.spigot().respawn();
            }
        }.runTaskLater(SkyPvP.getINSTANCE(), 10L);
    }
}
