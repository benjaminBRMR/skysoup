package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.inventories.GutscheinInventory;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;

/**
 * Created: 02.02.2023 21:09
 *
 * @author thvf
 */
public class CommandGutschein implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;

            final Player player = (Player) sender;


            if (args.length == 0) {
                new GutscheinInventory(player).openGUI();
                return true;
            }

            if (args.length == 2) {
                try {


                    GutscheinTypes gutscheinType;
                    long amount;
                    String displayName;
                    ItemStack item;
                    String[] lore;


                    gutscheinType = GutscheinTypes.valueOf(args[0].toUpperCase());
                    amount = Long.parseLong(args[1]);
                    displayName = "§8» §7Gutschein§8: §6§l" + gutscheinType.name().toUpperCase();
                    item = new ItemStack(Material.DOUBLE_PLANT);
                    lore = new String[]{
                            "§8§oRechtsklicke, um diesen Gutschein einzulösen.",
                            "",
                            " §8┌ §7Gutschein-Type§8: §e§l" + gutscheinType.name().toUpperCase(),
                            " §8└ §7Gutschein-Wert§8: §e§l" + NumberFormat.getInstance().format(amount),
                            "",
                    };


                    final ItemStack gutscheinItem = new ItemBuilder(item).setName(displayName).lore(lore);

                    player.getInventory().addItem(gutscheinItem);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§aDu hast einen Gutschein erhalten.");
                    Data.getMessageUtil().sendMessage(player, "§aGutschein-Type: §f" + gutscheinType.name().toUpperCase());
                    Data.getMessageUtil().sendMessage(player, "§aGutschein-Wert: §f" + NumberFormat.getInstance().format(amount));


                } catch (Exception ignored) {
                    Data.getMessageUtil().sendSyntax(player, "§8/§eGutschein §7<type> §7<wert>");
                    player.sendMessage("§r");
                    Data.getMessageUtil().sendMessage(player, "§7Folgende GutscheinTypes existieren§8:");
                    Data.getMessageUtil().sendSyntax(player, "§r");
                    for (GutscheinTypes type : GutscheinTypes.values()) {
                        Data.getMessageUtil().sendMessage(player, "§e" + type.name().toLowerCase());
                    }
                }
                return true;

            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eGutschein §7<type> §7<wert>");
            player.sendMessage("§r");
            Data.getMessageUtil().sendMessage(player, "§7Folgende GutscheinTypes existieren§8:");
            Data.getMessageUtil().sendSyntax(player, "§r");
            for (GutscheinTypes type : GutscheinTypes.values()) {
                Data.getMessageUtil().sendMessage(player, "§e" + type.name().toLowerCase());
            }
        }
        return false;
    }
}
