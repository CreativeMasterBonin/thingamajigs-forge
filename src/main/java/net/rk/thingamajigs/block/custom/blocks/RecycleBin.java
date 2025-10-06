package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class RecycleBin extends ThingamajigsDecorativeBlock {
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 9;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);
    public static final VoxelShape BLOCK_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);

    public RecycleBin(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE).setValue(TYPE, 0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit){
        if(!pLevel.isClientSide()){
            if(pHand == InteractionHand.MAIN_HAND && pPlayer.isShiftKeyDown()){
                int tv_type = pState.getValue(TYPE);
                tv_type++;
                pLevel.setBlock(pPos, pState.setValue(TYPE, tv_type), 0);

                if(tv_type >= MAX_TYPES){
                    tv_type = 0;
                    pLevel.setBlock(pPos, pState.setValue(TYPE, tv_type), 0);
                }
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return BLOCK_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED,TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(TYPE, 0);
    }
}
