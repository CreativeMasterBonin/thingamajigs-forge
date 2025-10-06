package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TickingTracker;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

import javax.annotation.Nullable;

public class AlarmedDoor extends DoorBlock {
    public BlockSetType type = BlockSetType.IRON;

    public AlarmedDoor(Properties p) {
        super(p.requiresCorrectToolForDrops().strength(1.0F,75F).noOcclusion().sound(SoundType.METAL), BlockSetType.IRON);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, false).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    // we want the door to play an alarm sound every (certain amount of) ticks that are called
    // this is a ticking loop! Do not place a billion of these doors!
    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        if(!slvl.isClientSide()){
            if(bs.getValue(OPEN)){
                slvl.playSound(null,bp,ThingamajigsSoundEvents.BEEP.get(),SoundSource.BLOCKS,1.0F,1.0F);
                slvl.scheduleTick(bp,bs.getBlock(),45,TickPriority.LOW);
            }
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bsOri, boolean bo1) {
        if(!lvl.isClientSide()){
            lvl.scheduleTick(bp,bs.getBlock(),45,TickPriority.LOW);
        }
    }

    @Override
    public InteractionResult use(BlockState p_52769_, Level p_52770_, BlockPos p_52771_, Player p_52772_, InteractionHand p_52773_, BlockHitResult p_52774_) {
        if (!this.type.canOpenByHand()) {
            return InteractionResult.PASS;
        } else {
            p_52769_ = p_52769_.cycle(OPEN);
            p_52770_.setBlock(p_52771_, p_52769_, 10);
            p_52770_.gameEvent(p_52772_, this.isOpen(p_52769_) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, p_52771_);
            return InteractionResult.sidedSuccess(p_52770_.isClientSide);
        }
    }

    @Override
    public void neighborChanged(BlockState p_52776_, Level p_52777_, BlockPos p_52778_, Block p_52779_, BlockPos p_52780_, boolean p_52781_) {
        boolean flag = p_52777_.hasNeighborSignal(p_52778_) || p_52777_.hasNeighborSignal(p_52778_.relative(p_52776_.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (!this.defaultBlockState().is(p_52779_) && flag != p_52776_.getValue(POWERED)) {
            if (flag != p_52776_.getValue(OPEN)) {
                p_52777_.gameEvent((Entity)null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, p_52778_);
            }
            //
            if(p_52776_.getValue(OPEN)){
                p_52777_.playSound(null,p_52778_,SoundEvents.IRON_DOOR_CLOSE,SoundSource.BLOCKS,1.0F,1.0F);
            }
            else{
                p_52777_.playSound(null,p_52778_,SoundEvents.IRON_DOOR_OPEN,SoundSource.BLOCKS,1.0F,1.0F);
            }
            p_52777_.setBlock(p_52778_, p_52776_.setValue(POWERED, Boolean.valueOf(flag)).setValue(OPEN, Boolean.valueOf(flag)), 2);
        }

    }

    public boolean attemptToBeep(Level lp, BlockPos bp) {
        if (!lp.isClientSide) {
            lp.playSound(null, bp, ThingamajigsSoundEvents.BEEP.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
            return true;
        }
        else {
            return false;
        }
    }
}
