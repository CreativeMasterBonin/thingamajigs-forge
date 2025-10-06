package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.RailroadCrossingElectronicBell;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

import java.util.stream.Stream;

public class EBellOne extends RailroadCrossingElectronicBell {
    public static final VoxelShape ALL = Stream.of(
            Block.box(7, 0, 7, 9, 1, 9),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(6, 2, 6, 10, 14, 10),
            Block.box(6, 14, 6, 10, 16, 10),
            Block.box(7, 2, 7, 9, 14, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public EBellOne(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }

    @Override
    public boolean attemptToHorn(Level lp, BlockPos bp) {
        if (!lp.isClientSide) {
            lp.playSound(null, bp,
                    ThingamajigsSoundEvents.EBELL_ONE.get(),
                    SoundSource.BLOCKS, 2.0F, 1.0F);
            return true;
        }
        else {
            return false;
        }
    }
}
