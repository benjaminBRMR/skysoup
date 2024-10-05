package eu.skysoup.skypvp.utils;

import eu.skysoup.skypvp.controller.PrefixController;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created: 23.01.2023 11:29
 *
 * @author thvf
 */
public class PermissionUtil {

    /**
     * simply checks if the player has the permission
     * if not the noperm message is sent
     *
     * @param player
     * @param permission
     * @param send
     * @return
     */
    public boolean hasPermission(final Player player, final String permission, final boolean send) {
        if (!player.hasPermission(permission) && send) {
            player.sendMessage(PrefixController.getSkyPvP() + "§cDu hast dazu keine Rechte! §8(§7§o" + permission + "§8)");
            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
        }
        return player.hasPermission(permission);
    }
}
