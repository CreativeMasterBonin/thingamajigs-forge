package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class RailroadCrossingLights extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 3, 0, 6, 9, 5),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(7, 7, 5, 9, 9, 11),
            Block.box(0, 7, 5, 16, 9, 6),
            Block.box(10, 3, 0, 16, 9, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(11, 3, 0, 16, 9, 6),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(5, 7, 7, 11, 9, 9),
            Block.box(10, 7, 0, 11, 9, 16),
            Block.box(11, 3, 10, 16, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(10, 3, 11, 16, 9, 16),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(7, 7, 5, 9, 9, 11),
            Block.box(0, 7, 10, 16, 9, 11),
            Block.box(0, 3, 11, 6, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 3, 10, 5, 9, 16),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(5, 7, 7, 11, 9, 9),
            Block.box(5, 7, 0, 6, 9, 16),
            Block.box(0, 3, 0, 5, 9, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    //public static final IntegerProperty TYPE = IntegerProperty.create("type",0,3);
    // 0 = single, 1 = double, 2 = triple, 3 = angled (towards another street)

    public RailroadCrossingLights(Properties p) {
        super(p.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default ->{return Shapes.block();}
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> bs) {
        super.createBlockStateDefinition(bs.add(FACING,POWERED));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED,false);
    }

    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean p_55671_) {
        if (!lvl.isClientSide) {
            boolean allverticalredstoneblocks = lvl.getBlockState(bp.below()).is(ThingamajigsTags.VERTICAL_REDSTONE_BLOCKS);
            boolean allrrbells = lvl.getBlockState(bp.below()).is(ThingamajigsTags.RAILROAD_CROSSING_BELLS);

            boolean allrrbellsabove = lvl.getBlockState(bp.above()).is(ThingamajigsTags.RAILROAD_CROSSING_BELLS);

            // Bells and this block hate each other, so it's disabled.
            if(allrrbellsabove){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(lvl.getBlockState(bp.below()).getValue(POWERED) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else{
                    lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                }
                return;
            }

            boolean cant = lvl.getBlockState(bp.above()).is(ThingamajigsBlocks.RR_CANTILEVER.get());
            boolean cant2 = lvl.getBlockState(bp.above()).is(ThingamajigsBlocks.RR_CANTILEVER_END.get());
            boolean cant3 = lvl.getBlockState(bp.above()).is(ThingamajigsBlocks.RR_CANTILEVER_LIGHTS.get());

            if(cant || cant2 || cant3){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED)){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(!lvl.getBlockState(bp.below()).getValue(POWERED)){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else if(lvl.getBlockState(bp.below()).is(ThingamajigsBlocks.CROSSWALK_BUTTON.get())){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED)){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else{
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else{
                    lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                }
                return;
            }

            if(!allrrbells){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(lvl.getBlockState(bp.below()).getValue(POWERED) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else{
                    if(lvl.hasNeighborSignal(bp) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                        lvl.scheduleTick(bp.above(),this,3,TickPriority.LOW);
                    }
                    else if(lvl.hasNeighborSignal(bp) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                        lvl.scheduleTick(bp.above(),this,3,TickPriority.LOW);
                    }
                }
            }
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        sl.updateNeighborsAt(bp.above(),this);
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        return RenderShape.MODEL;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.rr_lights.desc"));
    }
}
