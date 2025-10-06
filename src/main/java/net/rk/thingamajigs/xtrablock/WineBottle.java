package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class WineBottle extends Block {
    public static final VoxelShape BOTTLE = Stream.of(
            Block.box(6, 0, 6, 10, 10, 10),
            Block.box(6.5, 10, 6.5, 9.5, 11, 9.5),
            Block.box(7, 11, 7, 9, 15, 9),
            Block.box(7, 15, 7, 9, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WineBottle(Properties p) {
        super(p.strength(0.5F,7F).instabreak().sound(SoundType.GLASS));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return BOTTLE;
    }
}
