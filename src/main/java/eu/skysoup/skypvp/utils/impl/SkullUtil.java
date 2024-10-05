package eu.skysoup.skypvp.utils.impl;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created: 05.02.2023 13:02
 *
 * @author thvf
 */
public class SkullUtil {

    public static ItemStack getCachedSkull(final UUID uuid) {
        return Data.getCachedSkulls().getOrDefault(uuid, ItemSkull.getSkullP(Data.getUuidFetcher().getName(uuid), Data.getUuidFetcher().getName(uuid)));
    }
}
