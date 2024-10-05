package eu.skysoup.skypvp.data.implementorings;

import lombok.Getter;

/**
 * JavaDoc this file!
 * Created: 09.01.2023
 *
 * @author thvf
 */


public enum GutscheinTypes {

    TOKEN(null),
    MERGE(null),
    ENDERKISTE(null),
    TEAMSPEAK(null),
    OTHER(null),
    PLOTMOVE(null),
    PVP(null),
    CLAN("§6§LFARB§e§lIGE§f§lR CL§b§lAN§3§lTAG");


    @Getter
    final String prefix;

    GutscheinTypes(final String prefix) {
        this.prefix = prefix;
    }

}
