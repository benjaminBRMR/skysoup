package eu.skysoup.skypvp.controller.other;

import com.google.common.util.concurrent.AtomicDouble;
import eu.skysoup.skypvp.SkyPvP;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created: 13.02.2023 15:49
 *
 * @author thvf
 */
public class HologramController {


    public HologramController() {
    }

    public void spawnTempHologram(final long timeAliveSeconds, final Location location, final String... lines) {

        AtomicDouble atomicDouble = new AtomicDouble(0.0);
        location.setY(location.getY() + 3);
        for (String s : lines) {
            final ArmorStand armorStand = location.getWorld().spawn(location.subtract(0, atomicDouble.get(), 0), ArmorStand.class);

            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setCustomNameVisible((!s.contains("%empty%")));
            armorStand.setCustomName(s);
            armorStand.setGravity(false);
            atomicDouble.getAndAdd(0.1);

            new BukkitRunnable() {
                @Override
                public void run() {
                    armorStand.remove();
                }
            }.runTaskLater(SkyPvP.getINSTANCE(), timeAliveSeconds * 20);
        }


    }
}
