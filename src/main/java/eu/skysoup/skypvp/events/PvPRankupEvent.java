package eu.skysoup.skypvp.events;

import eu.skysoup.skypvp.data.implementorings.RankTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created: 01.02.2023
 *
 * @author benni
 */
@Getter
@AllArgsConstructor
public class PvPRankupEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;
    RankTypes currentRank;
    RankTypes nextRank;


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


}
