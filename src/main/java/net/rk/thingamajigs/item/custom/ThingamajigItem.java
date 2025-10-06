package net.rk.thingamajigs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.config.ThingamajigsServerConfigs;

import javax.annotation.Nullable;
import java.util.List;

public class ThingamajigItem extends Item {
    public ThingamajigItem(Properties pProperties) {
        super(pProperties);
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        int size = ThingamajigsServerConfigs.COMMON.maxThingamajigsStackSize.get();
        if(size > 64){
            return 64;
        }
        else if(size >= 16){
            return ThingamajigsServerConfigs.COMMON.maxThingamajigsStackSize.get();
        }
        else{
            return 16;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable("tooltip.thingamajigs.thingamajig"));
    }
}
