package eu.skysoup.skypvp.data;

import eu.skysoup.skypvp.casino.CasinoController;
import eu.skysoup.skypvp.controller.*;
import eu.skysoup.skypvp.controller.other.*;
import eu.skysoup.skypvp.kit.KitController;
import eu.skysoup.skypvp.reward.RewardController;
import eu.skysoup.skypvp.trophyshop.TrophyShopController;
import eu.skysoup.skypvp.utils.*;
import eu.skysoup.skypvp.utils.inventory.handler.InventoryHandler;
import eu.skysoup.skypvp.utils.teleport.TeleportCooldownController;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created: 23.01.2023 00:20
 *
 * @author thvf
 */
public class Data {

    @Getter
    private static final HashMap<UUID, ItemStack> cachedSkulls = new HashMap<>();
    @Getter
    private static final InventoryMaps inventoryMaps = new InventoryMaps();
    @Getter
    @Setter
    private static InventoryHandler inventoryHandler;
    @Setter
    @Getter
    private static long playercountToday = 0;
    @Getter
    private static World SkyPvP = Bukkit.getWorld("skypvp-3");
    @Getter
    private static final Logger LOGGER = Bukkit.getLogger();
    @Getter
    private static final Location location = new Location();
    @Getter
    private static final Random random = new Random();
    @Getter
    private static final HashMap<Player, Player> clanInvites = new HashMap<Player, Player>();
    @Getter
    private static final HashMap<Player, Player> reply = new HashMap<>();
    @Getter
    private static final HashMap<Player, Player> tparequest = new HashMap<>();
    @Getter
    private static final List<Player> vanished = new ArrayList<>();
    @Getter
    @Setter
    private static boolean scrambleRunning = false;
    @Getter
    @Setter
    private static boolean guessTheNumberRunning = false;
    @Getter
    @Setter
    private static boolean globalmute = false;
    @Getter
    @Setter
    private static boolean slowchat = false;
    @Getter
    private static final String[] combatLog = new String[]{
            "tpa", "tpaccept", "tpadeny", "p", "plot", "warp"};

    @Getter
    private static final String header = "%nl%&8» &e&lSkySoup &8┃ &7SkyPvP Server%nl%&7Du spielst auf&8: &askypvp%nl%%nl%&7Registrierte Spieler&8: &6&l%todaycount%%nl%&7Spieler Online&8: &e&l%size of all players%%nl%";
    @Getter
    private static final String footer = "%nl%&7Heute schon gevotet§8? /&evote%nl%&7Spenden&8: /&eshop &8┃ &7Discord&8: &edc.skysoup.eu%nl%                                                                      ";
    @Getter
    private static final BukkitController bukkitController = new BukkitController();
    @Getter
    private static final ChatController chatController = new ChatController();
    @Getter
    private static final ClanController clanController = new ClanController();
    @Getter
    private static final ScoreboardController scoreboardController = new ScoreboardController();
    @Getter
    private static final TablistController tablistController = new TablistController();
    @Getter
    private static final ToggleController toggleController = new ToggleController();
    @Getter
    private static final TrophyShopController trophyShopController = new TrophyShopController();
    @Getter
    private static final TaskController taskController = new TaskController();
    @Getter
    private static final KitController kitController = new KitController();
    @Getter
    private static final TeleportCooldownController teleportCooldowenController = new TeleportCooldownController(eu.skysoup.skypvp.SkyPvP.getINSTANCE(), 5);
    @Getter
    private static final GutscheinController gutscheinController = new GutscheinController();
    @Getter
    private static final CasinoController casinoController = new CasinoController();
    @Getter
    private static final RewardController rewardController = new RewardController();
    @Getter
    private static final BlacklistController blacklistController = new BlacklistController();
    @Getter
    private static final MotdController motdController = new MotdController();
    @Getter
    private static final AirdropController airDropController = new AirdropController();
    @Getter
    private static final HologramController hologramController = new HologramController();
    @Getter
    private static final CombatController combatController = new CombatController();
    @Getter
    private static final ConfigController configController = new ConfigController();
    @Getter
    private static final PlotRandController plotRandController = new PlotRandController();
    @Getter
    private static final PlotWandController plotWandController = new PlotWandController();

    @Getter
    private static final MessageUtil messageUtil = new MessageUtil();
    @Getter
    private static final PermissionUtil permissionUtil = new PermissionUtil();
    @Getter
    private static final RunnableUtil runnableUtil = new RunnableUtil();
    @Getter
    private static final UUIDFetcher uuidFetcher = new UUIDFetcher();
    @Getter
    private static final PlayerUtil playerUtil = new PlayerUtil();
    @Getter
    private static final LabyModUtil labymodUtil = new LabyModUtil();
    @Getter
    private static final PlotSquaredUtil plotSquaredUtil = new PlotSquaredUtil();
}
