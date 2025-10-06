package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.world.level.block.RedstoneLampBlock;

public class NeonFloorBlock extends RedstoneLampBlock {

    public NeonFloorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.FALSE));
    }
}
