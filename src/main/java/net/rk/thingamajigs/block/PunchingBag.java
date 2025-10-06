package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class PunchingBag extends Block {
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 4, 8, 1, 12),
            Block.box(3, 1, 7, 5, 28, 9),
            Block.box(5, 26, 7, 15, 28, 9),
            Block.box(9, 6, 3, 19, 22, 13),
            Block.box(13.5, 22, 7.5, 14.5, 26, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(8, 0, 4, 16, 1, 12),
            Block.box(11, 1, 7, 13, 28, 9),
            Block.box(1, 26, 7, 11, 28, 9),
            Block.box(-3, 6, 3, 7, 22, 13),
            Block.box(1.5, 22, 7.5, 2.5, 26, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(4, 0, 0, 12, 1, 8),
            Block.box(7, 1, 3, 9, 28, 5),
            Block.box(7, 26, 5, 9, 28, 15),
            Block.box(3, 6, 9, 13, 22, 19),
            Block.box(7.5, 22, 13.5, 8.5, 26, 14.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(4, 0, 8, 12, 1, 16),
            Block.box(7, 1, 11, 9, 28, 13),
            Block.box(7, 26, 1, 9, 28, 11),
            Block.box(3, 6, -3, 13, 22, 7),
            Block.box(7.5, 22, 1.5, 8.5, 26, 2.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PunchingBag(Properties p) {
        super(p.sound(SoundType.WOOL).strength(1F,10F).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return Shapes.block();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
