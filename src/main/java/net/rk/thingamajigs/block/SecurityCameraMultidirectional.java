package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

public class SecurityCameraMultidirectional extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 7;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);

    public static final VoxelShape NORTH_SECURE = Stream.of(
            Block.box(7, 7.5, 10.5, 9, 13.5, 15.5),
            Block.box(6, 6, 15, 10, 10, 16),
            Block.box(10, 14, 1, 11, 18, 12),
            Block.box(5, 14, 1, 6, 18, 12),
            Block.box(6, 14, 1, 10, 18, 1),
            Block.box(6, 14, 2, 10, 18, 12),
            Block.box(5, 18, -1, 11, 19, 12),
            Block.box(6, 13, 1, 10, 14, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SECURE = Stream.of(
            Block.box(0.5, 7.5, 7, 5.5, 13.5, 9),
            Block.box(0, 6, 6, 1, 10, 10),
            Block.box(4, 14, 10, 15, 18, 11),
            Block.box(4, 14, 5, 15, 18, 6),
            Block.box(15, 14, 6, 15, 18, 10),
            Block.box(4, 14, 6, 14, 18, 10),
            Block.box(4, 18, 5, 17, 19, 11),
            Block.box(4, 13, 6, 15, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SECURE = Stream.of(
            Block.box(7, 7.5, 0.5, 9, 13.5, 5.5),
            Block.box(6, 6, 0, 10, 10, 1),
            Block.box(5, 14, 4, 6, 18, 15),
            Block.box(10, 14, 4, 11, 18, 15),
            Block.box(6, 14, 15, 10, 18, 15),
            Block.box(6, 14, 4, 10, 18, 14),
            Block.box(5, 18, 4, 11, 19, 17),
            Block.box(6, 13, 4, 10, 14, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SECURE = Stream.of(
            Block.box(10.5, 7.5, 7, 15.5, 13.5, 9),
            Block.box(15, 6, 6, 16, 10, 10),
            Block.box(1, 14, 5, 12, 18, 6),
            Block.box(1, 14, 10, 12, 18, 11),
            Block.box(1, 14, 6, 1, 18, 10),
            Block.box(2, 14, 6, 12, 18, 10),
            Block.box(-1, 18, 5, 12, 19, 11),
            Block.box(1, 13, 6, 12, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_FILM = Stream.of(
            Block.box(7, 8, 12, 9, 10, 15),
            Block.box(5, 6, 15, 11, 12, 16),
            Block.box(7, 8, 3, 9, 10, 5),
            Block.box(6, 7, 5, 10, 17, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_FILM = Stream.of(
            Block.box(1, 8, 7, 4, 10, 9),
            Block.box(0, 6, 5, 1, 12, 11),
            Block.box(11, 8, 7, 13, 10, 9),
            Block.box(3, 7, 6, 11, 17, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_FILM = Stream.of(
            Block.box(7, 8, 1, 9, 10, 4),
            Block.box(5, 6, 0, 11, 12, 1),
            Block.box(7, 8, 11, 9, 10, 13),
            Block.box(6, 7, 3, 10, 17, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_FILM = Stream.of(
            Block.box(12, 8, 7, 15, 10, 9),
            Block.box(15, 6, 5, 16, 12, 11),
            Block.box(3, 8, 7, 5, 10, 9),
            Block.box(5, 7, 6, 13, 17, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();



    public SecurityCameraMultidirectional(Properties p) {
        super(p.strength(1F,32F).noOcclusion().sound(SoundType.LANTERN).noCollission());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(ThingamajigsBlocks.SECURE_SECURITY_CAMERA.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_SECURE;}
                case SOUTH->{return SOUTH_SECURE;}
                case EAST->{return EAST_SECURE;}
                case WEST->{return WEST_SECURE;}
                default -> {return Shapes.block();}
            }
        }
        else if(state.is(ThingamajigsBlocks.FILM_SECURITY_CAMERA.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_FILM;}
                case SOUTH->{return SOUTH_FILM;}
                case EAST->{return EAST_FILM;}
                case WEST->{return WEST_FILM;}
                default -> {return Shapes.block();}
            }
        }
        return Shapes.block();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit){
        if(!pLevel.isClientSide()){
            if(pHand == InteractionHand.MAIN_HAND && pPlayer.isShiftKeyDown()){
                int rotated_type = pState.getValue(TYPE);
                double d0 = (double)pPos.getX() + 0.5D;
                double d1 = (double)pPos.getY() + 0.5D;
                double d2 = (double)pPos.getZ() + 0.5D;

                rotated_type++;
                pLevel.setBlock(pPos, pState.setValue(TYPE, rotated_type), 0);

                if(rotated_type >= MAX_TYPES){
                    rotated_type = 0;
                    pLevel.setBlock(pPos, pState.setValue(TYPE, rotated_type), 0);
                }

                // play noise to alert player of change in state
                playSound(pLevel, d0, d1, d2);
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    // Old Thingamajigs sound method condensed for when using vanilla Minecraft sounds
    public static void playSound(LevelAccessor world, double x, double y, double z) {
        if(world instanceof Level lvl) {
            if(!lvl.isClientSide()) {
                lvl.playSound(null, x, y, z, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS,1,1);
            }
            else {
                lvl.playLocalSound(x, y, z, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 1, 1, false);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("§eHas 7 angles, Shift right click on the block to change it"));
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
