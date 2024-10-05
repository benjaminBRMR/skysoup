package eu.skysoup.skypvp.data;

import eu.skysoup.skypvp.data.implementorings.ClantagTypes;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created: 05.02.2023 19:33
 *
 * @author thvf
 */
public class InventoryMaps {


    public final HashMap<Player, ClantagTypes> acceptHashMap = new HashMap<>();


    public final HashMap<UUID, BukkitTask> schmelzTask = new HashMap<>();
    public final HashMap<UUID, Integer> schmelzProgress = new HashMap<>();


}
