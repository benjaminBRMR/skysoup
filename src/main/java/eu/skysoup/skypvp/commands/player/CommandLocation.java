package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 23.01.2023 14:21
 *
 * @author thvf
 */
public class CommandLocation implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                final String location = args[1];

                Data.getLocation().setLocation(player.getLocation(), location);
                Data.getMessageUtil().sendMessage(player, "§7Die Location §e" + location + " §7wurde gesetzt§8.");
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
                final String location = args[1];

                if (Data.getLocation().existsLocation(location)) {
                    Data.getLocation().deleteLocation(location);
                    Data.getMessageUtil().sendMessage(player, "§cDie Location §7" + location + " §cwurde gelöscht§8.");
                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDie Location §7" + location + " §cexistiert nicht§8.");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eLocation §7<set> <name>", "§8/§eLocation §7<delete> <name>");

        }


        return false;
    }
}
