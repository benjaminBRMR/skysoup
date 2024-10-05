package eu.skysoup.skypvp.inventories.clan;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.impl.StringUtil;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created: 24.02.2023 17:15
 *
 * @author thvf
 */
public class ClanInventory extends SingletonInventory {


    public ClanInventory(@NonNull Player player, @NonNull final String clan) {
        super(InventoryTitles.CLAN.getName(), Rows.CHEST_ROW_6, player);

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        final ItemStack clanOverview = new ItemBuilder(Material.BANNER).setDataId(1).setName("§8┃ §b§lCLAN §8: §7Übersicht").lore(
                "§8§oHier findest du eine",
                "§8§oeinfache Clanübersicht.",
                "",
                "§8┌ §7Clanbesitzer§8: §c" + Data.getUuidFetcher().getName(UUID.fromString(Data.getClanController().getClanBesitzer(clan))),
                "§8├ §7Gründungsdatum§8: §c" + new SimpleDateFormat("dd.MM.yyyy").format(new Date(Data.getClanController().getClanErstellt(clan))),
                "§8├ §7Clan EXP§8: §e" + Data.getClanController().getClanEXP(clan),
                "§8└ §7Clan Tag§8: " + (Data.getClanController().hasCustomClanTag(clan) ? Data.getClanController().getCustomClanTag(clan).replaceAll("%CLAN%", StringUtil.capitalize(clan.toLowerCase())) : "§7" + StringUtil.capitalize(clan.toLowerCase())),
                ""
        );

        final ItemStack clanWappen = new ItemBuilder(Data.getClanController().getClanWappen(clan).getItemStack()).setName("§8┃ §b§lCLAN §8: §7Wappen").lore(
                "§8§oKlicke, um das Wappen",
                "§8§odeines Clan zu bestimmen.",
                "",
                "§8├ §7Aktuelles Wappen§8: §e" + Data.getClanController().getClanWappen(clan).getName(),
                ""
        );

        final ItemStack clanMitgliederListe = new ItemBuilder(Material.BOOK_AND_QUILL).setName("§8┃ §b§lCLAN §8: §7Mitgliederliste").lore(
                "§8§oHier findest du alles über",
                "§8§odeine ganzen Clanmitglieder.",
                "",
                "§8┌ §7Mitgliederanzahl§8: §e" + Data.getClanController().getMitglieder(clan).size() + "§7/§6" + Data.getClanController().getMaxMitglieder(clan),
                "§8└ §7Mitglieder online§8: §e" + Data.getClanController().getOnlineMitglieder(clan).size(),
                "",
                "§8├ §7Klicke§8, §7um eine Gesamtliste abzurufen.",
                ""
        );


        setItem(12, clanOverview, null);
        setItem(14, clanWappen, (click, clickType) -> {

            if (!Data.getClanController().isClanBesitzer(player, clan)) {
                Data.getMessageUtil().sendMessage(player, "§cNur der Clanbesitzer kann das Wappen ändern!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                return;
            }
            player.sendMessage("clanwappenseite");
        });

        setItem(29, clanMitgliederListe, (click, type) -> {
            new ClanMitgliederInventory(player, clan).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
