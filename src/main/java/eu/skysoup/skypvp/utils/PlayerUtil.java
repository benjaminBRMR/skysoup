package eu.skysoup.skypvp.utils;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created: 24.01.2023 11:12
 *
 * @author thvf
 */
public class PlayerUtil {

    public boolean isInventoryEmpty(final Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null) return false;
        }
        return true;
    }

    /**
     * fixes the player invisible
     * to other players bug
     *
     * @param player
     */
    public void fixPlayer(final Player player) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            all.hidePlayer(player);
        });
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    all.showPlayer(player);
                });
            }
        }.runTaskLater(SkyPvP.getINSTANCE(), 3L);
    }

    public String getPlayerClientBrand(final Player player) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Field field = handle.getClass().getDeclaredField("playerConnection");
            field.setAccessible(true);
            Object playerConnection = field.get(handle);
            Field userAgentField = playerConnection.getClass().getDeclaredField("networkManager");
            userAgentField.setAccessible(true);
            Object networkManager = userAgentField.get(playerConnection);
            Object userAgent = networkManager.getClass().getMethod("getVersion").invoke(networkManager);
            return (String) userAgent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean removeItems(Inventory inv, ItemStack item, int amount) {
        if (!hasEnoughItems(inv, item, amount)) {
            return false;
        }
        int toRemove = amount;
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
        int slot = 0;
        while (slot < inv.getSize()) {
            ItemStack slotItem = inv.getItem(slot);
            if (slotItem != null && slotItem.getType() != Material.AIR && slotItem.getType() == item.getType() && slotItem.getDurability() == item.getDurability()) {
                slots.put(slot, slotItem);
            }
            ++slot;
        }
        for (Map.Entry<Integer, ItemStack> entrySlots : slots.entrySet()) {
            if ((entrySlots.getValue()).getAmount() <= toRemove) {
                inv.setItem(entrySlots.getKey(), new ItemStack(Material.AIR));
                toRemove -= (entrySlots.getValue()).getAmount();
                continue;
            }
            ItemStack invItem = inv.getItem(entrySlots.getKey());
            invItem.setAmount(invItem.getAmount() - toRemove);
            break;
        }
        return true;
    }

    public boolean hasEnoughItems(Inventory inv, ItemStack item, int amount) {
        return getAvailableItems(inv, item) >= amount;
    }

    public int getAvailableItems(Inventory inv, ItemStack item) {
        int counted = 0;
        ItemStack[] itemStackArray = inv.getContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack content = itemStackArray[n2];
            if (content != null && content.getType() != Material.AIR && content.getType() == item.getType() && content.getDurability() == item.getDurability()) {
                counted += content.getAmount();
            }
            ++n2;
        }
        return counted;
    }

    public void sendActionbar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {

        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        if (!userController.getSettingFromUser(SettingTypes.TITLES)) return;

        player.playSound(player.getLocation(), Sound.LEVEL_UP, 50, 18.4F);


        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent) null, fadeIn, stay, fadeOut);
        connection.sendPacket(packetPlayOutTimes);
        IChatBaseComponent titleMain;
        PacketPlayOutTitle packetPlayOutTitle;
        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleMain);
            connection.sendPacket(packetPlayOutTitle);
        }

        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket(packetPlayOutTitle);
        }

    }


    public void sendPlayerListTab(Player player, String header, String footer) {
        CraftPlayer craftplayer = (CraftPlayer) player;
        PlayerConnection connection =
                craftplayer.getHandle().playerConnection;
        IChatBaseComponent hj = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\": \"" + header + "\"}"));
        IChatBaseComponent fj = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\": \"" + footer + "\"}"));
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, hj);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, fj);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception ignored) {
        }
        connection.sendPacket(packet);
    }

    public void addItem(final Player player, final ItemStack itemStack) {

        if (player.getInventory().firstEmpty() == -1) {
            player.getLocation().getWorld().dropItemNaturally(player.getLocation(), itemStack);
        } else {
            player.getInventory().addItem(itemStack);
        }
    }

    public void playSoundToEveryone(final Sound sound) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            all.playSound(all.getLocation(), sound, 10, 18.4F);
        });
    }

    public void removeHand(final Player player) {

        if (player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        } else {
            player.setItemInHand(null);
        }
    }


    public String getRang(final Player player) {
        final LuckPerms luckPerms = LuckPermsProvider.get();
        final String primaryGroup = luckPerms.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();

        return primaryGroup;


    }


    public Player getRandomPlayer(final boolean noTeammembers) {
        final CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<Player>();
        final Random random = new Random();
        if (noTeammembers) {
            for (final Player all : Bukkit.getOnlinePlayers()) {
                if (!all.hasPermission(Permissions.TEAM.getPermission())) {
                    players.add(all);
                }
            }
            final int index = random.nextInt(players.size());

            if (players.get(index) != null) {
                return players.get(index);
            }

        }
        players.addAll(Bukkit.getOnlinePlayers());
        final int index = random.nextInt(players.size());
        return players.get(index);
    }

    /*
        Ändert den kompletten namen von dem Spieler
        z.B changeName("§4Monki" + player.getName(), player);
        Dann return player.getName(); immer "§4Monki" + player.getName();
     */
    @SuppressWarnings("deprecation")
    public void changeName(String name, Player player) {
        player.setCustomName(player.getName());
        player.setPlayerListName(name);
        player.setDisplayName(name);

        try {
            Method getHandle = player.getClass().getMethod("getHandle");
            Object entityPlayer = getHandle.invoke(player);
            boolean gameProfileExists = false;
            try {
                Class.forName("net.minecraft.util.com.mojang.authlib.GameProfile");
                gameProfileExists = true;
            } catch (ClassNotFoundException ignored) {
            }
            try {
                Class.forName("com.mojang.authlib.GameProfile");
                gameProfileExists = true;
            } catch (ClassNotFoundException ignored) {
            }
            if (!gameProfileExists) {
                Field nameField = entityPlayer.getClass().getSuperclass().getDeclaredField("name");
                nameField.setAccessible(true);
                nameField.set(entityPlayer, name);
            } else {
                Object profile = entityPlayer.getClass().getMethod("getProfile").invoke(entityPlayer);
                Field ff = profile.getClass().getDeclaredField("name");
                ff.setAccessible(true);
                ff.set(profile, name);
            }
            if (Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).getReturnType() == Collection.class) {
                @SuppressWarnings("unchecked")
                Collection<? extends Player> players = (Collection<? extends Player>) Bukkit.class.getMethod("getOnlinePlayers").invoke(null);
                for (Player p : players) {
                    p.hidePlayer(player);
                    p.showPlayer(player);
                }
            } else {
                Player[] players = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers").invoke(null));
                for (Player p : players) {
                    p.hidePlayer(player);
                    p.showPlayer(player);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
