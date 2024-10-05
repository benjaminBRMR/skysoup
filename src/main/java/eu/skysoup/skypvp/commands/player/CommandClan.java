package eu.skysoup.skypvp.commands.player;

import eu.skysoup.skypvp.SkyPvP;
import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.inventories.clan.ClanInventory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created: 23.01.2023 13:15
 *
 * @author thvf
 */
public class CommandClan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            final Player player = (Player) sender;


            if (args.length == 2 && args[0].equalsIgnoreCase("create")) {
                if (!Data.getClanController().isInClan(player)) {
                    final String clanName = args[1];
                    if (!Data.getClanController().isNameValid(clanName)) {
                        Data.getMessageUtil().sendMessage(player, "§cDer angegebene Clanname ist nicht gültig!");
                        return true;
                    }
                    if (Data.getClanController().existsClan(clanName)) {
                        Data.getMessageUtil().sendMessage(player, "§cEs existiert bereits ein Clan mit dem Namen §8'§7" + clanName.toUpperCase() + "§8'§c!");
                        return true;
                    }
                    if (clanName.length() > 6) {
                        Data.getMessageUtil().sendMessage(player, "§cDer angegebene Clanname ist zu lange! < 6");
                        return true;
                    }
                    if (clanName.length() < 4) {
                        Data.getMessageUtil().sendMessage(player, "§cDer angegebene Clanname ist zu kurz! > 4");
                        return true;
                    }

                    for (String arr : Data.getChatController().getBlacklistedWords()) {

                        if (clanName.contains(arr.toLowerCase())) {
                            Data.getMessageUtil().sendMessage(player, "§cDer angegebene Clanname ist nicht gültig!");
                            return true;
                        }
                    }


                    Data.getClanController().createClan(clanName, player);
                    Data.getMessageUtil().sendMessage(player, "§aErfolg! Dein Clan §8'§7" + clanName.toUpperCase() + "§8' §awurde erfolgreich gegründet!");
                    Bukkit.getOnlinePlayers().forEach(all -> Data.getMessageUtil().sendMessage(all, "§7" + player.getName() + " §ahat den Clan §8'§7" + clanName.toUpperCase() + "§8' §agegründet!"));
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 50, 18.4F);
                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist bereits in einem Clan vertreten!");
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("info")) {

                final String clanName = args[1];

                if (!Data.getClanController().existsClan(clanName)) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Clan wurde nicht gefunden!");
                    return true;
                }

                new ClanInventory(player, clanName.toLowerCase()).openGUI();

                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("delete")) {
                if (Data.getClanController().isInClan(player)) {
                    if (Data.getClanController().isClanBesitzer(player)) {

                        Bukkit.getOnlinePlayers().forEach(all -> Data.getMessageUtil().sendMessage(all, "§7" + player.getName() + " §chat den Clan §8'§7" + Data.getClanController().getClan(player).toUpperCase() + "§8' §cgelöscht!"));


                        Data.getMessageUtil().sendMessage(player, "§cDein Clan §8'§7" + Data.getClanController().getClan(player) + "§8' §cwurde gelöscht!");
                        Data.getClanController().deleteClan(Data.getClanController().getClan(player));


                        return true;
                    }
                    Data.getMessageUtil().sendMessage(player, "§cDeinen Clan §8'§7" + Data.getClanController().getClan(player) + "§8' §ckann nur der Besitzer löschen!");
                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                if (Data.getClanController().isInClan(player)) {


                    Data.getMessageUtil().sendMessage(player, "§aFolgende Informationen wurden abgerufen:");
                    Data.getMessageUtil().sendMessage(player, "");


                    Data.getMessageUtil().sendMessage(player, "§aClan: §7" + Data.getClanController().getClan(player).toUpperCase() + " §8┃ §aClan Besitzer: §7" + Data.getUuidFetcher().getName(UUID.fromString(Data.getClanController().getClanBesitzer(player))) +
                            " §8(" + (Bukkit.getPlayer(UUID.fromString(Data.getClanController().getClanBesitzer(player))) == null ? "§c§lOFFLINE" : "§a§lONLINE") + "§8)");

                    Data.getMessageUtil().sendMessage(player, "§aClan Exp: §7" + NumberFormat.getInstance().format(Data.getClanController().getClanEXP(Data.getClanController().getClan(player))) + " §8┃ §aClan Erstellt: §7" + new SimpleDateFormat("dd.MM.yyyy").format(new Date(Data.getClanController().getClanErstellt(Data.getClanController().getClan(player)))));

                    Data.getMessageUtil().sendMessage(player, "§aClan Mitglieder §8(§7" + (Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).size() - 1) + "§7/§7" + Data.getClanController().getMaxMitglieder(Data.getClanController().getClan(player)) + "§8)");
                    for (String uuid : Data.getClanController().getMitglieder(Data.getClanController().getClan(player))) {

                        if (Objects.equals(uuid, Data.getClanController().getClanBesitzer(player))) continue;

                        Data.getMessageUtil().sendMessage(player, "§7" + Data.getUuidFetcher().getName(UUID.fromString(uuid)) +
                                " §8(" + (Bukkit.getPlayer(UUID.fromString(uuid)) == null ? "§c§lOFFLINE" : "§a§lONLINE") + "§8)");
                    }

                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("invite")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (!Data.getClanController().isInClan(player)) {
                    Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");
                    return true;
                }

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                if (!Data.getClanController().isInClan(target)) {


                    if (Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).size() >= Data.getClanController().getMaxMitglieder(Data.getClanController().getClan(player))) {
                        Data.getMessageUtil().sendMessage(player, "§cDieser Clan hat bereits das Mitgliedermaximum erreicht!");
                        return true;

                    }

                    if (Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).size() >= 20) {
                        Data.getMessageUtil().sendMessage(player, "§cDas Clan-Mitgliedermaximum liegt bei 20 Mitgliedern!");
                        return true;
                    }

                    if (!Data.getClanController().isClanBesitzer(player)) {
                        Data.getMessageUtil().sendMessage(player, "§cNur der Clanbesitzer kann Mitglieder einladen.");
                        return true;
                    }

                    if (Data.getClanInvites().containsValue(player)) {
                        Data.getMessageUtil().sendMessage(player, "§cDu hast bereits eine Claneinladung gesendet.");
                        return true;
                    }

                    Data.getClanInvites().put(player, target);
                    Data.getClanInvites().put(target, player);
                    Data.getMessageUtil().sendMessage(player, "§aDu hast eine Claneinladung an §7" + target.getName() + " §agesendet.");
                    Data.getMessageUtil().sendMessage(target, "§e" + player.getName() + " §7hat dich in den Clan §e§l" + Data.getClanController().getClan(player).toUpperCase() + " §7eingeladen§8.");
                    Data.getMessageUtil().sendClickableMessageCommand(target, "§7Klicke um die Einladung §a§lANZUNEHMEN", "§a§lANNEHMEN", "/clan accept");
                    Data.getMessageUtil().sendClickableMessageCommand(target, "§7Klicke um die Einladung §c§lABZULEHNEN", "§c§lABLEHNEN", "/clan deny");

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (Data.getClanInvites().containsValue(player) && Data.getClanInvites().containsValue(target)) {
                                Data.getMessageUtil().sendMessage(player, "§cDeine Claneinladung an §7" + target.getName() + " §cist abgelaufen.");
                                Data.getMessageUtil().sendMessage(target, "§cDie Claneinladung von §7" + player.getName() + " §cist abgelaufen.");
                                Data.getClanInvites().remove(player);
                                Data.getClanInvites().remove(target);
                            }
                        }
                    }.runTaskLater(SkyPvP.getINSTANCE(), Data.getRunnableUtil().getTimeFromMinutes(1));
                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDieser Spieler ist bereits in einem Clan vertreten!");

                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("accept")) {

                if (Data.getClanInvites().get(player) == null) {
                    Data.getMessageUtil().sendMessage(player, "§cDu hast keine Claneinladungen!");
                    return true;
                }

                if (!Data.getClanController().isInClan(player)) {

                    if (!Data.getClanInvites().containsValue(player)) {
                        Data.getMessageUtil().sendMessage(player, "§cDu hast keine Claneinladungen!");
                        return true;
                    }

                    final Player target = Data.getClanInvites().get(player);
                    Data.getClanController().addPlayerToClan(Data.getClanController().getClan(Data.getClanInvites().get(player)), player.getUniqueId().toString());
                    Data.getMessageUtil().sendMessage(player, "§aDu bist dem Clan §7" + Data.getClanController().getClan(target).toUpperCase() + " §abeigetreten!");

                    for (String mitglieder : Data.getClanController().getMitglieder(Data.getClanController().getClan(target))) {

                        if (Bukkit.getPlayer(UUID.fromString(mitglieder)) == null || !Bukkit.getPlayer(UUID.fromString(mitglieder)).isOnline())
                            continue;


                        Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(mitglieder)), "§7" + player.getName() + " §aist dem Clan beigetreten.");
                    }

                    Data.getMessageUtil().sendMessage(target, "§7" + player.getName() + " §ahat die Claneinladung angenommen!");

                    Data.getClanInvites().remove(player);
                    Data.getClanInvites().remove(target);

                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist bereits in einem Clan vertreten!");
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("deny")) {

                if (!Data.getClanController().isInClan(player)) {

                    if (!Data.getClanInvites().containsValue(player)) {
                        Data.getMessageUtil().sendMessage(player, "§cDu hast keine Claneinladungen!");
                        return true;
                    }

                    final Player target = Data.getClanInvites().get(player);

                    Data.getMessageUtil().sendMessage(target, "§7" + player.getName() + " §chast deine Claneinladung abgelehnt!");
                    Data.getMessageUtil().sendMessage(player, "§cDu hast die Claneinladung abgelehnt!");

                    Data.getClanInvites().remove(player);
                    Data.getClanInvites().remove(target);


                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist bereits in einem Clan vertreten!");
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("kick")) {

                if (Data.getClanController().isInClan(player)) {


                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        final String playerName = args[1];


                        // ASYNC SONST LAG
                        CompletableFuture.supplyAsync(() -> {
                            if (!Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).contains(Data.getUuidFetcher().getUUID(playerName).toString())) {
                                Data.getMessageUtil().sendMessage(player, "§cDieses Mitglied ist nicht im deinem Clan vertreten!");
                                return true;
                            }
                            return this;
                        });


                        for (String memberUUIDS : Data.getClanController().getMitglieder(Data.getClanController().getClan(player))) {
                            if (Objects.equals(Data.getUuidFetcher().getName(UUID.fromString(memberUUIDS)), playerName)) {
                                if (playerName.equals(player.getName())) {
                                    Data.getMessageUtil().sendMessage(player, "§cDu kannst dich nicht selber kicken!");
                                    return true;
                                }

                                if (!Data.getClanController().isClanBesitzer(player)) {
                                    Data.getMessageUtil().sendMessage(player, "§cNur der Clanbesitzer kann Mitglieder rauswerfen.");
                                    return true;
                                }

                                if (!Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).contains(Data.getUuidFetcher().getUUID(playerName).toString())) {
                                    Data.getMessageUtil().sendMessage(player, "§cDieses Mitglied ist nicht im deinem Clan vertreten!");
                                    return true;
                                }

                                Data.getClanController().removePlayerFromClan(Data.getClanController().getClan(player), Data.getUuidFetcher().getUUID(playerName).toString());

                                for (String mitglieder : Data.getClanController().getMitglieder(Data.getClanController().getClan(player))) {

                                    if (Bukkit.getPlayer(UUID.fromString(mitglieder)) == null || !Bukkit.getPlayer(UUID.fromString(mitglieder)).isOnline())
                                        continue;

                                    Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(mitglieder)), "§7" + playerName + " §cwurde aus dem Clan geworfen!");
                                }

                            }
                        }
                        return true;
                    }

                    if (target.getName().equals(player.getName())) {
                        Data.getMessageUtil().sendMessage(player, "§cDu kannst dich nicht selber kicken!");
                        return true;
                    }

                    if (!Data.getClanController().isClanBesitzer(player)) {
                        Data.getMessageUtil().sendMessage(player, "§cNur der Clanbesitzer kann Mitglieder rauswerfen.");
                        return true;
                    }

                    if (!Data.getClanController().getMitglieder(Data.getClanController().getClan(player)).contains(target.getUniqueId().toString())) {
                        Data.getMessageUtil().sendMessage(player, "§cDieses Mitglied ist nicht im deinem Clan vertreten!");
                        return true;
                    }

                    Data.getClanController().removePlayerFromClan(Data.getClanController().getClan(player), target.getUniqueId().toString());
                    Data.getMessageUtil().sendMessage(target, "§cDu wurdest auf dem Clan §7" + Data.getClanController().getClan(player).toUpperCase() + " §cgeworfen!");

                    for (String mitglieder : Data.getClanController().getMitglieder(Data.getClanController().getClan(player))) {
                        Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(mitglieder)), "§7" + target.getName() + " §cwurde aus dem Clan geworfen!");
                    }


                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");


                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {

                if (Data.getClanController().isInClan(player)) {

                    if (Data.getClanController().isClanBesitzer(player)) {
                        Data.getMessageUtil().sendMessage(player, "§cDu bist Clanbesitzer! Benutze /clan delete um fortzufahren.");
                        return true;
                    }


                    for (String mitglieder : Data.getClanController().getMitglieder(Data.getClanController().getClan(player))) {
                        Data.getMessageUtil().sendMessage(Bukkit.getPlayer(UUID.fromString(mitglieder)), "§7" + player.getName() + " §chat den Clan verlassen");
                    }

                    Data.getMessageUtil().sendMessage(player, "§cDu hast den Clan §7" + Data.getClanController().getClan(player).toUpperCase() + " §cverlassen!");
                    Data.getClanController().removePlayerFromClan(Data.getClanController().getClan(player), player.getUniqueId().toString());
                    return true;
                }
                Data.getMessageUtil().sendMessage(player, "§cDu bist aktuell in keinem Clan vertreten!");
                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eClan §7<create> §7<name>",
                    "§8/§eClan §7<invite> <spieler>",
                    "§8/§eClan §7<kick> <spieler>",
                    "§8/§eClan §7<info> <clan>",
                    "§8/§eClan §7<accept>",
                    "§8/§eClan §7<deny>",
                    "§8/§eClan §7<leave>",
                    "§8/§eClan §7<list>");

        }
        return false;
    }
}
