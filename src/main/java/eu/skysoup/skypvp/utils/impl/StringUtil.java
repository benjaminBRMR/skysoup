package eu.skysoup.skypvp.utils.impl;

import com.google.common.primitives.Chars;
import net.md_5.bungee.api.ChatColor;

import java.util.Collections;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 07.01.2023
 *
 * @author thvf
 */
public class StringUtil {

    public static boolean isValid(final String code) {
        return code.matches("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-]*");
    }

    public static String getCenteredMessage(String message) {
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

    public static boolean containsUnicodes(final String string) {
        String message = string.replaceAll("[^a-zA-Z0-9 +*~#'!\\\"§$%&()=?´`^@²³{\\[\\]}\\ß?#'-_.,;:ÜÄÖüäö<>|]", "");
        return string.equals(message);
    }

    public static String capitalize(final String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String shuffle(final String text) {
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int) (Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static String schuffleEasy(final String text) {
        final List<Character> chars = Chars.asList(text.toCharArray());
        Collections.shuffle(chars);
        return new String(Chars.toArray(chars));
    }
}

