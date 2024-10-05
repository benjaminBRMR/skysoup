package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.utils.TempCooldown;
import org.bukkit.Bukkit;
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
public class CommandHeal implements CommandExecutor {

    private final TempCooldown cooldown = new TempCooldown();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.HEAL.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (args.length == 0) {


                if (!player.hasPermission(Permissions.TEAM.getPermission()) && !cooldown.isDone(player.getUniqueId())) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte warte noch " + cooldown.getReminding(player.getUniqueId()) + " Sekunden");
                    return true;
                }

                player.setHealth(20);
                player.playSound(player.getLocation(), Sound.BURP, 20, 18.4F);
                Data.getMessageUtil().sendMessage(player, "§7Deine Herzen wurden aufgefüllt.");
                if (!player.hasPermission(Permissions.TEAM.getPermission()))
                    cooldown.addPlayerToCooldown(player.getUniqueId(), 60);
                return true;
            }

            if (args.length == 1 && !args[0].equalsIgnoreCase("*")) {

                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.HEALOTHER.getPermission(), true))
                    return true;

                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                target.setHealth(20);
                target.playSound(player.getLocation(), Sound.BURP, 20, 18.4F);
                Data.getMessageUtil().sendMessage(target, "§7Deine Herzen wurden aufgefüllt..");
                Data.getMessageUtil().sendMessage(player, "§e" + target.getName() + "§8'§es §7Herzen wurden aufgefüllt.");
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("*")) {
                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.HEALALL.getPermission(), true))
                    return true;

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setHealth(20);
                    all.playSound(player.getLocation(), Sound.BURP, 20, 18.4F);
                    Data.getMessageUtil().sendMessage(all, "§7Deine Herzen wurden von §e" + player.getName() + " §7aufgefüllt.");
                }


                return true;
            }
            Data.getMessageUtil().sendSyntax(player,
                    "§8/§fHeal");
        }
        return false;
    }
}
