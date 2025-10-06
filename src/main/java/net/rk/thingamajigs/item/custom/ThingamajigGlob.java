package net.rk.thingamajigs.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.item.bases.BaseGlob;

import javax.annotation.Nullable;
import java.util.List;

public class ThingamajigGlob extends Item {
    public ThingamajigGlob(Properties p) {
        super(p.stacksTo(64));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> ttc, TooltipFlag pIsAdvanced) {
        ttc.add(Component.translatable("tooltip.thingamajigs.thingamajig_glob")
                .withStyle(ChatFormatting.GRAY));

        ttc.add(Component.translatable("title.thingamajigs.cbui")
                .withStyle(ChatFormatting.GREEN));

        ttc.add(Component.translatable("tooltip.thingamajigs.compat_crafters")
                .withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(pStack, pLevel, ttc, pIsAdvanced);
    }
}
