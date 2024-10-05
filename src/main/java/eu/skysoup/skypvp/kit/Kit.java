package eu.skysoup.skypvp.kit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created: 02.02.2023 12:06
 *
 * @author thvf
 */
@RequiredArgsConstructor
@Getter
public class Kit {

    private final String name;
    private final List<ItemStack> items;
    private final long cooldown;
    private final TimeUnit timeUnit;
    private final String permission;

}
