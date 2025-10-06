package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class ComputerControls extends ThingamajigsDecorativeBlock {
    public static final VoxelShape CCSHAPE = Block.box(0,0,0,16,1,16);

    public ComputerControls(Properties properties) {
        super(properties.strength(0.5F,1F).sound(SoundType.LANTERN));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return CCSHAPE;
    }
}
