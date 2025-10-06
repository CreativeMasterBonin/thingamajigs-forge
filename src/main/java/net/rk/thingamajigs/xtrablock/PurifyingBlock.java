package net.rk.thingamajigs.xtrablock;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurifyingBlock extends Block {
    public PurifyingBlock(Properties p) {
        super(p.strength(1f,10f)
                .sound(SoundType.NETHERITE_BLOCK)
                .mapColor(MapColor.DIAMOND)
                .requiresCorrectToolForDrops());
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable BlockGetter bg, List<Component> lc, TooltipFlag tf) {
        lc.add(Component.translatable("block.thingamajigs.purifying_block.desc")
                .withStyle(ChatFormatting.GRAY));
    }
}
