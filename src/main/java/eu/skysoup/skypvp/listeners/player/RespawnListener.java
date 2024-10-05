package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created: 01.02.2023 12:52
 *
 * @author thvf
 */
public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event) {

        final Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Data.getLocation().existsLocation("spawn"))
                    player.teleport(Data.getLocation().getLocation("spawn"));
            }
        }.runTaskLater(SkyPvP.getINSTANCE(), 3L);
    }
}
