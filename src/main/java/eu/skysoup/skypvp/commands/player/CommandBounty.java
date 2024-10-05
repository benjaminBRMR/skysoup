package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.BountyInventory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.Objects;

/**
 * Created: 31.01.2023 20:23
 *
 * @author thvf
 */
public class CommandBounty implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {

            final Player player = (Player) sender;
            UserController userController;


            if (args.length == 3 && args[0].equalsIgnoreCase("set")) {

                try {

                    final Player target = Bukkit.getPlayer(args[1]);
                    final long amount = Long.parseLong(args[2]);

                    if (target == null) {
                        Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                        return true;
                    }

                    if (Objects.equals(target.getName(), player.getName())) {
                        Data.getMessageUtil().sendMessage(player, "§cWillst du dich selber umbringen oder wie?");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return true;
                    }


                    if (amount < 1000) {
                        Data.getMessageUtil().sendMessage(player, "§cDu musst mindestens 1.000 Tokens angeben!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return true;
                    }

                    if (amount > 1000000) {
                        Data.getMessageUtil().sendMessage(player, "§cDu darfst maximal 1.000.000 Tokens angeben!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return true;
                    }

                    userController = new UserController().getUserByUUID(player.getUniqueId());

                    if (userController.getStatisticByType(StatisticTypes.MONEY) < amount) {
                        Data.getMessageUtil().sendMessage(player, "§cDafür hast du nicht genug Tokens!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        return true;
                    }

                    userController = new UserController().getUserByUUID(target.getUniqueId());


                    userController.getBountyController().setBounty((userController.getBountyController().hasBounty() ? userController.getBountyController().getBounty() + amount : amount));


                    userController = new UserController().getUserByUUID(player.getUniqueId());
                    userController.setStatisticFromUser(StatisticTypes.SPENTBOUNTYS, userController.getStatisticByType(StatisticTypes.SPENTBOUNTYS) + amount);
                    userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - amount);

                    if (amount > 10000) {
                        Data.getMessageUtil().sendMessageToAll("§e" + player.getName() + " §7hat ein Kopfgeld in Höhe von §e" + NumberFormat.getInstance().format(amount) + " §7Tokens auf §e" + target.getName() + " §7gesetzt§8.");
                        Data.getPlayerUtil().playSoundToEveryone(Sound.ENDERDRAGON_GROWL);
                    } else {
                        Data.getMessageUtil().sendMessage(player, "§7Du hast ein Kopfgeld in Höhe von §e" + NumberFormat.getInstance().format(amount) + " §7Tokens auf §e" + target.getName() + " §7gesetzt§8.");
                        Data.getMessageUtil().sendMessage(target, "§e" + player.getName() + " §7hat ein Kopfgeld in Höhe von §e" + NumberFormat.getInstance().format(amount) + " §7Tokens auf dich gesetzt§8.");
                    }
                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                }

                return true;
            }

            new BountyInventory(player).openGUI();
        }
        return false;
    }
}
