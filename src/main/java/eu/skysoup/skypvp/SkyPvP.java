package eu.skysoup.skypvp;


import eu.skysoup.skypvp.data.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created: 22.01.2023 23:46
 *
 * @author thvf
 */
public class SkyPvP extends JavaPlugin {

    @Getter
    @Setter
    private static SkyPvP INSTANCE;
    @Getter
    @Setter
    private static long UPTIME;

    @Override
    public void onEnable() {
        setINSTANCE(this);
        setUPTIME(System.currentTimeMillis());


        try {
            Data.getBukkitController().initOther();

            Data.getBukkitController().initCommands();
            Data.getBukkitController().initListeners();
            Data.getBukkitController().initSchedulers();
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.shutdown();
        }
    }


    @Override
    public void onDisable() {
        Data.getBukkitController().initClose();
    }
}
