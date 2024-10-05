package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.inventories.trophy.TrophyInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 29.01.2023 01:55
 *
 * @author thvf
 */
public class CommandTrophy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            new TrophyInventory(player).openGUI();

        }
        return false;
    }
}
