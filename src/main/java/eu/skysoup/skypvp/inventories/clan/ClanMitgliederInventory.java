package eu.skysoup.skypvp.inventories.clan;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.impl.SkullUtil;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 24.02.2023 18:09
 *
 * @author thvf
 */
public class ClanMitgliederInventory extends SingletonInventory {

    public ClanMitgliederInventory(@NonNull Player player, @NonNull final String clan) {
        super(InventoryTitles.CLAN_MITGLIEDER.getName(), Rows.CHEST_ROW_6, player);

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        AtomicInteger atomicInteger = new AtomicInteger(11);
        AtomicInteger count = new AtomicInteger(1);
        for (String allUUID : Data.getClanController().getMitglieder(clan)) {


            final ItemBuilder itemBuilder = new ItemBuilder(SkullUtil.getCachedSkull(UUID.fromString(allUUID))).setName("§8┃ §9§l" + Data.getUuidFetcher().getName(UUID.fromString(allUUID)) + " §8- §8#§b" + count.get()).lore(
                    "",
                    "§8├ §7Rang§8: " + (Objects.equals(Data.getUuidFetcher().getName(UUID.fromString(Data.getClanController().getClanBesitzer(player))), Data.getUuidFetcher().getName(UUID.fromString(allUUID))) ? "§cBesitzer" : "§aMitglied"),
                    "§8├ §7Status§8: " + (Bukkit.getPlayer(UUID.fromString(allUUID)) == null ? "§c§lOFFLINE" : "§a§lONLINE"),
                    ""
            );

            if (atomicInteger.get() == 16 || atomicInteger.get() == 17 || atomicInteger.get() == 18 || atomicInteger.get() == 19)
                atomicInteger.set(20);
            if (atomicInteger.get() == 25 || atomicInteger.get() == 26 || atomicInteger.get() == 27 || atomicInteger.get() == 28)
                atomicInteger.set(29);
            if (atomicInteger.get() == 34 || atomicInteger.get() == 35 || atomicInteger.get() == 37 || atomicInteger.get() == 37)
                atomicInteger.set(38);
            if (atomicInteger.get() > 42) return;


            setItem(atomicInteger.get(), itemBuilder, null);


            atomicInteger.getAndIncrement();
            count.getAndIncrement();

        }

        setItem(45, ItemSkull.getSkull("§8┃ §b§lCLAN §8: §7Zurück", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==",
                "§8§oKlicke, um zum",
                "§8§oHauptmenü zu gelangen."
        ), (click, type) -> {
            new ClanInventory(player, clan).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
    }
}
