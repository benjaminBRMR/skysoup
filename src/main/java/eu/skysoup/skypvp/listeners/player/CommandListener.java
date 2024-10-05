package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import java.util.List;

/**
 * Created: 03.02.2023 13:42
 *
 * @author thvf
 */
public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onCommand(final PlayerCommandPreprocessEvent event) {

        final Player player = event.getPlayer();
        final CasinoController casinoController = Data.getCasinoController().getUser(player);
        final String command = event.getMessage().replaceAll("/", "").toLowerCase();
        final String cmd = event.getMessage().split(" ")[0];
        final HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
        if (topic == null) {
            Data.getMessageUtil().sendMessage(player, "§cDer Befehl §8'§e/" + command + "§8' §cwurde nicht gefunden!");
            event.setCancelled(true);
        }

        for (String s : (List<String>) Data.getConfigController().getList("watchercmds")) {
            if (command.split(" ")[0].equalsIgnoreCase(s)) {
                Data.getMessageUtil().sendMessageToAllPlayersWithSetting(SettingTypes.CW, PrefixController.getCw(), "§f" + player.getName() + " §8-> §f/" + command);
            }
        }

        for (String s : (List<String>) Data.getConfigController().getList("blockedcmds")) {
            if (command.split(" ")[0].equalsIgnoreCase(s) && !Data.getPermissionUtil().hasPermission(player, Permissions.ADMIN.getPermission(), false)) {
                Data.getMessageUtil().sendMessage(player, "§cDu hast dazu keine Rechte!");
                event.setCancelled(true);
                Data.getMessageUtil().sendMessageToAllPlayersWithSetting(SettingTypes.ADMINCW, PrefixController.getAdminCW(), "§f" + player.getName() + " §8-> §f/" + command);
            }
        }

    }
}
