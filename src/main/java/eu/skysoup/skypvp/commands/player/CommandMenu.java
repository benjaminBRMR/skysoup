package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.inventories.MenuInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            if (Data.getPlotSquaredUtil().getPlotOwnerPlayerIsStandingOn(player).equalsIgnoreCase(player.getName())) {
                new MenuInventory(player).openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDiesen Befehl kannst du nur auf deinem Plot!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        }
        return false;
    }
}
