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

public class ArrowBeacon extends Block{
    public static final VoxelShape NORTH_SHAPE_ALT = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(6, 1, 2, 10, 5, 4),
            Block.box(5, 5, 0, 11, 6, 5),
            Block.box(5, 2, 0, 6, 5, 2),
            Block.box(10, 2, 0, 11, 5, 2),
            Block.box(7, 2, 4, 9, 4, 7),
            Block.box(0, 7, 7, 16, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_SHAPE_ALT = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(6, 1, 12, 10, 5, 14),
            Block.box(5, 5, 11, 11, 6, 16),
            Block.box(10, 2, 14, 11, 5, 16),
            Block.box(5, 2, 14, 6, 5, 16),
            Block.box(7, 2, 9, 9, 4, 12),
            Block.box(0, 7, 7, 16, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_SHAPE_ALT = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(2, 1, 6, 4, 5, 10),
            Block.box(0, 5, 5, 5, 6, 11),
            Block.box(0, 2, 10, 2, 5, 11),
            Block.box(0, 2, 5, 2, 5, 6),
            Block.box(4, 2, 7, 7, 4, 9),
            Block.box(7, 7, 0, 9, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_SHAPE_ALT = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(12, 1, 6, 14, 5, 10),
            Block.box(11, 5, 5, 16, 6, 11),
            Block.box(14, 2, 5, 16, 5, 6),
            Block.box(14, 2, 10, 16, 5, 11),
            Block.box(9, 2, 7, 12, 4, 9),
            Block.box(7, 7, 0, 9, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ArrowBeacon(Properties properties) {
        super(properties.strength(1F,2F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE,0).setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player player, InteractionHand hand, BlockHitResult bhr) {
        if(player.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.setBlock(bp,bs.cycle(TYPE),2);
                lvl.playSound(null,bp, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS,1.0F,1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos bp, CollisionContext p_60558_) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_SHAPE_ALT;
            case SOUTH:
                return SOUTH_SHAPE_ALT;
            case EAST:
                return EAST_SHAPE_ALT;
            case WEST:
                return WEST_SHAPE_ALT;
        }
        return Shapes.block();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TYPE,0).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.arrow_beacon.desc"));
    }
}
