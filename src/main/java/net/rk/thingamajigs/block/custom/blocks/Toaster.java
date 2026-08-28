package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecated,unused")
public class Toaster extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape NORTHSOUTH = Block.box(6, 0, 4, 10, 5, 12);
    public static final VoxelShape EASTWEST = Block.box(4, 0, 6, 12, 5, 10);

    public Toaster(Properties p) {
        super(p.noOcclusion().strength(1F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (state.getValue(FACING)){
            case NORTH,SOUTH ->{return NORTHSOUTH;}
            case EAST,WEST->{return EASTWEST;}
            default -> {return Shapes.block();}
        }
    }

    private int getPressDuration() {
        return 50;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(!state.getValue(POWERED)) {
                player.playSound(ThingamajigsSoundEvents.METALLIC_HIT.get(), 0.7f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.5f, 0.7f));
                return InteractionResult.SUCCESS;
            }
        }
        else{
            // recipe is fetched but this cannot be used here
            /*if(level instanceof ServerLevel serverLevel){
                ItemStack stackInHand = player.getItemInHand(hand);
                SimpleContainer container = new SimpleContainer(stackInHand);
                List<CampfireCookingRecipe> recipe = serverLevel.getRecipeManager().getRecipesFor(RecipeType.CAMPFIRE_COOKING,container,serverLevel)
                if(!recipe.isEmpty()){

                    return InteractionResult.SUCCESS;
                }
            }*/
            press(state,level,pos); // just update the state
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        if(state.getValue(POWERED)){
            serverLevel.setBlock(pos, state.setValue(POWERED, false),3);
            serverLevel.gameEvent(null,GameEvent.BLOCK_DEACTIVATE,pos);
            // make a noise when popping up
            serverLevel.playSound(null,pos,SoundEvents.LANTERN_STEP, SoundSource.BLOCKS,0.2f,
                    ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.42f,0.57f));
        }
    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, true), 3);
        pLevel.scheduleTick(pPos, this, this.getPressDuration());
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED,false);
    }
}
