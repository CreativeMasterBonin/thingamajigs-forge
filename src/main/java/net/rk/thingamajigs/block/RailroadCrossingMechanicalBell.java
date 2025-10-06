package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.config.ThingamajigsServerConfigs;
import net.rk.thingamajigs.tag.ThingamajigsTags;

import java.util.List;

public class RailroadCrossingMechanicalBell extends Block {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int BELL_SPEED = 10; // how fast in ticks this bell will ring

    public RailroadCrossingMechanicalBell(Properties p) {
        super(p.strength(1.1F,12F).sound(SoundType.LANTERN).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false).setValue(FACING,Direction.NORTH));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState bs, BlockGetter bg, BlockPos bp) {
        return true;
    }

    @Override
    public int getSignal(BlockState bs, BlockGetter bg, BlockPos bp, Direction dir) {
        return bs.getValue(POWERED) ? 15:0;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean p_55671_) {
        if (!lvl.isClientSide) {
            boolean allverticalredstoneblocks = lvl.getBlockState(bp.below()).is(ThingamajigsTags.VERTICAL_REDSTONE_BLOCKS);
            boolean allrrbells = lvl.getBlockState(bp.below()).is(ThingamajigsTags.RAILROAD_CROSSING_BELLS);
            if(!allrrbells){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(lvl.getBlockState(bp.below()).getValue(POWERED) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
            }
        }
    }


    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        if(!slvl.isClientSide){
            if(bs.getValue(POWERED)){
                boolean allverticalredstoneblocks = slvl.getBlockState(bp.below()).is(ThingamajigsTags.VERTICAL_REDSTONE_BLOCKS);
                if(!allverticalredstoneblocks){
                    slvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    return;
                }
                attemptToHorn(slvl,bp);
                slvl.scheduleTick(bp,bs.getBlock(),ThingamajigsServerConfigs.COMMON.mechanicalBellSpeed.get(), TickPriority.LOW);
            }
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bsOri, boolean bo1) {
        if(!lvl.isClientSide()){
            lvl.scheduleTick(bp,bs.getBlock(),ThingamajigsServerConfigs.COMMON.mechanicalBellSpeed.get(),TickPriority.LOW);
        }
    }

    // "Baah!" Temp sound until overwritten. If not, "ba ba bell do you any wool? Yes, I DO NOT!"
    // other blocks calling this method must overwrite this or the default sound WILL be used!
    public boolean attemptToHorn(Level lp, BlockPos bp) {
        if (!lp.isClientSide) {
            lp.playSound(null, bp, SoundEvents.SHEEP_AMBIENT, SoundSource.BLOCKS, 2.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED,FACING);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.rr_bell.desc"));
    }
}
