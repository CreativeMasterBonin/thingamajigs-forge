package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class CustomLeverBlock extends LeverBlock {
    public CustomLeverBlock(Properties p) {
        super(p.strength(1F,1F).sound(SoundType.STONE).noCollission().noOcclusion());
    }

    // removed redstone particles for switches so they are more 'static'
    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rnd) {

    }
}
