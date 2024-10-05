package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.utils.Config;

/**
 * Created: 23.01.2023 13:03
 *
 * @author thvf
 */
public class ToggleController {

    public enum Types {
        MSG,
        TPA,
        EP,
        PVP,
        DROPS,
        PICKUP,
        POTIONS,
        PAY,
        NOWL,
        TROPHYMARKT,
        NACHTMARKT,
        GAPS,
        KILLHOLO
    }

    Config config;

    public ToggleController() {
        this.config = new Config("plugins/SkySoup/config/", "toggle.yml");
    }

    public void toggleType(final Types type, final boolean value) {
        this.config.getConfig().set("." + type.name().toLowerCase(), value);
        this.config.saveConfig();
    }

    /**
     * return true if the type is activated
     * and false is the type is deactivated
     *
     * @param type
     * @return
     */
    public boolean isToggled(final Types type) {

        if (this.config.getConfig().get("." + type.name().toLowerCase()) == null) return true;
        return this.config.getConfig().getBoolean("." + type.name().toLowerCase());

    }


}
