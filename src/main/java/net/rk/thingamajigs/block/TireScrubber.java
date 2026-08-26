package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.entity.customblock.TireScrubberBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class TireScrubber extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static BooleanProperty LIT = BlockStateProperties.LIT;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(-8, 5, 7, 24, 7, 9),
            Block.box(-10, 0, 7, -8, 10, 9),
            Block.box(24, 0, 7, 26, 10, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(7, 5, -8, 9, 7, 24),
            Block.box(7, 0, -10, 9, 10, -8),
            Block.box(7, 0, 24, 9, 10, 26)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TireScrubber(Properties p) {
        super(p.noCollission().sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(LIT,false)
                .setValue(FACING,Direction.NORTH).setValue(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH,SOUTH -> {return NORTHSOUTH;}
            case EAST,WEST -> {return EASTWEST;}
            default -> {return Shapes.block();}
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(ctx.isHoldingItem(ThingamajigsBlocks.CAR_WASH_TIRE_SCRUBBER.get().asItem())){
            return Shapes.block();
        }
        else{
            switch(state.getValue(FACING)){
                case NORTH,SOUTH -> {return NORTHSOUTH;}
                case EAST,WEST -> {return EASTWEST;}
                default -> {return Shapes.block();}
            }
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TireScrubberBE(blockPos,blockState);
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

    /*@Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getItemInHand(hand);
        // unfortunately this does nothing as the rotation variable this was supposed to affect is not working
        if(level.isClientSide()){
            if(handStack.is(Items.RABBIT_FOOT) || handStack.is(Items.RABBIT_HIDE) || handStack.isEmpty()){
                player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.75f,
                        ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(handStack.is(Items.RABBIT_FOOT)){
                TireScrubberBE scrubber = (TireScrubberBE)level.getBlockEntity(pos);
                if(scrubber instanceof TireScrubberBE){
                    scrubber.speedAddition = 15.0f;
                    scrubber.updateBlock();
                }
                return InteractionResult.SUCCESS;
            }
            else if(handStack.is(Items.RABBIT_HIDE)){
                TireScrubberBE scrubber = (TireScrubberBE)level.getBlockEntity(pos);
                if(scrubber instanceof TireScrubberBE){
                    scrubber.speedAddition = 6.35f;
                    scrubber.updateBlock();
                }
                return InteractionResult.SUCCESS;
            }
            else if(handStack.isEmpty()){
                TireScrubberBE scrubber = (TireScrubberBE)level.getBlockEntity(pos);
                if(scrubber instanceof TireScrubberBE){
                    scrubber.speedAddition = 1.0f;
                    scrubber.updateBlock();
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }*/

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
        builder.add(LIT,FACING,WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()))
                .setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
