package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NorthPole extends Block {
    public static final VoxelShape BLOCK_SHAPE = Shapes.join(Block.box(5, 24, 5, 11, 30, 11), Block.box(6, 0, 6, 10, 24, 10), BooleanOp.OR);

    public NorthPole(Properties p) {
        super(p.strength(1.75F,5F));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return BLOCK_SHAPE;
    }
}
