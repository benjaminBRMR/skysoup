package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created: 14.02.2023 19:33
 *
 * @author thvf
 */
public class LagerPlatzController {


    UUID uuid;
    Config config;

    public LagerPlatzController(final UUID uuid) {
        this.uuid = uuid;
        this.config = new Config("plugins/SkySoup/lagerplaetze/", this.uuid.toString());

    }

    public void createLagerPlatz() {
        if (hasLagerPlatz()) return;
        this.config.getConfig().set(".Besitzer", uuid.toString());
        this.config.getConfig().set(".Items", new ArrayList<>());
        this.config.getConfig().set(".SizeReihen", 1);
        this.config.saveConfig();
    }

    public void deleteLagerPlatz() {
        if (!hasLagerPlatz()) return;
        this.config.getConfig().set(".Besitzer", null);
        this.config.getConfig().set(".Items", null);
        this.config.getConfig().set(".SizeReihen", null);
        this.config.saveConfig();
    }

    public int getLagerPlatzLevel() {
        return this.config.getConfig().getInt(".SizeReihen");
    }

    public void setLagerPlatzLevel(final int rows) {
        this.config.getConfig().set(".SizeReihen", rows);
        this.config.saveConfig();
    }

    public UUID getLagerPlatzOwner() {
        return UUID.fromString(this.config.getConfig().getString(".Besitzer"));
    }

    public List<ItemStack> lagerPlatzItems() {
        if (this.config.getConfig().get(".Items") == null) return new ArrayList<>();
        return (List<ItemStack>) this.config.getConfig().getList(".Items");
    }

    public void openLagerPlatzForOwner() {
        final Inventory inventory = Bukkit.createInventory(null, getLagerPlatzLevel() * 9, "§8┃ §9§lLAGERPLATZ §8: §7" + Data.getUuidFetcher().getName(uuid));

        for (int i = 0; i < lagerPlatzItems().size(); i++) {
            inventory.setItem(i, lagerPlatzItems().get(i));
        }

        Bukkit.getPlayer(uuid).openInventory(inventory);
        Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.CHEST_OPEN, 2, 10);
    }


    public void saveLagerPlatzItems(final List<ItemStack> items) {

        this.config.getConfig().set(".Items", null);
        this.config.getConfig().set(".Items", items);
        this.config.saveConfig();
    }

    public void openLagerPlatzForViewer(final UUID viewer) {
        final Inventory inventory = Bukkit.createInventory(null, getLagerPlatzLevel() * 9, "§8┃ §9§lLAGERPLATZ §8: §7" + Data.getUuidFetcher().getName(uuid));


        for (int i = 0; i < lagerPlatzItems().size(); i++) {
            inventory.setItem(i, lagerPlatzItems().get(i));
        }
        Bukkit.getPlayer(viewer).openInventory(inventory);
        Bukkit.getPlayer(viewer).playSound(Bukkit.getPlayer(viewer).getLocation(), Sound.CHEST_OPEN, 2, 5.1F);


    }

    public boolean hasLagerPlatz() {
        return this.config.getConfig().get(".Besitzer") != null;
    }
}
