package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.RankTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.time.Duration;

/**
 * Created: 02.02.2023 21:26
 *
 * @author thvf
 */
public class CommandStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {

            final Player player = (Player) sender;
            UserController userController;

            if (args.length == 1) {

                final Player target = Bukkit.getPlayer(args[0]);


                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }
                final Duration ONE_TICK = Duration.ofSeconds(1L).dividedBy(20L);
                final int ticks = target.getStatistic(Statistic.PLAY_ONE_TICK);
                final Duration plpTime = ONE_TICK.multipliedBy(ticks);

                userController = new UserController().getUserByUUID(target.getUniqueId());

                final RankTypes pvp_rank = RankTypes.getRankFromKills((int) userController.getStatisticByType(StatisticTypes.KILLS));


                player.sendMessage("");
                Data.getMessageUtil().sendMessage(player, "§7Spielerstatistiken von §e" + target.getName());
                player.sendMessage("");
                Data.getMessageUtil().sendMessage(player, "§7Money§8: §6$§e" + ValueUtil.format2(userController.getStatisticByType(StatisticTypes.MONEY)) + "§8, §7Kills§8: §a" + userController.getStatisticByType(StatisticTypes.KILLS) + "§8, §7Tode§8: §c" + userController.getStatisticByType(StatisticTypes.DEATHS) + "§8, §7PvP§8: " + pvp_rank.getValue());
                Data.getMessageUtil().sendMessage(player, "§7Clan§8: §e§l" + (Data.getClanController().isInClan(target) ? Data.getClanController().getClan(target).toUpperCase() : "§c✘") + "§8, §7Trophäen§8: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.TROPHYS)) + "§8, §7Killstreak§8: §a" + userController.getStatisticByType(StatisticTypes.KILLSTREAK));
                Data.getMessageUtil().sendMessage(player, "§7Spielzeit§8: §e" + ValueUtil.timeToString(plpTime.toMillis(), false, false) + "§8, §7Votes§8: §e" + userController.getStatisticByType(StatisticTypes.VOTES));

                return true;
            }

            userController = new UserController().getUserByUUID(player.getUniqueId());
            final Duration ONE_TICK = Duration.ofSeconds(1L).dividedBy(20L);
            final int ticks = player.getStatistic(Statistic.PLAY_ONE_TICK);
            final Duration plpTime = ONE_TICK.multipliedBy(ticks);
            final RankTypes pvp_rank = RankTypes.getRankFromKills((int) userController.getStatisticByType(StatisticTypes.KILLS));


            player.sendMessage("");
            Data.getMessageUtil().sendMessage(player, "§7Spielerstatistiken von §eDir");
            player.sendMessage("");
            Data.getMessageUtil().sendMessage(player, "§7Money§8: §6$§e" + ValueUtil.format2(userController.getStatisticByType(StatisticTypes.MONEY)) + "§8, §7Kills§8: §a" + userController.getStatisticByType(StatisticTypes.KILLS) + "§8, §7Tode§8: §c" + userController.getStatisticByType(StatisticTypes.DEATHS) + "§8, §7PvP§8: " + pvp_rank.getValue());
            Data.getMessageUtil().sendMessage(player, "§7Clan§8: §e§l" + (Data.getClanController().isInClan(player) ? Data.getClanController().getClan(player).toUpperCase() : "§c✘") + "§8, §7Trophäen§8: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.TROPHYS)) + "§8, §7Killstreak§8: §a" + userController.getStatisticByType(StatisticTypes.KILLSTREAK));
            Data.getMessageUtil().sendMessage(player, "§7Spielzeit§8: §e" + ValueUtil.timeToString(plpTime.toMillis(), false, false) + "§8, §7Votes§8: §e" + userController.getStatisticByType(StatisticTypes.VOTES));

        }
        return false;
    }
}
