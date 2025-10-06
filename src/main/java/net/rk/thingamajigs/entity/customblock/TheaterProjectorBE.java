package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class TheaterProjectorBE extends BlockEntity {
    BlockPos bp;
    public float yAngle = 0.0f;
    public float x_xtra = 0.0f;
    public float z_xtra = 0.0f;
    public float projector_image_angle;
    public boolean custom = false;

    public TheaterProjectorBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.THEATER_PROJECTOR_BE.get(), bp, bs);
        this.bp = bp;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putFloat("y_angle",yAngle);
        pTag.putBoolean("custom_settings",custom);
        pTag.putFloat("x_xtra",x_xtra);
        pTag.putFloat("z_xtra",z_xtra);
        pTag.putFloat("projector_image_angle",projector_image_angle);
    }

    @Override
    public void load(CompoundTag pTag) {
        yAngle = pTag.getFloat("y_angle");
        custom = pTag.getBoolean("custom_settings");
        x_xtra = pTag.getFloat("x_xtra");
        z_xtra = pTag.getFloat("z_xtra");
        projector_image_angle = pTag.getFloat("projector_image_angle");
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
