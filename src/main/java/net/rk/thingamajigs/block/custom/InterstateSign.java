package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InterstateSign extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 10;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);
    public static final VoxelShape SIGN_SHAPE_SIDE = Block.box(6.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    public static final VoxelShape SIGN_SHAPE = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 8.0D);

    public InterstateSign(Properties properties) {
        super(properties.strength(0.25F).sound(SoundType.LANTERN).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE, 0));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return SIGN_SHAPE;
            case SOUTH:
                return SIGN_SHAPE;
            case EAST:
                return SIGN_SHAPE_SIDE;
            case WEST:
                return SIGN_SHAPE_SIDE;
            default:
                return SIGN_SHAPE;
        }
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.translatable("tooltip.thingamajigs.interstate_sign"));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit){
        if(!pLevel.isClientSide()){
            if(pHand == InteractionHand.MAIN_HAND && pPlayer.isShiftKeyDown()){
                int tv_type = pState.getValue(TYPE);
                double d0 = (double)pPos.getX() + 0.5D;
                double d1 = (double)pPos.getY() + 0.5D;
                double d2 = (double)pPos.getZ() + 0.5D;

                tv_type++;
                pLevel.setBlock(pPos, pState.setValue(TYPE, tv_type), 0);

                if(tv_type >= MAX_TYPES){
                    tv_type = 0;
                    pLevel.setBlock(pPos, pState.setValue(TYPE, tv_type), 0);
                }

                playSound(pLevel, d0, d1, d2);
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    // Old Method for all of Old Thingamajig's SoundEvents, which were very convoluted and annoying to set up
    public static void playSound(LevelAccessor world, double x, double y, double z) {
        if(world instanceof Level _level) {
            if(!_level.isClientSide()) {
                _level.playSound(null, new BlockPos((int) x, (int) y, (int) z), SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS, 10, 1);
            }
            else {
                _level.playLocalSound(x, y, z, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS, 10, 1, false);
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
