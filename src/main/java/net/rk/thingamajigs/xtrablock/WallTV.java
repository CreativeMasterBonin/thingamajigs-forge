package net.rk.thingamajigs.xtrablock;

import net.minecraft.world.level.material.MapColor;
import net.rk.thingamajigs.block.custom.blocks.ToggledStateBlock;

public class WallTV extends ToggledStateBlock {
    public WallTV(Properties p){
        super(p.strength(1f,10f).noOcclusion().noCollission().mapColor(MapColor.COLOR_LIGHT_GRAY));
    }
}
