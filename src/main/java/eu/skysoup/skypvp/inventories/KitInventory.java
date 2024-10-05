package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.impl.StringUtil;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 06.02.2023 01:04
 *
 * @author thvf
 */
public class KitInventory extends SingletonInventory {

    private final BukkitTask task;
    private BukkitTask task2;

    public KitInventory(final Player player) {
        super(InventoryTitles.KIT.getName(), Rows.CHEST_ROW_6, player);

        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        final String arrowbase64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19";
        final ItemStack nextPage = ItemSkull.getSkull("§8┃ §d§lKIT §8: §7Alle", arrowbase64, "§8§oKlicke, um eine Übersicht,", "§8§oüber alle erstellten Kits einzusehen.");


        setItem(43, nextPage, (player1, clickType) -> {

            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);

            getInventoryItems().clear();

            task2 = new BukkitRunnable() {
                @Override
                public void run() {

                    task.cancel();


                    fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
                    fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
                    fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

                    setItem(45, ItemSkull.getSkull("§8┃ §d§lKIT §8: §7Zurück", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==",
                            "§8§oKlicke, um zum",
                            "§8§oHauptmenü zu gelangen."
                    ), (click, type) -> {
                        new KitInventory(player).openGUI();
                        player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
                    });


                    AtomicInteger atomicInteger = new AtomicInteger(10);
                    for (String kits : Data.getKitController().getListedKits()) {

                        if (kits.equals("spieler") || kits.equals("soup") || kits.equals("superia") || kits.equals("onyx") || kits.equals("saphir") || kits.equals("azur"))
                            continue;

                        if (atomicInteger.get() == 16 || atomicInteger.get() == 17) atomicInteger.set(20);
                        if (atomicInteger.get() == 25 || atomicInteger.get() == 26) atomicInteger.set(29);
                        if (atomicInteger.get() == 34 || atomicInteger.get() == 35) atomicInteger.set(38);
                        if (atomicInteger.get() >= 43) return;


                        final ItemStack kitItem = new ItemBuilder(Material.LEATHER_HELMET).setName("§8┃ §f" + StringUtil.capitalize(kits) + " §8: §7Kit").lore(
                                "§8§oKlicke, um dieses Kit abzuholen!",
                                "",
                                "§8┌ §7" + (player.hasPermission(Data.getKitController().getPermission(kits)) ? (userController.getCooldownController().isOnCooldown(kits.toLowerCase()) ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime(kits
                                ), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                                "§8├ §7Berechtigung§8: §c" + Data.getKitController().getPermission(kits),
                                ""
                        );


                        setItem(atomicInteger.getAndIncrement(), kitItem, (click, type) -> {

                            final String permission = kitItem.getItemMeta().getLore().get(3).replaceAll("§8├ §7Berechtigung§8: §c", "");
                            final String kitName = kitItem.getItemMeta().getDisplayName().replaceAll("§8┃ §f", "").replaceAll(" §8: §7Kit", "").trim().toLowerCase();

                            if (!Data.getPermissionUtil().hasPermission(player, permission, true)) return;


                            if (!Data.getKitController().kitExist(kitName)) {
                                Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                                return;
                            }

                            if (userController.getCooldownController().isOnCooldown(kitName)) {


                                Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                                return;
                            }

                            Data.getKitController().getItems(kitName).forEach(all -> Data.getPlayerUtil().addItem(player, all));
                            player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                            Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§f" + kitName + " §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                            userController.getCooldownController().addCooldown(kitName, Data.getKitController().getCooldown(kitName), Data.getKitController().getTimeUnit(kitName));
                        });

                    }
                }
            }.runTaskTimer(SkyPvP.getINSTANCE(), 0L, 20L);


        });

        task = new BukkitRunnable() {
            @Override
            public void run() {


                final ItemBuilder soupKit = new ItemBuilder(Material.DIAMOND_HELMET).setName("§8┃ §6§lSoup §8: §7Kit §8§o#§f1").lore(
                        "§8§oKlicke, um dieses Kit abzuholen!",
                        "",
                        "§8┌ §7" + (player.hasPermission(Permissions.SOUP.getPermission()) ? (userController.getCooldownController().isOnCooldown("soup") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("soup"), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                        ""
                );


                final ItemStack superiaKit = new ItemBuilder(Material.IRON_HELMET).setName("§8┃ §dSuperia §8: §7Kit §8§o#§f2").lore(
                        "§8§oKlicke, um dieses Kit abzuholen!",
                        "",
                        "§8┌ §7" + (player.hasPermission(Permissions.SUPERIA.getPermission()) ? (userController.getCooldownController().isOnCooldown("superia") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("onyx"), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                        ""
                );
                final ItemStack onyxKit = new ItemBuilder(Material.GOLD_HELMET).setName("§8┃ §bOnyx §8: §7Kit §8§o#§f3").lore(
                        "§8§oKlicke, um dieses Kit abzuholen!",
                        "",
                        "§8┌ §7" + (player.hasPermission(Permissions.ONYX.getPermission()) ? (userController.getCooldownController().isOnCooldown("onyx") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("onyx"), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                        ""
                );
                final ItemStack saphir = new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§8┃ §6Saphir §8: §7Kit §8§o#§f4").lore(
                        "§8§oKlicke, um dieses Kit abzuholen!",
                        "",
                        "§8┌ §7" + (player.hasPermission(Permissions.SAPHIR.getPermission()) ? (userController.getCooldownController().isOnCooldown("saphir") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("saphir"), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                        ""
                );
                final ItemStack azur = new ItemBuilder(Material.LEATHER_HELMET).setName("§8┃ §5Azur §8: §7Kit §8§o#§f5").lore(
                        "§8§oKlicke, um dieses Kit abzuholen!",
                        "",
                        "§8┌ §7" + (player.hasPermission(Permissions.AZUR.getPermission()) ? (userController.getCooldownController().isOnCooldown("azur") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("azur"), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                        ""
                );

                final ItemStack spielerKit = new ItemBuilder(Material.BOW).setName("§8┃ §7Spieler §8: §7Kit §8§o#§f6").lore(
                        "§8§oKlicke, um dieses Kit abzuholen!",
                        "",
                        "§8┌ §7" + (player.hasPermission(Permissions.DEFAULT.getPermission()) ? (userController.getCooldownController().isOnCooldown("spieler") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("spieler"), false, true) : "§aJetzt abholbereit!") : "§cDu hast keine Rechte dieses Kit abzuholen"),
                        ""
                );

                setItem(13, spielerKit, (click, type) -> {

                    if (type == ClickType.RIGHT) {
                        // VORSCHAU
                        return;
                    }

                    if (!Data.getKitController().kitExist("spieler")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }


                    if (userController.getCooldownController().isOnCooldown("spieler")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }

                    Data.getKitController().getItems("spieler").forEach(all -> Data.getPlayerUtil().addItem(player, all));
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§aSpieler §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                    userController.getCooldownController().addCooldown("spieler", Data.getKitController().getCooldown("spieler"), Data.getKitController().getTimeUnit("spieler"));


                });

                setItem(20, soupKit, (click, type) -> {

                    if (!Data.getPermissionUtil().hasPermission(player, Permissions.SOUP.getPermission(), true)) return;

                    if (!Data.getKitController().kitExist("soup")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }

                    if (userController.getCooldownController().isOnCooldown("soup")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }

                    Data.getKitController().getItems("soup").forEach(all -> Data.getPlayerUtil().addItem(player, all));
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§6Soup §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                    userController.getCooldownController().addCooldown("soup", Data.getKitController().getCooldown("soup"), Data.getKitController().getTimeUnit("soup"));

                });
                setItem(21, superiaKit, (click, type) -> {

                    if (!Data.getPermissionUtil().hasPermission(player, Permissions.SUPERIA.getPermission(), true))
                        return;


                    if (!Data.getKitController().kitExist("superia")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }

                    if (userController.getCooldownController().isOnCooldown("superia")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }

                    Data.getKitController().getItems("superia").forEach(all -> Data.getPlayerUtil().addItem(player, all));
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§dSuperia §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                    userController.getCooldownController().addCooldown("superia", Data.getKitController().getCooldown("superia"), Data.getKitController().getTimeUnit("superia"));
                });

                setItem(31, onyxKit, (click, type) -> {

                    if (!Data.getPermissionUtil().hasPermission(player, Permissions.ONYX.getPermission(), true)) return;


                    if (!Data.getKitController().kitExist("onxy")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }

                    if (userController.getCooldownController().isOnCooldown("onyx")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }

                    Data.getKitController().getItems("onyx").forEach(all -> Data.getPlayerUtil().addItem(player, all));
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§bOnxy §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                    userController.getCooldownController().addCooldown("onyx", Data.getKitController().getCooldown("onyx"), Data.getKitController().getTimeUnit("onyx"));
                });

                setItem(23, saphir, (click, type) -> {

                    if (!Data.getPermissionUtil().hasPermission(player, Permissions.SAPHIR.getPermission(), true))
                        return;


                    if (!Data.getKitController().kitExist("saphir")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }

                    if (userController.getCooldownController().isOnCooldown("saphir")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }

                    Data.getKitController().getItems("saphir").forEach(all -> Data.getPlayerUtil().addItem(player, all));
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§6Saphir §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                    userController.getCooldownController().addCooldown("saphir", Data.getKitController().getCooldown("saphir"), Data.getKitController().getTimeUnit("saphir"));
                });
                setItem(24, azur, (click, type) -> {

                    if (!Data.getPermissionUtil().hasPermission(player, Permissions.AZUR.getPermission(), true)) return;


                    if (!Data.getKitController().kitExist("azur")) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit wurde nicht gefunden!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }

                    if (userController.getCooldownController().isOnCooldown("azur")) {


                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }

                    Data.getKitController().getItems("azur").forEach(all -> Data.getPlayerUtil().addItem(player, all));
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast das §8'§5Azur §8: §7Kit§8' §7erfolgreich abgeholt§8.");
                    userController.getCooldownController().addCooldown("azur", Data.getKitController().getCooldown("azur"), Data.getKitController().getTimeUnit("azur"));
                });

            }
        }.runTaskTimer(SkyPvP.getINSTANCE(), 0L, 20L);


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
        if (task != null) task.cancel();
        if (task2 != null) task2.cancel();
    }
}
