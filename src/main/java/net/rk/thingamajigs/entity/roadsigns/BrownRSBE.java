package net.rk.thingamajigs.entity.roadsigns;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class BrownRSBE extends SignBlockEntity {
    public BrownRSBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.BROWN_ROADWAY_SIGN_BE.get(), bp, bs);
    }

    @Override
    public BlockEntityType<BrownRSBE> getType() {
        return ThingamajigsBlockEntities.BROWN_ROADWAY_SIGN_BE.get();
    }
}
