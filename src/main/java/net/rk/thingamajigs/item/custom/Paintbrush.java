package net.rk.thingamajigs.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.rk.thingamajigs.block.MarkedAsphalt;
import net.rk.thingamajigs.block.Sidewalk;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.custom.Asphalt;
import net.rk.thingamajigs.block.custom.blocks.WhiteRoadMarking;
import net.rk.thingamajigs.legacy.ChangeTypeFirst;
import net.rk.thingamajigs.xtrablock.AsphaltSlab;
import net.rk.thingamajigs.xtrablock.RotatingSlab;

import javax.annotation.Nullable;
import java.util.List;

public class Paintbrush extends Item {
    public String currentName = "Undefined";
    public Paintbrush(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            LevelAccessor levelAccessor = pContext.getLevel();
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
            ItemStack stack = pContext.getItemInHand();
            Block marking = ThingamajigsBlocks.WHITE_ROAD_MARKING.get();
            Player ply = pContext.getPlayer();
            int marking_type = 0;

            // direct painting of asphalt slabs
            if (blockClicked instanceof AsphaltSlab && !ply.isShiftKeyDown()) {
                BlockState oldState = pContext.getLevel().getBlockState(pContext.getClickedPos());
                BlockState paintAsp = ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get().defaultBlockState();

                if (ply.getItemInHand(ply.getUsedItemHand()).getTag() != null) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).getTag().getInt("marking_type");

                    SlabType top = SlabType.TOP;
                    SlabType bottom = SlabType.BOTTOM;
                    SlabType d = SlabType.DOUBLE;
                    SlabType slab = top;

                    if (oldState.getValue(SlabBlock.TYPE) == top) {
                        slab = top;
                    } else if (oldState.getValue(SlabBlock.TYPE) == bottom) {
                        slab = bottom;
                    } else if (oldState.getValue(SlabBlock.TYPE) == d) {
                        slab = d;
                    }

                    //case 2: currentName = "Center Double Line Turn"; break;
                    //            case 3: currentName = "Center Double Line"; break;

                    if (blockClicked == ThingamajigsBlocks.ASPHALT_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = ThingamajigsBlocks.WHITE_DT_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = ThingamajigsBlocks.WHITE_D_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OK_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = ThingamajigsBlocks.WHITE_DT_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = ThingamajigsBlocks.WHITE_D_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = ThingamajigsBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = ThingamajigsBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = ThingamajigsBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = ThingamajigsBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OLD_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = ThingamajigsBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = ThingamajigsBlocks.WHITE_D_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = ThingamajigsBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                }
            }

            // direct painting of asphalt blocks
            if (blockClicked instanceof Asphalt && !ply.isShiftKeyDown()) {
                BlockState paintAsp = ThingamajigsBlocks.WHITE_PARKING_ASPHALT.get().defaultBlockState();
                if (ply.getItemInHand(ply.getUsedItemHand()).getTag() != null) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).getTag().getInt("marking_type");
                    int age = 0;
                    boolean notValid = false;

                    if(blockClicked == ThingamajigsBlocks.ASPHALT.get()){
                        age = 0;
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OK.get()) {
                        age = 1;
                    } else if (blockClicked == ThingamajigsBlocks.ASPHALT_MEDIOCRE.get()) {
                        age = 2;
                    } else if (blockClicked == ThingamajigsBlocks.ASPHALT_OLD.get()) {
                        age = 3;
                    }
                    else{
                        notValid = true;
                    }

                    if(notValid == false){
                        if (type == 2) {
                            paintAsp = ThingamajigsBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = ThingamajigsBlocks.DOUBLE_WHITE_ASPHALT.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = ThingamajigsBlocks.WHITE_PARKING_ASPHALT.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                    }
                    else{
                        notValid = true;
                    }
                }
            }

            if(pContext.getHand() == InteractionHand.MAIN_HAND) {
                CompoundTag tag = new CompoundTag();

                if(pContext.getPlayer().isShiftKeyDown()){
                    if(stack.hasTag()) {
                        increaseType(stack);
                        marking_type = stack.getTag().getInt("marking_type");
                        level.playSound(null,positionClicked,SoundEvents.HONEYCOMB_WAX_ON,SoundSource.BLOCKS,1F,1F);
                    }
                    else {
                        tag.putInt("marking_type", 0);
                        stack.setTag(tag);
                        level.playSound(null,positionClicked,SoundEvents.AXE_STRIP,SoundSource.BLOCKS,1F,1F);
                    }
                }
                else{
                    if(stack.hasTag()){
                        marking_type = stack.getTag().getInt("marking_type");
                    }
                    ChangeTypeFirst.execute(levelAccessor,pContext.getClickedPos().getX(),pContext.getClickedPos().getY(),pContext.getClickedPos().getZ(),pContext.getPlayer(), stack, marking_type);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.SUCCESS;
    }

    private InteractionResult finished(Player ply){
        ply.swing(ply.getUsedItemHand());
        return InteractionResult.CONSUME;
    }

    private void increaseType(ItemStack stack) {
        if (stack.getTag() != null){
            stack.getTag().putInt("marking_type", stack.getTag().getInt("marking_type") + 1);
            if(stack.getTag().getInt("marking_type") >= WhiteRoadMarking.MAX_TYPES){
                stack.getTag().putInt("marking_type", 0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.thingamajigs.paintbrush"));
        if(pStack.hasTag()) {
            typeToName(pStack.getTag().getInt("marking_type"));
            pTooltipComponents.add(Component.literal("Type: " + pStack.getTag().getInt("marking_type")));
            pTooltipComponents.add(Component.literal(currentName).withStyle(ChatFormatting.GREEN));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public void typeToName(int type){
        switch(type){
            case 0: currentName = "Full Cover"; break;
            case 1: currentName = "Thin Corner Dot"; break;
            case 2: currentName = "Center Double Line Turn"; break;
            case 3: currentName = "Center Double Line"; break;
            case 4: currentName = "Center Single Corner"; break;
            case 5: currentName = "Center Single Line"; break;
            case 6: currentName = "Center Dashed Single Line"; break;
            case 7: currentName = "Thick Parking Line"; break;
            case 8: currentName = "Thin Corner"; break;
            case 9: currentName = "Thin Parking Line"; break;
            case 10: currentName = "Left Arrow With Center Line"; break;
            case 11: currentName = "Left Arrow With Center Arrow"; break;
            case 12: currentName = "Left Arrow"; break;
            case 13: currentName = "Right Arrow With Center Line"; break;
            case 14: currentName = "Right Arrow With Center Arrow"; break;
            case 15: currentName = "Right Arrow"; break;
            case 16: currentName = "U-Turn"; break;
            case 17: currentName = "Up Arrow"; break;
            case 18: currentName = "Disabled Symbol"; break;
            case 19: currentName = "Ahead"; break;
            case 20: currentName = "Only"; break;
            case 21: currentName = "Parking 'P'"; break;
            case 22: currentName = "Stop"; break;
            case 23: currentName = "School"; break;
            case 24: currentName = "Shcool"; break;
            case 25: currentName = "Sidewalk Line";break;
            case 26: currentName = "Disabled Symbol (Alt)";break;
            case 27: currentName = "Bicycle Symbol";break;
            case 28: currentName = "Short Line";break;
            case 29: currentName = "Short 'T'";break;
            case 30: currentName = "Plus";break;
            case 31: currentName = "Square";break;
            case 32: currentName = "Center 'T'";break;
            case 33: currentName = "Long 'T'";break;
            case 34: currentName = "white marking";break;
            default: currentName = "undefined";break;
        }
    }
}
