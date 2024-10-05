package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.other.LagerPlatzController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 14.02.2023 19:40
 *
 * @author thvf
 */
public class CommandLagerPlatz implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            LagerPlatzController lagerPlatzController;


            if (args.length == 0) {

                // Extra Permission einbauen ("skysoup.lp.openmobil") das man nur mit der Permission den Lagerplatz mobil aufmachen kann

                lagerPlatzController = new LagerPlatzController(player.getUniqueId());
                if (!lagerPlatzController.hasLagerPlatz()) {
                    Data.getMessageUtil().sendMessage(player, "§cDu besitzt aktuell noch keinen Lagerplatz!");
                    Data.getMessageUtil().sendMessage(player, "§cKaufe dir jetzt einen im Shop §8'§f§l/SHOP§8'.");
                    return true;
                }

                lagerPlatzController.openLagerPlatzForOwner();
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("setlevel") && Data.getPermissionUtil().hasPermission(player, Permissions.ADMIN.getPermission(), true)) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                try {
                    final int rows = Integer.parseInt(args[2]);

                    if (rows > 6) {
                        Data.getMessageUtil().sendMessage(player, "§cDas Maximale Level liegt bei 6!");
                        return true;
                    }

                    if (rows < 1) {
                        Data.getMessageUtil().sendMessage(player, "§cDas Minimale Level liegt bei 1!");
                        return true;
                    }

                    lagerPlatzController = new LagerPlatzController(target.getUniqueId());

                    if (!lagerPlatzController.hasLagerPlatz()) {
                        Data.getMessageUtil().sendMessage(player, "§cDieser Spieler besitzt keinen Lagerplatz!");
                        return true;
                    }


                    lagerPlatzController.setLagerPlatzLevel(rows);
                    Data.getMessageUtil().sendMessage(player, "§e" + target.getName() + "§8'§es §7Lagerplatz hat nun §e" + rows + " §7Reihen§8.");


                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("view") && Data.getPermissionUtil().hasPermission(player, Permissions.LAGERPLATZVIEWOTHER.getPermission(), true)) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                lagerPlatzController = new LagerPlatzController(target.getUniqueId());

                if (!lagerPlatzController.hasLagerPlatz()) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Spieler besitzt keinen Lagerplatz!");
                    return true;
                }

                lagerPlatzController.openLagerPlatzForViewer(player.getUniqueId());
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("create") && Data.getPermissionUtil().hasPermission(player, Permissions.ADMIN.getPermission(), true)) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                lagerPlatzController = new LagerPlatzController(target.getUniqueId());

                if (lagerPlatzController.hasLagerPlatz()) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Spieler hat bereits einen Lagerplatz!");
                    return true;
                }

                lagerPlatzController.createLagerPlatz();
                Data.getMessageUtil().sendMessage(player, "§e" + target.getName() + " §7hat nun einen Lagerplatz§8.");
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("delete") && Data.getPermissionUtil().hasPermission(player, Permissions.ADMIN.getPermission(), true)) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                lagerPlatzController = new LagerPlatzController(target.getUniqueId());

                if (lagerPlatzController.hasLagerPlatz()) {
                    lagerPlatzController.deleteLagerPlatz();
                    Data.getMessageUtil().sendMessage(player, "§7Lagerplatz von §e" + target.getName() + " §cgelöscht§8.");
                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§e" + target.getName() + " §7besitzt §ckeinen§7 Lagerplatz§8.");
                return true;
            }

            if (player.hasPermission(Permissions.ADMIN.getPermission()))
                Data.getMessageUtil().sendSyntax(player,
                        "§8/§eLagerplatz §7<setlevel> <spieler> <1-6>",
                        "§8/§eLagerplatz §7<view> <spieler>",
                        "§8/§eLagerplatz §7<create> <spieler>",
                        "§8/§eLagerplatz §7<delete> <spieler>"
                );
        }
        return false;
    }
}
