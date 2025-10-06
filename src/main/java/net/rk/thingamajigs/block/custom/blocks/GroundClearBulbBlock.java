package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.rk.thingamajigs.block.custom.CustomTorchBlock;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class GroundClearBulbBlock extends CustomTorchBlock {
    public GroundClearBulbBlock(BlockBehaviour.Properties p_57491_, ParticleOptions p_57492_) {
        super(p_57491_,p_57492_);
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        // do nothing please
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        ItemStack item = ThingamajigsItems.CLEAR_BULB_ITEM.get().getDefaultInstance();
        return item;
    }
}
