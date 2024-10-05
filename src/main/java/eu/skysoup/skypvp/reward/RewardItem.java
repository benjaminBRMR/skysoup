package eu.skysoup.skypvp.reward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 10.02.2023 12:33
 *
 * @author thvf
 */
@Getter
@AllArgsConstructor
public class RewardItem {

    private final long id;
    private final long day;
    private final ItemStack itemStack;

}
