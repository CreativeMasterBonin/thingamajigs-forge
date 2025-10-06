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

public class RedStopAllwayBeacon extends Block {
    public static final VoxelShape ALL = Stream.of(
            Block.box(3, -3, 3, 13, 9, 13),
            Block.box(5, 9, 1, 11, 15, 7),
            Block.box(5, 15, 0, 11, 16, 8),
            Block.box(4, 13, 0, 5, 16, 8),
            Block.box(11, 13, 0, 12, 16, 8),
            Block.box(9, 9, 5, 15, 15, 11),
            Block.box(8, 15, 5, 16, 16, 11),
            Block.box(8, 12, 5, 9, 15, 11),
            Block.box(8, 13, 4, 16, 16, 5),
            Block.box(8, 13, 11, 16, 16, 12),
            Block.box(5, 9, 9, 11, 15, 15),
            Block.box(5, 15, 8, 11, 16, 16),
            Block.box(11, 13, 8, 12, 16, 16),
            Block.box(4, 13, 8, 5, 16, 16),
            Block.box(4, -1, 2.85, 12, 7, 2.85),
            Block.box(2.85, -1, 4, 2.85, 7, 12),
            Block.box(13.15, -1, 4, 13.15, 7, 12),
            Block.box(4, -1, 13.15, 12, 7, 13.15),
            Block.box(7, 16, 7, 9, 17, 9),
            Block.box(1, 9, 5, 7, 15, 11),
            Block.box(0, 15, 5, 8, 16, 11),
            Block.box(7, 12, 5, 8, 15, 11),
            Block.box(0, 13, 11, 8, 16, 12),
            Block.box(0, 13, 4, 8, 16, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    // anyone else wonder why voxelshapes are called shapes but they are made of voxels...? yeah me neither...

    public RedStopAllwayBeacon(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
