package eu.skysoup.skypvp.utils;

/**
 * Created: 23.01.2023 13:19
 *
 * @author thvf
 */
public class RunnableUtil {

    public int getTimeFromMinutes(final int minutes) {
        return 1200 * minutes;
    }

    public int getTimeFromHours(final int hours) {
        return 72000 * hours;
    }

    public int getTimeFromSeconds(final int seconds) {
        return 20 * seconds;
    }
}
