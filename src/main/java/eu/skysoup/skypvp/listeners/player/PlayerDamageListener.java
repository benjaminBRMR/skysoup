package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.data.Data;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created: 13.02.2023 23:38
 *
 * @author thvf
 */
public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onFallDamage(final EntityDamageEvent event) {
        final Entity entity = event.getEntity();

        if (entity instanceof Player) {
            final Player player = (Player) entity;

            if (player.getWorld().getName().equals("SoupPlots-V2") && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }


    }


    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent event) {

        final Entity entity = event.getEntity();

        if (entity instanceof Player) {

            final Player player = (Player) entity;


            if (!Data.getToggleController().isToggled(ToggleController.Types.PVP)) {
                event.setCancelled(true);
                Data.getMessageUtil().sendMessage(player, "§cAktuell ist PvP deaktiviert!");
                return;
            }
        }

        // FIXME: 14.02.2023

        /*
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            final Player playerOne = (Player) event.getEntity();
            final Player playerTwo = (Player) event.getDamager();


            if(playerOne.getWorld() == null || playerTwo.getWorld() == null) return;
            if(playerOne.getWorld().getName().equals(Data.getSkyPvP().getName()) || playerTwo.getWorld().getName().equals(Data.getSkyPvP().getName())) return;

            Data.getCombatController().beginOrUpdate(playerOne, playerTwo);
        }

         */
    }
}
