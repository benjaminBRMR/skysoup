package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.commands.player.CommandInvsee;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.data.implementorings.UserValues;
import eu.skysoup.skypvp.utils.scoreboard.FastBoard;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created: 23.01.2023 11:10
 *
 * @author thvf
 */
public class QuitListener implements Listener {


    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {


        final Player player = event.getPlayer();
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final CasinoController casinoController = Data.getCasinoController().getUser(player);
        final FastBoard fastBoard = Data.getScoreboardController().getFastBoardMap().remove(player.getUniqueId());
        final int ticks = player.getStatistic(Statistic.PLAY_ONE_TICK);


        event.setQuitMessage((player.hasPermission(Permissions.QUITMSG.getPermission()) && userController.getCustomValue(UserValues.QUITMESSAGE) != null ? "§c§l- §8┃ §7" + userController.getCustomValue(UserValues.QUITMESSAGE).toString().replaceAll("&", "§").replaceAll("%name%", player.getName()) : null));


        if (fastBoard != null) fastBoard.delete();
        Data.getReply().remove(player);
        Data.getTparequest().remove(player);
        if (CommandInvsee.getInvseeMap().containsKey(player)) CommandInvsee.getInvseeMap().remove(player);
        userController.setStatisticFromUser(StatisticTypes.PLAYTIME, ticks);
        userController.setStatisticFromUser(StatisticTypes.OFFLINESINCE, System.currentTimeMillis());
        casinoController.logoutFromCasino();
        userController.setDisconnectLocation();


    }
}
