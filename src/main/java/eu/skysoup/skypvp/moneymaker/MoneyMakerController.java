package eu.skysoup.skypvp.moneymaker;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.Config;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created: 26.02.2023 15:17
 *
 * @author thvf
 */
public class MoneyMakerController {

    UUID uuid;
    UserController userController;
    Config config;

    // ARBEITER CODEN MIT 3 LEVELN 1,2,3

    // ARBEITER Lvl 1. = 1 Rohgold pro Stunde
    // ARBEITER Lvl 2. = 1 Rohgold pro 45 Minuten
    // ARBEITER Lvl 3. = 1 Rohgold pro 30 Minuten

    // Normales Schmelzen im Inventar anklicken | 15 Rohgold = 1 Gold

    public MoneyMakerController getUserbyUUID(final UUID uuid) {
        this.uuid = uuid;
        this.userController = new UserController().getUserByUUID(uuid);
        this.config = new Config("plugins/SkySoup/MoneyMaker/", this.uuid.toString());
        return this;
    }


    public void createArbeiter() {
        this.config.getConfig().set(".arbeiter", true);
        this.config.getConfig().set(".arbeiter.level", 1);
        this.config.saveConfig();
    }

    public long getArbeiterLevel() {
        if (!hasArbeiter()) return 0;
        return this.config.getConfig().getLong(".arbeiter.level");
    }

    public boolean hasArbeiter() {
        return this.config.getConfig().getBoolean(".arbeiter");
    }


    public boolean isInVorgang() {

        if (Data.getInventoryMaps().schmelzTask.get(uuid) == null) return false;
        if (Data.getInventoryMaps().schmelzProgress.get(uuid) == null) return false;

        return true;
    }

    public void startSchmelzing(final long rohGold, final long dauerSekunden) {

        Data.getInventoryMaps().schmelzProgress.put(uuid, 0);
        final Player player = Bukkit.getPlayer(uuid);

        userController.setStatisticFromUser(StatisticTypes.ROHGOLD, userController.getStatisticByType(StatisticTypes.ROHGOLD) - rohGold);
        Data.getMessageUtil().sendMessage(player, "§7Der §eSchmelzprozess §7wurde §egestartet!");
        Data.getMessageUtil().sendMessage(player, "§8└ §cHabe einen Moment Geduld...");
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 5, 0.1F);


        Data.getInventoryMaps().schmelzTask.put(uuid, new BukkitRunnable() {

            final Runnable whenDone = () -> {
                Data.getMessageUtil().sendMessage(player, "§7Der §eSchmelzprozess §7ist §eabgeschlossen!");
                Data.getMessageUtil().sendMessage(player, "§8└ §7Geschmolzenes Gold§8: §61");
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 5, 0.1F);

                userController.setStatisticFromUser(StatisticTypes.GOLD, userController.getStatisticByType(StatisticTypes.GOLD) + 1);
            };

            final Runnable whileRunning = () -> {

                int progress = Data.getInventoryMaps().schmelzProgress.getOrDefault(uuid, 0);
                if (progress < dauerSekunden) {
                    Data.getInventoryMaps().schmelzProgress.replace(uuid, progress + 1);
                    Data.getPlayerUtil().sendActionbar(player, "§8» §eSchmelzprozess§8: " + ValueUtil.showProgressBar(Data.getInventoryMaps().schmelzProgress.get(uuid), dauerSekunden) + " §8┃ §7" + (dauerSekunden - progress) + " §7Sek§8.");
                } else {
                    Data.getInventoryMaps().schmelzProgress.remove(uuid);
                    Data.getInventoryMaps().schmelzTask.remove(uuid);
                    whenDone.run();
                    cancel();
                }
                if (progress > (dauerSekunden + 2)) {
                    player.sendMessage("§cDer Schmelzprozess wurde abgebrochen, da er zu lange dauerte!");
                    Data.getInventoryMaps().schmelzProgress.remove(uuid);
                    Data.getInventoryMaps().schmelzTask.remove(uuid);
                    cancel();
                }
            };

            @Override
            public void run() {
                whileRunning.run();
            }
        }.runTaskTimerAsynchronously(SkyPvP.getINSTANCE(), 0L, 20L));


    }


}
