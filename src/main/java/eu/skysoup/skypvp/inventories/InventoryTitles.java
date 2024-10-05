package eu.skysoup.skypvp.inventories;

import lombok.Getter;

/**
 * Created: 05.02.2023 21:39
 *
 * @author thvf
 */
public enum InventoryTitles {

    EXAMPLE("§eMONKEE"),
    TOP("§8┃ §6§lTOP §8: §7Inventar"),
    MENU("§8┃ §e§lMENU §8: §7Inventar"),
    RAND("§8┃ §d§lRAND §8: §7Inventar"),
    WAND("§8┃ §d§lWAND §8: §7Inventar"),
    ENCHANTMENTTABLE("§8┃ §5§lENCHANT §8: §7Inventar"),
    SHOPMAIN("§8┃ §f§lSHOP §8: §7Inventar"),
    SHOPBLOCK("§8┃ §f§lSHOP §8: §7Blöcke"),
    SHOPBLOCK2("§8┃ §f§lSHOP §8: §7Blöcke 2"),
    SHOPEQUIP("§8┃ §f§lSHOP §8: §7Equipment"),
    SHOPVERSCH("§8┃ §f§lSHOP §8: §7Versch."),
    SHOPBES("§8┃ §f§lSHOP §8: §7Besonder."),
    SHOPTOOLS("§8┃ §f§lSHOP §8: §7Tools"),
    PVPRANK("§8┃ §4§lPVP-RANG §8: §7Inventar"),
    TROPHY("§8┃ §6§lTROPHÄEN §8: §7Inventar"),
    BOUNTY("§8┃ §2§lBOUNTY §8: §7Aufträge"),
    GUTSCHEIN("§8┃ §7§lGUTSCHEIN §8: §7Inventar"),
    SETTINGS("§8┃ §3§lSETTINGS §8: §7Inventar"),
    CLANTAG("§8┃ §f§lCL§b§lANTAG §8: §7Inventar"),
    CLAN("§8┃ §b§lCLAN §8: §7Inventar"),
    CLAN_MITGLIEDER("§8┃ §b§lCLAN §8: §7Mitglieder"),
    ACCEPT("§8┃ §a§lBESTÄTIGUNG §8: §7Inventar"),
    KIT("§8┃ §d§lKIT §8: §7Inventar"),
    REWARD("§8┃ §5§lREWARD §8: §7Inventar"),
    CASINOMAIN("§8┃ §6§lCAS§e§lINO §8: §7Inventar"),
    TRASH("§8┃ §4§lMÜLLEIMER §8: §7Inventar"),
    MONEYMAKERBURN("§8┃ §e§lMONEYMAKER §8: §7Inventar"),
    MONEYMAKERWORKER("§8┃ §e§lMONEYMAKER §8: §7Arbeiter"),
    TROPHYMARKT("§8┃ §6§lTROPHÄEN §8: §7Markt");

    @Getter
    final String name;


    InventoryTitles(final String name) {
        this.name = name;
    }
}
