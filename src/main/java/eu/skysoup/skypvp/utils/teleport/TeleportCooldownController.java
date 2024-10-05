package eu.skysoup.skypvp.utils.teleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created: 06.02.2023 12:08
 *
 * @author thvf
 */
public class TeleportCooldownController {
    private final Map<TeleportCooldownCategory, Map<Object, TeleportCooldown>> registry = new HashMap<>();

    /**
     * Create a new instance of CooldownManager
     *
     * @param plugin Your plugin instance
     * @param delay  The delay between each cooldown check
     */
    public TeleportCooldownController(@Nonnull Plugin plugin, long delay) {
        Bukkit.getScheduler().runTaskTimer(plugin, () ->
        {
            for (Map<Object, TeleportCooldown> category : registry.values()) {
                Iterator<Map.Entry<Object, TeleportCooldown>> iterator = category.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Object, TeleportCooldown> entry = iterator.next();
                    TeleportCooldown cooldown = entry.getValue();
                    if (!cooldown.isCancelled()) {
                        cooldown.tick();
                    }
                    if (cooldown.isCompleted() || cooldown.isCancelled()) {
                        iterator.remove();
                    }
                }
            }
        }, 0L, delay);
    }

    /**
     * Create a new cooldown
     * If a value is already mapped to the category/owner, the value will be override
     *
     * @param category Category of the cooldown
     * @param owner    Owner of the cooldown
     * @param time     Time of the cooldown
     * @param unit     TimeUnit used by the cooldown
     * @return the cooldown created
     */
    @Nonnull
    public TeleportCooldown create(@Nonnull TeleportCooldownCategory category, @Nonnull Object owner, int time, @Nonnull TimeUnit unit) {
        Map<Object, TeleportCooldown> cooldowns = registry.computeIfAbsent(category, k -> new HashMap<>());
        return cooldowns.compute(owner, (k, v) -> new TeleportCooldown(time, unit));
    }

    /**
     * Get an existing cooldown
     *
     * @param category Category of the cooldown
     * @param owner    Owner of the cooldown
     * @return The cooldown mapped to category/owner or null if none
     */
    @Nullable
    public TeleportCooldown get(@Nonnull TeleportCooldownCategory category, @Nonnull Object owner) {
        Map<Object, TeleportCooldown> cooldowns = registry.get(category);
        return cooldowns != null ? cooldowns.get(owner) : null;
    }
}
