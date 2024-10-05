package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.utils.Config;

import java.util.Collection;

/**
 * Created: 23.01.2023 13:21
 *
 * @author thvf
 */
public class ChatController {


    Config config;

    public ChatController() {
        this.config = new Config("plugins/SkySoup/config/", "chat.yml");
    }

    public Collection<String> getBlacklistedWords() {
        return this.config.getConfig().getStringList(".BlacklistedWords");
    }

    public boolean isWordBlacklisted(final String word) {
        return getBlacklistedWords().contains(word.toLowerCase());
    }


    public void addWord(final String word) {


        Collection<String> collection = this.config.getConfig().getStringList(".BlacklistedWords");
        collection.add(word.toLowerCase());
        this.config.getConfig().set(".BlacklistedWords", collection);
        this.config.saveConfig();
    }

    public void removeWord(final String word) {
        Collection<String> collection = this.config.getConfig().getStringList(".BlacklistedWords");
        collection.remove(word.toLowerCase());
        this.config.getConfig().set(".BlacklistedWords", collection);
        this.config.saveConfig();
    }


}
