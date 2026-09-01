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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.config.ThingamajigsServerConfigs;
import net.rk.thingamajigs.tag.ThingamajigsTags;

import java.util.List;
import java.util.stream.Stream;

public class RailroadCrossingMechanicalBell extends Block {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int BELL_SPEED = 10; // how fast in ticks this bell will ring

    public static final VoxelShape NORTH_STANDARD = Stream.of(
            Block.box(7, 0, 7, 9, 2, 9),
            Block.box(4, 3, 5, 12, 11, 7),
            Block.box(6, 2, 6, 10, 3, 10),
            Block.box(7, 6, 4, 9, 8, 5),
            Block.box(3, 11, 4, 13, 12, 10),
            Block.box(5, 4, 7, 11, 10, 11),
            Block.box(7, 3, 7, 9, 11, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_STANDARD = Stream.of(
            Block.box(7, 0, 7, 9, 2, 9),
            Block.box(9, 3, 4, 11, 11, 12),
            Block.box(6, 2, 6, 10, 3, 10),
            Block.box(11, 6, 7, 12, 8, 9),
            Block.box(6, 11, 3, 12, 12, 13),
            Block.box(5, 4, 5, 9, 10, 11),
            Block.box(7, 3, 7, 9, 11, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_STANDARD = Stream.of(
            Block.box(7, 0, 7, 9, 2, 9),
            Block.box(4, 3, 9, 12, 11, 11),
            Block.box(6, 2, 6, 10, 3, 10),
            Block.box(7, 6, 11, 9, 8, 12),
            Block.box(3, 11, 6, 13, 12, 12),
            Block.box(5, 4, 5, 11, 10, 9),
            Block.box(7, 3, 7, 9, 11, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_STANDARD = Stream.of(
            Block.box(7, 0, 7, 9, 2, 9),
            Block.box(5, 3, 4, 7, 11, 12),
            Block.box(6, 2, 6, 10, 3, 10),
            Block.box(4, 6, 7, 5, 8, 9),
            Block.box(4, 11, 3, 10, 12, 13),
            Block.box(7, 4, 5, 11, 10, 11),
            Block.box(7, 3, 7, 9, 11, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public RailroadCrossingMechanicalBell(Properties p) {
        super(p.strength(1.1F,12F).sound(SoundType.LANTERN).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false).setValue(FACING,Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH_STANDARD;}
            case SOUTH->{return SOUTH_STANDARD;}
            case EAST->{return EAST_STANDARD;}
            case WEST->{return WEST_STANDARD;}
        }
        return Shapes.block();
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
                slvl.scheduleTick(bp,bs.getBlock(),ThingamajigsServerConfigs.SERVER.mechanicalBellSpeed.get(), TickPriority.LOW);
            }
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bsOri, boolean bo1) {
        if(!lvl.isClientSide()){
            lvl.scheduleTick(bp,bs.getBlock(),ThingamajigsServerConfigs.SERVER.mechanicalBellSpeed.get(),TickPriority.LOW);
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
