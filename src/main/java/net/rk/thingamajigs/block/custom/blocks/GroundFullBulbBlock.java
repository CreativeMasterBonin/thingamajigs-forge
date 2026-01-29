package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
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

public class GroundFullBulbBlock extends CustomTorchBlock {
    public GroundFullBulbBlock(BlockBehaviour.Properties p_57491_, ParticleOptions p_57492_) {
        super(p_57491_,p_57492_);
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        // do nothing please
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState newState, boolean p_60570_) {
        level.playSound(null,pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS,1.0f,
                ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.17f));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        ItemStack item = ThingamajigsItems.FULL_BULB_ITEM.get().getDefaultInstance();
        return item;
    }
}
