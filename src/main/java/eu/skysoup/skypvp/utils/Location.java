package eu.skysoup.skypvp.utils;

import org.bukkit.Bukkit;

/**
 * JavaDoc this file!
 * Created: 06.01.2023
 *
 * @author thvf
 */
public class Location {

    final Config config;

    public Location() {
        this.config = new Config("plugins/SkySoup/locations/", "config.yml");
    }

    public void setLocation(final org.bukkit.Location location, final String name) {
        config.getConfig().set("." + name + ".welt", location.getWorld().getName());
        config.getConfig().set("." + name + ".x", location.getX());
        config.getConfig().set("." + name + ".y", location.getY());
        config.getConfig().set("." + name + ".z", location.getZ());
        config.getConfig().set("." + name + ".yaw", location.getYaw());
        config.getConfig().set("." + name + ".pitch", location.getPitch());
        config.saveConfig();
    }

    public void deleteLocation(final String name) {
        config.getConfig().set("." + name + ".welt", null);
        config.getConfig().set("." + name + ".x", null);
        config.getConfig().set("." + name + ".y", null);
        config.getConfig().set("." + name + ".z", null);
        config.getConfig().set("." + name + ".yaw", null);
        config.getConfig().set("." + name + ".pitch", null);
        config.saveConfig();

    }

    public org.bukkit.Location getLocation(final String name) {
        String welt = config.getConfig().getString("." + name + ".welt");
        double x = config.getConfig().getDouble("." + name + ".x");
        double y = config.getConfig().getDouble("." + name + ".y");
        double z = config.getConfig().getDouble("." + name + ".z");
        float yaw = (float) config.getConfig().getDouble("." + name + ".yaw");
        float pitch = (float) config.getConfig().getDouble("." + name + ".pitch");
        return new org.bukkit.Location(Bukkit.getWorld(welt), x, y, z, yaw, pitch);
    }

    public boolean existsLocation(final String name) {
        return config.getConfig().get("." + name + ".welt") != null;
    }
}
