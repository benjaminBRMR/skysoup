package eu.skysoup.skypvp.shop.inventories;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
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

public class ShopEquipmentInventory extends SingletonInventory {
    public ShopEquipmentInventory(final Player player) {
        super(InventoryTitles.SHOPEQUIP.getName(), Rows.CHEST_ROW_5, player);

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);

        setItem(11, new ItemBuilder(Material.BOOK).setName("§8┃ §f§lSHOP §8: §c§lPVP§8-§c§LPAKET §8(§7Tier §cI§8)").lore(
                "§8§oEquipment",
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
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §c§lPVP§8-§c§LPAKET §8(§7Tier §cI§8)");
                        player.getInventory().addItem(new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §c§lPVP§8-§c§lPAKET").lore(
                                "§8§oLöse ein PvP-Paket ein und kämpfe in",
                                "§8§oder Arena und besiege die Welt!",
                                "",
                                " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                                " §8└ §7Gutschein-Wert§8: §7Tier §cI",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §c§lPVP§8-§c§LPAKET §8(§7Tier §cI§8)",
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

        setItem(12, new ItemBuilder(Material.BOOK).setName("§8┃ §f§lSHOP §8: §c§lPVP§8-§c§LPAKET §8(§7Tier §cII§8)").lore(
                "§8§oEquipment",
                "",
                "§8├ §7Preis§8: §6$§e30.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 30000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 30000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 30000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e30.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §c§lPVP§8-§c§LPAKET §8(§7Tier §cII§8)");
                        player.getInventory().addItem(new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §c§lPVP§8-§c§lPAKET").lore(
                                "§8§oLöse ein PvP-Paket ein und kämpfe in",
                                "§8§oder Arena und besiege die Welt!",
                                "",
                                " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                                " §8└ §7Gutschein-Wert§8: §7Tier §cII",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §c§lPVP§8-§c§LPAKET §8(§7Tier §cII§8)",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e30.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(13, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8┃ §f§lSHOP §8: §7AirDrop§8: §cLevel 1").lore(
                "§8§oEquipment",
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
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7AirDrop§8: §cLevel 1");
                        player.getInventory().addItem(new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8» §7AirDrop§8: §cLevel 1").lore(
                                "§8§oPlatziere diesen AirDrop, um viele",
                                "§8§overschiedene Items",
                                "§8§ogeliefert zu bekommen.",
                                "",
                                "§8├ §7Level§8: §c1",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §7AirDrop§8: §cLevel 1",
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

        setItem(14, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8┃ §f§lSHOP §8: §7AirDrop§8: §cLevel 2").lore(
                "§8§oEquipment",
                "",
                "§8├ §7Preis§8: §6$§e150.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 150000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 150000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 150000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e150.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7AirDrop§8: §cLevel 2");
                        player.getInventory().addItem(new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8» §7AirDrop§8: §cLevel 2").lore(
                                "§8§oPlatziere diesen AirDrop, um viele",
                                "§8§overschiedene Items",
                                "§8§ogeliefert zu bekommen.",
                                "",
                                "§8├ §7Level§8: §c2",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §7AirDrop§8: §cLevel 2",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e150.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(14, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8┃ §f§lSHOP §8: §7AirDrop§8: §cLevel 2").lore(
                "§8§oEquipment",
                "",
                "§8├ §7Preis§8: §6$§e150.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 150000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 150000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 150000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e150.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7AirDrop§8: §cLevel 2");
                        player.getInventory().addItem(new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8» §7AirDrop§8: §cLevel 2").lore(
                                "§8§oPlatziere diesen AirDrop, um viele",
                                "§8§overschiedene Items",
                                "§8§ogeliefert zu bekommen.",
                                "",
                                "§8├ §7Level§8: §c2",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §7AirDrop§8: §cLevel 2",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e150.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(15, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8┃ §f§lSHOP §8: §7AirDrop§8: §cLevel 3").lore(
                "§8§oEquipment",
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
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7AirDrop§8: §cLevel 3");
                        player.getInventory().addItem(new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8» §7AirDrop§8: §cLevel 3").lore(
                                "§8§oPlatziere diesen AirDrop, um viele",
                                "§8§overschiedene Items",
                                "§8§ogeliefert zu bekommen.",
                                "",
                                "§8├ §7Level§8: §c3",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §7AirDrop§8: §cLevel 3",
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

        setItem(21, new ItemBuilder(Material.NETHER_STAR).setName("§8┃ §f§lSHOP §8: §7§lREPAIR §8§lTOKEN").lore(
                "§8§oEquipment",
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
                        Data.getMessageUtil().sendMessage(player, "§8└ §f2§8x §7§lREPAIR §8§lTOKEN");
                        player.getInventory().addItem(new ItemBuilder(Material.NETHER_STAR).setName("§8» §7Gutschein§8: §8§lREPAIR §7§lTOKEN").lore(
                                "§8§oDieser Repair Token ermöglicht es dir dein,",
                                "§8§oGegenstand in der Hand zu reparieren.",
                                "",
                                " §8┌ §7Gutschein-Type§8: §7§l" + GutscheinTypes.OTHER.name().toUpperCase(),
                                " §8└ §7Gutschein-Wert§8: §7Tier §cII",
                                " §8└ §7Gutschein-Wert§8: §7§l1 REPERATUR",
                                ""
                        ));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f2§8x §7§lREPAIR §8§lTOKEN",
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

        setItem(20, new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§8┃ §f§lSHOP §8: §fKis§7ten §fSchl§7üss§fel§8: §c§LPvP").lore(
                "§8§oEquipment",
                "",
                "§8├ §7Preis§8: §6$§e40.000",
                ""
        ).glow(true), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 40000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 40000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 40000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e40.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §fKis§7ten §fSchl§7üss§fel§8: §c§LPvP");
                        player.getInventory().addItem(new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§8» §fKis§7ten §fSchl§7üss§fel§8: §c§LPvP").lore(
                                "§8§oNutze diesen Kistenschlüssel am",
                                "§8§oSpawn, um Belohnungen zu erhalten."
                        ).glow(true));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f1§8x §fKis§7ten §fSchl§7üss§fel§8: §c§LPvP",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e40.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(22, new ItemBuilder(Material.POTION).setName("§8┃ §f§lSHOP §8: §fHeiltränke").setDataId(16453).amount(3).lore(
                "§8§oEquipment",
                "",
                "§8├ §7Preis§8: §6$§e10.000",
                ""
        ).glow(true), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 10000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 10000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 10000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e10.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f3§8x §fHeiltränke");
                        player.getInventory().addItem(new ItemBuilder(Material.POTION).setName("§8» §fHeiltränke").setDataId(16453).amount(3));

                        new ShopEquipmentInventory(player).openGUI();

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
                        "§8└ §f3§8x §fHeiltränke",
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