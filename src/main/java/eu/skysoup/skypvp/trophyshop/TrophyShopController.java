package eu.skysoup.skypvp.trophyshop;

import eu.skysoup.skypvp.utils.Config;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created: 30.01.2023 22:17
 *
 * @author thvf
 */
public class TrophyShopController {


    Config config;

    public TrophyShopController() {
        this.config = new Config("plugins/SkySoup/trophyshop/", "config.yml");
    }

    public void addItemToShop(final TrophyShopItem trophyShopItem) {

        final List<Long> listed = this.config.getConfig().getLongList(".listed");

        listed.add(trophyShopItem.getId());

        this.config.getConfig().set("." + trophyShopItem.getId() + ".item", trophyShopItem.getItemStack());
        this.config.getConfig().set("." + trophyShopItem.getId() + ".preis", trophyShopItem.getTrophyPreis());
        this.config.getConfig().set(".listed", listed);
        this.config.saveConfig();

    }

    public void removeItemFromShop(final Long id) {
        final List<Long> listed = this.config.getConfig().getLongList(".listed");
        listed.remove(id);

        this.config.getConfig().set("." + id, null);
        this.config.getConfig().set(".listed", listed);
        this.config.saveConfig();
    }

    public boolean isItemListed(final Long id) {
        return getListedItems().contains(id);
    }


    public List<Long> getListedItems() {
        final List<Long> listed = this.config.getConfig().getLongList(".listed");
        return listed;
    }

    public ItemStack getItemFromID(final long id) {
        return this.config.getConfig().getItemStack("." + id + ".item");
    }

    public long getPreisFromID(final long id) {
        return this.config.getConfig().getLong("." + id + ".preis");
    }

    public void setPreis(final long id, final long preis) {
        this.config.getConfig().set("." + id + ".preis", preis);
        this.config.saveConfig();
    }

}
