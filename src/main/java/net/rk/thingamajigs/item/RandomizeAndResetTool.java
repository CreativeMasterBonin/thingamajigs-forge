package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.entity.customblock.CustomizableCopyingDecoBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RandomizeAndResetTool extends Item {
    public static final String ROTATE_WHEN_RESETTING_TAG_NAME = "rotate_when_resetting";

    public RandomizeAndResetTool(Properties p) {
        super(p.setNoRepair().fireResistant().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.thingamajigs.randomize_and_reset_tool.desc")
                .withStyle(ChatFormatting.GRAY));
        if(stack.hasTag()) {
            if(stack.getTag().contains(ROTATE_WHEN_RESETTING_TAG_NAME)){
                list.add(Component.translatable("item.thingamajigs.randomize_and_reset_tool.rotate_when_resetting",stack.getTag().getBoolean(ROTATE_WHEN_RESETTING_TAG_NAME))
                        .withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        CompoundTag tag = handStack.getOrCreateTag();

        if(!player.isSecondaryUseActive()){
            if(level.isClientSide()){
                if(handStack.hasTag()){
                    if(handStack.getTag().contains(ROTATE_WHEN_RESETTING_TAG_NAME)){
                        if(handStack.getTag().getBoolean(ROTATE_WHEN_RESETTING_TAG_NAME)){
                            player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.3f,1.0f);
                            return InteractionResultHolder.success(handStack);
                        }
                        else{
                            player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.3f,0.7f);
                            return InteractionResultHolder.success(handStack);
                        }
                    }
                }
                else{
                    player.playSound(SoundEvents.UI_LOOM_SELECT_PATTERN,0.4f,ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.0f));
                    return InteractionResultHolder.success(handStack);
                }
            }
            else{
                // if the ItemStack has our tag with the boolean we need, continue
                if(handStack.hasTag()){
                    if(tag.contains(ROTATE_WHEN_RESETTING_TAG_NAME)){
                        tag.putBoolean(ROTATE_WHEN_RESETTING_TAG_NAME,!tag.getBoolean(ROTATE_WHEN_RESETTING_TAG_NAME));
                        return InteractionResultHolder.success(handStack);
                    }
                }
                else{// no tag, no problem; just add the tag with the required default value
                    tag.putBoolean(ROTATE_WHEN_RESETTING_TAG_NAME,false);
                    handStack.setTag(tag);
                    return InteractionResultHolder.success(handStack);
                }
            }
        }
        return InteractionResultHolder.pass(handStack);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        Player player = ctx.getPlayer();
        BlockPos clickedPos = ctx.getClickedPos();
        ItemStack handStack = ctx.getItemInHand();
        if(level.getBlockState(clickedPos).isAir()){
            return InteractionResult.PASS;
        }

        if(level.isClientSide()){
            if(player.isSecondaryUseActive()){
                CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
                if(customDeco instanceof CustomizableCopyingDecoBE){
                    boolean allOffsetZero = customDeco.modelOffsets.x == 0.0D && customDeco.modelOffsets.y == 0.0D && customDeco.modelOffsets.z == 0.0D;
                    boolean allRotationZero = customDeco.modelRotations.x == 0.0D && customDeco.modelRotations.y == 0.0D && customDeco.modelRotations.z == 0.0D;
                    boolean allScaleZero = customDeco.modelScale.x == 0.0D && customDeco.modelScale.y == 0.0D && customDeco.modelScale.z == 0.0D;

                    if(!allOffsetZero && !allRotationZero && !allScaleZero){
                        player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL,0.4f,ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                        return InteractionResult.SUCCESS;
                    }
                }
            }
            else{
                player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.4f,ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.isSecondaryUseActive()){
                CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
                if(customDeco instanceof CustomizableCopyingDecoBE){
                    boolean allOffsetZero = customDeco.modelOffsets.x == 0.0D && customDeco.modelOffsets.y == 0.0D && customDeco.modelOffsets.z == 0.0D;
                    boolean allRotationZero = customDeco.modelRotations.x == 0.0D && customDeco.modelRotations.y == 0.0D && customDeco.modelRotations.z == 0.0D;
                    boolean allScaleZero = customDeco.modelScale.x == 0.0D && customDeco.modelScale.y == 0.0D && customDeco.modelScale.z == 0.0D;

                    if(!allOffsetZero && !allRotationZero && !allScaleZero){
                        // reset values to their defaults
                        customDeco.modelOffsets = new Vec3(0D,0D,0D);
                        customDeco.modelScale = new Vec3(1D,1D,1D); // must be 1 for all or the model won't be visible
                        customDeco.modelRotations = new Vec3(0D,0D,0D);

                        // rotate towards player if item has been set to do so
                        if(handStack.hasTag()){
                            if(handStack.getTag().contains(ROTATE_WHEN_RESETTING_TAG_NAME)){
                                if(handStack.getTag().getBoolean(ROTATE_WHEN_RESETTING_TAG_NAME)){
                                    float rotationToTurn = player.getDirection().getOpposite().toYRot();
                                    if(rotationToTurn == 180.0f){
                                        rotationToTurn = 0.0f;
                                    }
                                    else if(rotationToTurn == 0.0f){
                                        rotationToTurn = 180.0f;
                                    }
                                    customDeco.modelRotations = new Vec3(0D,rotationToTurn,0D);
                                }
                            }
                        }
                        // update the custom deco

                        customDeco.updateBlock();
                        // show particles for feedback reasons
                        if(level instanceof ServerLevel serverLevel){
                            serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,clickedPos.getX() + 1.5D,clickedPos.getY() + 1.5,clickedPos.getZ() + 1.5D,
                                    10,
                                    0D,0D,0D,2);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            else{
                CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
                if(customDeco instanceof CustomizableCopyingDecoBE){
                    customDeco.modelOffsets = new Vec3(ThingamajigsCalcStuffs.nextDoubleBetweenInclusive(-0.25D,0.25D) - 0.5D,0D,
                            ThingamajigsCalcStuffs.nextDoubleBetweenInclusive(0.25D,0.25D) - 0.5D);
                    customDeco.updateBlock();
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
