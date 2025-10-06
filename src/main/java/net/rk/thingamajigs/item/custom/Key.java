package net.rk.thingamajigs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Key extends Item {
    public Key(Properties p) {
        super(p.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable Level p_41422_, List<Component> lc, TooltipFlag tf) {
        lc.add(Component.translatable("item.thingamajigs.key.desc"));
    }
}
