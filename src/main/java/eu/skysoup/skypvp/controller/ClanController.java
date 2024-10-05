package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.data.implementorings.ClantagTypes;
import eu.skysoup.skypvp.data.implementorings.WappenTypes;
import eu.skysoup.skypvp.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created: 23.01.2023 13:15
 *
 * @author thvf
 */
public class ClanController {

    final Config config;

    public ClanController() {
        this.config = new Config("plugins/SkySoup/clan/", "clans.yml");
    }

    public void createClan(final String clanName, final Player besitzer) {
        this.config.getConfig().set("Spieler." + besitzer.getUniqueId().toString() + ".Clan", clanName.toLowerCase());
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Name", clanName.toLowerCase());
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Besitzer", besitzer.getUniqueId().toString());
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Mitglieder", new ArrayList<>());
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".MaxMitglieder", 5);
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Erstellt", System.currentTimeMillis());
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Exp", 0);
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Tag", ClantagTypes.DEFAULT.getName());
        this.config.getConfig().set("Clans." + clanName.toLowerCase() + ".Wappen", WappenTypes.DEFAULT.getId());
        this.config.saveConfig();
        addPlayerToClan(clanName, besitzer.getUniqueId().toString());
    }

    public void deleteClan(final String clan) {
        List<String> mitglieder = this.config.getConfig().getStringList("Clans." + clan.toLowerCase() + ".Mitglieder");
        for (String s : mitglieder) this.config.getConfig().set("Spieler." + s + ".Clan", null);
        this.config.getConfig().set("Clans." + clan.toLowerCase(), null);
        this.config.saveConfig();
    }

    public void addPlayerToClan(final String clan, final String uuid) {
        List<String> mitglieder = this.config.getConfig().getStringList("Clans." + clan.toLowerCase() + ".Mitglieder");

        mitglieder.add(uuid);
        this.config.getConfig().set("Clans." + clan.toLowerCase() + ".Mitglieder", mitglieder);
        this.config.getConfig().set("Spieler." + uuid + ".Clan", clan.toLowerCase());
        this.config.saveConfig();
    }

    public void removePlayerFromClan(final String clan, final String uuid) {
        List<String> mitglieder = this.config.getConfig().getStringList("Clans." + clan.toLowerCase() + ".Mitglieder");

        mitglieder.remove(uuid);
        this.config.getConfig().set("Clans." + clan.toLowerCase() + ".Mitglieder", mitglieder);
        this.config.getConfig().set("Spieler." + uuid + ".Clan", null);
        this.config.saveConfig();
    }

    public void setMaxMitglieder(final String clan, final int value) {
        this.config.getConfig().set("Clans." + clan.toLowerCase() + ".MaxMitglieder", value);
        this.config.saveConfig();
    }

    public List<String> getMitglieder(final String clan) {
        return this.config.getConfig().getStringList("Clans." + clan.toLowerCase() + ".Mitglieder");
    }

    public List<Player> getOnlineMitglieder(final String clan) {
        final List<Player> online = new ArrayList<>();
        for (String s : getMitglieder(clan)) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null) {
                online.add(Bukkit.getPlayer(s));
            }
        }
        return online;
    }

    public int getMaxMitglieder(final String clan) {
        return this.config.getConfig().getInt("Clans." + clan.toLowerCase() + ".MaxMitglieder");
    }

    public boolean existsClan(final String clan) {
        return this.config.getConfig().getString("Clans." + clan.toLowerCase() + ".Besitzer") != null;
    }

    public boolean isNameValid(final String clanName) {
        return clanName.matches("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]*");
    }

    public boolean isInClan(final Player player) {
        return this.config.getConfig().get("Spieler." + player.getUniqueId().toString() + ".Clan") != null;
    }

    public boolean hasCustomClanTag(final String clan) {
        return this.config.getConfig().get(".Clans." + clan.toLowerCase() + ".Tag") != ClantagTypes.DEFAULT.getName();
    }

    public void setCustomClanTag(final String clan, final ClantagTypes clantagTypes) {
        this.config.getConfig().set("Clans." + clan.toLowerCase() + ".Tag", clantagTypes.getName());
        this.config.saveConfig();
    }

    public String getCustomClanTag(final String clan) {
        return (this.config.getConfig().get("Clans." + clan.toLowerCase() + ".Tag") == null ? ClantagTypes.DEFAULT.getName() : this.config.getConfig().getString("Clans." + clan.toLowerCase() + ".Tag"));
    }

    public String getClan(final Player player) {
        return this.config.getConfig().getString("Spieler." + player.getUniqueId().toString() + ".Clan");
    }

    public boolean isClanBesitzer(final Player player) {
        return this.config.getConfig().getString("Clans." + getClan(player) + ".Besitzer").equals(player.getUniqueId().toString());
    }

    public boolean isClanBesitzer(final Player player, final String clan) {
        return this.config.getConfig().getString("Clans." + clan + ".Besitzer").equals(player.getUniqueId().toString());
    }

    public String getClanBesitzer(final Player player) {
        return this.config.getConfig().getString("Clans." + getClan(player) + ".Besitzer");
    }


    public String getClanBesitzer(final String clan) {
        return this.config.getConfig().getString("Clans." + clan.toLowerCase() + ".Besitzer");
    }

    public long getClanEXP(final String clan) {
        return this.config.getConfig().getLong("Clans." + clan.toLowerCase() + ".Exp");
    }

    public void setClanEXP(final String clan, final long value) {
        this.config.getConfig().set("Clans." + clan.toLowerCase() + ".Exp", value);
        this.config.saveConfig();
    }

    public long getClanErstellt(final String clan) {
        return this.config.getConfig().getLong("Clans." + clan.toLowerCase() + ".Erstellt");
    }

    public WappenTypes getClanWappen(final String clan) {
        return WappenTypes.getWappenByID(this.config.getConfig().getLong("Clans." + clan.toLowerCase() + ".Wappen"));
    }

    public void setClanWappen(final String clan, final WappenTypes wappen) {
        this.config.getConfig().set("Clans." + clan.toLowerCase() + ".Wappen", wappen.getId());
    }
}
