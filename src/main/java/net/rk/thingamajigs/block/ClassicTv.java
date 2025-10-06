package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

import java.util.List;
import java.util.stream.Stream;

public class ClassicTv extends Block {

    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 3, 4, 20, 19, 12),
            Block.box(-4, 3, 4, 0, 19, 12),
            Block.box(0, 0, 5, 1, 3, 7),
            Block.box(15, 0, 5, 16, 3, 7),
            Block.box(0, 0, 9, 1, 3, 11),
            Block.box(15, 0, 9, 16, 3, 11),
            Block.box(-2, 4, 12, 18, 18, 15),
            Block.box(-3, 15, 3, -1, 17, 4),
            Block.box(-3, 12, 3, -1, 14, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(-4, 3, 4, 16, 19, 12),
            Block.box(16, 3, 4, 20, 19, 12),
            Block.box(15, 0, 9, 16, 3, 11),
            Block.box(0, 0, 9, 1, 3, 11),
            Block.box(15, 0, 5, 16, 3, 7),
            Block.box(0, 0, 5, 1, 3, 7),
            Block.box(-2, 4, 1, 18, 18, 4),
            Block.box(17, 15, 12, 19, 17, 13),
            Block.box(17, 12, 12, 19, 14, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(4, 3, 0, 12, 19, 20),
            Block.box(4, 3, -4, 12, 19, 0),
            Block.box(9, 0, 0, 11, 3, 1),
            Block.box(9, 0, 15, 11, 3, 16),
            Block.box(5, 0, 0, 7, 3, 1),
            Block.box(5, 0, 15, 7, 3, 16),
            Block.box(1, 4, -2, 4, 18, 18),
            Block.box(12, 15, -3, 13, 17, -1),
            Block.box(12, 12, -3, 13, 14, -1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(4, 3, -4, 12, 19, 16),
            Block.box(4, 3, 16, 12, 19, 20),
            Block.box(5, 0, 15, 7, 3, 16),
            Block.box(5, 0, 0, 7, 3, 1),
            Block.box(9, 0, 15, 11, 3, 16),
            Block.box(9, 0, 0, 11, 3, 1),
            Block.box(12, 4, -2, 15, 18, 18),
            Block.box(3, 15, 17, 4, 17, 19),
            Block.box(3, 12, 17, 4, 14, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty CHANNEL = IntegerProperty.create("channel",0,3);

    public ClassicTv(Properties p) {
        super(p.sound(SoundType.WOOD).strength(1F,10F));
        this.registerDefaultState(this.defaultBlockState().setValue(CHANNEL,0).setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = state.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return NORTH_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level lvl, BlockPos bp, Player player, InteractionHand hand, BlockHitResult bhr) {
        if(player.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.playSound(null,bp, ThingamajigsSoundEvents.STATIC.get(), SoundSource.BLOCKS,1F,1F);
                lvl.setBlock(bp, state.cycle(CHANNEL), 2);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.tv.desc"));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,CHANNEL);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(CHANNEL,0).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
