package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.Optional;

public class BluemanStatueNoToggle extends ThingamajigsDecorativeBlock {
    public BluemanStatueNoToggle(Properties properties) {
        super(properties.noOcclusion().sound(SoundType.POWDER_SNOW).strength(0.5F,0.25F));
    }

    public static final VoxelShape ALL = Optional.of(Block.box(4, 0, 4, 12, 23, 12)).get();

    // requested by blueman himself... for christmas stuff...

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
