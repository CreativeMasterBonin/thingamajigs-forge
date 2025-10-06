package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.minecraftforge.items.ItemStackHandler;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;

public class ItemDisplayBE extends BlockEntity implements ContainerSingleItem {
    private ItemStack singularItemStack = ItemStack.EMPTY;
    public static final String SINGULAR_ITEMSTACK = "item";

    public int ticks;
    public float rot;

    public boolean hidePose = false;

    public ItemDisplayBE(BlockPos pos, BlockState bs){
        super(ThingamajigsBlockEntities.ITEM_DISPLAY_BE.get(),pos,bs);
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


    public ItemStack getSingularItemStack() {
        return singularItemStack;
    }

    public void setSingularItemStack(ItemStack singularItemStack) {
        this.singularItemStack = singularItemStack;
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
        if(bs != ThingamajigsBlocks.ITEM_DISPLAY_BLOCK.get().defaultBlockState()){
            return false;
        }
        else if(bs == ThingamajigsBlocks.ITEM_DISPLAY_BLOCK.get().defaultBlockState()){
            return true;
        }
        return false;
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, ItemDisplayBE be){
        ++be.ticks;
        if(!be.getLevel().hasNearbyAlivePlayer((double)bp.getX() + 0.5, (double)bp.getY() + 0.5, (double)bp.getZ() + 0.5, (double)12)){
            be.hidePose = true;
        }
        else{
            ++be.rot;
            be.hidePose = false;
        }
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, ItemDisplayBE sbe){
        ++sbe.ticks;
    }
}
