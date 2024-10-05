package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.inventories.SettingsInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 03.02.2023 13:23
 *
 * @author thvf
 */
public class CommandSettings implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            new SettingsInventory(player).openGUI();
        }
        return false;
    }
}
