package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 10.02.2023 12:26
 *
 * @author thvf
 */
public class RewardInventory extends SingletonInventory {

    public RewardInventory(@NonNull Player player) {
        super(InventoryTitles.REWARD.getName(), Rows.CHEST_ROW_6, player);


        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        final String question = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjcwNWZkOTRhMGM0MzE5MjdmYjRlNjM5YjBmY2ZiNDk3MTdlNDEyMjg1YTAyYjQzOWUwMTEyZGEyMmIyZTJlYyJ9fX0=";

        final AtomicInteger atomicInteger2 = new AtomicInteger(10);
        final int dayOfWeek = LocalDate.now().getDayOfMonth();


        setItem(4, new ItemBuilder(Material.SIGN).setName("§8┃ §5§lREWARD §8: §7Erklärung").lore(
                "§8§oHole dir täglich den ganzen Monat"
                , "§8§olang spannende Belohnungen ab!",
                "§8§oVerpasst du einen Tag, so ist die",
                "§8§oBelohnung nicht mehr abholbereit!"
        ), null);


        for (int i = 1; i < getInventory().getSize(); i++) {
            if (atomicInteger2.get() == 17) atomicInteger2.set(19);
            if (atomicInteger2.get() == 26) atomicInteger2.set(28);
            if (atomicInteger2.get() == 35) atomicInteger2.set(37);
            if (atomicInteger2.get() == 44) atomicInteger2.set(48);
            if (atomicInteger2.get() > 50) return;

            if (Data.getRewardController().isItemListedByDay(i)) {
                int finalI = i;
                setItem(atomicInteger2.getAndIncrement(), getItemStackOfDay(player, i, Data.getRewardController().getItemStackByDay(i)), (player1, clickType) -> {

                    if (dayOfWeek == finalI) {

                        if (!Data.getRewardController().getStatusFromPlayer(player, finalI)) {
                            Data.getMessageUtil().sendMessage(player, "§7Du hast deine Belohnung §aerfolgreich §7abgeholt§8.");
                            Data.getMessageUtil().sendMessage(player, "§8├ §7Item§8: §e" + (Data.getRewardController().getItemStackByDay(finalI).getItemMeta().hasDisplayName() ? Data.getRewardController().getItemStackByDay(finalI).getItemMeta().getDisplayName() : Data.getRewardController().getItemStackByDay(finalI).getType().name().toUpperCase().replaceAll("_", "")));
                            Data.getMessageUtil().sendMessage(player, "§8└ §7Anzahl§8: §e" + Data.getRewardController().getItemStackByDay(finalI).getAmount());

                            Data.getPlayerUtil().addItem(player, Data.getRewardController().getItemStackByDay(finalI));
                            player.closeInventory();

                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 20, 0.1F);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.CREEPER_DEATH, 20, 0.1F);

                                }
                            }.runTaskLater(SkyPvP.getINSTANCE(), 5L);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.CREEPER_DEATH, 20, 0.1F);

                                }
                            }.runTaskLater(SkyPvP.getINSTANCE(), 6L);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 20, 0.1F);

                                }
                            }.runTaskLater(SkyPvP.getINSTANCE(), 10L);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 20, 13);

                                }
                            }.runTaskLater(SkyPvP.getINSTANCE(), 15L);

                            Data.getRewardController().setStatusFromPlayer(player, finalI, true);
                        } else {
                            Data.getMessageUtil().sendMessage(player, "§cDu hast dieses Belohnung bereits abgeholt!");
                            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        }

                    } else {
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                    }
                });
            } else {

                setItem(atomicInteger2.getAndIncrement(), ItemSkull.getSkull("§8┃ §5§lREWARD §8: §cNichts", question, "§8§oDieses Item wurde noch", "§8§onicht gesetzt."), null);
            }
        }

    }

    private ItemStack getItemStackOfDay(final Player player, final int slot, final ItemStack standard) {
        final int dayOfWeek = LocalDate.now().getDayOfMonth();

        final String abgelaufen = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA2YTNkZTJiMWI5NWY0MmZmZDQ0ODAxOGQxY2MxMWRjNzYzZjlkNWM3MjJjYzFiNmNjM2ZjZmQ0ZjA5MDg5ZSJ9fX0=";

        final String demnaechst = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThiYmE4ODFkYWViODcyYTY4MWZmMGExMzE1M2Y1MTlmYjJkZGRkMDBjYmU2N2EzMzYwMTIyMDQ0YTAyNjVkZSJ9fX0=";

        if (dayOfWeek > slot) {

            final ItemBuilder bereitsabgeholt = new ItemBuilder(standard).setName("§8┃ §5§lREWARD §8: §cBereits abgeholt §8(§7Tag §f" + slot + "§8)").lore(
                    "§8§oDieses Item hast du schon",
                    "§8§obereits erfolgreich abgeholt.",
                    "",
                    "§8┌ §7Belohnung",
                    "§8├ §7Item§8: §d" + (standard.getItemMeta().hasDisplayName() ? standard.getItemMeta().getDisplayName() : standard.getType().name().toUpperCase().replaceAll("_", " ")),

                    "§8└ §7Anzahl§8: §d" + standard.getAmount() + "§8x",
                    "",
                    "§8├ §7Status§8: §c§lBEREITS ABGEHOLT",
                    ""
            ).addFlag(ItemFlag.HIDE_ATTRIBUTES).amount(-1);

            if (Data.getRewardController().getStatusFromPlayer(player, slot)) {
                return bereitsabgeholt;
            }

            return ItemSkull.getSkull("§8┃ §5§lREWARD §8: §cAbgelaufen §8(§7Tag §f" + slot + "§8)", abgelaufen, "§8§oDiese Belohnung hast", "§8§oDu leider verpasst!");
        }
        if (dayOfWeek < slot) {
            return ItemSkull.getSkull("§8┃ §5§lREWARD §8: §cDemnächst §8(§7Tag §f" + slot + "§8)", demnaechst, "§8§oDiese Belohnung kannst", "§8§oDu bald schon abholen.");
        }

        final ItemBuilder abholbereit = new ItemBuilder(standard).setName("§8┃ §5§lREWARD §8: §aAbholbereit §8(§7Tag §f" + slot + "§8)").lore(
                "§8§oDieses Item kannst du",
                "§8§oheute gratis abholen.",
                "",
                "§8┌ §7Belohnung",
                "§8├ §7Item§8: §d" + (standard.getItemMeta().hasDisplayName() ? standard.getItemMeta().getDisplayName() : standard.getType().name().toUpperCase().replaceAll("_", " ")),

                "§8└ §7Anzahl§8: §d" + standard.getAmount() + "§8x",
                "",
                "§8├ §7Status§8: §a§lABHOLBEREIT",
                ""
        ).glow(true).addFlag(ItemFlag.HIDE_ATTRIBUTES);

        final ItemBuilder abgeholt = new ItemBuilder(Material.BARRIER).setName("§8┃ §5§lREWARD §8: §cAbgeholt §8(§7Tag §f" + slot + "§8)").lore(
                "§8§oDieses Item kannst du",
                "§8§oheute gratis abholen.",
                "",
                "§8┌ §7Belohnung",
                "§8├ §7Item§8: §d" + (standard.getItemMeta().hasDisplayName() ? standard.getItemMeta().getDisplayName() : standard.getType().name().toUpperCase().replaceAll("_", " ")),
                "§8└ §7Anzahl§8: §d" + standard.getAmount() + "§8x",
                "",
                "§8├ §7Status§8: §c§lBEREITS ABGEHOLT",
                ""
        ).glow(true).addFlag(ItemFlag.HIDE_ATTRIBUTES);


        if (!Data.getRewardController().getStatusFromPlayer(player, slot)) {
            return abholbereit;
        }
        return abgeholt;


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
    }
}
