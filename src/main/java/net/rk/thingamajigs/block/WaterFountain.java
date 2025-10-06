package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class WaterFountain extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NORTH = Shapes.join(Block.box(0, 0, 10, 16, 8, 16), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape SOUTH = Shapes.join(Block.box(0, 0, 0, 16, 8, 6), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape EAST = Shapes.join(Block.box(0, 0, 0, 6, 8, 16), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape WEST = Shapes.join(Block.box(10, 0, 0, 16, 8, 16), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);

    public WaterFountain(Properties properties) {
        super(properties.strength(1F,10F).sound(SoundType.LANTERN).noOcclusion());
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            default:
                return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
