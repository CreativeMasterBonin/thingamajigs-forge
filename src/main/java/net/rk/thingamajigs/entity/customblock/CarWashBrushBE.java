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
    public float yExtensionOffset = 0.0f;
    public static float yOffset = 2.0f;
    public float facingDirectionAngle = -3.14f;
    public float xoffset = 5.0f;
    public float ymod = -0.5f;
    public float initialxoffset = 7.0f;
    public float initialzoffset = -2.0f;

    public static float normalYOffsetTarget = -0.17f;
    public static float normalConstantTargetAmt = 1.0f;

    public CarWashBrushBE(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.CAR_WASH_BRUSH_BE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("extensionangle")){
            extensionAngle = tag.getFloat("extensionangle");
        }
        if(tag.contains("yextensionoffset")){
            yExtensionOffset = tag.getFloat("yextensionoffset");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putFloat("extensionangle",extensionAngle);
        tag.putFloat("yextensionoffset",yExtensionOffset);
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

    public static void serverTick(Level level, BlockPos pos, BlockState state, CarWashBrushBE carWashBrush){
        if(state.getBlock() instanceof CarWashBrush && state.hasProperty(CarWashBrush.LIT)){
            if(state.getBlock() instanceof ShortCarWashBrush){
                if(state.getValue(CarWashBrush.LIT)){
                    if(carWashBrush.extensionAngle < 50.0f){
                        carWashBrush.extensionAngle += 3.5f / (float) Util.getMillis() + 2.5f;
                        carWashBrush.updateBlock();
                    }
                    else{
                        carWashBrush.extensionAngle = 50.0f;
                        carWashBrush.updateBlock();
                    }
                }
                else{
                    if(carWashBrush.extensionAngle > -8.0f){
                        carWashBrush.extensionAngle -= 2.0f;
                        carWashBrush.updateBlock();
                    }
                    else{
                        if(carWashBrush.extensionAngle != -8.0f){
                            carWashBrush.extensionAngle = -8.0f;
                            carWashBrush.updateBlock();
                        }
                    }

                    // correct angle
                    if(carWashBrush.yAngle != 0.0f){
                        carWashBrush.yAngle = 0.0f;
                        carWashBrush.updateBlock();
                    }
                }
            }
            else{ // normal car wash brushes
                if(state.getValue(CarWashBrush.LIT)){
                    if(carWashBrush.yExtensionOffset > normalYOffsetTarget){
                        carWashBrush.yExtensionOffset -= 0.15f;
                        carWashBrush.updateBlock();
                    }
                    // enforce offset
                    if(carWashBrush.yExtensionOffset < normalYOffsetTarget){
                        carWashBrush.yExtensionOffset = normalYOffsetTarget;
                        carWashBrush.updateBlock();
                    }

                    if(carWashBrush.extensionAngle < 80.0f){
                        carWashBrush.extensionAngle += normalConstantTargetAmt;
                        carWashBrush.updateBlock();
                    }
                    else{
                        carWashBrush.extensionAngle = 80.0f;
                        carWashBrush.updateBlock();
                    }
                }// if off
                else{
                    // the angle of attack for the brushes
                    if(carWashBrush.extensionAngle >= 0.5f){
                        carWashBrush.extensionAngle -= 0.5f;
                        carWashBrush.updateBlock();
                    }
                    else{
                        carWashBrush.extensionAngle = 0.0f;
                    }

                    // the movement that repositions the brushes
                    if(carWashBrush.yExtensionOffset < 0.0f){
                        carWashBrush.yExtensionOffset += 0.1f;
                        carWashBrush.updateBlock();
                    }
                    else{
                        carWashBrush.yExtensionOffset = 0.0f;
                    }

                    // correct angle
                    if(carWashBrush.yAngle != 0.0f){
                        if(carWashBrush.yAngle > 1.0f){
                            carWashBrush.yAngle -= 1.2f;
                            carWashBrush.updateBlock();
                        }
                        else if(carWashBrush.yAngle < -1.0f){
                            carWashBrush.yAngle += 1.2f;
                            carWashBrush.updateBlock();
                        }
                        else{
                            carWashBrush.yAngle = 0.0f;
                            carWashBrush.updateBlock();
                        }
                    }
                }
            }
        }
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
                    if(carWashBrush.extensionAngle > -8.0f){
                        carWashBrush.extensionAngle -= 0.99f;
                    }
                    else{
                        carWashBrush.extensionAngle = -8.0f;
                    }

                    // correct angle
                    if(carWashBrush.yAngle != 0.0f){
                        carWashBrush.yAngle = 0.0f;
                    }
                }
            }
            else{// normal car wash brushes
                if(state.getValue(CarWashBrush.LIT)){
                    if(carWashBrush.yExtensionOffset > normalYOffsetTarget){
                        carWashBrush.yExtensionOffset -= 0.15f;
                    }

                    // enforce offset
                    if(carWashBrush.yExtensionOffset < normalYOffsetTarget){
                        carWashBrush.yExtensionOffset = normalYOffsetTarget;
                        carWashBrush.updateBlock();
                    }

                    if(carWashBrush.extensionAngle < 80.0f){
                        carWashBrush.extensionAngle += normalConstantTargetAmt;
                    }
                    else{
                        carWashBrush.extensionAngle = 80.0f;
                    }
                }// if off
                else{
                    if(carWashBrush.extensionAngle >= 0.5f){
                        carWashBrush.extensionAngle -= 0.5f;
                    }
                    else{
                        carWashBrush.extensionAngle = 0.0f;
                    }

                    if(carWashBrush.yExtensionOffset < 0.0f){
                        carWashBrush.yExtensionOffset += 0.1f;
                    }
                    else{
                        carWashBrush.yExtensionOffset = 0.0f;
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
