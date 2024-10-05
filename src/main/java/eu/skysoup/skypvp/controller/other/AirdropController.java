package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.Config;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 12.02.2023 15:21
 *
 * @author thvf
 */
public class AirdropController {


    Config config;

    public AirdropController() {
        this.config = new Config("plugins/SkySoup/config/", "airdrop.yml");
    }


    public List<ItemStack> getItemsFromAirdrop(final long level) {
        if (this.config.getConfig().get(".Items." + level) == null) return new ArrayList<>();
        return (List<ItemStack>) this.config.getConfig().getList(".Items." + level);
    }

    public void setItemsFromAirdrop(final long level, final List<ItemStack> items) {
        this.config.getConfig().set(".Items." + level, items);
        this.config.saveConfig();
    }

    ArrayList<String> skulls;
    final HashMap<Player, BukkitTask> airDropAnimation = new HashMap<>();

    public void spawnAirDrop(final Player player, final Location location, final long level) {


        if (airDropAnimation.containsKey(player)) {
            Data.getMessageUtil().sendMessage(player, "§cBitte warte bis die AirDrop-Animation zu Ende ist!");
            return;
        }
        final ArmorStand armorStand = location.getWorld().spawn(location.add(0.5, 25, 0.5), ArmorStand.class);
        final AtomicInteger atomicInteger = new AtomicInteger(0);


        armorStand.getLocation().getWorld().playEffect(armorStand.getLocation(), Effect.FIREWORKS_SPARK, 15);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName("§4§l❒ §8┃ §7AirDrop für §c" + player.getName());
        Data.getMessageUtil().sendMessage(player, "§7Dein §eAirDrop §7wird in Kürze geliefert§8.");
        Data.getMessageUtil().sendMessage(player, "§7Schaue nach oben und warte...");

        skulls = new ArrayList<>();

        skulls.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I1MTA2YjA2MGVhZjM5ODIxNzM0OWYzY2ZiNGYyYzdjNGZkOWEwYjAzMDdhMTdlYmE2YWY3ODg5YmUwZmJlNiJ9fX0=");

        skulls.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk4NWEyOTk1N2Q0MGZhNTY0ZDVlMzFjYmQ5MDVlMzY5NGE2MTYzOTNjZTEzNzEwYmZjMzFiMWI4YjBhNTIyZCJ9fX0=");

        skulls.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjllMTY5NzkzMDliNWE5YjY3M2Q2MGQxMzkwYmJhYjBkMDM4NWVhYzcyNTRkODI4YWRhMmEzNmE0NmY3M2E1OSJ9fX0=");
        skulls.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA2MmQ4ZDcyZjU4OTFjNzFmYWIzMGQ1MmUwNDgxNzk1YjNkMmQzZDJlZDJmOGI5YjUxN2Q3ZDI4MjFlMzVkNiJ9fX0=");
        skulls.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E5ZWE2ZTM2ZjllNTc5ZjU4NmFkYjE5MzdiYjE0Mzc3YjBkNzQwMzRmZmNiMjU1NmEyYWNiNDM1NjcxNDQ4ZiJ9fX0=");
        skulls.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjAwYmY0YmYxNGM4Njk5YzBmOTIwOWNhNzlmZTE4MjUzZTkwMWU5ZWMzODc2YTJiYTA5NWRhMDUyZjY5ZWJhNyJ9fX0=");


        airDropAnimation.put(player, new BukkitRunnable() {

            @Override
            public void run() {

                atomicInteger.getAndIncrement();

                if (atomicInteger.get() == 50) {
                    airDropAnimation.get(player).cancel();
                    for (ItemStack items : Data.getAirDropController().getItemsFromAirdrop(level))
                        armorStand.getLocation().getWorld().dropItemNaturally(armorStand.getLocation(), items);
                    airDropAnimation.remove(player);
                    armorStand.remove();
                }


                armorStand.setHelmet(ItemSkull.getSkull("", skulls.get(Data.getRandom().nextInt(skulls.size())), ""));


                if (armorStand.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                    airDropAnimation.get(player).cancel();
                    armorStand.getLocation().getWorld().playEffect(armorStand.getLocation(), Effect.EXPLOSION_LARGE, 10);
                    armorStand.getLocation().getWorld().playEffect(armorStand.getLocation(), Effect.ENDER_SIGNAL, 15);
                    armorStand.getLocation().getWorld().playEffect(armorStand.getLocation(), Effect.HEART, 15);
                    armorStand.getLocation().getWorld().playSound(armorStand.getLocation(), Sound.ANVIL_LAND, 1, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Dein §eAirDrop §7ist gelandet§8.");

                    for (ItemStack items : Data.getAirDropController().getItemsFromAirdrop(level))
                        armorStand.getLocation().getWorld().dropItemNaturally(armorStand.getLocation(), items);

                    armorStand.remove();
                    airDropAnimation.remove(player);
                    return;
                }

                Location location1 = armorStand.getLocation().clone();
                location1.setY(location1.getY() - 1);
                location1.setYaw(location1.getYaw() + 15);

                armorStand.getLocation().getWorld().playSound(armorStand.getLocation().subtract(0, -5, 0), Sound.NOTE_PLING, 1, atomicInteger.get() - 3);

                armorStand.getLocation().getWorld().playEffect(armorStand.getLocation(), Effect.LAVA_POP, 15);

                armorStand.getLocation().getWorld().playEffect(armorStand.getLocation().subtract(0, 2, 0), Effect.WITCH_MAGIC, 3);
                armorStand.getLocation().getWorld().playEffect(armorStand.getLocation().subtract(-1, 2, -1), Effect.WITCH_MAGIC, 3);
                armorStand.getLocation().getWorld().playEffect(armorStand.getLocation().subtract(+1, 2, +1), Effect.WITCH_MAGIC, 3);
                armorStand.getLocation().getWorld().playEffect(armorStand.getLocation().subtract(0, 2, 0), Effect.WITCH_MAGIC, 3);
                armorStand.teleport(location1);


            }
        }.runTaskTimer(SkyPvP.getINSTANCE(), 0L, 2L));


    }


}
