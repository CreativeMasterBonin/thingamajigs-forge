package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.ticks.TickPriority;
import org.jetbrains.annotations.Nullable;

public class TestBlock extends Block {
    public static IntegerProperty COLOR = IntegerProperty.create("color",0,16777215);
    public TestBlock(Properties p) {
        super(p);
    }

    public static int getMaxColorValue(){
        return 16777215;
    }

    @Override
    public void onPlace(BlockState bs1, Level l, BlockPos bp, BlockState bs2, boolean b1) {
        l.scheduleTick(bp,this,10, TickPriority.LOW);
    }

    @Override
    public boolean isRandomlyTicking(BlockState bs) {
        return false;
    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        int i = rs.nextInt(0,getMaxColorValue());
        sl.setBlock(bp,bs.setValue(COLOR,i),3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(COLOR);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        return this.defaultBlockState().setValue(COLOR,0);
    }
}
