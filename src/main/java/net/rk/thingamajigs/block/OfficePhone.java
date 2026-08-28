package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.stream.Stream;

public class OfficePhone extends ThingamajigsDecorativeBlock {
    public static final VoxelShape SHAPE = Block.box(0,0,0,16,3,16);
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5.5, 0.92388, 9.61732, 9.5, 2.67388, 13.61732),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(5.5, 1.02, 3, 12.5, 1.02, 9),
            Block.box(9.7, 1, 10, 13.7, 3, 14),
            Block.box(3, 1, 3.5, 5, 2, 5.5),
            Block.box(3, 1, 11.5, 5, 2, 13.5),
            Block.box(3, 2, 4.5, 5, 3, 12.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(2.3826800000000006, 0.92388, 5.5, 6.382680000000001, 2.67388, 9.5),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(7, 1.02, 5.5, 13, 1.02, 12.5),
            Block.box(2, 1, 9.7, 6, 3, 13.7),
            Block.box(10.5, 1, 3, 12.5, 2, 5),
            Block.box(2.5, 1, 3, 4.5, 2, 5),
            Block.box(3.5, 2, 3, 11.5, 3, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6.5, 0.92388, 2.3826800000000006, 10.5, 2.67388, 6.382680000000001),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(3.5, 1.02, 7, 10.5, 1.02, 13),
            Block.box(2.3000000000000007, 1, 2, 6.300000000000001, 3, 6),
            Block.box(11, 1, 10.5, 13, 2, 12.5),
            Block.box(11, 1, 2.5, 13, 2, 4.5),
            Block.box(11, 2, 3.5, 13, 3, 11.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(9.61732, 0.92388, 6.5, 13.61732, 2.67388, 10.5),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(3, 1.02, 3.5, 9, 1.02, 10.5),
            Block.box(10, 1, 2.3000000000000007, 14, 3, 6.300000000000001),
            Block.box(3.5, 1, 11, 5.5, 2, 13),
            Block.box(11.5, 1, 11, 13.5, 2, 13),
            Block.box(4.5, 2, 11, 12.5, 3, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OfficePhone(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
