package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.commands.admin.*;
import eu.skysoup.skypvp.commands.player.*;
import eu.skysoup.skypvp.commands.team.*;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.listeners.player.*;
import eu.skysoup.skypvp.listeners.world.BlockPlaceListener;
import eu.skysoup.skypvp.listeners.world.WorldListener;
import eu.skysoup.skypvp.utils.impl.Utils;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import eu.skysoup.skypvp.utils.inventory.handler.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created: 23.01.2023 00:20
 *
 * @author thvf
 */
public class BukkitController {

    /**
     * initializes the commands
     */
    public void initCommands() {
        Bukkit.getConsoleSender().sendMessage("§a[SkySoupEU] Initializing commands...");

        SkyPvP.getINSTANCE().getCommand("clan").setExecutor(new CommandClan());
        SkyPvP.getINSTANCE().getCommand("location").setExecutor(new CommandLocation());
        SkyPvP.getINSTANCE().getCommand("joinmsg").setExecutor(new CommandJoinMsg());
        SkyPvP.getINSTANCE().getCommand("quitmsg").setExecutor(new CommandLeaveMsg());
        SkyPvP.getINSTANCE().getCommand("chatfilter").setExecutor(new eu.skysoup.skypvp.commands.admin.CommandChatFilter());
        SkyPvP.getINSTANCE().getCommand("top").setExecutor(new CommandTop());
        SkyPvP.getINSTANCE().getCommand("trophy").setExecutor(new CommandTrophy());
        SkyPvP.getINSTANCE().getCommand("chat").setExecutor(new CommandChat());
        SkyPvP.getINSTANCE().getCommand("tpa").setExecutor(new CommandTpa());
        SkyPvP.getINSTANCE().getCommand("tpdeny").setExecutor(new CommandTpdeny());
        SkyPvP.getINSTANCE().getCommand("tpaccept").setExecutor(new CommandTpaccept());
        SkyPvP.getINSTANCE().getCommand("heal").setExecutor(new CommandHeal());
        SkyPvP.getINSTANCE().getCommand("feed").setExecutor(new CommandFeed());
        SkyPvP.getINSTANCE().getCommand("msg").setExecutor(new CommandMsg());
        SkyPvP.getINSTANCE().getCommand("reply").setExecutor(new CommandReply());
        SkyPvP.getINSTANCE().getCommand("rename").setExecutor(new CommandRename());
        SkyPvP.getINSTANCE().getCommand("fly").setExecutor(new CommandFly());
        SkyPvP.getINSTANCE().getCommand("hat").setExecutor(new CommandHat());
        SkyPvP.getINSTANCE().getCommand("pvprank").setExecutor(new CommandPvPRank());
        SkyPvP.getINSTANCE().getCommand("trophymarkt").setExecutor(new CommandTrophymarkt());
        SkyPvP.getINSTANCE().getCommand("bounty").setExecutor(new CommandBounty());
        SkyPvP.getINSTANCE().getCommand("toggle").setExecutor(new CommandToggle());
        SkyPvP.getINSTANCE().getCommand("kit").setExecutor(new CommandKit());
        SkyPvP.getINSTANCE().getCommand("gutschein").setExecutor(new CommandGutschein());
        SkyPvP.getINSTANCE().getCommand("stats").setExecutor(new CommandStats());
        SkyPvP.getINSTANCE().getCommand("settings").setExecutor(new CommandSettings());
        SkyPvP.getINSTANCE().getCommand("vanish").setExecutor(new CommandVanish());
        SkyPvP.getINSTANCE().getCommand("repair").setExecutor(new CommandRepair());
        SkyPvP.getINSTANCE().getCommand("spawn").setExecutor(new CommandSpawn());
        SkyPvP.getINSTANCE().getCommand("casino").setExecutor(new CommandCasino());
        SkyPvP.getINSTANCE().getCommand("clear").setExecutor(new CommandClear());
        SkyPvP.getINSTANCE().getCommand("tps").setExecutor(new CommandTps());
        SkyPvP.getINSTANCE().getCommand("teamchat").setExecutor(new CommandTeamchat());
        SkyPvP.getINSTANCE().getCommand("enchant").setExecutor(new CommandEnchant());
        SkyPvP.getINSTANCE().getCommand("allmoney").setExecutor(new CommandAllMoney());
        SkyPvP.getINSTANCE().getCommand("invsee").setExecutor(new CommandInvsee());
        SkyPvP.getINSTANCE().getCommand("giveall").setExecutor(new CommandGiveall());
        SkyPvP.getINSTANCE().getCommand("user").setExecutor(new CommandUser());
        SkyPvP.getINSTANCE().getCommand("tp").setExecutor(new CommandTp());
        SkyPvP.getINSTANCE().getCommand("tpall").setExecutor(new CommandTpall());
        SkyPvP.getINSTANCE().getCommand("reward").setExecutor(new CommandReward());
        SkyPvP.getINSTANCE().getCommand("blacklist").setExecutor(new CommandBlacklist());
        SkyPvP.getINSTANCE().getCommand("ping").setExecutor(new CommandPing());
        SkyPvP.getINSTANCE().getCommand("motd").setExecutor(new CommandMotd());
        SkyPvP.getINSTANCE().getCommand("shop").setExecutor(new CommandShop());
        SkyPvP.getINSTANCE().getCommand("airdrop").setExecutor(new CommandAirdrop());
        SkyPvP.getINSTANCE().getCommand("pay").setExecutor(new CommandPay());
        SkyPvP.getINSTANCE().getCommand("lagerplatz").setExecutor(new CommandLagerPlatz());
        SkyPvP.getINSTANCE().getCommand("rang").setExecutor(new CommandRang());
        SkyPvP.getINSTANCE().getCommand("config").setExecutor(new CommandConfig());
        SkyPvP.getINSTANCE().getCommand("laby").setExecutor(new CommandLaby());
        SkyPvP.getINSTANCE().getCommand("gamemode").setExecutor(new CommandGamemode());
        SkyPvP.getINSTANCE().getCommand("menu").setExecutor(new CommandMenu());
        SkyPvP.getINSTANCE().getCommand("trash").setExecutor(new CommandTrash());
        SkyPvP.getINSTANCE().getCommand("moneymaker").setExecutor(new CommandMoneyMaker());

    }

