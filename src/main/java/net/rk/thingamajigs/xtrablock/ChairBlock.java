package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.entity.ChairEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class ChairBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final VoxelShape NORTH = Stream.of(
            Block.box(12, 0, 2, 14, 8, 4),
            Block.box(2, 8, 2, 14, 10, 14),
            Block.box(2, 10, 12, 14, 22, 14),
            Block.box(2, 0, 2, 4, 8, 4),
            Block.box(2, 0, 12, 4, 8, 14),
            Block.box(12, 0, 12, 14, 8, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(12, 0, 12, 14, 8, 14),
            Block.box(2, 8, 2, 14, 10, 14),
            Block.box(2, 10, 2, 4, 22, 14),
            Block.box(12, 0, 2, 14, 8, 4),
            Block.box(2, 0, 2, 4, 8, 4),
            Block.box(2, 0, 12, 4, 8, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(2, 0, 12, 4, 8, 14),
            Block.box(2, 8, 2, 14, 10, 14),
            Block.box(2, 10, 2, 14, 22, 4),
            Block.box(12, 0, 12, 14, 8, 14),
            Block.box(12, 0, 2, 14, 8, 4),
            Block.box(2, 0, 2, 4, 8, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(2, 0, 2, 4, 8, 4),
            Block.box(2, 8, 2, 14, 10, 14),
            Block.box(12, 10, 2, 14, 22, 14),
            Block.box(2, 0, 12, 4, 8, 14),
            Block.box(12, 0, 12, 14, 8, 14),
            Block.box(12, 0, 2, 14, 8, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ChairBlock(Properties p) {
        super(p.noOcclusion().pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    public SoundEvent SIT_SOUND = SoundEvents.WOOL_PLACE;

    public ChairBlock(Properties p, SoundEvent sv) {
        super(p.noOcclusion().pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        SIT_SOUND = sv;
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case EAST: return EAST;
            case WEST: return WEST;
            default: return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        FluidState fluidState = bpc.getLevel().getFluidState(bpc.getClickedPos());
        return this.defaultBlockState().setValue(FACING, bpc.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    // be use
    @Override
    public InteractionResult use(BlockState bs, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!level.isClientSide){

                List<ChairEntity> chairs = level.getEntitiesOfClass(ChairEntity.class, aabb);
                // we WANT a chair
                if(chairs.isEmpty()){
                    ChairEntity ce = ChairEntity.requestNewEntity(level,pos,bs);
                    level.addFreshEntity(ce);
                    player.startRiding(ce,false);
                }
            }

            playCustomSitSound(level,pos,player); // custom sitting sounds!
        }
        catch(Exception e){
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SIT_SOUND;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
