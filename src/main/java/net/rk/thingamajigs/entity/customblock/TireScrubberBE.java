package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class TireScrubberBE extends BlockEntity {
    public float yAngle = 0.0f;
    public float speedAddition = 1.0f;

    public TireScrubberBE(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.CAR_WASH_TIRE_SCRUBBER_BE.get(), pos, state);
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null){
            this.getLevel().sendBlockUpdated(this.getBlockPos(),this.getBlockState(),this.getBlockState(),3);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("speed_addition")){
            speedAddition = tag.getFloat("speed_addition");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putFloat("speed_addition",speedAddition);
    }
}
