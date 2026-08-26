package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import net.minecraftforge.common.Tags;
import net.rk.thingamajigs.entity.customblock.MitterCurtainBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class MitterCurtain extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape ALL = Stream.of(
            Block.box(0, 14, 0, 16, 15, 16),
            Block.box(0, 0, 0, 16, 14, 1),
            Block.box(15, 0, 1, 16, 14, 16),
            Block.box(0, 0, 15, 16, 14, 16),
            Block.box(0, 0, 1, 1, 14, 15),
            Block.box(1, 15, 1, 15, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public MitterCurtain(Properties p) {
        super(p.sound(SoundType.METAL));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.thingamajigs.mitter_curtain.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return ALL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(ctx.isHoldingItem(ThingamajigsBlocks.CAR_WASH_MITTER_CURTAIN.get().asItem())){
            return Shapes.block();
        }
        else{
            return ALL;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MitterCurtainBE(blockPos,blockState);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getItemInHand(hand);
        if(level.isClientSide()){
            if(handStack.is(ItemTags.AXES) || handStack.is(Tags.Items.SHEARS) || handStack.is(Tags.Items.DUSTS) || handStack.is(Tags.Items.GEMS)){
                player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.7f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.1f));
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(handStack.is(ItemTags.AXES) || handStack.is(Tags.Items.SHEARS)){
                MitterCurtainBE mitterCurtain = (MitterCurtainBE)level.getBlockEntity(pos);
                if(mitterCurtain instanceof MitterCurtainBE){
                    mitterCurtain.horizontal = !mitterCurtain.horizontal;
                    mitterCurtain.updateBlock();
                    return InteractionResult.SUCCESS;
                }
            }
            else if(handStack.is(Tags.Items.DUSTS)){
                MitterCurtainBE mitterCurtain = (MitterCurtainBE)level.getBlockEntity(pos);
                if(mitterCurtain instanceof MitterCurtainBE){
                    mitterCurtain.yAngle -= 5.0f;
                    mitterCurtain.updateBlock();
                    return InteractionResult.SUCCESS;
                }
            }
            else if (handStack.is(Tags.Items.GEMS)) {
                MitterCurtainBE mitterCurtain = (MitterCurtainBE)level.getBlockEntity(pos);
                if(mitterCurtain instanceof MitterCurtainBE){
                    mitterCurtain.yAngle += 5.0f;
                    mitterCurtain.updateBlock();
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @org.jetbrains.annotations.Nullable Direction direction) {
        return true;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block p_60512_, BlockPos p_60513_, boolean p_60514_) {
        if (!level.isClientSide()) {
            boolean isLit = state.getValue(LIT);
            if (isLit != level.hasNeighborSignal(pos)) {
                if (isLit) {
                    level.scheduleTick(pos, this, 2, TickPriority.EXTREMELY_LOW);
                } else {
                    level.setBlock(pos, state.cycle(LIT), 2);
                }
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(LIT), 2);
        }
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT,WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()))
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
