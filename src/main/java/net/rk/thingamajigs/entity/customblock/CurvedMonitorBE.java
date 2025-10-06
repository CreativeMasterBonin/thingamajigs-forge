package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class CurvedMonitorBE extends BlockEntity{
    BlockPos bp;
    public float yAngle = 0.0f;
    public boolean hideKeyboard = false;
    public boolean customAngle = false;

    public CurvedMonitorBE(BlockPos pos, BlockState blockState) {
        super(ThingamajigsBlockEntities.CURVED_MONITOR_BE.get(), pos, blockState);
        this.bp = pos;
    }

    public boolean getKeyboardState(){
        return hideKeyboard;
    }

    public void setKeyboardState(boolean newState){
        hideKeyboard = newState;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putFloat("y_angle",yAngle);
        pTag.putBoolean("hide_keyboard",hideKeyboard);
        pTag.putBoolean("custom_angle",customAngle);
    }

    @Override
    public void load(CompoundTag pTag) {
        yAngle = pTag.getFloat("y_angle");
        hideKeyboard = pTag.getBoolean("hide_keyboard");
        customAngle = pTag.getBoolean("custom_angle");
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket(){return ClientboundBlockEntityDataPacket.create(this);}

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag ct = new CompoundTag();
        saveAdditional(ct);
        return ct;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
    }

    @Override
    public AABB getRenderBoundingBox(){
        return new AABB(this.getBlockPos().getX() - 4, this.getBlockPos().getY() - 2, this.getBlockPos().getZ() - 4,
                this.getBlockPos().getX() + 4, this.getBlockPos().getY() + 2, this.getBlockPos().getZ() + 4);
    }
}
