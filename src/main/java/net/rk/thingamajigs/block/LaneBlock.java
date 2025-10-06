package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LaneBlock extends Block {
    private static final VoxelShape LANE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public LaneBlock(Properties p) {
        super(p.strength(1f,20f).sound(SoundType.WOOD)
                .friction(0.8F)
                .speedFactor(1.1F));
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable BlockGetter bg, List<Component> lc, TooltipFlag tf) {
        lc.add(Component.translatable("block.thingamajigs.lane_block.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.block();
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return Shapes.block();
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return Shapes.block();
    }

    @SuppressWarnings("deprecated")
    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 75;
    }

    // first go stepOn to start moving object
    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity e) {
        if(e instanceof ItemEntity){
            double x = 0.0D;
            double y = 0.01D;
            double z = 0.0D;

            switch(bs.getValue(FACING)){
                case NORTH -> z = 0.5D;
                case SOUTH -> z = -0.5D;
                case EAST -> x = -0.5D;
                case WEST -> x = 0.5D;
            }

            e.setDeltaMovement(x,y,z);
        }
    }

    // continuous motion when falling on this block (repeated in stepOn function)
    @Override
    public void fallOn(Level lvl, BlockState bs, BlockPos bp, Entity e, float f1) {
        if(e instanceof ItemEntity){
            double x = 0.0D;
            double y = e.getDeltaMovement().y;
            double z = 0.0D;

            switch(bs.getValue(FACING)){
                case NORTH -> z = 0.5D;
                case SOUTH -> z = -0.5D;
                case EAST -> x = -0.5D;
                case WEST -> x = 0.5D;
            }

            e.setDeltaMovement(x,y,z);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
