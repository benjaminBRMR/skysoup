package eu.skysoup.skypvp.listeners.player;

import com.avaje.ebean.validation.NotNull;
import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.controller.other.LagerPlatzController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.GutscheinTypes;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.inventories.AcceptInventory;
import eu.skysoup.skypvp.inventories.ClanTagInventory;
import eu.skysoup.skypvp.utils.TempCooldown;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.NumberFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created: 25.01.2023 00:12
 *
 * @author thvf
 */
public class InteractListener implements Listener {

    private final TempCooldown epcooldown = new TempCooldown();
    private final TempCooldown gapcooldown = new TempCooldown();
    private final TempCooldown runecooldown = new TempCooldown();


    @EventHandler(priority = EventPriority.HIGH)
    public void onGappleEat(final PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();

        if (event.getItem().getDurability() == 1) {
            event.setCancelled(true);
            Data.getMessageUtil().sendMessage(player, "§7Verzauberte Goldäpfel sind §c§ndauerhaft§r §7deaktiviert!");
            return;
        }


        if (event.getItem().getType().equals(Material.GOLDEN_APPLE)) {
            if (!Data.getToggleController().isToggled(ToggleController.Types.GAPS)) {
                event.setCancelled(true);
                Data.getMessageUtil().sendMessage(player, "§cAktuell sind Goldpäpfel deaktiviert!");
                return;
            }
            if (!player.hasPermission(Permissions.ADMIN.getPermission())) {
                if (gapcooldown.isDone(player.getUniqueId())) {
                    Data.getPlayerUtil().sendActionbar(player, "§7Du hast §aerfolgreich §7einen Goldäpfel konsumiert§8!");
                    gapcooldown.addPlayerToCooldown(player, 180);
                } else {
                    Data.getMessageUtil().sendMessage(player, "Bitte warte noch §e" + ValueUtil.timeToString(TimeUnit.SECONDS.toMillis(gapcooldown.getReminding(player.getUniqueId())), true, true) + "§8.");
                    event.setCancelled(true);
                }
                return;
            }
            Data.getPlayerUtil().sendActionbar(player, "§7Du hast §aerfolgreich §7einen Goldäpfel konsumiert§8!");

            return;
        }


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileLaunch(final ProjectileLaunchEvent event) {

        final Player player = (Player) event.getEntity().getShooter();


        if (event.getEntity() instanceof EnderPearl) {
            if (player.getWorld().getName().equals("Arena")) {
                event.setCancelled(true);
                player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                player.playSound(player.getLocation(), Sound.BURP, 5F, 0.1F);
                Data.getMessageUtil().sendMessage(player, "§cIn der Arena sind Enderperlen deaktiviert!");
                return;
            }

            if (!Data.getToggleController().isToggled(ToggleController.Types.EP)) {
                event.setCancelled(true);
                player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                Data.getMessageUtil().sendMessage(player, "§cAktuell sind Enderperlen deaktiviert!");
                return;
            }


            if (!player.hasPermission(Permissions.ADMIN.getPermission())) {
                if (!epcooldown.isDone(player.getUniqueId())) {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                    player.playSound(player.getLocation(), Sound.BURP, 5F, 0.1F);
                    return;
                }
                epcooldown.addPlayerToCooldown(player.getUniqueId(), 5);
            }
            return;
        }
    }


    @NotNull
    @EventHandler
    public void onGutscheinInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = player.getItemInHand();
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        if (item.getItemMeta() == null || item.getItemMeta().getLore() == null || item.getItemMeta().getDisplayName() == null)
            return;

        if (item.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §6§lTOKEN")) {
            final String type = item.getItemMeta().getLore().get(2).replaceAll(" §8┌ §7Gutschein-Type§8: §e§l", "").trim();
            final String amount = item.getItemMeta().getLore().get(3).replaceAll(" §8└ §7Gutschein-Wert§8: §e§l", "").replaceAll(",", "").trim();

            final GutscheinTypes finalType = GutscheinTypes.valueOf(type.toUpperCase());
            final long finalAmount = Long.parseLong(amount);
            int multiplier = item.getAmount();

            if (finalType == GutscheinTypes.TOKEN) {

                if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
                if (finalAmount < 1) return;
                if (multiplier < 2) multiplier = 1;


                for (int i = 0; i < multiplier; i++) {

                    userController.setStatisticFromUser(StatisticTypes.MONEY, userController.getStatisticByType(StatisticTypes.MONEY) + finalAmount);
                    Data.getPlayerUtil().sendActionbar(player, "§8(§a§l✔§8) §7Gutschein eingelöst§8: §6§lTOKEN§8: §6§l$" + NumberFormat.getInstance().format(finalAmount * multiplier) + " §8(§8x§e" + multiplier + "§8)");
                    Data.getPlayerUtil().removeHand(player);
                }
                player.playSound(player.getLocation(), Sound.BURP, 10, 0.1F);

            }
            return;
        }
        if (item.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §c§lSTATSRESET")) {

            if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

            final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                if (clicked) {
                    player.closeInventory();
                    Data.getPlayerUtil().removeHand(player);

                    userController.setStatisticFromUser(StatisticTypes.KILLS, 0);
                    userController.setStatisticFromUser(StatisticTypes.DEATHS, 0);
                    userController.setStatisticFromUser(StatisticTypes.KILLSTREAK, 0);
                    Data.getMessageUtil().sendMessage(player, "§7Deine Statistiken wurden §aerfolgreich §7zurückgesetzt§8.");
                    player.playSound(player.getLocation(), Sound.BURP, 10, 0.1F);


                } else {
                    player.closeInventory();
                    Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);

                }
            }, "§8§oKlicke auf einen der beiden Köpfe, um",
                    "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                    "",
                    "§8┌ §7Folgende Statistiken werden auf §c0 §7gesetzt",
                    "§8├ §aDeine Kills",
                    "§8├ §aDeine Tode",
                    "§8└ §aDeine Killstreak",
                    "");

            acceptInventory.openGUI();
            return;
        }


        if (item.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §e§l+1 §6§lCLANPLATZ")) {

            if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

            if (!Data.getClanController().isInClan(player)) {
                Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");
                return;
            }

            if (Data.getClanController().getMaxMitglieder(Data.getClanController().getClan(player)) >= 25) {
                Data.getMessageUtil().sendMessage(player, "§cDas Clan-Platzmaximum liegt bei 25 Mitgliedern!");
                return;
            }

            Data.getClanController().setMaxMitglieder(Data.getClanController().getClan(player), Data.getClanController().getMaxMitglieder(Data.getClanController().getClan(player)) + 1);

            for (String mitglieder : Data.getClanController().getMitglieder(Data.getClanController().getClan(player))) {

                if (Bukkit.getPlayer(UUID.fromString(mitglieder)) == null) continue;

                Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(mitglieder)), "§a" + player.getName() + " §7hat das Clan-Platzmaximum um einen Platz erhöht!" + " §8(§a" + Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).size() + "§8/§2" + Data.getClanController().getMaxMitglieder(Data.getClanController().getClan(player)) + "§8)");
            }
            Data.getPlayerUtil().removeHand(player);
            player.playSound(player.getLocation(), Sound.BURP, 10, 0.1F);
            return;
        }

        if (item.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §6§l" + GutscheinTypes.CLAN.getPrefix())) {

            if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

            if (!Data.getClanController().isInClan(player)) {
                Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");
                return;
            }


            if (!Data.getClanController().isClanBesitzer(player)) {
                Data.getMessageUtil().sendMessage(player, "§cNur der Clanbesitzer kann diesen Gutschein einlösen!");
                return;
            }

            new ClanTagInventory(player).openGUI();
            return;

        }

        if (item.getItemMeta().getDisplayName().equals("§8» §7Gutschein§8: §6§l" + GutscheinTypes.MERGE.name().toUpperCase())) {
            event.setCancelled(true);

            if (!Data.getPlotSquaredUtil().getPlotOwnerPlayerIsStandingOn(player).equalsIgnoreCase(player.getName())) {
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                Data.getMessageUtil().sendMessage(player, "§cDu bist nicht der Eigentümer dieses Plots§8!");
                return;
            }

            final int direction = Data.getPlotSquaredUtil().getCorrectDirection(player);

            if (direction == -1) {
                Data.getMessageUtil().sendMessage(player, "§cDieses Plot kann nicht zusammengeführt werden!");
                return;
            }

            try {
                Data.getPlotSquaredUtil().getPlotPlayerIsStandingOn(player).autoMerge(direction, 1, player.getUniqueId(), true);

                player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 14);
                Data.getMessageUtil().sendMessage(player, "§aDein Plot wurde erfolgreich zusammengeführt§8.");
                Data.getPlayerUtil().removeHand(player);
            } catch (Exception ignored) {
                Data.getMessageUtil().sendMessage(player, "§cFehler beim zusammenführen deines Plots!");

            }


            return;
        }

        if (item.getItemMeta().getDisplayName().equals("§8» §7Rune des Todes")) {
            event.setCancelled(true);
            if (!player.hasPermission(Permissions.ADMIN.getPermission())) {
                if (!runecooldown.isDone(player.getUniqueId())) {
                    Data.getMessageUtil().sendMessage(player, "Bitte warte noch §e" + runecooldown.getReminding(player.getUniqueId()) + " §7Sekunde(n)§8.");
                } else {
                    final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                        if (clicked) {
                            player.closeInventory();
                            Data.getPlayerUtil().removeHand(player);
                            runecooldown.addPlayerToCooldown(player.getUniqueId(), 60);
                            Data.getMessageUtil().sendMessage(player, "§7Du hast eine §eRune des Todes §7eingelöst§8.");
                            player.playSound(player.getLocation(), Sound.BURP, 10, 0.1F);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 800, 1, false, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1, false, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 3000, 1, false, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1, false, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3000, 1, false, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3000, 1, false, true));
                        } else {
                            Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                            player.closeInventory();
                        }
                    },
                            "§8§oKlicke auf einen der beiden Köpfe, um",
                            "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                            "",
                            "§8┌ §7Folgende Effete bekommst Du:",
                            "§8├ §fSpeed II",
                            "§8├ §fRegenerations II",
                            "§8├ §fAbsorption II",
                            "§8├ §fSprungkraft II",
                            "§8├ §fFeuerschutz II",
                            "§8└ §fResistenz II",
                            ""
                    );

                    acceptInventory.openGUI();

                }
            } else {
                final AcceptInventory acceptInventory = new AcceptInventory(player, clicked -> {
                    if (clicked) {
                        player.closeInventory();
                        Data.getPlayerUtil().removeHand(player);
                        Data.getMessageUtil().sendMessage(player, "§7Du hast eine §eRune des Todes §7eingelöst§8.");
                        player.playSound(player.getLocation(), Sound.BURP, 10, 0.1F);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 800, 1, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 3000, 1, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3000, 1, false, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3000, 1, false, true));
                    } else {
                        Data.getMessageUtil().sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen!");
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 0.1F);
                        player.closeInventory();
                    }
                },
                        "§8§oKlicke auf einen der beiden Köpfe, um",
                        "§8§odeine Auswahl abzulehnen oder zu bestätigen.",
                        "",
                        "§8┌ §7Folgende Effete bekommst Du:",
                        "§8├ §fSpeed II",
                        "§8├ §fRegenerations II",
                        "§8├ §fAbsorption II",
                        "§8├ §fSprungkraft II",
                        "§8├ §fFeuerschutz II",
                        "§8└ §fResistenz II",
                        ""
                );
                acceptInventory.openGUI();
            }
            return;
        }
    }

    @EventHandler
    public void onEnderChestInteract(final PlayerInteractEvent event) {

        final Player player = event.getPlayer();
        final LagerPlatzController lagerPlatzController = new LagerPlatzController(player.getUniqueId());
        final UserController userController = new UserController().getUserByUUID(player.getUniqueId());

        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.ENDER_CHEST && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
            if (!lagerPlatzController.hasLagerPlatz()) {
                Data.getMessageUtil().sendMessage(player, "§cDu besitzt aktuell noch keinen Lagerplatz!");
                Data.getMessageUtil().sendMessage(player, "§cKaufe dir jetzt einen im Shop §8'§f§l/SHOP§8'.");
                return;
            }

            lagerPlatzController.openLagerPlatzForOwner();
            return;
        }
    }


}
