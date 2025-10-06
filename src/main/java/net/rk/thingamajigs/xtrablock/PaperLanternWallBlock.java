package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.rk.thingamajigs.block.custom.CustomWallTorchBlock;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class PaperLanternWallBlock extends CustomWallTorchBlock {
    public PaperLanternWallBlock(Properties p, ParticleOptions po) {
        super(p, po);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ThingamajigsItems.PAPER_LANTERN_ITEM.get());
    }
}
