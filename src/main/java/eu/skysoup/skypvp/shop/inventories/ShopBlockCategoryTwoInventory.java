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

public class ShopBlockCategoryTwoInventory extends SingletonInventory {
    public ShopBlockCategoryTwoInventory(final Player player) {
        super(InventoryTitles.SHOPBLOCK2.getName(), Rows.CHEST_ROW_5, player);

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);

        setItem(11, new ItemBuilder(Material.LOG).setName("§8┃ §f§lSHOP §8: §7Eichenholz").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Eichenholz");
                        player.getInventory().addItem(new ItemBuilder(Material.LOG).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Eichenholz",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(12, new ItemBuilder(Material.LOG).setName("§8┃ §f§lSHOP §8: §7Fichtenholz").setDataId(1).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Fichtenholz");
                        player.getInventory().addItem(new ItemBuilder(Material.LOG).setDataId(1).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Fichtenholz",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(13, new ItemBuilder(Material.LOG).setName("§8┃ §f§lSHOP §8: §7Birkenholz").setDataId(2).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Birkenholz");
                        player.getInventory().addItem(new ItemBuilder(Material.LOG).setDataId(2).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Birkenholz",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(14, new ItemBuilder(Material.LOG).setName("§8┃ §f§lSHOP §8: §7Tropenholz").setDataId(3).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Tropenholz");
                        player.getInventory().addItem(new ItemBuilder(Material.LOG).setDataId(3).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Tropenholz",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(15, new ItemBuilder(Material.LOG_2).setName("§8┃ §f§lSHOP §8: §7Akazienholz").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Akazienholz");
                        player.getInventory().addItem(new ItemBuilder(Material.LOG_2).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Akazienholz",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(20, new ItemBuilder(Material.LOG_2).setName("§8┃ §f§lSHOP §8: §7Schwarzeichenholz").setDataId(1).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Schwarzeichenholz");
                        player.getInventory().addItem(new ItemBuilder(Material.LOG_2).setDataId(1).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Schwarzeichenholz",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(21, new ItemBuilder(Material.LEAVES).setName("§8┃ §f§lSHOP §8: §7Eichenlaub").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Eichenlaub");
                        player.getInventory().addItem(new ItemBuilder(Material.LEAVES).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Eichenlaub",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(22, new ItemBuilder(Material.LEAVES).setName("§8┃ §f§lSHOP §8: §7Fichtennadeln").setDataId(1).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Fichtennadeln");
                        player.getInventory().addItem(new ItemBuilder(Material.LEAVES).setDataId(1).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Fichtennadeln",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(23, new ItemBuilder(Material.LEAVES).setName("§8┃ §f§lSHOP §8: §7Birkenlaub").setDataId(2).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Birkenlaub");
                        player.getInventory().addItem(new ItemBuilder(Material.LEAVES).setDataId(2).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Birkenlaub",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(24, new ItemBuilder(Material.LEAVES).setName("§8┃ §f§lSHOP §8: §7Tropenbaumlaub").setDataId(3).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Tropenbaumlaub");
                        player.getInventory().addItem(new ItemBuilder(Material.LEAVES).setDataId(3).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Tropenbaumlaub",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(29, new ItemBuilder(Material.LEAVES_2).setName("§8┃ §f§lSHOP §8: §7Akazienlaub").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Akazienlaub");
                        player.getInventory().addItem(new ItemBuilder(Material.LEAVES_2).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Akazienlaub",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(30, new ItemBuilder(Material.LEAVES_2).setName("§8┃ §f§lSHOP §8: §7Schwarzeichenlaub").setDataId(1).amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e250",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 250) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 250);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 250);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e250 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Schwarzeichenlaub");
                        player.getInventory().addItem(new ItemBuilder(Material.LEAVES_2).setDataId(1).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Schwarzeichenlaub",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e250",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(31, new ItemBuilder(Material.BRICK).setName("§8┃ §f§lSHOP §8: §7Ziegelsteine").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e2.500",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 2500) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 2500);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 2500);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e2.500 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Ziegelsteine");
                        player.getInventory().addItem(new ItemBuilder(Material.BRICK).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Ziegelsteine",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e2.500",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(32, new ItemBuilder(Material.HARD_CLAY).setName("§8┃ §f§lSHOP §8: §7Gebrannter Ton").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Gebrannter Ton");
                        player.getInventory().addItem(new ItemBuilder(Material.HARD_CLAY).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Gebrannter Ton",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(33, new ItemBuilder(Material.GLASS).setName("§8┃ §f§lSHOP §8: §7Glass").amount(16).lore(
                "§8§oBaumaterial",
                "",
                "§8├ §7Preis§8: §6$§e1.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 1000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 1000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 1000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e1.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f16§8x §7Glass");
                        player.getInventory().addItem(new ItemBuilder(Material.GLASS).amount(16));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopBlockCategoryTwoInventory(player).openGUI();

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
                        "§8└ §f16§8x §7Glass",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e1.000",
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