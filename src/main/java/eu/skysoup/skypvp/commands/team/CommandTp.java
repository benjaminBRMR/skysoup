package eu.skysoup.skypvp.commands.team;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 10.02.2023 00:15
 *
 * @author thvf
 */
public class CommandTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.TP.getPermission(), true))
                return true;

            final Player player = (Player) sender;


            if (args.length == 1) {

                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                player.teleport(target);
                Data.getMessageUtil().sendMessage(player, "§7Du wurdest zu §e" + target.getName() + " §7teleportiert§8.");
                return true;
            }

            if (args.length == 2) {

                final Player player1 = Bukkit.getPlayer(args[0]);
                final Player player2 = Bukkit.getPlayer(args[1]);

                if (player1 == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                if (player2 == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                player1.teleport(player2);
                Data.getMessageUtil().sendMessage(player, "§e" + player1.getName() + " §7wurde zu §e" + player2.getName() + " §7teleportiert§8.");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eTp §7<spieler>", "§8/§eTp §7<spieler> <spieler>");


        }
        return false;
    }
}
