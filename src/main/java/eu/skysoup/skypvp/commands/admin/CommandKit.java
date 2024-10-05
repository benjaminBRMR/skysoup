package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.inventories.KitInventory;
import eu.skysoup.skypvp.kit.Kit;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 02.02.2023 12:15
 *
 * @author thvf
 */
public class CommandKit implements CommandExecutor {


    private void syntax(final Player player) {

        Data.getMessageUtil().sendSyntax(player,
                "§8/§eKit §7<create> <name> <cooldown> <timeunit> <permission>",
                "§8/§eKit §7<setcooldown> <name> <cooldown>",
                "§8/§eKit §7<settimeunit> <name> <timeunit>",
                "§8/§eKit §7<setpermission> <name> <permission>",
                "§8/§eKit §7<delete> <name>",
                "§8/§eKit §7<edit> <name>",
                "§8/§eKit §7<give> <name>",
                "§8/§eKit §7<list>",
                "",
                "§8§oBeispiel: /Kit create Test 2 Hours"
        );

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {


            final Player player = (Player) sender;

            if (!player.hasPermission(Permissions.ADMIN.getPermission())) {
                new KitInventory(player).openGUI();
                return true;
            }

            if (args.length == 0) {
                new KitInventory(player).openGUI();
                return true;
            }


            if (args.length == 5 && args[0].equalsIgnoreCase("create")) {


                try {
                    final String kitName = args[1];
                    final long cooldown = Long.parseLong(args[2]);
                    final TimeUnit timeUnit = TimeUnit.valueOf(args[3].toUpperCase());
                    final String permission = args[4];

                    final Kit kit = new Kit(kitName, new ArrayList<>(), cooldown, timeUnit, permission);


                    if (Data.getKitController().kitExist(kit.getName())) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert bereits!");
                        return true;
                    }

                    Data.getKitController().createKit(kit);
                    Data.getMessageUtil().sendMessage(player, "§7Das Kit §8'§a§l" + kitName + "§8' §7wurde erstellt§8.");

                } catch (Exception ignored) {
                    syntax(player);
                }
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {

                final String kitName = args[1];

                if (!Data.getKitController().kitExist(kitName)) {
                    Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert nicht!");
                    return true;
                }

                Data.getKitController().deleteKit(kitName);
                Data.getMessageUtil().sendMessage(player, "§7Das Kit §8'§a§l" + kitName + "§8' §7wurde §cgelöscht§8.");
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("setcooldown")) {

                try {
                    final String kitName = args[1];
                    final long cooldown = Long.parseLong(args[2]);

                    if (!Data.getKitController().kitExist(kitName)) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert nicht!");
                        return true;
                    }

                    Data.getKitController().setCooldown(kitName, cooldown);
                    Data.getMessageUtil().sendMessage(player, "§7Cooldown des Kits §8'§a§l" + kitName + "§8' §7auf §a" + cooldown + " §7gesetzt§8.");
                } catch (Exception ignored) {
                    syntax(player);
                }
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("setpermission")) {

                try {
                    final String kitName = args[1];
                    final String permission = args[2];

                    if (!Data.getKitController().kitExist(kitName)) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert nicht!");
                        return true;
                    }

                    Data.getKitController().setPermission(kitName, permission);
                    Data.getMessageUtil().sendMessage(player, "§7Permission des Kits §8'§a§l" + kitName + "§8' §7auf §a" + permission + " §7gesetzt§8.");
                } catch (Exception ignored) {
                    syntax(player);
                }
                return true;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("settimeunit")) {

                try {
                    final String kitName = args[1];
                    final TimeUnit timeUnit = TimeUnit.valueOf(args[2].toUpperCase());

                    if (!Data.getKitController().kitExist(kitName)) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert nicht!");
                        return true;
                    }

                    Data.getKitController().setTimeUnit(kitName, timeUnit);
                    Data.getMessageUtil().sendMessage(player, "§7TimeUnit des Kits §8'§a§l" + kitName + "§8' §7auf §a" + timeUnit + " §7gesetzt§8.");
                } catch (Exception ignored) {
                    syntax(player);
                }
                return true;
            }


            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                final AtomicInteger atomicInteger = new AtomicInteger(1);

                if (Data.getKitController().getListedKits().size() < 1) {
                    Data.getMessageUtil().sendMessage(player, "§cAktuell existieren keine Kits!");
                    return true;
                }

                for (String kit : Data.getKitController().getListedKits()) {


                    Data.getMessageUtil().sendMessage(player, "§8#§6§o" + atomicInteger.get() + " §8| §7Kit§8: §f" + kit);
                    Data.getMessageUtil().sendMessage(player, "§8┌ §7Items§8: §f" + Data.getKitController().getItems(kit).size());
                    Data.getMessageUtil().sendMessage(player, "§8├ §7Cooldown§8: §f" + Data.getKitController().getCooldown(kit));
                    Data.getMessageUtil().sendMessage(player, "§8└ §7TimeUnit§8: §f" + Data.getKitController().getTimeUnit(kit).name());


                    atomicInteger.getAndIncrement();
                }
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {

                final AtomicInteger atomicInteger = new AtomicInteger(0);
                final String kitName = args[1];

                if (!Data.getKitController().kitExist(kitName)) {
                    Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert nicht!");
                    return true;
                }

                Inventory inventory = Bukkit.createInventory(null, 54, "§8┃ §d§lKIT-EDIT §8: §7" + kitName);

                for (ItemStack items : Data.getKitController().getItems(kitName)) {
                    inventory.setItem(atomicInteger.get(), items);
                    atomicInteger.getAndIncrement();
                }

                player.openInventory(inventory);
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("give")) {

                final String kitName = args[1];

                if (!Data.getKitController().kitExist(kitName)) {
                    Data.getMessageUtil().sendMessage(player, "§cDieses Kit existiert nicht!");
                    return true;
                }


                Data.getKitController().getItems(kitName).forEach(all -> {
                    player.getInventory().addItem(all);
                });
                Data.getMessageUtil().sendMessage(player, "§7Du hast das Kit §8'§a§l" + kitName + "§8' §7erhalten§8.");

                return true;
            }
            syntax(player);
        }
        return false;
    }
}
