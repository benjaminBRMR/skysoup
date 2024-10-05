package eu.skysoup.skypvp.utils;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * JavaDoc this file!
 * Created: 07.01.2023
 *
 * @author thvf
 */
public class TempCooldown {

    private HashMap<UUID, Long> cooldowns;


    public TempCooldown() {
        cooldowns = Maps.newHashMap();
    }

    public TempCooldown addPlayerToCooldown(final UUID uuid, final Integer seconds) {
        cooldowns.put(uuid, System.currentTimeMillis() + (seconds * 1000));
        return this;
    }

    public TempCooldown removePlayerFromCooldown(final UUID uuid) {
        if (cooldowns.containsKey(uuid))
            cooldowns.remove(uuid);
        return this;
    }

    public boolean isDone(final UUID uuid) {
        if (cooldowns.containsKey(uuid)) {
            if (cooldowns.get(uuid) <= System.currentTimeMillis()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public Integer getReminding(final UUID uuid) {
        if (cooldowns.containsKey(uuid)) {
            return Math.toIntExact((cooldowns.get(uuid) - System.currentTimeMillis()) / 1000);
        } else {
            return null;
        }
    }

    public TempCooldown addPlayerToCooldown(final Player player, final Integer seconds) {
        addPlayerToCooldown(player.getUniqueId(), seconds);
        return this;
    }

    public TempCooldown removePlayerFromCooldown(final Player player) {
        removePlayerFromCooldown(player.getUniqueId());
        return this;
    }

    public boolean isDone(final Player player) {
        return isDone(player.getUniqueId());
    }

    public Integer getReminding(final Player player) {
        return getReminding(player.getUniqueId());
    }
}
