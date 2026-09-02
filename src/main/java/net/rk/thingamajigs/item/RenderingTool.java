package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.item.bases.Mode;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RenderingTool extends CustomizationTool{
    public static final String RENDER_MODE_TAG_NAME = "render_mode"; // the compound tag name for the render mode
    public static final String DEFAULT_RENDER_MODE_TYPE = "solidify"; // which is 0

    public RenderingTool(Properties p) {
        super(p);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.thingamajigs.rendering_tool.desc").withStyle(ChatFormatting.GRAY));
        if(stack.hasTag()){
            if(stack.getTag().contains(RENDER_MODE_TAG_NAME)){
                list.add(Component.translatable("item.thingamajigs.rendering_tool.mode",NAMED_RENDER_MODES.values().stream().toList().get(renderMode).name)
                        .withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        if(!stack.hasTag()){
            CompoundTag tag = new CompoundTag();
            tag.putInt(RENDER_MODE_TAG_NAME,0);
            stack.setTag(tag);
            renderMode = 0;
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        if(!stack.hasTag()){
            CompoundTag tag = new CompoundTag();
            tag.putInt(RENDER_MODE_TAG_NAME,0);
            stack.setTag(tag);
            renderMode = 0;
        }
        return stack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        InteractionHand hand = context.getHand();
        ItemStack stack = context.getItemInHand();
        if(level.isClientSide()){
            if(stack.hasTag()) {
                if (stack.getTag().contains(RENDER_MODE_TAG_NAME)) {
                    if(level.getRandom().nextBoolean()){
                        switch (level.getRandom().nextIntBetweenInclusive(1,3)){
                            case 1 -> {player.playSound(SoundEvents.SPYGLASS_USE,0.5f,1.0f);break;}
                            case 2 -> {player.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT,0.35f,1.0f);break;}
                            case 3 -> {player.playSound(SoundEvents.VILLAGER_YES,0.21f,1.0f);break;}
                        }
                    }
                    else{
                        player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.4f,1.0f);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        else{
            if(stack.hasTag()){
                if(stack.getTag().contains(RENDER_MODE_TAG_NAME)){
                    NAMED_RENDER_MODES.values().stream().toList().get(stack.getTag().getInt(RENDER_MODE_TAG_NAME)).performModeTask(stack,level,pos,player);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack currentStack, ItemStack otherStack, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if(action == ClickAction.SECONDARY){
            CompoundTag tag = currentStack.getOrCreateTag();
            if(currentStack.hasTag()){
                List<Mode> modes = NAMED_RENDER_MODES.values().stream().toList();

                if(currentStack.getTag().contains(RENDER_MODE_TAG_NAME)){
                    if(currentStack.getTag().getInt(RENDER_MODE_TAG_NAME) >= modes.size() - 1){
                        tag.putInt(RENDER_MODE_TAG_NAME,0);
                    }
                    else{
                        tag.putInt(RENDER_MODE_TAG_NAME,currentStack.getTag().getInt(RENDER_MODE_TAG_NAME) + 1);
                    }
                    currentStack.setTag(tag);
                    renderMode = currentStack.getTag().getInt(RENDER_MODE_TAG_NAME);
                    player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.45f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.92f,1.1f));
                    return true;
                }
                else{
                    tag.putInt(RENDER_MODE_TAG_NAME,0);
                    currentStack.setTag(tag);
                    return true;
                }
            }
            else{
                CompoundTag tag2 = new CompoundTag();
                tag2.putInt(RENDER_MODE_TAG_NAME,0);
                currentStack.setTag(tag2);
                return true;
            }
        }
        return false;
    }
}
