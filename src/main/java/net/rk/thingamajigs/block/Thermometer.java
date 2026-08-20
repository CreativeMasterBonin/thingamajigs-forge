package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Thermometer extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    public static final VoxelShape EAST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    public static final VoxelShape WEST_SHAPE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static BooleanProperty ACTIVE = BlockStateProperties.ENABLED;

    public Thermometer(Properties properties) {
        super(properties.strength(0.87F,1F).sound(SoundType.COPPER).noCollission().randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVE, false).setValue(WATERLOGGED, false));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter lvl, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.thingamajigs.thermometer.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(level.isInWorldBounds(pos) && state.getBlock() instanceof Thermometer){
            if(level.getBiome(pos).is(Tags.Biomes.IS_COLD) || level.getBiome(pos).is(Tags.Biomes.IS_SNOWY)){
                if(!state.getValue(ACTIVE)){
                    level.setBlock(pos,state.setValue(ACTIVE,true),3);
                }
            }
            else{
                if(state.getValue(ACTIVE)){
                    level.setBlock(pos,state.setValue(ACTIVE,false),3);
                }
            }
        }
    }



    // to save time, and of course, because this instance of a block will be attached to the wall visually, we use a wall block model
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return NORTH_SHAPE;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,ACTIVE,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(ACTIVE,false).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
