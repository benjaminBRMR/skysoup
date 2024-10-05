package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import eu.skysoup.skypvp.utils.builders.ItemSkull;
import eu.skysoup.skypvp.utils.impl.Gutschein;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created: 07.02.2023 12:42
 *
 * @author thvf
 */
public class GutscheinController {


    @Getter
    public final ArrayList<Gutschein> gutscheine = new ArrayList<>();

    public void initialize() {

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l2er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l4er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l8er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l12er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l16er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l20er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase(), GutscheinTypes.MERGE, new ItemStack(Material.PAPER), new String[]{"§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.MERGE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l50er", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §6§l" + GutscheinTypes.CLAN.getPrefix(), GutscheinTypes.CLAN, new ItemStack(Material.NAME_TAG), new String[]{"§8§oRechtsklicke, um dein Clantag farbig zu gestalten.", "§8§oDamit schaltest du das Feature Farbiger Clantag frei.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.CLAN.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: " + GutscheinTypes.CLAN.getPrefix(), "", "§8§oDu musst Clanbesitzer sein, um dieses Feature nutzen zu können!"}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §e§l+1 §6§lCLANPLATZ", GutscheinTypes.CLAN, new ItemStack(Material.SLIME_BALL), new String[]{"§8§oRechtsklicke, um deine Clanplatzkapazität", "§8§oum einen Platz zu vergrößern.", "", " §8┌ §7Gutschein-Type§8: §e§l" + GutscheinTypes.CLAN.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §e§l+1 §6§lCLANPLATZ", "", "§8§oDiesen Gutschein kann jedes Mitglied einlösen."}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §8§lREPAIR §7§lTOKEN", GutscheinTypes.OTHER, new ItemBuilder(Material.NETHER_STAR), new String[]{"§8§oDieser Repair Token ermöglicht es dir dein,", "§8§oGegenstand in der Hand zu reparieren.", "", " §8┌ §7Gutschein-Type§8: §7§l" + GutscheinTypes.OTHER.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §7§l1 REPERATUR", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §c§lSTATSRESET", GutscheinTypes.PVP, new ItemBuilder(Material.INK_SACK).setDataId(5), new String[]{"§8§oDieser Statsreset Token ermöglicht es dir", "§8§odeine PvP-Statistiken zurückzusetzen.", "", " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §c§l1x RESET", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §a§lPLOT MOVE", GutscheinTypes.PLOTMOVE, new ItemBuilder(Material.INK_SACK).setDataId(10), new String[]{"§8§oLass dein Grundstück von einem Admin", "§8§oan einen anderen Ort verschieben.", "", " §8┌ §7Gutschein-Type§8: §a§l" + GutscheinTypes.PLOTMOVE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §a§l1x", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §c§lPVP§8-§c§lPAKET", GutscheinTypes.PVP, new ItemBuilder(Material.BOOK), new String[]{"§8§oLöse ein PvP-Paket ein und kämpfe in", "§8§oder Arena und besiege die Welt!", "", " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §7Tier §cI", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §c§lPVP§8-§c§lPAKET", GutscheinTypes.PVP, new ItemBuilder(Material.BOOK), new String[]{"§8§oLöse ein PvP-Paket ein und kämpfe in", "§8§oder Arena und besiege die Welt!", "", " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §7Tier §cII", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §c§lPVP§8-§c§lPAKET", GutscheinTypes.PVP, new ItemBuilder(Material.BOOK), new String[]{"§8§oLöse ein PvP-Paket ein und kämpfe in", "§8§oder Arena und besiege die Welt!", "", " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §7Tier §cIII", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Gutschein§8: §c§lPVP§8-§c§lPAKET", GutscheinTypes.PVP, new ItemBuilder(Material.BOOK), new String[]{
                "§8§oLöse ein PvP-Paket ein und kämpfe in", "§8§oder Arena und besiege die Welt!", "", " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §7Tier §cIV", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7AirDrop§8: §cLevel 1", GutscheinTypes.PVP, new ItemBuilder(Material.REDSTONE_TORCH_ON), new String[]{
                "§8§oPlatziere diesen AirDrop, um viele", "§8§overschiedene Items", "§8§ogeliefert zu bekommen.", "", "§8├ §7Level§8: §c1", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7AirDrop§8: §cLevel 2", GutscheinTypes.PVP, new ItemBuilder(Material.REDSTONE_TORCH_ON), new String[]{
                "§8§oPlatziere diesen AirDrop, um viele", "§8§overschiedene Items", "§8§ogeliefert zu bekommen.", "", "§8├ §7Level§8: §c2", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7AirDrop§8: §cLevel 3", GutscheinTypes.PVP, new ItemBuilder(Material.REDSTONE_TORCH_ON), new String[]{
                "§8§oPlatziere diesen AirDrop, um viele", "§8§overschiedene Items", "§8§ogeliefert zu bekommen.", "", "§8├ §7Level§8: §c3", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7AirDrop§8: §cLevel 4", GutscheinTypes.PVP, new ItemBuilder(Material.REDSTONE_TORCH_ON), new String[]{
                "§8§oPlatziere diesen AirDrop, um viele", "§8§overschiedene Items", "§8§ogeliefert zu bekommen.", "", "§8├ §7Level§8: §c4", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7AirDrop§8: §cLevel 5", GutscheinTypes.PVP, new ItemBuilder(Material.REDSTONE_TORCH_ON), new String[]{
                "§8§oPlatziere diesen AirDrop, um viele", "§8§overschiedene Items", "§8§ogeliefert zu bekommen.", "", "§8├ §7Level§8: §c5", ""}));

        gutscheine.add(new Gutschein(1, "§8» §7Rune des Todes", GutscheinTypes.PVP, ItemSkull.getSkull("§8» §7Rune des Todes", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ2MjBlNGUzZDNhYmZlZDZhZDgxYTU4YTU2YmNkMDg1ZDllOWVmYzgwM2NhYmIyMWZhNmM5ZTM5NjllMmQyZSJ9fX0="), new String[]{"§8§oDiese Rune hast Du durch eine", "§8§oSeele eines toten Spielers erhalten.", "§8§oRechtsklick, um diese zu benutzen!", "",}
        ));
    }
}
