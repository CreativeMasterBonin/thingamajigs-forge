package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.rk.thingamajigs.block.custom.PavementMarking;

public class YellowRoadMarking extends PavementMarking {
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 16; // 10
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);


    public YellowRoadMarking(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE, 0));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    public static IntegerProperty getType(){
        return TYPE;
    }

    public static int getMinTypes(){
        return MIN_TYPES;
    }

    public static int getMaxTypes(){
        return MAX_TYPES;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
