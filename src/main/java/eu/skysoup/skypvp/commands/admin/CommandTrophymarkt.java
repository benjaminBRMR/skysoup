package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.trophyshop.TrophyShopItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 30.01.2023 22:24
 *
 * @author thvf
 */
public class CommandTrophymarkt implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;


            final Player player = (Player) sender;

            if (args.length == 2 && args[0].equalsIgnoreCase("additem")) {

                final ItemStack itemStack = player.getItemInHand();

                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in deiner Hand!");
                    return true;
                }

                try {

                    final long preis = Long.parseLong(args[1]);
                    final long id = randomId();

                    if (Data.getTrophyShopController().getListedItems().size() >= 20) {
                        Data.getMessageUtil().sendMessage(player, "§cDer Trophäenshop ist bereits voll.");
                        return true;
                    }


                    final TrophyShopItem trophyShopItem = new TrophyShopItem(itemStack, preis, id);


                    Data.getTrophyShopController().addItemToShop(trophyShopItem);
                    Data.getMessageUtil().sendMessage(player, "§aDein Item wurde dem Trophäenshop hinzugefügt.");
                    Data.getMessageUtil().sendMessageToAll("§7Ein neues Item wurde dem Trophäenshop hinzugefügt.");

                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }

                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {

                try {
                    final long id = Long.parseLong(args[1]);

                    if (!Data.getTrophyShopController().isItemListed(id)) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Item befindet sich nicht im Trophäenshop!");
                        return true;
                    }

                    Data.getTrophyShopController().removeItemFromShop(id);
                    Data.getMessageUtil().sendMessage(player, "§aDein Item wurde aus dem Trophäenshop entnommen.");

                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("setpreis")) {

                try {
                    final long id = Long.parseLong(args[1]);
                    final long preis = Long.parseLong(args[2]);

                    if (!Data.getTrophyShopController().isItemListed(id)) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Item befindet sich nicht im Trophäenshop!");
                        return true;
                    }

                    Data.getTrophyShopController().setPreis(id, preis);
                    Data.getMessageUtil().sendMessage(player, "§aDer Preis des Items wurde erfolgreich gesetzt.");

                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {


                if (Data.getTrophyShopController().getListedItems().size() < 1) {
                    Data.getMessageUtil().sendSyntax(player, "§cAktuell befinden sich keine Items im Trophäenshop!");
                    return true;
                }

                AtomicInteger atomicInteger = new AtomicInteger(1);
                for (Long id : Data.getTrophyShopController().getListedItems()) {

                    final String displayname = (Data.getTrophyShopController().getItemFromID(id).getItemMeta().hasDisplayName() ? Data.getTrophyShopController().getItemFromID(id).getItemMeta().getDisplayName() : "§c§lKEINER");

                    Data.getMessageUtil().sendMessage(player, "§8#§6§o" + atomicInteger.get() + " §8| §7ID§8: §f" + id);
                    Data.getMessageUtil().sendMessage(player, "§8┌ §7Item§8: §f" + Data.getTrophyShopController().getItemFromID(id).getType().name());
                    Data.getMessageUtil().sendMessage(player, "§8├ §7Displayname§8: §f" + displayname);
                    Data.getMessageUtil().sendMessage(player, "§8└ §7Preis§8: §f" + NumberFormat.getInstance().format(Data.getTrophyShopController().getPreisFromID(id)));

                    atomicInteger.getAndIncrement();
                }

                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eTrophyshop §7<additem> <preis>",
                    "§8/§eTrophyshop §7<remove> <id>",
                    "§8/§eTrophyshop §7<setpreis> <id> <preis>",
                    "§8/§eTrophyshop §7<list>");
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
        long id = Data.getRandom().nextInt(20);
        for (Long all : Data.getTrophyShopController().getListedItems()) {
            if (all == id) return randomId();
        }
        recursionCounter = 0;
        return id;
    }

}
