package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class CeilingFanBE extends BlockEntity {
    public boolean reversed = false;

    public CeilingFanBE(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.CEILING_FAN_BE.get(), pos, state);
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
    public void saveAdditional(CompoundTag tag) {
        tag.putBoolean("reversed",reversed);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("reversed")){
            reversed = tag.getBoolean("reversed");
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.getBlockPos().getX() - 1.5, this.getBlockPos().getY() - 1.5, this.getBlockPos().getZ() - 1.5,
                this.getBlockPos().getX() + 1.5, this.getBlockPos().getY() + 1.5, this.getBlockPos().getZ() + 1.5);
    }
}
