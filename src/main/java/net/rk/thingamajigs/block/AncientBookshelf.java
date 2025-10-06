package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class AncientBookshelf extends Block {
    public AncientBookshelf(Properties p) {
        super(p.strength(5f).sound(SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().mapColor(MapColor.NETHER));
    }

    // the recipe for 1 particular Thingamajigs' Bookshelf requires ancient debris, but because I'm nice, you get ENCHANTMENT POWER!!! WOO!!!
    @Override
    public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
        return 1.0F;
    }
}
