package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class CarWashBrush extends Block {
    //public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final VoxelShape ALL = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 32, 9),
            Block.box(4, 30, 4, 12, 31, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CarWashBrush(Properties p) {
        super(p.noOcclusion().strength(1f,5f)
                .sound(SoundType.LANTERN));
        //this.registerDefaultState(this.defaultBlockState().setValue(LIT,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean isHoldingCarWashBrush = context.isHoldingItem(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get().asItem()) || context.isHoldingItem(ThingamajigsBlocks.CAR_WASH_YELLOW_BRUSH.get().asItem()) || context.isHoldingItem(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get().asItem());
        return isHoldingCarWashBrush ? DoubleTallDecorationBlock.BLOCK_SHAPE : ALL;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return DoubleTallDecorationBlock.BLOCK_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ALL;
    }
/*
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block p_60512_, BlockPos p_60513_, boolean p_60514_) {
        if (!level.isClientSide) {
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
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }*/

    /*@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ThingamajigsBlockEntities.CAR_WASH_BRUSH_BE.get(),
                level.isClientSide ? CarWashBrushBE::clientTick : null);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CarWashBrushBE(blockPos,blockState);
    }*/
}
