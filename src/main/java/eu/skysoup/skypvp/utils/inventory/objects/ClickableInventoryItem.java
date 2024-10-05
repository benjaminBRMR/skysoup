package eu.skysoup.skypvp.utils.inventory.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 05.02.2023 20:50
 *
 * @author thvf
 */

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClickableInventoryItem {

    private final ItemStack itemStack;
    private InventoryItemAction clickAction;

    public void click(final Player player, final ClickType clickType) {
        clickAction.click(player, clickType);
    }
}