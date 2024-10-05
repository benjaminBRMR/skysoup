package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.impl.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;

/**
 * Created: 03.02.2023
 *
 * @author benni
 */
public final class CommandRepair implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
            if (args.length == 0) {

                if (player.hasPermission(Permissions.ADMIN.getPermission())) {
                    Data.getMessageUtil().sendSyntax(player, "§8/§erepair §7<inv§8, §7hand>");
                    return true;
                }

                final short currentdura = player.getItemInHand().getDurability();
                final int repairtokens = currentdura * 50;

                if (player.getItemInHand().getType() == Material.AIR || player.getItemInHand() == null) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in deiner Hand!");
                    return true;
                }

                if (currentdura <= 1) {
                    Data.getMessageUtil().sendMessage(player, "§cDein Item ist bereits voll repariert!");
                    return true;
                }

                if (!Utils.hasPlayerItemInInventory(player, "§8» §7Gutschein§8: §8§lREPAIR §7§lTOKEN")) {
                    Data.getMessageUtil().sendMessage(player, "§cDu hast keinen Repair-Token in deinem Inventar!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                    return true;
                }

                if (userController.getStatisticByType(StatisticTypes.MONEY) < repairtokens) {
                    Data.getMessageUtil().sendMessage(player, "§cDafür hast du nicht genug Tokens! §8(-§c" + (repairtokens - userController.getStatisticByType(StatisticTypes.MONEY)) + "§8)");
                    return true;
                }
                for (ItemStack items : player.getInventory().getContents()) {

                    if (items == null || items.getType() == Material.AIR) continue;
                    if (!items.getItemMeta().hasDisplayName()) continue;

                    if (items.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §8§lREPAIR §7§lTOKEN")) {

                        if (items.getItemMeta().getLore().get(3) == null) {
                            Data.getMessageUtil().sendMessage(player, "§cDieser Repairtoken ist ungültig und wurde entfernt!");
                            player.getInventory().remove(items);
                        }

                        if (items.getAmount() > 1) {
                            items.setAmount(items.getAmount() - 1);
                        } else {
                            player.getInventory().remove(items);
                        }

                        Data.getMessageUtil().sendMessage(player, "§7Du hast einen §8§lREPAIR §7§lTOKEN §7eingelöst§8.");
                        Data.getMessageUtil().sendMessage(player, "§7Außerdem hast du §6$§e" + NumberFormat.getInstance().format(repairtokens) + " §7bezahlt§8.");

                        player.playSound(player.getLocation(), Sound.BURP, 1, 0.1F);
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - repairtokens);
                        player.getItemInHand().setDurability((short) 0);

                        return true;
                    }

                }
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("inv") && Data.getPermissionUtil().hasPermission(player, Permissions.ADMIN.getPermission(), true)) {

                for (ItemStack all : player.getInventory().getContents()) {
                    if (all == null || all.getType() == Material.AIR) continue;
                    all.setDurability((short) 0);
                }

                for (ItemStack all : player.getInventory().getArmorContents()) {
                    if (all == null || all.getType() == Material.AIR) continue;
                    all.setDurability((short) 0);
                }

                Data.getMessageUtil().sendMessage(player, "§7Dein Inventar wurde §aerfolgreich §7repariert§8.");
                player.playSound(player.getLocation(), Sound.BURP, 1, 0.1F);
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("hand") && Data.getPermissionUtil().hasPermission(player, Permissions.ADMIN.getPermission(), true)) {

                if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in deiner Hand!");
                    return true;
                }

                player.getItemInHand().setDurability((short) 0);

                Data.getMessageUtil().sendMessage(player, "§7Dein Item wurde §aerfolgreich §7repariert§8.");
                player.playSound(player.getLocation(), Sound.BURP, 1, 0.1F);
                return true;
            }

            if (!player.hasPermission(Permissions.ADMIN.getPermission())) {
                Data.getMessageUtil().sendSyntax(player, "§8/§erepair");
                return true;
            }

            Data.getMessageUtil().sendSyntax(player, "§8/§erepair §7<inv§8, §7hand>");
            return true;

        }
        return false;
    }
}
