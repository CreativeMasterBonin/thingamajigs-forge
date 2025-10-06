package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldenAppleShard extends Item {
    public GoldenAppleShard(Properties p) {
        super(p);
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable Level lvl, List<Component> l, TooltipFlag tf) {
        l.add(Component.translatable("thingamajigs.golden_apple_shard.desc")
                .withStyle(ChatFormatting.GRAY));
    }
}
