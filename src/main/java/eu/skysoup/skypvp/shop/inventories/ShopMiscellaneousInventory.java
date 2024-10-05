package eu.skysoup.skypvp.shop.inventories;

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

public class ShopMiscellaneousInventory extends SingletonInventory {
    public ShopMiscellaneousInventory(final Player player) {
        super(InventoryTitles.SHOPVERSCH.getName(), Rows.CHEST_ROW_5, player);

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);

        setItem(11, new ItemBuilder(Material.BREWING_STAND_ITEM).setName("§8┃ §f§lSHOP §8: §7Braustand").lore(
                "§8§oVerschiedenes",
                "",
                "§8├ §7Preis§8: §6$§e50.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 50000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 50000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 50000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e50.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Braustand");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        player.getInventory().addItem(new ItemBuilder(Material.BREWING_STAND_ITEM));

                        new ShopMiscellaneousInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Braustand",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e50.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(12, new ItemBuilder(Material.WATER_BUCKET).setName("§8┃ §f§lSHOP §8: §7Wassereimer").lore(
                "§8§oVerschiedenes",
                "",
                "§8├ §7Preis§8: §6$§e500",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 500) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 500);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 500);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e500 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Wassereimer");
                        player.getInventory().addItem(new ItemBuilder(Material.WATER_BUCKET));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopMiscellaneousInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Wassereimer",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e500",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(13, new ItemBuilder(Material.LAVA_BUCKET).setName("§8┃ §f§lSHOP §8: §7Lavaeimer").lore(
                "§8§oVerschiedenes",
                "",
                "§8├ §7Preis§8: §6$§e500",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 500) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 500);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 500);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e500 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Lavaeimer");
                        player.getInventory().addItem(new ItemBuilder(Material.LAVA_BUCKET));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);

                        new ShopMiscellaneousInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Lavaeimer",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e500",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(14, new ItemBuilder(Material.ITEM_FRAME).setName("§8┃ §f§lSHOP §8: §7Rahmen").amount(8).lore(
                "§8§oVerschiedenes",
                "",
                "§8├ §7Preis§8: §6$§e5.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 5000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 5000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 5000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e5.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f8§8x §7Rahmen");
                        player.getInventory().addItem(new ItemBuilder(Material.ITEM_FRAME).amount(8));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopMiscellaneousInventory(player).openGUI();

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
                        "§8└ §f8§8x §7Rahmen",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e5.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(15, new ItemBuilder(Material.ENDER_CHEST).setName("§8┃ §f§lSHOP §8: §7Enderkiste").lore(
                "§8§oVerschiedenes",
                "",
                "§8├ §7Preis§8: §6$§e15.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 15000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 15000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 15000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e15.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Enderkiste");
                        player.getInventory().addItem(new ItemBuilder(Material.ENDER_CHEST));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);

                        new ShopMiscellaneousInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Enderkiste",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e15.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(20, new ItemBuilder(Material.EXP_BOTTLE).setName("§8┃ §f§lSHOP §8: §7Erfahrungsflässchen").amount(12).lore(
                "§8§oVerschiedenes",
                "",
                "§8├ §7Preis§8: §6$§e15.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 15000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 15000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 15000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e15.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f12§8x §7Erfahrungsflässchen");
                        player.getInventory().addItem(new ItemBuilder(Material.EXP_BOTTLE).amount(12));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopMiscellaneousInventory(player).openGUI();

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
                        "§8└ §f12§8x §7Erfahrungsflässchen",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e15.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

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
