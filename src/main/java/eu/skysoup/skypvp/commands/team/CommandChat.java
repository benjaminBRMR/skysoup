package eu.skysoup.skypvp.commands.team;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 29.01.2023 11:41
 *
 * @author thvf
 */
public class CommandChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.TEAM.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {

                for (int i = 0; i < 255; i++) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(Permissions.TEAM.getPermission())) continue;
                        all.sendMessage("§8§o¦");
                    }
                }


                for (Player all : Bukkit.getOnlinePlayers()) {

                    if (all.hasPermission(Permissions.TEAM.getPermission())) {
                        Data.getMessageUtil().sendMessageToTeam("§8» §cAls Teammitglied wird dein Chat nicht geleert.", true);
                    }

                    all.sendMessage("");
                    all.sendMessage(PrefixController.getSkyPvP() + "§7Der §eChat §7wurde von " + player.getDisplayName() + " §7geleert§8.");
                    all.sendMessage("");
                    all.playSound(all.getLocation(), Sound.IRONGOLEM_DEATH, 50, 18.4F);
                }

                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("globalmute")) {

                if (!Data.getPermissionUtil().hasPermission(player, Permissions.GLOBALMUTE.getPermission(), true))
                    return true;

                Data.setGlobalmute(!Data.isGlobalmute());

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage("");
                    all.sendMessage(PrefixController.getSkyPvP() + "§e" + player.getDisplayName() + " §7hat den §eChat §7kurzzeitig " + (Data.isGlobalmute() ? "§aaktiviert." : "§cdeaktiviert."));
                    all.sendMessage("");
                    all.playSound(all.getLocation(), Sound.IRONGOLEM_DEATH, 50, 18.4F);

                }
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("slowchat")) {
                if (!Data.getPermissionUtil().hasPermission(player, Permissions.SLOWCHAT.getPermission(), true))
                    return true;

                Data.setSlowchat(!Data.isSlowchat());

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage("");
                    all.sendMessage(PrefixController.getSkyPvP() + "§e" + player.getDisplayName() + " §7hat den Slowchat " + (Data.isSlowchat() ? "§aaktiviert. §8(§e5 Sek§8)" : "§cdeaktiviert."));
                    all.sendMessage("");
                    all.playSound(all.getLocation(), Sound.IRONGOLEM_DEATH, 50, 18.4F);

                }
                return true;
            }
            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eChat §7<clear>",
                    "§8/§eChat §7<globalmute>",
                    "§8/§eChat §7<slowchat>",
                    "§r",
                    "§7Globalmute§8: " + (Data.isGlobalmute() ? "§aaktiviert" : "§cdeaktiviert"),
                    "§7Slowchat §8(§e5 Sek.§8)§8: " + (Data.isSlowchat() ? "§aaktiviert" : "§cdeaktiviert"));


        }
        return false;
    }
}
