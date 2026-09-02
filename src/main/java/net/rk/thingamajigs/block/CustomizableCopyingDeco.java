package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.model.data.ModelProperty;
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getItemInHand(hand);
        if(level.isClientSide()){
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
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
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                        if(customDeco instanceof CustomizableCopyingDecoBE){
                            customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState();
                            customDeco.updateBlock();
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    /*@Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getItemInHand(hand);
        if(level.isClientSide()){
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        player.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM,0.7f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(handStack.is(Tags.Items.STONE)){
                    player.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,0.98f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(Tags.Items.GLASS_PANES)){
                    player.playSound(SoundEvents.SPYGLASS_USE,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,0.98f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(Tags.Items.GLASS_TINTED)){
                    player.playSound(SoundEvents.GLASS_BREAK,0.5f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.91f,1.0f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(ItemTags.AXES)){
                    player.playSound(SoundEvents.HANGING_SIGN_HIT,0.4f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.93f,0.97f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(Tags.Items.SHEARS)){
                    player.playSound(SoundEvents.HANGING_SIGN_HIT,0.4f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(ItemTags.SHOVELS)){
                    player.playSound(SoundEvents.HANGING_SIGN_HIT,0.4f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.93f,0.97f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(ItemTags.HOES)){
                    player.playSound(SoundEvents.HANGING_SIGN_HIT,0.4f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(ItemTags.SWORDS)){
                    player.playSound(SoundEvents.HANGING_SIGN_HIT,0.4f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.93f,0.97f));
                    return InteractionResult.SUCCESS;
                }
                else if(handStack.is(ItemTags.DECORATED_POT_SHERDS)){
                    player.playSound(SoundEvents.HANGING_SIGN_HIT,0.4f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                    return InteractionResult.SUCCESS;
                }
            }
        }
        else{
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                        if(customDeco instanceof CustomizableCopyingDecoBE){
                            customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState();
                            customDeco.updateBlock();
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
                else if(handStack.is(Tags.Items.STONE)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.renderingMode = "solid";
                        customDeco.updateBlock();
                    }
                }
                else if(handStack.is(Tags.Items.GLASS_PANES)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.renderingMode = "cutout";
                        customDeco.updateBlock();
                    }
                }
                else if(handStack.is(Tags.Items.GLASS_TINTED)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.renderingMode = "translucent";
                        customDeco.updateBlock();
                    }
                }
                // rot y dec
                else if(handStack.is(ItemTags.AXES)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double xRot = customDeco.modelRotations.x;
                        double yRot = customDeco.modelRotations.y;
                        double zRot = customDeco.modelRotations.z;

                        customDeco.modelRotations = new Vec3(xRot,yRot - 0.5f,zRot);
                        customDeco.updateBlock();
                    }
                    return InteractionResult.SUCCESS;
                } // rot y inc
                else if(handStack.is(Tags.Items.SHEARS)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double xRot = customDeco.modelRotations.x;
                        double yRot = customDeco.modelRotations.y;
                        double zRot = customDeco.modelRotations.z;

                        customDeco.modelRotations = new Vec3(xRot,yRot + 0.5f,zRot);
                        customDeco.updateBlock();
                    }
                    return InteractionResult.SUCCESS;
                }// decrease scale
                else if(handStack.is(ItemTags.SHOVELS)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double xScale = customDeco.modelScale.x;
                        double yScale = customDeco.modelScale.y;
                        double zScale = customDeco.modelScale.z;

                        customDeco.modelScale = new Vec3(Mth.clamp(xScale - 0.01f,0.01f,32.0f), Mth.clamp(yScale - 0.01f,0.01f,32.0f),Mth.clamp(zScale - 0.01f,0.01f,32.0f));
                        customDeco.updateBlock();
                    }
                    return InteractionResult.SUCCESS;
                }// increase scale
                else if(handStack.is(ItemTags.HOES)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double xScale = customDeco.modelScale.x;
                        double yScale = customDeco.modelScale.y;
                        double zScale = customDeco.modelScale.z;

                        customDeco.modelScale = new Vec3(Mth.clamp(xScale + 0.01f,0.01f,32.0f), Mth.clamp(yScale + 0.01f,0.01f,32.0f),Mth.clamp(zScale + 0.01f,0.01f,32.0f));
                        customDeco.updateBlock();
                    }
                    return InteractionResult.SUCCESS;
                }// reset offsets
                else if(handStack.is(ItemTags.SWORDS)){
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.modelOffsets = new Vec3(0D,0D,0D);
                        customDeco.updateBlock();
                    }
                    return InteractionResult.SUCCESS;
                }// offset x and z randomly and keep y unchanged
                else if(handStack.is(ItemTags.DECORATED_POT_SHERDS)){
                    double xOffset = Mth.randomBetween(level.getRandom(),-1.0f,1.0f);
                    double zOffset = Mth.randomBetween(level.getRandom(),-1.0f,1.0f);
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.modelOffsets = new Vec3(xOffset,customDeco.modelOffsets.y,zOffset);
                        customDeco.updateBlock();
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }*/

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
}
