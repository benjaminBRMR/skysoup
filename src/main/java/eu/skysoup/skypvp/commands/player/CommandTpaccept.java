package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.data.Data;
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
public class CommandTpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {

            final Player player = (Player) sender;

            if (!Data.getTparequest().containsValue(player)) {
                Data.getMessageUtil().sendMessage(player, "§cDu hast keine TPA-Anfragen.");
                return true;
            }

            final Player target = Data.getTparequest().get(player);
            target.teleport(player);

            Data.getMessageUtil().sendMessage(target, "§f" + player.getName() + " §ahat deine TPA-Anfrage akzeptiert§8.");
            Data.getMessageUtil().sendMessage(player, "§aDu hast die TPA-Anfrage akzeptiert§8.");

            Data.getTparequest().remove(player);
            Data.getTparequest().remove(target);

        }
        return false;
    }
}
