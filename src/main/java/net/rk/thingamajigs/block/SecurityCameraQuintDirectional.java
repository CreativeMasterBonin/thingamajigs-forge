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

public class SecurityCameraQuintDirectional extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 5;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);

    public static final VoxelShape NORTH_BOX = Stream.of(
            Block.box(6, 13, 3, 10, 17, 13),
            Block.box(7, 14, 0, 9, 16, 3),
            Block.box(5, 6, 15, 11, 12, 16),
            Block.box(7, 9.5, 10.37868, 9, 13.5, 15.37868)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_BOX = Stream.of(
            Block.box(3, 13, 6, 13, 17, 10),
            Block.box(13, 14, 7, 16, 16, 9),
            Block.box(0, 6, 5, 1, 12, 11),
            Block.box(0.6213200000000008, 9.5, 7, 5.621320000000001, 13.5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_BOX = Stream.of(
            Block.box(6, 13, 3, 10, 17, 13),
            Block.box(7, 14, 13, 9, 16, 16),
            Block.box(5, 6, 0, 11, 12, 1),
            Block.box(7, 9.5, 0.6213200000000008, 9, 13.5, 5.621320000000001)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_BOX = Stream.of(
            Block.box(3, 13, 6, 13, 17, 10),
            Block.box(0, 14, 7, 3, 16, 9),
            Block.box(15, 6, 5, 16, 12, 11),
            Block.box(10.37868, 9.5, 7, 15.37868, 13.5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();



    public SecurityCameraQuintDirectional(Properties p) {
        super(p.strength(1F,32F).noOcclusion().sound(SoundType.LANTERN).noCollission());
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(ThingamajigsBlocks.BOX_SECURITY_CAMERA.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_BOX;}
                case SOUTH->{return SOUTH_BOX;}
                case EAST->{return EAST_BOX;}
                case WEST->{return WEST_BOX;}
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
        list.add(Component.literal("§eHas 5 angles, Shift right click on the block to change it"));
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
