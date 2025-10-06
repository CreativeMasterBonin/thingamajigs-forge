package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rk.thingamajigs.block.ThingamajigsBlocks;

public class PlateBE extends BlockEntity implements ContainerSingleItem {
    private static final int ITEM_SLOT = 0; // currently unused
    // BE accepts any items that stack, even illegal stacked items (-1, 128, etc, etc.)
    // in normal gameplay, and server play, this is impossible to do
    private ItemStack singularItemStack = ItemStack.EMPTY;
    private LazyOptional<IItemHandler> lih = LazyOptional.empty(); // other mod support handler
    public static final String SINGULAR_ITEMSTACK = "item";

    public PlateBE(BlockPos pos, BlockState bs){
        super(ThingamajigsBlockEntities.PLATE_BE.get(),pos,bs);
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public ItemStack getRenderStack() {
        ItemStack newIS = new ItemStack(Items.BAKED_POTATO); // potato
        if(!singularItemStack.isEmpty()) {
            return singularItemStack;
        }
        return ItemStack.EMPTY;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public ItemStack getStackedItem(){
        return singularItemStack;
    }

    public void setStackedItem(ItemStack itemStack){
        singularItemStack = itemStack;
        // itemhandler doesn't handle the correct slots, so we must manually tell the server we request a 'block updated' thingy
        if(this.level != null){
            if(!this.level.isClientSide()){
                this.level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.singularItemStack.isEmpty()) {
            compoundTag.put(SINGULAR_ITEMSTACK, this.singularItemStack.save(new CompoundTag()));
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.singularItemStack = compoundTag.contains(SINGULAR_ITEMSTACK, 10) ? ItemStack.of(compoundTag.getCompound(SINGULAR_ITEMSTACK)) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack getItem(int slotId) {
        return singularItemStack;
    }

    @Override
    public ItemStack removeItem(int slotId, int p_18943_) {
        ItemStack is = new ItemStack(singularItemStack.getItem());
        if(is.getCount() == 0){
            return ItemStack.EMPTY;
        }
        is.shrink(1);
        return is;
    }

    @Override
    public void setItem(int slotId, ItemStack isNew) {
        singularItemStack = isNew;
    }

    @Override
    public boolean stillValid(Player p) {
        BlockState bs = p.level().getBlockState(this.getBlockPos());

        if(bs != ThingamajigsBlocks.PLATE.get().defaultBlockState()){
            return false;
        }
        else if(bs == ThingamajigsBlocks.PLATE.get().defaultBlockState()){
            return true;
        }

        return false;
    }
}
