package net.rk.thingamajigs.xtrablock;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.entity.customblock.DiscoBallBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiscoBall extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public DiscoBall(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.GLASS).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltipComponents, TooltipFlag flag) {
        tooltipComponents.add(Component.translatable("block.thingamajigs.disco_ball.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DiscoBallBE(pos,state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // something is in the player's hand
        if(!player.getItemInHand(hand).isEmpty()){
            // water buckets are allowed to do their action
            if(player.getItemInHand(hand).is(Items.WATER_BUCKET)){
                return InteractionResult.PASS;
            }
            // otherwise do other actions
            if(level.isClientSide()){
                ItemStack stack = player.getItemInHand(hand);
                DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
                if(discoBall instanceof DiscoBallBE){
                    if(stack.is(Tags.Items.SHEARS)){
                        player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.0f));
                    }
                    else if(stack.is(ItemTags.AXES)){
                        player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.92f,0.98f));
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            else{
                ItemStack stack = player.getItemInHand(hand);
                DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
                if(discoBall instanceof DiscoBallBE){
                    if(stack.is(Tags.Items.SHEARS)){
                        discoBall.speed -= 1.0D;
                        discoBall.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                    else if(stack.is(ItemTags.AXES)){
                        discoBall.speed += 1.0D;
                        discoBall.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        else{
            // nothing in the player's hand
            if(level.isClientSide()){
                DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
                if(discoBall instanceof DiscoBallBE){
                    player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.0f));
                    return InteractionResult.SUCCESS;
                }
            }
            else{
                DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
                if(discoBall instanceof DiscoBallBE){
                    discoBall.spinning = !discoBall.spinning;
                    discoBall.updateBlock();
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
