package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.rk.thingamajigs.block.custom.CustomWallTorchBlock;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class WallFullBulbBlock extends CustomWallTorchBlock {
    public WallFullBulbBlock(BlockBehaviour.Properties p, ParticleOptions po) {
        super(p, po);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        // do nothing please
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        ItemStack item = ThingamajigsItems.FULL_BULB_ITEM.get().getDefaultInstance();
        return item;
    }
}
