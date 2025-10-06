package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.rk.thingamajigs.entity.MailboxBlockEntity;
import net.rk.thingamajigs.item.ThingamajigsItems;
import org.jetbrains.annotations.Nullable;

public class BasicMailbox extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");

    //shapes from mailbox blockentity block class
    public static final VoxelShape NORTHSOUTH_SHAPE = Shapes.join(Block.box(6, 0, 6, 10, 16, 10), Block.box(5, 13, 1, 11, 19, 15), BooleanOp.OR);
    public static final VoxelShape EASTWEST_SHAPE = Shapes.join(Block.box(6, 0, 6, 10, 16, 10), Block.box(1, 13, 5, 15, 19, 11), BooleanOp.OR);

    // Mailbox copies hopper menu, but does not have hopper functionality, instead, it acts like a 5 slotted chest, or barrel, or whatever
    // 'Abstract container'
    // See MailboxBlockEntity for specifics

    public BasicMailbox(Properties properties) {
        super(BlockBehaviour.Properties.copy(Blocks.CHEST).sound(SoundType.LANTERN).strength(2f, 32f).requiresCorrectToolForDrops().noOcclusion()
                .randomTicks().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(LOCKED,false));
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED,LOCKED);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;;
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag).setValue(LOCKED,false);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos,
                                  BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem()instanceof TieredItem tieredItem)
            return tieredItem.getTier().getLevel() >= 0;
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH_SHAPE;
            case EAST:
            case WEST:
                return EASTWEST_SHAPE;
            default: return Shapes.block();
        }
    }

    // BLOCK ENTITY METHODS AND SETUP

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof MailboxBlockEntity){
                Containers.dropContents(level,blockPos,(MailboxBlockEntity)blockEntity);
            }
        }
        super.onRemove(state,level,blockPos,newState,isMoving);
    }

    // Open entity container on block position
    // if provider is null or in an illegal state, throw error (which will probably crash the game)
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()){
            if(player.getItemInHand(hand).is(ThingamajigsItems.KEY.get())){
                level.setBlock(blockPos,blockState.cycle(LOCKED),3);
                if(blockState.getValue(LOCKED)){
                    level.playSound(null,blockPos,SoundEvents.ARMOR_EQUIP_CHAIN, SoundSource.BLOCKS,1.0F,1.0F);
                }
                else{
                    level.playSound(null,blockPos,SoundEvents.ARMOR_EQUIP_NETHERITE,SoundSource.BLOCKS,1.0F,1.0F);
                }
                // "a whoo hoo hoo hooo hooooooo!"
            }
            else{
                if(!blockState.getValue(LOCKED)){
                    BlockEntity blockEntity = level.getBlockEntity(blockPos);
                    if(blockEntity instanceof MailboxBlockEntity){
                        NetworkHooks.openScreen(((ServerPlayer)player), (MailboxBlockEntity)blockEntity,blockPos);
                    }
                    else{
                        throw new IllegalStateException("MailboxBlockEntity Container Provider is missing!");
                    }
                    // Sided success for server network packets
                }
                else{
                    level.playSound(null,blockPos, SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS,0.75F,1.0F);
                    player.displayClientMessage(Component.translatable("message.thingamajigs.generic.locked"),true);
                    return InteractionResult.PASS;
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bpe1, BlockState bse1) {
        return new MailboxBlockEntity(bpe1, bse1);
    }
}
