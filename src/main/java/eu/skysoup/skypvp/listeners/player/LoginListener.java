package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.other.BetaController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created: 28.01.2023 14:49
 *
 * @author thvf
 */
public class LoginListener implements Listener {

    private final String[] whitelist = new String[]{
            "§r",
            "§r",
            "§8§m--§7§m--§f§m--§r §c§lWHITELIST §f§m--§7§m--§8§m--§r",
            "§r",
            "§cID§8: §4%s",
            "§r",
            "§cDu bist nicht auf der Whitelist!",
            "§cBitte versuche es später erneut.",
            "",
            "§7Unser Discord: §cdiscord.skysoup.eu",
            "§r",
            "§r",
            "§8§m--§7§m--§f§m--§r §c§lWHITELIST §f§m--§7§m--§8§m--§r"
    };

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerConnect(final PlayerLoginEvent event) {

        final Player player = event.getPlayer();


        if (!player.hasPermission(Permissions.WHITELIST.getPermission())) {

            final BetaController betaController = new BetaController();
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : whitelist)
                stringBuilder.append(String.format(s, betaController.getID(player.getUniqueId()))).append("\n");

            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, stringBuilder.toString().replaceAll("&", "§"));
            Data.getConfigController().setLong("loginCount", (Data.getConfigController().getLong("loginCount") + 1));

            if (Data.getToggleController().isToggled(ToggleController.Types.NOWL)) {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    Data.getMessageUtil().sendMessage(all, "§e" + player.getName() + " §7wollte den Server betreten§8.");
                    Data.getMessageUtil().sendMessage(all, "§8├ §7Uhrzeit§8: §e" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    Data.getMessageUtil().sendMessage(all, "§8├ §7ID§8: §e" + betaController.getID(player.getUniqueId()));
                    if (all.hasPermission(Permissions.ADMIN.getPermission()))
                        Data.getMessageUtil().sendMessage(all, "§8└ §7IP§8: §e" + event.getAddress().getHostAddress());
                });
            }

            return;
        }


        if (Data.getBlacklistController().isPlayerBlacklisted(player.getUniqueId()) || Data.getBlacklistController().isIPBlacklisted(event.getAddress().getHostAddress())) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, "§cio.netty.channel.AbstractChannel$AnnotatedConnectException: Connecction refused:");
            Data.getMessageUtil().sendMessageToAllPlayersWithSetting(SettingTypes.ADMINCW, PrefixController.getBlacklist(), "§e" + player.getName() + " §7wollte den Server betreten§8.");
            return;
        }

    }
}
