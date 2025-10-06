package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.rk.thingamajigs.block.custom.CustomTorchBlock;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class RedLanternBlock extends CustomTorchBlock {
    public RedLanternBlock(Properties p_57491_, ParticleOptions p_57492_) {
        super(p_57491_, p_57492_);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ThingamajigsItems.RED_LANTERN_ITEM.get());
    }
}
