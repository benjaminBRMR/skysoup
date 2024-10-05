package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.inventories.InventoryTitles;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 26.02.2023 12:13
 *
 * @author thvf
 */
public class CommandTrash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {

            final Player player = (Player) sender;
            player.openInventory(Bukkit.createInventory(null, 54, InventoryTitles.TRASH.getName()));

        }
        return false;
    }
}
