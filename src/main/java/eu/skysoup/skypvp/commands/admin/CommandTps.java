package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

/**
 * Created: 09.02.2023 01:15
 *
 * @author thvf
 */

public class CommandTps implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.TPS.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            player.sendMessage("");
            Data.getMessageUtil().sendMessage(player, "§7ServerTPS §8- §7Monitor");
            player.sendMessage("");
            Data.getMessageUtil().sendMessage(player, "§7Aktuelle TPS§8: §e" + format(MinecraftServer.getServer().recentTps[0]));
            Data.getMessageUtil().sendMessage(player, "§7TPS vor §65min§8: §e" + format(MinecraftServer.getServer().recentTps[1]));
            Data.getMessageUtil().sendMessage(player, "§7TPS vor §615min§8: §e" + format(MinecraftServer.getServer().recentTps[2]));
            Data.getMessageUtil().sendMessage(player, "§7Geladene Chunks§8: §e" + NumberFormat.getInstance().format(player.getWorld().getLoadedChunks().length));
            player.sendMessage("");


        }
        return false;
    }

    String format(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
                + ((tps > 20.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
    }
}
