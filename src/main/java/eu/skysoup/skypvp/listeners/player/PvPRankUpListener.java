package eu.skysoup.skypvp.listeners.player;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
import eu.skysoup.skypvp.data.implementorings.RankTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.events.PvPRankupEvent;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created: 01.02.2023
 *
 * @author benni
 */
public class PvPRankUpListener implements Listener {

    @EventHandler
    public void onPvPRankUp(final PvPRankupEvent event) {
        final Player player = event.getPlayer();
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());
        final RankTypes nextRank = event.getNextRank();
        RankTypes current = RankTypes.getRankFromKills((int) userController.getStatisticByType(StatisticTypes.KILLS));

        //if (current == RankTypes.ROGUE) return;
        if (userController.getStatisticByType(StatisticTypes.KILLS) > 1000) return;

        if (userController.getStatisticByType(StatisticTypes.KILLS) == nextRank.getMin()) {

            if (nextRank.getId() <= 21) {
                Data.getMessageUtil().sendMessageToAll("§e" + player.getName() + " §7ist ein §cPvP§8-§cRang §7aufgelevelt. §8(§r" + nextRank.getValue() + "§8)");
            } else {
                Data.getMessageUtil().sendMessageToAll("§e" + player.getName() + " §7ist nun §r" + nextRank.getValue() + "§8! §a§LGG!");
            }

            player.sendMessage("");
            player.sendMessage(Data.getMessageUtil().getCenteredMessage("§8§m--§7§m--§f§m--§r §c§lPVP§8-§c§LRANG §f§m--§7§m--§8§m--§r"));
            player.sendMessage("");
            Data.getMessageUtil().sendMessage(player, "§7Du bist ein §cPvP§8-§cRang §7aufgelevelt§8!");
            Data.getMessageUtil().sendMessage(player, "§7Neuer Rang§8: §e" + nextRank.getValue());
            Data.getMessageUtil().sendMessage(player, "§7Deine Belohnung§8:");

            if (nextRank.getId() == 2) {
                Data.getMessageUtil().sendMessage(player, "§8└ §6$§e1.000 §7Tokens");

                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.DOUBLE_PLANT).setName("§8» §7Gutschein§8: §6§lTOKEN").lore("§8§oRechtsklicke, um diesen Gutschein einzulösen.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §e§lTOKEN",
                        " §8└ §7Gutschein-Wert§8: §e§l1,000",
                        ""));


            } else if (nextRank.getId() == 3) {
                Data.getMessageUtil().sendMessage(player, "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cI§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cI",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ));
            } else if (nextRank.getId() == 4) {
                Data.getMessageUtil().sendMessage(player, "§8└ §6$§e5.000 §7Tokens");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.DOUBLE_PLANT).setName("§8» §7Gutschein§8: §6§lTOKEN").lore("§8§oRechtsklicke, um diesen Gutschein einzulösen.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §e§lTOKEN",
                        " §8└ §7Gutschein-Wert§8: §e§l5,000",
                        ""));
            } else if (nextRank.getId() == 5) {
                Data.getMessageUtil().sendMessage(player, "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cII§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cII",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ));
            } else if (nextRank.getId() == 6) {
                Data.getMessageUtil().sendMessage(player, "§8└ §e1§8x §6Saphir §7Crate"); // TODO: crate geben
            } else if (nextRank.getId() == 7) {
                Data.getMessageUtil().sendMessage(player, "§8└ §e3§8x §6Saphir §7Crate"); // TODO: crate geben
            } else if (nextRank.getId() == 8) {
                Data.getMessageUtil().sendMessage(player, "§8└ §6$§e10.000 §7Tokens");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.DOUBLE_PLANT).setName("§8» §7Gutschein§8: §6§lTOKEN").lore("§8§oRechtsklicke, um diesen Gutschein einzulösen.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §e§lTOKEN",
                        " §8└ §7Gutschein-Wert§8: §e§l10,000",
                        ""));
            } else if (nextRank.getId() == 9) {
                Data.getMessageUtil().sendMessage(player, "§8└ §75§8x §7§lREPAIR §8§lTOKEN");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.NETHER_STAR).setName("§8» §7Gutschein§8: §8§lREPAIR §7§lTOKEN").lore("§8§oDieser Repair Token ermöglicht es dir dein,",
                        "§8§oGegenstand in der Hand zu reparieren.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §7§l" + GutscheinTypes.OTHER.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7§l1 REPERATUR",
                        ""
                ).amount(5));
            } else if (nextRank.getId() == 10) {
                Data.getMessageUtil().sendMessage(player, "§8└ §31§8x §bOnyx §7Crate"); // TODO: crate geben
            } else if (nextRank.getId() == 11) {
                Data.getMessageUtil().sendMessage(player, "§8└ §6$§e15.000 §7Tokens");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.DOUBLE_PLANT).setName("§8» §7Gutschein§8: §6§lTOKEN").lore("§8§oRechtsklicke, um diesen Gutschein einzulösen.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §e§lTOKEN",
                        " §8└ §7Gutschein-Wert§8: §e§l15,000",
                        ""));
            } else if (nextRank.getId() == 12) {
                Data.getMessageUtil().sendMessage(player, "§8└ §21§8x §A§LPLOT MOVE");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8» §7Gutschein§8: §a§lPLOT MOVE").lore("§8§oLass dein Grundstück von einem Admin",
                        "§8§oan einen anderen Ort verschieben.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §a§l" + GutscheinTypes.PLOTMOVE.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §a§l1x",
                        ""
                ));
            } else if (nextRank.getId() == 13) {
                Data.getMessageUtil().sendMessage(player, "§8└ §e3§8x §6Saphir §7Crate"); // TODO: crate geben
            } else if (nextRank.getId() == 14) {
                Data.getMessageUtil().sendMessage(player, "§8└ §e6§8x §6Saphir §7Crate"); // TODO: crate geben
            } else if (nextRank.getId() == 15) {
                Data.getMessageUtil().sendMessage(player, "§8└ §41§8x §c§lSTATSRESET §7Token");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.INK_SACK).setDataId(5).setName("§8» §7Gutschein§8: §c§lSTATSRESET").lore("§8§oDieser Statsreset Token ermöglicht es dir",
                        "§8§odeine PvP-Statistiken zurückzusetzen.",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §c§l1x RESET",
                        ""
                ));
            } else if (nextRank.getId() == 16) {
                Data.getMessageUtil().sendMessage(player, "§8└ §d1§8x §5§l+1 ENDERKISTEN ZEILE");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.ENDER_CHEST).setName("§8» §7Gutschein§8: §5§l+1 ENDERKISTEN ZEILE").lore("§8§oRechtsklicke, um eine weitere Zeile", "§8§odeiner Enderkiste freizuschalten.", "", " §8┌ §7Gutschein-Type§8: §5§l" + GutscheinTypes.ENDERKISTE.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §5§l1 ZEILE", ""));
            } else if (nextRank.getId() == 17) {
                Data.getMessageUtil().sendMessage(player, "§8└ §42§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cII§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cII",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ).amount(2));
            } else if (nextRank.getId() == 18) {
                Data.getMessageUtil().sendMessage(player, "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIII§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cIII",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ));
            } else if (nextRank.getId() == 19) {
                Data.getMessageUtil().sendMessage(player, "§8└ §43§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIII§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cIII",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ).amount(3));
            } else if (nextRank.getId() == 20) {
                Data.getMessageUtil().sendMessage(player, "§8└ §45§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIII§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cIII",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ).amount(5));
            } else if (nextRank.getId() == 21) {

                System.out.println(nextRank.getValue());

                Data.getMessageUtil().sendMessage(player, "§8└ §41§8x §c§lPVP§8-§c§lPAKET §8(§7Tier §cIV§8)");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BOOK).setName("§8» §7Gutschein§8: §a§l§c§lPVP§8-§c§lPAKET").lore(
                        "§8§oLöse ein PvP-Paket ein und kämpfe in",
                        "§8§oder Arena und besiege die Welt!",
                        "",
                        " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.PVP.name().toUpperCase(),
                        " §8└ §7Gutschein-Wert§8: §7Tier §cIV",
                        "",
                        "§7Signiert von §e§lSKYSOUP §7am " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())
                ));
            } else if (nextRank.getId() == 22) {

                System.out.println(nextRank.getValue());

                Data.getMessageUtil().sendMessage(player, "§41§8x §c§lPERMA TS³ CHANNEL");
                Data.getPlayerUtil().addItem(player, new ItemBuilder(Material.BANNER).setDataId(1).setName("§8» §7Gutschein§8: §c§lPERMA TS³ CHANNEL").lore("§8§oSchreibe einem Admin,", "§8§oum diesen Gutschein einzulösen.", "", " §8┌ §7Gutschein-Type§8: §c§l" + GutscheinTypes.TEAMSPEAK.name().toUpperCase(), " §8└ §7Gutschein-Wert§8: §c§lPERMANENT", ""));
            }

            player.sendMessage("");
            player.sendMessage(Data.getMessageUtil().getCenteredMessage("§8§m--§7§m--§f§m--§r §c§lPVP§8-§c§LRANG §f§m--§7§m--§8§m--§r"));
            player.sendMessage("");

            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 0.1F);
        }
    }
}
