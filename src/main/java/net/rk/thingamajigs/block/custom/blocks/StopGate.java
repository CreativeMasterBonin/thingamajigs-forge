package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import java.util.stream.Stream;

import static net.minecraft.core.Direction.*;

public class StopGate extends RedstoneLampBlock {

    public static final VoxelShape NORTHSOUTH_ON = Block.box(-16, 0, 0, 32, 12, 16);
    public static final VoxelShape EASTWEST_ON = Block.box(0, 0, -16, 16, 12, 32);

    public static final VoxelShape NORTH_OFF = Stream.of(
            Block.box(-16, 0, 0, 0, 12, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_OFF = Stream.of(
            Block.box(16, 0, 0, 32, 12, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_OFF = Stream.of(
            Block.box(0, 0, -16, 16, 12, 0),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_OFF = Stream.of(
            Block.box(0, 0, 16, 16, 12, 32),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public StopGate(Properties p) {
        super(p.strength(1F,5F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, NORTH).setValue(LIT, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        if(direction == NORTH){
            if(state.getValue(LIT)){
                return NORTHSOUTH_ON;
            }
            else{
                return NORTH_OFF;
            }
        }
        else if(direction == SOUTH){
            if(state.getValue(LIT)){
                return NORTHSOUTH_ON;
            }
            else{
                return SOUTH_OFF;
            }
        }
        else if(direction == EAST){
            if(state.getValue(LIT)){
                return EASTWEST_ON;
            }
            else{
                return EAST_OFF;
            }
        }
        else if(direction == WEST){
            if(state.getValue(LIT)){
                return EASTWEST_ON;
            }
            else{
                return WEST_OFF;
            }
        }
        return NORTH_OFF;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, false).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
