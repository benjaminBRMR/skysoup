package eu.skysoup.skypvp.inventories.trophy;

import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created: 29.01.2023 01:54
 *
 * @author thvf
 */
public class TrophyInventory extends SingletonInventory {

    public TrophyInventory(final Player player) {
        super(InventoryTitles.TROPHY.getName(), Rows.CHEST_ROW_4, player);

        final String trophyShop = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzVjOWNjY2Y2MWE2ZTYyODRmZTliYmU2NDkxNTViZTRkOWNhOTZmNzhmZmNiMjc5Yjg0ZTE2MTc4ZGFjYjUyMiJ9fX0=";

        final String stats = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM0YTU5MmE3OTM5N2E4ZGYzOTk3YzQzMDkxNjk0ZmMyZmI3NmM4ODNhNzZjY2U4OWYwMjI3ZTVjOWYxZGZlIn19fQ==";


        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        setItem(15, new ItemBuilder(Material.BARRIER).setName("§c§l§k###"), null);

        setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(20, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);
        setItem(24, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(4).setName("§r"), null);

        setItem(11, ItemSkull.getSkull("§8┃ §e§lTROPHÄEN §8: §7Markt", trophyShop,
                "§8§oNutze deine Trophäen,",
                "§8§oum tolle Gegenstände zu erwerben.",
                "",
                "§8┌ §7Items im Markt§8: §e" + Data.getTrophyShopController().getListedItems().size(),
                "§8└ §7Aktueller Marktstatus§8: " + (Data.getToggleController().isToggled(ToggleController.Types.TROPHYMARKT) ? "§a§lGEÖFFNET" : "§c§lGESCHLOSSEN"),
                ""
        ), (click, type) -> {

            if (!Data.getToggleController().isToggled(ToggleController.Types.TROPHYMARKT)) {
                Data.getMessageUtil().sendMessage(player, "§cDer Trophäenmarkt hat aktuell geschlossen!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                return;
            }


            new TrophyMarktInventory(player).openGUI();


        });

        HashMap<UUID, Long> trophyMap = new HashMap<>();

        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
            trophyMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.TROPHYS));
        }

        Collections.reverse(Arrays.asList(trophyMap.keySet().toArray()));

        setItem(22, ItemSkull.getSkull("§8┃ §e§lTROPHÄEN §8: §7Stats", stats,
                "§8§oHier findest du eine Übersicht",
                "§8§odeiner Trophäen-Statistiken!",
                "",
                "§8┌ §7Deine aktuellen Trophäen§8: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.TROPHYS)),
                "§8└ §7Ausg. Trophäen im Markt§8: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.SPENTTROPHIES)),
                ""
        ), null);


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
