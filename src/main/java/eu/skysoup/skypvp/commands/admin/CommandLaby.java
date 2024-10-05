package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 16.02.2023 15:32
 *
 * @author thvf
 */
public class CommandLaby implements CommandExecutor {

    private final String[] labykick = new String[]{
            "§r",
            "§r",
            "§8§m--§7§m--§f§m--§r §c§lWHITELIST §f§m--§7§m--§8§m--§r",
            "§r",
            "§cDu bist nicht auf der Whitelist!",
            "§cBitte versuche es später erneut.",
            "",
            "§cUnser Discord: §4§kdiscord.skysoup.eu",
            "§r",
            "§8§m--§7§m--§f§m--§r §c§lWHITELIST §f§m--§7§m--§8§m--§r"
    };

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;

            final Player player = (Player) sender;

            if (args.length >= 2 && args[0].equalsIgnoreCase("spam")) {

                StringBuilder msg = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                msg.append("§").append(Data.getRandom().nextInt(9));
                Bukkit.getOnlinePlayers().forEach(all -> Data.getLabymodUtil().sendCurrentPlayingGamemode(all, true, msg.toString().replaceAll("&", "§")));
                Data.getPlayerUtil().sendActionbar(player, "§8» §7Labysend wurde an §e" + Bukkit.getOnlinePlayers().size() + " §7Spielern gesendet§8.");
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("kick")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                target.kickPlayer("");

                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eLaby §7<spam> <nachricht>");

        }

        return false;
    }
}
