package eu.skysoup.skypvp.utils;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import eu.skysoup.skypvp.utils.impl.DefaultFontInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created: 23.01.2023 11:18
 *
 * @author thvf
 */
public class MessageUtil {


    /**
     * sends the message with default prefix
     *
     * @param player
     * @param message
     */
    public void sendMessage(final Player player, final String message) {
        player.sendMessage(PrefixController.getSkyPvP() + message);
    }

    /**
     * sends the message to everyone
     * with the default prefix
     *
     * @param message
     */
    public void sendMessageToAll(final String message) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            all.sendMessage(PrefixController.getSkyPvP() + message);
        });
    }

    /**
     * sends the mssage with the
     * parameter prefix to everyone
     *
     * @param prefix
     * @param message
     */
    public void sendMessageWithCstmPrefixToAll(final String prefix, final String message) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            all.sendMessage(prefix + message);
        });
    }

    /**
     * sends the message with the parameter prefix
     *
     * @param player
     * @param prefix
     * @param message
     */
    public void sendMessageWithCstmPrefix(final Player player, final String prefix, final String message) {
        player.sendMessage(prefix + message);
    }

    /**
     * sends a clickable
     * message to the player
     *
     * @param player
     * @param message
     * @param hoverText
     * @param command
     */
    public void sendClickableMessage(final Player player, final String message, final String hoverText, final String command) {
        TextComponent text = new TextComponent(message);
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        player.spigot().sendMessage(text);
    }

    /**
     * sends a message to the
     * player he can only hover over
     *
     * @param player
     * @param message
     * @param hoverText
     */
    public void sendClickableMessageOnlyText(final Player player, final String message, final String hoverText) {
        TextComponent text = new TextComponent(message);
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
        player.spigot().sendMessage(text);
    }

    /**
     * sends a clickable
     * message to the player
     *
     * @param player
     * @param message
     * @param hoverText
     * @param command
     */
    public void sendClickableMessageCommand(final Player player, final String message, final String hoverText, final String command) {
        TextComponent text = new TextComponent(PrefixController.getSkyPvP() + message);
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(text);
    }


    /**
     * sends the message to every
     * player in a specific world
     *
     * @param world
     * @param string
     */
    public void sendMessageToAllPlayersInWorld(final World world, final String string) {

        world.getPlayers().forEach(all -> {
            sendMessage(all, string);
        });
    }

    /**
     * sends the message only to the players
     * that have the team permission
     *
     * @param message
     */
    public void sendMessageToTeam(final String message, final boolean actionbar) {

        if (actionbar) {
            Bukkit.getOnlinePlayers().forEach(all -> {
                if (!all.hasPermission(Permissions.TEAM.getPermission())) return;
                Data.getPlayerUtil().sendActionbar(all, message);

            });

        } else {

            Bukkit.getOnlinePlayers().forEach(all -> {
                if (!all.hasPermission(Permissions.TEAM.getPermission())) return;
                all.sendMessage(PrefixController.getSkyPvP() + message);
            });
        }
    }

    public void sendMessageToAdmin(final String message) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            if (!all.hasPermission(Permissions.ADMIN.getPermission())) return;
            all.sendMessage(PrefixController.getSkyPvP() + message);
        });
    }

    /**
     * sends the message only to the players
     * that have the team permission with the parameter prefix
     *
     * @param message
     */
    public void sendMessageWithCstmPrefixToTeam(final String prefix, final String message) {

        Bukkit.getOnlinePlayers().forEach(all -> {
            if (!all.hasPermission(Permissions.TEAM.getPermission())) return;
            all.sendMessage(prefix + message);
        });
    }

    /**
     * send the syntax
     *
     * @param player
     * @param strings
     */
    public void sendSyntax(final Player player, final String... strings) {

        sendMessage(player, "§cFalscher Syntax, versuche es erneut!");
        for (String s : strings) {
            player.sendMessage(PrefixController.getSkyPvP() + s);
        }

    }

    /**
     * send the message to everyone that
     * have the specified setting activated
     *
     * @param settingsType
     * @param prefix
     * @param message
     */
    public void sendMessageToAllPlayersWithSetting(final SettingTypes settingsType, final String prefix, final String message) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            final UserController userController = new UserController().getUserByUUID(all.getUniqueId());

            if (!all.hasPermission(settingsType.getPermission())) continue;
            if (!userController.getSettingFromUser(settingsType)) continue;

            all.sendMessage(prefix + message);
        }
    }


    public String getCenteredMessage(String message) {
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
