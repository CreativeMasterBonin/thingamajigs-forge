package net.rk.thingamajigs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BlankPaintBrush extends Item {
    public BlankPaintBrush(Properties pProperties) {
        super(pProperties.durability(3000));
    }

    /*
    @Override
    public InteractionResult useOn(UseOnContext usc1) {
        Player player = usc1.getPlayer();
        ItemStack itemstack = usc1.getItemInHand();

        if(!player.isCreative() && player.isShiftKeyDown()){
            itemstack.hurtAndBreak(1, player,(thing) -> thing.broadcastBreakEvent(usc1.getHand()));
            if(itemstack.getDamageValue() > 2999){

            }
            else{
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.PASS;
    }
    */

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.thingamajigs.blankpaintbrush"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
