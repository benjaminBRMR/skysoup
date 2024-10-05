package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.commands.player.CommandInvsee;
import eu.skysoup.skypvp.controller.other.LagerPlatzController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created: 02.02.2023 12:35
 *
 * @author thvf
 */
public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();

        if (CommandInvsee.getInvseeMap().containsKey(player)) CommandInvsee.getInvseeMap().remove(player);


        if (event.getInventory().getName().contains("§8┃ §d§lKIT-EDIT §8: §7") && player.hasPermission(Permissions.ADMIN.getPermission())) {


            final String kitName = event.getInventory().getName().trim().replaceAll("§8┃ §d§lKIT-EDIT §8: §7", "");
            final List<ItemStack> tempItems = new ArrayList<>();

            for (ItemStack all : event.getInventory().getContents()) {
                if (all == null || all.getType() == Material.AIR) continue;
                tempItems.add(all);
            }

            Data.getKitController().setItems(kitName, tempItems);
            Data.getMessageUtil().sendMessage(player, "§7Die Items des Kits §8'§a§l" + kitName + "§8' §7wurden gespeichert§8.");
            player.playSound(player.getLocation(), Sound.ANVIL_USE, 3, 18.4F);
            return;
        }

        if (event.getInventory().getName().contains("§8┃ §c§lAIRDROP-EDIT§8: §7") && player.hasPermission(Permissions.ADMIN.getPermission())) {

            final long level = Long.parseLong(event.getInventory().getName().trim().replaceAll("§8┃ §c§lAIRDROP-EDIT§8: §7", ""));
            final List<ItemStack> tempItems = new ArrayList<>();

            for (ItemStack all : event.getInventory().getContents()) {
                if (all == null || all.getType() == Material.AIR) continue;
                tempItems.add(all);
            }

            Data.getAirDropController().setItemsFromAirdrop(level, tempItems);
            Data.getMessageUtil().sendMessage(player, "§7Die Items des AirDrops §8'§7Level §c" + level + "§8' §7wurden gespeichert§8.");
            player.playSound(player.getLocation(), Sound.ANVIL_USE, 3, 18.4F);
            return;
        }

        if (event.getInventory().getName().contains("§8┃ §9§lLAGERPLATZ §8: §7")) {
            LagerPlatzController lagerPlatzController;

            final String owner = event.getInventory().getName().replaceAll("§8┃ §9§lLAGERPLATZ §8: §7", "").trim();

            if (owner.equals(player.getName())) {
                lagerPlatzController = new LagerPlatzController(player.getUniqueId());
                player.updateInventory();
                lagerPlatzController.saveLagerPlatzItems(Arrays.asList(event.getInventory().getContents()));


            } else {

                if (!player.hasPermission(Permissions.LAGERPLATZCLICK.getPermission())) return;
                lagerPlatzController = new LagerPlatzController(Data.getUuidFetcher().getUUID(owner));
                player.updateInventory();

                lagerPlatzController.saveLagerPlatzItems(Arrays.asList(event.getInventory().getContents()));

            }

        }

    }
}
