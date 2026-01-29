package net.rk.thingamajigs.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.rk.thingamajigs.Thingamajigs;

public class ThingamajigsTags {
    public static final TagKey<Fluid> PURIFIED_WATER_TAG = thingamajigsFluidTag("purified_water");
    public static final TagKey<Fluid> SLUDGE_TAG = thingamajigsFluidTag("sludge");
    public static final TagKey<Block> ASPHALT = thingamajigsBlockTag("asphalt");
    public static final TagKey<Item> ASPHALT_CRAFTING_INGREDIENTS = thingamajigsItemTag("asphalt_crafting_ingredients");
    public static final TagKey<Block> BALLOON_BLOCKS = thingamajigsBlockTag("balloon_blocks");
    public static final TagKey<Block> VERTICAL_REDSTONE_BLOCKS = thingamajigsBlockTag("vertical_redstone_blocks");
    public static final TagKey<Block> RAILROAD_CROSSING_BELLS = thingamajigsBlockTag("railroad_crossing_bells");
    public static final TagKey<Block> SUPPORTS_BUSH_LIKE = thingamajigsBlockTag("supports_bush_like");
    public static final TagKey<Item> ARCADE_ITEMS = thingamajigsItemTag("arcade_items");
    public static final TagKey<PaintingVariant> THINGAMAJIGS_PAINTING = thingamajigsPaintingVariantTag("thingamajigs_painting");

    // 1.7.7 tags
    public static final TagKey<Item> TABLES_ITEM = thingamajigsItemTag("tables");
    //public static final TagKey<Block> POLES = thingamajigsBlockTag("poles");

    public static final TagKey<Block> RUBBER_LOGS = thingamajigsBlockTag("rubber_logs");
    public static final TagKey<Item> RUBBER_LOGS_ITEM = thingamajigsItemTag("rubber_logs");
    //public static final TagKey<Item> POLES_ITEM = thingamajigsItemTag("poles");

    public static final TagKey<Block> DYED_PUMPKIN_STEMS = thingamajigsBlockTag("dyed_pumpkin_stems");

    // 1.8.1
    public static final TagKey<Biome> SPAWNS_RUBBER_TREES = thingamajigsBiomeTag("spawns_rubber_trees");
    public static final TagKey<Biome> RIVER_PLANTS_SUPPORTED = thingamajigsBiomeTag("river_plants_supported");
    public static final TagKey<Biome> CAN_SPAWN_JUNGLE_PLANTS = thingamajigsBiomeTag("can_spawn_jungle_plants");

    // forge tags
    public static final TagKey<Item> RUBBER_TAG = forgeItemTag("rubber");
    public static final TagKey<Item> TREE_RESIN_TAG = forgeItemTag("tree_resin");

    public static final TagKey<Block> CHAIRS = thingamajigsBlockTag("chairs");
    public static final TagKey<Item> CHAIRS_ITEM = thingamajigsItemTag("chairs");
    public static final TagKey<Block> TABLES = thingamajigsBlockTag("tables");
    public static final TagKey<Block> ELECTRICAL_OUTLETS = thingamajigsBlockTag("electrical_outlets");

    private static TagKey<Fluid> thingamajigsFluidTag(String name){
        return FluidTags.create(new ResourceLocation("thingamajigs", name));
    }

    private static TagKey<Block> thingamajigsBlockTag(String name){
        return BlockTags.create(new ResourceLocation("thingamajigs", name));
    }

    private static TagKey<Item> thingamajigsItemTag(String name){
        return ItemTags.create(new ResourceLocation("thingamajigs", name));
    }

    public static TagKey<Biome> thingamajigsBiomeTag(String name){
        return TagKey.create(Registries.BIOME, new ResourceLocation(Thingamajigs.MOD_ID,name));
    }

    public static TagKey<PaintingVariant> thingamajigsPaintingVariantTag(String name){
        return TagKey.create(Registries.PAINTING_VARIANT, new ResourceLocation(Thingamajigs.MOD_ID,name));
    }

    // default tag registry

    private static TagKey<Block> mcBlockTag(String name){
        return BlockTags.create(new ResourceLocation(name));
    }

    private static TagKey<Item> mcItemTag(String name){
        return ItemTags.create(new ResourceLocation(name));
    }

    private static TagKey<Fluid> mcFluidTag(String name){
        return FluidTags.create(new ResourceLocation(name));
    }

    // forge tag registry

    private static TagKey<Fluid> forgeFluidTag(String name){
        return FluidTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Block> forgeBlockTag(String name){
        return BlockTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Item> forgeItemTag(String name){
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
