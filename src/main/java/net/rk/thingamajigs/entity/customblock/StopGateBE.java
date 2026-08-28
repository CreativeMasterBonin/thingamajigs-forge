package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.block.custom.blocks.StopGate;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class StopGateBE extends BlockEntity {
    public float gateAngle = 0.0f;

    public StopGateBE(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.STOP_GATE_BE.get(), pos, state);
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
        tag.putFloat("gate_angle",gateAngle);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("gate_angle")){
            gateAngle = tag.getFloat("gate_angle");
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        BlockPos bp = this.getBlockPos();
        return new AABB(
                bp.getX() - 3.0f,
                bp.getY() - 1.5f,
                bp.getZ() - 3.0f,
                bp.getX() + 3.0f,
                bp.getY() + 1.5f,
                bp.getZ() + 3.0f);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, StopGateBE stopGate){
        if(state.hasProperty(StopGate.LIT)){
            if(state.getValue(StopGate.LIT)){
                if(stopGate.gateAngle < 90.0f){
                    stopGate.gateAngle += 0.5f;
                    stopGate.updateBlock();
                }
            }
            else{
                if(stopGate.gateAngle > 0.0f){
                    stopGate.gateAngle -= 0.5f;
                    stopGate.updateBlock();
                }
            }
        }
    }
}
