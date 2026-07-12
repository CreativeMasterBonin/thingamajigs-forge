package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class TubeManDecoBE extends BlockEntity {
    public float yAngle = 0.0f;
    public DyeColor color = DyeColor.BLUE;
    public float randomOffset = 0.0f;

    public TubeManDecoBE(BlockPos pos, BlockState blockState) {
        super(ThingamajigsBlockEntities.TUBE_MAN_DECO_BE.get(), pos, blockState);
        randomOffset = ThingamajigsCalcStuffs.nextFloatBetweenInclusive(2.11f,3.29f);
    }

    public float getRandomOffset(){
        return this.randomOffset;
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
        saveAdditional(ct);
        return ct;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putFloat("y_angle",yAngle);
        tag.putInt("color",color.getId());
        tag.putFloat("random_offset",randomOffset);
    }

    @Override
    public void load(CompoundTag tag) {
        if(tag.contains("y_angle")){
            yAngle = tag.getFloat("y_angle");
        }
        if(tag.contains("color")){
            color = DyeColor.byId(tag.getInt("color"));
        }
        if(tag.contains("random_offset")){
            randomOffset = tag.getFloat("random_offset");
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.getBlockPos().getX() - 5.5, this.getBlockPos().getY() - 2, this.getBlockPos().getZ() - 5.5,
                this.getBlockPos().getX() + 5.5, this.getBlockPos().getY() + 5, this.getBlockPos().getZ() + 5.5);
    }
}
