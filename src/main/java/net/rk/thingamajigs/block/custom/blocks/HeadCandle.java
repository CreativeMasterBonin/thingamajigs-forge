package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.Random;
import java.util.stream.Stream;

public class HeadCandle extends ThingamajigsDecorativeBlock {
    public static final VoxelShape ALL = Stream.of(
            Block.box(4, 0, 4, 12, 8, 12),
            Block.box(7.5, 13, 7.5, 8.5, 14, 8.5),
            Block.box(7, 8.05, 7, 9, 13.05, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public HeadCandle(BlockBehaviour.Properties properties) {
        super(properties.strength(1F,5F).sound(SoundType.BONE_BLOCK).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rnds) {
        double x1 = (double)bp.getX() + 0.5D;
        double y1 = (double)bp.getY() + 1.0D;
        double z1 = (double)bp.getZ() + 0.5D;
        lvl.addParticle(ParticleTypes.FLAME,x1,y1,z1,0.0D,0.0D,0.0D);
    }
}
