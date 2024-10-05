package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.inventories.TopInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 27.01.2023 18:11
 *
 * @author thvf
 */
public class CommandTop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            new TopInventory(player).openGUI();
        }
        return false;
    }
}
