package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class PhoneSwitcher extends ThingamajigsDecorativeBlock {
    public PhoneSwitcher(Properties properties) {
        super(properties.strength(1F,5F).sound(SoundType.COPPER));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return Block.box(4,0,4,12,24,12);
    }
}
