package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.Pole;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.misc.ThingamajigsBlockProperties;

import java.util.stream.Stream;

@SuppressWarnings({"deprecated, unused"})
public class DJGoboLight extends ThingamajigsDecorativeBlock {
    public static final BooleanProperty CONNECTED = ThingamajigsBlockProperties.CONNECTED;
    public static final BooleanProperty ON = ThingamajigsBlockProperties.ON;

    public static final VoxelShape NORTHSOUTH = Block.box(0, 0, 5, 16, 10, 11);
    public static final VoxelShape EASTWEST = Block.box(5, 0, 0, 11, 10, 16);

    public static final VoxelShape CONNECTED_NORTHSOUTH = Stream.of(
            NORTHSOUTH, Pole.VERTICAL_ALL
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape CONNECTED_EASTWEST= Stream.of(
            EASTWEST,Pole.VERTICAL_ALL
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DJGoboLight(BlockBehaviour.Properties p) {
        super(p.strength(1f,2f).sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(CONNECTED,false)
                .setValue(ON,false)
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(pState.getValue(FACING)){
            case NORTH,SOUTH -> {
                if(pState.getValue(CONNECTED)){
                    return CONNECTED_NORTHSOUTH;
                }
                else{
                    return NORTHSOUTH;
                }
            }
            case EAST,WEST -> {
                if(pState.getValue(CONNECTED)){
                    return CONNECTED_EASTWEST;
                }
                else{
                    return EASTWEST;
                }
            }
        }
        return Shapes.block();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED,CONNECTED,ON);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.isShiftKeyDown()){
            if (pLevel.isClientSide) {
                BlockState blockstate1 = pState.cycle(ON);
                pLevel.playLocalSound(pPos,SoundEvents.ITEM_FRAME_ROTATE_ITEM,SoundSource.BLOCKS,0.75f,1f,true);
                return InteractionResult.SUCCESS;
            }
            else {
                BlockState blockstate = pState = pState.cycle(ON);
                pLevel.setBlock(pPos, pState, 3);
                pLevel.updateNeighborsAt(pPos,this);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        BlockState bsAbove = pLevel.getBlockState(pPos.above());
        boolean airblk = bsAbove.isAir();
        boolean isSelf = bsAbove.getBlock() instanceof DJGoboLight;

        if(!airblk && !(bsAbove.getBlock() instanceof LiquidBlock) && !isSelf){
            pLevel.setBlock(pPos,pState.setValue(CONNECTED,true),3);
        }
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        BlockState bsAbove = pLevel.getBlockState(pPos.above());
        boolean airblk = bsAbove.isAir();
        boolean isSelf = bsAbove.getBlock() instanceof DJGoboLight;

        if(!airblk && !(bsAbove.getBlock() instanceof LiquidBlock) && !isSelf){
            pLevel.setBlock(pPos,pState.setValue(CONNECTED,true),3);
        }
        else {
            pLevel.setBlock(pPos,pState.setValue(CONNECTED,false),3);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos bs = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(bs);
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(CONNECTED,false)
                .setValue(ON,false);
    }
}
