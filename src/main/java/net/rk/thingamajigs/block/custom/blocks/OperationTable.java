package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class OperationTable extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NORTHSOUTH_S = Stream.of(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 3, 11),
            Block.box(6, 3, 6, 10, 11, 10),
            Block.box(0, 11, -8, 16, 13, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_S = Stream.of(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 3, 11),
            Block.box(6, 3, 6, 10, 11, 10),
            Block.box(-8, 11, 0, 24, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OperationTable(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH_S;
            case EAST:
            case WEST:
                return EASTWEST_S;
            default: return Shapes.block();
        }
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, @Nullable Entity player) {
        return false;
    }
}
