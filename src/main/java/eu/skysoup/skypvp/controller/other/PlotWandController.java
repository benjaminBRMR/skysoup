package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.utils.impl.PlotRandBlock;
import eu.skysoup.skypvp.utils.impl.PlotWandBlock;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;

public class PlotWandController {

    @Getter
    public final ArrayList<PlotWandBlock> plotWandBlocks = new ArrayList<>();

    public void initialize() {

        plotWandBlocks.add(new PlotWandBlock(Material.STONE, Permissions.DEFAULT.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.QUARTZ_BLOCK, Permissions.DEFAULT.getPermission()));


        plotWandBlocks.add(new PlotWandBlock(Material.SEA_LANTERN, Permissions.AZUR.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.PRISMARINE, Permissions.AZUR.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.OBSIDIAN, Permissions.AZUR.getPermission()));

        plotWandBlocks.add(new PlotWandBlock(Material.HAY_BLOCK, Permissions.SAPHIR.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.SLIME_BLOCK, Permissions.SAPHIR.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.BEDROCK, Permissions.SAPHIR.getPermission()));


        plotWandBlocks.add(new PlotWandBlock(Material.LAPIS_BLOCK, Permissions.ONYX.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.HARD_CLAY, Permissions.ONYX.getPermission()));
        
        plotWandBlocks.add(new PlotWandBlock(Material.EMERALD_BLOCK, Permissions.SUPERIA.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.PUMPKIN, Permissions.SUPERIA.getPermission()));

        plotWandBlocks.add(new PlotWandBlock(Material.BOOKSHELF, Permissions.SOUP.getPermission()));
        plotWandBlocks.add(new PlotWandBlock(Material.DIAMOND_BLOCK, Permissions.SOUP.getPermission()));




    }
}
