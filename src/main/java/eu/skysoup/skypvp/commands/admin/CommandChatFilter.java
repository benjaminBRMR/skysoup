package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 23.01.2023 19:50
 *
 * @author thvf
 */
public class CommandChatFilter implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 2 && args[0].equalsIgnoreCase("add")) {

                final String wort = args[1];

                if (Data.getChatController().isWordBlacklisted(wort)) {
                    Data.getMessageUtil().sendMessage(player, "§cDas Wort §7§o" + wort + " §cist bereits blockiert.");
                    return true;
                }


                Data.getChatController().addWord(wort);
                Data.getMessageUtil().sendMessage(player, "§aDas Wort §7§o" + wort + " §awurde blockiert.");
                return true;
            }


            if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {

                final String wort = args[1];

                if (!Data.getChatController().isWordBlacklisted(wort)) {
                    Data.getMessageUtil().sendMessage(player, "§cDas Wort §7§o" + wort + " §cist nicht blockiert.");

                    return true;
                }

                Data.getChatController().removeWord(wort);
                Data.getMessageUtil().sendMessage(player, "§aDas Wort §7§o" + wort + " §awurde entfernt.");

                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {

                for (String st : Data.getChatController().getBlacklistedWords()) {
                    Data.getChatController().removeWord(st);
                }
                player.sendMessage(PrefixController.getSkyPvP() + "§cAlle Wörter wurden von der Liste genommen§8.");
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {


                if (Data.getChatController().getBlacklistedWords().size() < 1) {
                    player.sendMessage(PrefixController.getSkyPvP() + "§cAktuell sind keine Wörter blockiert!");
                    return true;
                }


                player.sendMessage(PrefixController.getSkyPvP() + "§7Folgende §6" + Data.getChatController().getBlacklistedWords().size() + " §eWörter §7sind aktuell blockiert§8:");


                for (String st : Data.getChatController().getBlacklistedWords()) {
                    player.sendMessage(PrefixController.getSkyPvP() + "§e" + st);
                }

                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eChatfilter §7<add> <wort>",
                    "§8/§eChatfilter §7<remove> <wort>",
                    "§8/§eChatfilter §7<clear>",
                    "§8/§eChatfilter §7<list>");

        }
        return false;
    }
}
