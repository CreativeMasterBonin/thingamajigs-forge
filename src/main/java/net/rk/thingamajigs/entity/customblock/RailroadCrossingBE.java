package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.block.RailroadCrossing;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class RailroadCrossingBE extends BlockEntity{
    BlockPos bp;
    public float armAngle = 0;
    public float startingArmAngle = 1.35f;
    public float endArmAngle = 0.0f;
    public float yAngle = 0.0f;
    public float armLength = 1.0f;
    public float armGateOffsetZ = 1.0f;

    public int ticks;
    public RailroadCrossingArmState railroadCrossingArmState = RailroadCrossingArmState.UP;

    public enum RailroadCrossingArmState{
        UP,
        MOVING,
        DOWN
    }

    public RailroadCrossingBE(BlockPos pos, BlockState blockState) {
        super(ThingamajigsBlockEntities.RAILROAD_CROSSING_ARM_BE.get(), pos, blockState);
        bp = pos;
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, RailroadCrossingBE rrcbe){
        ++rrcbe.ticks;
        if(slvl.getBlockState(sbp).getBlock() == ThingamajigsBlocks.RAILROAD_CROSSING.get()){
            if(slvl.getBlockState(sbp).getValue(RailroadCrossing.ON) == true){
                if(rrcbe.armAngle > rrcbe.endArmAngle){
                    rrcbe.armAngle = Mth.rotLerp(0.05f,rrcbe.armAngle,rrcbe.armAngle - 0.15f); //0.01f;
                    // rrcbe.armAngle -= 0.01f;

                    rrcbe.railroadCrossingArmState = RailroadCrossingArmState.MOVING;
                    rrcbe.updateBlock();
                }
                else{
                    if(rrcbe.railroadCrossingArmState != RailroadCrossingArmState.DOWN){
                        rrcbe.railroadCrossingArmState = RailroadCrossingArmState.DOWN;
                        rrcbe.updateBlock();
                    }
                }
            }
            else{
                if(rrcbe.armAngle < rrcbe.startingArmAngle){
                    rrcbe.armAngle = Mth.rotLerp(0.05f,rrcbe.armAngle,rrcbe.armAngle + 0.15f); //0.01f;
                    //rrcbe.armAngle += 0.01f;
                    rrcbe.railroadCrossingArmState = RailroadCrossingArmState.MOVING;
                    rrcbe.updateBlock();
                }
                else{
                    if(rrcbe.railroadCrossingArmState != RailroadCrossingArmState.UP){
                        rrcbe.railroadCrossingArmState = RailroadCrossingArmState.UP;
                        rrcbe.updateBlock();
                    }
                }
            }
        }
        // hard reset tick counter
        if(rrcbe.ticks >= 32767){
            rrcbe.ticks = 0;
        }
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, RailroadCrossingBE rrcbe){
        ++rrcbe.ticks;
        // hard reset tick counter
        if(rrcbe.ticks >= 32767){
            rrcbe.ticks = 0;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putFloat("arm_angle",armAngle);
        pTag.putFloat("starting_arm_angle",startingArmAngle);
        pTag.putFloat("end_arm_angle",endArmAngle);
        pTag.putString("railroad_crossing_state",railroadCrossingArmState.name());
        pTag.putFloat("y_angle",yAngle);
        pTag.putFloat("arm_length",armLength);
        pTag.putFloat("arm_gate_offset_z",armGateOffsetZ);
    }

    @Override
    public void load(CompoundTag pTag) {
        armAngle = pTag.getFloat("arm_angle");
        startingArmAngle = pTag.getFloat("starting_arm_angle");
        endArmAngle = pTag.getFloat("end_arm_angle");
        railroadCrossingArmState = RailroadCrossingArmState.valueOf(pTag.getString("railroad_crossing_state"));
        yAngle = pTag.getFloat("y_angle");
        armLength = pTag.getFloat("arm_length");
        armGateOffsetZ = pTag.getFloat("arm_gate_offset_z");
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
        int additionalamt = 5;
        return new AABB(this.getBlockPos().getX() - this.armLength / 2 - additionalamt, this.getBlockPos().getY() - 2, this.getBlockPos().getZ() - this.armLength / 2 - additionalamt,
                this.getBlockPos().getX() + this.armLength / 2 + additionalamt, this.getBlockPos().getY() + additionalamt, this.getBlockPos().getZ() + this.armLength / 2 + additionalamt);
    }
}
