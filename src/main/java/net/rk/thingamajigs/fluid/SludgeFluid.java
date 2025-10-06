package net.rk.thingamajigs.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
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

public class SludgeFluid extends ForgeFlowingFluid {
    public static final ForgeFlowingFluid.Properties SLUDGE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> ThingamajigsFluids.SLUDGE_TYPE.get(),
            () -> ThingamajigsFluids.SLUDGE.get(),
            () -> ThingamajigsFluids.SLUDGE_FLOWING.get())
            .explosionResistance(100f)
            .levelDecreasePerBlock(3)
            .slopeFindDistance(3)
            .bucket(() -> ThingamajigsItems.SLUDGE_BUCKET.get())
            .block(() -> (LiquidBlock) ThingamajigsBlocks.SLUDGE.get());

    // do not change this to 'public'
    private SludgeFluid() {
        super(SLUDGE_PROPERTIES);
    }

    @Override
    public void animateTick(Level level, BlockPos bp, FluidState flst, RandomSource rnds) {
        if (!flst.isSource() && !flst.getValue(FALLING)) {
            if (rnds.nextInt(72) == 0) {
                level.playLocalSound(
                        (double)bp.getX() + 0.5D,
                        (double)bp.getY() + 0.5D,
                        (double)bp.getZ() + 0.5D,
                        SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT,
                        SoundSource.BLOCKS,
                        rnds.nextFloat() * 0.25F + 0.75F,
                        rnds.nextFloat() + 0.5F, false);
            }
        }
        else if (rnds.nextInt(97) == 0) {
            level.addParticle(ParticleTypes.MYCELIUM,
                    (double)bp.getX() + rnds.nextDouble(),
                    (double)bp.getY() + rnds.nextDouble(),
                    (double)bp.getZ() + rnds.nextDouble(),
                    0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean isSource(FluidState sourceState) {
        if(sourceState.is(ThingamajigsFluids.SLUDGE_FLOWING.get())){
            return false;
        }
        else if(sourceState.is(ThingamajigsFluids.SLUDGE.get())){
            return true;
        }
        return false;
    }

    @Override
    public int getAmount(FluidState amtState) {
        if(amtState.is(ThingamajigsFluids.SLUDGE_FLOWING.get())){
            return amtState.getAmount();
        }
        else if(amtState.is(ThingamajigsFluids.SLUDGE.get())){
            return 8;
        }
        return 0;
    }

    public static class Source extends SludgeFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends SludgeFluid {
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
