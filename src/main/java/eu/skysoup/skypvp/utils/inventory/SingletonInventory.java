package eu.skysoup.skypvp.utils.inventory;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.inventory.objects.ClickableInventoryItem;
import eu.skysoup.skypvp.utils.inventory.objects.InventoryItemAction;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: 05.02.2023 20:49
 *
 * @author thvf
 */

@Getter
public abstract class SingletonInventory {

    final Inventory inventory;
    final String inventoryTitle;
    final Map<Integer, ClickableInventoryItem> inventoryItems = new HashMap<>();
    final Player player;

    public enum Rows {

        CHEST_ROW_1(1 * 9),
        CHEST_ROW_2(2 * 9),
        CHEST_ROW_3(3 * 9),
        CHEST_ROW_4(4 * 9),
        CHEST_ROW_5(5 * 9),
        CHEST_ROW_6(6 * 9);

        @Getter
        final int size;

        Rows(final int size) {
            this.size = size;
        }
    }

    public SingletonInventory(@NonNull final String name, @NonNull final Rows rows, @NonNull final Player player) {
        this.inventoryTitle = name;
        this.inventory = Bukkit.createInventory(null, rows.size, name);
        (this.player = player).closeInventory();
    }


    public void setItem(final int slot, final ItemStack itemStack, final InventoryItemAction inventoryItemAction) {
        inventoryItems.put(slot, new ClickableInventoryItem(itemStack, inventoryItemAction));
        this.getInventory().setItem(slot, itemStack);
    }

    public void openGUI() {
        Data.getInventoryHandler().addPlayerToGui(this);
        this.player.openInventory(this.inventory);
    }

    public abstract void onClose(@NonNull final Player player, @NonNull final InventoryView inventoryView);

    public void callClick(@NonNull final int slot, @NonNull ClickType clickType) {

        if (this.inventory == null) return;
        if (this.inventoryItems.get(slot) == null) return;
        if (this.inventoryItems.get(slot).getClickAction() == null) return;
        if (this.inventoryItems.get(slot).getItemStack().getItemMeta().getDisplayName().equals("§r") ||
                this.inventoryItems.get(slot).getItemStack().getItemMeta().getDisplayName() == null) return;

        if (this.inventoryItems.containsKey(slot)) this.inventoryItems.get(slot).click(player, clickType);
    }

    public void fillInventory(final ItemStack itemStack) {
        for (int i = 0; i < this.inventory.getSize(); i++) {
            this.inventory.setItem(i, itemStack);
        }
    }

    public void fillBorders(final ItemStack itemStack) {
        int size = inventory.getSize();
        int rows = (size + 1) / 9;

        for (int i = 0; i < rows * 9; ++i) {
            if (i <= 8 || i >= rows * 9 - 9 || i == 9 || i == 18 || i == 27 || i == 36 || i == 17 || i == 26 || i == 35 || i == 44) {
                this.inventory.setItem(i, itemStack);
            }
        }
    }

    public void fillCorners(final ItemStack itemStack) {
        this.inventory.setItem(0, itemStack);
        this.inventory.setItem(8, itemStack);
        this.inventory.setItem(getInventory().getSize() - 1, itemStack);
        this.inventory.setItem(getInventory().getSize() - 9, itemStack);
    }


    public void updateItemStackLore(Inventory inventory, int index, String... newLore) {
        ItemStack itemStack = inventory.getItem(index);

        if (itemStack == null) {
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        itemMeta.setLore(Arrays.asList(newLore));
        itemStack.setItemMeta(itemMeta);

        inventory.setItem(index, itemStack);
    }


}
