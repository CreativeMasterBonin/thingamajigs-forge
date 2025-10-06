package net.rk.thingamajigs.entity.roadsigns;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class BlueRSBE extends SignBlockEntity {
    public BlueRSBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.BLUE_ROADWAY_SIGN_BE.get(), bp, bs);
    }

    @Override
    public BlockEntityType<BlueRSBE> getType() {
        return ThingamajigsBlockEntities.BLUE_ROADWAY_SIGN_BE.get();
    }
}
