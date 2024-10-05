package eu.skysoup.skypvp.controller;

import eu.skysoup.skypvp.data.Data;
import eu.skysoup.skypvp.data.implementorings.StatisticTypes;
import eu.skysoup.skypvp.utils.builders.ItemBuilder;
import org.bukkit.Material;

import java.text.NumberFormat;
import java.util.UUID;

/**
 * Created: 31.01.2023 20:15
 *
 * @author thvf
 */
public class BountyController {

    UUID uuid;
    UserController userController;

    public BountyController(final UUID uuid) {
        this.uuid = uuid;
        this.userController = new UserController().getUserByUUID(uuid);
    }

    public void setBounty(final long value) {
        if (value < 1000) throw new RuntimeException("value needs to be greater than 1000");
        userController.setStatisticFromUser(StatisticTypes.BOUNTY, value);
    }

    public long getBounty() {
        if (userController.getStatisticByType(StatisticTypes.BOUNTY) == 0) throw new RuntimeException("No bounty set");
        return userController.getStatisticByType(StatisticTypes.BOUNTY);
    }

    public boolean hasBounty() {
        return userController.getStatisticByType(StatisticTypes.BOUNTY) >= 1000;
    }

    public ItemBuilder getBountyItem(final long value) {
        final ItemBuilder itemBuilder = new ItemBuilder(Material.PRISMARINE_SHARD).setName("§8» §a§LKOPF§2§LGELD §7Marke §8(§f$§8)").lore(
                "§8§oDieses Item schreibt dir den Wert",
                "§8§odeiner erkämpften Kopfgeldmarke gut.",
                "§8§oZahle diese Marke mit /bounty ein!",
                "",
                "§8┌ §7Kopfgeldmarke",
                "§8├ §7Wert§8: §6$§e" + NumberFormat.getInstance().format(value),
                "§8└ §7Von§8: §e" + Data.getUuidFetcher().getName(uuid),
                ""
        );

        return itemBuilder;
    }
}
