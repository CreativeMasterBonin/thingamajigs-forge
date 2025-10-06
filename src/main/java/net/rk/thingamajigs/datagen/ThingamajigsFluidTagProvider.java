package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.fluid.ThingamajigsFluids;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ThingamajigsFluidTagProvider extends FluidTagsProvider {
    public ThingamajigsFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> cfhp, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, cfhp, modId, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider hlp) {
        this.tag(ThingamajigsTags.SLUDGE_TAG).add(ThingamajigsFluids.SLUDGE.get(), ThingamajigsFluids.SLUDGE_FLOWING.get());
        this.tag(ThingamajigsTags.PURIFIED_WATER_TAG).add(ThingamajigsFluids.PURIFYING_WATER.get(), ThingamajigsFluids.PURIFYING_WATER_FLOWING.get());
    }
}
