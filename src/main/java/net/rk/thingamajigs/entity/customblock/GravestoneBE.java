package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;
import net.rk.thingamajigs.xtrablock.DJLaserLight;

public class GravestoneBE {
    /*
    public int internalLimit = 2648300;
    public int ticks;
    public int angle = 0;
    public String line = "R.I.P.";
    public boolean hidePose;

    public GravestoneBE(BlockEntityType<?> bet, BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.GRAVESTONE_BE.get(), bp, bs);
    }

    public GravestoneBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.GRAVESTONE_BE.get(), bp, bs);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket(){return ClientboundBlockEntityDataPacket.create(this);}

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, GravestoneBE be){
        if(be.ticks > be.internalLimit){
            be.ticks = 0;
        }
        else{
            ++be.ticks;
        }

        if(!be.getLevel().hasNearbyAlivePlayer((double)bp.getX() + 0.5, (double)bp.getY() + 0.5, (double)bp.getZ() + 0.5, 32)){
            be.hidePose = true;
        }

        switch(bs.getValue(DJLaserLight.FACING)){
            case NORTH ->{
                be.angle = 180;
                return;
            }
            case SOUTH ->{
                be.angle = 0;
                return;
            }
            case EAST ->{
                be.angle = 90;
                return;
            }
            case WEST ->{
                be.angle = 270;
                return;
            }
        }
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, GravestoneBE sbe){
        if(sbe.ticks > sbe.internalLimit){
            sbe.ticks = 0;
        }
        else{
            ++sbe.ticks;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag ctsa) {
        super.saveAdditional(ctsa);
    }

    @Override
    public void load(CompoundTag ctl) {
        super.load(ctl);
    }

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

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        BlockPos bp = this.getBlockPos();
        return new AABB(
                bp.getX() - 16,
                bp.getY() - 16,
                bp.getZ() - 16,
                bp.getX() + 16,
                bp.getY() + 16,
                bp.getZ() + 16);
    }

     */
}
