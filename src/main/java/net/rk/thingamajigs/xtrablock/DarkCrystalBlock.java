package net.rk.thingamajigs.xtrablock;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DarkCrystalBlock extends AncientRelicCrystalBlock{
    public DarkCrystalBlock(Properties p) {
        super(p.mapColor(MapColor.COLOR_PURPLE).strength(47F,2200F));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter bg, List<Component> list, TooltipFlag ttf) {
        list.add(Component.translatable("block.thingamajigs.dark_crystal_block.desc"));
    }
}
