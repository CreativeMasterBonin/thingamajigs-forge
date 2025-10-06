package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import org.jetbrains.annotations.NotNull;

public class ThingamajigsBlockModelProvider extends BlockModelProvider {
    // global texture references for all models
    public static final ResourceLocation POLE_TEXTURE = new ResourceLocation("minecraft:block/blast_furnace_side");

    // the generator for future blocks that only need to use a base model for reference instead of a completely new model
    public ThingamajigsBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Thingamajigs.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        signModelBuilder(ThingamajigsBlocks.CANADIAN_CROSSBUCK, "symbol_signs", "canadian_crossbuck","back_rr_crossing");
        signModelBuilder(ThingamajigsBlocks.JAPAN_CROSSBUCK, "international", "japan_rrc","international/back_jrrc");
        signModelBuilder(ThingamajigsBlocks.CATEYE_CROSSBUCK, "text_signs", "cateye_railroad_crossing","back_rr_crossing");
        signModelBuilder(ThingamajigsBlocks.INVERTED_CATEYE_CROSSBUCK, "text_signs", "inverted_cateye_railroad_crossing","back_rr_crossing");
        // torches
        standingTorchModelBuilder(ThingamajigsBlocks.RED_LANTERN,"thingamajigs:block/torches/red_lantern");
        wallTorchModelBuilder(ThingamajigsBlocks.WALL_RED_LANTERN,"thingamajigs:block/torches/red_lantern");
        standingTorchModelBuilder(ThingamajigsBlocks.PAPER_LANTERN,"thingamajigs:block/torches/paper_lantern");
        wallTorchModelBuilder(ThingamajigsBlocks.WALL_PAPER_LANTERN,"thingamajigs:block/torches/paper_lantern");
        // lanterns (should be 2 for each block)
        lanternModelBuilder(ThingamajigsBlocks.SCULK_LANTERN,"thingamajigs:block/lanterns/sculk_lantern");
        hangingLanternModelBuilder(ThingamajigsBlocks.SCULK_LANTERN,"thingamajigs:block/lanterns/sculk_lantern");
        customChainModelBuilder(ThingamajigsBlocks.SCULK_CHAIN,"thingamajigs:block/chains/sculk_chain");
        // all sided blocks
        allSidedBlock(ThingamajigsBlocks.SPOOKY_STONE,"thingamajigs:block/stone/spooky_stone");
        allSidedBlock(ThingamajigsBlocks.BLUEBERRY_STONE,"thingamajigs:block/stone/blueberry_stone");
        allSidedBlock(ThingamajigsBlocks.NETHERISH_STONE,"thingamajigs:block/stone/netherish_stone");
        allSidedBlock(ThingamajigsBlocks.VOLCANIC_STONE,"thingamajigs:block/stone/volcanic_stone");
        allSidedBlock(ThingamajigsBlocks.CHARGED_VOLCANIC_STONE,"thingamajigs:block/stone/charged_volcanic_stone");
        // uk signs
        signModelBuilder(ThingamajigsBlocks.ASCENT, "uk_signs/triangle", "ascent","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.DESCENT, "uk_signs/triangle", "descent","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.CHILDREN, "uk_signs/triangle", "children","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.CROSSING_NO_GATES, "uk_signs/triangle", "crossing_no_gates","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.DANGER, "uk_signs/triangle", "danger","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.DUAL_ENDS, "uk_signs/triangle", "dual_ends","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.ELDER, "uk_signs/triangle", "elder","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.GATED_CROSSING, "uk_signs/triangle", "gated_crossing","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.GIVE_WAY, "uk_signs/triangle", "giveway","back_uk_inverted_triangle");
        signModelBuilder(ThingamajigsBlocks.HUMP_BRIDGE, "uk_signs/triangle", "hump_bridge","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.MINECARTS, "uk_signs/triangle", "minecarts","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.NARROW_BOTH, "uk_signs/triangle", "narrow_both","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.OPEN_BRIDGE, "uk_signs/triangle", "open_bridge","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.PEDS_AHEAD, "uk_signs/triangle", "peds_ahead","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.RIVERBANK, "uk_signs/triangle", "riverbank","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.ROAD_WORK, "uk_signs/triangle", "road_work","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.ROUNDABOUT_UK, "uk_signs/triangle", "roundabout","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.SIDE_WIND, "uk_signs/triangle", "side_wind","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.SIGNAL_AHEAD, "uk_signs/triangle", "signal_ahead","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.SLIPPERY, "uk_signs/triangle", "slippery","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.SOFT_VERGE, "uk_signs/triangle", "soft_verge","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.STOP_OR_GIVEWAY_AHEAD, "uk_signs/triangle", "stop_or_giveway_ahead","back_uk_inverted_triangle");
        signModelBuilder(ThingamajigsBlocks.TRY, "uk_signs/triangle", "try","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.TUNNEL, "uk_signs/triangle", "tunnel","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.UNEVEN, "uk_signs/triangle", "uneven","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.WATER_COURSE, "uk_signs/triangle", "water_course","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.ZEBRA, "uk_signs/triangle", "zebra","back_uk_triangle");
        signModelBuilder(ThingamajigsBlocks.ALL_PROHIBITED, "uk_signs/circle", "all_vehicles_prohibited","back_circle");
        signModelBuilder(ThingamajigsBlocks.HORSE_PROHIBITED, "uk_signs/circle", "horse_prohibited","back_circle");
        signModelBuilder(ThingamajigsBlocks.MOTOR_VEHICLES_PROHIBITED, "uk_signs/circle", "motor_vehicles_prohibited","back_circle");
        signModelBuilder(ThingamajigsBlocks.MOTORCYCLES_PROHIBITED, "uk_signs/circle", "motorcycles_prohibited","back_circle");
        signModelBuilder(ThingamajigsBlocks.NO_OVERTAKING, "uk_signs/circle", "no_overtaking","back_circle");
        signModelBuilder(ThingamajigsBlocks.NO_STOPPING, "uk_signs/circle", "no_stopping","back_circle");
        signModelBuilder(ThingamajigsBlocks.NO_WAITING, "uk_signs/circle", "no_waiting","back_circle");
        signModelBuilder(ThingamajigsBlocks.PEDS_PROHIBITED, "uk_signs/circle", "peds_prohibited","back_circle");
        signModelBuilder(ThingamajigsBlocks.SPEED_50, "uk_signs/circle", "speed_50","back_circle");
        signModelBuilder(ThingamajigsBlocks.SPEED_40, "uk_signs/circle", "speed_40","back_circle");
        signModelBuilder(ThingamajigsBlocks.SPEED_30, "uk_signs/circle", "speed_30","back_circle");
        signModelBuilder(ThingamajigsBlocks.SPEED_20, "uk_signs/circle", "speed_20","back_circle");
        signModelBuilder(ThingamajigsBlocks.SPEED_10, "uk_signs/circle", "speed_10","back_circle");
        signModelBuilder(ThingamajigsBlocks.CATTLE_GRID, "uk_signs/triangle", "cattle_grid","back_uk_triangle");
        // ref full blocks
        allSidedBlock(ThingamajigsBlocks.CRYSTAL_BLOCK,"thingamajigs:block/crystal");
        // other things I don't feel like putting in a group
        allSidedBlock(ThingamajigsBlocks.CIRCUITS,"thingamajigs:block/circuit_board_texture");
        allSidedBlock(ThingamajigsBlocks.MYSTERIOUS_ONE_WOOL,"thingamajigs:block/mysterious_one_wool");
        // us signs
        signModelBuilder(ThingamajigsBlocks.GRID_SIGN, "text_signs", "grid","back_diamond");
        signModelBuilder(ThingamajigsBlocks.WRONG_WAY, "text_signs", "wrong_way","back_slightly_bigger_rectangle");

