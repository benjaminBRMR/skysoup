package eu.skysoup.skypvp.commands.team;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 17.02.2023 13:44
 *
 * @author thvf
 */
public class CommandTpaall implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.TPAAALL.getPermission(), true))
                return true;

            final Player player = (Player) sender;

            Bukkit.getOnlinePlayers().forEach(all -> {

                if (all == player) return;

                all.teleport(player);
                Data.getMessageUtil().sendMessage(all, "§7Du wurdest zu §e" + player.getName() + " §7teleportiert§8.");
                all.playSound(all.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 18.4F);
            });
            Data.getMessageUtil().sendMessage(player, "§eAlle §7Spieler wurden zu §eDir §7teleportiert§8.");
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 18.4F);
        }
        return false;
    }
}
