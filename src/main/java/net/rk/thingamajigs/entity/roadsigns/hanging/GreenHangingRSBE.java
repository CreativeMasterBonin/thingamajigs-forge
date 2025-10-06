package net.rk.thingamajigs.entity.roadsigns.hanging;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class GreenHangingRSBE extends HangingSignBlockEntity {
    public GreenHangingRSBE(BlockPos bp, BlockState bs) {
        super(bp, bs);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ThingamajigsBlockEntities.GREEN_HANGING_SIGN.get();
    }
}
