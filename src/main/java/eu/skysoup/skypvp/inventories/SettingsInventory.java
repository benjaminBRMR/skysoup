package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created: 03.02.2023 12:55
 *
 * @author thvf
 */
public class SettingsInventory extends SingletonInventory {


    private String pn = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGFlN2JmNDUyMmIwM2RmY2M4NjY1MTMzNjNlYWE5MDQ2ZmRkZmQ0YWE2ZjFmMDg4OWYwM2MxZTYyMTZlMGVhMCJ9fX0=";

    private String tpa = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOGU3ZjJkYjhlYWE4OGEwNDFjODlkNGMzNTNkMDY2Y2M0ZWRlZjc3ZWRjZjVlMDhiYjVkM2JhYWQifX19";

    private String spawnteleport = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDQ4MTEyZDViYjMxNjBmZDdiOTViYjZjNDYzZWNiM2NjZTdjN2U5MWM4OGIyOWE3ZTc5N2RkNzdjNDZhZTYifX19";

    private String msg = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGUwZDM2MWQ4MzI5MmMwNGZlYWE4MTFmMWU1NzgzODQyYjc4ZmFhOWM5ZmY4ZGE2Y2MwNjMwMThkNjJkYjdkZiJ9fX0=";

    private String cw = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNkZmY1ODE0YzU3NjIyNDdmM2Y4NTg1NzY0NmIwMTZjOWFhMzYxMjNmY2ZhYjUwNTQ2YWYxNTgyN2JiODZmYyJ9fX0=";

    private String admincw = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNjYjBmNTRmOTE4MjJkMzdmOTcyMzI2ZjMwNTEyZGY2NTJjOTA1MGIwNjgyOWFmMWQ0MTJlZWNhMmQ0MjUzIn19fQ==";

    private BukkitTask task;


