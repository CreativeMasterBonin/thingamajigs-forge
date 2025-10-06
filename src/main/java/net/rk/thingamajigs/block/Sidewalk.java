package net.rk.thingamajigs.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class Sidewalk extends Block {
    public Sidewalk(Properties p) {
        super(p.mapColor(MapColor.COLOR_GRAY));
    }
}
