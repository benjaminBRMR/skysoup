package eu.skysoup.skypvp.controller;

import com.avaje.ebean.validation.NotNull;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.impl.Utils;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import eu.skysoup.skypvp.utils.scoreboard.FastBoard;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created: 24.01.2023 10:39
 *
 * @author thvf
 */
public class ScoreboardController {

    @Getter
    public Map<UUID, FastBoard> fastBoardMap = new HashMap<>();


    @NotNull
    public void updateScoreboard(final FastBoard fastBoard) {

        final Player player = fastBoard.getPlayer();
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        final long tokens = userController.getStatisticByType(StatisticTypes.MONEY);
        final long kills = userController.getStatisticByType(StatisticTypes.KILLS);
        final long tode = userController.getStatisticByType(StatisticTypes.DEATHS);
        final long vote = userController.getStatisticByType(StatisticTypes.VOTES);
        final long trophys = userController.getStatisticByType(StatisticTypes.TROPHYS);
        final long killstreak = userController.getStatisticByType(StatisticTypes.KILLSTREAK);
        final long gold = userController.getStatisticByType(StatisticTypes.GOLD);


        final Duration ONE_TICK = Duration.ofSeconds(1L).dividedBy(20L);
        final int ticks = player.getStatistic(Statistic.PLAY_ONE_TICK);
        Duration plpTime = ONE_TICK.multipliedBy(ticks);


        fastBoard.updateTitle("     §e§lＳｋｙＳｏｕｐ§r");


        if (player.getWorld().getName().equals("Arena") || player.getWorld().getName().equals("PvP")) {

            fastBoard.updateLines(
                    "",
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE1),
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE2),
                    "",
                    "§8┌ §7Helm: §c" + (player.getInventory().getHelmet() == null ? "§c§l✘" : Utils.getItemDurabilityPercentage(player.getInventory().getHelmet())),
                    "§8├ §7Brust: §c" + (player.getInventory().getChestplate() == null ? "§c§l✘" : Utils.getItemDurabilityPercentage(player.getInventory().getChestplate())),
                    "§8├ §7Hose: §c" + (player.getInventory().getLeggings() == null ? "§c§l✘" : Utils.getItemDurabilityPercentage(player.getInventory().getLeggings())),
                    "§8└ §7Schuhe: §c" + (player.getInventory().getBoots() == null ? "§c§l✘" : Utils.getItemDurabilityPercentage(player.getInventory().getBoots())),
                    "",
                    "§8┌ §7K/D: §a" + (kills > 900 ? ValueUtil.format2(kills) : kills) + "§7/§c" + tode,
                    "§8├ §7Killstreak: §a" + killstreak,
                    "§8└ §7Trophäen: §6" + NumberFormat.getInstance().format(trophys),
                    "",
                    "§8§oskysoup.eu"
            );
            return;
        }

        if (player.getWorld().getName().equals("Casino")) {

            fastBoard.updateLines(
                    "",
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE1),
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE2),
                    "",
                    "§8┌ §7Tokens: §6$§e" + ValueUtil.format2(tokens),
                    "§8├ §7Chips: §6⛃§e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.CASINOCHIPS)),
                    "",
                    "§8┌ §7Spieler: §a" + Bukkit.getWorld("Casino").getPlayers().size(),
                    "",
                    "§8§oskysoup.eu"
            );
            return;
        }

        if (player.getWorld().getName().equals("moneymaker")) {

            fastBoard.updateLines(
                    "",
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE1),
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE2),
                    "",
                    "§8┌ §7Gold: §6" + NumberFormat.getInstance().format(gold),
                    "§8└ §7Roh: §e" + NumberFormat.getInstance().format(userController.getStatisticByType(StatisticTypes.ROHGOLD)),
                    "",
                    "§8┌ §7Arbeiter: §e0§7/§61",
                    "§8└ §7Level: §e1",
                    "",
                    "§8┌ §7Spieler: §a" + Bukkit.getWorld("moneymaker").getPlayers().size(),
                    "",
                    "§8§oskysoup.eu"
            );
            return;
        }

        if (player.getWorld().getName().equals("SoupPlots-V2")) {


            fastBoard.updateLines(
                    "",
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE1),
                    "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE2),
                    "",
                    "§e§l" + (Data.getPlotSquaredUtil().getPlotOwnerPlayerIsStandingOnSecond(player) == null ? "§c§lKein Besitzer" : Data.getPlotSquaredUtil().getPlotOwnerPlayerIsStandingOnSecond(player)),
                    "§8├ §7ID: §b" + (Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player) == null ? "§c§l✘" : Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).getId()),
                    "§8├ §7Besucher: §6" + (Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player) == null ? "§c§l✘" : Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).getPlayersInPlot().size()),
                    "§8└ §7Plots: §a" + Data.getPlotSquaredUtil().getPlotAPI().wrapPlayer(player).getPlots().size() + "§8/§c" + (Data.getPlotSquaredUtil().getPlotAPI().wrapPlayer(player).getAllowedPlots() > 200 ? "§c∞" : Data.getPlotSquaredUtil().getPlotAPI().wrapPlayer(player).getAllowedPlots()),
                    "",
                    "§8┌ §7Tokens: §6$§e" + ValueUtil.format2(tokens),
                    "§8└ §7Playtime: §f" + (plpTime.toMillis() < 60000 ? "§c✘" : ValueUtil.timeToString(plpTime.toMillis(), true, false)),
                    "",
                    "§8§oskysoup.eu"
            );
            return;
        }


        fastBoard.updateLines(
                "",
                "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE1),
                "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE2),
                "",
                "§8┌ §7Tokens: §6$§e" + ValueUtil.format2(tokens),
                "§8├ §7K/D: §a" + (kills > 900 ? ValueUtil.format2(kills) : kills) + "§7/§c" + tode,
                "§8├ §7Killstreak: §a" + killstreak,
                "§8└ §7Trophäen: §6" + NumberFormat.getInstance().format(trophys),
                "",
                "§8┌ §7Votes: §2" + ValueUtil.format2(vote),
                "§8└ §7Playtime: §f" + (plpTime.toMillis() < 60000 ? "§c✘" : ValueUtil.timeToString(plpTime.toMillis(), true, false)),
                "",
                "§8§oskysoup.eu"
        );




        /*
        fastBoard.updateLines(
                "",
                "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE1),
                "§8§o" + Data.getTaskController().getMapValue(TaskController.Lines.LINE2),
                "",
                " §6§l⛃§8┌ §x7Tokens: §e" + ValueUtil.format2(tokens),
                " §c§l✞§8├ §7K/§7D: §a" + (kills > 900 ? ValueUtil.format2(kills) : kills) + "§8/§c" + tode,
                " §3§l⚔§8├ §7Killstreak: §3" + killstreak,
                " §6§l®§8└ §7Trophäen: §6" + trophys,
                "",
                " §2§l✔§8┌ §7Votes: §a" + vote,
                " §4§l❤§8└ §7Spielzeit: §f" + ValueUtil.timeToString(plpTime.toMillis(), true, false),
                "",
                "§8§oskysoup.eu"
        );

         */

    }


}
