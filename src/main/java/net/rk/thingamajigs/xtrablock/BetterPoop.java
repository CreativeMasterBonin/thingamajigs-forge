package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.ThingamajigsBlocks;

public class BetterPoop extends Block {
    private static final VoxelShape SHAPE = Block.box(1.5D, 0.0D, 1.5D, 13.5D, 13.5D, 13.5D);

    public BetterPoop(Properties p) {
        super(p);
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @SuppressWarnings("deprecated")
    @Override
    public boolean canBeReplaced(BlockState bs, Fluid fl) {
        return false;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ThingamajigsBlocks.POOP.get().asItem());
    }

    @SuppressWarnings("deprecated")
    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        BlockState cbb = lr.getBlockState(bp.below());
        return Block.isFaceFull(cbb.getCollisionShape(lr, bp.below()), Direction.UP);
    }
}
