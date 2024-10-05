package eu.skysoup.skypvp.utils.impl;

import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

/**
 * JavaDoc this file!
 * Created: 09.01.2023
 *
 * @author thvf
 */

@RequiredArgsConstructor
public class Gutschein {

    @Getter
    final long amount;
    @Getter
    final String displayName;
    @Getter
    final GutscheinTypes gutscheinType;
    @Getter
    final ItemStack item;
    @Getter
    final String[] lore;


}
