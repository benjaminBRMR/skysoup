package eu.skysoup.skypvp.utils.builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * JavaDoc this file!
 * Created: 08.01.2023
 *
 * @author thvf
 */
public class ItemSkull {
    private static ItemStack itemStack;
    private static SkullMeta itemMeta;

    public static ItemStack getSkullName(final String skullOwner, final String title, final String... lore) {
        itemStack = new ItemStack(397, 1, (short) 3);
        itemMeta = (SkullMeta) itemStack.getItemMeta();
        if (title != null) {
            itemMeta.setDisplayName(title);
        }
        if (lore != null) {
            List<String> li = new ArrayList<>(Arrays.asList(lore));
            itemMeta.setLore(li);
        }
        itemMeta.setOwner(skullOwner);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getSkull(String title, String base64, String... lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        UUID hashAsId = new UUID(base64.hashCode(), base64.hashCode());


        item = Bukkit.getUnsafe().modifyItemStack(item,
                "{display:{Name:\"" + title + "\"},SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + base64 + "\"}]}}}"
        );
        ItemMeta im = item.getItemMeta();
        if (title != null) {
            im.setDisplayName(title);
        } else {
            im.setDisplayName("Â§rSkull");
        }
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (lore != null) {
            List<String> li = new ArrayList<>(Arrays.asList(lore));
            im.setLore(li);
        }
        item.setItemMeta(im);
        return item;

    }

    public static ItemStack getSkullP(String title, String player, String... lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        item = Bukkit.getUnsafe().modifyItemStack(item,
                "{display:{Name:\"" + title + "\"},SkullOwner:{Name:\"" + player + "\"}}"
        );
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(title);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (lore != null) {
            List<String> li = new ArrayList<>(Arrays.asList(lore));
            im.setLore(li);
        }
        item.setItemMeta(im);
        return item;
    }
}
