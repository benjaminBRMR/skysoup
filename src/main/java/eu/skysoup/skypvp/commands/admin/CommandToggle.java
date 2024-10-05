package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.controller.ToggleController;
import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created: 31.01.2023 23:50
 *
 * @author thvf
 */
public class CommandToggle implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.TOGGLE.getPermission(), true))
                return true;
            final Player player = (Player) sender;


            if (args.length == 1) {

                try {
                    final ToggleController.Types type = ToggleController.Types.valueOf(args[0].toUpperCase());


                    Data.getToggleController().toggleType(type, !Data.getToggleController().isToggled(type));


                    Data.getMessageUtil().sendMessageToAll("§7Der Type §e" + type.name().toUpperCase() + " §7wurde " + (Data.getToggleController().isToggled(type) ? "§aaktiviert!" : "§cdeaktiviert!"));

                } catch (Exception e) {
                    Data.getMessageUtil().sendMessage(player, "§7Folgende OptionTypes existieren§8:");
                    Data.getMessageUtil().sendSyntax(player, "§r");
                    for (ToggleController.Types type : ToggleController.Types.values()) {
                        Data.getMessageUtil().sendMessage(player, "§e" + type.name().toUpperCase() + " §8(" + (Data.getToggleController().isToggled(type) ? "§aaktiviert" : "§cdeaktiviert") + "§8)");
                    }
                }
                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eToggle §7<type>");
            for (ToggleController.Types type : ToggleController.Types.values()) {
                Data.getMessageUtil().sendMessage(player, "§e" + type.name().toLowerCase() + " §8(" + (Data.getToggleController().isToggled(type) ? "§aaktiviert" : "§cdeaktiviert") + "§8)");
            }
        }
        return false;
    }
}
