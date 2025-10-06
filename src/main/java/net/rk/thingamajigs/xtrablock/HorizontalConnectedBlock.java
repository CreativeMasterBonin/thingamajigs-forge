package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;

public class HorizontalConnectedBlock extends Block {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public HorizontalConnectedBlock(Properties p) {
        super(p);
        this.defaultBlockState().setValue(NORTH,false).setValue(SOUTH,false)
                .setValue(EAST,false).setValue(WEST,false)
                .setValue(UP,false).setValue(DOWN,false);
    }

    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block b, BlockPos bp2, boolean bo1) {
        BlockState newBlockstate = bs;

        if(lvl.getBlockState(bp.north()).is(bs.getBlock())){
            newBlockstate.setValue(NORTH,true);
        }

        if(lvl.getBlockState(bp.south()).is(bs.getBlock())){
            newBlockstate.setValue(SOUTH,true);
        }

        if(lvl.getBlockState(bp.east()).is(bs.getBlock())){
            newBlockstate.setValue(EAST,true);
        }

        if(lvl.getBlockState(bp.west()).is(bs.getBlock())){
            newBlockstate.setValue(WEST,true);
        }

        if(lvl.getBlockState(bp.above()).is(bs.getBlock())){
            newBlockstate.setValue(UP,true);
        }

        if(lvl.getBlockState(bp.below()).is(bs.getBlock())){
            newBlockstate.setValue(DOWN,true);
        }

        // update to the new state we just finished modifying
        lvl.setBlock(bp,newBlockstate,3);
    }

    /*
    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player p, InteractionHand h, BlockHitResult bhr) {
        String stateStr = bs.toString();

        if(p.isShiftKeyDown()){
            p.displayClientMessage(Component.literal("This block is state: " + stateStr),false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    */

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH,SOUTH,EAST,WEST,UP,DOWN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(NORTH,false).setValue(SOUTH,false)
                .setValue(EAST,false).setValue(WEST,false)
                .setValue(UP,false).setValue(DOWN,false);
    }
}
