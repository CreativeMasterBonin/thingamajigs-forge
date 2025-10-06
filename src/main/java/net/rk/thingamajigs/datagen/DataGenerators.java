package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rk.thingamajigs.Thingamajigs;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Thingamajigs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //generator.addProvider(event.includeServer(), new ThingamajigsPaintingTagsProvider(packOutput,lookupProvider,existingFileHelper));

        generator.addProvider(event.includeServer(), new ThingamajigsRecipeProvider(packOutput));

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ThingamajigsLootTables::new, LootContextParamSets.BLOCK))));

        generator.addProvider(event.includeClient(), new ThingamajigsBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(true, new ThingamajigsBlockModelProvider(packOutput,existingFileHelper));
        generator.addProvider(true, new ThingamajigsItemModelProvider(packOutput,existingFileHelper));

        generator.addProvider(true,new ThingamajigsFluidTagProvider(packOutput,lookupProvider,Thingamajigs.MOD_ID,existingFileHelper));

        ThingamajigsBlockTagProvider blockTagGenerator = generator.addProvider(true,new ThingamajigsBlockTagProvider(packOutput,lookupProvider,Thingamajigs.MOD_ID,existingFileHelper));
        generator.addProvider(event.includeServer(), new ThingamajigsItemTagProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ThingamajigsBiomeTags(packOutput,lookupProvider,existingFileHelper));
        generator.addProvider(event.includeServer(), new ThingamajigsWorldGenProvider(packOutput, lookupProvider));
    }
}
