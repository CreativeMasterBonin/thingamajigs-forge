package net.rk.thingamajigs.entity.customblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.roadsigns.RedRoadSignBlockEntity;

public class RedRWWallSignBlock extends WallSignBlock {
    public RedRWWallSignBlock(Properties p_58068_, WoodType p_58069_) {
        super(p_58068_, p_58069_);
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        return lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).isSolid()
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.STRAIGHT_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.STRAIGHT_HORIZONTAL_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.T_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.T_POLE_B.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.T_POLE_C.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.HOLDER_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.AXIS_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.TL_CONNECTOR.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.PLUS_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.L_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.L_ONLY_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.VERTICAL_T_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.TRI_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.TRI_POLE_B.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.VERTICAL_AXIS_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.THREE_WAY_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.ALL_WAY_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(ThingamajigsBlocks.T_HORZ_ONLY_POLE.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new RedRoadSignBlockEntity(bp,bs);
    }
}
