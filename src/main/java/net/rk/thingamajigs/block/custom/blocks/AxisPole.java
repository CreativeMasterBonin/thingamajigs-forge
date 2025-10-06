package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.Pole;

import java.util.stream.Stream;

public class AxisPole extends Pole {
    public static final VoxelShape NS = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(7, 7, 7, 16, 9, 9),
            Block.box(7, 7, 9, 9, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(0, 7, 7, 9, 9, 9),
            Block.box(7, 7, 0, 9, 9, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(7, 7, 7, 9, 9, 16),
            Block.box(0, 7, 7, 7, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(7, 7, 0, 9, 9, 9),
            Block.box(9, 7, 7, 16, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public AxisPole(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NS;
            case SOUTH:
                return SS;
            case EAST:
                return ES;
            case WEST:
                return WS;
            default: return Shapes.block();
        }
    }
}
