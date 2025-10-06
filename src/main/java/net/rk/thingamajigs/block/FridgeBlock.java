package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.rk.thingamajigs.entity.FridgeBlockEntity;
import org.jetbrains.annotations.Nullable;

public class FridgeBlock extends DoubleTallDecorationBlock implements EntityBlock {
    public static final VoxelShape BLOCK_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);

    public FridgeBlock(Properties p) {
        super(p.strength(1.25F,10F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return BLOCK_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof FridgeBlockEntity){
                Containers.dropContents(level,blockPos,(FridgeBlockEntity)blockEntity);
            }
        }
        super.onRemove(state,level,blockPos,newState,isMoving);
    }

    @Override
    public InteractionResult use(BlockState bs, Level l, BlockPos bp, Player pl, InteractionHand h, BlockHitResult bhr) {
        if(!l.isClientSide()){
            BlockEntity blockEntity = l.getBlockEntity(bp);
            if(blockEntity instanceof FridgeBlockEntity){
                l.playSound(null,bp, SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundSource.BLOCKS,1.0f,1.0f);
                NetworkHooks.openScreen(((ServerPlayer)pl), (FridgeBlockEntity)blockEntity,bp);
            }
            else{
                throw new IllegalStateException("FridgeBlockEntity Container Provider is missing!");
            }
            // Sided success for server network packets
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.sidedSuccess(l.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new FridgeBlockEntity(bp,bs);
    }
}
