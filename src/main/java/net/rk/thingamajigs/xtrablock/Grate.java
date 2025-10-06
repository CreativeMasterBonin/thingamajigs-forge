package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.rk.thingamajigs.block.custom.GlassLikeBlock;

import javax.annotation.Nullable;

public class Grate extends GlassLikeBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public Grate(Properties properties) {
        super(properties.strength(2f).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        FluidState fluidstate = bpc.getLevel().getFluidState(bpc.getClickedPos());
        return super.getStateForPlacement(bpc).setValue(WATERLOGGED, fluidstate.is(Fluids.WATER));
    }

    @Override
    public BlockState updateShape(BlockState bs, Direction dir, BlockState bs2, LevelAccessor la, BlockPos bp, BlockPos bp2) {
        if(bs.getValue(WATERLOGGED)){
            la.scheduleTick(bp,Fluids.WATER,Fluids.WATER.getTickDelay(la));
        }
        return super.updateShape(bs,dir,bs2,la,bp,bp2);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(true) : super.getFluidState(bs);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
}
