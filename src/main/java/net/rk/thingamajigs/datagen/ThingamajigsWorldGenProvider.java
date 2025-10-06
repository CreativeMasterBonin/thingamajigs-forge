package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.world.ThingamajigsBiomeModifiers;
import net.rk.thingamajigs.world.ThingamajigsConfiguredFeatures;
import net.rk.thingamajigs.world.ThingamajigsPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ThingamajigsWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ThingamajigsConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ThingamajigsPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ThingamajigsBiomeModifiers::bootstrap);


    public ThingamajigsWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Thingamajigs.MOD_ID));
    }
}
