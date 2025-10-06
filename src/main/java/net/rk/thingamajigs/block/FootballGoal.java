package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.entity.customblock.FootballGoalBE;
import org.jetbrains.annotations.Nullable;

public class FootballGoal extends ThingamajigsDecorativeBlock implements EntityBlock {
    public FootballGoal(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FootballGoalBE(blockPos,blockState);
    }
}
