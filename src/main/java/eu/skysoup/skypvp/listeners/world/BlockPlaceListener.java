package eu.skysoup.skypvp.listeners.world;

import eu.skysoup.skypvp.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created: 10.02.2023 20:58
 *
 * @author thvf
 */
public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (event.getItemInHand() == null || event.getItemInHand().getItemMeta().getDisplayName() == null) return;


        if (event.getItemInHand().getItemMeta().getDisplayName().contains("§8» §7Gutschein§8:")) {
            event.setCancelled(true);
            return;
        }

        if (event.getItemInHand().getItemMeta().getDisplayName().contains("§8» §7AirDrop§8: §cLevel")) {
            event.setCancelled(true);
            final long level = Long.parseLong(event.getItemInHand().getItemMeta().getDisplayName().replaceAll("§8» §7AirDrop§8: §cLevel", "").trim());
            Data.getPlayerUtil().removeHand(player);
            Data.getAirDropController().spawnAirDrop(player, event.getBlock().getLocation(), level);
            return;
        }
    }
}
