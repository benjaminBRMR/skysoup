package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRang implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (args.length == 2 && args[0].equalsIgnoreCase("info")) {

                // - /Rang info <spieler>
                final String target = args[1];

                try {

                    String group;

                    group = (LuckPermsProvider.get().getUserManager().getUser(target).getPrimaryGroup() == null ? "Spieler" : LuckPermsProvider.get().getUserManager().getUser(target).getPrimaryGroup());
                    Data.getMessageUtil().sendMessage(player, "§7Aktueller Rang von §e" + target + "§8: §7" + group.toUpperCase());


                } catch (Exception e) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Spieler wurde nicht gefunden!");
                }
                return true;
            }

            if (args.length == 2 && !args[0].equalsIgnoreCase("info")) {

                final String target = args[0];

                Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);
                if (group == null) {
                    Data.getMessageUtil().sendMessage(player, "§cDieser Rang existiert nicht. §8(§e" + args[1] + "§8)");
                    return false;
                }

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target + " parent set " + group.getName());
                Data.getMessageUtil().sendMessage(player, "§7Rang von §e" + target + " §7wurde auf §e" + group.getName().toUpperCase() + " §7gesetzt§8.");
                Bukkit.getPlayer(target).kickPlayer("\n\n§8§m--§7§m--§f§m--§r §a§lRANG UPDATE §f§m--§7§m--§8§m--§r\n\n§7Dein Rang wurde geupdatet§8!\n§8└ §7Rang§8: §f§l" + group.getName().toUpperCase() + "\n\n§8§m--§7§m--§f§m--§r §a§lRANG UPDATE §f§m--§7§m--§8§m--§r");
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eRang §7<info> <spieler>", "§8/§eRang §7<spieler> §7<rang>");
            return false;
        }
        return false;
    }
}
