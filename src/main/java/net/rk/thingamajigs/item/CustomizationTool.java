package net.rk.thingamajigs.item;

import com.google.common.collect.ImmutableList;
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
    public int renderMode = 0;

    // each modifier mode is stored here
    public static final Map<String,Mode> NAMED_MODES = Map.of(
            "rotate_x", new Mode("rotate_x") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelRotations.x;
                        if(player.isShiftKeyDown()){
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x - 1f,customDeco.modelRotations.y,customDeco.modelRotations.z);
                        }
                        else{
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x + 1f,customDeco.modelRotations.y,customDeco.modelRotations.z);
                        }
                        customDeco.updateBlock();
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","rot X",oldValue,customDeco.modelRotations.x)
                                .withStyle(ChatFormatting.BLUE),true);
                    }
                }
            },
            "rotate_y", new Mode("rotate_y") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelRotations.y;
                        if(player.isShiftKeyDown()){
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y - 1f,customDeco.modelRotations.z);
                        }
                        else{
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y + 1f,customDeco.modelRotations.z);
                        }
                        customDeco.updateBlock();
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","rot Y",oldValue,customDeco.modelRotations.y)
                                .withStyle(ChatFormatting.BLUE),true);
                    }
                }
            },
            "rotate_z", new Mode("rotate_z") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelRotations.z;
                        if(player.isShiftKeyDown()){
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y,customDeco.modelRotations.z - 1f);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y,customDeco.modelRotations.z + 1f);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","rot Z",oldValue,customDeco.modelRotations.z)
                                .withStyle(ChatFormatting.BLUE),true);
                    }
                }
            },
            "offset_x", new Mode("offset_x") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelOffsets.x;
                        if(player.isShiftKeyDown()){
                            customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x - 0.5f,customDeco.modelOffsets.y,customDeco.modelOffsets.z);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x + 0.5f,customDeco.modelOffsets.y,customDeco.modelOffsets.z);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","offset X",oldValue,customDeco.modelOffsets.x)
                                .withStyle(ChatFormatting.GREEN),true);
                    }
                }
            },
            "offset_y", new Mode("offset_y") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelOffsets.y;
                        if(player.isShiftKeyDown()){
                            customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y - 0.5f,customDeco.modelOffsets.z);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y + 0.5f,customDeco.modelOffsets.z);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","offset Y",oldValue,customDeco.modelOffsets.y)
                                .withStyle(ChatFormatting.GREEN),true);
                    }
                }
            },
            "offset_z", new Mode("offset_z") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelOffsets.z;
                        if(player.isShiftKeyDown()){
                            customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y,customDeco.modelOffsets.z - 0.5f);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y,customDeco.modelOffsets.z + 0.5f);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","offset Z",oldValue,customDeco.modelOffsets.z)
                                .withStyle(ChatFormatting.GREEN),true);
                    }
                }
            },
            "scale_x", new Mode("scale_x") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelScale.x;
                        if(player.isShiftKeyDown()){
                            customDeco.modelScale = new Vec3(customDeco.modelScale.x - 0.5f,customDeco.modelScale.y,customDeco.modelScale.z);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelScale = new Vec3(customDeco.modelScale.x + 0.5f,customDeco.modelScale.y,customDeco.modelScale.z);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","scale X",oldValue,customDeco.modelScale.x)
                                .withStyle(ChatFormatting.YELLOW),true);
                    }
                }
            },
            "scale_y", new Mode("scale_y") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelScale.y;
                        if(player.isShiftKeyDown()){
                            customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y - 0.5f,customDeco.modelScale.z);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y + 0.5f,customDeco.modelScale.z);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","scale Y",oldValue,customDeco.modelScale.y)
                                .withStyle(ChatFormatting.YELLOW),true);
                    }
                }
            },
            "scale_z", new Mode("scale_z") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        double oldValue = customDeco.modelScale.z;
                        if(player.isShiftKeyDown()){
                            customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y,customDeco.modelScale.z - 0.5f);
                            customDeco.updateBlock();
                        }
                        else{
                            customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y,customDeco.modelScale.z + 0.5f);
                            customDeco.updateBlock();
                        }
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","scale Z",oldValue,customDeco.modelScale.z)
                                .withStyle(ChatFormatting.YELLOW),true);
                    }
                }
            }
    );

    // each render mode is stored here
    public static final Map<String,Mode> NAMED_RENDER_MODES = Map.of(
            "solidify", new Mode("solidify") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        String oldValue = customDeco.renderingMode;
                        customDeco.renderingMode = "solid";
                        customDeco.updateBlock();
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","render mode",oldValue,customDeco.renderingMode)
                                .withStyle(ChatFormatting.WHITE),true);
                    }
                }
            },
            "cutout", new Mode("cutout") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        String oldValue = customDeco.renderingMode;
                        customDeco.renderingMode = "cutout";
                        customDeco.updateBlock();
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","render mode",oldValue,customDeco.renderingMode)
                                .withStyle(ChatFormatting.WHITE),true);
                    }
                }
            },
            "translucent", new Mode("translucent") {
                @Override
                public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
                    CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                    if(customDeco instanceof CustomizableCopyingDecoBE){
                        String oldValue = customDeco.renderingMode;
                        customDeco.renderingMode = "translucent";
                        customDeco.updateBlock();
                        player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","render mode",oldValue,customDeco.renderingMode)
                                .withStyle(ChatFormatting.WHITE),true);
                    }
                }
            }
    );

    public static final List<Mode> modesListified = NAMED_MODES.values().stream().toList();
    public static final List<Mode> renderModesListified = NAMED_RENDER_MODES.values().stream().toList();

    public CustomizationTool(Properties p) {
        super(p.setNoRepair().fireResistant().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.thingamajigs.customization_tool.desc").withStyle(ChatFormatting.GRAY));
        if(stack.hasTag()){
            list.add(Component.translatable("item.thingamajigs.customization_tool.mode",modesListified.get(stack.getTag().getInt("mode")).name)
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
                    modesListified.get(stack.getTag().getInt("mode")).performModeTask(stack,level,pos,player);
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
                    if(currentStack.getTag().getInt("mode") >= modesListified.size() - 1){
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
