package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.BowlingBall;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ThingamajigsItemTagProvider extends ItemTagsProvider {

    public ThingamajigsItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> cprovider,
                               CompletableFuture<TagLookup<Block>> lookupblock, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, cprovider, lookupblock, Thingamajigs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ThingamajigsTags.RUBBER_LOGS_ITEM)
                .add(ThingamajigsBlocks.RUBBER_LOG.get().asItem())
                .add(ThingamajigsBlocks.RUBBER_WOOD.get().asItem())
                .add(ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get().asItem())
                .add(ThingamajigsBlocks.STRIPPED_RUBBER_WOOD.get().asItem());

        // asphalt crafting ingredients
        this.tag(ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS)
                .add(ThingamajigsBlocks.ASPHALT.get().asItem())
                .add(ThingamajigsBlocks.ASPHALT_MEDIOCRE.get().asItem())
                .add(ThingamajigsBlocks.ASPHALT_OK.get().asItem())
                .add(ThingamajigsBlocks.ASPHALT_OLD.get().asItem())
                .replace(false)
        ;

        this.tag(ThingamajigsTags.CHAIRS_ITEM)
                .add(ThingamajigsBlocks.STONE_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.GOLD_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.QUARTZ_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.NETHER_BRICK_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.PRISMARINE_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.PURPUR_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.SCULK_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.POOP_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.DIAMOND_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.IRON_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.WAXED_COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_CHAIR.get().asItem())
                .add(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get().asItem())
                .replace(false)
        ;

        // nf supported tags
        this.tag(ThingamajigsTags.TREE_RESIN_TAG)
                .add(ThingamajigsItems.TREE_RESIN.get())
                .replace(false)
        ;

        this.tag(ThingamajigsTags.RUBBER_TAG)
                .add(ThingamajigsItems.RUBBER.get())
                .replace(false)
        ;

        // vanilla tags
        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ThingamajigsTags.RUBBER_LOGS_ITEM)
                .replace(false)
        ;

        this.tag(ItemTags.PLANKS)
                .add(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem())
                .replace(false)
        ;

        this.tag(ItemTags.TOOLS)
                .add(ThingamajigsItems.PAINT_BRUSH.get())
                .add(ThingamajigsItems.WHITE_PAINT_BRUSH.get())
                .add(ThingamajigsItems.YELLOW_PAINT_BRUSH.get())
                .add(ThingamajigsItems.BLUE_PAINT_BRUSH.get())
                .replace(false)
        ;

        this.tag(ThingamajigsTags.TABLES_ITEM)
                .add(ThingamajigsBlocks.STONE_TABLE.get().asItem())
                .add(ThingamajigsBlocks.SCULK_TABLE.get().asItem())
                .add(ThingamajigsBlocks.QUARTZ_TABLE.get().asItem())
                .add(ThingamajigsBlocks.PURPUR_TABLE.get().asItem())
                .add(ThingamajigsBlocks.PRISMARINE_TABLE.get().asItem())
                .add(ThingamajigsBlocks.NETHER_BRICK_TABLE.get().asItem())
                .add(ThingamajigsBlocks.GOLD_TABLE.get().asItem())
                .add(ThingamajigsBlocks.IRON_TABLE.get().asItem())
                .add(ThingamajigsBlocks.DIAMOND_TABLE.get().asItem())
                .add(ThingamajigsBlocks.WAXED_COPPER_TABLE.get().asItem())
                .add(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_TABLE.get().asItem())
                .add(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_TABLE.get().asItem())
                .add(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().asItem())
                .replace(false)
        ;
        this.tag(ThingamajigsTags.ARCADE_ITEMS)
                .add(ThingamajigsBlocks.LIGHTUP_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.BASKETBALL_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.HAMMER_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.SPHERES_AND_RINGS_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.PINBALL_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.LIGHTUP_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.ARCADE_MACHINE.get().asItem())
                .add(ThingamajigsBlocks.ARCADE_MACHINE_OPENABLE.get().asItem())
                .replace(false)
        ;
        this.tag(ThingamajigsTags.BOWLING_BALLS)
                .add(ThingamajigsBlocks.PINK_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.PURPLE_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.BROWN_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.YELLOW_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.BLUE_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.LIGHT_BLUE_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.GREEN_BOWLING_BALL.get().asItem())
                .add(ThingamajigsBlocks.LIME_BOWLING_BALL.get().asItem())
                .replace(false)
        ;
    }
}
