package net.rk.thingamajigs.xtrablock;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class CustomWaterlilyBlock extends WaterlilyBlock {
    // setup custom waterlily BEFORE block uses constructor so all blocks like it act and sound the same way
    public CustomWaterlilyBlock(Properties p) {
        super(p.pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)
                .instabreak()
                .sound(SoundType.LILY_PAD)
                .noOcclusion());
    }
}
