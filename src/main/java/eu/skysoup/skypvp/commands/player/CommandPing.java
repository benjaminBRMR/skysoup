package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.data.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created: 11.02.2023 00:44
 *
 * @author thvf
 */
public class CommandPing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            Data.getMessageUtil().sendMessage(player, "§7Dein aktueller Ping§8: §e" + ((CraftPlayer) player).getHandle().ping + "§8§oms!");


        }
        return false;
    }
}
