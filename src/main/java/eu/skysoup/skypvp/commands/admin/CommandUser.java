package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.data.implementorings.SettingTypes;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.data.implementorings.UserValues;
import eu.skysoup.skypvp.utils.impl.StringUtil;
import eu.skysoup.skypvp.utils.impl.ValueUtil;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created: 09.02.2023 20:13
 *
 * @author thvf
 */
public class CommandUser implements CommandExecutor {


    private UserController userController = new UserController();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;

            final Player player = (Player) sender;

            if (args.length == 2 && args[0].equalsIgnoreCase("info")) {

                final String targetName = args[1];

                try {
                    CompletableFuture.supplyAsync(() -> userController.getUsers().contains(Data.getUuidFetcher().getUUID(targetName))).thenAcceptAsync(result -> {

                        if (!result) {
                            Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");
                            return;
                        }

                        userController = new UserController().getUserByUUID(Data.getUuidFetcher().getUUID(targetName));

                        Data.getMessageUtil().sendMessage(player, "§aUser erfolgreich gefunden!");
                        player.sendMessage("");
                        Data.getMessageUtil().sendMessage(player, "§7UUID§8: §e§o" + Data.getUuidFetcher().getUUID(targetName));
                        player.sendMessage("");
                        EnumSet.allOf(StatisticTypes.class).forEach(all -> {
                            Data.getMessageUtil().sendMessage(player, StringUtil.capitalize(all.name().toLowerCase()) + "§8: §e" + userController.getStatisticByType(all));
                        });
                        Data.getMessageUtil().sendMessage(player, "§8§m---------------");
                        EnumSet.allOf(SettingTypes.class).forEach(all -> {
                            Data.getMessageUtil().sendMessage(player, StringUtil.capitalize(all.name().toLowerCase()) + "§8: §e" + userController.getSettingFromUser(all));
                        });
                        Data.getMessageUtil().sendMessage(player, "§8§m---------------");
                        EnumSet.allOf(UserValues.class).forEach(all -> {
                            Data.getMessageUtil().sendMessage(player, StringUtil.capitalize(all.name().toLowerCase()) + "§8: §e" + (userController.getCustomValue(all) == null ? "Kein Wert" : userController.getCustomValue(all)));
                        });


                        player.sendMessage("");

                    });

                } catch (Exception ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");
                }
                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                Data.getMessageUtil().sendMessage(player, "§7Folgende User existieren§8.");
                player.sendMessage("");
                CompletableFuture.supplyAsync(() -> {
                    for (UUID uuid : userController.getUsers()) {
                        Data.getMessageUtil().sendClickableMessageCommand(player, (Bukkit.getPlayer(uuid) == null ? Data.getUuidFetcher().getName(uuid) : Bukkit.getPlayer(uuid).getName()) + " §8(" + (Bukkit.getPlayer(uuid) == null ? "§c§lOFFLINE" : "§a§lONLINE") + "§8)", "§8/§eUser info " + (Bukkit.getPlayer(uuid) == null ? Data.getUuidFetcher().getName(uuid) : Bukkit.getPlayer(uuid).getName()), "/user info " + (Bukkit.getPlayer(uuid) == null ? Data.getUuidFetcher().getName(uuid) : Bukkit.getPlayer(uuid).getName()));
                    }
                    return this;
                });
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("check")) {
                final String targetName = args[1];

                try {

                    if (Bukkit.getPlayer(targetName) != null) {
                        Data.getMessageUtil().sendMessage(player, "§aDieser Spieler ist aktuell online!");
                        return true;
                    }

                    CompletableFuture.supplyAsync(() -> userController.getUsers().contains(Data.getUuidFetcher().getUUID(targetName))).thenAcceptAsync(result -> {

                        if (!result) {
                            Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");
                            return;
                        }


                        userController = new UserController().getUserByUUID(Data.getUuidFetcher().getUUID(targetName));

                        Data.getMessageUtil().sendMessage(player, "§aUser erfolgreich gefunden!");
                        player.sendMessage("");


                        long offlineSince = (System.currentTimeMillis() - userController.getStatisticByType(StatisticTypes.OFFLINESINCE));
                        String formattedTime = ValueUtil.formatTime(offlineSince);

                        Data.getMessageUtil().sendMessage(player, "§e" + targetName + " §7war zuletzt vor §e" + formattedTime + " §7online§8.");


                        player.sendMessage("");

                    });
                } catch (Exception ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");

                }

                return true;
            }

            if (args.length == 4 && args[0].equalsIgnoreCase("set")) {
                final String targetName = args[1];
                try {
                    CompletableFuture.supplyAsync(() -> userController.getUsers().contains(Data.getUuidFetcher().getUUID(targetName))).thenAcceptAsync(result -> {

                        if (!result) {
                            Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");
                            return;
                        }


                        userController = new UserController().getUserByUUID(Data.getUuidFetcher().getUUID(targetName));


                        final StatisticTypes statisticTypes = StatisticTypes.valueOf(args[2].toUpperCase());
                        final long wert = Long.parseLong(args[3]);

                        if (wert < 0) {
                            Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an!");
                            return;
                        }


                        userController.setStatisticFromUser(statisticTypes, wert);
                        Data.getMessageUtil().sendMessage(player, "§e" + StringUtil.capitalize(statisticTypes.name().toLowerCase()) + " §7von §e" + targetName + " §7auf §6" + NumberFormat.getInstance().format(wert) + " §7gesetzt§8!");
                    });

                } catch (Exception ignored) {
                    Data.getMessageUtil().sendSyntax(player,
                            "§8/§eUser §7<set> <spieler> <stats> <wert>",
                            "§8/§eUser §7<info> <spieler>",
                            "§8/§eUser §7<check> <spieler>",
                            "§8/§eUser §7<list>"
                    );

                }
                return true;
            }

            if (args.length == 4 && args[0].equalsIgnoreCase("setstats")) {

                try {
                    final Player target = Bukkit.getPlayer(args[1]);
                    final Statistic statistic = Statistic.valueOf(args[2].toUpperCase());
                    final int wert = Integer.parseInt(args[3]);

                    if (target == null) {
                        Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                        return true;
                    }

                    target.setStatistic(statistic, wert);
                    Data.getMessageUtil().sendMessage(player, "§e" + StringUtil.capitalize(statistic.name().toLowerCase()) + " §7von §e" + target.getName() + " §7auf §6" + NumberFormat.getInstance().format(wert) + " §7gesetzt§8.");
                } catch (Exception ignored) {
                    EnumSet.allOf(Statistic.class).forEach(all -> {
                        Data.getMessageUtil().sendMessage(player, StringUtil.capitalize(all.name().toLowerCase()));
                    });
                }

                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eUser §7<set> <spieler> <stats> <wert>",
                    "§8/§eUser §7<setstats> <spieler> <statistic> <wert>",
                    "§8/§eUser §7<info> <spieler>",
                    "§8/§eUser §7<check> <spieler>",
                    "§8/§eUser §7<list>"
            );
        }
        return false;
    }
}
