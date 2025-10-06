package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LavaLamp extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 18, 12);
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 5;
    // red = 0, orange = 1, yellow = 2, green = 3, blue = 4, purple = 5
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);

    public LavaLamp(Properties properties) {
        super(properties.strength(1F, 5F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE,0).setValue(WATERLOGGED, false));
    }

    @Override
    public void animateTick(BlockState p_220893_, Level level, BlockPos p_220895_, RandomSource rnd) {
        double d0 = p_220895_.getX();
        double d1 = p_220895_.getY();
        double d2 = p_220895_.getZ();
        if (rnd.nextInt(200) == 0) {
            level.playLocalSound(d0, d1, d2, SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.2F + rnd.nextFloat() * 0.2F, 0.9F + rnd.nextFloat() * 0.15F, false);
        }
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }



    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        boolean logYes = state.getValue(WATERLOGGED);
        return logYes;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(TYPE,0).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    // Borrow this method's code if you need some tips on custom dya-ble blocks like this Lava Lamp!
    // It's so cool when it works! For items you need to reverse this logic quite a bit before it will work...

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack itemstack = player.getItemInHand(hand); // off-hand, main-hand, back-hand, and red-handed support!
        boolean successfullyDyed = false; // a simple boolean to control whether we hear a sound and update the block or not

        // MUST BEE SERVER-SIDE ONLY to work! at least for blocks
        if (!level.isClientSide) {
            if(player.getAbilities().mayBuild == true){
                if(itemstack.getItem() == Items.RED_DYE){
                    level.setBlock(blockPos,blockState.setValue(TYPE,0),0);
                    successfullyDyed = true;
                }
                else if(itemstack.getItem() == Items.ORANGE_DYE){
                    level.setBlock(blockPos,blockState.setValue(TYPE,1),0);
                    successfullyDyed = true;
                }
                else if(itemstack.getItem() == Items.YELLOW_DYE){
                    level.setBlock(blockPos,blockState.setValue(TYPE,2),0);
                    successfullyDyed = true;
                }
                else if(itemstack.getItem() == Items.GREEN_DYE){
                    level.setBlock(blockPos,blockState.setValue(TYPE,3),0);
                    successfullyDyed = true;
                }
                else if(itemstack.getItem() == Items.BLUE_DYE){
                    level.setBlock(blockPos,blockState.setValue(TYPE,4),0);
                    successfullyDyed = true;
                }
                else if(itemstack.getItem() == Items.PURPLE_DYE){
                    level.setBlock(blockPos,blockState.setValue(TYPE,5),0);
                    successfullyDyed = true;
                }
                else{
                    // does the player actually have an item in their hand?
                    if(player.getItemInHand(hand) == ItemStack.EMPTY){
                        return InteractionResult.PASS; // we don't want to swing the arm or interact with this block
                    }
                    else{
                        // if there is an item, then since we know it's not the proper dye or isn't dye, display a hint message
                        player.displayClientMessage(Component.translatable("block.thingamajigs.lava_lamp.wrong_dye"), true);
                        return InteractionResult.CONSUME;
                    }
                }
                // update THIS block, no matter what happens
                if(successfullyDyed == true){
                    level.updateNeighborsAt(blockPos,this);
                    level.playSound(null, blockPos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}