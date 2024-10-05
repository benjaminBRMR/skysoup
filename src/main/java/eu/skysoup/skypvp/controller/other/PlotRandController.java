package eu.skysoup.skypvp.controller.other;

import eu.skysoup.skypvp.data.implementorings.Permissions;
import eu.skysoup.skypvp.utils.impl.PlotRandBlock;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;

public class PlotRandController {

    @Getter
    public final ArrayList<PlotRandBlock> plotRandBlocks = new ArrayList<>();

    public void initialize() {

        plotRandBlocks.add(new PlotRandBlock(Material.BARRIER, Permissions.DEFAULT.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.STONE_SLAB2, Permissions.DEFAULT.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.OBSIDIAN, Permissions.DEFAULT.getPermission()));


        plotRandBlocks.add(new PlotRandBlock(Material.DIAMOND_ORE, Permissions.AZUR.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.DIAMOND_BLOCK, Permissions.AZUR.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.GOLD_BLOCK, Permissions.AZUR.getPermission()));

        plotRandBlocks.add(new PlotRandBlock(Material.EMERALD_BLOCK, Permissions.SAPHIR.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.REDSTONE_BLOCK, Permissions.SAPHIR.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.REDSTONE_ORE, Permissions.SAPHIR.getPermission()));


        plotRandBlocks.add(new PlotRandBlock(Material.BOOKSHELF, Permissions.ONYX.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.GRASS, Permissions.ONYX.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.SPRUCE_FENCE, Permissions.ONYX.getPermission()));


        plotRandBlocks.add(new PlotRandBlock(Material.SEA_LANTERN, Permissions.SUPERIA.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.GLASS, Permissions.SUPERIA.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.BEDROCK, Permissions.SUPERIA.getPermission()));

        plotRandBlocks.add(new PlotRandBlock(Material.ENCHANTMENT_TABLE, Permissions.SOUP.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.ENDER_PORTAL_FRAME, Permissions.SOUP.getPermission()));
        plotRandBlocks.add(new PlotRandBlock(Material.ANVIL, Permissions.SOUP.getPermission()));


    }
}
