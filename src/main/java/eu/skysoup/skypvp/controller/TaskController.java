package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.impl.StringUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 31.01.2023 20:38
 *
 * @author thvf
 */
public class TaskController {

    public enum Lines {
        LINE1,
        LINE2
    }


    final HashMap<Lines, String> map = new HashMap<>();


    public String getMapValue(final Lines line) {

        if (!map.containsKey(line)) return "§8§oLoading...";
        return map.get(line);
    }

    public String initScoreboardMessages() {

        final String line1Nachricht1 = "Unser Discord";
        final String line1Nachricht2 = ".gg/Wpx5Fx69t5";

        final String line2Nachricht1 = "Bist du ein Krieger?";
        final String line2Nachricht2 = "Nutze /pvpi";

        final String line3Nachricht1 = "Was kannst du?";
        final String line3Nachricht2 = "Nutze /ranginfo";


        final int interval = Data.getRunnableUtil().getTimeFromMinutes(5);
        final AtomicInteger taskID = new AtomicInteger();

        Bukkit.getScheduler().runTaskTimer(SkyPvP.getINSTANCE(), () -> {
            taskID.getAndIncrement();

            switch (taskID.get()) {

                case 1:
                    map.put(Lines.LINE1, line1Nachricht1);
                    map.put(Lines.LINE2, line1Nachricht2);
                    break;

                case 2:
                    map.put(Lines.LINE1, line2Nachricht1);
                    map.put(Lines.LINE2, line2Nachricht2);
                    break;

                case 3:
                    map.put(Lines.LINE1, line3Nachricht1);
                    map.put(Lines.LINE2, line3Nachricht2);
                    break;
            }


            if (taskID.get() == 3) taskID.set(0);

        }, 0, interval);

        return "§8§oLoading...";
    }

    final String headerLine = StringUtil.getCenteredMessage("§7§m----§r §8§m----§r §r%s §7§m----§r §8§m----§r");


    @Getter
    public BukkitTask broadcastTask;

    public void beginBroadcast() {

        AtomicInteger taskID = new AtomicInteger(1);

        broadcastTask = new BukkitRunnable() {
            @Override
            public void run() {
                taskID.getAndIncrement();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    switch (taskID.get()) {
                        case 1:
                            all.sendMessage(String.format(headerLine, "§a●§8●●●●§r"));
                            all.sendMessage("§r");
                            all.sendMessage(StringUtil.getCenteredMessage("§7Du willst §c§nuns§7 unterstützen und §6tolle"));
                            all.sendMessage(StringUtil.getCenteredMessage("§7Belohnungen erhalten§8? §aVote mit §8/§2vote§8!"));
                            all.sendMessage("§r");
                            all.sendMessage(String.format(headerLine, "§a●§8●●●●§r"));
                            all.playSound(all.getLocation(), Sound.NOTE_PLING, 20, 5);
                            break;
                        case 2:
                            all.sendMessage(String.format(headerLine, "§8●§a●§8●●●§r"));
                            all.sendMessage("§r");
                            all.sendMessage(StringUtil.getCenteredMessage("§7Hole dir §ddeine täglichen §7Belohnungen"));
                            all.sendMessage(StringUtil.getCenteredMessage("§7ganz einfach mit §8/§5rewards §7ab§8!"));
                            all.sendMessage("§r");
                            all.sendMessage(String.format(headerLine, "§8●§a●§8●●●§r"));
                            all.playSound(all.getLocation(), Sound.NOTE_PLING, 20, 5);
                            break;

                    }
                    if (taskID.get() > 2) taskID.set(0);
                }
            }
        }.runTaskTimer(SkyPvP.getINSTANCE(), 0L, Data.getRunnableUtil().getTimeFromMinutes((int) Data.getConfigController().getLong("broadcasttime")));
    }
}
