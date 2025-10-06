package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RubberItem extends Item {
    public RubberItem(Properties p) {
        super(p.stacksTo(64).setNoRepair());
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable Level p_41422_, List<Component> cl, TooltipFlag tf) {
        cl.add(Component.translatable("item.thingamajigs.rubber.desc").withStyle(ChatFormatting.GRAY));
    }
}
