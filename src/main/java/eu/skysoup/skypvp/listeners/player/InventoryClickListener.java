package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.commands.player.CommandInvsee;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created: 09.02.2023 11:58
 *
 * @author thvf
 */
public class InventoryClickListener implements Listener {


    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (CommandInvsee.getInvseeMap().containsKey(player) && !player.hasPermission(Permissions.ADMIN.getPermission())) {
            event.setCancelled(true);
            return;
        }

        if (event.getInventory().getName().contains("§8┃ §9§lLAGERPLATZ §8: §7")) {
            final String owner = event.getInventory().getName().replaceAll("§8┃ §9§lLAGERPLATZ §8: §7", "").trim();


            if (!player.getName().equalsIgnoreCase(owner) && player.hasPermission(Permissions.LAGERPLATZCLICK.getPermission()))
                event.setCancelled(false);
            if (!player.getName().equalsIgnoreCase(owner) && !player.hasPermission(Permissions.LAGERPLATZCLICK.getPermission()))
                event.setCancelled(true);
            return;
        }


    }
}
