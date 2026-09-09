package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class DiscoBallBE extends BlockEntity {
    public boolean spinning = false;
    public double speed = 1.0D;

    public DiscoBallBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.DISCO_BALL_BE.get(), bp, bs);
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
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
        CompoundTag ct = new CompoundTag();
        this.saveAdditional(ct);
        return ct;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putBoolean("spinning",spinning);
        tag.putDouble("speed",speed);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("spinning")){
            spinning = tag.getBoolean("spinning");
        }
        if(tag.contains("speed")){
            speed = tag.getDouble("speed");
        }
    }
}
