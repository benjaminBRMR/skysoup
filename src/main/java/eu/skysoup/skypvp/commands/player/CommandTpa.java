package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * JavaDoc this file!
 * Created: 07.01.2023
 *
 * @author thvf
 */
public class CommandTpa implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;


            if (args.length == 1) {

                final Player target = Bukkit.getPlayer(args[0]);
                final UserController userController;


                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                userController = new UserController().getUserByUUID(target.getUniqueId());


                if (!Data.getToggleController().isToggled(ToggleController.Types.TPA)) {
                    Data.getMessageUtil().sendMessage(player, "§cDu darfst aktuell keine TPA-Anfragen senden.");
                    return true;
                }

                if (!userController.getSettingFromUser(SettingTypes.TPA)) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Spieler hat seine TPA-Anfragen deaktiviert.");
                    return true;
                }

                if (Data.getTparequest().containsValue(player)) {
                    Data.getMessageUtil().sendMessage(player, "§cDu hast bereits eine TPA-Anfrage gesendet.");
                    return true;
                }


                if (target.getName().equals(player.getName())) {
                    Data.getMessageUtil().sendMessage(player, "§cDu kannst keine TPA-Anfrage an dich senden.");
                    return true;
                }


                Data.getTparequest().put(player, target);
                Data.getTparequest().put(target, player);

                Data.getMessageUtil().sendMessage(player, "§aDu hast eine TPA-Anfrage an §f" + target.getName() + " §ageschickt.");

                Data.getMessageUtil().sendMessage(target, "§e" + player.getName() + " §7möchte sich zu dir teleportieren§8.");
                Data.getMessageUtil().sendClickableMessageCommand(target, "§7Klicke um die Anfrage §a§lANZUNEHMEN", "§a§lANNEHMEN", "/tpaccept");
                Data.getMessageUtil().sendClickableMessageCommand(target, "§7Klicke um die Anfrage §c§lABZULEHNEN", "§c§lABLEHNEN", "/tpdeny");


                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Data.getTparequest().containsValue(player) && Data.getTparequest().containsValue(target)) {
                            Data.getMessageUtil().sendMessage(player, "§cDeine TPA-Anfrage an §f" + target.getName() + " §cist abgelaufen.");
                            Data.getMessageUtil().sendMessage(target, "§cDie TPA-Anfrage von §f" + player.getName() + " §cist abgelaufen.");
                            Data.getTparequest().remove(player);
                            Data.getTparequest().remove(target);
                        }
                    }
                }.runTaskLater(SkyPvP.getINSTANCE(), Data.getRunnableUtil().getTimeFromMinutes(1));
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eTpa §7<spieler>");

        }
        return false;
    }
}
