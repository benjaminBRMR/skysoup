package eu.skysoup.skypvp.casino;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.UserValues;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Created: 07.02.2023 14:44
 *
 * @author thvf
 */
public class CasinoController {

    Player player;
    HashMap<UUID, Long> inCasinoSince = new LinkedHashMap<>();

    public CasinoController getUser(final Player player) {
        this.player = player;
        return this;
    }

    public void loginToCasino() {
        inCasinoSince.put(player.getUniqueId(), System.currentTimeMillis());
        player.sendMessage("");
        player.sendMessage(Data.getMessageUtil().getCenteredMessage("§8§m--§7§m--§f§m--§r §6§lCASINO §f§m--§7§m--§8§m--§r"));
        player.sendMessage("");
        Data.getMessageUtil().sendMessage(player, "§7Herzlich Willkommen im §7Casino§8, §e" + player.getName());
        Data.getMessageUtil().sendMessage(player, "§7Aktuell befinden sich §e" + Bukkit.getWorld("Casino").getPlayers().size() + " §7Spieler im Casino§8.");
        player.sendMessage("");
        player.sendMessage(Data.getMessageUtil().getCenteredMessage("§8§m--§7§m--§f§m--§r §6§lCASINO §f§m--§7§m--§8§m--§r"));
        player.sendMessage("");


    }

    public void logoutFromCasino() {

        if (!inCasinoSince.containsKey(player.getUniqueId())) return;

        long casinoTime = (System.currentTimeMillis() - inCasinoSince.get(player.getUniqueId()));
        String formattedTime = ValueUtil.formatTime(casinoTime);

        player.sendMessage("");
        player.sendMessage(Data.getMessageUtil().getCenteredMessage("§8§m--§7§m--§f§m--§r §6§lCASINO §f§m--§7§m--§8§m--§r"));
        player.sendMessage("");
        Data.getMessageUtil().sendMessage(player, "§cDu hast das Casino verlassen!");
        Data.getMessageUtil().sendMessage(player, "§7Du warst insgesamt §e" + formattedTime + " §7im Casino§8.");
        player.sendMessage("");
        player.sendMessage(Data.getMessageUtil().getCenteredMessage("§8§m--§7§m--§f§m--§r §6§lCASINO §f§m--§7§m--§8§m--§r"));
        player.sendMessage("");

        inCasinoSince.remove(player.getUniqueId());
    }

    public String getTimeInCasino() {
        long casinoTime = (System.currentTimeMillis() - inCasinoSince.get(player.getUniqueId()));
        return ValueUtil.formatTime(casinoTime);
    }

    public boolean isLoggedIn() {
        return inCasinoSince.containsKey(player.getUniqueId());
    }

    public boolean hasPlayerCasinoPass() {
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        return userController.getCustomValue(UserValues.CASINOPASS) != null && !userController.getCustomValue(UserValues.CASINOPASS).equals(false);
    }

    public void setPlayerCasinoPass() {
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        userController.setCustomValue(UserValues.CASINOPASS, !hasPlayerCasinoPass());
    }


}
