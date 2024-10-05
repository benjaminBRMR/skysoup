package eu.skysoup.skypvp.commands.admin;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.Permissions;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 09.02.2023 11:28
 *
 * @author thvf
 */
public class CommandEnchant implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (!Data.getPermissionUtil().hasPermission((Player) sender, Permissions.ADMIN.getPermission(), true))
                return true;
            final Player player = (Player) sender;

            if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                Data.getMessageUtil().sendMessage(player, "§cBitte halte ein gültiges Item in der Hand.");
                return true;
            }


            if (args.length == 2) {

                final String enchant = args[0];
                int level = 1;
                try {
                    level = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    Data.getMessageUtil().sendMessage(player, "§cBitte gebe eine gültige Anzahl an.");
                    return true;
                }


                final ItemStack item = player.getItemInHand();

                switch (enchant.toLowerCase()) {

                    case "schärfe":
                        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fSchärfe §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "haltbarkeit":
                        item.addUnsafeEnchantment(Enchantment.DURABILITY, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fHaltbarkeit §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "rückstoß":
                        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fRückstoß §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "verbrennung":
                        item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fVerbrennung §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "behutsamkeit":
                        item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fBehutsamkeit §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "effizienz":
                        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fEffizienz §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "unendlichkeit":
                        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fUnendlichkeit §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "glück":
                        item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fGlück §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "plünderung":
                        item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fPlünderung §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;

                    case "schutz":
                        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
                        Data.getMessageUtil().sendMessage(player, "§aDein Item wurde mit §fSchutz §f" + level + " §averzaubert.");
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2, 5);
                        break;
                }

                return true;
            }
            Data.getMessageUtil().sendSyntax(player, "§8/§eEnchant §7<schärfe,haltbarkeit,rücktoß,verbrennung,behutsamkeit,effizienz,uendlichkeit,glück,plünderung,schutz> <level>");


        }
        return false;
    }
}
