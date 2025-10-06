package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.stream.Stream;

public class Snowman extends ThingamajigsDecorativeBlock {
    public static final VoxelShape ALL = Stream.of(
            Block.box(2, 0, 2, 14, 12, 14),
            Block.box(4, 12, 4, 12, 20, 12),
            Block.box(5, 20, 5, 11, 26, 11),
            Block.box(3, 26, 3, 13, 27, 13),
            Block.box(5, 27, 5, 11, 32, 11),
            Block.box(6, 4, 1.95, 10, 10, 1.95)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Snowman(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
