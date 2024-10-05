package eu.skysoup.skypvp.data.implementorings;

import lombok.Getter;

/**
 * Created: 05.02.2023 13:18
 *
 * @author thvf
 */
public enum ClantagTypes {


    DEFAULT("§7%CLAN%"),
    COLOR1("§a%CLAN%"),
    COLOR2("§b%CLAN%"),
    COLOR3("§c%CLAN%"),
    COLOR4("§6%CLAN%"),
    COLOR5("§3%CLAN%");


    @Getter
    final String name;

    ClantagTypes(final String name) {
        this.name = name;
    }
}
