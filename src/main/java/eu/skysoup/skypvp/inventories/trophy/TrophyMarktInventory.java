package eu.skysoup.skypvp.inventories.trophy;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 01.02.2023 13:00
 *
 * @author thvf
 */
public class TrophyMarktInventory extends SingletonInventory {

    public TrophyMarktInventory(final Player player) {
        super(InventoryTitles.TROPHYMARKT.getName(), Rows.CHEST_ROW_6, player);

        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final long trophys = userController.getStatisticByType(StatisticTypes.TROPHYS);


        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);

        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);


        AtomicInteger atomicInteger = new AtomicInteger(11);
        AtomicInteger count = new AtomicInteger(1);
        for (long id : Data.getTrophyShopController().getListedItems()) {

            final long preis = Data.getTrophyShopController().getPreisFromID(id);
            final ItemStack itemStack = Data.getTrophyShopController().getItemFromID(id);
            final ItemBuilder itemBuilder = new ItemBuilder(itemStack).setName("§8┃ §e§lITEM §8: §7" + (itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().name()) + " §8- §8#§6§o" + count.get()).lore(
                    "",
                    "§8┌ §7Item§8: §e" + itemStack.getType(),
                    "§8├ §7Preis§8: §e" + NumberFormat.getInstance().format(preis),
                    "§8└ §7ID§8: §e" + id,
                    "",
                    "§8├ §7Klick §8┃ §7Erwerbe dieses Item.",
                    ""
            ).addFlag(ItemFlag.HIDE_ATTRIBUTES);

            if (atomicInteger.get() == 16 || atomicInteger.get() == 17 || atomicInteger.get() == 18 || atomicInteger.get() == 19)
                atomicInteger.set(20);
            if (atomicInteger.get() == 25 || atomicInteger.get() == 26 || atomicInteger.get() == 27 || atomicInteger.get() == 28)
                atomicInteger.set(29);
            if (atomicInteger.get() == 34 || atomicInteger.get() == 35 || atomicInteger.get() == 37 || atomicInteger.get() == 37)
                atomicInteger.set(38);
            if (atomicInteger.get() > 42) return;

            setItem(atomicInteger.get(), itemBuilder, (click, type) -> {


                if (trophys < preis) {
                    Data.getMessageUtil().sendMessage(player, "§cDafür hast du nicht genug Trophäen!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                    return;
                }

                userController.setStatisticFromUser(StatisticTypes.SPENTTROPHIES, userController.getStatisticByType(StatisticTypes.SPENTTROPHIES) + preis);
                userController.setStatisticFromUser(StatisticTypes.TROPHYS, trophys - preis);
                Data.getMessageUtil().sendMessage(player, "§7Du hast erfolgreich §6" + itemBuilder.getAmount() + "§8x §8'§e§l" + (itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().name()) + "§8' §7erworben§8.");
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 0.1F);
                player.getInventory().addItem(itemStack);

            });

            atomicInteger.getAndIncrement();
            count.getAndIncrement();

        }


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
        new BukkitRunnable() {
            @Override
            public void run() {
                new TrophyInventory(player).openGUI();
            }
        }.runTaskLater(SkyPvP.getINSTANCE(), 0L);
    }
}
