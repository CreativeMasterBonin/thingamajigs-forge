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
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.entity.customblock.CustomizableCopyingDecoBE;
import net.rk.thingamajigs.item.bases.Mode;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CustomizationTool extends Item {
    public int mode = 0;
    public static final Map<String,Mode> NAMED_MODES = Map.of(
            "solidify", new Mode("solidify") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.renderingMode = "solid";
                        customDeco.updateBlock();
                    }
                }
            },
            "cutout", new Mode("cutout") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.renderingMode = "cutout";
                        customDeco.updateBlock();
                    }
                }
            },
            "translucent", new Mode("translucent") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        customDeco.renderingMode = "translucent";
                        customDeco.updateBlock();
                    }
                }
            },
            "rotate_x", new Mode("rotate_x") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        if(player.isShiftKeyDown()){
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x - 0.5f,customDeco.modelRotations.y,customDeco.modelRotations.z);
                        }
                        else{
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x + 0.5f,customDeco.modelRotations.y,customDeco.modelRotations.z);
                        }
                    }
                }
            },
            "rotate_y", new Mode("rotate_y") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        if(player.isShiftKeyDown()){
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y - 0.5f,customDeco.modelRotations.z);
                        }
                        else{
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y + 0.5f,customDeco.modelRotations.z);
                        }
                    }
                }
            },
            "rotate_z", new Mode("rotate_z") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        if(player.isShiftKeyDown()){
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y,customDeco.modelRotations.z - 0.5f);
                        }
                        else{
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y,customDeco.modelRotations.z + 0.5f);
                        }
                    }
                }
            }
    );

    public CustomizationTool(Properties p) {
        super(p.setNoRepair().fireResistant().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        if(stack.hasTag()){
            if(stack.getTag().contains("mode")){
                list.add(Component.translatable("item.thingamajigs.customization_tool.mode",String.valueOf(mode))
                        .withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        if(!stack.hasTag()){
            CompoundTag tag = new CompoundTag();
            tag.putString("mode","solidify");
            stack.setTag(tag);
            mode = 0;
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        if(!stack.hasTag()){
            CompoundTag tag = new CompoundTag();
            tag.putString("mode","solidify");
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
                    NAMED_MODES.values().stream().toList().get(stack.getTag().getInt("mode")).performModeTask(stack,level,pos,player);
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
                List<Mode> modes = NAMED_MODES.values().stream().toList();

                if(currentStack.getTag().contains("mode")){
                    if(currentStack.getTag().getInt("mode") >= modes.size() - 1){
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
        }
        return false;
    }
}
