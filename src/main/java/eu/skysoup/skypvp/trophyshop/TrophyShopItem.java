package eu.skysoup.skypvp.trophyshop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 30.01.2023 22:16
 *
 * @author thvf
 */
@RequiredArgsConstructor
@Getter
public class TrophyShopItem {

    private final ItemStack itemStack;
    private final long trophyPreis;
    private final long id;
}
