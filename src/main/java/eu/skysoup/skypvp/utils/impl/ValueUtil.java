package eu.skysoup.skypvp.utils.impl;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created: 23.01.2023 11:17
 *
 * @author thvf
 */
public class ValueUtil {


    public static String showProgressBar(double progress, double maxValue) {
        int completedBars = (int) (progress * 10 / maxValue); // Multiply progress by 10 and divide by maxValue to get number of completed bars
        int incompleteBars = 10 - completedBars; // Calculate the number of incomplete bars
        String progressBar = "§8(";

        // Add completed bars to the progress bar string
        for (int i = 0; i < completedBars; i++) {
            progressBar += "§a:";
        }

        // Add incomplete bars to the progress bar string
        for (int i = 0; i < incompleteBars; i++) {
            progressBar += "§8:";
        }

        progressBar += "§8)";

        // Print the progress bar
        return progressBar;
    }

    /**
     * CHATGPT
     *
     * @param num
     * @return
     */
    public static String formatNumber(long num) {
        long divider = 10000000;
        if (num >= 1000000000) {
            return String.format("%d Mrd.", Math.round((double) num / 1000000000 / divider) * divider / 1000000);
        } else if (num >= 1000000) {
            return String.format("%d Mio.", Math.round((double) num / 1000000 / divider) * divider / 1000);
        } else if (num >= 1000) {
            return String.format("%dk", Math.round((double) num / 1000 / divider) * divider);
        } else {
            return String.valueOf(num);
        }
    }


    public static String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        minutes = minutes % 60;
        seconds = seconds % 60;

        long days = hours / 24;
        hours = hours % 24;
        long weeks = days / 7;
        days = days % 7;
        long months = weeks / 4;
        weeks = weeks % 4;

        String result = "";
        if (months > 0) {
            result += months + "mo ";
        }
        if (weeks > 0) {
            result += weeks + "w ";
        }
        if (days > 0) {
            result += days + "d ";
        }
        if (hours > 0 || result.length() > 0) {
            result += hours + "h ";
        }
        if (minutes > 0 || result.length() > 0) {
            result += minutes + "m ";
        }
        if (seconds > 0 || result.length() > 0) {
            result += seconds + "s";
        }
        return result.isEmpty() ? "Lädt..." : result;
    }


    public static String timeToString(long time, boolean shorten, boolean withSeconds) {
        String msg = "";
        long seconds = time / 1000L;
        long minutes;
        if (seconds >= 86400L) {
            minutes = seconds / 86400L;
            msg = msg + minutes + (shorten ? "d " : (minutes == 1L ? " Tag, " : " Tage, "));
            seconds %= 86400L;
        }

        if (seconds >= 3600L) {
            minutes = seconds / 3600L;
            msg = msg + minutes + (shorten ? "h " : " Std, ");
            seconds %= 3600L;
        }

        if (seconds >= 60L) {
            minutes = seconds / 60L;
            msg = msg + minutes + (shorten ? "m " : " Min, ");
            seconds %= 60L;
        }

        if (withSeconds) {
            if (seconds > 0L) {
                msg = msg + seconds + (shorten ? "s " : " Sek, ");
            }
        }


        if (!msg.isEmpty()) {
            msg = msg.substring(0, msg.length() - (shorten ? 1 : 2));
        } else {
            msg = shorten ? "0s" : "0 Sek";
        }

        return msg;
    }

    public static String format(long d) {

        if (d < 1000D) {
            Double f = Math.floor(d * 1000) / 1000;
            return new DecimalFormat("#,###").format(f).replaceAll(",", ".");
        } else {
            if (d < 1000000D) {
                Double f = Math.floor(d) / 1000;
                return f + "K";
            } else {
                if (d < 1000000000D) {
                    Double f = Math.floor(d / 1000D) / 1000;
                    return f + "Mio";
                } else {
                    if (d < 1000000000000D) {
                        Double f = Math.floor(d / 1000000D) / 1000;
                        return f + "Mrd";
                    } else {
                        if (d < 1000000000000000D) {
                            Double f = Math.floor(d / 1000000000D) / 1000;
                            return f + "Bio";
                        } else {
                            if (d < 1000000000000000000D) {
                                Double f = Math.floor(d / 1000000000000D) / 1000;
                                return f + "Brd";
                            } else {
                                Double f = Math.floor(d / 1000000000000000D) / 1000;
                                return f + "Q";
                            }
                        }
                    }
                }
            }
        }
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "Mrd");
        suffixes.put(1_000_000_000_000L, "B");
        suffixes.put(1_000_000_000_000_000L, "Brd");
        suffixes.put(1_000_000_000_000_000_000L, "T");
    }

    public static String format2(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format2(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format2(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
}
