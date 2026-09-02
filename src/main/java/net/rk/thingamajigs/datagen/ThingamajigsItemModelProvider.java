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

        handheld(ThingamajigsItems.PAINT_BRUSH);
        handheld(ThingamajigsItems.WHITE_PAINT_BRUSH);
        handheld(ThingamajigsItems.YELLOW_PAINT_BRUSH);
        handheld(ThingamajigsItems.BLUE_PAINT_BRUSH);
        handheld(ThingamajigsItems.SCRAPE_TOOL);

        simple(ThingamajigsItems.MUSIC_COMPONENT);

        fromModelMod(ThingamajigsBlocks.TRIPLE_SHELF.get(),"block/triple_shelf");
        fromModelMod(ThingamajigsBlocks.TEDDY_BEAR.get(),"block/teddy_bear");
        fromModelMod(ThingamajigsBlocks.CHIMNEY.get(),"block/chimney");
        fromModelMod(ThingamajigsBlocks.GOAL.get(),"block/goal");
        fromModelMod(ThingamajigsBlocks.GLOWING_INVERTED_CATEYE_CROSSBUCK.get(),"block/glowing_cateye_crossbuck");
        fromModelMod(ThingamajigsBlocks.ORANGE_PRIVATE_PROPERTY_SIGN.get(),"block/orange_private_property");
        fromModelMod(ThingamajigsBlocks.VIDEO_IN_PROGRESS_SIGN.get(),"block/video_in_progress");
        fromModelMod(ThingamajigsBlocks.NO_STARING_PRIVATE_PROPERTY_SIGN.get(),"block/no_staring_private_property");

        simple(ThingamajigsItems.THINGAMAJIGS_PAINTING_ITEM);

        fromModelMod(ThingamajigsBlocks.PHONE_CROSSBAR.get(),"block/phone_crossbar");
        fromModelMod(ThingamajigsBlocks.STAINLESS_WASHER.get(),"block/stainless_washer");
        fromModelMod(ThingamajigsBlocks.WEIGHT_SCALE.get(),"block/weight_scale");

        rotatableLayeredBlockItemModelFromMod(ThingamajigsBlocks.ASPHALT_LAYER.get(),"asphalt");
        rotatableLayeredBlockItemModelFromMod(ThingamajigsBlocks.OK_ASPHALT_LAYER.get(),"asphalt");
        rotatableLayeredBlockItemModelFromMod(ThingamajigsBlocks.MEDIOCRE_ASPHALT_LAYER.get(),"asphalt");
        rotatableLayeredBlockItemModelFromMod(ThingamajigsBlocks.OLD_ASPHALT_LAYER.get(),"asphalt");
        fromModelMod(ThingamajigsBlocks.PHONE_GROUP_SELECTOR.get(),"block/phone_group_selector");
        fromModelMod(ThingamajigsBlocks.PHONE_AXIS_SWITCH.get(),"block/phone_axis_switch");
        fromModelMod(ThingamajigsBlocks.PHONE_AXIS_SWITCH_RELAY.get(),"block/phone_axis_switch_relay");
        fromModelMod(ThingamajigsBlocks.RUBBER_LEAVES.get(),"block/rubber_leaves");
        // 1.8.5
        fromModelMod(ThingamajigsBlocks.CAR_WASH_MIXED_BRUSH.get(),"block/car_wash_brush_mixed");
        fromModelMod(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get(),"block/car_wash_brush_red");
        fromModelMod(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get(),"block/car_wash_brush_blue");
        fromModelMod(ThingamajigsBlocks.CAR_WASH_YELLOW_BRUSH.get(),"block/car_wash_brush_yellow");
        fromModelMod(ThingamajigsBlocks.CAR_WASH_TIRE_SCRUBBER.get(),"block/tire_scrubber");
        fromModelMod(ThingamajigsBlocks.CAR_WASH_MITTER_CURTAIN.get(),"block/car_wash_mitter_curtain");

        fromModelMod(ThingamajigsBlocks.DELUXE_CAT_TREE.get(),"block/deluxe_cat_tree");
        fromModelMod(ThingamajigsBlocks.CLAW_MACHINE.get(),"block/claw_machine");
        fromModelMod(ThingamajigsBlocks.OLD_MICROWAVE_TRANSMITTER.get(),"block/old_microwave_reflector");
        fromModelMod(ThingamajigsBlocks.OLD_MICROWAVE_TRANSMITTER_OPAQUE.get(),"block/old_microwave_reflector_opaque");
        fromModelMod(ThingamajigsBlocks.OLD_MICROWAVE_REFLECTOR_ROUNDED.get(),"block/old_microwave_reflector_rounded");
        fromModelMod(ThingamajigsBlocks.OLD_MICROWAVE_REFLECTOR_ROUNDED_OPAQUE.get(),"block/old_microwave_reflector_rounded_opaque");
        fromModelMod(ThingamajigsBlocks.DECORATIONAL_BUCKET.get(),"block/decorational_bucket");
        fromModelMod(ThingamajigsBlocks.EASEL.get(),"item/easel_all");
        fromModelMod(ThingamajigsBlocks.PORTABLE_DISH_WASHER.get(),"block/portable_dishwasher");
        fromModelMod(ThingamajigsBlocks.WHITE_CUBE_SHELF.get(),"block/white_cube_shelf");
        fromModelMod(ThingamajigsBlocks.WHITE_SECTIONED_SHELF.get(),"block/white_sectioned_shelf");
        fromModelMod(ThingamajigsBlocks.RUBBER_DUCK.get(),"block/rubber_duck");
        fromModelMod(ThingamajigsBlocks.AIR_STATION.get(),"block/air_station");
        fromModelMod(ThingamajigsBlocks.SAFE.get(),"block/safe_closed");
        fromModelMod(ThingamajigsBlocks.CAKE_DISPLAY_CASE.get(),"block/cake_display_case_empty");
        fromModelMod(ThingamajigsBlocks.CELL_TOWER_AMPLIFIER.get(),"block/cell_tower_amplifier");
        fromModelMod(ThingamajigsBlocks.FANCY_GAS_PUMP.get(),"block/fancy_gas_pump");
        fromModelMod(ThingamajigsBlocks.DELUXE_ARCADE_MACHINE.get(),"item/deluxe_arcade_machine_held");
        fromModelMod(ThingamajigsBlocks.CEILING_FAN.get(),"block/ceiling_fan");
        fromModelMod(ThingamajigsBlocks.DAUNTING_STATUE.get(),"block/statue/daunting_statue");
        fromModelMod(ThingamajigsBlocks.BLUEYDOWS_LAPTOP.get(),"block/laptop/blueydows_laptop");
        fromModelMod(ThingamajigsBlocks.BLUEYTOSH_LAPTOP.get(),"block/laptop/blueytosh_laptop");
        fromModelMod(ThingamajigsBlocks.BLUEYTOSH_LAPTOP_OLD.get(),"block/laptop/blueytosh_laptop_old");

        fromModelMod(ThingamajigsBlocks.TOILET.get(),"block/toilets/toilet_full");

        // 1.8.6-1.8.7
        fromModelMod(ThingamajigsBlocks.TUBE_MAN_DECO.get(),"bases/tube_man_components/tube_man_base_compressed");
        fromModelMod(ThingamajigsBlocks.GRAB_BAR.get(),"block/grab_bar/hand_bar_unconnected");
        fromModelMod(ThingamajigsBlocks.URINAL.get(),"block/toilets/urinal");
        fromModelMod(ThingamajigsBlocks.BLUEYBOX.get(),"block/blueybox");
        // 1.8.9
        fromModelMod(ThingamajigsBlocks.HOSPITAL_BED.get(),"block/hospital_bed");
        fromModelMod(ThingamajigsBlocks.MINIGOLF_FLAG.get(),"block/minigolf_flags/red_minigolf_flag");
        fromModelMod(ThingamajigsBlocks.ELECTRIC_HOSPITAL_BED.get(),"block/electric_hospital_bed");
        fromModelMod(ThingamajigsBlocks.PHONE_STEP_SELECTORS_GROUP.get(),"block/phone_step_selectors_group");
        // 1.9.0
        fromModelMod(ThingamajigsBlocks.STOP_GATE.get(),"block/stop_gate_off");
        fromModelMod(ThingamajigsBlocks.OLD_LEGENDARY_COMPUTER.get(),"block/old_legendary_computer");
        handheld(ThingamajigsItems.CUSTOMIZATION_TOOL);
        handheld(ThingamajigsItems.RENDERING_TOOL);
    }

    private ItemModelBuilder fromModelModItem(Item item, String source){
        return withExistingParent(item.toString(),
                new ResourceLocation("thingamajigs",source));
    }

    // flat 2D facing player model
    private ItemModelBuilder defaultSimple(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("minecraft","item/barrier"));
    }

    // exclusively for use with layered rotatable block items
    private ItemModelBuilder rotatableLayeredBlockItemModelFromMod(Block layeredBlock,String subfolder){
        String source = layeredBlock.asItem().getDescriptionId().replaceAll("block.thingamajigs.","");
        return withExistingParent(layeredBlock.asItem().toString(),
                new ResourceLocation("thingamajigs","block/layer/" + subfolder + "/" + source + "_2"));
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
