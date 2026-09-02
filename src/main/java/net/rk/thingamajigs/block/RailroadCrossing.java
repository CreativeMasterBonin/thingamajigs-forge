package net.rk.thingamajigs.block;

import io.netty.buffer.Unpooled;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;
import net.rk.thingamajigs.entity.customblock.RailroadCrossingBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import net.rk.thingamajigs.screen.RailroadCrossingArmMenu;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class RailroadCrossing extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty ON = BooleanProperty.create("on");

    public static final VoxelShape ALL = Stream.of(
            Block.box(4, 0, 4, 12, 2, 12),
            Block.box(6, 2, 6, 10, 10, 10),
            Block.box(7, 10, 7, 9, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public RailroadCrossing(Properties p){
        super(p.noOcclusion().sound(SoundType.LANTERN));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(ON, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return ALL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> bs) {
        super.createBlockStateDefinition(bs.add(FACING,ON));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(ON,false);
    }

    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean bl1) {
        if (!lvl.isClientSide) {
            boolean flag = bs.getValue(ON);
            if (flag != lvl.hasNeighborSignal(bp)) {
                if (flag) {
                    lvl.scheduleTick(bp, this, 4);
                } else {
                    lvl.setBlock(bp, bs.cycle(ON), 2);
                }
            }
        }
    }

    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp2, RandomSource rnds) {
        if (bs.getValue(ON) && !slvl.hasNeighborSignal(bp2)) {
            slvl.setBlock(bp2, bs.cycle(ON), 2);
        }
    }

    // new

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.rr_arm.desc"));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RailroadCrossingBE(pPos,pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lvl, BlockState bs, BlockEntityType<T> bet) {
        return createTickerHelper(bet, ThingamajigsBlockEntities.RAILROAD_CROSSING_ARM_BE.get(),
                lvl.isClientSide ? RailroadCrossingBE::clientTick : RailroadCrossingBE::serverTick);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    // using
    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand h, BlockHitResult bhr) {
        boolean isItemUsable = ply.getItemInHand(h).is(Tags.Items.GEMS) || ply.getItemInHand(h).is(Tags.Items.DUSTS) || ply.getItemInHand(h).is(ItemTags.AXES) || ply.getItemInHand(h).is(Tags.Items.SHEARS) || ply.getItemInHand(h).is(Tags.Items.DYES) || ply.getItemInHand(h).is(Tags.Items.BONES) || ply.getItemInHand(h).is(Items.RABBIT_FOOT) || ply.getItemInHand(h).is(Items.RABBIT_HIDE);

        if(lvl.isClientSide()){
            if(ply.getItemInHand(h).isEmpty()){
                ply.playSound(SoundEvents.HANGING_SIGN_HIT,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.1f));
            }
            else if(ply.getItemInHand(h).is(ItemTags.AXES) || ply.getItemInHand(h).is(Tags.Items.SHEARS)){
                ply.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.98f,1.0f));
            }
            else if(ply.getItemInHand(h).is(Tags.Items.GEMS) || ply.getItemInHand(h).is(Tags.Items.DUSTS)){
                ply.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.91f,0.97f));
            }
            return InteractionResult.SUCCESS;
        }
        else {
            if (ply.getItemInHand(h).isEmpty() || !isItemUsable) {
                try {
                    MenuProvider mp = new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.translatable("container.thingamajigs.railroad_crossing_arm.title")
                                    .withStyle(ChatFormatting.WHITE);
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                            return new RailroadCrossingArmMenu(id, inventory,
                                    new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(bp));
                        }
                    };

                    if (ply instanceof ServerPlayer player) {
                        NetworkHooks.openScreen(player, mp, bp);
                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.PASS;
                    }
                } catch (Exception e) {
                    Logger.getAnonymousLogger().warning("Railroad Crossing encountered an exception: " + e.getMessage());
                    return InteractionResult.FAIL;
                }
            }
            else{
                if(ply.getItemInHand(h).is(ItemTags.AXES) || ply.getItemInHand(h).is(Tags.Items.SHEARS)) {
                    RailroadCrossingBE arm = (RailroadCrossingBE)lvl.getBlockEntity(bp);
                    if(arm instanceof RailroadCrossingBE){
                        arm.yAngle += 5.0f;
                        arm.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(ply.getItemInHand(h).is(Tags.Items.GEMS) || ply.getItemInHand(h).is(Tags.Items.DUSTS)){
                    RailroadCrossingBE arm = (RailroadCrossingBE)lvl.getBlockEntity(bp);
                    if(arm instanceof RailroadCrossingBE){
                        arm.yAngle -= 5.0f;
                        arm.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(ply.getItemInHand(h).is(Tags.Items.DYES)){
                    RailroadCrossingBE arm = (RailroadCrossingBE)lvl.getBlockEntity(bp);
                    if(arm instanceof RailroadCrossingBE){
                        arm.armLength -= 0.5f;
                        arm.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(ply.getItemInHand(h).is(Tags.Items.BONES)){
                    RailroadCrossingBE arm = (RailroadCrossingBE)lvl.getBlockEntity(bp);
                    if(arm instanceof RailroadCrossingBE){
                        arm.armLength += 0.5f;
                        arm.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(ply.getItemInHand(h).is(Items.RABBIT_HIDE)){
                    RailroadCrossingBE arm = (RailroadCrossingBE)lvl.getBlockEntity(bp);
                    if(arm instanceof RailroadCrossingBE){
                        arm.armGateOffsetZ -= 0.5f;
                        arm.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(ply.getItemInHand(h).is(Items.RABBIT_FOOT)){
                    RailroadCrossingBE arm = (RailroadCrossingBE)lvl.getBlockEntity(bp);
                    if(arm instanceof RailroadCrossingBE){
                        arm.armGateOffsetZ += 0.5f;
                        arm.updateBlock();
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }
}
