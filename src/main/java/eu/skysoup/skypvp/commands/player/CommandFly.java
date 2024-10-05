package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * JavaDoc this file!
 * Created: 09.01.2023
 *
 * @author thvf
 */
public class CommandFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.FLY.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 0) {

                player.setAllowFlight(!player.getAllowFlight());
                Data.getMessageUtil().sendMessage(player, "§7Dein Flugmodus wurde " + (player.getAllowFlight() ? "§aaktiviert." : "§cdeaktiviert."));
                return true;
            }

            if (args.length == 1) {

                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.FLYOTHER.getPermission(), true))
                    return true;

                final Player target = Bukkit.getPlayer(args[0]);

                target.setAllowFlight(!target.getAllowFlight());
                Data.getMessageUtil().sendMessage(target, "§7Dein Flugmodus wurde " + (target.getAllowFlight() ? "§aaktiviert." : "§cdeaktiviert."));
                Data.getMessageUtil().sendMessage(player, "§f" + target.getName() + "§8'§fs §7Flugmodus wurde " + (target.getAllowFlight() ? "§aaktiviert." : "§cdeaktiviert."));
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eFly");
        }
        return false;
    }
}
