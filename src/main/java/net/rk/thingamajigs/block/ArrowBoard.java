package net.rk.thingamajigs.block;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

public class ArrowBoard extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty MODE = IntegerProperty.create("mode",0, 5);
    // 0 = off, 1 = corners, 2 = arrow_left, 3 = arrow_right, 4 = arrow_both, 5 = flashing_diamond

    public static final VoxelShape ALL = Block.box(0,0,0,16,32,16);

    public static final VoxelShape NORTH = Stream.of(
            Block.box(-2, 5, 0, 18, 7, 16),
            Block.box(-3, 0, 0, -2, 5, 5),
            Block.box(-3, 0, 11, -2, 5, 16),
            Block.box(18, 0, 11, 19, 5, 16),
            Block.box(18, 0, 0, 19, 5, 5),
            Block.box(1, 7, 2, 15, 13, 14),
            Block.box(7, 13, 10, 9, 32, 12),
            Block.box(-5, 18, 0, 21, 32, 2),
            Block.box(2, 8, 1, 3, 12, 2),
            Block.box(13, 8, 1, 14, 12, 2),
            Block.box(-2, 2, 2, 18, 3, 3),
            Block.box(-2, 2, 13, 18, 3, 14),
            Block.box(7, 3, 1, 9, 5, 15),
            Block.box(7, 19, 2, 9, 30, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 5, -2, 16, 7, 18),
            Block.box(11, 0, -3, 16, 5, -2),
            Block.box(0, 0, -3, 5, 5, -2),
            Block.box(0, 0, 18, 5, 5, 19),
            Block.box(11, 0, 18, 16, 5, 19),
            Block.box(2, 7, 1, 14, 13, 15),
            Block.box(4, 13, 7, 6, 32, 9),
            Block.box(14, 18, -5, 16, 32, 21),
            Block.box(14, 8, 2, 15, 12, 3),
            Block.box(14, 8, 13, 15, 12, 14),
            Block.box(13, 2, -2, 14, 3, 18),
            Block.box(2, 2, -2, 3, 3, 18),
            Block.box(1, 3, 7, 15, 5, 9),
            Block.box(6, 19, 7, 14, 30, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(-2, 5, 0, 18, 7, 16),
            Block.box(18, 0, 11, 19, 5, 16),
            Block.box(18, 0, 0, 19, 5, 5),
            Block.box(-3, 0, 0, -2, 5, 5),
            Block.box(-3, 0, 11, -2, 5, 16),
            Block.box(1, 7, 2, 15, 13, 14),
            Block.box(7, 13, 4, 9, 32, 6),
            Block.box(-5, 18, 14, 21, 32, 16),
            Block.box(13, 8, 14, 14, 12, 15),
            Block.box(2, 8, 14, 3, 12, 15),
            Block.box(-2, 2, 13, 18, 3, 14),
            Block.box(-2, 2, 2, 18, 3, 3),
            Block.box(7, 3, 1, 9, 5, 15),
            Block.box(7, 19, 6, 9, 30, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 5, -2, 16, 7, 18),
            Block.box(0, 0, 18, 5, 5, 19),
            Block.box(11, 0, 18, 16, 5, 19),
            Block.box(11, 0, -3, 16, 5, -2),
            Block.box(0, 0, -3, 5, 5, -2),
            Block.box(2, 7, 1, 14, 13, 15),
            Block.box(10, 13, 7, 12, 32, 9),
            Block.box(0, 18, -5, 2, 32, 21),
            Block.box(1, 8, 13, 2, 12, 14),
            Block.box(1, 8, 2, 2, 12, 3),
            Block.box(2, 2, -2, 3, 3, 18),
            Block.box(13, 2, -2, 14, 3, 18),
            Block.box(1, 3, 7, 15, 5, 9),
            Block.box(2, 19, 7, 10, 30, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ArrowBoard(Properties p) {
        super(p.strength(1F,5F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(MODE,0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return ALL;}
        }
    }

    // woo! right click to change mode functionality
    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if(p_60506_.isShiftKeyDown()){
            if(!p_60504_.isClientSide()){
                p_60504_.setBlock(p_60505_,p_60503_.cycle(MODE),2);
                p_60504_.playSound(null,p_60505_, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS,1.0F,1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, MODE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(MODE,0).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.arrow_board.desc"));
    }
}
