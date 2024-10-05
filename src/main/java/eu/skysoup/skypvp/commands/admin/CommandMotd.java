package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 11.02.2023 11:06
 *
 * @author thvf
 */
public class CommandMotd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;

            final Player player = (Player) sender;

            if (args.length >= 1 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("1")) {

                StringBuilder msg = new StringBuilder();

                for (int i = 2; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }
                Data.getMotdController().setLine1(msg.toString());
                Data.getMessageUtil().sendMessage(player, "§7Du hast die §eerste Zeile §7der §6Motd §7verändert§8.");
                Data.getMessageUtil().sendMessage(player, Data.getMotdController().getLine1().replaceAll("&", "§").replaceAll("#", "&"));
                return true;
            }

            if (args.length >= 1 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("2")) {

                StringBuilder msg = new StringBuilder();

                for (int i = 2; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }
                Data.getMotdController().setLine2(msg.toString());
                Data.getMessageUtil().sendMessage(player, "§7Du hast die §ezweite Zeile §7der §6Motd §7verändert§8.");
                Data.getMessageUtil().sendMessage(player, Data.getMotdController().getLine2().replaceAll("&", "§").replaceAll("#", "&"));
                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eMotd §7<set> <1> <text>",
                    "§8/§eMotd §7<set> <2> <text>",
                    "§r",
                    "§7Line§8-§71§8: §e" + Data.getMotdController().getLine1().replaceAll("&", "§").replaceAll("#", "&"),
                    "§7Line§8-§72§8: §e" + Data.getMotdController().getLine2().replaceAll("&", "§").replaceAll("#", "&"));
        }
        return false;
    }
}
