package net.rk.thingamajigs.xtrablock;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.rk.thingamajigs.block.custom.GlassLikeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ColoredGlass extends GlassLikeBlock {
    public ColoredGlass(Properties properties) {
        super(properties
                .strength(0.3F,20F)
                .instrument(NoteBlockInstrument.HAT)
                .sound(SoundType.GLASS)
        );
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.thingamajigs.colored_glass.desc"));
    }
}
