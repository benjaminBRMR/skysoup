package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.commands.player.CommandCasino;
import eu.skysoup.skypvp.commands.player.CommandSpawn;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.teleport.TeleportCooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created: 23.01.2023 14:10
 *
 * @author thvf
 */
public class MoveListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final CasinoController casinoController = Data.getCasinoController().getUser(player);

        if (player.getLocation().getWorld().getName().equals("skypvp-3") && player.getLocation().getY() <= Data.getConfigController().getLong("spawnteleportheight")) {
            if (Data.getLocation().existsLocation("pvp")) player.teleport(Data.getLocation().getLocation("pvp"));
            Data.getPlayerUtil().sendTitle(player, 0, 25, 8, "§8§l┃ §c§lPVP §8§l┃", "§a§oPvP ist nun aktiviert!");
            return;
        }


        if (event.getFrom().getBlockX() != event.getTo().getBlockX()
                || event.getFrom().getBlockY() != event.getTo().getBlockY()
                || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
            TeleportCooldown cooldown = Data.getTeleportCooldowenController().get(CommandSpawn.getTELEPORT_COUNTDOWN(), player);
            TeleportCooldown cooldown2 = Data.getTeleportCooldowenController().get(CommandCasino.getTELEPORT_COUNTDOWN(), player);
            if (cooldown != null) cooldown.cancel();
            if (cooldown2 != null) cooldown2.cancel();

            return;
        }


    }
}
