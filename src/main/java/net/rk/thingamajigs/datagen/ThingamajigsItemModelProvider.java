package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class ThingamajigsItemModelProvider extends ItemModelProvider {
    public ThingamajigsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, Thingamajigs.MOD_ID, existingFileHelper);
    }

    // put the types of models to be generated for what items here
    // must use a valid constructor for models defined here (will translate into JSON)
    @Override
    protected void registerModels() {
        // map maker only item makers (block items)
        blockAll(ThingamajigsItems.WATER_SOURCE, "block/water_still");
        blockAll(ThingamajigsItems.NP_PLACEABLE, "block/nether_portal");
        blockAll(ThingamajigsItems.EP_PLACEABLE, "item/ender_eye");
        blockAll(ThingamajigsItems.EG_PLACEABLE, "item/ender_pearl");
        // standard item models
        defaultCustomSimple(ThingamajigsBlocks.SLUDGE.get(),"sludge_still");
        defaultCustomSimple(ThingamajigsBlocks.PURIFYING_WATER.get(),"purifying_water_still");
        // item handhelds
        customHandheld(ThingamajigsItems.RED_LANTERN_ITEM,"block/torches/red_lantern");
        customHandheld(ThingamajigsItems.PAPER_LANTERN_ITEM,"block/torches/paper_lantern");
        // other block items
        defaultCustomSimple(ThingamajigsBlocks.RED_LANTERN.get(),"torches/red_lantern");
        defaultCustomSimple(ThingamajigsBlocks.WALL_RED_LANTERN.get(),"torches/red_lantern");
        defaultCustomSimple(ThingamajigsBlocks.PAPER_LANTERN.get(),"torches/paper_lantern");
        defaultCustomSimple(ThingamajigsBlocks.WALL_PAPER_LANTERN.get(),"torches/paper_lantern");
        // real lantern block items
        defaultCustomSimpleItem(ThingamajigsBlocks.SCULK_LANTERN.get(),"sculk_lantern");
        defaultCustomSimpleItem(ThingamajigsBlocks.SCULK_CHAIN.get(),"sculk_chain");
        //
        simple(ThingamajigsItems.THINGAMAJIG_GLOB);
        simple(ThingamajigsItems.SIGN_GLOB);
        simple(ThingamajigsItems.DOOR_GLOB);
        simple(ThingamajigsItems.GLOB_SANDWICH);

        simple(ThingamajigsItems.WHITE_PUMPKIN_SEEDS);

        simple(ThingamajigsItems.LIGHT_GRAY_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.GRAY_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.BLACK_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.BROWN_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.RED_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.YELLOW_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.LIME_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.GREEN_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.CYAN_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.LIGHT_BLUE_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.BLUE_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.PURPLE_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.MAGENTA_PUMPKIN_SEEDS);
        simple(ThingamajigsItems.PINK_PUMPKIN_SEEDS);

        // pumpkin blocks
        fromModel(ThingamajigsBlocks.GRAY_PUMPKIN.get(),"thingamajigs:block/gray_pumpkin");
        fromModel(ThingamajigsBlocks.GRAY_CARVED_PUMPKIN.get(),"thingamajigs:block/gray_carved_pumpkin");

        fromModel(ThingamajigsBlocks.BLACK_PUMPKIN.get(),"thingamajigs:block/black_pumpkin");
        fromModel(ThingamajigsBlocks.BLACK_CARVED_PUMPKIN.get(),"thingamajigs:block/black_carved_pumpkin");

        fromModel(ThingamajigsBlocks.BROWN_PUMPKIN.get(),"thingamajigs:block/brown_pumpkin");
        fromModel(ThingamajigsBlocks.BROWN_CARVED_PUMPKIN.get(),"thingamajigs:block/brown_carved_pumpkin");

        fromModel(ThingamajigsBlocks.RED_PUMPKIN.get(),"thingamajigs:block/red_pumpkin");
        fromModel(ThingamajigsBlocks.RED_CARVED_PUMPKIN.get(),"thingamajigs:block/red_carved_pumpkin");

        fromModel(ThingamajigsBlocks.YELLOW_PUMPKIN.get(),"thingamajigs:block/yellow_pumpkin");
        fromModel(ThingamajigsBlocks.YELLOW_CARVED_PUMPKIN.get(),"thingamajigs:block/yellow_carved_pumpkin");

        fromModel(ThingamajigsBlocks.LIME_PUMPKIN.get(),"thingamajigs:block/lime_pumpkin");
        fromModel(ThingamajigsBlocks.LIME_CARVED_PUMPKIN.get(),"thingamajigs:block/lime_carved_pumpkin");

        fromModel(ThingamajigsBlocks.GREEN_PUMPKIN.get(),"thingamajigs:block/green_pumpkin");
        fromModel(ThingamajigsBlocks.GREEN_CARVED_PUMPKIN.get(),"thingamajigs:block/green_carved_pumpkin");

        fromModel(ThingamajigsBlocks.CYAN_PUMPKIN.get(),"thingamajigs:block/cyan_pumpkin");
        fromModel(ThingamajigsBlocks.CYAN_CARVED_PUMPKIN.get(),"thingamajigs:block/cyan_carved_pumpkin");

        fromModel(ThingamajigsBlocks.LIGHT_BLUE_PUMPKIN.get(),"thingamajigs:block/light_blue_pumpkin");
        fromModel(ThingamajigsBlocks.LIGHT_BLUE_CARVED_PUMPKIN.get(),"thingamajigs:block/light_blue_carved_pumpkin");

        fromModel(ThingamajigsBlocks.BLUE_PUMPKIN.get(),"thingamajigs:block/blue_pumpkin");
        fromModel(ThingamajigsBlocks.BLUE_CARVED_PUMPKIN.get(),"thingamajigs:block/blue_carved_pumpkin");

        fromModel(ThingamajigsBlocks.PURPLE_PUMPKIN.get(),"thingamajigs:block/purple_pumpkin");
        fromModel(ThingamajigsBlocks.PURPLE_CARVED_PUMPKIN.get(),"thingamajigs:block/purple_carved_pumpkin");

        fromModel(ThingamajigsBlocks.MAGENTA_PUMPKIN.get(),"thingamajigs:block/magenta_pumpkin");
        fromModel(ThingamajigsBlocks.MAGENTA_CARVED_PUMPKIN.get(),"thingamajigs:block/magenta_carved_pumpkin");

        fromModel(ThingamajigsBlocks.PINK_PUMPKIN.get(),"thingamajigs:block/pink_pumpkin");
        fromModel(ThingamajigsBlocks.PINK_CARVED_PUMPKIN.get(),"thingamajigs:block/pink_carved_pumpkin");

        // jack o lanterns

        fromModel(ThingamajigsBlocks.WHITE_JOL.get(),"thingamajigs:block/pumpkins/white_jol");
        fromModel(ThingamajigsBlocks.LIGHT_GRAY_JOL.get(),"thingamajigs:block/pumpkins/light_gray_jol");
        fromModel(ThingamajigsBlocks.GRAY_JOL.get(),"thingamajigs:block/pumpkins/gray_jol");
        fromModel(ThingamajigsBlocks.BLACK_JOL.get(),"thingamajigs:block/pumpkins/black_jol");
        fromModel(ThingamajigsBlocks.BROWN_JOL.get(),"thingamajigs:block/pumpkins/brown_jol");
        fromModel(ThingamajigsBlocks.RED_JOL.get(),"thingamajigs:block/pumpkins/red_jol");
        fromModel(ThingamajigsBlocks.YELLOW_JOL.get(),"thingamajigs:block/pumpkins/yellow_jol");
        fromModel(ThingamajigsBlocks.LIME_JOL.get(),"thingamajigs:block/pumpkins/lime_jol");
        fromModel(ThingamajigsBlocks.GREEN_JOL.get(),"thingamajigs:block/pumpkins/green_jol");
        fromModel(ThingamajigsBlocks.CYAN_JOL.get(),"thingamajigs:block/pumpkins/cyan_jol");
        fromModel(ThingamajigsBlocks.LIGHT_BLUE_JOL.get(),"thingamajigs:block/pumpkins/light_blue_jol");
        fromModel(ThingamajigsBlocks.BLUE_JOL.get(),"thingamajigs:block/pumpkins/blue_jol");
        fromModel(ThingamajigsBlocks.PURPLE_JOL.get(),"thingamajigs:block/pumpkins/purple_jol");
        fromModel(ThingamajigsBlocks.MAGENTA_JOL.get(),"thingamajigs:block/pumpkins/magenta_jol");
        fromModel(ThingamajigsBlocks.PINK_JOL.get(),"thingamajigs:block/pumpkins/pink_jol");

        // currency models
        simple(ThingamajigsItems.COIN);
        simple(ThingamajigsItems.MONEY);
        simple(ThingamajigsItems.DEBIT_CARD);

        simpleCustomBlock(ThingamajigsBlocks.BRAMBLE,"block/plants/bramble");
        defaultCustomSimple(ThingamajigsBlocks.POTTED_BRAMBLE.get(),"plants/bramble");

        handheld(ThingamajigsItems.SCRAPE_TOOL);

        //simpleCustomBlock(ThingamajigsBlocks.CURVED_MONITOR,"item/curved_monitor");
        //simpleCustomBlock(ThingamajigsBlocks.CLEVER_BLACKBOARD,"item/clever_blackboard");
        //simpleCustomBlock(ThingamajigsBlocks.UMBRELLA,"item/umbrella");

        simple(ThingamajigsItems.MUSIC_COMPONENT);
        //simpleCustomBlock(ThingamajigsBlocks.THEATER_PROJECTOR,"item/theater_projector");

        fromModelMod(ThingamajigsBlocks.TRIPLE_SHELF.get(),"block/triple_shelf");
        fromModelMod(ThingamajigsBlocks.TEDDY_BEAR.get(),"block/teddy_bear");
        fromModelMod(ThingamajigsBlocks.CHIMNEY.get(),"block/chimney");
        fromModelMod(ThingamajigsBlocks.GOAL.get(),"block/goal");
        fromModelMod(ThingamajigsBlocks.GLOWING_INVERTED_CATEYE_CROSSBUCK.get(),"block/glowing_cateye_crossbuck");
        fromModelMod(ThingamajigsBlocks.ORANGE_PRIVATE_PROPERTY_SIGN.get(),"block/orange_private_property");
        fromModelMod(ThingamajigsBlocks.VIDEO_IN_PROGRESS_SIGN.get(),"block/video_in_progress");
        fromModelMod(ThingamajigsBlocks.NO_STARING_PRIVATE_PROPERTY_SIGN.get(),"block/no_staring_private_property");

        simple(ThingamajigsItems.THINGAMAJIGS_PAINTING_ITEM);
    }

    // flat 2D facing player model

    private ItemModelBuilder defaultSimple(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("minecraft","item/barrier"));
    }

    // flat 2d custom facing player model
    private ItemModelBuilder defaultCustomSimple(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("thingamajigs","block/" + source));
    }

    private ItemModelBuilder fromModel(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                new ResourceLocation(source));
    }

    private ItemModelBuilder fromModelMod(Block block2, String source){
        return withExistingParent(block2.asItem().toString(),
                new ResourceLocation("thingamajigs",source));
    }

    private ItemModelBuilder defaultCustomSimpleItem(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("thingamajigs","item/" + source));
    }

    // cube_all model with custom texture
    private ItemModelBuilder blockAll(RegistryObject<Item> item, String textureLocation){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("block/cube_all")).texture("all",
                new ResourceLocation(textureLocation));
    }

    private ItemModelBuilder blockItemModelAll(String blockName, String textureLocation){
        return withExistingParent(blockName,
                new ResourceLocation("thingamajigs:block/" + blockName)).texture("all",
                new ResourceLocation(textureLocation));
    }

    // copy block model from existing directory (model must be valid)
    private ItemModelBuilder blockSimple(RegistryObject<Item> item, String blockModelPath){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(blockModelPath));
    }

    // flat 2D simple item model using texture with same name as item (texture must exist to work)
    private ItemModelBuilder simple(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Thingamajigs.MOD_ID,"item/" + item.getId().getPath()));
    }

    // vanilla tool model (fishing rods, swords, pickaxes, etc.)
    private ItemModelBuilder handheld(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Thingamajigs.MOD_ID,"item/" + item.getId().getPath()));
    }

    // custom simple tool model
    private ItemModelBuilder customHandheld(RegistryObject<Item> item, String directory){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Thingamajigs.MOD_ID, directory));
    }


    private ItemModelBuilder simpleCustomBlock(RegistryObject<Block> block, String fullPathNoModID){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Thingamajigs.MOD_ID,fullPathNoModID));
    }

    private ItemModelBuilder itemFromModel(Item item, String source){
        return withExistingParent(item.toString(),
                new ResourceLocation(source));
    }

}
