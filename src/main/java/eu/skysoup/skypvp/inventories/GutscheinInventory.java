package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.impl.Gutschein;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created: 02.02.2023 21:06
 *
 * @author thvf
 */
public class GutscheinInventory extends SingletonInventory {


    public GutscheinInventory(final Player player) {
        super(InventoryTitles.GUTSCHEIN.getName(), Rows.CHEST_ROW_6, player);


        int i = 0;
        for (Gutschein gutschein : Data.getGutscheinController().getGutscheine()) {

            final ItemBuilder item = new ItemBuilder(gutschein.getItem());
            item.lore(gutschein.getLore());
            item.setAmount((int) gutschein.getAmount());
            item.setName(gutschein.getDisplayName());
            item.addToLore("§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
            item.glow(true);

            setItem(i, item, (click, type) -> {
                player.getInventory().addItem(item);
            });
            i++;
        }

    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
