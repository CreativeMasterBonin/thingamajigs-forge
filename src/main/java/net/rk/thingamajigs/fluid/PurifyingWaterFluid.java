package net.rk.thingamajigs.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class PurifyingWaterFluid extends ForgeFlowingFluid {
    public static final ForgeFlowingFluid.Properties PURIFYING_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> ThingamajigsFluids.PURIFYING_WATER_TYPE.get(),
            () -> ThingamajigsFluids.PURIFYING_WATER.get(),
            () -> ThingamajigsFluids.PURIFYING_WATER_FLOWING.get())
            .explosionResistance(100f)
            .levelDecreasePerBlock(1)
            .slopeFindDistance(5)
            .bucket(() -> ThingamajigsItems.PURIFYING_WATER_BUCKET.get())
            .block(() -> (LiquidBlock) ThingamajigsBlocks.PURIFYING_WATER.get());

    private PurifyingWaterFluid() {
        super(PURIFYING_WATER_PROPERTIES);
    }

    @Override
    public void animateTick(Level level, BlockPos bp, FluidState flst, RandomSource rnds) {
        if (!flst.isSource() && !flst.getValue(FALLING)) {
            if (rnds.nextInt(64) == 0) {
                level.playLocalSound(
                        (double)bp.getX() + 0.5D,
                        (double)bp.getY() + 0.5D,
                        (double)bp.getZ() + 0.5D,
                        SoundEvents.WATER_AMBIENT,
                        SoundSource.BLOCKS,
                        rnds.nextFloat() * 0.25F + 0.75F,
                        rnds.nextFloat() + 0.5F, false);
            }
        }
        else if (rnds.nextInt(94) == 0) {
            level.addParticle(ParticleTypes.CLOUD,
                    (double)bp.getX() + rnds.nextDouble(),
                    (double)bp.getY() + rnds.nextDouble(),
                    (double)bp.getZ() + rnds.nextDouble(),
                    0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean isSource(FluidState sourceState) {
        if(sourceState.is(ThingamajigsFluids.PURIFYING_WATER_FLOWING.get())){
            return false;
        }
        else if(sourceState.is(ThingamajigsFluids.PURIFYING_WATER.get())){
            return true;
        }
        return false;
    }

    @Override
    public int getAmount(FluidState amtState) {
        if(amtState.is(ThingamajigsFluids.PURIFYING_WATER_FLOWING.get())){
            return amtState.getAmount();
        }
        else if(amtState.is(ThingamajigsFluids.PURIFYING_WATER.get())){
            return 8;
        }
        return 0;
    }

    public static class Source extends PurifyingWaterFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends PurifyingWaterFluid {
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
