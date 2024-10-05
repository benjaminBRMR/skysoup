package eu.skysoup.skypvp.controller.other;

import com.avaje.ebean.validation.NotNull;
import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.data.Data;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created: 13.02.2023 23:26
 *
 * @author thvf
 */
public class CombatController {

    Player playerOne;
    Player playerTwo;
    @Getter
    public final HashMap<Player, Long> combatLog = new HashMap<>();


    public CombatController() {
        new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimerAsynchronously(SkyPvP.getINSTANCE(), 20L, 20L);
    }


    public void beginOrUpdate(final Player playerone, final Player playertwo) {
        this.playerOne = playerone;
        this.playerTwo = playertwo;

        if (combatLog.containsKey(playerone)) {
            combatLog.replace(playerone, 13L);
        } else {
            combatLog.put(playerone, 13L);
            Data.getMessageUtil().sendMessage(playerone, "§7Du bist jetzt mit §e" + playertwo.getName() + " §7im Kampf§8.");

        }
        if (combatLog.containsKey(playertwo)) {
            combatLog.replace(playertwo, 13L);
        } else {
            combatLog.put(playertwo, 13L);
            Data.getMessageUtil().sendMessage(playertwo, "§7Du bist jetzt mit §e" + playerone.getName() + " §7im Kampf§8.");

        }

    }

    @NotNull
    public void tick() {
        if (!combatLog.isEmpty()) {
            for (Player player : combatLog.keySet()) {
                if (player == null) return;

                if (combatLog.get(player) - 1 > 0) {
                    combatLog.replace(player, combatLog.get(player) - 1);
                    Data.getPlayerUtil().sendActionbar(player, "§8(§4§l⚔§8) §7Du bist noch §c" + combatLog.get(player) + " §7Sekunden im Kampf§8!");
                } else {
                    combatLog.remove(player);
                    Data.getMessageUtil().sendMessage(player, "§cDu bist nun nicht mehr im Kampf!");
                }
            }
        }
    }


}
