package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class ChristmasTree extends Block {
    public static final VoxelShape ALL = Stream.of(
            Block.box(7, 0, 7, 9, 30, 9),
            Block.box(0, 4, 0, 16, 6, 16),
            Block.box(1, 8, 1, 15, 10, 15),
            Block.box(2, 12, 2, 14, 14, 14),
            Block.box(3, 16, 3, 13, 18, 13),
            Block.box(4, 20, 4, 12, 22, 12),
            Block.box(5, 24, 5, 11, 26, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ChristmasTree(Properties p) {
        super(p.strength(1F));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
