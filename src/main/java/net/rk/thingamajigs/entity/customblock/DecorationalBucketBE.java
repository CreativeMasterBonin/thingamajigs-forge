package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class DecorationalBucketBE extends BlockEntity{
    public FluidTank fakeTank = new FluidTank(16); // this is for show only, not for actual fluid storage

    public DecorationalBucketBE(BlockPos pos, BlockState blockState) {
        super(ThingamajigsBlockEntities.DECORATIONAL_BUCKET_BE.get(), pos, blockState);
    }

    public void setFluid(Fluid fluid){
        fakeTank.setFluid(new FluidStack(fluid,16));
        updateBlock();
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
    protected void saveAdditional(CompoundTag tag) {
        fakeTank.writeToNBT(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        fakeTank.readFromNBT(tag);
    }

    @Override
    public AABB getRenderBoundingBox() {
        BlockPos bp = this.getBlockPos();
        return new AABB(
                bp.getX() - 2,
                bp.getY() - 2,
                bp.getZ() - 2,
                bp.getX() + 2,
                bp.getY() + 2,
                bp.getZ() + 2);
    }
}
