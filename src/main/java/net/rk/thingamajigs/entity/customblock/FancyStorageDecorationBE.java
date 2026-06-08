package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.block.FancyStorageDecoration;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

import java.util.Objects;

public class FancyStorageDecorationBE extends RandomizableContainerBlockEntity {
    public NonNullList<ItemStack> items;
    private String containerNameTranslation = "blockEntities.thingamajigs.fancy_storage_decoration.name";
    public final int itemContainerSize;

    public FancyStorageDecorationBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.FANCY_STORAGE_DECORATION_BE.get(), bp, bs);
        this.itemContainerSize = 54;
        this.items = NonNullList.withSize(itemContainerSize, ItemStack.EMPTY);
    }

    // do not use
    public FancyStorageDecorationBE(BlockPos bp, BlockState bs, String name) {
        this(bp,bs);
        this.containerNameTranslation = name;
    }

    public FancyStorageDecorationBE(BlockPos bp, BlockState bs, String name, int itemContainerSize) {
        super(ThingamajigsBlockEntities.FANCY_STORAGE_DECORATION_BE.get(), bp, bs);
        this.itemContainerSize = itemContainerSize;
        this.items = NonNullList.withSize(this.itemContainerSize, ItemStack.EMPTY);
        this.containerNameTranslation = name;
    }

    public String getContainerNameTranslation() {
        return containerNameTranslation;
    }

    public void updateBlock(){
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    @Override
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
    public void setChanged() {
        if(this.level != null){
            Objects.requireNonNull(getLevel()).blockEntityChanged(getBlockPos());
            if (!getBlockState().isAir()) {
                getLevel().updateNeighbourForOutputSignal(getBlockPos(),getBlockState().getBlock());
            }
            updateBlock();
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> nnlis) {
        this.items = nnlis;
    }

    @Override
    public Component getDefaultName() {
        return Component.translatable(containerNameTranslation);
    }

    @Override
    public AbstractContainerMenu createMenu(int cmi1, Inventory i) {
        if(this.getBlockState().getBlock() instanceof FancyStorageDecoration deco){
            return deco.createMenu(cmi1,i,this);
        }
        return null;
    }

    @Override
    public int getContainerSize(){return itemContainerSize;}

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if(!this.trySaveLootTable(tag)){
            ContainerHelper.saveAllItems(tag,this.items);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag,this.items);
        }
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
