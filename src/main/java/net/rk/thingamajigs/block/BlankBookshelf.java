package net.rk.thingamajigs.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class BlankBookshelf extends Block {
    // empty bookshelf doesn't do much
    public BlankBookshelf(Properties p) {
        super(p.strength(1.5F).sound(SoundType.WOOD).mapColor(MapColor.WOOD));
    }
}
