package net.rk.thingamajigs.fluid;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.fluid.types.PurifyingWaterFluidType;
import net.rk.thingamajigs.fluid.types.SludgeFluidType;

public class ThingamajigsFluids {
   // Combined fluids and fluidtypes setup so all bugs are in one class
   public static final DeferredRegister<FluidType> FLUID_TYPES =
           DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES,
                   Thingamajigs.MOD_ID); // registerer for fluid_types (for usage and properties setup)

   public static final DeferredRegister<Fluid> FLUIDS =
           DeferredRegister.create(ForgeRegistries.FLUIDS,
                   Thingamajigs.MOD_ID); // registerer for forge fluids (all the fancy effects and such)

   // fluid type setup
   public static final RegistryObject<FluidType> SLUDGE_TYPE =
           FLUID_TYPES.register("sludge",
                   () -> new SludgeFluidType(FluidType.Properties.create()));

   public static final RegistryObject<FluidType> PURIFYING_WATER_TYPE =
           FLUID_TYPES.register("purifying_water",
                   () -> new PurifyingWaterFluidType(FluidType.Properties.create()));

   // fluid setup
   public static final RegistryObject<FlowingFluid> SLUDGE = FLUIDS.register("sludge", SludgeFluid.Source::new);
   public static final RegistryObject<FlowingFluid> SLUDGE_FLOWING = FLUIDS.register("flowing_sludge", SludgeFluid.Flowing::new);

   public static final RegistryObject<FlowingFluid> PURIFYING_WATER = FLUIDS.register("purifying_water", PurifyingWaterFluid.Source::new);
   public static final RegistryObject<FlowingFluid> PURIFYING_WATER_FLOWING = FLUIDS.register("flowing_purifying_water", PurifyingWaterFluid.Flowing::new);
}
