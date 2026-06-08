package net.rk.thingamajigs.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

@Deprecated(forRemoval = true,since = "1.20.1")
public class AsphaltDynamicBlock extends Block {
    public AsphaltDynamicBlock(Properties p) {
        super(p.strength(1.5F).sound(SoundType.TUFF).requiresCorrectToolForDrops());
    }
}
