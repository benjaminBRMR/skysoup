package eu.skysoup.skypvp.kit;

import eu.skysoup.skypvp.utils.Config;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created: 02.02.2023 12:07
 *
 * @author thvf
 */
public class KitController {

    Config config;


    public KitController() {
        this.config = new Config("plugins/SkySoup/kits/", "config.yml");
    }

    public void createKit(final Kit kit) {

        final List<String> listedKits = (this.config.getConfig().getStringList(".listed") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".listed"));

        listedKits.add(kit.getName());

        this.config.getConfig().set("." + kit.getName() + ".items", kit.getItems());
        this.config.getConfig().set("." + kit.getName() + ".cooldown", kit.getCooldown());
        this.config.getConfig().set("." + kit.getName() + ".timeunit", kit.getTimeUnit().name());
        this.config.getConfig().set("." + kit.getName() + ".permission", kit.getPermission().toLowerCase());
        this.config.getConfig().set(".listed", listedKits);
        this.config.saveConfig();
    }

    public void deleteKit(final String kitName) {
        final List<String> listedKits = (this.config.getConfig().getStringList(".listed") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".listed"));

        listedKits.remove(kitName);

        this.config.getConfig().set("." + kitName, null);
        this.config.getConfig().set(".listed", listedKits);
        this.config.saveConfig();
    }

    public boolean kitExist(final String kitName) {
        return this.config.getConfig().get("." + kitName) != null;
    }

    public List<String> getListedKits() {
        return (this.config.getConfig().getStringList(".listed") == null ? new ArrayList<>() : this.config.getConfig().getStringList(".listed"));
    }

    public void setItems(final String kitName, final List<ItemStack> itemStackList) {
        this.config.getConfig().set("." + kitName.toLowerCase() + ".items", itemStackList);
        this.config.saveConfig();
    }


    public void setCooldown(final String kitName, final long cooldown) {
        this.config.getConfig().set("." + kitName.toLowerCase() + ".cooldown", cooldown);
        this.config.saveConfig();
    }

    public void setTimeUnit(final String kitName, final TimeUnit timeUnit) {
        this.config.getConfig().set("." + kitName.toLowerCase() + ".timeunit", timeUnit.name());
        this.config.saveConfig();
    }

    public void setPermission(final String kitName, final String permission) {
        this.config.getConfig().set("." + kitName.toLowerCase() + ".permission", permission);
        this.config.saveConfig();
    }

    public String getPermission(final String kitName) {
        return this.config.getConfig().getString("." + kitName.toLowerCase() + ".permission");
    }

    public List<ItemStack> getItems(final String kitName) {
        if (this.config.getConfig().get("." + kitName.toLowerCase() + ".items") == null) return new ArrayList<>();
        return (List<ItemStack>) this.config.getConfig().getList("." + kitName.toLowerCase() + ".items");
    }

    public long getCooldown(final String kitName) {
        return this.config.getConfig().getLong("." + kitName.toLowerCase() + ".cooldown");
    }

    public TimeUnit getTimeUnit(final String kitName) {
        return TimeUnit.valueOf(this.config.getConfig().getString("." + kitName.toLowerCase() + ".timeunit"));
    }


}
