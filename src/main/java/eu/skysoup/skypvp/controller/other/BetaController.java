package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.Config;

import java.util.List;
import java.util.UUID;

/**
 * Created: 19.02.2023 00:14
 *
 * @author thvf
 */
public class BetaController {

    long id;
    Config config;

    public BetaController() {
        this.config = new Config("plugins/SkySoup/config/", "beta.yml");
    }

    public void setID(final UUID uuid, final long value) {
        final List<Long> allIDS = this.config.getConfig().getLongList(".allIDS");
        allIDS.add(value);

        this.config.getConfig().set(uuid + ".ID", value);
        this.config.getConfig().set(".allIDS", allIDS);
        this.config.saveConfig();
    }

    public List<Long> allIDS() {
        return this.config.getConfig().getLongList(".allIDS");
    }

    public long getID(final UUID uuid) {
        if (this.config.getConfig().get(uuid + ".ID") == null) setID(uuid, randomId());
        return this.config.getConfig().getLong(uuid + ".ID");
    }

    private static final int MAX_RECURSIVE_CALLS = 1000;
    private static int recursionCounter = 0;

    public long randomId() {
        if (recursionCounter++ >= MAX_RECURSIVE_CALLS) {
            recursionCounter = 0;
            return -1;
        }
        long id = Data.getRandom().nextInt(999);
        for (Long all : allIDS()) {
            if (all == id) return randomId();
        }
        recursionCounter = 0;
        return id;
    }
}
