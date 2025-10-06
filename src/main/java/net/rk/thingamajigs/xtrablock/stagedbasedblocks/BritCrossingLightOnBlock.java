package net.rk.thingamajigs.xtrablock.stagedbasedblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.block.ThingamajigsBlocks;

public class BritCrossingLightOnBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BritCrossingLightOnBlock(Properties p) {
        super(p.strength(1f,2f)
                .sound(SoundType.LANTERN).mapColor(MapColor.METAL).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public void onPlace(BlockState bs1, Level lvl, BlockPos bp, BlockState bs2, boolean b1) {
        lvl.scheduleTick(bp,this,2,TickPriority.LOW);
    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        BlockState bsnori = ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS.get().defaultBlockState();
        BlockState belowState = sl.getBlockState(bp.below());
        boolean checker = false; // if this is true, the block should still tick

        if(belowState.is(ThingamajigsBlocks.VERTICAL_POLE_REDSTONE.get())){
            if(!belowState.getValue(POWERED)){
                sl.setBlock(bp,bsnori.setValue(POWERED,false).setValue(FACING,bs.getValue(FACING)),3);
                sl.updateNeighborsAt(bp.above(),this);
            }
            else{
                checker = true;
            }
        }
        else{
            checker = true;
        }
        // check val
        if(checker){
            sl.scheduleTick(bp,this,5,TickPriority.LOW);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS.get().asItem());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED,FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, false).setValue(FACING,context.getHorizontalDirection().getOpposite());
    }
}