        allSidedBlock(ThingamajigsBlocks.CONCRETE,"thingamajigs:block/concrete");
        allSidedBlock(ThingamajigsBlocks.CONCRETE_BRICKS,"thingamajigs:block/concrete_bricks");
        allSidedBlock(ThingamajigsBlocks.COBBLED_CONCRETE,"thingamajigs:block/cobbled_concrete");


        // pumpkins
        /*
        orientableBlock(ThingamajigsBlocks.WHITE_JOL,
                "thingamajigs:block/pumpkins/white_jack_o_lantern",
                "thingamajigs:block/pumpkins/white_pumpkin_side",
                "thingamajigs:block/pumpkins/white_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.LIGHT_GRAY_JOL,
                "thingamajigs:block/pumpkins/light_gray_jack_o_lantern",
                "thingamajigs:block/pumpkins/light_gray_pumpkin_side",
                "thingamajigs:block/pumpkins/light_gray_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.GRAY_JOL,
                "thingamajigs:block/pumpkins/gray_jack_o_lantern",
                "thingamajigs:block/pumpkins/gray_pumpkin_side",
                "thingamajigs:block/pumpkins/gray_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.BLACK_JOL,
                "thingamajigs:block/pumpkins/black_jack_o_lantern",
                "thingamajigs:block/pumpkins/black_pumpkin_side",
                "thingamajigs:block/pumpkins/black_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.BROWN_JOL,
                "thingamajigs:block/pumpkins/brown_jack_o_lantern",
                "thingamajigs:block/pumpkins/brown_pumpkin_side",
                "thingamajigs:block/pumpkins/brown_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.RED_JOL,
                "thingamajigs:block/pumpkins/red_jack_o_lantern",
                "thingamajigs:block/pumpkins/red_pumpkin_side",
                "thingamajigs:block/pumpkins/red_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.YELLOW_JOL,
                "thingamajigs:block/pumpkins/yellow_jack_o_lantern",
                "thingamajigs:block/pumpkins/yellow_pumpkin_side",
                "thingamajigs:block/pumpkins/yellow_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.LIME_JOL,
                "thingamajigs:block/pumpkins/lime_jack_o_lantern",
                "thingamajigs:block/pumpkins/lime_pumpkin_side",
                "thingamajigs:block/pumpkins/lime_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.GREEN_JOL,
                "thingamajigs:block/pumpkins/green_jack_o_lantern",
                "thingamajigs:block/pumpkins/green_pumpkin_side",
                "thingamajigs:block/pumpkins/green_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.CYAN_JOL,
                "thingamajigs:block/pumpkins/cyan_jack_o_lantern",
                "thingamajigs:block/pumpkins/cyan_pumpkin_side",
                "thingamajigs:block/pumpkins/cyan_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.LIGHT_BLUE_JOL,
                "thingamajigs:block/pumpkins/light_blue_jack_o_lantern",
                "thingamajigs:block/pumpkins/light_blue_pumpkin_side",
                "thingamajigs:block/pumpkins/light_blue_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.BLUE_JOL,
                "thingamajigs:block/pumpkins/blue_jack_o_lantern",
                "thingamajigs:block/pumpkins/blue_pumpkin_side",
                "thingamajigs:block/pumpkins/blue_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.PURPLE_JOL,
                "thingamajigs:block/pumpkins/purple_jack_o_lantern",
                "thingamajigs:block/pumpkins/purple_pumpkin_side",
                "thingamajigs:block/pumpkins/purple_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.MAGENTA_JOL,
                "thingamajigs:block/pumpkins/magenta_jack_o_lantern",
                "thingamajigs:block/pumpkins/magenta_pumpkin_side",
                "thingamajigs:block/pumpkins/magenta_pumpkin_top");
        orientableBlock(ThingamajigsBlocks.PINK_JOL,
                "thingamajigs:block/pumpkins/pink_jack_o_lantern",
                "thingamajigs:block/pumpkins/pink_pumpkin_side",
                "thingamajigs:block/pumpkins/pink_pumpkin_top");
        */
        signModelBuilder(ThingamajigsBlocks.NO_LEFT_TURN, "symbol_signs", "no_left_turn","back");
        signModelBuilder(ThingamajigsBlocks.NO_RIGHT_TURN, "symbol_signs", "no_right_turn","back");
        signModelBuilder(ThingamajigsBlocks.NO_STRAIGHT, "symbol_signs", "no_straight","back");
        signModelBuilder(ThingamajigsBlocks.NO_U_TURN, "symbol_signs", "no_u_turn","back");
        signModelBuilder(ThingamajigsBlocks.DO_NOT_ENTER, "text_signs", "do_not_enter","back");
        signModelBuilder(ThingamajigsBlocks.HAZARDOUS_MATERIALS, "text_signs", "hazardous_materials","back");
        signModelBuilder(ThingamajigsBlocks.NO_HAZARDOUS_MATERIALS, "text_signs", "no_hazardous_materials","back");

