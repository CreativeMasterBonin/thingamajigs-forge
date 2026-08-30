package net.rk.thingamajigs.block;

import io.netty.buffer.Unpooled;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.screen.PhoneMenu;

import java.util.stream.Stream;

public class CordlessPhoneBase extends ThingamajigsDecorativeBlock {
    public static final VoxelShape BASE_SHAPE = Block.box(0,0,0, 16,3.25,16);

    public static final VoxelShape NORTH_FEATURED = Stream.of(
            Block.box(2, 0, 0, 14, 2, 16),
            Block.box(9, 2, 2, 13, 4, 14),
            Block.box(9, 3, 14, 10, 4, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_FEATURED = Stream.of(
            Block.box(0, 0, 2, 16, 2, 14),
            Block.box(2, 2, 9, 14, 4, 13),
            Block.box(0, 3, 9, 2, 4, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_FEATURED = Stream.of(
            Block.box(2, 0, 0, 14, 2, 16),
            Block.box(3, 2, 2, 7, 4, 14),
            Block.box(6, 3, 0, 7, 4, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_FEATURED = Stream.of(
            Block.box(0, 0, 2, 16, 2, 14),
            Block.box(2, 2, 3, 14, 4, 7),
            Block.box(14, 3, 6, 16, 4, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CordlessPhoneBase(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(ThingamajigsBlocks.FEATURED_CORDLESS_PHONE.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_FEATURED;}
                case SOUTH->{return SOUTH_FEATURED;}
                case EAST->{return EAST_FEATURED;}
                case WEST->{return WEST_FEATURED;}
                default -> {return BASE_SHAPE;}
            }
        }
        return BASE_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).isEmpty()){
                player.playSound(ThingamajigsSoundEvents.MOBILE_BEEP.get(),0.5f,0.57f);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.getItemInHand(hand).isEmpty()){
                if(player instanceof ServerPlayer){
                    NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.translatable("title.thingamajigs.phone")
                                    .withStyle(ChatFormatting.WHITE);
                        }
                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                            return new PhoneMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                        }
                    }, pos);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
