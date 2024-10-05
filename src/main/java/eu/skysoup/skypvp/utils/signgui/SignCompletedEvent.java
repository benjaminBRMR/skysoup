package eu.skysoup.skypvp.utils.signgui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * JavaDoc this file!
 * Created: 13.01.2023
 *
 * @author thvf
 */
@Getter
@AllArgsConstructor
public final class SignCompletedEvent {
    private final Player player;
    private final List<String> lines;
}
