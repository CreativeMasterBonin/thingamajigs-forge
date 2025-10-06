package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class HorizontalConnectingBlock extends Block {
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,9);

    public HorizontalConnectingBlock(Properties p) {
        super(p.noLootTable());
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 0));
    }

    /*
        0 north, 1 north-east, 2 north-west
        3 south, 4 south-east, 5 south-west
        6 east
        7 west
        8 none
        9 all
    */

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block b, BlockPos pos2, boolean bln) {
        // these booleans check if north, south, east and west are 'this' block, respectively
        // if so, they are marked with a 'true' value which is used below in the if-else chain
        boolean north_ok = level.getBlockState(pos.north()).is(state.getBlock());
        boolean south_ok = level.getBlockState(pos.south()).is(state.getBlock());
        boolean east_ok = level.getBlockState(pos.east()).is(state.getBlock());
        boolean west_ok = level.getBlockState(pos.west()).is(state.getBlock());

        // check "on a neighbor update" if there are same blocks in a certain direction of this currently checking block
        if(!north_ok && !south_ok && !east_ok && !west_ok){
            state.trySetValue(TYPE,8); // No Directions (singleton)
        }
        else if(north_ok && south_ok && east_ok && west_ok){
            state.trySetValue(TYPE,9); // All Directions (middle/center)
        }
        else if(level.getBlockState(pos.north()).is(state.getBlock())){
            if(level.getBlockState(pos.east()).is(state.getBlock())){
                state.trySetValue(TYPE,1); // north-east
            }
            else if(level.getBlockState(pos.west()).is(state.getBlock())){
                state.trySetValue(TYPE,2); // north-west
            }
            else{
                state.trySetValue(TYPE,0); // north
            }
        }
        else if(level.getBlockState(pos.south()).is(state.getBlock())){
            if(level.getBlockState(pos.east()).is(state.getBlock())){
                state.trySetValue(TYPE,4); // south-east
            }
            else if(level.getBlockState(pos.west()).is(state.getBlock())){
                state.trySetValue(TYPE,5); // south-west
            }
            else{
                state.trySetValue(TYPE,3); // south
            }
        }
        else if(level.getBlockState(pos.east()).is(state.getBlock())){
            state.trySetValue(TYPE,6); // east
        }
        else if(level.getBlockState(pos.west()).is(state.getBlock())){
            state.trySetValue(TYPE,7); // west
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TYPE, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }
}
