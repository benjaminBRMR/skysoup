package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.Objects;

/**
 * Created: 13.02.2023 13:04
 *
 * @author thvf
 */
public class CommandPay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;


            if (!Data.getToggleController().isToggled(ToggleController.Types.PAY)) {
                Data.getMessageUtil().sendMessage(player, "§cAktuell kannst du niemanden payen!");
                return true;
            }

            if (args.length == 2) {

                final Player target = Bukkit.getPlayer(args[0]);
                final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
                long amount = 0;

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                if (Objects.equals(target.getName(), player.getName())) {
                    Data.getMessageUtil().sendMessage(player, "§cDu kannst dir selber keine Tokens payen§8.");
                    return true;
                }

                try {
                    amount = Long.parseLong(args[1]);
                } catch (NumberFormatException ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                    return true;
                }

                if (amount < 100) {
                    Data.getMessageUtil().sendMessage(player, "§cDie Anzahl muss größer als 100 sein!");
                    return true;
                }

                if (amount > userController.getStatisticByType(StatisticTypes.MONEY)) {
                    Data.getMessageUtil().sendMessage(player, "§cDu hast nicht genügend Tokens!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                    return true;
                }


                userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) - amount);
                final UserController targetUser = new UserController().getUserByUUID(target.getUniqueId());
                targetUser.setStatisticFromUser(StatisticTypes.MONEY, targetUser.getStatisticByType(StatisticTypes.MONEY) + amount);

                player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 10, 18.4F);
                target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10, 18.4F);
                Data.getMessageUtil().sendMessage(player, "§7Du hast §e" + target.getName() + " §8'§6$§e" + NumberFormat.getInstance().format(amount) + "§8' §7Tokens gepayed§8.");
                Data.getMessageUtil().sendMessage(target, "§e" + player.getName() + " §7hat dir §8'§6$§e" + NumberFormat.getInstance().format(amount) + "§8' §7Tokens gepayed§8.");

                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§ePay §7<spieler> <anzahl>");
        }
        return false;
    }
}
