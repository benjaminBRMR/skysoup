package eu.skysoup.skypvp.utils.impl;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created: 24.01.2023 17:12
 *
 * @author thvf
 */
public class Utils {

    public static String getStatistic(final double totaP, final int scale, final ChatColor color) {
        StringBuilder msg = new StringBuilder("     §8[");

        int divide = 100 / scale;
        double totalB = totaP / divide;
        int finalB = (int) Math.round(totalB);

        int difference = 100 - finalB;

        for (int i = 0; i < finalB; i++) {
            msg.append(color).append("|");
        }

        msg.append("§8]");

        return msg.toString();
    }

    public static void playSoundToTeam(final Sound sound, final float volume, final float pitch) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            if (!all.hasPermission(Permissions.TEAM.getPermission())) return;
            all.playSound(all.getLocation(), sound, volume, pitch);
        });
    }

    public static boolean hasPlayerItemInInventory(final Player player, final String itemName) {
        for (ItemStack all : player.getInventory().getContents()) {

            if (all == null || all.getType() == Material.AIR) continue;
            if (!all.getItemMeta().hasDisplayName()) continue;
            if (all.getItemMeta().getDisplayName().equals(itemName)) {
                return true;
            }
            continue;
        }
        return false;
    }

    public static <E> E getRandomSetElement(Set<E> set) {
        return set.stream().skip(new Random().nextInt(set.size())).findFirst().orElse(null);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }


    public static HoverEvent showItem(final ItemStack itemStack) {
        return new HoverEvent(HoverEvent.Action.SHOW_ITEM, new BaseComponent[]{new TextComponent(CraftItemStack.asNMSCopy(itemStack).save(new NBTTagCompound()).toString())});
    }

    public static void spawnRandomFirework(Location loc) {
        Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        Random random = Data.getRandom();
        FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getColor(random.nextInt(17) + 1)).withFade(getColor(random.nextInt(17) + 1)).with(FireworkEffect.Type.values()[random.nextInt((FireworkEffect.Type.values()).length)]).trail(random.nextBoolean()).build();
        fireworkMeta.addEffect(effect);
        fireworkMeta.setPower(random.nextInt(2) + 1);
        firework.setFireworkMeta(fireworkMeta);
    }

    private static Color getColor(int i) {
        switch (i) {
            case 1:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.GRAY;
            case 5:
                return Color.GREEN;
            case 6:
                return Color.LIME;
            case 7:
                return Color.ORANGE;
            case 8:
                return Color.PURPLE;
            case 9:
                return Color.RED;
            case 10:
                return Color.WHITE;
            case 11:
                return Color.YELLOW;
        }
        return Color.AQUA;
    }


    public static int getCorrectOnlinePlayers() {
        AtomicInteger integer = new AtomicInteger(0);
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (Data.getVanished().contains(all)) continue;
            integer.getAndIncrement();
        }
        return integer.get();
    }

    public static String getItemDurabilityPercentage(final ItemStack itemStack) {
        int max = itemStack.getType().getMaxDurability();
        int uses = itemStack.getDurability();
        double percent = 100 - ((double) uses / (double) max * (double) 100);
        String s = "§c";
        if (percent > 20) s = "§6";
        if (percent > 40) s = "§e";
        if (percent > 60) s = "§2";
        if (percent > 80) s = "§a";
        return s + ((int) Math.round(percent)) + "§8%";
    }
}
