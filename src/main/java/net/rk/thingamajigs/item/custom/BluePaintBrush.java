package net.rk.thingamajigs.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.rk.thingamajigs.block.MarkedAsphalt;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.custom.Asphalt;
import net.rk.thingamajigs.block.custom.blocks.BlueRoadMarking;
import net.rk.thingamajigs.legacy.BluePaintBrushItemActions;
import net.rk.thingamajigs.xtrablock.AsphaltSlab;
import net.rk.thingamajigs.xtrablock.RotatingSlab;

import javax.annotation.Nullable;
import java.util.List;

public class BluePaintBrush extends Item {
    public String currentname = "Undefined";
    public BluePaintBrush(Properties pProperties) {
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
            Block marking = ThingamajigsBlocks.BLUE_ROAD_MARKING.get();
            Player ply = pContext.getPlayer();
            int marking_type = 0;


            // direct painting of asphalt slabs
            if (blockClicked instanceof AsphaltSlab && !ply.isShiftKeyDown()) {
                BlockState oldState = pContext.getLevel().getBlockState(pContext.getClickedPos());
                BlockState paintAsp = ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get().defaultBlockState();

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

                    if (blockClicked == ThingamajigsBlocks.ASPHALT_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OK_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = ThingamajigsBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = ThingamajigsBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OLD_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = ThingamajigsBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
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
                BlockState paintAsp = ThingamajigsBlocks.BLUE_PARKING_ASPHALT.get().defaultBlockState();
                if (ply.getItemInHand(ply.getUsedItemHand()).getTag() != null) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).getTag().getInt("marking_type");
                    int age = 0;
                    boolean notValid = false;

                    if (blockClicked == ThingamajigsBlocks.ASPHALT.get()) {
                        age = 0;
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OK.get()) {
                        age = 1;
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_MEDIOCRE.get()) {
                        age = 2;
                    }
                    else if (blockClicked == ThingamajigsBlocks.ASPHALT_OLD.get()) {
                        age = 3;
                    }
                    else{
                        notValid = true;
                    }

                    if(notValid == false){
                        if (type == 1) {
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                        else{
                            notValid = true;
                        }
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
                    BluePaintBrushItemActions.paint(levelAccessor,pContext.getClickedPos().getX(),pContext.getClickedPos().getY(),pContext.getClickedPos().getZ(),pContext.getPlayer(), stack, marking_type);
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
            if(stack.getTag().getInt("marking_type") >= BlueRoadMarking.getMaxTypes()){
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
            pTooltipComponents.add(Component.literal(currentname).withStyle(ChatFormatting.GREEN));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public void typeToName(int type){
        switch(type){
            case 0: currentname = "Thin Corner";break;
            case 1: currentname = "Thin Parking Line";break;
            case 2: currentname = "Thick Parking Line";break;
            case 3: currentname = "Sidewalk Line";break;
            case 4: currentname = "Thin Corner Dot";break;
            case 5: currentname = "Disabled Parking";break;
            case 6: currentname = "Disabled Parking (Alt)";break;
            case 7: currentname = "Short Line";break;
            case 8: currentname = "Short 'T'";break;
            case 9: currentname = "Plus";break;
            case 10: currentname = "Square";break;
            case 11: currentname = "Center 'T'";break;
            case 12: currentname = "Long 'T'";break;
            case 13: currentname = "blue marking";break;
            default: currentname = "undefined";break;
        }
    }
}
