package net.rk.thingamajigs.entity.customblock.hangingblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.thingamajigs.entity.roadsigns.hanging.GreenHangingRSBE;

public class GreenWallHangingSignBlock extends WallHangingSignBlock {
    public GreenWallHangingSignBlock(Properties p, WoodType wt) {
        super(p, wt);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new GreenHangingRSBE(bp,bs);
    }
}
