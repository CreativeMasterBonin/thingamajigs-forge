package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"deprecated","unused"})
public class SpringBlock extends Block implements SimpleWaterloggedBlock {
    public static final VoxelShape UNSPRUNG_SHAPE = Block.box(0,0,0,16,7,16);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty SPRUNG = BooleanProperty.create("sprung");
    public static IntegerProperty USES = IntegerProperty.create("uses",0,4);
    public static BooleanProperty REINFORCED = BooleanProperty.create("reinforced");

    public SpringBlock(Properties p) {
        super(p.strength(1f,2f)
                .mapColor(MapColor.COLOR_GRAY).noOcclusion().sound(SoundType.LANTERN));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false)
                .setValue(SPRUNG,false).setValue(USES,0)
                .setValue(REINFORCED,false));
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand ih, BlockHitResult bhr) {
        ItemStack itemStack = ply.getItemInHand(ih);
        if(itemStack.is(Items.IRON_INGOT)){
            itemStack.shrink(1);
            lvl.setBlock(bp,bs.setValue(REINFORCED,true),3);
            lvl.playSound(null,bp, SoundEvents.ARMOR_EQUIP_NETHERITE, SoundSource.BLOCKS,1.0f,0.7f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity ent) {
        double yUpdoub = 1.53D;

        double x2 = ent.getX() * 0.25;
        double z2 = ent.getZ() * 0.25;

        if(!bs.getValue(SPRUNG) && ent.onGround()){
            if(bs.getValue(USES) < 4){
                ent.setDeltaMovement(0,yUpdoub,0);
                if(!bs.getValue(REINFORCED)){
                    lvl.playSound(null,bp, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS,1.0f,1.0f);
                    lvl.setBlock(bp,bs
                            .setValue(SPRUNG,true)
                            .setValue(USES,bs.getValue(USES) + 1)
                            .setValue(REINFORCED,bs.getValue(REINFORCED))
                            .setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
                    return;
                }
                else{
                    lvl.playSound(null,bp, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS,1.0f,0.8f);
                    lvl.setBlock(bp,bs
                            .setValue(SPRUNG,true)
                            .setValue(USES,0)
                            .setValue(REINFORCED,bs.getValue(REINFORCED))
                            .setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
                    return;
                }
            }
            else{
                if(!bs.getValue(REINFORCED)){
                    lvl.destroyBlock(bp,false);
                }
            }
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(state.getValue(REINFORCED)){
            if(!player.getInventory().add(new ItemStack(Items.IRON_INGOT,1))){
                player.drop(new ItemStack(Items.IRON_INGOT,1), false);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(SPRUNG);
            if(flag && pLevel.hasNeighborSignal(pPos)){
                pLevel.setBlock(pPos,pState.setValue(SPRUNG,false),3);
                pLevel.playSound(null,pPos,
                        SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS,1.0f,0.7f);
            }
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter pLevel, BlockPos bp, CollisionContext pContext) {
        if(!bs.getValue(SPRUNG)){
            return UNSPRUNG_SHAPE;
        }
        return Shapes.block();
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,SPRUNG,USES,REINFORCED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(SPRUNG,false)
                .setValue(USES,0)
                .setValue(REINFORCED,false)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
