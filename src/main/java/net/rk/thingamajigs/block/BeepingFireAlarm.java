package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import net.minecraftforge.registries.ForgeRegistries;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.legacy.BeepSoundBlockPro;
import net.rk.thingamajigs.legacy.BlahblahtestRedstoneOnProcedure;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BeepingFireAlarm extends RedstoneLampBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    public static final VoxelShape EAST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    public static final VoxelShape WEST_SHAPE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public BeepingFireAlarm(Properties p) {
        super(p.strength(1.5F,15F).sound(SoundType.LANTERN).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return NORTH_SHAPE;
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }


    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        if (bs.getValue(LIT) && !slvl.hasNeighborSignal(bp)) {
            slvl.setBlock(bp, bs.cycle(LIT), 2);
        }
        if(!slvl.isClientSide()){
            if(bs.getValue(LIT)){
                attemptToBeep(slvl,bp);
                slvl.scheduleTick(bp,bs.getBlock(),20, TickPriority.LOW);
            }
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bsOri, boolean bo1) {
        if(!lvl.isClientSide()){
            lvl.scheduleTick(bp,bs.getBlock(),20,TickPriority.LOW);
        }
    }

    public boolean attemptToBeep(Level lp, BlockPos bp) {
        if (!lp.isClientSide) {
            lp.playSound(null, bp, ThingamajigsSoundEvents.BEEP.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
            //lp.playSound(null, bp, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 2.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.fire_alarm.desc"));
    }
}
