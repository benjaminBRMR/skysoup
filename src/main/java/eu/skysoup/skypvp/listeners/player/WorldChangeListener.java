package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * Created: 10.02.2023 11:06
 *
 * @author thvf
 */
public class WorldChangeListener implements Listener {

    @EventHandler
    public void onWorldChange(final PlayerChangedWorldEvent event) {

        final Player player = event.getPlayer();
        final CasinoController casinoController = Data.getCasinoController().getUser(player);

        if (event.getFrom().getName().equals("Casino") && casinoController.isLoggedIn()) {
            casinoController.logoutFromCasino();
        }

        if (player.getWorld().getName().equals("Casino")) {
            if (!casinoController.hasPlayerCasinoPass()) {
                if (Data.getLocation().existsLocation("spawn"))
                    player.teleport(Data.getLocation().getLocation("spawn"));
                return;
            }
            casinoController.loginToCasino();
        }
    }
}
