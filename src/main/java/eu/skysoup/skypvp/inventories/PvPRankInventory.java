package eu.skysoup.skypvp.inventories;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.implementorings.RankTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.inventory.SingletonInventory;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

/**
 * Created: 30.01.2023
 *
 * @author benni
 */
public class PvPRankInventory extends SingletonInventory {


    public PvPRankInventory(final Player player) {
        super(InventoryTitles.PVPRANK.getName(), Rows.CHEST_ROW_6, player);

        String KNABE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2JiMWVlZjk4ZWI4ODgzNGY1ZGFmN2VlYjVlM2JjYjc5M2RlY2ExNzE1OTY0MWJlNjRmNWQ1ODM3YWRiMjBjNSJ9fX0=";

        String ADIS = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhN2UyODU4ZTZmZjdhNmIwZDFjODgzNGZlZjJhMTk2YmY4MWY1YWRkZTM2NTA0YjhjNTkxY2MwYTNmODA5YiJ9fX0=";

        String VANCE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QzM2FjNzUzYjkyOWZmMWFlNTgzMjE3NjUwZmRjNzU1MTBlNmY4MjE4NjNiZWMwZmE1MTZlZDQ1ZjZlZThlMCJ9fX0=";

        String HERONE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWY1NjlhYWRiMTEwZWVkNDI0OTRiMzgyMTcwMDJhMzAyZTgyOWZkZjJkOGQ1NTE2ZTg0YWM5NTZhNTllODJiZSJ9fX0=";

        String SCHOLAR = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE3ODAxOTgwMGU2OThhOGU2MjZkZjM3MjQzN2RjMjdhMDBjMWQ0NjBmNTc5NmI2MDI5MzgwZjY4NGVkZWIifX19";

        String SAINT = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZhYzZkMWU1YjQzZTcxM2RmYjE0OWNjODkzMDU4OGFjZGY3NWQ2MDcyZWJkNzBiZjBhNmZlYjBmZGI3NDdmMSJ9fX0=";

        String ROGUE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2RmZjgzYmNhMDYzNzY4NzMyNmM2OGVkYmFjYzQ2NzJiNTkzNjU5YmVmYWRiM2IwNzBlZWI2ZTY4MWFiZTg1OSJ9fX0=";

        String questionmark = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFkYzA0OGE3Y2U3OGY3ZGFkNzJhMDdkYTI3ZDg1YzA5MTY4ODFlNTUyMmVlZWQxZTNkYWYyMTdhMzhjMWEifX19";

        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        fillInventory(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(7));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§r").setDataId(15));
        fillCorners(new ItemBuilder(Material.STONE_BUTTON).setName("§r"));

        RankTypes pvp_rank = RankTypes.getRankFromKills((int) userController.getStatisticByType(StatisticTypes.KILLS));

        setItem(0, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.KNABE.getValue() + " §8: §7Der Start..", KNABE,
                "§8§oDieser PvP-Rang ist dein Anfang",
                "§8§ofür ein Abenteuer",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.KNABE.getMin() + " §7Kills " + (pvp_rank == RankTypes.KNABE ? "§8(§a§lDONE§8)" : ""),
                ""
        )).amount((pvp_rank == RankTypes.KNABE ? -1 : 1)), null);

        setItem(9, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.ADIS_I.getValue() + " §8: §7Guter Anfang", ADIS,
                "§8§oDieser PvP-Rang ist dein Anfang",
                "§8§ofür ein glorreichen Sieg!",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.ADIS_I.getMin() + " §7Kills " + (pvp_rank == RankTypes.ADIS_I ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §6$§e1.000",
                ""
        )).amount((pvp_rank == RankTypes.ADIS_I ? -1 : 1)), null);

        setItem(10, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.ADIS_II.getValue() + " §8: §7Guter Anfang 2.0", ADIS,
                "§8§oDieser PvP-Rang ist dein Anfang 2.0",
                "§8§ofür ein glorreichen Sieg!",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.ADIS_II.getMin() + " §7Kills " + (pvp_rank == RankTypes.ADIS_II ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cI§8)",
                ""
        )).amount((pvp_rank == RankTypes.ADIS_II ? -1 : 1)), null);

        setItem(19, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.ADIS_III.getValue() + " §8: §7Guter Anfang 3.0", ADIS,
                "§8§oDieser PvP-Rang ist dein Anfang 3.0",
                "§8§ofür ein glorreichen Sieg!",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.ADIS_III.getMin() + " §7Kills " + (pvp_rank == RankTypes.ADIS_III ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §6$§e5.000",
                ""
        )).amount((pvp_rank == RankTypes.ADIS_III ? -1 : 1)), null);

        setItem(28, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.ADIS_IV.getValue() + " §8: §7Das Ende vom guten Anfang", ADIS,
                "§8§oDieser PvP-Rang ist dein Ende vom",
                "§8§oguten Anfang für ein glorreichen Sieg!",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.ADIS_IV.getMin() + " §7Kills " + (pvp_rank == RankTypes.ADIS_IV ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cII§8)",
                ""
        )).amount((pvp_rank == RankTypes.ADIS_IV ? -1 : 1)), null);

        setItem(37, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.VANCE_I.getValue() + " §8: §7Weiter geht's..", VANCE,
                "§8§oHoffe Dir ist noch nicht",
                "§8§olangweilig geworden!",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.VANCE_I.getMin() + " §7Kills " + (pvp_rank == RankTypes.VANCE_I ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §e1§8x §6Saphir §7Crate",
                ""
        )).amount((pvp_rank == RankTypes.VANCE_I ? -1 : 1)), null);

        setItem(38, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.VANCE_II.getValue() + " §8: §7Geht's dir gut?", VANCE,
                "§8§oDu kämpfst ja nur",
                "§8§oin der Arena..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.VANCE_II.getMin() + " §7Kills " + (pvp_rank == RankTypes.VANCE_II ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §e3§8x §6Saphir §7Crate",
                ""
        )).amount((pvp_rank == RankTypes.VANCE_II ? -1 : 1)), null);

        setItem(39, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.VANCE_III.getValue() + " §8: §7Halloooo?", VANCE,
                "§8§oMittlerweile glaub ich Dir",
                "§8§ogeht's nicht mehr so gut..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.VANCE_III.getMin() + " §7Kills " + (pvp_rank == RankTypes.VANCE_III ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §6$§e10.000",
                ""
        )).amount((pvp_rank == RankTypes.VANCE_III ? -1 : 1)), null);

        setItem(30, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.VANCE_IV.getValue() + " §8: §7Bist du Tod?", VANCE,
                "§8§oMelde dich endlich doch mal",
                "§8§obist du noch daaa?",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.VANCE_IV.getMin() + " §7Kills " + (pvp_rank == RankTypes.VANCE_IV ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §75§8x §7§lREPAIR §8§lTOKEN",
                ""
        )).amount((pvp_rank == RankTypes.VANCE_IV ? -1 : 1)), null);

        setItem(21, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.HERONE_I.getValue() + " §8: §7Ahh du lebst!!", HERONE,
                "§8§oSchön, dass du dich mal wieder",
                "§8§omeldest mein kleiner PvPler!",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.HERONE_I.getMin() + " §7Kills " + (pvp_rank == RankTypes.HERONE_I ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §31§8x §bOnyx §7Crate",
                ""
        )).amount((pvp_rank == RankTypes.HERONE_I ? -1 : 1)), null);

        setItem(12, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.HERONE_II.getValue() + " §8: §7Bist du ein Ritter?", HERONE,
                "§8§oDu hast ja echt viel Talent",
                "§8§obitte hör doch auf.. :(.",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.HERONE_II.getMin() + " §7Kills " + (pvp_rank == RankTypes.HERONE_II ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §6$§e15.000",
                ""
        )).amount((pvp_rank == RankTypes.HERONE_II ? -1 : 1)), null);

        setItem(13, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.HERONE_III.getValue() + " §8: §7Okey, okey...", HERONE,
                "§8§oBleiben wir hier mal ganz",
                "§8§orealistisch, okeyyy?",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.HERONE_III.getMin() + " §7Kills " + (pvp_rank == RankTypes.HERONE_III ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §21§8x §A§LPLOT MOVE",
                ""
        )).amount((pvp_rank == RankTypes.HERONE_III ? -1 : 1)), null);

        setItem(14, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.HERONE_IV.getValue() + " §8: §7PC oder MAC?", HERONE,
                "§8§oDu bist echt ein reicher Affe,",
                "§8§owenn du auf einem MAC spielst..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.HERONE_IV.getMin() + " §7Kills " + (pvp_rank == RankTypes.HERONE_IV ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §e3§8x §6Saphir §7Crate",
                ""
        )).amount((pvp_rank == RankTypes.HERONE_IV ? -1 : 1)), null);

        setItem(23, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SCHOLAR_I.getValue() + " §8: §7Hast du Hunger?", SCHOLAR,
                "§8§oIch muss ehrlich zugeben, beim programmieren",
                "§8§obekommt man immer richtig krank Hunger.",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SCHOLAR_I.getMin() + " §7Kills " + (pvp_rank == RankTypes.SCHOLAR_I ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §e6§8x §6Saphir §7Crate",
                ""
        )).amount((pvp_rank == RankTypes.SCHOLAR_I ? -1 : 1)), null);

        setItem(32, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SCHOLAR_II.getValue() + " §8: §7Sweet Paprika", SCHOLAR,
                "§8§oWer bei Pringles eine andere Sorte",
                "§8§okauft ist echt ein ehrenloser B****.",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SCHOLAR_II.getMin() + " §7Kills " + (pvp_rank == RankTypes.SCHOLAR_II ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §41§8x §c§lSTATSRESET §7Token",
                ""
        )).amount((pvp_rank == RankTypes.SCHOLAR_II ? -1 : 1)), null);

        setItem(41, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SCHOLAR_III.getValue() + " §8: §7SkySuppe", SCHOLAR,
                "§8§oEine kleine Portion Buchstabensuppe,",
                "§8§odie wäre jetzt echt ganz super.",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SCHOLAR_III.getMin() + " §7Kills " + (pvp_rank == RankTypes.SCHOLAR_III ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §d1§8x §5§l+1 ENDERKISTEN ZEILE",
                ""
        )).amount((pvp_rank == RankTypes.SCHOLAR_III ? -1 : 1)), null);

        setItem(42, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SCHOLAR_IV.getValue() + " §8: §7Reicht jetzt..", SCHOLAR,
                "§8§oGlaube mittlerweile, dass du echt",
                "§8§oder beste Spieler auf dem Server bist.",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SCHOLAR_IV.getMin() + " §7Kills " + (pvp_rank == RankTypes.SCHOLAR_IV ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §42§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cII§8)",
                ""
        )).amount((pvp_rank == RankTypes.SCHOLAR_IV ? -1 : 1)), null);

        setItem(43, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SAINT_I.getValue() + " §8: §7Wild", SAINT,
                "§8§oErstmal eine Pulle Vita Vate",
                "§8§oauf sehr süß angelehnt..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SAINT_I.getMin() + " §7Kills " + (pvp_rank == RankTypes.SAINT_I ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIII§8)",
                ""
        )).amount((pvp_rank == RankTypes.SAINT_I ? -1 : 1)), null);

        setItem(34, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SAINT_II.getValue() + " §8: §7Bald bist du fertig!", SAINT,
                "§8§oMuss zugeben, dass es echt schon",
                "§8§oein kranker Flex ist..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SAINT_II.getMin() + " §7Kills " + (pvp_rank == RankTypes.SAINT_II ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §43§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIII§8)",
                ""
        )).amount((pvp_rank == RankTypes.SAINT_II ? -1 : 1)), null);

        setItem(25, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SAINT_III.getValue() + " §8: §7Meow", SAINT,
                "§8§oKomisch, dass viele Leute sagen, das",
                "§8§oKatzen 7 Leben haben, schon weird..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SAINT_III.getMin() + " §7Kills " + (pvp_rank == RankTypes.SAINT_III ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §45§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIII§8)",
                ""
        )).amount((pvp_rank == RankTypes.SAINT_III ? -1 : 1)), null);

        setItem(16, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.SAINT_IV.getValue() + " §8: §7Affe", SAINT,
                "§8§oKennst du eigentlich schon",
                "§8§odie Gibbon Affenfamilie?",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.SAINT_IV.getMin() + " §7Kills " + (pvp_rank == RankTypes.SAINT_IV ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIV§8)",
                ""
        )).amount((pvp_rank == RankTypes.SAINT_IV ? -1 : 1)), null);

        setItem(17, new ItemBuilder(ItemSkull.getSkull("§8┃ " + RankTypes.ROGUE.getValue() + " §8: §7Oha..", ROGUE,
                "§8§oHöher geht es erstmal nicht mehr..",
                "§8§oDu bist ein wahrer King..",
                "",
                "§8┌ §7Anforderungen§8:",
                "§8└ §c" + RankTypes.ROGUE.getMin() + " §7Kills " + (pvp_rank == RankTypes.ROGUE ? "§8(§a§lDONE§8)" : ""),
                "",
                "§8┌ §7Belohnung§8:",
                "§8└ §41§8x §c§lPERMA TS³ CHANNEL",
                ""
        )).amount((pvp_rank == RankTypes.ROGUE ? -1 : 1)), null);

        final RankTypes rankTypes = RankTypes.getUserRankFromID(pvp_rank.getId() + 1);

        setItem(4, new ItemBuilder(ItemSkull.getSkull("§8┃ §c§l" + InventoryTitles.PVPRANK + " §8: §7Übersicht", questionmark,
                "§8§oSiehe deine aktuelle Übersicht/Stats",
                "§8§ofür deinen Status des PvP-Rang's!",
                "",
                "§8┌ §7Derzeitiger Rang§8: §r" + pvp_rank.getValue(),
                "§8├ §7Nächster Rang§8: §r" + (pvp_rank == RankTypes.ROGUE ? RankTypes.ROGUE.getValue() : rankTypes.getValue()),
                "§8└ §7Fehlende Kills§8: §r" + (pvp_rank == RankTypes.ROGUE ? "§f-1" : rankTypes.getMin() - userController.getStatisticByType(StatisticTypes.KILLS)),
                "",
                "§8§oDie Belohnungen bekommst Du automatisch,",
                "§8§owenn Du eine Stufe aufsteigst!",
                ""
        )), null);

    }

    @Override
    public void onClose(@NonNull Player player, @NonNull InventoryView inventoryView) {

    }
}
