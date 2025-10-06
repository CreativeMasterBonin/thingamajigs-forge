package net.rk.thingamajigs.xtrablock.redstoneblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class RailroadCrossingCantileverLights extends RailroadCrossingCantilever {
    public RailroadCrossingCantileverLights(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public boolean isSignalSource(BlockState bs) {
        return false;
    }

    @Override
    public int getSignal(BlockState bs, BlockGetter bg, BlockPos bp, Direction dir) {
        return 0;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        return this.defaultBlockState().setValue(FACING, bpc.getHorizontalDirection().getOpposite()).setValue(POWERED, bpc.getLevel().hasNeighborSignal(bpc.getClickedPos()));
    }

    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos p_55668_, Block blk, BlockPos bp, boolean p_55671_) {
        if (!lvl.isClientSide) {
            boolean flag = bs.getValue(POWERED);
            if (flag != lvl.hasNeighborSignal(p_55668_)) {
                if (flag) {
                    lvl.scheduleTick(p_55668_, this, 4);
                } else {
                    lvl.setBlock(p_55668_, bs.cycle(POWERED), 2);
                }
            }

        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rnd) {
        if (bs.getValue(POWERED) && !slvl.hasNeighborSignal(bp)) {
            slvl.setBlock(bp, bs.cycle(POWERED), 2);
        }
    }
}
