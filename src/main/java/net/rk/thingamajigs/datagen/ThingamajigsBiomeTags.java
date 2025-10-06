package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ThingamajigsBiomeTags extends BiomeTagsProvider {
    public ThingamajigsBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Thingamajigs.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Thingamajigs Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider p) {
        this.tag(ThingamajigsTags.SPAWNS_RUBBER_TREES)
                .add(Biomes.SPARSE_JUNGLE)
        ;
        this.tag(ThingamajigsTags.RIVER_PLANTS_SUPPORTED)
                .add(Biomes.RIVER)
        ;
        this.tag(ThingamajigsTags.CAN_SPAWN_JUNGLE_PLANTS)
                .add(Biomes.JUNGLE)
                .add(Biomes.SPARSE_JUNGLE)
                .add(Biomes.BAMBOO_JUNGLE)
        ;
    }
}
