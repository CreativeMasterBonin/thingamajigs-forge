package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class Antenna extends DoubleTallDecorationBlock{
    public Antenna(Properties properties) {
        super(properties);
    }
    public static final VoxelShape NORTH = Stream.of(
            Block.box(7.25, 0, 7.25, 8.75, 32, 8.75),
            Block.box(7.75, 19, -2.25, 8.8, 21, 19.75),
            Block.box(7, 0, 7, 9, 1, 9),
            Block.box(2, 16, 3, 4, 18, 5),
            Block.box(0, 16, 1, 2, 18, 3),
            Block.box(6, 16, 7, 8, 18, 9),
            Block.box(4, 16, 5, 6, 18, 7),
            Block.box(12, 16, 13, 14, 18, 15),
            Block.box(8, 16, 9, 10, 18, 11),
            Block.box(10, 16, 11, 12, 18, 13),
            Block.box(14, 16, 15, 16, 18, 17),
            Block.box(14, 29, 10, 16, 31, 12),
            Block.box(9, 29, 8, 11, 31, 10),
            Block.box(11, 29, 9, 13, 31, 11),
            Block.box(16, 29, 11, 18, 31, 13),
            Block.box(6, 29, 7, 8, 31, 9),
            Block.box(4, 29, 6, 6, 31, 8),
            Block.box(-1, 29, 4, 1, 31, 6),
            Block.box(1, 29, 5, 4, 31, 7),
            Block.box(14, 25, 1, 16, 27, 3),
            Block.box(12, 25, 3, 14, 27, 5),
            Block.box(10, 25, 5, 12, 27, 7),
            Block.box(4, 25, 11, 6, 27, 13),
            Block.box(6, 25, 9, 8, 27, 11),
            Block.box(8, 25, 7, 10, 27, 9),
            Block.box(0, 25, 15, 2, 27, 17),
            Block.box(2, 25, 13, 4, 27, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(7.25, 0, 7.25, 8.75, 32, 8.75),
            Block.box(-3.75, 19, 7.75, 18.25, 21, 8.8),
            Block.box(7, 0, 7, 9, 1, 9),
            Block.box(11, 16, 2, 13, 18, 4),
            Block.box(13, 16, 0, 15, 18, 2),
            Block.box(7, 16, 6, 9, 18, 8),
            Block.box(9, 16, 4, 11, 18, 6),
            Block.box(1, 16, 12, 3, 18, 14),
            Block.box(5, 16, 8, 7, 18, 10),
            Block.box(3, 16, 10, 5, 18, 12),
            Block.box(-1, 16, 14, 1, 18, 16),
            Block.box(4, 29, 14, 6, 31, 16),
            Block.box(6, 29, 9, 8, 31, 11),
            Block.box(5, 29, 11, 7, 31, 13),
            Block.box(3, 29, 16, 5, 31, 18),
            Block.box(7, 29, 6, 9, 31, 8),
            Block.box(8, 29, 4, 10, 31, 6),
            Block.box(10, 29, -1, 12, 31, 1),
            Block.box(9, 29, 1, 11, 31, 4),
            Block.box(13, 25, 14, 15, 27, 16),
            Block.box(11, 25, 12, 13, 27, 14),
            Block.box(9, 25, 10, 11, 27, 12),
            Block.box(3, 25, 4, 5, 27, 6),
            Block.box(5, 25, 6, 7, 27, 8),
            Block.box(7, 25, 8, 9, 27, 10),
            Block.box(-1, 25, 0, 1, 27, 2),
            Block.box(1, 25, 2, 3, 27, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(7.25, 0, 7.25, 8.75, 32, 8.75),
            Block.box(7.199999999999999, 19, -3.75, 8.25, 21, 18.25),
            Block.box(7, 0, 7, 9, 1, 9),
            Block.box(12, 16, 11, 14, 18, 13),
            Block.box(14, 16, 13, 16, 18, 15),
            Block.box(8, 16, 7, 10, 18, 9),
            Block.box(10, 16, 9, 12, 18, 11),
            Block.box(2, 16, 1, 4, 18, 3),
            Block.box(6, 16, 5, 8, 18, 7),
            Block.box(4, 16, 3, 6, 18, 5),
            Block.box(0, 16, -1, 2, 18, 1),
            Block.box(0, 29, 4, 2, 31, 6),
            Block.box(5, 29, 6, 7, 31, 8),
            Block.box(3, 29, 5, 5, 31, 7),
            Block.box(-2, 29, 3, 0, 31, 5),
            Block.box(8, 29, 7, 10, 31, 9),
            Block.box(10, 29, 8, 12, 31, 10),
            Block.box(15, 29, 10, 17, 31, 12),
            Block.box(12, 29, 9, 15, 31, 11),
            Block.box(0, 25, 13, 2, 27, 15),
            Block.box(2, 25, 11, 4, 27, 13),
            Block.box(4, 25, 9, 6, 27, 11),
            Block.box(10, 25, 3, 12, 27, 5),
            Block.box(8, 25, 5, 10, 27, 7),
            Block.box(6, 25, 7, 8, 27, 9),
            Block.box(14, 25, -1, 16, 27, 1),
            Block.box(12, 25, 1, 14, 27, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(7.25, 0, 7.25, 8.75, 32, 8.75),
            Block.box(-2.25, 19, 7.199999999999999, 19.75, 21, 8.25),
            Block.box(7, 0, 7, 9, 1, 9),
            Block.box(3, 16, 12, 5, 18, 14),
            Block.box(1, 16, 14, 3, 18, 16),
            Block.box(7, 16, 8, 9, 18, 10),
            Block.box(5, 16, 10, 7, 18, 12),
            Block.box(13, 16, 2, 15, 18, 4),
            Block.box(9, 16, 6, 11, 18, 8),
            Block.box(11, 16, 4, 13, 18, 6),
            Block.box(15, 16, 0, 17, 18, 2),
            Block.box(10, 29, 0, 12, 31, 2),
            Block.box(8, 29, 5, 10, 31, 7),
            Block.box(9, 29, 3, 11, 31, 5),
            Block.box(11, 29, -2, 13, 31, 0),
            Block.box(7, 29, 8, 9, 31, 10),
            Block.box(6, 29, 10, 8, 31, 12),
            Block.box(4, 29, 15, 6, 31, 17),
            Block.box(5, 29, 12, 7, 31, 15),
            Block.box(1, 25, 0, 3, 27, 2),
            Block.box(3, 25, 2, 5, 27, 4),
            Block.box(5, 25, 4, 7, 27, 6),
            Block.box(11, 25, 10, 13, 27, 12),
            Block.box(9, 25, 8, 11, 27, 10),
            Block.box(7, 25, 6, 9, 27, 8),
            Block.box(15, 25, 14, 17, 27, 16),
            Block.box(13, 25, 12, 15, 27, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter lvl, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
