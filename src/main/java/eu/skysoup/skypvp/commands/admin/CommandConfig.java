package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created: 16.02.2023 12:43
 *
 * @author thvf
 */
public class CommandConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("broadcasttime")) {

                try {
                    final long height = Long.parseLong(args[2]);

                    Data.getTaskController().getBroadcastTask().cancel();
                    Data.getConfigController().setLong("broadcasttime", height);
                    Data.getTaskController().beginBroadcast();
                    Data.getMessageUtil().sendMessage(player, "§7Die §eBroadcastTime §7wurde auf §e" + height + " §7Minuten §7gesetzt§8.");

                } catch (NumberFormatException ignored) {

                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");

                }
                return true;
            }
            if (args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("spawntpheight")) {

                try {
                    final long height = Long.parseLong(args[2]);

                    Data.getConfigController().setLong("spawnteleportheight", height);
                    Data.getMessageUtil().sendMessage(player, "§7Die §eSpawnteleportHöhe §7wurde auf §e" + height + " §7gesetzt§8.");

                } catch (NumberFormatException ignored) {

                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");

                }
                return true;
            }

            if (args.length >= 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("labyjoinmsg")) {
                StringBuilder msg = new StringBuilder();

                for (int i = 2; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                Data.getConfigController().setString("labyjoinmsg", msg.toString());
                Data.getMessageUtil().sendMessage(player, "§7Die §eLabyjoinmessage §7wurde geändert§8.");
                Data.getMessageUtil().sendMessage(player, Data.getConfigController().getString("labyjoinmsg").replaceAll("&", "§"));
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("nachtmarktchance")) {

                try {
                    final long prozent = Long.parseLong(args[2]);

                    if (prozent > 100) {
                        Data.getMessageUtil().sendMessage(player, "§cDie Chance darf nicht größer als 100 sein!");
                        return true;
                    }

                    Data.getConfigController().setLong("nachtmarktchance", prozent);
                    Data.getMessageUtil().sendMessage(player, "§7Die §eNachtmarktchance §7wurde zu §e" + prozent + "§8% §7geändert§8.");

                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");

                }
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("blockedcmds")) {

                final String adap = args[1];
                final String command = args[2];

                switch (adap.toUpperCase()) {

                    case "ADD":
                        if (!Data.getConfigController().getList("blockedcmds").contains(command))
                            Data.getConfigController().addStringToList("blockedcmds", command);
                        Data.getMessageUtil().sendMessage(player, "§7Die §eBlockedcommands Liste §7wurde geändert§8.");
                        break;

                    case "REMOVE":
                        if (Data.getConfigController().getList("blockedcmds").contains(command))
                            Data.getConfigController().removeStringToList("blockedcmds", command);
                        Data.getMessageUtil().sendMessage(player, "§7Die §eBlockedcommands Liste §7wurde geändert§8.");
                        break;

                    case "LIST":
                        Data.getMessageUtil().sendMessage(player, "§eBlockedcommands Liste§8:");
                        for (String s2 : (List<String>) Data.getConfigController().getList("blockedcmds")) {
                            Data.getMessageUtil().sendMessage(player, "§e" + s2);
                        }
                        break;
                }
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("watchercmds")) {

                final String adap = args[1];
                final String command = args[2];

                switch (adap.toUpperCase()) {

                    case "ADD":
                        if (!Data.getConfigController().getList("watchercmds").contains(command))
                            Data.getConfigController().addStringToList("watchercmds", command);
                        Data.getMessageUtil().sendMessage(player, "§7Die §eCommandWatcher Liste §7wurde geändert§8.");
                        break;

                    case "REMOVE":
                        if (Data.getConfigController().getList("watchercmds").contains(command))
                            Data.getConfigController().removeStringToList("watchercmds", command);
                        Data.getMessageUtil().sendMessage(player, "§7Die §eCommandWatcher Liste §7wurde geändert§8.");
                        break;

                    case "LIST":
                        Data.getMessageUtil().sendMessage(player, "§eCommandWatcher Liste§8:");
                        for (String s2 : (List<String>) Data.getConfigController().getList("watchercmds")) {
                            Data.getMessageUtil().sendMessage(player, "§e" + s2);
                        }
                        break;
                }
                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eConfig §7<set> <labyjoinmsg> <value>",
                    "§8/§eConfig §7<set> <nachtmarktchance> <wert>",
                    "§8/§eConfig §7<set> <spawntpheight> <wert>",
                    "§8/§eConfig §7<set> <broadcasttime> <wert>",
                    "§8/§eConfig §7<blockedcmds> <add,rem,list> <value>",
                    "§8/§eConfig §7<watchercmds> <add,rem,list> <value>");

        }
        return false;
    }
}
