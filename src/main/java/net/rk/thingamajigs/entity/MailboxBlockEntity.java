package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.screen.MailboxMenu;

public class MailboxBlockEntity extends RandomizableContainerBlockEntity {

    // entity borrows hopper UI and functionality. only change is having different registration and name
    // some functionality was omitted
    // mailbox will keep contents across world save (until world itself breaks)
    // mailbox will also attempt to get its own menu if possible, else just default to using Hopper Menu (experimental)
    private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

    public MailboxBlockEntity(BlockPos pos, BlockState state) {
        super(ThingamajigsBlockEntities.MAILBOX_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag cpt) {
        super.saveAdditional(cpt);
        if (!this.trySaveLootTable(cpt)) {
            ContainerHelper.saveAllItems(cpt, this.items);
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("blockEntities.mailbox.name");
    }

    @Override
    protected AbstractContainerMenu createMenu(int num1, Inventory inv1) {
        return new MailboxMenu(num1,inv1,this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_59625_) {
        this.items = p_59625_;
    }

    @Override
    public int getContainerSize() {
        return 5;
    }
}
