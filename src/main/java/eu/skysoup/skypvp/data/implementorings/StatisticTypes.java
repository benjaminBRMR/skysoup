package eu.skysoup.skypvp.data.implementorings;

import lombok.Getter;

/**
 * Created: 23.01.2023 00:53
 *
 * @author thvf
 */
public enum StatisticTypes {


    MONEY(250),
    SPENTMONEYINSHOP(0),
    SPENTCASINOCHIPS(0),
    BOUGHTITEMSINSHOP(0),
    KILLS(0),
    DEATHS(0),
    VOTES(0),
    PLAYTIME(0),
    KILLSTREAK(0),
    TROPHYS(0),
    FISHINGLEVEL(0),
    CASINOCHIPS(0),
    SPENTTROPHIES(0),
    BOUNTY(0),
    SPENTBOUNTYS(0),
    OFFLINESINCE(0),
    GOLD(0),
    ROHGOLD(0),
    MONEYARBEITER(0),
    REGISTERED(System.currentTimeMillis());

    @Getter
    final long defaultV;

    StatisticTypes(final long defaultV) {
        this.defaultV = defaultV;
    }
}
