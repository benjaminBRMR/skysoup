package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 12.02.2023 15:33
 *
 * @author thvf
 */
public class CommandAirdrop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {
                try {
                    final AtomicInteger atomicInteger = new AtomicInteger(0);
                    final long level = Long.parseLong(args[1]);

                    if (level < 1 || level > 5) {
                        Data.getMessageUtil().sendMessage(player, "§cBitte gebe ein Level zwischen 1-5 an!");
                        return true;
                    }

                    final Inventory inventory = Bukkit.createInventory(null, 54, "§8┃ §c§lAIRDROP-EDIT§8: §7" + level);

                    for (ItemStack items : Data.getAirDropController().getItemsFromAirdrop(level)) {
                        inventory.setItem(atomicInteger.get(), items);
                        atomicInteger.getAndIncrement();
                    }

                    player.openInventory(inventory);


                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }
                return true;
            }
            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eAirdrop §7<edit> <1-5>"
            );
        }
        return false;
    }
}
