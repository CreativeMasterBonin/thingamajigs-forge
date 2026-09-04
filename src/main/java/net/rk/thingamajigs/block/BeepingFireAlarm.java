package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

import java.util.List;
import java.util.stream.Stream;

public class BeepingFireAlarm extends RedstoneLampBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(2, 5, 14, 3, 13, 16),
            Block.box(3, 4, 14, 13, 14, 16),
            Block.box(4, 3, 14, 12, 4, 16),
            Block.box(4, 14, 14, 12, 15, 16),
            Block.box(13, 5, 14, 14, 13, 16),
            Block.box(4, 9, 12, 12, 13, 14),
            Block.box(5, 5, 13, 11, 6, 14),
            Block.box(5, 7, 13, 11, 8, 14),
            Block.box(5, 10, 13, 11, 12, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(13, 5, 0, 14, 13, 2),
            Block.box(3, 4, 0, 13, 14, 2),
            Block.box(4, 3, 0, 12, 4, 2),
            Block.box(4, 14, 0, 12, 15, 2),
            Block.box(2, 5, 0, 3, 13, 2),
            Block.box(4, 9, 2, 12, 13, 4),
            Block.box(5, 5, 2, 11, 6, 3),
            Block.box(5, 7, 2, 11, 8, 3),
            Block.box(5, 10, 2, 11, 12, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 5, 2, 2, 13, 3),
            Block.box(0, 4, 3, 2, 14, 13),
            Block.box(0, 3, 4, 2, 4, 12),
            Block.box(0, 14, 4, 2, 15, 12),
            Block.box(0, 5, 13, 2, 13, 14),
            Block.box(2, 9, 4, 4, 13, 12),
            Block.box(2, 5, 5, 3, 6, 11),
            Block.box(2, 7, 5, 3, 8, 11),
            Block.box(2, 10, 5, 3, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(14, 5, 13, 16, 13, 14),
            Block.box(14, 4, 3, 16, 14, 13),
            Block.box(14, 3, 4, 16, 4, 12),
            Block.box(14, 14, 4, 16, 15, 12),
            Block.box(14, 5, 2, 16, 13, 3),
            Block.box(12, 9, 4, 14, 13, 12),
            Block.box(13, 5, 5, 14, 6, 11),
            Block.box(13, 7, 5, 14, 8, 11),
            Block.box(13, 10, 5, 14, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

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
