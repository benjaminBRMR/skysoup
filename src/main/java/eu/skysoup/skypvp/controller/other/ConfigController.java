package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.utils.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 16.02.2023 12:45
 *
 * @author thvf
 */
public class ConfigController {

    Config config;

    public ConfigController() {
        this.config = new Config("plugins/SkySoup/config/", "nochange.yml");
    }


    public List<?> getList(final String object) {
        if (this.config.getConfig().get("." + object) == null) return new ArrayList<>();
        return this.config.getConfig().getList("." + object);
    }

    public void addStringToList(final String object, final String value) {
        List<String> collection = this.config.getConfig().getStringList("." + object);
        collection.add(value.toLowerCase());
        this.config.getConfig().set("." + object, collection);
        this.config.saveConfig();
    }

    public void removeStringToList(final String object, final String value) {
        List<String> collection = this.config.getConfig().getStringList("." + object);
        collection.remove(value.toLowerCase());
        this.config.getConfig().set("." + object, collection);
        this.config.saveConfig();
    }

    public void setList(final String object, final List<?> list) {
        this.config.getConfig().set("." + object, list);
        this.config.saveConfig();
    }

    public void setString(final String object, final String value) {
        this.config.getConfig().set("." + object, value);
        this.config.saveConfig();
    }

    public String getString(final String object) {
        if (this.config.getConfig().get("." + object) == null) return "";
        return this.config.getConfig().getString("." + object);
    }

    public void setLong(final String object, final long value) {
        this.config.getConfig().set("." + object, value);
        this.config.saveConfig();
    }

    public long getLong(final String object) {
        if (this.config.getConfig().get("." + object) == null) return 0;
        return this.config.getConfig().getLong("." + object);
    }


}
