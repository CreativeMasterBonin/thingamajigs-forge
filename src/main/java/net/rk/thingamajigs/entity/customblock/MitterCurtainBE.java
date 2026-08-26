package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class MitterCurtainBE extends BlockEntity {
    public float yAngle = 0.0f;
    public boolean horizontal = false;
    public float yOffset = 0.0f;

    public MitterCurtainBE(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.CAR_WASH_MITTER_CURTAIN_BE.get(), pos, state);
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null){
            this.getLevel().sendBlockUpdated(this.getBlockPos(),this.getBlockState(),this.getBlockState(),3);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        this.saveAdditional(compoundTag);
        return compoundTag;
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("y_angle")){
            yAngle = tag.getFloat("y_angle");
        }
        if(tag.contains("horizontal")){
            horizontal = tag.getBoolean("horizontal");
        }
        if(tag.contains("y_offset")){
            yOffset = tag.getFloat("y_offset");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putFloat("y_angle",yAngle);
        tag.putBoolean("horizontal",horizontal);
        tag.putFloat("y_offset",yOffset);
    }

    @Override
    public AABB getRenderBoundingBox() {
        BlockPos bp = this.getBlockPos();
        return new AABB(
                bp.getX() - 3.0f,
                bp.getY() - 3.1f,
                bp.getZ() - 3.0f,
                bp.getX() + 3.0f,
                bp.getY() + 3.1f,
                bp.getZ() + 3.0f);
    }
}
