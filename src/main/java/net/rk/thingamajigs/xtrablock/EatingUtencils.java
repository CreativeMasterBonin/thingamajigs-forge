package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class EatingUtencils extends ThingamajigsDecorativeBlock {
    public static final VoxelShape ALL = Block.box(0.0D,0.0D,0.0D,16.0D,1.0D,16.0D);

    public EatingUtencils(Properties properties) {
        super(properties.strength(0.75F,15F)
                .mapColor(MapColor.METAL)
                .sound(SoundType.WOOL)
                .noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }
}
