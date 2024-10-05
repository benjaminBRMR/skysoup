package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.impl.SkullUtil;
import eu.skysoup.skypvp.utils.impl.Utils;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 31.01.2023 20:26
 *
 * @author thvf
 */
public class BountyInventory extends SingletonInventory {

    public BountyInventory(final Player player) {
        super(InventoryTitles.BOUNTY.getName(), Rows.CHEST_ROW_6, player);

        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        final String question = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjcwNWZkOTRhMGM0MzE5MjdmYjRlNjM5YjBmY2ZiNDk3MTdlNDEyMjg1YTAyYjQzOWUwMTEyZGEyMmIyZTJlYyJ9fX0=";

        final String how = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFkYzA0OGE3Y2U3OGY3ZGFkNzJhMDdkYTI3ZDg1YzA5MTY4ODFlNTUyMmVlZWQxZTNkYWYyMTdhMzhjMWEifX19";

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        HashMap<UUID, Long> topBounty = new HashMap<>();
        List<UUID> cached = new ArrayList<>();
        AtomicInteger finalI = new AtomicInteger(29);
        AtomicInteger count = new AtomicInteger(1);


        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
            topBounty.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.BOUNTY));
        }

        for (int e = 29; e < 34; e++) {
            setItem(e, ItemSkull.getSkull("§r", question), null);
        }

        Utils.sortMapByValue(topBounty).forEach((key, value) -> {

            if (finalI.get() == 34) return;
            if (cached.contains(key)) return;

            setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key) + " §8- §8§o#§6§o" + count).lore(
                    "§8§oDieser Spieler hat mehrere Verbrechen",
                    "§8§ogetätigt und gilt als Schwerverbrecher.",
                    "",
                    "§8┌ §7Kopfgeld",
                    "§8└ §2$§a" + NumberFormat.getInstance().format(value),
                    ""
            ), null);


            cached.add(key);
            finalI.getAndIncrement();
            count.getAndIncrement();
        });


        setItem(11, ItemSkull.getSkull("§8┃ §a§lBOUNTY §8: §7Erklärung", how,
                "§8§oWie genau funktioniert ein Bounty?",
                "",
                "§8§oGanz einfach! Mit dem Befehl /bounty set <spieler> <anzahl>",
                "§8§osetzt Du ein Kopfgeld auf einen Spieler.",
                "§8§oFalls der Spieler stirbt, droppt die!",
                "§8§oKopfgeldmarke an seiner Todesposition.",
                ""
        ), null);

        setItem(13, new ItemBuilder(Material.SLIME_BALL).setName("§8┃ §a§lBOUNTY §8: §7Einzahlen").lore(
                "§8§oZahle Kopfgeldmarken ein und erhalte",
                "§8§oden Wert der Marke auf dein Konto.",
                "§8§oBeim Klicken, werden alle Kopfgeldmarken",
                "§8§oaus deinem Inventar eingezahlt, außer",
                "§8§odeine Kopfgeldmarken sind gestackt!",
                "",
                "§8├ §7Klick §8┃ §7Zahle Kopfgeldmarken ein.",
                ""
        ), (click, type) -> {


            if (!Utils.hasPlayerItemInInventory(player, "§8» §a§LKOPF§2§LGELD §7Marke §8(§f$§8)")) {
                Data.getMessageUtil().sendMessage(player, "§cDu hast keine Kopfgeldmarken in deinem Inventar!");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                return;
            }


            for (ItemStack items : player.getInventory().getContents()) {

                if (items == null || items.getType() == Material.AIR) continue;
                if (!items.getItemMeta().hasDisplayName()) continue;


                if (items.getItemMeta().getDisplayName().equals("§8» §a§LKOPF§2§LGELD §7Marke §8(§f$§8)")) {

                    if (items.getItemMeta().getLore().get(5) == null) {
                        Data.getMessageUtil().sendMessage(player, "§cDiese Kopfgeldmarke ist ungültig und wurde entfernt!");
                        player.getInventory().remove(items);
                    }

                    final long value = Long.parseLong(items.getItemMeta().getLore().get(5).replaceAll("§8├ §7Wert§8: §6\\$§e", "").replaceAll(",", "").trim());


                    Data.getMessageUtil().sendMessage(player, "§7Du hast eine §a§LKOPF§2§LGELD §7Marke §7eingezahlt§8.");
                    Data.getMessageUtil().sendMessage(player, "§7Diese hatte einen Wert von §6$§e" + NumberFormat.getInstance().format(value) + " §7Tokens");

                    player.playSound(player.getLocation(), Sound.BURP, 1, 0.1F);
                    userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) + value);

                    Data.getPlayerUtil().removeItems(player.getInventory(), items, 1);
                    return;
                }
            }
        });

        setItem(15, new ItemBuilder(Material.SIGN).setName("§8┃ §a§lBOUNTY §8: §7Übersicht").lore(
                "§8§oHier findest du eine Übersicht",
                "§8§odeiner Kopfgeld Statistiken!",
                "",
                "§8┌ §7Dein aktuelles Bounty§8: §e" + (userController.getBountyController().hasBounty() ? "§6$§e" + NumberFormat.getInstance().format(userController.getBountyController().getBounty()) : "§c§l✘"),
                "§8└ §7Ausg. Tokens für Bountys§8: §6$§e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.SPENTBOUNTYS)),
                ""), null);


    }


    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
