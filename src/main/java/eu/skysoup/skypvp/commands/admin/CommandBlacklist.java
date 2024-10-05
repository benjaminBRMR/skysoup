package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created: 10.02.2023 19:52
 *
 * @author thvf
 */
public class CommandBlacklist implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;
            final UserController userController = new UserController();


            if (args.length == 1 && !args[0].equalsIgnoreCase("list")) {

                final String target = args[0];


                try {
                    CompletableFuture.supplyAsync(() -> userController.getUsers().contains(Data.getUuidFetcher().getUUID(target))).thenAcceptAsync(result -> {

                        if (!result) {
                            Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");
                            return;
                        }

                        if (Data.getBlacklistController().isPlayerBlacklisted(Data.getUuidFetcher().getUUID(target))) {
                            Data.getBlacklistController().removePlayer(Data.getUuidFetcher().getUUID(target));
                            Data.getMessageUtil().sendMessage(player, "§7Der Spieler §e" + target + " §7ist nun §cnicht mehr §7geblacklisted§8.");

                        } else {

                            Data.getBlacklistController().addPlayer(Data.getUuidFetcher().getUUID(target));
                            Data.getMessageUtil().sendMessage(player, "§7Der Spieler §e" + target + " §7ist §anun §7geblacklisted§8.");
                            if (Bukkit.getPlayer(target).isOnline()) {
                                ((CraftPlayer) Bukkit.getPlayer(target)).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
                            }
                        }

                    });
                } catch (Exception ignored) {
                    Data.getMessageUtil().sendMessage(player, "§cKein User mit diesem Namen wurde gefunden!");
                }
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("ip")) {

                final String ip = args[1];

                if (Data.getBlacklistController().isIPBlacklisted(ip)) {
                    Data.getBlacklistController().removeIp(ip);
                    Data.getMessageUtil().sendMessage(player, "§7Die IP-Adresse §e" + ip + " §7ist nun §cnicht mehr §7geblacklisted§8.");
                    return true;
                }

                Data.getBlacklistController().addIP(ip);
                Data.getMessageUtil().sendMessage(player, "§7Die IP-Adresse §e" + ip + " §7ist §anun §7geblacklisted§8.");

                Bukkit.getOnlinePlayers().forEach(all -> {
                    if (all.getAddress() == null) return;
                    if (Objects.equals(all.getAddress().getHostString(), ip)) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
                    }
                });

                return true;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {


                Data.getMessageUtil().sendMessage(player, "§7Folgende Spieler sind geblacklisted§8:");


                CompletableFuture.supplyAsync(() -> {
                    Data.getBlacklistController().getBlacklisted().forEach(all -> {
                        Data.getMessageUtil().sendMessage(player, "§e" + Data.getUuidFetcher().getName(UUID.fromString(all)));
                    });
                    return this;
                });

                Data.getMessageUtil().sendMessage(player, "§7Folgende IPs sind geblacklisted§8:");

                Data.getBlacklistController().getBlacklistedIPs().forEach(all -> {
                    Data.getMessageUtil().sendMessage(player, "§e" + all);
                });

                return true;
            }

            Data.getMessageUtil().sendSyntax(player,
                    "§8/§eBlacklist §7<spieler>",
                    "§8/§eBlacklist §7<ip> <adresse>",
                    "§8/§eBlacklist §7<list>");

        }
        return false;
    }
}
