package eu.skysoup.skypvp.utils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * JavaDoc this file!
 * Created: 13.01.2023
 *
 * @author thvf
 */
public class Cooldown {


    UUID uuid;
    Config config;

    public Cooldown(final UUID uuid) {
        this.uuid = uuid;
        this.config = new Config("plugins/SkySoup/cooldowns/", uuid + ".yml");
    }

    public void addCooldown(final String name, final long time, final TimeUnit timeUnit) {
        final long end = System.currentTimeMillis() + timeUnit.toMillis(time);
        this.config.getConfig().set(name, end);
        this.config.saveConfig();
    }

    public long getRemainingTime(final String name) {
        return this.config.getConfig().getLong(name) - System.currentTimeMillis();
    }

    public long getTime(final String name) {
        return this.config.getConfig().getLong(name);
    }

    public boolean isOnCooldown(final String name) {
        final long end = this.getTime(name) - System.currentTimeMillis();
        if (end <= 0L) {
            this.removeCooldown(name);
        }
        return end > 0L;
    }

    public void removeCooldown(final String name) {
        this.config.getConfig().set(name, (Object) null);
        this.config.saveConfig();
    }
}