    /**
     * initializes the listeners
     */
    public void initListeners() {
        Bukkit.getConsoleSender().sendMessage("§a[SkySoupEU] Initializing listeners...");

        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new JoinListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new QuitListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new AsyncChatListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new MoveListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new WorldListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new InteractListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new KillListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new PvPRankUpListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new RespawnListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new InventoryCloseListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new CommandListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new LoginListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new InventoryClickListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new WorldChangeListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new BlockPlaceListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new ServerPingListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new PlayerDamageListener(), SkyPvP.getINSTANCE());
        SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new ItemListener(), SkyPvP.getINSTANCE());
        //SkyPvP.getINSTANCE().getServer().getPluginManager().registerEvents(new FlightListener(), SkyPvP.getINSTANCE());
    }


    /**
     * initializes the schedulers
     */

    private long lastopened = 0;

    public void initSchedulers() {

        Data.getTaskController().initScoreboardMessages();
        Data.getTaskController().beginBroadcast();

        SkyPvP.getINSTANCE().getServer().getScheduler().runTaskTimer(SkyPvP.getINSTANCE(), () -> {

            final String header =
                    Data.getHeader().replaceAll("%nl%", "\n")
                            .replaceAll("%size of all players%", String.valueOf(Utils.getCorrectOnlinePlayers()))
                            .replaceAll("%uhrzeit%", new SimpleDateFormat("HH:mm").format(new Date()))
                            .replaceAll("%erreichbar%", ValueUtil.timeToString(System.currentTimeMillis() - SkyPvP.getUPTIME(), true, true))
                            .replaceAll("%todaycount%", String.valueOf(Bukkit.getOfflinePlayers().length));


            Data.getScoreboardController().getFastBoardMap().values().forEach(all -> {
                Data.getScoreboardController().updateScoreboard(all);
                Data.getTablistController().updateTablist(all.getPlayer());
                Data.getPlayerUtil().sendPlayerListTab(all.getPlayer(), header, Data.getFooter().replaceAll("%nl%", "\n"));
            });

            if (System.currentTimeMillis() - lastopened < 2000000) return;

            final LocalTime time = LocalTime.now();

            if (time.getHour() == 2 && time.getMinute() == 50 && time.getSecond() == 1) {
                Data.getMessageUtil().sendMessageToAll("§7Der Server startet in §e10 §7Minuten neu§8.");
                Data.getPlayerUtil().playSoundToEveryone(Sound.NOTE_PLING);
            }

            if (time.getHour() == 3 && time.getMinute() == 0 && (time.getSecond() > 0 && time.getSecond() < 3)) {
                lastopened = System.currentTimeMillis();
                Bukkit.shutdown();
                return;
            }


        }, 0, Data.getRunnableUtil().getTimeFromSeconds(1));


    }

    public void initClose() {
        Bukkit.getOnlinePlayers().forEach(all -> {
            all.kickPlayer("§cDer Server startet nun neu!");
        });
    }


    /**
     * initializes other stuff
     */
    public void initOther() {

        Data.setInventoryHandler(new InventoryHandler());
        Data.getGutscheinController().initialize();
        Data.getPlotRandController().initialize();
        Data.getPlotWandController().initialize();

        Bukkit.getWorlds().forEach(all -> {
            all.setFullTime(0);
            all.setStorm(false);
        });
    }
}
