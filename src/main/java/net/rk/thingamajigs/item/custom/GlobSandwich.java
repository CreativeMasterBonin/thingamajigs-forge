package net.rk.thingamajigs.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.item.ThingamajigsFood;

import javax.annotation.Nullable;
import java.util.List;

public class GlobSandwich extends Item {
    public GlobSandwich(Properties p) {
        super(p.food(ThingamajigsFood.GLOB_SANDWICH_PROPERTIES).stacksTo(64).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.thingamajigs.glob_sandwich").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