    public SettingsInventory(final Player player) {
        super(InventoryTitles.SETTINGS.getName(), Rows.CHEST_ROW_6, player);

        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-");


        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));


        final ItemStack msgItem = ItemSkull.getSkull("§8┃ §3§lSETTING §8: §7Private-Nachrichten", pn,

                "§8§oDiese Einstellung ermöglicht es dir deine",
                "§8§oprivaten Nachrichten aus- oder anzuschalten.",
                "",
                " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.MSG) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                "");
        final ItemStack tpaItem = ItemSkull.getSkull("§8┃ §3§lSETTING §8: §7TPA-Anfragen", tpa, "§8§oDiese Einstellung ermöglicht es dir deine",
                "§8§oTeleportationsanfragen aus- oder anzuschalten.",
                "",
                " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.TPA) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                "");
        final ItemStack spawntpItem = ItemSkull.getSkull("§8┃ §3§lSETTING §8: §7Spawnteleport", spawnteleport, "§8§oDiese Einstellung ermöglicht es dir deine Spawnposition",
                "§8§ozwichen Spawn und deinem letzten Ausloggpunkt einzustellen.",
                "",
                " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.SPAWNTELEPORT) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                "");
        final ItemStack msgspyItem = ItemSkull.getSkull("§8┃ §3§lSETTING §8: §7MSG-Spy", msg, "§8§oDiese Einstellung ermöglicht es private",
                "§8§oNachrichten anderer Spieler mitzulesen",
                "",
                " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.MSGSPY) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                "");
        final ItemStack cwItem = ItemSkull.getSkull("§8┃ §3§lSETTING §8: §7CMD-Spy", cw, "§8§oDiese Einstellung ermöglicht es dir",
                "§8§oBefehle anderer Spieler zu sehen.",
                "",
                " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.CW) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                "");
        final ItemStack admincwItem = ItemSkull.getSkull("§8┃ §3§lSETTING §8: §7ADMINCMD-Spy", admincw, "§8§oDiese Einstellung ermöglicht es dir",
                "§8§overbotene Befehle anderer Spieler zu sehen.",
                "",
                " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.ADMINCW) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                "");

        setItem(10, msgItem, (click, type) -> {

            userController.setSettingFromUser(SettingTypes.MSG, !userController.getSettingFromUser(SettingTypes.MSG));
            player.playSound(player.getLocation(), Sound.CLICK, 2, 0.8F);
            Data.getMessageUtil().sendMessage(player, "§7Privaten-Nachrichten wurden " + (userController.getSettingFromUser(SettingTypes.MSG) ? "§a§lAKTIVIERT§8." : "§c§lDEAKTIVIERT§8."));


            updateItemStackLore(getInventory(), 10, "§8§oDiese Einstellung ermöglicht es dir deine",
                    "§8§oprivaten Nachrichten aus- oder anzuschalten.",
                    "",
                    " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.MSG) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                    "");

        });
        setItem(11, tpaItem, (click, type) -> {
            userController.setSettingFromUser(SettingTypes.TPA, !userController.getSettingFromUser(SettingTypes.TPA));
            player.playSound(player.getLocation(), Sound.CLICK, 2, 0.8F);
            Data.getMessageUtil().sendMessage(player, "§7TPA-Anfragen wurden " + (userController.getSettingFromUser(SettingTypes.TPA) ? "§a§lAKTIVIERT§8." : "§c§lDEAKTIVIERT§8."));

            updateItemStackLore(getInventory(), 11, "§8§oDiese Einstellung ermöglicht es dir deine",
                    "§8§oTeleportationsanfragen aus- oder anzuschalten.",
                    "",
                    " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.TPA) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                    "");
        });
        setItem(12, spawntpItem, (click, type) -> {
            userController.setSettingFromUser(SettingTypes.SPAWNTELEPORT, !userController.getSettingFromUser(SettingTypes.SPAWNTELEPORT));
            player.playSound(player.getLocation(), Sound.CLICK, 2, 0.8F);
            Data.getMessageUtil().sendMessage(player, "§7Spawnteleport wurde " + (userController.getSettingFromUser(SettingTypes.SPAWNTELEPORT) ? "§a§lAKTIVIERT§8." : "§c§lDEAKTIVIERT§8."));

            updateItemStackLore(getInventory(), 12, "§8§oDiese Einstellung ermöglicht es dir deine Spawnposition",
                    "§8§ozwichen Spawn und deinem letzten Ausloggpunkt einzustellen.",
                    "",
                    " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.SPAWNTELEPORT) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                    "");
        });


        if (userController.hasPermissionForSetting(SettingTypes.CW)) {
            setItem(37, cwItem, (click, type) -> {
                userController.setSettingFromUser(SettingTypes.CW, !userController.getSettingFromUser(SettingTypes.CW));
                player.playSound(player.getLocation(), Sound.CLICK, 2, 0.8F);
                Data.getMessageUtil().sendMessage(player, "§7CommandWatcher wurde " + (userController.getSettingFromUser(SettingTypes.CW) ? "§a§lAKTIVIERT§8." : "§c§lDEAKTIVIERT§8."));

                updateItemStackLore(getInventory(), 37, "§8§oDiese Einstellung ermöglicht es dir",
                        "§8§oBefehle anderer Spieler zu sehen.",
                        "",
                        " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.CW) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                        "");

            });
        } else {
            setItem(37, glass, null);
        }

        if (userController.hasPermissionForSetting(SettingTypes.MSGSPY)) {
            setItem(38, msgspyItem, (click, type) -> {
                userController.setSettingFromUser(SettingTypes.MSGSPY, !userController.getSettingFromUser(SettingTypes.MSGSPY));
                player.playSound(player.getLocation(), Sound.CLICK, 2, 0.8F);
                Data.getMessageUtil().sendMessage(player, "§7MSG-Spy wurde " + (userController.getSettingFromUser(SettingTypes.MSGSPY) ? "§a§lAKTIVIERT§8." : "§c§lDEAKTIVIERT§8."));

                updateItemStackLore(getInventory(), 38, "§8§oDiese Einstellung ermöglicht es private",
                        "§8§oNachrichten anderer Spieler mitzulesen",
                        "",
                        " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.MSGSPY) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                        "");
            });

        } else {
            setItem(38, glass, null);

        }


        if (userController.hasPermissionForSetting(SettingTypes.ADMINCW)) {
            setItem(39, admincwItem, (click, type) -> {
                userController.setSettingFromUser(SettingTypes.ADMINCW, !userController.getSettingFromUser(SettingTypes.ADMINCW));
                player.playSound(player.getLocation(), Sound.CLICK, 2, 0.8F);
                Data.getMessageUtil().sendMessage(player, "§7AdminCMD-Spy wurden " + (userController.getSettingFromUser(SettingTypes.ADMINCW) ? "§a§lAKTIVIERT§8." : "§c§lDEAKTIVIERT§8."));

                updateItemStackLore(getInventory(), 39, "§8§oDiese Einstellung ermöglicht es dir",
                        "§8§overbotene Befehle anderer Spieler zu sehen.",
                        "",
                        " §8├ §7Status§8: " + (userController.getSettingFromUser(SettingTypes.ADMINCW) ? "§a§lAKTIVIERT" : "§c§lDEAKTIVIERT"),
                        "");

            });

        } else {
            setItem(39, glass, null);
        }


    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {
    }
}
