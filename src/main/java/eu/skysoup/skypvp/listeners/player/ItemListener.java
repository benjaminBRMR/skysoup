package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created: 21.02.2023 13:07
 *
 * @author thvf
 */
public class ItemListener implements Listener {

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {

        final Player player = event.getPlayer();

        if (!Data.getToggleController().isToggled(ToggleController.Types.DROPS)) {
            Data.getMessageUtil().sendMessage(player, "§cAktuell kannst du keine Items droppen!");
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onItemPickup(final PlayerPickupItemEvent event) {
        final Player player = event.getPlayer();

        if (!Data.getToggleController().isToggled(ToggleController.Types.PICKUP)) {
            event.setCancelled(true);
            return;
        }
    }
}
