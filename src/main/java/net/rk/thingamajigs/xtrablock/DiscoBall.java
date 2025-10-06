package net.rk.thingamajigs.xtrablock;

import net.minecraft.world.level.block.SoundType;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class DiscoBall extends ThingamajigsDecorativeBlock {
    public DiscoBall(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.GLASS).noOcclusion());
    }
}
