package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

public class BlueyMechanicalBell extends RailroadCrossingMechanicalBell {
    public static final VoxelShape ALL = Block.box(3, 0, 3, 13, 13, 13);
    public BlueyMechanicalBell(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }

    @Override
    public boolean attemptToHorn(Level lp, BlockPos bp) {
        if (!lp.isClientSide) {
            lp.playSound(null, bp, ThingamajigsSoundEvents.MECH_BELL_ONE.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }
}
