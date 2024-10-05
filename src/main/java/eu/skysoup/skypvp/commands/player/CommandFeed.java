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
public class CommandFeed implements CommandExecutor {

    private final TempCooldown cooldown = new TempCooldown();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.FEED.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (args.length == 0) {


                if (!player.hasPermission(Permissions.TEAM.getPermission()) && !cooldown.isDone(player.getUniqueId())) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte warte noch " + cooldown.getReminding(player.getUniqueId()) + " Sekunden");
                    return true;
                }

                player.setFoodLevel(20);
                player.playSound(player.getLocation(), Sound.BURP, 20, 18.4F);
                Data.getMessageUtil().sendMessage(player, "§7Dein Hunger wurde gestillt.");
                if (!player.hasPermission(Permissions.TEAM.getPermission()))
                    cooldown.addPlayerToCooldown(player.getUniqueId(), 10);
                return true;
            }

            if (args.length == 1 && !args[0].equalsIgnoreCase("*")) {
                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.FEEDOTHER.getPermission(), true))
                    return true;

                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                target.setFoodLevel(20);
                target.playSound(player.getLocation(), Sound.BURP, 20, 18.4F);
                Data.getMessageUtil().sendMessage(target, "§7Dein Hunger wurde gestillt.");
                Data.getMessageUtil().sendMessage(player, "§e" + target.getName() + "§8'§es §7Hunger wurde gestillt.");
                return true;
            }


            if (args.length == 1 && args[0].equalsIgnoreCase("*")) {
                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.FEEDALL.getPermission(), true))
                    return true;

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setFoodLevel(20);
                    all.playSound(player.getLocation(), Sound.BURP, 20, 18.4F);
                    Data.getMessageUtil().sendMessage(all, "§7Dein Hunger wurde von §e" + player.getName() + " §7gestillt.");
                }

                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§fFeed");


        }
        return false;
    }
}
