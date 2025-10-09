package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class ThrowSphereIntoRingMachine extends ThingamajigsDecorativeBlock {
    public static EnumProperty<MachineEnding> MACHINE_ENDING = EnumProperty.create("machine_ending",MachineEnding.class);

    public enum MachineEnding implements StringRepresentable {
        PAYMENT_SECTION("payment_section"),
        GOAL_SECTION("goal_section");

        private String endName;

        MachineEnding(String name){
            this.endName = name;
        }

        @Override
        public String toString() {
            return this.endName;
        }

        @Override
        public String getSerializedName() {
            return this.endName;
        }
    }

    public static final VoxelShape NORTH_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 14, 4, 7, 16),
            Block.box(12, 0, 14, 16, 7, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(0, 12, 17, 16, 13, 26),
            Block.box(4, 24, 13, 12, 29, 14),
            Block.box(2, 11, 11, 14, 12.5, 14),
            Block.box(2, 6, 4, 14, 7, 13),
            Block.box(0, 13, 13, 16, 26, 28)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 0, 2, 7, 4),
            Block.box(0, 0, 12, 2, 7, 16),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(-10, 12, 0, -1, 13, 16),
            Block.box(2, 24, 4, 3, 29, 12),
            Block.box(2, 11, 2, 5, 12.5, 14),
            Block.box(3, 6, 2, 12, 7, 14),
            Block.box(-12, 13, 0, 3, 26, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(12, 0, 0, 16, 7, 2),
            Block.box(0, 0, 0, 4, 7, 2),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 12, -10, 16, 13, -1),
            Block.box(4, 24, 2, 12, 29, 3),
            Block.box(2, 11, 2, 14, 12.5, 5),
            Block.box(2, 6, 3, 14, 7, 12),
            Block.box(0, 13, -12, 16, 26, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(14, 0, 12, 16, 7, 16),
            Block.box(14, 0, 0, 16, 7, 4),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(17, 12, 0, 26, 13, 16),
            Block.box(13, 24, 4, 14, 29, 12),
            Block.box(11, 11, 2, 14, 12.5, 14),
            Block.box(4, 6, 2, 13, 7, 14),
            Block.box(13, 13, 0, 28, 26, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 0, 4, 7, 2),
            Block.box(12, 0, 0, 16, 7, 2),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(14, 11, 0, 16, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(14, 0, 0, 16, 7, 4),
            Block.box(14, 0, 12, 16, 7, 16),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(0, 11, 14, 16, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(12, 0, 14, 16, 7, 16),
            Block.box(0, 0, 14, 4, 7, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 11, 0, 2, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 12, 2, 7, 16),
            Block.box(0, 0, 0, 2, 7, 4),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(0, 11, 0, 16, 13, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ThrowSphereIntoRingMachine(Properties properties) {
        super(properties.strength(2f,10f).noOcclusion().sound(SoundType.LANTERN).pushReaction(PushReaction.BLOCK));
        this.registerDefaultState(this.defaultBlockState().setValue(MACHINE_ENDING,MachineEnding.PAYMENT_SECTION)
                .setValue(WATERLOGGED,false).setValue(FACING,Direction.NORTH));
    }

    public static Direction getConnected(BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        return blockState.getValue(MACHINE_ENDING) == MachineEnding.PAYMENT_SECTION ? direction.getOpposite() : direction;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context){
        if(blockState.getValue(MACHINE_ENDING) != MachineEnding.PAYMENT_SECTION){
            switch(getConnected(blockState).getOpposite()){
                case NORTH -> {
                    return SOUTH_BACK;
                }
                case SOUTH -> {
                    return NORTH_BACK;
                }
                case WEST -> {
                    return EAST_BACK;
                }
                case EAST -> {
                    return WEST_BACK;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
        else{
            switch(getConnected(blockState).getOpposite()){
                case NORTH -> {
                    return NORTH_FRONT;
                }
                case SOUTH -> {
                    return SOUTH_FRONT;
                }
                case WEST -> {
                    return WEST_FRONT;
                }
                case EAST -> {
                    return EAST_FRONT;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder params){
        if(blockState.getValue(MACHINE_ENDING) == MachineEnding.PAYMENT_SECTION){
            return super.getDrops(blockState, params);
        }
        else{
            List<ItemStack> emptyItems = List.of(ItemStack.EMPTY); // hardcoded to stop extra pieces from dropping anything
            return emptyItems;
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockState state = blockState;
        super.playerWillDestroy(level, blockPos, blockState, player);
        if(blockState.getValue(MACHINE_ENDING) == MachineEnding.GOAL_SECTION){
            return;
        }
        switch(state.getValue(FACING)){
            case NORTH -> {
                level.removeBlock(blockPos.south(),false);
                break;
            }
            case SOUTH -> {
                level.removeBlock(blockPos.north(),false);
                break;
            }
            case EAST -> {
                level.removeBlock(blockPos.west(),false);
                break;
            }
            case WEST -> {
                level.removeBlock(blockPos.east(),false);
                break;
            }
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity entity, ItemStack itemStack) {
        super.setPlacedBy(level,blockPos,blockState,entity,itemStack);
        if(!level.isClientSide){
            BlockPos bp = blockPos.relative(blockState.getValue(FACING));
            switch (blockState.getValue(FACING)){
                case NORTH -> {
                    level.setBlock(bp.south().south(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
                case SOUTH -> {
                    level.setBlock(bp.north().north(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
                case EAST -> {
                    level.setBlock(bp.west().west(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
                case WEST -> {
                    level.setBlock(bp.east().east(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
            }
            level.blockUpdated(blockPos,Blocks.AIR);
            blockState.updateNeighbourShapes(level,blockPos,3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(MACHINE_ENDING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection(); // reverse
        BlockPos blockPos = context.getClickedPos();
        BlockPos blockPos1 = blockPos.relative(direction);
        Level level = context.getLevel();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        return level.getBlockState(blockPos1).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(blockPos1) ?
                this.defaultBlockState().setValue(FACING, direction.getOpposite()) : null;
    }
}
