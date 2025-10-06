package net.rk.thingamajigs.xtrablock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DecorativeEngine extends RedstoneLampBlock {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final IntegerProperty LEVEL = IntegerProperty.create("level",0,7);

    public DecorativeEngine(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(LIT);
    }
}
