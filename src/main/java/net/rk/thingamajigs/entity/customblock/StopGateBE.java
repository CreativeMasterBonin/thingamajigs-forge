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
    public double offsetX = 0.5D;
    public double offsetY = -1.0D;
    public double offsetZ = 0.375D;

    public float northXRot = 0.5f;
    public float southXRot = 0.5f;
    public float eastXRot = 0.0f;
    public float westXRot = 0.0f;

    public float northYRot = 0.43f;
    public float southYRot = 0.43f;
    public float eastYRot = 0.43f;
    public float westYRot = 0.43f;

    public float northZRot = 0.0f;
    public float southZRot = 0.0f;
    public float eastZRot = 0.5f;
    public float westZRot = 0.5f;

    public boolean inverse = false;

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
        tag.putBoolean("inverse",inverse);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("gate_angle")){
            gateAngle = tag.getFloat("gate_angle");
        }
        if(tag.contains("inverse")){
            inverse = tag.getBoolean("inverse");
        }
    }

    public static final float BOUNDING_BOX_RANGE = 4.575f;

    @Override
    public AABB getRenderBoundingBox() {
        BlockPos bp = this.getBlockPos();
        return new AABB(
                bp.getX() - BOUNDING_BOX_RANGE,
                bp.getY() - BOUNDING_BOX_RANGE,
                bp.getZ() - BOUNDING_BOX_RANGE,
                bp.getX() + BOUNDING_BOX_RANGE,
                bp.getY() + BOUNDING_BOX_RANGE,
                bp.getZ() + BOUNDING_BOX_RANGE);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, StopGateBE stopGate){
        if(state.hasProperty(StopGate.LIT)){
            if(stopGate.inverse){
                if(state.getValue(StopGate.LIT)){
                    if(stopGate.gateAngle > -90.0f){
                        stopGate.gateAngle -= 0.5f;
                        stopGate.updateBlock();
                    }
                }
                else{
                    if(stopGate.gateAngle < 0.0f){
                        stopGate.gateAngle += 0.5f;
                        stopGate.updateBlock();
                    }
                }
            }
            else{
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
}
