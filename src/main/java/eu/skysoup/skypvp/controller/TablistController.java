package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.utils.impl.StringUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created: 24.01.2023 11:16
 *
 * @author thvf
 */
public class TablistController {

    private Scoreboard scoreboard;

    public TablistController() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        scoreboard.registerNewTeam("0001owner");
        scoreboard.registerNewTeam("0002srdev");
        scoreboard.registerNewTeam("0003dev");
        scoreboard.registerNewTeam("0004srmod");
        scoreboard.registerNewTeam("0005srcontent");
        scoreboard.registerNewTeam("0006mod");
        scoreboard.registerNewTeam("0007content");
        scoreboard.registerNewTeam("0008testmod");
        scoreboard.registerNewTeam("0009supporter");
        scoreboard.registerNewTeam("0010jrcontent");
        scoreboard.registerNewTeam("0011azubi");
        scoreboard.registerNewTeam("0012soup");
        scoreboard.registerNewTeam("0013superia");
        scoreboard.registerNewTeam("0014onyx");
        scoreboard.registerNewTeam("0015saphir");
        scoreboard.registerNewTeam("0016azur");
        scoreboard.registerNewTeam("99spieler");

        scoreboard.getTeam("0001owner").setPrefix("§4§lO §8» §4");
        scoreboard.getTeam("0002srdev").setPrefix("§b§lSrD §8» §b");
        scoreboard.getTeam("0003dev").setPrefix("§b§lDev §8» §b");
        scoreboard.getTeam("0004srmod").setPrefix("§c§lSrM §8» §c");
        scoreboard.getTeam("0005srcontent").setPrefix("§6§lSrC §8» §6");
        scoreboard.getTeam("0006mod").setPrefix("§3§lMod §8» §3");
        scoreboard.getTeam("0007content").setPrefix("§6§lCon §8» §6");
        scoreboard.getTeam("0008testmod").setPrefix("§3§lT-M §8» §3");
        scoreboard.getTeam("0009supporter").setPrefix("§a§lSup §8» §a");
        scoreboard.getTeam("0010jrcontent").setPrefix("§6§lJrC §8» §6");
        scoreboard.getTeam("0011azubi").setPrefix("§9§lAzu §8» §9");
        scoreboard.getTeam("0012soup").setPrefix("§eSoup §8» §e");
        scoreboard.getTeam("0013superia").setPrefix("§dSup §8» §d");
        scoreboard.getTeam("0014onyx").setPrefix("§bOnyx §8» §b");
        scoreboard.getTeam("0015saphir").setPrefix("§6Saph §8» §6");
        scoreboard.getTeam("0016azur").setPrefix("§5Azur §8» §5");
        scoreboard.getTeam("99spieler").setPrefix("§a");

    }


    public void updateTablist(Player player) {
        final String team;
        Group group = LuckPermsProvider.get().getGroupManager().getGroup(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
        final String clan = Data.getClanController().getClan(player);


        if (group == null) {
            team = "99spieler";
        } else {

            if (group.getName().equalsIgnoreCase("default")) {
                team = "99spieler";
            } else if (group.getName().equalsIgnoreCase("owner")) {
                team = "0001owner";
            } else if (group.getName().equalsIgnoreCase("srdev")) {
                team = "0002srdev";
            } else if (group.getName().equalsIgnoreCase("dev")) {
                team = "0003dev";
            } else if (group.getName().equalsIgnoreCase("srmod")) {
                team = "0004srmod";
            } else if (group.getName().equalsIgnoreCase("srcontent")) {
                team = "0005srcontent";
            } else if (group.getName().equalsIgnoreCase("mod")) {
                team = "0006mod";
            } else if (group.getName().equalsIgnoreCase("content")) {
                team = "0007content";
            } else if (group.getName().equalsIgnoreCase("testmod")) {
                team = "0008testmod";
            } else if (group.getName().equalsIgnoreCase("sup")) {
                team = "0009supporter";
            } else if (group.getName().equalsIgnoreCase("jrcontent")) {
                team = "0010jrcontent";
            } else if (group.getName().equalsIgnoreCase("azubi")) {
                team = "0011azubi";
            } else if (group.getName().equalsIgnoreCase("soup")) {
                team = "0012soup";
            } else if (group.getName().equalsIgnoreCase("superia")) {
                team = "0013superia";
            } else if (group.getName().equalsIgnoreCase("onyx")) {
                team = "0014onyx";
            } else if (group.getName().equalsIgnoreCase("saphir")) {
                team = "0015saphir";
            } else if (group.getName().equalsIgnoreCase("azur")) {
                team = "0016azur";
            } else {
                team = "99spieler";
            }
        }

        if (scoreboard.getTeam(team) == null) {
            scoreboard.registerNewTeam(team);
        } else {
            scoreboard.getTeam(team).addPlayer(player);
        }

        Bukkit.getOnlinePlayers().forEach(all -> all.setScoreboard(scoreboard));


        player.setPlayerListName(scoreboard.getTeam(team).getPrefix() + player.getName() + (Data.getVanished().contains(player) ? " §8(§c§lV§8)" : "") + (Data.getClanController().isInClan(player) ? " §8┃ §e" + (Data.getClanController().hasCustomClanTag(clan) ? Data.getClanController().getCustomClanTag(clan).replaceAll("%CLAN%", StringUtil.capitalize(clan)) : "§7" + StringUtil.capitalize(clan)) : ""));


        player.setDisplayName(scoreboard.getTeam(team).getPrefix() + player.getName());

    }
}
