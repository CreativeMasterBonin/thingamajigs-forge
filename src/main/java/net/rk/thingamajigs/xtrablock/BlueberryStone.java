package net.rk.thingamajigs.xtrablock;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlueberryStone extends Block {
    public BlueberryStone(Properties p) {
        super(p.strength(1.5F,32F).sound(SoundType.TUFF));
    }

    @Override
    public float getJumpFactor() {
        return 1.5F;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter bg, List<Component> list, TooltipFlag ttf) {
        list.add(Component.translatable("block.thingamajigs.blueberry_stone.desc"));
    }
}
