package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.ClantagTypes;
import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created: 05.02.2023 13:38
 *
 * @author thvf
 */
public class ClanTagInventory extends SingletonInventory {


    public ClanTagInventory(final Player player) {
        super(InventoryTitles.CLANTAG.getName(), Rows.CHEST_ROW_3, player);

        final String clan = Data.getClanController().getClan(player);
        final String acceptID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkyZTMxZmZiNTljOTBhYjA4ZmM5ZGMxZmUyNjgwMjAzNWEzYTQ3YzQyZmVlNjM0MjNiY2RiNDI2MmVjYjliNiJ9fX0=";

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        final ItemBuilder color1 = new ItemBuilder(Material.BANNER).setName("§8┃ " + ClantagTypes.COLOR1.getName().replaceAll("%CLAN%", clan.toUpperCase()) + " §8: §7Variante 1").lore("§8§oKlicke, um diese ClanTag-Variante auszuwählen.", "§8§oZum bestätigen, klicke auf den Bestätigungskopf!").setDataId(0);

        final ItemBuilder color2 = new ItemBuilder(Material.BANNER).setName("§8┃ " + ClantagTypes.COLOR2.getName().replaceAll("%CLAN%", clan.toUpperCase()) + " §8: §7Variante 2").lore("§8§oKlicke, um diese ClanTag-Variante auszuwählen.", "§8§oZum bestätigen, klicke auf den Bestätigungskopf!").setDataId(0);

        final ItemBuilder color3 = new ItemBuilder(Material.BANNER).setName("§8┃ " + ClantagTypes.COLOR3.getName().replaceAll("%CLAN%", clan.toUpperCase()) + " §8: §7Variante 3").lore("§8§oKlicke, um diese ClanTag-Variante auszuwählen.", "§8§oZum bestätigen, klicke auf den Bestätigungskopf!!").setDataId(0);

        final ItemBuilder color4 = new ItemBuilder(Material.BANNER).setName("§8┃ " + ClantagTypes.COLOR4.getName().replaceAll("%CLAN%", clan.toUpperCase()) + " §8: §7Variante 4").lore("§8§oKlicke, um diese ClanTag-Variante auszuwählen.", "§8§oZum bestätigen, klicke auf den Bestätigungskopf!").setDataId(0);

        final ItemBuilder color5 = new ItemBuilder(Material.BANNER).setName("§8┃ " + ClantagTypes.COLOR5.getName().replaceAll("%CLAN%", clan.toUpperCase()) + " §8: §7Variante 5").lore("§8§oKlicke, um diese ClanTag-Variante auszuwählen.", "§8§oZum bestätigen, klicke auf den Bestätigungskopf!").setDataId(0);


        setItem(11, color1, (click, type) -> {
            Data.getInventoryMaps().acceptHashMap.put(player, ClantagTypes.COLOR1);
            setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8┃ §a§lAUSGEWÄHLT §8⬇"), null);
            setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
        });

        setItem(12, color2, (click, type) -> {
            Data.getInventoryMaps().acceptHashMap.put(player, ClantagTypes.COLOR2);
            setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8┃ §a§lAUSGEWÄHLT §8⬇"), null);
            setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);

        });

        setItem(13, color3, (click, type) -> {
            Data.getInventoryMaps().acceptHashMap.put(player, ClantagTypes.COLOR3);
            setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8┃ §a§lAUSGEWÄHLT §8⬇"), null);
            setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);

        });

        setItem(14, color4, (click, type) -> {
            Data.getInventoryMaps().acceptHashMap.put(player, ClantagTypes.COLOR4);
            setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8┃ §a§lAUSGEWÄHLT §8⬇"), null);
            setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);


        });

        setItem(15, color5, (click, type) -> {
            Data.getInventoryMaps().acceptHashMap.put(player, ClantagTypes.COLOR5);
            setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
            setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8┃ §a§lAUSGEWÄHLT §8⬇"), null);


        });


        setItem(22, ItemSkull.getSkull("§8┃ §f§lCL§b§lANTAG §8: §7Auswählen", acceptID,
                "§8§oKlicke, um deinen ausgewählten Clantag",
                "§8§oauszuwählen, dieser wird daraufhin direkt gesetzt!"
        ), (click, type) -> {

            if (Data.getInventoryMaps().acceptHashMap.get(player) != null) {

                for (ItemStack items : player.getInventory().getContents()) {

                    if (items == null || items.getType() == Material.AIR) continue;
                    if (!items.getItemMeta().hasDisplayName()) continue;

                    if (items.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §6§l" + GutscheinTypes.CLAN.getPrefix())) {

                        if (Data.getInventoryMaps().acceptHashMap.containsKey(player)) {
                            player.playSound(player.getLocation(), Sound.BURP, 10, 0.1F);


                            Data.getClanController().setCustomClanTag(clan, Data.getInventoryMaps().acceptHashMap.get(player));

                            Data.getClanController().getMitglieder(clan).forEach(all -> {
                                if (Bukkit.getPlayer(UUID.fromString(all)) == null) return;
                                Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(all)), "§7Dein Clantag wurde von §e" + player.getName() + " §7geändert!");
                                Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(all)), " §8├ §7Neuer Clantag§8: " + Data.getClanController().getCustomClanTag(clan).replaceAll("%CLAN%", clan.toUpperCase()));

                            });

                            Data.getPlayerUtil().removeItems(player.getInventory(), items, 1);
                        }


                        player.closeInventory();
                    }
                }
                return;
            }


            Data.getMessageUtil().sendMessage(player, "§cBitte wähle einen gültigen Clantag aus!");


        });


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
        Data.getInventoryMaps().acceptHashMap.remove(player);
    }
}
