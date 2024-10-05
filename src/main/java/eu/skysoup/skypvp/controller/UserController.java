package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.RankTypes;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.data.implementorings.UserValues;
import eu.skysoup.skypvp.events.PvPRankupEvent;
import eu.skysoup.skypvp.utils.Config;
import eu.skysoup.skypvp.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created: 23.01.2023 00:49
 *
 * @author thvf
 */
public class UserController {

    UUID uuid;
    Config config;


    /**
     * sets all parameters so nothing is null
     *
     * @param uuid
     * @return functional usercontroller
     */
    public UserController getUserByUUID(final UUID uuid) {
        this.uuid = uuid;
        this.config = new Config("plugins/SkySoup/users/", this.uuid.toString());
        return this;
    }

    /**
     * CooldownController of the user
     *
     * @return cooldownController
     */
    public Cooldown getCooldownController() {
        return new Cooldown(uuid);
    }

    public BountyController getBountyController() {
        return new BountyController(uuid);
    }

    /**
     * trys to the the user async because else of a log
     *
     * @param name
     * @return null if no user with that name was found
     */
    public UUID getUserByName(final String name) {

        CompletableFuture.supplyAsync(() -> {
            for (UUID uuid : getUsers()) {
                if (uuid.equals(Data.getUuidFetcher().getUUID(name))) {
                    return Data.getUuidFetcher().getUUID(name);
                }
            }
            return this;
        });
        return null;
    }

    public List<UUID> getUsers() {
        final List<UUID> tempList = new ArrayList<>();
        File[] files = new File("plugins/SkySoup/users/").listFiles();

        if (files == null) return new ArrayList<>();

        for (File file : files) {
            try {
                final String correctFileName = file.getName().replaceAll(".yml", "").trim();
                tempList.add(UUID.fromString(correctFileName));
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return tempList;
    }


    /**
     * simply checks if one of the statistics are not set
     *
     * @return if the statistic is set
     */
    public boolean doesUserExist() {
        return this.config.getConfig().get("." + StatisticTypes.REGISTERED.name().toLowerCase()) != null;
    }

    /**
     * creates the user and sets all statistics
     * to the default value that is entered in the class
     */
    public void createUser() {
        if (doesUserExist()) throw new RuntimeException("user already exists");
        EnumSet.allOf(StatisticTypes.class).forEach(all -> {
            setStatisticFromUser(all, all.getDefaultV());
        });
        EnumSet.allOf(SettingTypes.class).forEach(all -> {
            setSettingFromUser(all, Bukkit.getPlayer(uuid).hasPermission(all.getPermission()));
        });
        setCustomValue(UserValues.IPADRESS, (Bukkit.getPlayer(uuid).getAddress() != null ? Bukkit.getPlayer(uuid).getAddress().getHostString() : 0));
        Data.getLOGGER().info("[" + Bukkit.getPlayer(uuid).getName() + "] = Created.");
    }

    /**
     * deletes the user and sets
     * every statistic to 0
     */
    public void deleteUser() {
        if (!doesUserExist()) throw new RuntimeException("user does not exist");
        EnumSet.allOf(StatisticTypes.class).forEach(all -> {
            this.config.getConfig().set("." + all.name().toLowerCase(), null);
            this.config.saveConfig();
        });


        Data.getLOGGER().info("[" + Bukkit.getPlayer(uuid).getName() + "] = Deleted.");
    }


    /**
     * @param statisticsType
     * @return the value of the player's statistic
     */
    public long getStatisticByType(final StatisticTypes statisticsType) {
        return (this.config.getConfig().get("." + statisticsType.name().toLowerCase()) == null ? statisticsType.getDefaultV() : this.config.getConfig().getLong("." + statisticsType.name().toLowerCase()));
    }

    /**
     * set the statistic to the specified value
     *
     * @param statisticsType
     * @param value
     */
    public void setStatisticFromUser(final StatisticTypes statisticsType, final long value) {

        final RankTypes currentRank = RankTypes.getRankFromKills((int) getStatisticByType(StatisticTypes.KILLS));
        final RankTypes nextRank = RankTypes.getUserRankFromID(currentRank.getId() + 1);
        this.config.getConfig().set("." + statisticsType.name().toLowerCase(), value);
        this.config.saveConfig();

        if (statisticsType == StatisticTypes.KILLS)
            SkyPvP.getINSTANCE().getServer().getPluginManager().callEvent(new PvPRankupEvent(Bukkit.getPlayer(uuid), currentRank, nextRank));

    }

    /**
     * sets the setting to the specified value
     *
     * @param settingTypes
     * @param value
     */
    public void setSettingFromUser(final SettingTypes settingTypes, final boolean value) {
        if (!Bukkit.getPlayer(uuid).hasPermission(settingTypes.getPermission())) return;
        this.config.getConfig().set(".settings." + settingTypes.name().toLowerCase(), value);
        this.config.saveConfig();
    }

    /**
     * gets the setting status
     *
     * @param settingTypes
     * @return
     */
    public boolean getSettingFromUser(final SettingTypes settingTypes) {
        return this.config.getConfig().getBoolean(".settings." + settingTypes.name().toLowerCase(), false);
    }

    /**
     * set a custom uservalue to a value
     *
     * @param userValues
     * @param value
     */
    public void setCustomValue(final UserValues userValues, final Object value) {
        this.config.getConfig().set(".value." + userValues.name().toLowerCase(), value);
        this.config.saveConfig();
    }

    /**
     * get the custom uservalue parameter
     * return null if the value isnt set
     *
     * @param userValues
     * @return
     */
    public Object getCustomValue(final UserValues userValues) {
        return this.config.getConfig().get(".value." + userValues.name().toLowerCase());
    }


    public boolean hasPermissionForSetting(SettingTypes settingTypes) {
        return Bukkit.getPlayer(uuid).hasPermission(settingTypes.getPermission());
    }

    public Location getDisconnectLocation() {

        if (config.getConfig().getString(".Auslogg-Punkt" + ".world") == null)
            return Data.getLocation().getLocation("spawn");

        String welt = config.getConfig().getString(".Auslogg-Punkt" + ".world");
        double x = config.getConfig().getDouble(".Auslogg-Punkt" + ".x");
        double y = config.getConfig().getDouble(".Auslogg-Punkt" + ".y");
        double z = config.getConfig().getDouble(".Auslogg-Punkt" + ".z");
        float yaw = (float) config.getConfig().getDouble(".Auslogg-Punkt" + ".yaw");
        float pitch = (float) config.getConfig().getDouble(".Auslogg-Punkt" + ".pitch");
        return new org.bukkit.Location(Bukkit.getWorld(welt), x, y, z, yaw, pitch);
    }

    public void setDisconnectLocation() {

        final Player player = Bukkit.getPlayer(uuid);

        config.getConfig().set(".Auslogg-Punkt" + ".world", player.getLocation().getWorld().getName());
        config.getConfig().set(".Auslogg-Punkt" + ".x", player.getLocation().getX());
        config.getConfig().set(".Auslogg-Punkt" + ".y", player.getLocation().getY());
        config.getConfig().set(".Auslogg-Punkt" + ".z", player.getLocation().getZ());
        config.getConfig().set(".Auslogg-Punkt" + ".yaw", player.getLocation().getYaw());
        config.getConfig().set(".Auslogg-Punkt" + ".pitch", player.getLocation().getPitch());
        config.saveConfig();

    }

}
