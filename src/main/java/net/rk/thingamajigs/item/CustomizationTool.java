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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.item.bases.modes.Mode;
import net.rk.thingamajigs.item.bases.modes.Modes;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomizationTool extends Item {
    public int mode = 0;
    public int renderMode = 0;

    public CustomizationTool(Properties p) {
        super(p.setNoRepair().fireResistant().stacksTo(1));
    }

    public Mode grabModeFromID(int id){
        switch (id){
            case 0: return Modes.ROTATE_X;
            case 1: return Modes.ROTATE_Y;
            case 2: return Modes.ROTATE_Z;
            case 3: return Modes.OFFSET_X;
            case 4: return Modes.OFFSET_Y;
            case 5: return Modes.OFFSET_Z;
            case 6: return Modes.SCALE_X;
            case 7: return Modes.SCALE_Y;
            case 8: return Modes.SCALE_Z;
            case 9: return Modes.SCALE_Z;
            default: throw new IllegalArgumentException("Invalid id: " + id + " expected range 0-8!");
        }
    }

    public Mode grabRenderModeFromID(int id){
        switch (id){
            case 0: return Modes.SOLIDIFY;
            case 1: return Modes.CUTOUT;
            case 2: return Modes.TRANSLUCENT;
            case 3: return Modes.TRANSLUCENT;
            default: throw new IllegalArgumentException("Invalid id: " + id + " expected range 0-2!");
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.thingamajigs.customization_tool.desc").withStyle(ChatFormatting.GRAY));
        if(stack.hasTag()){
            list.add(Component.translatable("item.thingamajigs.customization_tool.mode",grabModeFromID(stack.getTag().getInt("mode")).name)
                    .withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        if(!stack.hasTag()){
            CompoundTag tag = new CompoundTag();
            tag.putInt("mode",0);
            stack.setTag(tag);
            mode = 0;
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        if(!stack.hasTag()){
            CompoundTag tag = new CompoundTag();
            tag.putInt("mode",0);
            stack.setTag(tag);
            mode = 0;
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
                if (stack.getTag().contains("mode")) {
                    if(level.getRandom().nextBoolean()){
                        player.playSound(SoundEvents.SPYGLASS_USE,0.5f,1.0f);
                    }
                    else{
                        player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.5f,1.0f);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        else{
            if(stack.hasTag()){
                if(stack.getTag().contains("mode")){
                    grabModeFromID(stack.getTag().getInt("mode")).performModeTask(stack,level,pos,player);
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
                if(currentStack.getTag().contains("mode")){
                    if(currentStack.getTag().getInt("mode") >= 8){
                        tag.putInt("mode",0);
                    }
                    else{
                        tag.putInt("mode",currentStack.getTag().getInt("mode") + 1);
                    }
                    currentStack.setTag(tag);
                    mode = currentStack.getTag().getInt("mode");
                    player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.45f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.92f,1.1f));
                    return true;
                }
                else{
                    tag.putInt("mode",0);
                    currentStack.setTag(tag);
                    return true;
                }
            }
            else{
                CompoundTag tag2 = new CompoundTag();
                tag2.putInt("mode",0);
                currentStack.setTag(tag2);
                return true;
            }
        }
        return false;
    }
}
