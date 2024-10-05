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
 * Created: 09.02.2023 11:25
 *
 * @author thvf
 */
public class CommandTeamchat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.TEAM.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                Bukkit.getOnlinePlayers().forEach(all -> {
                    if (all.hasPermission(Permissions.TEAM.getPermission())) {
                        all.sendMessage(PrefixController.getTeamchat() + "§e" + player.getName() + " §8-> §7" + msg);
                    }
                });
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eTeamchat §7<nachricht>");


        }
        return false;
    }
}
