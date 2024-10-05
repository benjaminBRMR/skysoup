package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.RankTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.TempCooldown;
import eu.skysoup.skypvp.utils.impl.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created: 23.01.2023 13:33
 *
 * @author thvf
 */
public class AsyncChatListener implements Listener {

    private String getSentWort(String str) {

        for (String s : Data.getChatController().getBlacklistedWords()) {
            if (str.contains(s)) {
                return s;
            }
        }
        return "unknown";
    }

    //

    private final TempCooldown cooldown = new TempCooldown();


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAsyncChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage().replaceAll("%", "%%");
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final RankTypes pvp_rank = RankTypes.getRankFromKills((int) userController.getStatisticByType(StatisticTypes.KILLS));


        switch (Data.getPlayerUtil().getRang(player).toLowerCase()) {
            case "owner":
                event.setFormat("§4§lOwner §8× §4" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "srdev":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §b§lSrDeveloper §8× §b" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "dev":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §b§lDeveloper §8× §b" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "srmod":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §c§lSr-Mod §8× §4" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "srcontent":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §6§lSr-Content §8× §6" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "mod":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §3§lModerator §8× §3" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "content":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §6§lContent §8× §6" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "testmod":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §3§lTest-Mod §8× §3" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "supporter":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §a§lSupporter §8× §a" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "jrcontent":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §6§lJrContent §8× §6" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "azubi":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §9§lAzubi §8× §9" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "soup":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §eSoup §8× §e" + player.getName() + " §8» §7" + message.replaceAll("&", "§"));
                break;
            case "superia":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §dSuperia §8× §d" + player.getName() + " §8» §7" + message);
                break;
            case "onyx":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §bOnyx §8× §b" + player.getName() + " §8» §7" + message);
                break;
            case "saphir":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §6Saphir §8× §6" + player.getName() + " §8» §7" + message);
                break;
            case "azur":
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §5Azur §8× §5" + player.getName() + " §8» §7" + message);
                break;
            default:
                event.setFormat(pvp_rank.getValue() + "§r §8┃ §a" + player.getName() + " §8» §7" + message);
                break;
        }

        if (Data.isGlobalmute() && !player.hasPermission(Permissions.TEAM.getPermission())) {
            event.setCancelled(true);
            Data.getMessageUtil().sendMessage(player, "§cAktuell ist der Globalmute aktiviert.");
            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 15, 0.1F);
            return;
        }

        if (Data.isSlowchat() && !player.hasPermission(Permissions.TEAM.getPermission())) {
            if (!cooldown.isDone(player.getUniqueId())) {
                event.setCancelled(true);
                Data.getMessageUtil().sendMessage(player, "§cDu kannst nur alle 5 Sekunden schreiben.");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 15, 0.1F);
                return;
            }
            cooldown.addPlayerToCooldown(player.getUniqueId(), 5);
            return;
        }

        for (String str : Data.getChatController().getBlacklistedWords()) {
            if (!player.hasPermission(Permissions.ADMIN.getPermission()) && message.toLowerCase().contains(str.toLowerCase())) {
                event.setCancelled(true);
                Data.getMessageUtil().sendMessage(player, "§cDeine Nachricht wurde blockiert.");
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 15, 0.1F);

                Data.getMessageUtil().sendMessageWithCstmPrefixToTeam(PrefixController.getChatFilter(), "§eNachricht §7von §e" + player.getName() + " §7blockiert§8.");
                Data.getMessageUtil().sendMessageWithCstmPrefixToTeam(PrefixController.getChatFilter(), "§f" + message.trim());
                Utils.playSoundToTeam(Sound.VILLAGER_NO, 1, 10);
            }
        }
    }
}
