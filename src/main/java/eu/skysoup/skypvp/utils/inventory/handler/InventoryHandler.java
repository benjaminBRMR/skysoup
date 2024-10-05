package eu.skysoup.skypvp.utils.inventory.handler;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: 05.02.2023 21:01
 *
 * @author thvf
 */
public class InventoryHandler implements Listener {

    private final Map<Player, SingletonInventory> playerInventoryMap;

    public InventoryHandler() {
        this.playerInventoryMap = new HashMap<Player, SingletonInventory>();
        Bukkit.getPluginManager().registerEvents(this, SkyPvP.getINSTANCE());
    }

    private void removePlayerFromGui(@NonNull final SingletonInventory inventory) {
        this.playerInventoryMap.remove(inventory.getPlayer());
    }

    public void addPlayerToGui(@NonNull final SingletonInventory inventory) {
        this.removePlayerFromGui(inventory);
        this.playerInventoryMap.put(inventory.getPlayer(), inventory);
    }

    private boolean isValidInventory(final Inventory inventory) {
        return inventory != null && inventory.getName() != null;
    }

    private boolean hasSameName(@Nonnull final Inventory inventory, @Nonnull final Inventory inventory2) {
        return inventory.getName().equalsIgnoreCase(inventory2.getName());
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && !event.isCancelled()) {
            final Player player = (Player) event.getWhoClicked();

            if (Data.getInventoryHandler().playerInventoryMap.containsKey(player)) {
                event.setCancelled(true);
                if (this.isValidInventory(event.getInventory()) && this.isValidInventory(event.getClickedInventory()) && this.isValidInventory(player.getOpenInventory().getTopInventory()) && this.hasSameName(event.getInventory(), event.getClickedInventory()) && this.hasSameName(event.getInventory(), player.getOpenInventory().getTopInventory()) && event.getInventory().getViewers().contains(player) && event.getClickedInventory().getViewers().contains(player) && event.getCurrentItem() != null) {
                    final SingletonInventory singletonInventory = this.playerInventoryMap.get(player);
                    singletonInventory.callClick(event.getSlot(), event.getClick());
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {

        final Player player = (Player) event.getPlayer();

        if (this.playerInventoryMap.containsKey(player)) {
            if (this.isValidInventory(event.getInventory()) && this.isValidInventory(player.getOpenInventory().getTopInventory()) && this.hasSameName(event.getInventory(), player.getOpenInventory().getTopInventory()) && event.getInventory().getViewers().contains(player)) {
                final SingletonInventory singletonInventory = this.playerInventoryMap.get(player);
                singletonInventory.onClose(player, event.getView());
                this.removePlayerFromGui(singletonInventory);
            }
        }
    }
}
