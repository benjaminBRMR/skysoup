package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * Created: 09.02.2023 11:54
 *
 * @author thvf
 */
public class CommandInvsee implements CommandExecutor {

    @Getter
    private static final HashMap<Player, Inventory> invseeMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.INVSEE.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 1) {

                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                invseeMap.put(player, target.getInventory());
                player.openInventory(target.getInventory());
                Data.getMessageUtil().sendMessage(player, "§7Du siehst nun das Inventar von §e" + target.getName() + " §7an§8.");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eInvsee §7<spieler>");


        }
        return false;
    }
}
