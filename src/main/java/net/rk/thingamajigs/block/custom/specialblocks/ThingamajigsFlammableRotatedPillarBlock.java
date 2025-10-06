package net.rk.thingamajigs.block.custom.specialblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import org.jetbrains.annotations.Nullable;

public class ThingamajigsFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ThingamajigsFlammableRotatedPillarBlock(Properties properties) {
        super(properties.ignitedByLava());
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(ThingamajigsBlocks.RUBBER_LOG.get())){
                return ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }

            if(state.is(ThingamajigsBlocks.RUBBER_WOOD.get())){
                return ThingamajigsBlocks.STRIPPED_RUBBER_WOOD.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state,context,toolAction,simulate);
    }
}
