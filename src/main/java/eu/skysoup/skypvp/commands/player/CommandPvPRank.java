package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.inventories.PvPRankInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 30.01.2023
 *
 * @author benni
 */
public class CommandPvPRank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            new PvPRankInventory(player).openGUI();
        }
        return false;
    }
}
