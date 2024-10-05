package eu.skysoup.skypvp.inventories;

import com.intellectualcrafters.plot.util.WorldUtil;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.TempCooldown;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class MenuInventory extends SingletonInventory {

    private TempCooldown plotdelete = new TempCooldown();
    private TempCooldown plotclear = new TempCooldown();
    private TempCooldown plotaushoehlen = new TempCooldown();

    public MenuInventory(Player player) {
        super(InventoryTitles.MENU.getName(), Rows.CHEST_ROW_5, player);

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        if (plotdelete.isDone(player)) {
            setItem(11, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Grundstück löschen", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VkMWFiYTczZjYzOWY0YmM0MmJkNDgxOTZjNzE1MTk3YmUyNzEyYzNiOTYyYzk3ZWJmOWU5ZWQ4ZWZhMDI1In19fQ==",
                    "§8§oKlicke hier, um dein gesamtes ",
                    "§8§oGrundstück komplett zu löschen!",
                    "",
                    "§8├ §7Preis§8: §6$§e10.000",
                    ""
            ), (click, type) -> {
                if (userController.getStatisticByType(StatisticTypes.MONEY) >= 10000) {
                    final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                        if (clicked) {
                            userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 10000);
                            Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).deletePlot(new Runnable() {
                                @Override
                                public void run() {
                                    Data.getMessageUtil().sendMessage(player, "§aDas Plot wurde erfolgreich gelöscht!");
                                    Data.getMessageUtil().sendMessage(player, "§8└ §8-§e10.000 §6Tokens");
                                }
                            });
                            player.closeInventory();
                            plotdelete.addPlayerToCooldown(player, 180);
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        } else {
                            new MenuInventory(player).openGUI();
                            Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        }
                    },
                            "§8§oKlicke auf einen der beiden Köpfe, um",
                            "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                            "",
                            "§8┌ §7Folgendes wird ausgeführt:",
                            "§8└ §c§nPlot wird gelöscht!",
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
        } else {
            setItem(11, new ItemBuilder(Material.INK_SACK).setDataId(7).setName("§7Bitte warte..").lore(
                    "§8§oBitte öffne das",
                    "§8§oInventar erneut!"
            ), null);
        }

        if (plotclear.isDone(player)) {
            setItem(12, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Grundstück leeren", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VkMWFiYTczZjYzOWY0YmM0MmJkNDgxOTZjNzE1MTk3YmUyNzEyYzNiOTYyYzk3ZWJmOWU5ZWQ4ZWZhMDI1In19fQ==",
                    "§8§oKlicke hier, um dein Grundstück",
                    "§8§ozu komplett zu leeren!",
                    "",
                    "§8├ §7Preis§8: §6$§e2.500",
                    ""
            ), (click, type) -> {
                if (userController.getStatisticByType(StatisticTypes.MONEY) >= 2500) {
                    final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                        if (clicked) {
                            userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 2500);
                            Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).clear(new Runnable() {
                                @Override
                                public void run() {
                                    Data.getMessageUtil().sendMessage(player, "§aDas Plot wurde erfolgreich geleert!");
                                    Data.getMessageUtil().sendMessage(player, "§8└ §8-§e2.500 §6Tokens");
                                }
                            });
                            plotclear.addPlayerToCooldown(player, 180);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        } else {
                            new MenuInventory(player).openGUI();
                            Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        }
                    },
                            "§8§oKlicke auf einen der beiden Köpfe, um",
                            "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                            "",
                            "§8┌ §7Folgendes wird ausgeführt:",
                            "§8└ §c§nPlot wird geleert!",
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
        } else {
            setItem(12, new ItemBuilder(Material.INK_SACK).setDataId(7).setName("§7Bitte warte..").lore(
                    "§8§oBitte öffne das",
                    "§8§oInventar erneut!"
            ), null);
        }

        if (plotaushoehlen.isDone(player)) {
            setItem(13, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Grundstück aushöhlen", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTgxODUyOTQzZTM2YzhhM2YxZTNkMGU0OTEyNTQ5Y2JjMjA1ZDk0NzM5NGFkYmU2NWY0ZDgxZDYxMWJlMmM4NyJ9fX0=",
                    "§8§oKlicke hier, um dein Grundstück",
                    "§8§okomplett zu leeren!",
                    "§8§oAlles unter der ersten",
                    "§8§oSchicht wird gelöscht!",
                    "",
                    "§8├ §7Preis§8: §6$§e30.000",
                    ""
            ), (click, type) -> {
                if (userController.getStatisticByType(StatisticTypes.MONEY) >= 30000) {
                    final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                        if (clicked) {
                            userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 30000);
                            Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).setComponent("main", String.valueOf(Material.AIR));
                            Data.getMessageUtil().sendMessage(player, "§aDas Plot wurde erfolgreich ausgehöhlt!");
                            Data.getMessageUtil().sendMessage(player, "§8└ §8-§e30.000 §6Tokens");
                            plotaushoehlen.addPlayerToCooldown(player, 360);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        } else {
                            new MenuInventory(player).openGUI();
                            Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        }
                    },
                            "§8§oKlicke auf einen der beiden Köpfe, um",
                            "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                            "",
                            "§8┌ §7Folgendes wird ausgeführt:",
                            "§8└ §c§nPlot wird ausgehöhlt!",
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
        } else {
            setItem(13, new ItemBuilder(Material.INK_SACK).setDataId(7).setName("§7Bitte warte..").lore(
                    "§8§oBitte öffne das",
                    "§8§oInventar erneut!"
            ), null);
        }

        setItem(14, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Freunde §8& §7Helfer entfernen", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZjYmFlNzI0NmNjMmM2ZTg4ODU4NzE5OGM3OTU5OTc5NjY2YjRmNWE0MDg4ZjI0ZTI2ZTA3NWYxNDBhZTZjMyJ9fX0=",
                "§8§oKlicke hier, um alle deine ",
                "§8§oFreunde und Helfer von deinem",
                "§8§oGrundstück zu entfernen!",
                "",
                "§8├ §7Preis§8: §cGratis",
                ""
        ), (click, type) -> {
            final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                if (clicked) {
                    Data.getMessageUtil().sendMessage(player, "§aFreunde §8& §aHelfer wurden erfolgreich entfernt!");


                    final List<UUID> tempTrusted = new LinkedList<>(Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).getTrusted());

                    tempTrusted.forEach(all -> {
                        Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).removeTrusted(all);
                    });

                    final List<UUID> tempMember = new LinkedList<>(Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).getMembers());

                    tempMember.forEach(all -> {
                        Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).removeMember(all);
                    });

                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                    player.closeInventory();
                } else {
                    new MenuInventory(player).openGUI();
                    Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                }
            },
                    "§8§oKlicke auf einen der beiden Köpfe, um",
                    "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                    "",
                    "§8┌ §7Folgendes wird ausgeführt:",
                    "§8└ §cFreunde §8& §cHelfer werden entfernt!",
                    "",
                    "§8┌ §7Folgender Betrag wird ausgegeben:",
                    "§8└ §cKeiner",
                    "");
            acceptInventory.openGUI();
        });

        setItem(15, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Gebannte Spieler entbannen", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U5Yzc3MTkxYjIzODM5OTkzOGY1ZDNhMzYwNThhNmQxM2ZmZWI0M2FiMjY2OWI5MmFlN2ZiODE4OWI2NDZlNiJ9fX0=",
                "§8§oKlicke hier, um alle",
                "§8§odeine vom Plot gebannten",
                "§8§oSpieler zu entbannen!",
                "",
                "§8├ §7Preis§8: §cGratis",
                ""
        ), (click, type) -> {
            final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                if (clicked) {
                    Data.getMessageUtil().sendMessage(player, "§aAlle gebannten Spieler wurden erfolgreich entbannt!");

                    final List<UUID> tempDenied = new LinkedList<>(Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).getDenied());

                    tempDenied.forEach(all -> {
                        Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).removeDenied(all);
                    });

                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                    player.closeInventory();
                } else {
                    new MenuInventory(player).openGUI();
                    Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                }
            },
                    "§8§oKlicke auf einen der beiden Köpfe, um",
                    "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                    "",
                    "§8┌ §7Folgendes wird ausgeführt:",
                    "§8└ §cAlle Spieler werden entbannt!",
                    "",
                    "§8┌ §7Folgender Betrag wird ausgegeben:",
                    "§8└ §cKeiner",
                    "");
            acceptInventory.openGUI();
        });

        setItem(20, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Zufälliges Biom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWExNGM3ZTEzNjVlMDNiZDhjNDg1ODY5OTg3Yzc5ODJmYWJmNDAwNDk4MmM2ODk4MjA2NWFlYmM3ZTU0ZmVjMSJ9fX0=",
                "§8§oKlicke hier, um ein",
                "§8§ozufälliges Biom",
                "§8§ozu erwerben.",
                "",
                "§8├ §7Preis§8: §6$§e15.000",
                ""
        ), (click, type) -> {
            if (userController.getStatisticByType(StatisticTypes.MONEY) >= 15000) {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        String randomBiom = WorldUtil.IMP.getBiomeList()[Data.getRandom().nextInt(WorldUtil.IMP.getBiomeList().length)];
                        userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - 15000);
                        Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).setBiome(randomBiom, () -> {
                            Data.getMessageUtil().sendMessage(player, "§aDu hast folgendes Biom bekommen§8:");
                            Data.getMessageUtil().sendMessage(player, "§aBiom§8: §a§l" + Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).getBiome());
                        });
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                        player.closeInventory();
                    } else {
                        new MenuInventory(player).openGUI();
                        Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                    }
                },
                        "§8§oKlicke auf einen der beiden Köpfe, um",
                        "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                        "",
                        "§8┌ §7Folgendes wird ausgeführt:",
                        "§8└ §7Zufälliges Biom",
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

        setItem(33, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Wand ändern", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFmY2Y0NDNiNWI1MWI3MzUyNjg4OTY0ZGE0NmZkYjMzMGQwNTdjNDAzZGM4ZDJjNjdlM2M3MTY5YWRjNjM0NyJ9fX0=",
                "§8§oKlicke hier, um deiner",
                "§8§oWand einen wundervollen und",
                "§8§obesonderen Look zu geben!",
                ""
        ), (click, type) -> {
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
            new WandInventory(player).openGUI();
        });

        setItem(32, ItemSkull.getSkull("§8┃ §e§lMENU §8: §7Rand ändern", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZhZjAyNGVhZWIxOWYxOTYzMjdhYjc3Y2Y4MTcxZTA2MzlkOTM5ODMwM2ZkOTQ4MmIzMDNkOWQwMjU0MWU1YyJ9fX0=",
                "§8§oKlicke hier, um deinen",
                "§8§oRand einen wundervollen und",
                "§8§obesonderen Look zu geben!",
                ""
        ), (click, type) -> {
            /*
            if(Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).isMerged()) {
                Data.getMessageUtil().sendMessage(player, "§cDu kannst deinen Rand nur auf ein einzelnes Plot ändern.");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                return;
            }

             */
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
            new RandInventory(player).openGUI();
        });
    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
