package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OpenableContainer extends RandomizableContainerBlockEntity {
    private static final int CONTAINER_SIZE = 54;
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public OpenableContainer(BlockPos blockPos, BlockState blockState) {
        super(ThingamajigsBlockEntities.OPENABLE_CONTAINER.get(), blockPos, blockState);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("blockEntities.openable_container.name");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return ChestMenu.sixRows(i, inventory, this);
    }

    @Override
    public int getContainerSize() {return CONTAINER_SIZE;}

    @Override
    protected void saveAdditional(CompoundTag cts) {
        super.saveAdditional(cts);
        if (!this.trySaveLootTable(cts)) {
            ContainerHelper.saveAllItems(cts, this.items);
        }
    }
    @Override
    public void load(CompoundTag ctl) {
        super.load(ctl);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(ctl)) {
            ContainerHelper.loadAllItems(ctl, this.items);
        }
    }
}
