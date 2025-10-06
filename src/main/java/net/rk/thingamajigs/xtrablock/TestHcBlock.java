package net.rk.thingamajigs.xtrablock;

import net.minecraft.world.level.block.SoundType;

public class TestHcBlock extends HorizontalConnectedBlock{
    public TestHcBlock(Properties p) {
        super(p.strength(1F,5F).sound(SoundType.NETHERITE_BLOCK));
    }
}
