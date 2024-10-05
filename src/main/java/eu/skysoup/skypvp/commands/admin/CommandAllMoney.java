package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

/**
 * Created: 09.02.2023 11:28
 *
 * @author thvf
 */
public class CommandAllMoney implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            long allMoney = 0;
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                final UserController userHandler1 = new UserController().getUserByUUID(offlinePlayer.getUniqueId());
                allMoney = allMoney + userHandler1.getStatisticByType(StatisticTypes.MONEY);
            }

            Data.getMessageUtil().sendMessage(player, "§7Tokens im Umlauf§8: §e" + NumberFormat.getInstance().format(allMoney) + " §8= §6" + ValueUtil.format(allMoney));

        }
        return false;
    }
}
