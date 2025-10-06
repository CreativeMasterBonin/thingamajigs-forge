package net.rk.thingamajigs.xtrablock;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetherishStone extends Block {
    public NetherishStone(Properties p) {
        super(p.strength(1F,20F).sound(SoundType.NETHERRACK));
    }

    @Override
    public float getSpeedFactor() {
        return 0.35F;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter bg, List<Component> list, TooltipFlag ttf) {
        list.add(Component.translatable("block.thingamajigs.netherish_stone.desc"));
    }
}
