package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RoadwaySignBlockEntity extends SignBlockEntity {
    public RoadwaySignBlockEntity(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.GREEN_ROADWAY_SIGN_BLOCK_ENTITIES.get(), bp, bs);
    }

    @Override
    public BlockEntityType<RoadwaySignBlockEntity> getType() {
        return ThingamajigsBlockEntities.GREEN_ROADWAY_SIGN_BLOCK_ENTITIES.get();
    }
}
