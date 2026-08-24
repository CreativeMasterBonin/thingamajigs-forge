package net.rk.thingamajigs.entity.customblock;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.block.CarWashBrush;
import net.rk.thingamajigs.block.ShortCarWashBrush;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class CarWashBrushBE extends BlockEntity {
    public float yAngle = 0.0f;
    public float extensionAngle = 0.0f;
    public static float yOffset = 2.0f;
    public float facingDirectionAngle = -3.14f;
    public float xoffset = 5.0f;
    public float ymod = -0.5f;
    public float initialxoffset = 7.0f;
    public float initialzoffset = 0.0f;

    public CarWashBrushBE(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.CAR_WASH_BRUSH_BE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("extensionangle")){
            extensionAngle = tag.getFloat("extensionangle");
        }
        if(tag.contains("initialxoffset")){
            initialxoffset = tag.getFloat("initialxoffset");
        }
        if(tag.contains("initialzoffset")){
            initialzoffset = tag.getFloat("initialzoffset");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putFloat("extensionangle",extensionAngle);
        tag.putFloat("initialxoffset",initialxoffset);
        tag.putFloat("initialzoffset",initialzoffset);
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
        CompoundTag compoundTag = new CompoundTag();
        this.saveAdditional(compoundTag);
        return compoundTag;
    }

    // only on client side as BE does not need server to show its blades spinning
    public static void clientTick(Level level, BlockPos pos, BlockState state, CarWashBrushBE carWashBrush){
        if(state.getBlock() instanceof CarWashBrush && state.hasProperty(CarWashBrush.LIT)){
            if(state.getBlock() instanceof ShortCarWashBrush){
                if(state.getValue(CarWashBrush.LIT)){
                    if(carWashBrush.extensionAngle < 50.0f){
                        carWashBrush.extensionAngle += 3.5f / (float) Util.getMillis() + 2.5f;
                    }
                    else{
                        carWashBrush.extensionAngle = 50.0f;
                    }
                }
                else{
                    if(carWashBrush.extensionAngle > 6.0f){
                        carWashBrush.extensionAngle -= 1.2f;
                    }
                    else if(carWashBrush.extensionAngle < -6.0f){
                        carWashBrush.extensionAngle += 1.2f;
                    }
                    else{
                        carWashBrush.extensionAngle = 6.0f;
                    }
                    // correct angle
                    if(carWashBrush.yAngle != 0.0f){
                        if(carWashBrush.yAngle > 1.0f){
                            carWashBrush.yAngle -= 1.2f;
                        }
                        else if(carWashBrush.yAngle < -1.0f){
                            carWashBrush.yAngle += 1.2f;
                        }
                        else{
                            carWashBrush.yAngle = 0.0f;
                        }
                    }
                }
            }
            else{
                if(state.getValue(CarWashBrush.LIT)){
                    if(carWashBrush.extensionAngle < 80.0f){
                        carWashBrush.extensionAngle += 3.5f / (float)Util.getMillis() + 2.5f;
                    }
                    else{
                        carWashBrush.extensionAngle = 80.0f;
                    }
                }
                else{
                    if(carWashBrush.extensionAngle > 1.0f){
                        carWashBrush.extensionAngle -= 1.2f;
                    }
                    else if(carWashBrush.extensionAngle < -1.0f){
                        carWashBrush.extensionAngle += 1.2f;
                    }
                    else{
                        carWashBrush.extensionAngle = 0.0f;
                    }
                    // correct angle
                    if(carWashBrush.yAngle != 0.0f){
                        if(carWashBrush.yAngle > 1.0f){
                            carWashBrush.yAngle -= 1.2f;
                        }
                        else if(carWashBrush.yAngle < -1.0f){
                            carWashBrush.yAngle += 1.2f;
                        }
                        else{
                            carWashBrush.yAngle = 0.0f;
                        }
                    }
                }
            }
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.getBlockPos().getX() - 2, this.getBlockPos().getY() - 2, this.getBlockPos().getZ() - 2,
                this.getBlockPos().getX() + 2, this.getBlockPos().getY() + 2, this.getBlockPos().getZ() + 2);
    }
}
