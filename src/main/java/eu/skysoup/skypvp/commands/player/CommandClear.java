package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
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
public class CommandClear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.CLEAR.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (args.length == 0) {

                if (Data.getPlayerUtil().isInventoryEmpty(player)) {
                    Data.getMessageUtil().sendMessage(player, "§cDein Inventar ist bereits geleert.");
                    return true;
                }

                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                Data.getMessageUtil().sendMessage(player, "§aDein Inventar wurde geleert.");
                return true;
            }

            if (args.length == 1) {
                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.CLEAROTHER.getPermission(), true))
                    return true;

                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                if (Data.getPlayerUtil().isInventoryEmpty(target)) {
                    Data.getMessageUtil().sendMessage(player, "§c" + target.getName() + "§8'§cs Inventar ist bereits geleert.");
                    return true;
                }

                target.getInventory().clear();
                target.getInventory().setArmorContents(null);
                Data.getMessageUtil().sendMessage(target, "§aDein Inventar wurde geleert.");
                Data.getMessageUtil().sendMessage(player, "§a" + target.getName() + "§8'§as Inventar wurde geleert.");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eClear");
        }
        return false;
    }
}
