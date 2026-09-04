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

public class LoudFireAlarm extends RedstoneLampBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(5, 6, 14, 11, 14, 16),
            Block.box(5, 7, 13, 11, 13, 14),
            Block.box(5.75, 10.25, 12, 10.25, 12.25, 13),
            Block.box(6, 8, 12.9, 10, 10, 12.9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(5, 6, 0, 11, 14, 2),
            Block.box(5, 7, 2, 11, 13, 3),
            Block.box(5.75, 10.25, 3, 10.25, 12.25, 4),
            Block.box(6, 8, 3.0999999999999996, 10, 10, 3.0999999999999996)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 6, 5, 2, 14, 11),
            Block.box(2, 7, 5, 3, 13, 11),
            Block.box(3, 10.25, 5.75, 4, 12.25, 10.25),
            Block.box(3.0999999999999996, 8, 6, 3.0999999999999996, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(14, 6, 5, 16, 14, 11),
            Block.box(13, 7, 5, 14, 13, 11),
            Block.box(12, 10.25, 5.75, 13, 12.25, 10.25),
            Block.box(12.9, 8, 6, 12.9, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LoudFireAlarm(Properties p) {
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
                attemptToLoud(slvl,bp);
                slvl.scheduleTick(bp,bs.getBlock(),100, TickPriority.LOW);
            }
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bsOri, boolean bo1) {
        if(!lvl.isClientSide()){
            lvl.scheduleTick(bp,bs.getBlock(),20,TickPriority.LOW);
        }
    }

    public boolean attemptToLoud(Level lp, BlockPos bp) {
        if (!lp.isClientSide) {
            lp.playSound(null, bp, ThingamajigsSoundEvents.CODE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        }
        else {
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
