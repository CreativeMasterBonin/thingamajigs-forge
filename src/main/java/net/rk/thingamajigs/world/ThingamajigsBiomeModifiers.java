package net.rk.thingamajigs.world;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.tag.ThingamajigsTags;

import java.util.List;

public class ThingamajigsBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_RUBBER_TREE = registerKey("add_rubber_tree");
    public static final ResourceKey<BiomeModifier> ADD_WISPY_WEEDS_RIVER = registerKey("add_wispy_weeds_river");
    public static final ResourceKey<BiomeModifier> ADD_BULBLETS_JUNGLE = registerKey("add_bulblets_jungle");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_RUBBER_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeatures.getOrThrow(ThingamajigsPlacedFeatures.RUBBER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WISPY_WEEDS_RIVER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ThingamajigsTags.RIVER_PLANTS_SUPPORTED),
                HolderSet.direct(placedFeatures.getOrThrow(ThingamajigsPlacedFeatures.WISPY_WEEDS_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_BULBLETS_JUNGLE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ThingamajigsTags.CAN_SPAWN_JUNGLE_PLANTS),
                HolderSet.direct(placedFeatures.getOrThrow(ThingamajigsPlacedFeatures.BULBLET_PATCH_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Thingamajigs.MOD_ID, name));
    }
}
