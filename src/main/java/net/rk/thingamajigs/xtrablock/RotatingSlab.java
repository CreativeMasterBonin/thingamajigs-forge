package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

public class RotatingSlab extends SlabBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public RotatingSlab(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING,Direction.NORTH).setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED,false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        BlockPos blockpos = bpc.getClickedPos();
        BlockState blockstate = bpc.getLevel().getBlockState(blockpos);
        Player ply = bpc.getPlayer();
        if (blockstate.is(this)){
            return blockstate.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED,false).setValue(FACING,ply.getDirection().getOpposite());
        }
        else{
            FluidState fluidstate = bpc.getLevel().getFluidState(blockpos);
            BlockState blockstate1 = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED,fluidstate.getType() == Fluids.WATER);
            Direction direction = bpc.getClickedFace();
            return direction != Direction.DOWN && (direction == Direction.UP || !(bpc.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? blockstate1.setValue(FACING,ply.getDirection().getOpposite()) : blockstate1.setValue(TYPE, SlabType.TOP).setValue(FACING,ply.getDirection().getOpposite());
        }
    }
}
