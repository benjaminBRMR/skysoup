package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

/**
 * Created: 06.02.2023 00:00
 *
 * @author thvf
 */
public class AcceptInventory extends SingletonInventory {


    public interface AcceptAction {
        void onClick(final boolean clicked);
    }


    public AcceptInventory(final Player player, final AcceptAction acceptAction, final String... lore) {
        super(InventoryTitles.ACCEPT.getName(), Rows.CHEST_ROW_3, player);

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        final String jabase64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkyZTMxZmZiNTljOTBhYjA4ZmM5ZGMxZmUyNjgwMjAzNWEzYTQ3YzQyZmVlNjM0MjNiY2RiNDI2MmVjYjliNiJ9fX0=";
        final String neinbase64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==";

        final String infobase64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzMyNzAwOWNkNDcxODg0MTE1MjAzODYwYzFkYWY2ZDgyNWMwODQ1ZjhhNTI4YzZlZjZiZDI0ZWI4NmI0YjM4YiJ9fX0=";

        setItem(11, ItemSkull.getSkull("§8┃ §a§lANNEHMEN", jabase64, "§8§oKlicke, um deine Veränderung", "§8§ozu akzeptieren."), (player1, clickType) -> {
            acceptAction.onClick(true);
        });


        setItem(13, ItemSkull.getSkull("§8┃ §a§lBESTÄTIGUNG §8: §7Information", infobase64, lore), null);

        setItem(15, ItemSkull.getSkull("§8┃ §c§lABLEHNEN", neinbase64, "§8§oKlicke, um den Vorgang abzubrechen."), (player1, clickType) -> {
            acceptAction.onClick(false);

        });


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
