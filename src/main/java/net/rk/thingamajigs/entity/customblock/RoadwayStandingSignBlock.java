package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.RoadwaySignBlockEntity;

public class RoadwayStandingSignBlock extends StandingSignBlock {
    public RoadwayStandingSignBlock(Properties p_56990_, WoodType p_56991_) {
        super(p_56990_, p_56991_);
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        return lr.getBlockState(bp.below()).isSolid()
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.STRAIGHT_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.PLUS_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.HOLDER_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.TL_CONNECTOR.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.T_POLE_B.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.VERTICAL_T_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.TRI_POLE_B.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.RAILROAD_CROSSING_LIGHTS.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.VERTICAL_AXIS_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.THREE_WAY_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.ALL_WAY_POLE.get())
                || lr.getBlockState(bp.below()).is(ThingamajigsBlocks.CROSSWALK_BUTTON.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new RoadwaySignBlockEntity(bp,bs);
    }
}
