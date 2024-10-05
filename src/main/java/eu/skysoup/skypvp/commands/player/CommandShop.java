package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.shop.inventories.ShopMainInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 11.02.2023
 *
 * @author benni
 */
public class CommandShop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            new ShopMainInventory(player).openGUI();
        }
        return false;
    }
}
