package eu.skysoup.skypvp.shop.inventories;

import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.AcceptInventory;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.text.NumberFormat;

/**
 * Created: 11.02.2023
 *
 * @author benni
 */
public class ShopMainInventory extends SingletonInventory {
    public ShopMainInventory(final Player player) {
        super(InventoryTitles.SHOPMAIN.getName(), Rows.CHEST_ROW_4, player);

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
        setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
        setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
        setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15), null);
        setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7), null);
        setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7), null);
        setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7), null);

        setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(29, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(33, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE), null);
        setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE), null);

        setItem(30, new ItemBuilder(Material.PAPER).setName("§8┃ §f§lSHOP §8: §7Übersicht").lore(
                "§8§oHier siehst Du einen kleine",
                "§8§oÜberblick über deine Statistiken.",
                "",
                "§8┌ §7Aktuelle Tokens§8: §6$§e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.MONEY)),
                "§8├ §7Gekaufte Items§8: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP)),
                "§8├ §7Ausg. Tokens für Items§8: §6$§e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP)),
                "§8└ §7Nachtmarkt§8: §e" + (Data.getToggleController().isToggled(ToggleController.Types.NACHTMARKT) ? "§a§lGEÖFFNET" : "§c§lGESCHLOSSEN"),
                ""
        ), null);

        setItem(32, new ItemBuilder(Material.BOOK).setName("§8┃ §f§lSHOP §8: §7Erklärung").lore(
                "§8§oHier im Shop kannst Du verschiedene Items",
                "§8§oerwerben. Außerdem gibt es einen Nachtmarkt,",
                "§8§owelcher zwischen 2 bis 3 Uhr offen hat!",
                "§8§oBei diesem Nachtmarkt kannst Du",
                "§8§oaber auch abgezogen werden!",
                "",
                "§8├ §7Chance§8: §e§l" + Data.getConfigController().getLong("nachtmarktchance") + "§8%",
                ""
        ), null);

        setItem(31, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §8§lNachtmarkt", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTc4MmFiNWI2ZWYxZmE3OTNlZjA0ZGFjMGIyYWFkY2RiOTkyMDU3YzQzZmZjMWExYmMzODVkNWQ4ZGQwOTI1ZiJ9fX0=",
                "§8§oDer Nachtmarkt bietet Dir viele",
                "§8§oillegale und normale Items zu einem",
                "§8§osehr günstigen Preis an aber sei Dir",
                "§8§obewusst, dass Du jederzeit abgezogen",
                "§8§owerden kannst von einem Händler!",
                "",
                "§8├ §7Status§8: §7" + (Data.getToggleController().isToggled(ToggleController.Types.NACHTMARKT) ? "§a§lGEÖFFNET" : "§c§lGESCHLOSSEN"),
                ""
        ), null);

        setItem(10, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Blöcke §8(§7pt§8. §f1§8)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGU3Y2Q2MWY1MzgzZjNhOTgxN2U3MmY1YThkM2FiMzQ2NDY2MWU2ZWY0YzEwNGI5MTQwMjU3NDA4ZGI1YTM4YSJ9fX0=",
                "§8§oKlicke, um die Blöcke",
                "§8§oKategorie zu öffnen!"
        ), (click, type) -> {
            new ShopBlockCategoryOneInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });

        setItem(11, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Blöcke §8(§7pt§8. §f2§8)", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YxMzQ2MDkyYzgwZDNkYjIxN2VmZTRjOTM2OTY5MWU2MWM4YWZjMWIyODc0MWZhNTA0ODJjOTJjOWZkM2QxOCJ9fX0=",
                "§8§oKlicke, um die Blöcke",
                "§8§oKategorie zu öffnen!"
        ), (click, type) -> {
            new ShopBlockCategoryTwoInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });

        setItem(12, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Equipment", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDFlZGVhMzVmNzk3N2VlYmJhMzI4NGY1ZjQxNjU3NzBmMmU5NDZmMGMwZjFmMzA3ZjI1NTcwMzBmMzNmMzA0OCJ9fX0=",
                "§8§oKlicke, um die Equipment",
                "§8§oKategorie zu öffnen!"
        ), (click, type) -> {
            new ShopEquipmentInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });

        setItem(13, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Tools", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmRhZjY4MjU5ZmQyNzlkN2NiZmVkZWIzYzlkOGU3ZTNkZWYxNmMwOWRmZDg0NTIyZjQ4OGJlMWUwMjdmZTVlMyJ9fX0=",
                "§8§oKlicke, um die Tools",
                "§8§oKategorie zu öffnen!"
        ), (click, type) -> {
            new ShopToolsInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });

        setItem(14, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Verschiedenes", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ4NDNlYTVmZTA5MzMxNWQ4YjNmNDAxNWIyYTZjMmNjMjNhZTUyZThhYWIxNDczYmJmMmY2MjM1NDJmNTI1YiJ9fX0=",
                "§8§oKlicke, um die Verschiedenes",
                "§8§oKategorie zu öffnen!"
        ), (click, type) -> {
            new ShopMiscellaneousInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });

        setItem(19, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Besonderes", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZkY2E0MDgwM2FiNDk1NjIzNzAzM2E5ZmNhMGRkYTBhNTBjNTZiMTg2OTgyYWFiM2IyMzBkMmRhNmE4OTk4YiJ9fX0=",
                "§8§oKlicke, um die Besonderes",
                "§8§oKategorie zu öffnen!"
        ), (click, type) -> {
            new ShopRareInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });

        setItem(25, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §f§LZ§b§Lu§3§lf§f§lä§b§ll§3§ll§f§li§b§lg§3§le§f§lr §b§lK§3§li§f§ls§b§lt§3§le§f§ln§b§ls§3§lc§f§lh§b§ll§3§lü§f§ls§b§ls§3§le§f§ll", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM0OTcwZWE5MWFiMDZlY2U1OWQ0NWZjZTc2MDRkMjU1NDMxZjJlMDNhNzM3YjIyNjA4MmM0Y2NlMWFjYTFjNCJ9fX0=",
                "§8§oKlicke, um einen zufälligen",
                "§8§oKistenschlüssel zu erwerben!",
                "",
                "§8├ §7Preis§8: §6$§e10.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 10000) {
                player.closeInventory();
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 10000);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 10000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e10.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §f§LZ§b§Lu§3§lf§f§lä§b§ll§3§ll§f§li§b§lg§3§le§f§lr §b§lK§3§li§f§ls§b§lt§3§le§f§ln§b§ls§3§lc§f§lh§b§ll§3§lü§f§ls§b§ls§3§le§f§ll");
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                    } else {
                        new ShopMainInventory(player).openGUI();
                        Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                    }
                },
                        "§8§oKlicke auf einen der beiden Köpfe, um",
                        "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                        "",
                        "§8┌ §7Folgendes Item wird erworben:",
                        "§8└ §f§LZ§b§Lu§3§lf§f§lä§b§ll§3§ll§f§li§b§lg§3§le§f§lr §b§lK§3§li§f§ls§b§lt§3§le§f§ln§b§ls§3§lc§f§lh§b§ll§3§lü§f§ls§b§ls§3§le§f§ll",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e10.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });
    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
