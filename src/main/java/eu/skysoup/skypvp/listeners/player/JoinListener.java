package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.data.implementorings.UserValues;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.impl.Utils;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import eu.skysoup.skypvp.utils.scoreboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * Created: 23.01.2023 00:47
 *
 * @author thvf
 */
public class JoinListener implements Listener {


    //private final String[] greetings = new String[]{"Was geht ", "Oha was machst du denn hier ", "Hallo "};

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final PlayerJoinEvent event) {

        final String header =
                Data.getHeader().replaceAll("%nl%", "\n")
                        .replaceAll("%size of all players%", String.valueOf(Utils.getCorrectOnlinePlayers()))
                        .replaceAll("%uhrzeit%", new SimpleDateFormat("HH:mm").format(new Date()))
                        .replaceAll("%erreichbar%", ValueUtil.timeToString(System.currentTimeMillis() - SkyPvP.getUPTIME(), true, true))
                        .replaceAll("%todaycount%", String.valueOf(Bukkit.getOfflinePlayers().length));

        final Player player = event.getPlayer();
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final String formattedTime = ValueUtil.formatTime((System.currentTimeMillis() - player.getLastPlayed()));

        IntStream.range(0, 10).forEach(all -> {
            Data.getLabymodUtil().sendCurrentPlayingGamemode(player, true, Data.getConfigController().getString("labyjoinmsg").replaceAll("&", "§"));

        });

        if (!player.hasPlayedBefore()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.kickPlayer("\n\n §8§l┃ §e§lSKYSOUP §8» §7Bitte betrete den Server erneut!");
                }
            }.runTaskLater(SkyPvP.getINSTANCE(), 5L);
            event.setJoinMessage(null);
            return;
        }

        if (!userController.doesUserExist()) {
            Bukkit.getOnlinePlayers().forEach(all -> {
                Data.getMessageUtil().sendMessageWithCstmPrefix(player, "", "");
                Data.getMessageUtil().sendMessage(player, "§e" + player.getName() + " §7ist neu auf dem Server§8! §7§o#§f§o" + Bukkit.getOfflinePlayers().length);
                Data.getMessageUtil().sendMessageWithCstmPrefix(player, "", "");
            });

            userController.createUser();
        }


        event.setJoinMessage((player.hasPermission(Permissions.JOINMSG.getPermission()) && userController.getCustomValue(UserValues.JOINMESSAGE) != null ? "§a§l+ §8┃ §7" + userController.getCustomValue(UserValues.JOINMESSAGE).toString().replaceAll("§", "§").replaceAll("%name%", player.getName()) : null));

        if (!Data.getCachedSkulls().containsKey(player.getUniqueId()))
            Data.getCachedSkulls().put(player.getUniqueId(), new ItemBuilder(Material.SKULL_ITEM).setDataId(3).skullOwner(player.getName()));


        if (!userController.getSettingFromUser(SettingTypes.SPAWNTELEPORT)) {
            player.teleport(userController.getDisconnectLocation());
        } else {
            if (Data.getLocation().existsLocation("spawn")) player.teleport(Data.getLocation().getLocation("spawn"));
        }

        player.setFoodLevel(20);
        player.setHealth(20);
        player.setGameMode(player.hasPermission(Permissions.ADMIN.getPermission()) ? GameMode.CREATIVE : GameMode.SURVIVAL);
        Utils.spawnRandomFirework(player.getLocation());
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        userController.setStatisticFromUser(StatisticTypes.OFFLINESINCE, 0);
        Data.getPlayerUtil().fixPlayer(player);

        Data.getPlayerUtil().sendTitle(player, 0, 43, 8, "§8» §e§lSKYSOUP", "§7Herzlich Willkommen§8, §6" + player.getName() + "§8! §c❤");


        Data.getScoreboardController().getFastBoardMap().put(player.getUniqueId(), new FastBoard(player));
        Data.getScoreboardController().getFastBoardMap().values().forEach(all -> {
            Data.getScoreboardController().updateScoreboard(all);
            Data.getTablistController().updateTablist(all.getPlayer());
            Data.getPlayerUtil().sendPlayerListTab(all.getPlayer(), header, Data.getFooter().replaceAll("%nl%", "\n"));
        });

        IntStream.range(0, 200).forEach(i -> player.sendMessage("§8┊"));


        long timeDiff = Duration.between(ZonedDateTime.now(), ZonedDateTime.now().toLocalDate().atStartOfDay(ZonedDateTime.now().getZone())).toMillis();
        if (timeDiff < 0) {
            timeDiff += 86400000;
        }

        long finalTimeDiff = timeDiff;
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("");
                Data.getMessageUtil().sendMessageWithCstmPrefix(player, "§8┃ §6§l● §8┃ ", "§7Willkommen auf §e§lSKYSOUP.EU§8§8, §6" + player.getName() + "§8! §c❤");
                Data.getMessageUtil().sendMessageWithCstmPrefix(player, "", "");
                if (player.hasPlayedBefore())
                    Data.getMessageUtil().sendMessageWithCstmPrefix(player, "§8┃ §e§l● §8┃ ", "§7Du warst insgesamt §e" + (formattedTime.contains("Lädt") ? "0s" : formattedTime) + " §7offline§8.");

                Data.getMessageUtil().sendMessageWithCstmPrefix(player, "§8┃ §f§l● §8┃ ", (!Data.getRewardController().getStatusFromPlayer(player, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) ? "§7Hole §ejetzt §7deine tägliche Belohnung ab! §8(§e/rewards§8)" : "§cWarte noch §7" + ValueUtil.timeToString(finalTimeDiff, false, false) + " §cfür deine tägliche Belohnung!"));
                player.sendMessage("");
            }
        }.runTaskLater(SkyPvP.getINSTANCE(), 10L);


    }


    private String getColoredName(final String name, int coloramount) {

        final String[] colorWays = new String[]{"§6§l", "§e§l"};
        final String[] splitted = name.split("");
        String finished = "";
        int i = 0;
        int f = 0;
        coloramount = coloramount - 1;

        for (String letter : splitted) {
            finished = finished + colorWays[i] + letter;
            if (f == coloramount) {
                i++;
                if (i >= colorWays.length) {
                    i = 0;
                }
                f = 0;
            } else {
                f++;
            }
        }
        return finished;
    }
}
