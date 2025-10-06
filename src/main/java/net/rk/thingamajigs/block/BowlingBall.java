package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BowlingBall extends Block {

    public static final VoxelShape BOWLING_BALL_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D,  12.0D, 15.0D);

    public BowlingBall(Properties p) {
        super(p.strength(0.5F,5.2F).noOcclusion());
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return BOWLING_BALL_SHAPE;
    }
}
