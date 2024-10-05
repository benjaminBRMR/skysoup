package eu.skysoup.skypvp.utils.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@AllArgsConstructor
public class PlotRandBlock {

    @Getter
    final Material randBlock;

    @Getter
    final String permission;
}
