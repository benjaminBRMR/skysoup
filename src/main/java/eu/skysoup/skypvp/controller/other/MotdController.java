package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.utils.Config;

/**
 * Created: 11.02.2023 11:12
 *
 * @author thvf
 */
public class MotdController {

    Config config;


    public MotdController() {
        this.config = new Config("plugins/SkySoup/config/", "motd.yml");
    }

    public void setLine1(final String text) {
        this.config.getConfig().set(".Line.1", text);
        this.config.saveConfig();
    }

    public void setLine2(final String text) {
        this.config.getConfig().set(".Line.2", text);
        this.config.saveConfig();
    }

    public String getLine1() {
        return (this.config.getConfig().getString(".Line.1") == null ? "Nicht gesetzt" : this.config.getConfig().getString(".Line.1"));
    }

    public String getLine2() {
        return (this.config.getConfig().getString(".Line.2") == null ? "Nicht gesetzt" : this.config.getConfig().getString(".Line.2"));

    }
}
