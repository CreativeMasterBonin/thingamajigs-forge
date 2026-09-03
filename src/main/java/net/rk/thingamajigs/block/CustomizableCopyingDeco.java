package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.Tags;
import net.rk.thingamajigs.entity.customblock.CustomizableCopyingDecoBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomizableCopyingDeco extends BaseEntityBlock {
    public static final ModelProperty<BlockState> BLOCKSTATE_ID = new ModelProperty<>();

    public CustomizableCopyingDeco(Properties p) {
        super(p.noOcclusion().noCollission().pushReaction(PushReaction.BLOCK).instrument(NoteBlockInstrument.BIT));
    }

    @Override
    public void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
        if(customDeco instanceof CustomizableCopyingDecoBE){
            // custom particles from the state used in the render instead of this block's actual particles
            if(!customDeco.blockTypeToCopy.isAir()){
                level.levelEvent(player,2001,pos,getId(customDeco.blockTypeToCopy));
                return;
            }
            else{
                level.levelEvent(player,2001,pos,getId(state));
                return;
            }
        }
        super.spawnDestroyParticles(level,player,pos,state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack oppositeStack = player.getItemInHand(InteractionHand.OFF_HAND);
        if(level.isClientSide()){
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
                    // air, liquid or entity blocks are not allowed, as they may render things outside of block models (especially air and liquid, which have no model and a special renderer, respectively)
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        player.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM,0.7f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        else{
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
                    // air, liquid or entity blocks are not allowed, as they may render things outside of block models (especially air and liquid, which have no model and a special renderer, respectively)
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                        if(customDeco instanceof CustomizableCopyingDecoBE){
                            if(oppositeStack.isEmpty()){
                                customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState();
                            }
                            else{
                                if(blockItem.getBlock().getStateDefinition().getProperties().contains(BlockStateProperties.WATERLOGGED) && oppositeStack.is(Items.WATER_BUCKET)){
                                    customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,true);
                                }
                                else{
                                    customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState();
                                }
                            }
                            customDeco.updateBlock();
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        if(level.getBlockEntity(pos) instanceof CustomizableCopyingDecoBE customDeco){
            return customDeco.blockTypeToCopy.getBlock() instanceof HalfTransparentBlock || customDeco.blockTypeToCopy.getBlock() instanceof LeavesBlock;
        }
        return false;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(Component.translatable("block.thingamajigs.customizable_copying_deco.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CustomizableCopyingDecoBE(blockPos,blockState);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if(entity instanceof Player player){
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE) level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                float rotationToTurn = player.getDirection().getOpposite().toYRot();
                if(rotationToTurn == 180.0f){
                    rotationToTurn = 0.0f;
                }
                else if(rotationToTurn == 0.0f){
                    rotationToTurn = 180.0f;
                }
                customDeco.modelRotations = new Vec3(0D,rotationToTurn,0D);
                customDeco.updateBlock();
            }
        }
    }
}
