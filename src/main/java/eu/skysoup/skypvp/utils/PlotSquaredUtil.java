package eu.skysoup.skypvp.utils;

import com.avaje.ebean.validation.NotNull;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created: 20.02.2023 20:40
 *
 * @author thvf
 */
public class PlotSquaredUtil {


    @Getter
    private final PlotAPI plotAPI = new PlotAPI();


    public String direction(float yaw) {
        yaw /= 90.0F;
        int i = Math.round(yaw);
        switch (i) {
            case -4:
            case 0:
            case 4:
                return "SOUTH";
            case -3:
            case 1:
                return "WEST";
            case -2:
            case 2:
                return "NORTH";
            case -1:
            case 3:
                return "EAST";
            default:
                return "";
        }
    }

    int direction = -1;

    public int getCorrectDirection(final Player player) {
        switch (direction(player.getLocation().getYaw())) {
            case "NORTH":
                direction = 0;
                break;
            case "EAST":
                direction = 1;
                break;
            case "SOUTH":
                direction = 2;
                break;
            case "WEST":
                direction = 3;
        }
        return direction;
    }


    /**
     * @param player
     * @return Null if plot isn't claimed
     */
    public Plot getPlotPlayerIsStandingOn(final Player player) {
        return plotAPI.getPlot(player);
    }

    public boolean isPlotOwner(final Plot plot, final Player player) {
        return plotAPI.getPlot(player).owner == plot.owner;
    }

    /**
     * @param player
     * @return "✘" is the plot has NO Owner
     */
    @NotNull
    public String getPlotOwnerPlayerIsStandingOn(final Player player) {

        try {
            if (plotAPI.getPlot(player).getOwners().size() < 1) return "§c§l✘";
            if (plotAPI.getPlot(player) == null || !plotAPI.getPlot(player).hasOwner()) return "§c§l✘";
            if (getPlotPlayerIsStandingOn(player) == null || Bukkit.getOfflinePlayer(getPlotPlayerIsStandingOn(player).owner) == null)
                return "§c§l✘";
            if (getPlotPlayerIsStandingOn(player).owner == null) return "§c§l✘";

            return Bukkit.getOfflinePlayer(getPlotPlayerIsStandingOn(player).owner).getName();
        } catch (Exception ignored) {
            return "§c§l✘";
        }
    }

    @NotNull
    public String getPlotOwnerPlayerIsStandingOnSecond(final Player player) {

        try {
            if (plotAPI.getPlot(player).getOwners().size() < 1) return "§c§lKein Besitzer";
            if (plotAPI.getPlot(player) == null || !plotAPI.getPlot(player).hasOwner()) return "§c§lKein Besitzer";
            if (getPlotPlayerIsStandingOn(player) == null || Bukkit.getOfflinePlayer(getPlotPlayerIsStandingOn(player).owner) == null)
                return "§c§lKein Besitzer";
            if (getPlotPlayerIsStandingOn(player).owner == null) return "§c§lKein Besitzer";

            return Bukkit.getOfflinePlayer(getPlotPlayerIsStandingOn(player).owner).getName();
        } catch (Exception ignored) {
            return "§c§lKein Besitzer";
        }
    }

}
