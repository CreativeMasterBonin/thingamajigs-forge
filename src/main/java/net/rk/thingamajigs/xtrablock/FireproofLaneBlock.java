package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.LaneBlock;

public class FireproofLaneBlock extends LaneBlock {
    public FireproofLaneBlock(Properties p) {
        super(p.sound(SoundType.NETHER_WOOD));
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }
}
