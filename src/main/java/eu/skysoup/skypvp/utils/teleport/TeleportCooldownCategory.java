package eu.skysoup.skypvp.utils.teleport;

import java.util.Objects;
import java.util.UUID;

/**
 * Created: 06.02.2023 12:09
 *
 * @author thvf
 */
public class TeleportCooldownCategory {
    private final UUID id;

    public TeleportCooldownCategory() {
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeleportCooldownCategory that = (TeleportCooldownCategory) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
