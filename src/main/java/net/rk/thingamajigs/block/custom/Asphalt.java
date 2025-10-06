package net.rk.thingamajigs.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class Asphalt extends Block {
    public Asphalt(Properties properties) {
        super(properties.sound(SoundType.TUFF).strength(2F).requiresCorrectToolForDrops());
    }
}
