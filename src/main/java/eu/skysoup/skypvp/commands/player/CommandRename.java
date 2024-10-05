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
import org.bukkit.inventory.meta.ItemMeta;

/**
 * JavaDoc this file!
 * Created: 14.01.2023
 *
 * @author thvf
 */
public class CommandRename implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.RENAME.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (args.length >= 1) {

                final ItemStack item = player.getItemInHand();

                if (item == null || item.getType() == Material.AIR) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in der Hand.");
                    return true;
                }

                StringBuilder msg = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(msg.toString().replaceAll("&", "§").trim());

                item.setItemMeta(itemMeta);

                player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                Data.getMessageUtil().sendMessage(player, "§aDein Item wurde erfolgreich umbenannt!");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eRename §7<text>");
        }
        return false;
    }
}
