package net.rk.thingamajigs.entity.customblock.chests;

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
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class StorageDecorationBE extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private String containerNameTranslation = "blockEntities.storage_decoration.name";

    public StorageDecorationBE(BlockPos bp, BlockState bs) {
        super(ThingamajigsBlockEntities.STORAGE_DECORATION_BE.get(), bp, bs);
    }

    public StorageDecorationBE(BlockPos bp, BlockState bs, String name) {
        this(bp,bs);
        this.containerNameTranslation = name;
    }

    public String getContainerNameTranslation() {
        return containerNameTranslation;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nnlis) {
        this.items = nnlis;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("blockEntities.storage_decoration.name");
    }

    @Override
    protected AbstractContainerMenu createMenu(int cmi1, Inventory i) {
        return ChestMenu.threeRows(cmi1, i, this);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    protected void saveAdditional(CompoundTag cts) {
        super.saveAdditional(cts);
        if (!this.trySaveLootTable(cts)) {
            ContainerHelper.saveAllItems(cts, this.items);
        }
    }

    public void load(CompoundTag ctl) {
        super.load(ctl);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(ctl)) {
            ContainerHelper.loadAllItems(ctl, this.items);
        }
    }
}
