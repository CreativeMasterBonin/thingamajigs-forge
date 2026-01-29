package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
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
import net.rk.thingamajigs.block.custom.CustomTorchBlock;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class GroundClearLanternBlock extends CustomTorchBlock {
    public GroundClearLanternBlock(BlockBehaviour.Properties p_57491_, ParticleOptions p_57492_) {
        super(p_57491_,p_57492_);
    }

    @Override
    public void animateTick(BlockState p_222593_, Level p_222594_, BlockPos p_222595_, RandomSource p_222596_) {
        double d0 = (double)p_222595_.getX() + 0.5D;
        double d1 = (double)p_222595_.getY() + 0.5D;
        double d2 = (double)p_222595_.getZ() + 0.5D;
        p_222594_.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        ItemStack item = ThingamajigsItems.CLEAR_LANTERN_ITEM.get().getDefaultInstance();
        return item;
    }
}
