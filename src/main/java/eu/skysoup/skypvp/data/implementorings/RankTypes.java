package eu.skysoup.skypvp.data.implementorings;

/**
 * Created: 29.01.2023
 *
 * @author benni
 */
public enum RankTypes {

    /*
        1. Knabe - 0
        2. Adis I - 25
        3. Adis II - 50
        4. Adis III - 75
        5. Adis IV - 100
        6. Vance I - 125
        7. Vance II - 150
        8. Vance III - 175
        9. Vance IV - 200
        10. Herone I - 250
        11. Herone II - 300
        12. Herone III - 350
        13. Herone IV - 400
        14. Scholar I - 450
        15. Scholar II - 500
        16. Scholar III - 550
        17. Scholar IV - 600
        18. Saint I - 650
        19. Saint II - 700
        20. Saint III - 750
        21. Saint IV - 800
        22. Rouge - 1.000
     */

    KNABE("§8§LKNABE", 0, 1),
    ADIS_I("§7§lAD§f§LIS §7I", 25, 2),
    ADIS_II("§7§lAD§f§LIS §7II", 50, 3),
    ADIS_III("§7§lAD§f§LIS §7III", 75, 4),
    ADIS_IV("§7§lAD§f§LIS §7IV", 100, 5),
    VANCE_I("§a§LVAN§2§LCE §7I", 125, 6),
    VANCE_II("§a§LVAN§2§LCE §7II", 150, 7),
    VANCE_III("§a§LVAN§2§LCE §7III", 175, 8),
    VANCE_IV("§a§LVAN§2§LCE §7IV", 200, 9),
    HERONE_I("§e§lHE§6§LRO§e§LNE §7I", 250, 10),
    HERONE_II("§e§lHE§6§LRO§e§LNE §7II", 300, 11),
    HERONE_III("§e§lHE§6§LRO§e§LNE §7III", 350, 12),
    HERONE_IV("§e§lHE§6§LRO§e§LNE §7IV", 400, 13),
    SCHOLAR_I("§3§LSCH§B§LOL§f§LAR §7I", 450, 14),
    SCHOLAR_II("§3§LSCH§B§LOL§f§LAR §7II", 500, 15),
    SCHOLAR_III("§3§LSCH§B§LOL§f§LAR §7III", 550, 16),
    SCHOLAR_IV("§3§LSCH§B§LOL§f§LAR §7IV", 600, 17),
    SAINT_I("§e§k.§6§LSAINT§e§K.§7 I", 650, 18),
    SAINT_II("§e§k.§6§LSAINT§e§K.§7 II", 700, 19),
    SAINT_III("§e§k.§6§LSAINT§e§K.§7 III", 750, 20),
    SAINT_IV("§e§k.§6§LSAINT§e§K.§7 IV", 800, 21),
    ROGUE("§c§k.§4§L§nR§c§LOUG§4§L§nE§c§k.", 1000, 22);

    private String value;
    private int min;
    private int id;

    RankTypes(final String value, final int min, final int id) {
        this.value = value;
        this.min = min;
        this.id = id;
    }

    public static RankTypes getUserRankFromID(final int id) {
        for (final RankTypes rank : values()) {
            if (rank.getId() == id) {
                return rank;
            }
        }
        return null;
    }

    public static RankTypes getRankFromKills(final int kills) {
        RankTypes value = RankTypes.KNABE;
        for (final RankTypes rank : values()) {
            if (kills >= rank.getMin()) {
                value = rank;
            }
        }
        return value;
    }

    public String getValue() {
        return this.value;
    }

    public int getMin() {
        return this.min;
    }

    public int getId() {
        return this.id;
    }
}
