package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * JavaDoc this file!
 * Created: 09.01.2023
 *
 * @author thvf
 */
public class CommandHat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.HAT.getPermission(), true))
                return true;

            final Player player = (Player) sender;
            final ItemStack item = player.getItemInHand();

            if (item == null || item.getType() == Material.AIR) {
                Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in der Hand.");
                return true;
            }

            if (player.getInventory().getHelmet() != null) {
                player.getInventory().addItem(player.getInventory().getHelmet());
            }

            final ItemStack newItem = new ItemStack(item.getType());
            newItem.setAmount(1);

            player.getInventory().setHelmet(newItem);
            player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 5, 18.4F);
            Data.getPlayerUtil().removeHand(player);
            Data.getMessageUtil().sendMessage(player, "§aDu trägt nun §f" + item.getType().toString().toLowerCase() + " §aauf dem Kopf.");


        }
        return false;
    }
}
