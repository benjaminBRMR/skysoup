package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.impl.DefaultFontInfo;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Created: 08.02.2023 13:32
 *
 * @author thvf
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onServerListPing(final ServerListPingEvent event) {

        final String line1 = Data.getMotdController().getLine1().replaceAll("&", "§").replaceAll("#", "&");
        final String line2 = Data.getMotdController().getLine2().replaceAll("&", "§").replaceAll("#", "&");

        event.setMotd((line1.contains("%centered%") ? getCenteredMessage(line1.replaceAll("%centered%", "")) : line1) + "§r\n§r" + (line2.contains("%centered%") ? getCenteredMessage(line2.replaceAll("%centered%", "")) : line2));

    }

    private String getCenteredMessage(String message) {
        if (message == null || message.equals("")) return "";
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
                continue;
            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        return (sb.toString() + message);
    }
}
