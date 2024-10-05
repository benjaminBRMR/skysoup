package eu.skysoup.skypvp.reward;

import eu.skysoup.skypvp.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 10.02.2023 12:34
 *
 * @author thvf
 */
public class RewardController {


    Config config;
    Config userConfig;

    public RewardController() {
        this.config = new Config("plugins/SkySoup/rewards/", "config.yml");
        this.userConfig = new Config("plugins/SkySoup/rewards/", "users.yml");
    }

    public void setStatusFromPlayer(final Player player, final long id, final boolean value) {
        this.userConfig.getConfig().set("." + player.getUniqueId() + ".id." + id, value);
        this.userConfig.saveConfig();
    }

    public boolean getStatusFromPlayer(final Player player, final long id) {
        return this.userConfig.getConfig().getBoolean("." + player.getUniqueId() + ".id." + id);
    }


    public List<Long> getItems() {
        if (config.getConfig().get(".Rewards") == null) return new ArrayList<>();
        return this.config.getConfig().getLongList(".Rewards");
    }

    public ItemStack getItemStackById(final long id) {
        return this.config.getConfig().getItemStack("." + id + ".itemstack");
    }

    public ItemStack getItemStackByDay(final long day) {
        for (Long item : getItems()) {
            if (getDayByID(item) == day) return getItemStackById(item);
        }
        return null;
    }

    public boolean isItemListedByDay(final long day) {
        for (Long all : getItems()) {
            if (getDayByID(all) == day) return true;
        }
        return false;
    }

    public boolean isItemListedByID(final long id) {
        for (Long all : getItems()) {
            if (all == id) return true;
        }
        return false;
    }

    public long getDayByID(final long id) {
        return this.config.getConfig().getLong("." + id + ".day");
    }

    public long getIDByDay(final long day) {
        for (Long all : getItems()) {
            if (this.config.getConfig().getLong("." + all + ".day") == day) {
                return this.config.getConfig().getLong("." + all + ".id");
            }
        }
        return 0;
    }


    public void setItem(final long day, final ItemStack itemStack) {
        for (long all : getItems()) {
            if (getDayByID(all) == day) {
                this.config.getConfig().set("." + all + ".itemstack", itemStack);
                this.config.saveConfig();
            }
        }
    }


    public void addItem(final RewardItem rewardItem) {
        final List<Long> items = (this.config.getConfig().getLongList(".Rewards") == null ? new ArrayList<>() : this.config.getConfig().getLongList(".Rewards"));
        items.add(rewardItem.getId());

        this.config.getConfig().set("." + rewardItem.getId() + ".id", rewardItem.getId());
        this.config.getConfig().set("." + rewardItem.getId() + ".day", rewardItem.getDay());
        this.config.getConfig().set("." + rewardItem.getId() + ".itemstack", rewardItem.getItemStack());
        this.config.getConfig().set(".Rewards", items);
        this.config.saveConfig();
    }

    public void removeItem(final RewardItem rewardItem) {
        final List<Long> items = (this.config.getConfig().getLongList(".Rewards") == null ? new ArrayList<>() : this.config.getConfig().getLongList(".Rewards"));
        items.remove(rewardItem.getId());

        this.config.getConfig().set("." + rewardItem.getId(), null);
        this.config.getConfig().set(".Rewards", items);
        this.config.saveConfig();
    }
}
