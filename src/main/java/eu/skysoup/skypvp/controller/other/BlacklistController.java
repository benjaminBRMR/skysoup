package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created: 10.02.2023 19:53
 *
 * @author thvf
 */
public class BlacklistController {

    Config config;


    public BlacklistController() {
        this.config = new Config("plugins/SkySoup/config/", "blacklist.yml");
    }

    public List<String> getBlacklisted() {
        return (this.config.getConfig().get(".BlacklistedUser") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedUser"));
    }

    public List<String> getBlacklistedIPs() {
        return (this.config.getConfig().get(".BlacklistedIps") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedIps"));
    }

    public void addPlayer(final UUID uuid) {
        final List<String> list = (this.config.getConfig().get(".BlacklistedUser") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedUser"));
        list.add(uuid.toString());
        this.config.getConfig().set(".BlacklistedUser", list);
        this.config.saveConfig();
    }

    public void removePlayer(final UUID uuid) {
        final List<String> list = (this.config.getConfig().get(".BlacklistedUser") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedUser"));
        list.remove(uuid.toString());
        this.config.getConfig().set(".BlacklistedUser", list);
        this.config.saveConfig();
    }

    public boolean isPlayerBlacklisted(final UUID uuid) {
        final List<String> list = (this.config.getConfig().get(".BlacklistedUser") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedUser"));
        return list.contains(uuid.toString());
    }

    public void addIP(final String ip) {
        final List<String> list = (this.config.getConfig().get(".BlacklistedIps") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedIps"));
        list.add(ip);
        this.config.getConfig().set(".BlacklistedIps", list);
        this.config.saveConfig();
    }

    public void removeIp(final String ip) {
        final List<String> list = (this.config.getConfig().get(".BlacklistedIps") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedIps"));
        list.remove(ip);
        this.config.getConfig().set(".BlacklistedIps", list);
        this.config.saveConfig();
    }

    public boolean isIPBlacklisted(final String ip) {
        final List<String> list = (this.config.getConfig().get(".BlacklistedIps") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".BlacklistedIps"));
        return list.contains(ip);
    }
}
