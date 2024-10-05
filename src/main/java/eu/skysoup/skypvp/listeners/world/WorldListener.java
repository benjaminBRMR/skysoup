package eu.skysoup.skypvp.listeners.world;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created: 24.01.2023 11:25
 *
 * @author thvf
 */
public class WorldListener implements Listener {

    @EventHandler
    public void onHunger(final FoodLevelChangeEvent event) {

        final Player player = (Player) event.getEntity();

        if (player.getWorld().getName().equals("SoupPlots-V2") || player.getWorld().getName().equals("Farmwelt") || player.getWorld().getName().equals("moneymaker")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onWeatherChange(final WeatherChangeEvent event) {
        event.setCancelled(event.toWeatherState());
    }

    private final String[] antiMobsWorlds = new String[]{"skypvp-3", "Casino", "Arena", "PvP", "moneymaker"};

    @EventHandler(priority = EventPriority.HIGH)
    public void onCreatureSpawn(final CreatureSpawnEvent event) {

        for (String world : antiMobsWorlds) {
            if (event.getEntity() instanceof Monster || event.getEntity() instanceof Animals && event.getEntity().getWorld().getName().equals(world)) {
                event.setCancelled(true);
            }
        }
    }

}
