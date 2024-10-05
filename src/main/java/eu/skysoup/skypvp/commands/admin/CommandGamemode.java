package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 17.02.2023 13:29
 *
 * @author thvf
 */
public class CommandGamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {

            final Player player = (Player) sender;

            if (!player.hasPermission(Permissions.GAMEMODE.getPermission())) {
                player.sendMessage(PrefixController.getSkyPvP() + "§cDu hast dazu keine Rechte! §8(§7§o" + Permissions.GAMEMODE.getPermission() + "§8)");
            } else {

                if (args.length == 1 && args[0].equalsIgnoreCase("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(PrefixController.getSkyPvP() + "§7Dein Spielmodus wurde auf §eÜberleben §7gesetzt§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(PrefixController.getSkyPvP() + "§7Dein Spielmodus wurde auf §eKreativ §7gesetzt§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(PrefixController.getSkyPvP() + "§7Dein Spielmodus wurde auf §eAbenteuer §7gesetzt§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(PrefixController.getSkyPvP() + "§7Dein Spielmodus wurde auf §eZuschauer §7gesetzt§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }


                //

                if (args.length == 2 && args[0].equalsIgnoreCase("0")) {

                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                        return true;
                    }

                    target.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(PrefixController.getSkyPvP() + "§e" + target.getName() + " §7ist nun im Spielmodus §eÜberleben§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("1")) {
                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                        return true;
                    }

                    target.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(PrefixController.getSkyPvP() + "§e" + target.getName() + " §7ist nun im Spielmodus §eKreativ§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("2")) {
                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                        return true;
                    }

                    target.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(PrefixController.getSkyPvP() + "§e" + target.getName() + " §7ist nun im Spielmodus §eAbenteuer§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("3")) {
                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                        return true;
                    }

                    target.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(PrefixController.getSkyPvP() + "§e" + target.getName() + " §7ist nun im Spielmodus §eZuschauer§8.");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10, 18.4F);
                    return true;
                }


                if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                    player.sendMessage(PrefixController.getSkyPvP() + "§7Folgende §eSpieler §7sind in dem §eSpielmodus§8:");
                    player.sendMessage(PrefixController.getSkyPvP() + "§7Gamemode Überleben:");

                    for (Player all : Bukkit.getOnlinePlayers()) {

                        if (all.getGameMode() == GameMode.SURVIVAL)
                            player.sendMessage(PrefixController.getSkyPvP() + " §8┃ §e" + all.getName());
                    }
                    player.sendMessage("");


                    player.sendMessage(PrefixController.getSkyPvP() + "§7Gamemode Kreativ:");

                    for (Player all : Bukkit.getOnlinePlayers()) {

                        if (all.getGameMode() == GameMode.CREATIVE)
                            player.sendMessage(PrefixController.getSkyPvP() + " §8┃ §e" + all.getName());
                    }
                    player.sendMessage("");


                    player.sendMessage(PrefixController.getSkyPvP() + "§7Gamemode Abenteuer:");

                    for (Player all : Bukkit.getOnlinePlayers()) {

                        if (all.getGameMode() == GameMode.ADVENTURE)
                            player.sendMessage(PrefixController.getSkyPvP() + " §8┃ §e" + all.getName());
                    }
                    player.sendMessage("");


                    player.sendMessage(PrefixController.getSkyPvP() + "§7Gamemode Zuschauer:");

                    for (Player all : Bukkit.getOnlinePlayers()) {

                        if (all.getGameMode() == GameMode.SPECTATOR)
                            player.sendMessage(PrefixController.getSkyPvP() + " §8┃ §e" + all.getName());
                    }
                    player.sendMessage("");
                    return true;
                }
                player.sendMessage(PrefixController.getSkyPvP() + "§cFalscher Syntax!");
                player.sendMessage(PrefixController.getSkyPvP() + "§8/§eGamemode §7<0,1,2,3> <Spieler>");
                player.sendMessage(PrefixController.getSkyPvP() + "§8/§eGamemode §7<List>");

            }
        }
        return false;
    }
}
