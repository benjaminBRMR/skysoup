package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.UserValues;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * JavaDoc this file!
 * Created: 07.01.2023
 *
 * @author thvf
 */
public class CommandJoinMsg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.JOINMSG.getPermission(), true))
                return true;
            final Player player = (Player) sender;
            final UserController userController = new UserController().getUserByUUID(player.getUniqueId());


            if (args.length >= 1 && !args[0].equalsIgnoreCase("clear")) {

                StringBuilder msg = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                if (!msg.toString().contains("%name%")) {
                    Data.getMessageUtil().sendMessage(player, "§cDeine Join-Message muss %name% enthalten.");
                    Data.getMessageUtil().sendMessage(player, "§c%name% wird automatisch mit deinem Namen ersetzt.");
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 50, 18.4F);
                    return true;
                }

                userController.setCustomValue(UserValues.JOINMESSAGE, msg.toString().replaceAll("&", "§"));
                Data.getMessageUtil().sendMessage(player, "§aDeine Join-Message wurde bearbeitet.");
                Data.getMessageUtil().sendMessage(player, "§a§l+ §8┃ §7" + msg.toString().replaceAll("&", "§").replaceAll("%name%", player.getName()));
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {

                userController.setCustomValue(UserValues.JOINMESSAGE, null);
                Data.getMessageUtil().sendMessage(player, "§cDeine Join-Message wurde entfernt.");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eJoinmessage §7<Nachricht>",
                    "§8/§eJoinmessage §7<clear>");


        }
        return false;
    }
}