        signModelBuilder(ThingamajigsBlocks.ONLY_LEFT, "text_signs", "only_left","back_special_vertical_rectangle");
        signModelBuilder(ThingamajigsBlocks.ONLY_RIGHT, "text_signs", "only_right","back_special_vertical_rectangle");
        signModelBuilder(ThingamajigsBlocks.ONLY_UP, "text_signs", "only_up","back_special_vertical_rectangle");
        signModelBuilder(ThingamajigsBlocks.HOV_ONLY, "text_signs", "hov_only","back_special_vertical_rectangle");
        signModelBuilder(ThingamajigsBlocks.HOV_ENDS, "text_signs", "hov_ends","back_special_vertical_rectangle");

        allSidedBlock(ThingamajigsBlocks.OUTLET_BLOCK,"thingamajigs:block/factory/outlet");
        allSidedBlock(ThingamajigsBlocks.PAPER_WALL_BLOCK,"thingamajigs:block/paper/plain");
        allSidedBlock(ThingamajigsBlocks.PAPER_FLOWER_WALL_BLOCK,"thingamajigs:block/paper/flower");

        // 1.7.7
        signModelBuilder(ThingamajigsBlocks.ORANGE_PRIVATE_PROPERTY_SIGN, "text_signs", "orange_private_property","back_slightly_bigger_rectangle");
        signModelBuilder(ThingamajigsBlocks.VIDEO_IN_PROGRESS_SIGN, "text_signs", "video_in_progress","back_slightly_bigger_rectangle");
        signModelBuilder(ThingamajigsBlocks.NO_STARING_PRIVATE_PROPERTY_SIGN, "text_signs", "no_staring_private_property","back_slightly_bigger_rectangle");

    }

    private BlockModelBuilder cakeBlock(RegistryObject<Block> block, String cakeBottom, String cakeTop, String cakeSide){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("minecraft:block/cake"))
                .texture("bottom", new ResourceLocation(cakeBottom)) // cake bottom (cakey part)
                .texture("top", new ResourceLocation(cakeTop)) // cake top (frosting)
                .texture("side", new ResourceLocation(cakeSide)) // cake side (frosting to cakey part)
                .texture("particle", new ResourceLocation("side"));
    }

    public BlockModelBuilder rotatedSlabBottom(RegistryObject<Block> block, String marking, String age){
        return withExistingParent(block.getId().getPath(), new ResourceLocation("thingamajigs:block/bases/base_marked_asphalt_slab_bottom"))
                .texture("marking",new ResourceLocation(marking))
                .texture("age",new ResourceLocation(age));
    }
    public BlockModelBuilder rotatedSlabTop(RegistryObject<Block> block, String marking, String age){
        return withExistingParent(block.getId().getPath(), new ResourceLocation("thingamajigs:block/bases/base_marked_asphalt_slab_top"))
                .texture("marking",new ResourceLocation(marking))
                .texture("age",new ResourceLocation(age));
    }



    private BlockModelBuilder orientableBlock(RegistryObject<Block> block, String front, String side, String top){
        return withExistingParent(block.getId().getPath(), new ResourceLocation("minecraft:block/orientable"))
                .texture("front",new ResourceLocation(front))
                .texture("side",new ResourceLocation(side))
                .texture("top",new ResourceLocation(top));
    }

    private BlockModelBuilder cubeColumnBlock(RegistryObject<Block> block, String end, String side){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("minecraft:cube_column"))
                .texture("end",new ResourceLocation(end))
                .texture("side",new ResourceLocation(side));
    }

    private BlockModelBuilder customPumpkinBlock(RegistryObject<Block> block, String end, String side){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("thingamajigs:bases/custom_pumpkin"))
                .texture("end",new ResourceLocation(end))
                .texture("side",new ResourceLocation(side));
    }

    private BlockModelBuilder allSidedBlock(RegistryObject<Block> block, String textureLocation){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("minecraft:block/cube_all"))
                .texture("all", new ResourceLocation(textureLocation));
    }

    @NotNull
    private BlockModelBuilder signModelBuilder(RegistryObject<Block> block, String directory, String front, String back){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("thingamajigs:bases/sign"))
                .texture("front", new ResourceLocation("thingamajigs:block/signs/" + directory + "/" + front))
                .texture("back", new ResourceLocation("thingamajigs:block/signs/back/" + back))
                .texture("pole", POLE_TEXTURE)
                .texture("particle", POLE_TEXTURE);
    }

    private BlockModelBuilder standingTorchModelBuilder(RegistryObject<Block> block, String directory){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("thingamajigs:bases/standing_torch"))
                .texture("0", new ResourceLocation(directory))
                .texture("particle", new ResourceLocation(directory)).renderType("cutout");
    }

    private BlockModelBuilder wallTorchModelBuilder(RegistryObject<Block> block, String directory){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("thingamajigs:bases/wall_torch"))
                .texture("0", new ResourceLocation(directory))
                .texture("missing", new ResourceLocation(directory))
                .texture("particle", new ResourceLocation(directory)).renderType("cutout");
    }

    private BlockModelBuilder lanternModelBuilder(RegistryObject<Block> block, String directory){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("minecraft:block/template_lantern"))
                .texture("lantern",directory).renderType("cutout");
    }

    private BlockModelBuilder hangingLanternModelBuilder(RegistryObject<Block> block, String directory){
        return withExistingParent(block.getId().getPath() + "_hanging",
                new ResourceLocation("minecraft:block/template_hanging_lantern"))
                .texture("lantern",directory).renderType("cutout");
    }

    private BlockModelBuilder customChainModelBuilder(RegistryObject<Block> block, String textureDirectory){
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("minecraft:block/chain"))
                .texture("all",textureDirectory)
                .texture("particle",textureDirectory).renderType("cutout");
    }
}
