package eu.skysoup.skypvp.moneymaker.inventories;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.moneymaker.MoneyMakerController;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
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

/**
 * Created: 26.02.2023 22:34
 *
 * @author thvf
 */
public class MoneyMakerInventory extends SingletonInventory {

    private final BukkitTask task;


    public MoneyMakerInventory(@NonNull Player player) {
        super(InventoryTitles.MONEYMAKERBURN.getName(), Rows.CHEST_ROW_6, player);

        final MoneyMakerController moneyMakerController = new MoneyMakerController().getUserbyUUID(player.getUniqueId());
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        final String autoMiner64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdkODkwMTk1ZjU1ZDEwY2VlOTY0ODZhYjc2NjI0M2E3MmFjM2EwM2QwNDQ1ZTFiMDNjMjlmZWIxZjBlNTJkNSJ9fX0=";

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        task = new BukkitRunnable() {
            @Override
            public void run() {


                final ItemStack autoMiner = ItemSkull.getSkull("§8┃ §e§lMoneyMaker §8: §7Arbeiter", autoMiner64,
                        "§8§oKlicke, um Arbeiter einzustellen.",
                        "",
                        "§8├ §7Klicke§8, §7um die Arbeiterübersicht abzurufen.",
                        ""
                );

                final ItemBuilder stats = new ItemBuilder(Material.SIGN)
                        .setName("§8┃ §e§lMoneyMaker §8: §7Übersicht")
                        .lore(
                                "§8§oHier findest du eine Übersicht",
                                "§8§odeiner MoneyMaker Statistiken!",
                                "",
                                "§8┌ §7Dein Gold§8: §6" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.GOLD)),
                                "§8├ §7Dein Rohgold§8: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.ROHGOLD)),
                                "§8└ §7Deine Arbeiter§8: §e" + (moneyMakerController.hasArbeiter() ? "1 §8(§7Lvl§8: §e" + moneyMakerController.getArbeiterLevel() + "§8)" : "0") + "§7/§61",
                                ""
                        );

                final ItemBuilder prozessBegin = new ItemBuilder(moneyMakerController.isInVorgang() ? Material.MINECART : Material.STORAGE_MINECART)
                        .setName("§8┃ §e§lMoneyMaker §8: §7Schmelzen")
                        .lore(
                                "§8§oKlicke, um §7§o15 §8§oRohgold in Gold",
                                "§8§oschmelzen zu lassen, dieser Vorgang",
                                "§8§odauert aktuell §7§o50 §8§oSekunden§8.",
                                "",
                                "§8┌ §7Fortschritt§8: §7" + (!moneyMakerController.isInVorgang() ? "§c§lKEIN VORGANG" : ValueUtil.showProgressBar(Data.getInventoryMaps().schmelzProgress.get(player.getUniqueId()), 50)),
                                "§8└ §7Dauer§8: §7" + (!moneyMakerController.isInVorgang() ? "§c§lKEIN VORGANG" : 50 - Data.getInventoryMaps().schmelzProgress.get(player.getUniqueId()) + " §7Sek§8."),
                                ""
                        );

                setItem(20, prozessBegin, (click, type) -> {


                    if (moneyMakerController.isInVorgang()) {
                        Data.getMessageUtil().sendMessage(player, "§cBitte warte noch §7" + (50 - Data.getInventoryMaps().schmelzProgress.get(player.getUniqueId()) + " §cSekunden§8."));
                        return;
                    }

                    if (userController.getStatisticByType(StatisticTypes.ROHGOLD) < 15) {
                        Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Rohgold!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return;
                    }

                    moneyMakerController.startSchmelzing(15, 50);

                });


                setItem(22, stats, null);
                setItem(24, autoMiner, (player1, clickType) -> {
                    new MoneyMakerMinerInventory(player).openGUI();
                });

            }
        }.runTaskTimer(SkyPvP.getINSTANCE(), 0L, 20L);


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
        task.cancel();
    }
}
