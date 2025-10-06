package net.rk.thingamajigs.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;

import java.util.List;

public class ThingamajigsPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RUBBER_PLACED_KEY = registerKey("rubber_placed");
    public static final ResourceKey<PlacedFeature> WISPY_WEEDS_KEY = registerKey("wispy_weeds_patch");
    public static final ResourceKey<PlacedFeature> BULBLET_PATCH_KEY = registerKey("bulblets_patch");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RUBBER_PLACED_KEY, configuredFeatures.getOrThrow(ThingamajigsConfiguredFeatures.RUBBER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1),
                        ThingamajigsBlocks.RUBBER_SAPLING.get()));

        context.register(WISPY_WEEDS_KEY, new PlacedFeature(configuredFeatures.getOrThrow(ThingamajigsConfiguredFeatures.WISPY_WEEDS_FEATURE),
                VegetationPlacements.worldSurfaceSquaredWithCount(2)));

        context.register(BULBLET_PATCH_KEY, new PlacedFeature(configuredFeatures.getOrThrow(ThingamajigsConfiguredFeatures.BULBLETS_PATCH_FEATURE),
                VegetationPlacements.worldSurfaceSquaredWithCount(1)));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Thingamajigs.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
