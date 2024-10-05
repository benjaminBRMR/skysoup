package eu.skysoup.skypvp.data.implementorings;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 24.02.2023 17:27
 *
 * @author thvf
 */
public enum WappenTypes {

    DEFAULT("§7Neuling", new ItemStack(Material.WORKBENCH), 1);


    @Getter
    final String name;
    @Getter
    final ItemStack itemStack;
    @Getter
    final long id;

    WappenTypes(final String name, final ItemStack itemStack, final long id) {
        this.name = name;
        this.itemStack = itemStack;
        this.id = id;
    }

    public static WappenTypes getWappenByID(final long id) {

        for (WappenTypes value : WappenTypes.values()) {
            if (value.getId() == id) return value;
        }
        return null;
    }
}
