package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.HitResult;
import net.rk.thingamajigs.block.custom.CustomWallTorchBlock;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class WallClearLanternBlock extends CustomWallTorchBlock {
    public WallClearLanternBlock(BlockBehaviour.Properties p, ParticleOptions po) {
        super(p, po);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // overwritten to correct height of particle spawn
    @Override
    public void animateTick(BlockState p_222660_, Level p_222661_, BlockPos p_222662_, RandomSource p_222663_) {
        Direction direction = p_222660_.getValue(FACING);
        double d0 = (double)p_222662_.getX() + 0.5D;
        double d1 = (double)p_222662_.getY() + 0.4D; // we want the Y position to be slightly less than the default torch one
        double d2 = (double)p_222662_.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;
        Direction direction1 = direction.getOpposite();
        p_222661_.addParticle(this.flameParticle, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.2D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        ItemStack item = ThingamajigsItems.CLEAR_LANTERN_ITEM.get().getDefaultInstance();
        return item;
    }
}
