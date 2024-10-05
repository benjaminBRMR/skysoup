package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Created: 22.02.2023 20:05
 *
 * @author thvf
 */
public class FlightListener implements Listener {

    @EventHandler
    public void onPlayerFlight(final PlayerToggleFlightEvent event) {

        final Player player = event.getPlayer();

        if (player.getWorld().getName().equals("PvP") && !player.hasPermission(Permissions.TEAM.getPermission()))
            player.setAllowFlight(false);
    }
}
