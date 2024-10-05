package eu.skysoup.skypvp.commands.team;

import eu.skysoup.skypvp.controller.PrefixController;
import eu.skysoup.skypvp.controller.UserController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created: 03.02.2023 13:57
 *
 * @author thvf
 */
public class CommandVanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.VANISH.getPermission(), true))
                return true;
            final Player player = (Player) sender;
            final UserController userController = new UserController().getUserByUUID(player.getUniqueId());


            if (args.length == 0) {

                if (Data.getVanished().contains(player)) {
                    Data.getVanished().remove(player);
                    Data.getMessageUtil().sendMessage(player, "§7Vanish wurde §c§lDEAKTIVIERT§8.");

                    Bukkit.getOnlinePlayers().forEach(all -> {
                        all.showPlayer(player);
                    });
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);

                } else {
                    Data.getVanished().add(player);
                    Data.getMessageUtil().sendMessage(player, "§7Vanish wurde §a§lAKTIVIERT§8.");

                    Bukkit.getOnlinePlayers().forEach(all -> {
                        if (all.hasPermission(Permissions.TEAM.getPermission())) {
                            all.showPlayer(player);
                        } else {
                            all.hidePlayer(player);
                        }
                    });
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, false));
                }
                return true;
            }

            if (args.length == 1) {

                if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.VANISHOTHER.getPermission(), true))
                    return true;
                final Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Data.getMessageUtil().sendMessage(player, PrefixController.getOffline());
                    return true;
                }

                if (Data.getVanished().contains(target)) {
                    Data.getVanished().remove(target);
                    Data.getMessageUtil().sendMessage(target, "§7Dein Vanish wurde §c§lDEAKTIVIERT§8.");
                    Data.getMessageUtil().sendMessage(player, "§7Vanish für §a" + target.getName() + " §7wurde §c§lDEAKTIVIERT§8.");

                    Bukkit.getOnlinePlayers().forEach(all -> {
                        all.showPlayer(target);
                    });
                    target.removePotionEffect(PotionEffectType.INVISIBILITY);


                } else {
                    Data.getVanished().add(target);
                    Data.getMessageUtil().sendMessage(target, "§7Dein Vanish wurde §a§lAKTIVIERT§8.");
                    Data.getMessageUtil().sendMessage(player, "§7Vanish für §a" + target.getName() + " §7wurde §a§lAKTIVIERT§8.");

                    Bukkit.getOnlinePlayers().forEach(all -> {
                        if (all.hasPermission(Permissions.TEAM.getPermission())) {
                            all.showPlayer(target);
                        } else {
                            all.hidePlayer(target);
                        }
                    });
                    target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, false));


                }
                return true;
            }
        }
        return false;
    }
}
