package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.moneymaker.inventories.MoneyMakerInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 26.02.2023 22:36
 *
 * @author thvf
 */
public class CommandMoneyMaker implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (!player.getWorld().getName().equals("moneymaker")) {
                Data.getMessageUtil().sendMessage(player, "§cDieses Menü kannst du hier nicht öffnen!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                return true;
            }
            new MoneyMakerInventory(player).openGUI();
        }
        return false;
    }
}
