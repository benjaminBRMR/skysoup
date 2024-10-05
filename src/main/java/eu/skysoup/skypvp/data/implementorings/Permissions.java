package eu.skysoup.skypvp.data.implementorings;

import lombok.Getter;

/**
 * Created: 23.01.2023 13:52
 *
 * @author thvf
 */
public enum Permissions {

    DEFAULT("skysoup.default"),
    AZUR("skysoup.azur"),
    SAPHIR("skysoup.saphir"),
    ONYX("skysoup.onyx"),
    SUPERIA("skysoup.superia"),
    SOUP("skysoup.soup"),


    WHITELIST("skysoup.whitelist"),


    ADMIN("skysoup.admin"),
    CW("skysoup.cw"),
    MSGSPY("skysoup.msgspy"),
    JOINMSG("skysoup.joinmsg"),
    GIVEALL("skysoup.giveall"),
    QUITMSG("skysoup.quitmsg"),
    GLOBALMUTE("skysoup.globalmute"),
    SLOWCHAT("skysoup.slowchat"),
    FLY("skysoup.fly"),
    FLYOTHER("skysoup.fly.other"),
    FEED("skysoup.feed"),
    FEEDOTHER("skysoup.feed.other"),
    FEEDALL("skysoup.feed.all"),
    CLEAR("skysoup.clear"),
    CLEAROTHER("skysoup.clear.other"),
    HEAL("skysoup.heal"),
    HEALOTHER("skysoup.heal.other"),
    HEALALL("skysoup.heal.all"),
    HAT("skysoup.hat"),
    RENAME("skysoup.rename"),
    TOGGLE("skysoup.toggle"),
    VANISH("skysoup.vanish"),
    VANISHOTHER("skysoup.vanish.other"),
    TPS("skysoup.tps"),
    INVSEE("skysoup.invsee"),
    GAMEMODE("skysoup.gm"),
    TP("skysoup.tp"),
    TPALL("skysoup.tpall"),
    TPAAALL("skysoup.tpaall"),
    LAGERPLATZVIEWOTHER("skysoup.lp.other"),
    LAGERPLATZCLICK("skysoup.lp.click"),
    TEAM("skysoup.team");

    @Getter
    final String permission;

    Permissions(final String permission) {
        this.permission = permission;
    }
}
