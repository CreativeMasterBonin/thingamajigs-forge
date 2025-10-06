package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.rk.thingamajigs.block.custom.GlassLikeBlock;

public class NotQuiteMengerBlock extends GlassLikeBlock {
    public NotQuiteMengerBlock(Properties p) {
        super(p.strength(1F,120F).sound(SoundType.NETHERITE_BLOCK).mapColor(MapColor.COLOR_BLUE));
    }
}
