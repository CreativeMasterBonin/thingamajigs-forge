package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
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
    public RandomizeAndResetTool(Properties p) {
        super(p.setNoRepair().fireResistant().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.thingamajigs.randomize_and_reset_tool.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        Player player = ctx.getPlayer();
        BlockPos clickedPos = ctx.getClickedPos();
        if(level.isClientSide()){
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                if(player.isShiftKeyDown()){
                    boolean allOffsetZero = customDeco.modelOffsets.x == 0.0D && customDeco.modelOffsets.y == 0.0D && customDeco.modelOffsets.z == 0.0D;
                    boolean allRotationZero = customDeco.modelRotations.x == 0.0D && customDeco.modelRotations.y == 0.0D && customDeco.modelRotations.z == 0.0D;
                    boolean allScaleZero = customDeco.modelScale.x == 0.0D && customDeco.modelScale.y == 0.0D && customDeco.modelScale.z == 0.0D;

                    if(!allOffsetZero && !allRotationZero && !allScaleZero){
                        player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL,0.4f,ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                        return InteractionResult.SUCCESS;
                    }
                }
                else{
                    player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.4f,ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.97f,1.1f));
                    return InteractionResult.SUCCESS;
                }
            }
        }
        else{
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                if(player.isShiftKeyDown()){
                    boolean allOffsetZero = customDeco.modelOffsets.x == 0.0D && customDeco.modelOffsets.y == 0.0D && customDeco.modelOffsets.z == 0.0D;
                    boolean allRotationZero = customDeco.modelRotations.x == 0.0D && customDeco.modelRotations.y == 0.0D && customDeco.modelRotations.z == 0.0D;
                    boolean allScaleZero = customDeco.modelScale.x == 0.0D && customDeco.modelScale.y == 0.0D && customDeco.modelScale.z == 0.0D;

                    if(!allOffsetZero && !allRotationZero && !allScaleZero){
                        customDeco.modelOffsets = new Vec3(0D,0D,0D);
                        customDeco.modelScale = new Vec3(0D,0D,0D);
                        customDeco.modelRotations = new Vec3(0D,0D,0D);
                        customDeco.updateBlock();
                        if(level instanceof ServerLevel serverLevel){
                            serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,clickedPos.getX() + 0.5D,clickedPos.getY() + 0.75,clickedPos.getZ() + 0.5D,
                                    10,
                                    0D,0D,0D,0);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
                else{
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
