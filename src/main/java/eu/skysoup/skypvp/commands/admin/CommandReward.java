package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.inventories.RewardInventory;
import eu.skysoup.skypvp.reward.RewardItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 10.02.2023 12:48
 *
 * @author thvf
 */
public class CommandReward implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), false)) {
                new RewardInventory(player).openGUI();
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("set")) {

                final ItemStack itemStack = player.getItemInHand();

                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in deiner Hand!");
                    return true;
                }

                final long day = Long.parseLong(args[1]);

                if (day < 1 || day > 31) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe einen Tag zwischen 1-31 an!");
                    return true;
                }

                if (Data.getRewardController().getItemStackById(Data.getRewardController().getIDByDay(day)) == null) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Tag hat noch kein Item!");
                    return true;
                }


                Data.getRewardController().setItem(day, itemStack);
                Data.getMessageUtil().sendMessage(player, "§aDer Tag §7" + day + " §ahat nun ein neues Item!");

                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("add")) {

                final ItemStack itemStack = player.getItemInHand();

                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in deiner Hand!");
                    return true;
                }

                long id = randomId();

                try {
                    final long day = Long.parseLong(args[1]);

                    if (day < 1 || day > 31) {
                        Data.getMessageUtil().sendMessage(player, "§cBitte gebe einen Tag zwischen 1-31 an!");
                        return true;
                    }


                    if (Data.getRewardController().isItemListedByDay(day)) {
                        Data.getMessageUtil().sendMessage(player, "§cDieser Tag hat bereits ein Item!");
                        return true;
                    }

                    final RewardItem rewardItem = new RewardItem(id, day, itemStack);
                    Data.getRewardController().addItem(rewardItem);
                    Data.getMessageUtil().sendMessage(player, "§aDas Item wurde erfolgreich gesetzt!");

                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }

                return true;
            }

            // REMOVE ARGUMENT UM ITEMS ZU ENTFERNEN
            // ALLE ITEMS JEDEN MONAT LEEREN

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                if (Data.getRewardController().getItems().size() < 1) {
                    Data.getMessageUtil().sendSyntax(player, "§cAktuell befinden sich keine Items im Rewardsinventar!");
                    return true;
                }

                AtomicInteger atomicInteger = new AtomicInteger(1);
                for (Long all : Data.getRewardController().getItems()) {
                    Data.getMessageUtil().sendMessage(player, "§8#§6§o" + atomicInteger.get() + " §8| §7ID§8: §f" + all);
                    Data.getMessageUtil().sendMessage(player, "§8┌ §7Item§8: §f" + Data.getRewardController().getItemStackById(all));
                    Data.getMessageUtil().sendMessage(player, "§8└ §7Tag§8: §f" + Data.getRewardController().getDayByID(all));
                    atomicInteger.getAndIncrement();
                }

                return true;
            }
            new RewardInventory(player).openGUI();
            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eReward §7<add> <day>",
                    "§8/§eReward §7<set> <day>",
                    "§8/§eReward §7<remove> <day>"
            );
        }
        return false;
    }

    private static final int MAX_RECURSIVE_CALLS = 1000;
    private static int recursionCounter = 0;

    private static long randomId() {
        if (recursionCounter++ >= MAX_RECURSIVE_CALLS) {
            recursionCounter = 0;
            return -1;
        }
        long id = Data.getRandom().nextInt(31);
        for (Long all : Data.getRewardController().getItems()) {
            if (all == id) return randomId();
        }
        recursionCounter = 0;
        return id;
    }

}
