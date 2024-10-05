package eu.skysoup.skypvp.utils.inventory.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * Created: 05.02.2023 20:55
 *
 * @author thvf
 */
public interface InventoryItemAction {

    void click(final Player player, final ClickType clickType);

}
