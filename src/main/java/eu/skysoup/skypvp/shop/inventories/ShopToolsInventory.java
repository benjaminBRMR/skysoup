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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;

public class ShopToolsInventory extends SingletonInventory {
    public ShopToolsInventory(final Player player) {
        super(InventoryTitles.SHOPTOOLS.getName(), Rows.CHEST_ROW_5, player);

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);
        setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r"), null);

        setItem(11, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§8┃ §f§lSHOP §8: §7Verzauberte Spitzhacke").addFlag(ItemFlag.HIDE_ATTRIBUTES).glow(true).lore(
                "§7Effizienz V",
                "§7Haltbarkeit III",
                "§8§oTools",
                "",
                "§8├ §7Preis§8: §6$§e10.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 10000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 10000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 10000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e10.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Verzauberte Spitzhacke");
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopToolsInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Verzauberte Spitzhacke",
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

        setItem(12, new ItemBuilder(Material.DIAMOND_AXE).setName("§8┃ §f§lSHOP §8: §7Verzauberte Axt").addFlag(ItemFlag.HIDE_ATTRIBUTES).glow(true).lore(
                "§7Effizienz V",
                "§7Haltbarkeit III",
                "§8§oTools",
                "",
                "§8├ §7Preis§8: §6$§e10.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 10000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 10000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 10000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e10.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Verzauberte Axt");
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopToolsInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Verzauberte Axt",
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

        setItem(13, new ItemBuilder(Material.DIAMOND_SPADE).setName("§8┃ §f§lSHOP §8: §7Verzauberte Schaufel").addFlag(ItemFlag.HIDE_ATTRIBUTES).glow(true).lore(
                "§7Effizienz V",
                "§7Haltbarkeit III",
                "§8§oTools",
                "",
                "§8├ §7Preis§8: §6$§e10.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 10000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 10000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 10000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e10.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §7Verzauberte Schaufel");
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopToolsInventory(player).openGUI();

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
                        "§8└ §f1§8x §7Verzauberte Schaufel",
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

        setItem(14, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§8┃ §f§lSHOP §8: §4§k.§c§lDANGER§4§k.§7 Spitzhacke").addFlag(ItemFlag.HIDE_ATTRIBUTES).glow(true).lore(
                "§7Effizienz X",
                "§7Haltbarkeit X",
                "§8§oTools",
                "",
                "§8├ §7Preis§8: §6$§e500.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 500000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 500000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 500000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e500.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §4§k.§c§lDANGER§4§k.§7 Spitzhacke");
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§4§k.§c§lDANGER§4§k.§7 Spitzhacke").addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.DURABILITY, 10));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopToolsInventory(player).openGUI();

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
                        "§8└ §f1§8x §4§k.§c§lDANGER§4§k.§7 Spitzhacke",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e500.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(15, new ItemBuilder(Material.DIAMOND_AXE).setName("§8┃ §f§lSHOP §8: §4§k.§c§lDANGER§4§k.§7 Axt").addFlag(ItemFlag.HIDE_ATTRIBUTES).glow(true).lore(
                "§7Effizienz X",
                "§7Haltbarkeit X",
                "§8§oTools",
                "",
                "§8├ §7Preis§8: §6$§e500.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 500000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 500000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 500000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e500.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §4§k.§c§lDANGER§4§k.§7 Axt");
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_AXE).setName("§4§k.§c§lDANGER§4§k.§7 Axt").addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.DURABILITY, 10));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopToolsInventory(player).openGUI();

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
                        "§8└ §f1§8x §4§k.§c§lDANGER§4§k.§7 Axt",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e500.000",
                        "");
                acceptInventory.openGUI();
            } else {
                Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
            }
        });

        setItem(20, new ItemBuilder(Material.DIAMOND_SPADE).setName("§8┃ §f§lSHOP §8: §4§k.§c§lDANGER§4§k.§7 Schaufel").addFlag(ItemFlag.HIDE_ATTRIBUTES).glow(true).lore(
                "§7Effizienz X",
                "§7Haltbarkeit X",
                "§8§oTools",
                "",
                "§8├ §7Preis§8: §6$§e500.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 500000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 500000);
                        userController.setStatisticFromUser(StatisticTypes.BOUGHTITEMSINSHOP, userController.getStatisticByType(StatisticTypes.BOUGHTITEMSINSHOP) + 1);
                        userController.setStatisticFromUser(StatisticTypes.SPENTMONEYINSHOP, userController.getStatisticByType(StatisticTypes.SPENTMONEYINSHOP) + 500000);
                        Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Item erworben§8!");
                        Data.getMessageUtil().sendMessage(player, "§8├ §8-§e500.000 §6Tokens");
                        Data.getMessageUtil().sendMessage(player, "§8└ §f1§8x §4§k.§c§lDANGER§4§k.§7 Schaufel");
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SPADE).setName("§4§k.§c§lDANGER§4§k.§7 Schaufel").addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.DURABILITY, 10));
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        new ShopToolsInventory(player).openGUI();

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
                        "§8└ §f1§8x §4§k.§c§lDANGER§4§k.§7 Schaufel",
                        "",
                        "§8┌ §7Folgender Betrag wird ausgegeben:",
                        "§8└ §6$§e500.000",
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