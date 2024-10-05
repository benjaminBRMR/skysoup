package eu.skysoup.skypvp.casino.inventories;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created: 15.02.2023 14:29
 *
 * @author thvf
 */
public class CasinoInventory extends SingletonInventory {

    private final BukkitTask task;

    public CasinoInventory(@NonNull Player player) {
        super(InventoryTitles.CASINOMAIN.getName(), Rows.CHEST_ROW_5, player);


        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        task = new BukkitRunnable() {

            final CasinoController casinoController = new CasinoController().getUser(player);
            final UserController userController = new UserController().getUserByUUID(player.getUniqueId());


            @Override
            public void run() {
                final ItemStack reward = new ItemBuilder((userController.getCooldownController().isOnCooldown("dailycasino") ? Material.MINECART : Material.STORAGE_MINECART)).setName("§8┃ §6§lCAS§e§lINO §8: §7Belohnung").lore(
                        "§8§oKlicke, um deine täglichen",
                        "§8§o1,000 Chips abzuholen.",
                        "",
                        "§8┌ " + (userController.getCooldownController().isOnCooldown("dailycasino") ? "§7Abholbereit in §c" + ValueUtil.timeToString(userController.getCooldownController().getRemainingTime("dailycasino"), false, true) : "§aJetzt Abholbereit!"),
                        "",
                        "§8├ §7Belohnung",
                        "§8└ §6⛃§e1,000 Chips",
                        ""
                );


                final ItemStack statistics = new ItemBuilder(Material.BREWING_STAND_ITEM).setName("§8┃ §6§lCAS§e§lINO §8: §7Übersicht").lore(
                        "§8§oHier findest du eine Übersicht",
                        "§8§odeiner Casino Statistiken!",
                        "",
                        "§8├ §7Casino-Chips§8: §6⛃§e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.CASINOCHIPS)),
                        "§8└ §7Ausg. Casino-Chips§8: §6⛃§e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.SPENTCASINOCHIPS)),
                        ""
                );

                setItem(22, statistics, null);

                setItem(24, reward, (player1, clickType) -> {

                    if (userController.getCooldownController().isOnCooldown("dailycasino")) {
                        Data.getMessageUtil().sendMessage(player, "§cDiese Belohnung kannst du aktuell nicht abholen!");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 101F);
                        return;
                    }


                    userController.getCooldownController().addCooldown("dailycasino", 24, TimeUnit.HOURS);
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 18.4F);
                    Data.getMessageUtil().sendMessage(player, "§7Du hast die §8'§6§lCAS§e§lINO §8: §7Belohnung§8' §7erfolgreich abgeholt§8.");
                    userController.setStatisticFromUser(StatisticTypes.CASINOCHIPS, userController.getStatisticByType(StatisticTypes.CASINOCHIPS) + 1000);
                });
            }
        }.runTaskTimer(SkyPvP.getINSTANCE(), 0L, 20L);


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
        task.cancel();
    }
}
