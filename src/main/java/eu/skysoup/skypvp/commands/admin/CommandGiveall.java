package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.Objects;

/**
 * Created: 09.02.2023 16:07
 *
 * @author thvf
 */
public class CommandGiveall implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.GIVEALL.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 1 && args[0].equalsIgnoreCase("hand")) {

                final ItemStack item = player.getItemInHand();

                if (item == null || item.getType() == Material.AIR) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in der Hand.");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 18.4F);
                    return true;
                }

                final int amount = item.getAmount();
                item.setAmount(amount);


                for (Player all : Bukkit.getOnlinePlayers()) {

                    if (Objects.equals(all.getName(), player.getName())) continue;

                    Data.getPlayerUtil().addItem(all, item);
                    Data.getMessageUtil().sendMessage(all, "§7Alle Spieler haben §e" + amount + "§6x §e" + (item.getItemMeta().getDisplayName() != null ? item.getItemMeta().getDisplayName() + " §7erhalten§8." : item.getType().name().toUpperCase() + " §7erhalten§8."));
                    all.playSound(all.getLocation(), Sound.LEVEL_UP, 55, 18.4F);
                }
                Data.getMessageUtil().sendMessage(player, "§7Alle Spieler haben §e" + amount + "§6x §e" + (item.getItemMeta().getDisplayName() != null ? item.getItemMeta().getDisplayName() + " §7erhalten§8." : item.getType().name().toUpperCase() + " §7erhalten§8."));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 55, 18.4F);
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("inventory") || args.length == 1 && args[0].equalsIgnoreCase("inventar")) {

                ItemStack[] items = player.getInventory().getContents();

                if (Data.getPlayerUtil().isInventoryEmpty(player)) {
                    Data.getMessageUtil().sendMessage(player, "§cDu kannst kein leeres Inventar vergeben.");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 18.4F);
                    return true;
                }

                for (Player all : Bukkit.getOnlinePlayers()) {
                    for (ItemStack item : items) {
                        if (item == null || item.getType() == Material.AIR) continue;
                        Data.getPlayerUtil().addItem(all, item);
                    }
                    Data.getMessageUtil().sendMessage(all, "§7Alle Spieler haben das §eInventar §7von §e" + player.getName() + " §7erhalten§8.");
                    all.playSound(all.getLocation(), Sound.NOTE_PLING, 35, 18.4F);

                }

                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("money")) {

                try {
                    final long money = Long.parseLong(args[1]);

                    Bukkit.getOnlinePlayers().forEach(all -> {
                        final UserController userHandler = new UserController().getUserByUUID(all.getUniqueId());

                        userHandler.setStatisticFromUser(StatisticTypes.MONEY, userHandler.getStatisticByType(StatisticTypes.MONEY) + money);
                        Data.getMessageUtil().sendMessage(all, "§7Alle Spieler haben §6$§e" + NumberFormat.getInstance().format(money) + " §7Tokens erhalten§8.");
                        all.playSound(all.getLocation(), Sound.NOTE_PLING, 35, 18.4F);
                    });
                } catch (NumberFormatException exception) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an.");
                }

                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("back")) {

                int i = 0;
                for (Player all : Bukkit.getOnlinePlayers()) {

                    ItemStack[] contents = all.getInventory().getContents();

                    for (ItemStack content : contents) {
                        if (content == null || content.getType() == Material.AIR) continue;
                        if (all.getName().equals(player.getName())) continue;
                        if (content.getType() == player.getItemInHand().getType()) {
                            i++;
                            all.getInventory().remove(content);
                        }
                    }
                }

                if (i > 0) {
                    Data.getMessageUtil().sendMessage(player, "§aDas Item wurde §7" + i + " §aSpielern entzogen.");
                } else {
                    Data.getMessageUtil().sendMessage(player, "§cKein Spieler hat das Item in deiner Hand.");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 18.4F);
                }
                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eGiveall §7<hand>",
                    "§8/§eGiveall §7<inventar>",
                    "§8/§eGiveall §7<back>",
                    "§8/§eGiveall §7<money> §7<anzahl>");


        }
        return false;
    }
}
