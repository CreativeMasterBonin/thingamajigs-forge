package net.rk.thingamajigs.xtrablock;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;
import net.rk.thingamajigs.entity.customblock.ItemDisplayBE;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("deprecated,unused")
public class ItemDisplayBlock extends BaseEntityBlock {
    public static final BooleanProperty FULL = BooleanProperty.create("full");

    public ItemDisplayBlock(Properties p) {
        super(p.strength(1F,5F)
                .sound(SoundType.LANTERN).noOcclusion().mapColor(MapColor.METAL));
        this.registerDefaultState(this.defaultBlockState().setValue(FULL, false));
    }

    // from decorated pot, modified for use with renderer and custom behaviors, such as a singular item
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Object object = level.getBlockEntity(blockPos);
        if (!(object instanceof ItemDisplayBE)) {
            return InteractionResult.PASS;
        }
        try{
            ItemDisplayBE idbe = (ItemDisplayBE) object;
            object = player.getItemInHand(interactionHand);
            ItemStack itemStack = idbe.getStackedItem();

            if(itemStack.getCount() >= 1){
                level.setBlock(blockPos,blockState.setValue(FULL,true),3);
            }
            else if(itemStack.getCount() < 1){
                level.setBlock(blockPos,blockState.setValue(FULL,false),3);
            }

            if(itemStack.getCount() >= 1){
                return InteractionResult.PASS;
            }
            else{
                if (!((ItemStack)object).isEmpty() && (itemStack.isEmpty() || ItemStack.isSameItemSameTags(itemStack, (ItemStack)object) && itemStack.getCount() < 1)) {
                    ItemStack itemStack2 = player.isCreative() ? ((ItemStack)object).copyWithCount(1) : ((ItemStack)object).split(1);

                    player.awardStat(Stats.ITEM_USED.get(((ItemStack)object).getItem()));

                    if (idbe.isEmpty()) {
                        idbe.setStackedItem(itemStack2); // add new item inside block (accesses BE save data)
                    }
                    else {
                        itemStack.grow(1); // increase same item amt (accesses BE save data)
                    }
                    level.playSound(null, blockPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0f, 0.7f + 0.5f);
                    level.updateNeighbourForOutputSignal(blockPos, this);
                }
                else {
                    level.playSound(null, blockPos, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS, 1.0f, 0.5f);
                    return InteractionResult.PASS;
                }
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos); // tell the game we just changed a block for the usage thingy
                return InteractionResult.SUCCESS;
            }
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning("Thingamajigs encountered an exception with the ItemDisplayBlock and ItemDisplayBlockEntity. " + e.getMessage());
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("block.thingamajigs.item_displayer.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof ItemDisplayBE) {
            ItemDisplayBE idbe1 = (ItemDisplayBE) blockEntity;
        }
        return super.getDrops(blockState, builder);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState.is(blockState2.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof ItemDisplayBE) {
                Containers.dropContents(level, blockPos, (ItemDisplayBE)blockentity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }
            super.onRemove(blockState, level, blockPos, blockState2, bl);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new ItemDisplayBE(bp, bs);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FULL);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        return this.defaultBlockState().setValue(FULL, false);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lvl, BlockState bs, BlockEntityType<T> bet) {
        return createTickerHelper(bet, ThingamajigsBlockEntities.ITEM_DISPLAY_BE.get(),
                lvl.isClientSide ? ItemDisplayBE::clientTick : ItemDisplayBE::serverTick);
    }
}
