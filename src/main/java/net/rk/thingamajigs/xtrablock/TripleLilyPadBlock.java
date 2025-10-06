package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class TripleLilyPadBlock extends CustomWaterlilyBlock{
    public TripleLilyPadBlock(Properties p) {
        super(p);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ThingamajigsItems.TRIPLE_LILY_PAD_ITEM.get());
    }
}
