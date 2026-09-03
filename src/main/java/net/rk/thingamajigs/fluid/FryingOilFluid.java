package net.rk.thingamajigs.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class FryingOilFluid extends ForgeFlowingFluid {
    public static final ForgeFlowingFluid.Properties FRYING_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> ThingamajigsFluids.FRYING_OIL_TYPE.get(),
            () -> ThingamajigsFluids.FRYING_OIL.get(),
            () -> ThingamajigsFluids.FRYING_OIL_FLOWING.get())
            .explosionResistance(100f)
            .levelDecreasePerBlock(1)
            .slopeFindDistance(5)
            .bucket(() -> ThingamajigsItems.FRYING_OIL_BUCKET.get())
            .block(() -> (LiquidBlock) ThingamajigsBlocks.FRYING_OIL.get());

    public FryingOilFluid() {
        super(FRYING_OIL_PROPERTIES);
    }

    @Override
    public int getTickDelay(LevelReader level){
        return level.dimensionType().ultraWarm() ? 1 : 3;
    }

    @Override
    public void animateTick(Level level, BlockPos bp, FluidState flst, RandomSource rnds) {
        if (!flst.isSource() && !flst.getValue(FALLING)) {
            if (rnds.nextInt(64) == 0) {
                if(level.getRandom().nextBoolean()){
                    level.playLocalSound(
                            (double)bp.getX() + 0.5D,
                            (double)bp.getY() + 0.5D,
                            (double)bp.getZ() + 0.5D,
                            ThingamajigsSoundEvents.FRYING_ONE.get(),
                            SoundSource.BLOCKS,
                            rnds.nextFloat() * 0.25F + 0.75F,
                            rnds.nextFloat() + 0.5F, false);
                }
                else{
                    level.playLocalSound(
                            (double)bp.getX() + 0.5D,
                            (double)bp.getY() + 0.5D,
                            (double)bp.getZ() + 0.5D,
                            ThingamajigsSoundEvents.FRYING_TWO.get(),
                            SoundSource.BLOCKS,
                            rnds.nextFloat() * 0.25F + 0.75F,
                            rnds.nextFloat() + 0.5F, false);
                }
            }
        }
    }

    @Override
    public boolean isSource(FluidState sourceState) {
        if(sourceState.is(ThingamajigsFluids.FRYING_OIL_FLOWING.get())){
            return false;
        }
        else if(sourceState.is(ThingamajigsFluids.FRYING_OIL.get())){
            return true;
        }
        return false;
    }

    @Override
    public int getAmount(FluidState amtState) {
        if(amtState.is(ThingamajigsFluids.FRYING_OIL_FLOWING.get())){
            return amtState.getAmount();
        }
        else if(amtState.is(ThingamajigsFluids.FRYING_OIL.get())){
            return 8;
        }
        return 0;
    }

    public static class Source extends FryingOilFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends FryingOilFluid {
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }
}
