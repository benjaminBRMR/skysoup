package eu.skysoup.skypvp.shop.inventories;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.controller.other.LagerPlatzController;
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

public class ShopRareInventory extends SingletonInventory {
    public ShopRareInventory(final Player player) {
        super(InventoryTitles.SHOPBES.getName(), Rows.CHEST_ROW_5, player);

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        CasinoController casinoController = new CasinoController().getUser(player);
        LagerPlatzController lagerPlatzController = new LagerPlatzController(player.getUniqueId());

        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);


        if (!casinoController.hasPlayerCasinoPass()) {
            setItem(11, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §6§lCAS§e§lINO §7Mitgliedschaft", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ2NWMxMjE5NThjMDUyMmUzZGNjYjNkMTRkNjg2MTJkNjMxN2NkMzgwYjBlNjQ2YjYxYjc0MjBiOTA0YWYwMiJ9fX0=",
                    "§8§oBesonderes",
                    "",
                    "§8┌ §eWas kann die Casino Mitgliedschaft?",
                    "§8├ §7Die Mitgliedschaft ermöglicht dir",
                    "§8└ §7vollen Zugang zu §8/§6casino§8!",
                    "",
                    "§8├ §7Preis§8: §6$§e250.000",
                    ""
            ), (click, type) -> {
                if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250000) {
                    final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                        if (clicked) {
                            userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250000);
                            userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                            userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250000);
                            Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                            Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250.000 §6Tokens");
                            Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §6§lCAS§e§lINO §7Mitgliedschaft");
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                            casinoController.setPlayerCasinoPass();
                            new ShopRareInventory(player).openGUI();

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
                            "§8└ §f1§8x §6§lCAS§e§lINO §7Mitgliedschaft",
                            "",
                            "§8┌ §7Folgender Betrag wird ausgegeben:",
                            "§8└ §6$§e250.000",
                            "");
                    acceptInventory.openGUI();
                } else {
                    Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                }
            });
        } else {
            setItem(11, new ItemBuilder(Material.INK_SACK).setDataId(8).setName("§6§lCAS§e§lINO §7Mitgliedschaft").lore(
                    "§8§oDie Casino Mitgliedschaft",
                    "§8§ohast Du bereits erworben!"
            ), null);
        }


        if (!lagerPlatzController.hasLagerPlatz()) {
            setItem(12, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §9§LLAGERPLATZ §7Reihe §f1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU2MzllNDFiZTM2ODgzMGEyYzdiYTNmYmQ2OWUyZWJhZjhjMzkxZDNmNzI1YzI4NDRlOGE1MmI0NTZhZjQyMSJ9fX0=",
                    "§8§oBesonderes",
                    "",
                    "§8├ §7Preis§8: §6$§e250.000",
                    ""
            ), (click, type) -> {
                if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250000) {
                    final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                        if (clicked) {
                            userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250000);
                            userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                            userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250000);
                            Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                            Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250.000 §6Tokens");
                            Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §9§LLAGERPLATZ §7Reihe §f1");
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                            lagerPlatzController.createLagerPlatz();
                            new ShopRareInventory(player).openGUI();

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
                            "§8└ §f1§8x §9§LLAGERPLATZ §7Reihe §f1",
                            "",
                            "§8┌ §7Folgender Betrag wird ausgegeben:",
                            "§8└ §6$§e250.000",
                            "");
                    acceptInventory.openGUI();
                } else {
                    Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                }
            });
        } else {
            setItem(12, new ItemBuilder(Material.INK_SACK).setDataId(8).setName("§9§LLAGERPLATZ §7Reihe §f1").lore(
                    "§8§oDiese Reihe hast Du",
                    "§8§obereits erworben."
            ), null);
        }


        setItem(36, ItemSkull.getSkull("§8┃ §f§lSHOP §8: §7Zurück", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==",
                "§8§oKlicke, um zum",
                "§8§oHauptmenü zu gelangen."
        ), (click, type) -> {
            new ShopMainInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });
    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
