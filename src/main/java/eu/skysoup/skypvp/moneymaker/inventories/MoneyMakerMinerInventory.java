package eu.skysoup.skypvp.moneymaker.inventories;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.inventories.InventoryTitles;
import eu.skysoup.skypvp.moneymaker.MoneyMakerController;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 27.02.2023 15:22
 *
 * @author thvf
 */
public class MoneyMakerMinerInventory extends SingletonInventory {

    public MoneyMakerMinerInventory(@NonNull Player player) {
        super(InventoryTitles.MONEYMAKERWORKER.getName(), Rows.CHEST_ROW_5, player);

        final MoneyMakerController moneyMakerController = new MoneyMakerController().getUserbyUUID(player.getUniqueId());
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        final ItemStack minerLevel1 = new ItemBuilder(Material.IRON_INGOT)
                .setName("§8┃ §e§lARBEITER §8: §7Level §eI")
                .lore(
                        "§8§oDer Arbeiter Level 1 generiert",
                        "§8§o1 Rohgold pro Stunde§8.",
                        "",
                        "§8┌ §7Preis§8: §65,000 Gold",
                        "§8└ §7Preis2§8: §650,000 Rohgold",
                        "",
                        "§8┌ §7Verdienst§8: §e1 Rohgold/h",
                        "§8└ §7Status§8: " + (moneyMakerController.getArbeiterLevel() == 1 ? "§a§lEINGESTELLT" : "§c§lNICHT EINGESTELLT"),
                        ""
                );

        final ItemStack minerLevel2 = new ItemBuilder(Material.GOLD_INGOT)
                .setName("§8┃ §e§lARBEITER §8: §7Level §eII")
                .lore(
                        "§8§oDer Arbeiter Level 2 generiert",
                        "§8§o1 Rohgold pro 45 Minuten§8.",
                        "",
                        "§8┌ §7Preis§8: §65,000 Gold",
                        "§8└ §7Preis2§8: §650,000 Rohgold",
                        "",
                        "§8┌ §7Verdienst§8: §e1 Rohgold/45min",
                        "§8└ §7Status§8: " + (moneyMakerController.getArbeiterLevel() == 2 ? "§a§lEINGESTELLT" : "§c§lNICHT EINGESTELLT"),
                        ""
                );

        final ItemStack minerLevel3 = new ItemBuilder(Material.DIAMOND)
                .setName("§8┃ §e§lARBEITER §8: §7Level §eIII")
                .lore(
                        "§8§oDer Arbeiter Level 3 generiert",
                        "§8§o1 Rohgold pro 30 Minuten§8.",
                        "",
                        "§8┌ §7Preis§8: §65,000 Gold",
                        "§8└ §7Preis2§8: §650,000 Rohgold",
                        "",
                        "§8┌ §7Verdienst§8: §e1 Rohgold/30min",
                        "§8└ §7Status§8: " + (moneyMakerController.getArbeiterLevel() == 3 ? "§a§lEINGESTELLT" : "§c§lNICHT EINGESTELLT"),
                        ""
                );


        setItem(20, minerLevel1, null);
        setItem(21, minerLevel2, null);
        setItem(22, minerLevel3, null);
        setItem(23, new ItemBuilder(Material.BARRIER).setName("§c§l§k###"), null);
        setItem(24, new ItemBuilder(Material.BARRIER).setName("§c§l§k###"), null);


        setItem(36, ItemSkull.getSkull("§8┃ §e§lMONEYMAKER §8: §7Zurück", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==",
                "§8§oKlicke, um zum",
                "§8§oHauptmenü zu gelangen."
        ), (click, type) -> {
            new MoneyMakerInventory(player).openGUI();
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 5, 0.1F);
        });


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
