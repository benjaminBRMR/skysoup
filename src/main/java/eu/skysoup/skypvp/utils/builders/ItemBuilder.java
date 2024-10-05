package eu.skysoup.skypvp.utils.builders;

/**
 * Created: 30.12.2022
 *
 * @author thvf
 */

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ItemBuilder extends ItemStack {
    public ItemBuilder() {
        super(Material.AIR, 1);
    }

    public ItemBuilder(final Material type) {
        super(type);
    }

    public ItemBuilder(final Material type, final int amount) {
        super(type, amount);
    }

    public ItemBuilder(final Material type, final int amount, final short damage) {
        super(type, amount, damage);
    }

    public ItemBuilder(final Material type, final int amount, final short damage, final byte data) {
        super(type, amount, damage, Byte.valueOf(data));
    }

    public ItemBuilder(final ItemStack itemStack) {
        super(itemStack);
    }

    public ItemBuilder setMaterial(final Material material) {
        this.setType(material);
        return this;
    }

    public ItemBuilder setDataId(final int id) {
        this.setDurability((short) id);
        return this;
    }

    public ItemBuilder setName(final String name) {
        final ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(name);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder amount(final int amount) {
        this.setAmount(amount);
        return this;
    }

    public ItemBuilder lore(final List<String> lore) {
        final ItemMeta meta = this.getItemMeta();
        meta.setLore((List) lore);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder durability(final int i) {
        this.setDurability((short) i);
        return this;
    }

    public ItemBuilder lore(final String... lore) {
        final ItemMeta meta = this.getItemMeta();
        meta.setLore((List) Arrays.asList(lore));
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addToLore(final String... lore) {
        final ItemMeta meta = this.getItemMeta();
        final List<String> loreList = (List<String>) meta.getLore();
        Collections.addAll(loreList, lore);
        meta.setLore((List) loreList);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addToLore(final List<String> lore) {
        final ItemMeta meta = this.getItemMeta();
        final List<String> loreList = (List<String>) meta.getLore();
        loreList.addAll(lore);
        meta.setLore((List) loreList);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder glow(boolean glow) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(this);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) {
            tag = nmsStack.getTag();
        }
        final NBTTagList ench = new NBTTagList();
        tag.set("ench", (glow ? ench : null));
        nmsStack.setTag(tag);
        return new ItemBuilder(CraftItemStack.asCraftMirror(nmsStack));
    }

    public boolean isGlow() {
        final ItemMeta meta = this.getItemMeta();
        return meta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS);
    }

    public ItemBuilder addEnchant(final Enchantment enchantment, final int level) {
        final ItemMeta itemMeta = this.getItemMeta();
        itemMeta.addEnchant(enchantment, level, false);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addUnsafeEnchant(final Enchantment enchantment, final int level) {
        final ItemMeta itemMeta = this.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        this.setItemMeta(itemMeta);
        return this;
    }


    public ItemBuilder removeEnchant(final Enchantment enchantment) {
        final ItemMeta itemMeta = this.getItemMeta();
        itemMeta.removeEnchant(enchantment);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setUnbreakable(final boolean unbreakable) {
        final ItemMeta meta = this.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addFlag(final ItemFlag flag) {
        final ItemMeta meta = this.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{flag});
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder removeFlag(final ItemFlag flag) {
        final ItemMeta meta = this.getItemMeta();
        meta.removeItemFlags(new ItemFlag[]{flag});
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder skullOwner(final String playerName) {
        final SkullMeta meta = (SkullMeta) this.getItemMeta();
        meta.setOwner(playerName);
        this.setItemMeta((ItemMeta) meta);
        return this;
    }

    public ItemBuilder addSkullValue(final String value) {
        final UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        Bukkit.getUnsafe().modifyItemStack((ItemStack) this, "{display:SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
        return this;
    }

    public boolean isUnbreakable() {
        return this.getItemMeta().spigot().isUnbreakable();
    }
}

