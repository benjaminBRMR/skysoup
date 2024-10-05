package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.impl.SkullUtil;
import eu.skysoup.skypvp.utils.impl.Utils;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 27.01.2023 17:54
 *
 * @author thvf
 */
public class TopInventory extends SingletonInventory {


    public TopInventory(final Player player) {
        super(InventoryTitles.TOP.getName(), Rows.CHEST_ROW_6, player);

        String topMoney = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5Mjk5YTExN2JlZTg4ZDMyNjJmNmFiOTgyMTFmYmEzNDRlY2FlMzliNDdlYzg0ODEyOTcwNmRlZGM4MWU0ZiJ9fX0=";

        String topVotes = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEzODM1MmY0NzQ1ZTAyYzA5MzkxNDZkYmQzNjZlNjUzNWE3ZjRlZjM5NjUzMDA5YjVjMzljMjRiOTRkNGNhNyJ9fX0=";

        String topKills = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEzMDRmYmIxMzg0MTlmYTk5ZDU2NjhlMzg4NjYxMmVmNzA3ZjVhN2MxODY4ZWE3YmJiNjQ1NDA3NDY4NTgyNCJ9fX0=\n";

        String topPlaytime = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMyYzYxNzE1MzJhMmE4N2YwZWViMjhlZGQwMTA4MzNmMzNmMGFlNjg0MWE1MjRlMWI1MjAwYTM1ZDM4NTA1MCJ9fX0=";

        String question = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjcwNWZkOTRhMGM0MzE5MjdmYjRlNjM5YjBmY2ZiNDk3MTdlNDEyMjg1YTAyYjQzOWUwMTEyZGEyMmIyZTJlYyJ9fX0=";


        String topKilltreak = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM5YjVkMmU2MjRjMTQ3ZWZlNDdiMTMwZTFjZGRiOGEzNzdjZGIzZTc3NDJiZDJjZjRkZGRmNjlhNjg5MjcyIn19fQ==\n";

        String topTrophys = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM0YTU5MmE3OTM5N2E4ZGYzOTk3YzQzMDkxNjk0ZmMyZmI3NmM4ODNhNzZjY2U4OWYwMjI3ZTVjOWYxZGZlIn19fQ==";

        String topFishingLevel = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzc0MGZhMWZhOTA5MmUzNjhlNjc5OGY5YmNmZThjODMxOGUwMzk5NmEyMDJiZWFlOTE5ZTVjYjkwMDAzOWQ0YyJ9fX0=";

        String topCasinoChips = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2YmM2ODU0N2M3NmJhNDYyNTA4NTAyOTgwNDY0N2I4MmY4MTY3MDVjOGJiNjFlMzdkMTQ4NWE3NWM3MmM1ZiJ9fX0=";

        String allMoney = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUzZjUwYmE2MmNiZGEzZWNmNTQ3OWI2MmZlZGViZDYxZDc2NTg5NzcxY2MxOTI4NmJmMjc0NWNkNzFlNDdjNiJ9fX0=";


        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        for (int e = 20; e < 25; e++) {
            setItem(e, ItemSkull.getSkull("§cKlicke auf eine Kategorie...", question), null);
        }
        for (int e = 29; e < 34; e++) {
            setItem(e, ItemSkull.getSkull("§cKlicke auf eine Kategorie...", question), null);
        }


        setItem(9, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Money", topMoney, "§8§oSiehe ein, wer der reichste Spieler,", "§8§omomentan auf dem Server ist."), (click, type) -> {
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);

            HashMap<UUID, Long> moneyMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
                moneyMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.MONEY));
            }

            long money = 0;
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                final UserController userController = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
                money = money + userController.getStatisticByType(StatisticTypes.MONEY);
            }

            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            setItem(13, ItemSkull.getSkull("§8┃ §e§lGELD IM UMLAUF", allMoney, "§8§oAktuell sind §7§o" + ValueUtil.format(money) + " §8§oTokens im Umlauf."), null);

            Utils.sortMapByValue(moneyMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;


                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count.get()).lore("§8§oDieser Spieler hat", "§8§ozurzeit §7§o" + ValueUtil.format(value) + " §8§oTokens."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });

        });

        setItem(18, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Votes", topVotes, "§8§oSiehe ein, wer die meisten Votes,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);

            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);


            HashMap<UUID, Long> votesMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
                votesMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.VOTES));
            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);

            Utils.sortMapByValue(votesMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;

                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count.get()).lore("§8§oDieser Spieler hat", "§8§ozurzeit §7§o" + NumberFormat.getInstance().format(value) + " §8§oVotes."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });

        });

        setItem(27, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Kills", topKills, "§8§oSiehe ein, wer die meisten Kills,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);

            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);


            HashMap<UUID, Long> killsMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
                killsMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.KILLS));
            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            Utils.sortMapByValue(killsMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;


                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count.get()).lore("§8§oDieser Spieler hat", "§8§ozurzeit §7§o" + NumberFormat.getInstance().format(value) + " §8§oKills."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });

        });

        setItem(36, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Spielzeit", topPlaytime, "§8§oSiehe ein, wer die meiste Spielzeit,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);


            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);


            HashMap<UUID, Long> playtimeMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

                // UUIDS von htx1 und sprachlvs filtern

                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());

                final Duration ONE_TICK = Duration.ofSeconds(1L).dividedBy(20L);
                final int ticks = offlinePlayer.isOnline() ? Bukkit.getPlayer(offlinePlayer.getUniqueId()).getStatistic(Statistic.PLAY_ONE_TICK) : (int) us.getStatisticByType(StatisticTypes.PLAYTIME);
                Duration plpTime = ONE_TICK.multipliedBy(ticks);

                playtimeMap.put(offlinePlayer.getUniqueId(), plpTime.toMillis());

            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            Utils.sortMapByValue(playtimeMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;

                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count.get()).lore("§8§oDieser Spieler hat eine Spielzeit", "§8§ovon §7§o" + ValueUtil.timeToString(value, false, false) + "§8§o."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });

        });

        setItem(17, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Killstreak", topKilltreak, "§8§oSiehe ein, wer die höchste Killstreak,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);


            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);


            HashMap<UUID, Long> killstreakMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());

                killstreakMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.KILLSTREAK));

            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            Utils.sortMapByValue(killstreakMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;

                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count).lore("§8§oDieser Spieler hat", "§8§oeine Killstreak von §7§o" + NumberFormat.getInstance().format(value) + "§8§o."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });

        });

        setItem(26, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Trophäen", topTrophys, "§8§oSiehe ein, wer die meisten Trophäen,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);

            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);


            HashMap<UUID, Long> trophyMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());

                trophyMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.TROPHYS));

            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            Utils.sortMapByValue(trophyMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;


                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count).lore("§8§oDieser Spieler hat", "§8§ozurzeit §7§o" + NumberFormat.getInstance().format(value) + " §8§oTrophäen."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });
        });

        setItem(35, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Fishing-Level", topFishingLevel, "§8§oSiehe ein, wer das höchste Fishing-Level,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);


            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);

            HashMap<UUID, Long> fishingLvlMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());

                fishingLvlMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.FISHINGLEVEL));

            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            Utils.sortMapByValue(fishingLvlMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;


                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count).lore("§8§oDieser Spieler hat ein", "§8§oFishing-Level von §7§o" + NumberFormat.getInstance().format(value) + "§8§o."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });

        });

        setItem(44, ItemSkull.getSkull("§8┃ §e§lTOP §8: §7Casinochips", topCasinoChips, "§8§oSiehe ein, wer die meisten Casinochips,", "§8§omomentan auf dem Server hat."), (click, type) -> {

            setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7), null);


            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);

            HashMap<UUID, Long> casinoChipsMap = new HashMap<>();
            List<UUID> cached = new ArrayList<>();


            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {

                final UserController us = new UserController().getUserByUUID(offlinePlayer.getUniqueId());

                casinoChipsMap.put(offlinePlayer.getUniqueId(), us.getStatisticByType(StatisticTypes.CASINOCHIPS));

            }
            for (int e = 20; e < 25; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }
            for (int e = 29; e < 34; e++) {
                setItem(e, ItemSkull.getSkull("§r", question), null);
            }

            AtomicInteger finalI = new AtomicInteger(20);
            AtomicInteger count = new AtomicInteger(1);


            Utils.sortMapByValue(casinoChipsMap).forEach((key, value) -> {

                if (finalI.get() == 25) finalI.set(29);
                if (finalI.get() == 34) return;
                if (cached.contains(key)) return;


                setItem(finalI.get(), new ItemBuilder(SkullUtil.getCachedSkull(key)).setName("§e§l" + Data.getUuidFetcher().getName(key).toLowerCase() + " §8- §8§o#§6§o" + count).lore("§8§oDieser Spieler hat", "§8§ozurzeit §7§o" + ValueUtil.format(value) + " §8§oCasinochips."), null);


                cached.add(key);
                finalI.getAndIncrement();
                count.getAndIncrement();
            });
        });


    }


    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}


