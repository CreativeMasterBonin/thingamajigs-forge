package net.rk.thingamajigs.xtrablock;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.entity.customblock.CurvedMonitorBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecated,unused")
public class CurvedMonitor extends BaseEntityBlock implements SimpleWaterloggedBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CurvedMonitor(Properties p) {
        super(p.strength(1f,5f).sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BLACK)
                .noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter lvl, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.thingamajigs.curved_monitor.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs){return new CurvedMonitorBE(bp,bs);}
    @Override
    public RenderShape getRenderShape(BlockState state){return RenderShape.ENTITYBLOCK_ANIMATED;}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }
    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).is(ItemTags.AXES) || player.getItemInHand(hand).getItem() instanceof ShearsItem){
                player.playSound(SoundEvents.CALCITE_HIT,0.75f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.getItemInHand(hand).is(ItemTags.AXES) || player.getItemInHand(hand).getItem() instanceof ShearsItem){
                CurvedMonitorBE curvedMonitor = (CurvedMonitorBE)level.getBlockEntity(pos);
                if(curvedMonitor instanceof CurvedMonitorBE){
                    curvedMonitor.hideKeyboard = !curvedMonitor.hideKeyboard;
                    curvedMonitor.updateBlock();
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
