package net.rk.thingamajigs;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.config.ThingamajigsClientConfigs;
import net.rk.thingamajigs.config.ThingamajigsServerConfigs;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;
import net.rk.thingamajigs.entity.ThingamajigsEntities;
import net.rk.thingamajigs.entity.models.*;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.fluid.ThingamajigsFluids;
import net.rk.thingamajigs.item.ThingamajigsCreativeTab;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.misc.ThingamajigsBlockTypes;
import net.rk.thingamajigs.misc.ThingamajigsColors;
import net.rk.thingamajigs.painting.ThingamajigsPaintings;
import net.rk.thingamajigs.particle.ThingamajigsParticles;
import net.rk.thingamajigs.recipe.ThingamajigsRecipes;
import net.rk.thingamajigs.renderers.*;
import net.rk.thingamajigs.screen.*;
import org.slf4j.Logger;

@SuppressWarnings("deprecated,unused")
@Mod(Thingamajigs.MOD_ID)
public class Thingamajigs {
    public static final String MOD_ID = "thingamajigs";
    private static final Logger LOGGERV2 = LogUtils.getLogger();

    private static boolean isCreateIn = false;

    public static boolean creating(){
        return isCreateIn;
    }

    private static void logErrorInternal(Exception e){
        LOGGERV2.error("Thingamajigs encountered and error: Exception goes as follows: " + e.getMessage());
    }

    public Thingamajigs() {
        // Register the setup method for mod loading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        try{
            if(ModList.get().isLoaded("create")) {
                isCreateIn = true;
            }
        }
        catch(Exception e){
            logErrorInternal(e);
        }

        // register creative mode tabs
        ThingamajigsCreativeTab.register(eventBus);

        ThingamajigsMenuTypes.register(eventBus);

        try {
            ThingamajigsParticles.register(eventBus);
        }
        catch (Exception e){
            LOGGERV2.error("Thingamajigs encountered a particle-related error: {}", e.getMessage());
        }

        // register all OTHER classes from the mod using their eventBus 'getter' method
        ThingamajigsSoundEvents.register(eventBus);
        ThingamajigsItems.register(eventBus);
        ThingamajigsBlocks.register(eventBus);
        ThingamajigsPaintings.register(eventBus);
        ThingamajigsBlockEntities.register(eventBus);
        ThingamajigsEntities.register(eventBus);

        // Fluid and Fluid Type Registry (quite fluid if I do say so myself)
        ThingamajigsFluids.FLUID_TYPES.register(eventBus);
        ThingamajigsFluids.FLUIDS.register(eventBus);

        // recipes
        ThingamajigsRecipes.register(eventBus);

        // setup listeners "...oh im, listening for your voice..."
        eventBus.addListener(this::setup);
        eventBus.addListener(this::setupClient);

        if(FMLLoader.getDist() == Dist.CLIENT){
            eventBus.addListener(this::layerSetup);
        }

        // setup MORE listeners "more, more!"
        eventBus.addListener(this::addCreative);

        // register client and server (common) configs
        LOGGERV2.info("Thingamajigs is doing final preparations.");

        // Split config into two parts client and server
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ThingamajigsServerConfigs.CPSEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ThingamajigsClientConfigs.CPSECCLIENT);

        // hmm.... colors?
        try{
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                eventBus.addListener(Thingamajigs::setColors);
                eventBus.addListener(Thingamajigs::setItemColors);
            });
        }
        catch (Exception e){
            logErrorInternal(e);
        }

        // finale mod registry
        try{
            MinecraftForge.EVENT_BUS.register(this);
        }
        catch (Exception e){
            // note that if this mod errors out here, report bug and^or crash immediately!
            logErrorInternal(e);
        }
    }

    // New MC Tabs System
    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == ThingamajigsCreativeTab.ALL_ITEMS_TAB_v2.get()){
            //
            //event.accept(ThingamajigsBlocks.TEST_BLOCK.get().asItem());
            //
            // items (ingredient items used in primary recipes)
            event.accept(ThingamajigsItems.THINGAMAJIG);
            // globs (used in secondary recipes)
            event.accept(ThingamajigsItems.THINGAMAJIG_GLOB);
            event.accept(ThingamajigsItems.SIGN_GLOB);
            event.accept(ThingamajigsItems.CIRCLE_SIGN_GLOB);
            event.accept(ThingamajigsItems.SQUARE_SIGN_GLOB);
            event.accept(ThingamajigsItems.TRIANGLE_SIGN_GLOB);
            event.accept(ThingamajigsItems.MISC_SIGN_GLOB);
            event.accept(ThingamajigsItems.DOOR_GLOB);
            // components (used in tertiary recipes and beyond)
            event.accept(ThingamajigsItems.BASE_COMPONENT);
            event.accept(ThingamajigsItems.INFRASTRUCTURE_COMPONENT);
            event.accept(ThingamajigsItems.FACTORY_COMPONENT);
            event.accept(ThingamajigsItems.TECHNOLOGY_COMPONENT);
            event.accept(ThingamajigsItems.SPORTS_COMPONENT);
            event.accept(ThingamajigsItems.FURNITURE_COMPONENT);
            event.accept(ThingamajigsItems.MISC_COMPONENT);
            // subcategory components (used in recipes and beyond)
            event.accept(ThingamajigsItems.MINI_COMPONENT);
            event.accept(ThingamajigsItems.CAR_WASH_COMPONENT);
            event.accept(ThingamajigsItems.TRAFFIC_SIGNAL_COMPONENT);
            event.accept(ThingamajigsItems.RAILROAD_COMPONENT);
            event.accept(ThingamajigsItems.COMPUTER_COMPONENT);
            event.accept(ThingamajigsItems.GAME_CONSOLE_COMPONENT);
            event.accept(ThingamajigsItems.CHRISTMAS_COMPONENT);
            event.accept(ThingamajigsItems.SAFETY_COMPONENT);
            event.accept(ThingamajigsItems.ARCADE_COMPONENT);
            event.accept(ThingamajigsItems.PHONE_COMPONENT);
            event.accept(ThingamajigsItems.HOME_COMPONENT);
            event.accept(ThingamajigsItems.APPLIANCE_COMPONENT);
            event.accept(ThingamajigsItems.SCIENCE_COMPONENT);
            event.accept(ThingamajigsItems.HEALTH_COMPONENT);
            event.accept(ThingamajigsItems.TOY_COMPONENT);
            event.accept(ThingamajigsItems.MUSIC_COMPONENT);
            // items continued
            event.accept(ThingamajigsItems.THINGAMAJIGS_PAINTING_ITEM);
            event.accept(ThingamajigsItems.POOP_HORN);
            event.accept(ThingamajigsItems.KEY);
            event.accept(ThingamajigsItems.COIN);
            event.accept(ThingamajigsItems.MONEY);
            event.accept(ThingamajigsItems.DEBIT_CARD);
            // refinement and other recipe items
            // rubber tree stuff
            event.accept(ThingamajigsBlocks.RUBBER_SAPLING.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_LEAVES.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_LOG.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD.get().asItem());
            event.accept(ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get().asItem());
            event.accept(ThingamajigsBlocks.STRIPPED_RUBBER_WOOD.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_STAIRS.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_FENCE.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_FENCE_GATE.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_TRAPDOOR.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_PRESSURE_PLATE.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_WOOD_BUTTON.get().asItem());
            // continued other items
            event.accept(ThingamajigsItems.TREE_RESIN);
            event.accept(ThingamajigsItems.RUBBER);
            // xtras
            event.accept(ThingamajigsBlocks.RESIN_FILLED_ROCK.get().asItem());
            //
            event.accept(ThingamajigsItems.GOLDEN_APPLE_SHARD);
            event.accept(ThingamajigsItems.GLOBIZED_GOLDEN_APPLE_SHARD);
            event.accept(ThingamajigsItems.PURIFYING_NUGGET);
            event.accept(ThingamajigsItems.PURIFYING_INGOT);
            event.accept(ThingamajigsItems.PURIFYING_GLOB);
            event.accept(ThingamajigsBlocks.PURIFYING_BLOCK.get().asItem());
            // crafting blocks
            event.accept(ThingamajigsBlocks.SLUDGE_CONVERTER.get().asItem());
            // food
            event.accept(ThingamajigsItems.GLOB_SANDWICH);
            // fluid items
            event.accept(ThingamajigsItems.PURIFYING_WATER_BUCKET);
            event.accept(ThingamajigsItems.SLUDGE_BUCKET);
            // plants and flowers
            event.accept(ThingamajigsBlocks.BRAMBLE.get().asItem());
            event.accept(ThingamajigsBlocks.ROUND_BUSH.get().asItem());
            event.accept(ThingamajigsBlocks.BULBLET.get().asItem());
            event.accept(ThingamajigsBlocks.WISPY_WEED.get().asItem());
            event.accept(ThingamajigsBlocks.BULBY_FLOWER.get().asItem());
            event.accept(ThingamajigsBlocks.DROOPY_FLOWER.get().asItem());
            event.accept(ThingamajigsItems.FLOWERING_LILY_PAD_ITEM);
            event.accept(ThingamajigsItems.TRIPLE_LILY_PAD_ITEM);
            // seeds
            event.accept(ThingamajigsItems.WHITE_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.LIGHT_GRAY_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.GRAY_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.BLACK_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.BROWN_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.RED_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.YELLOW_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.LIME_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.GREEN_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.CYAN_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.LIGHT_BLUE_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.BLUE_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.PURPLE_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.MAGENTA_PUMPKIN_SEEDS);
            event.accept(ThingamajigsItems.PINK_PUMPKIN_SEEDS);
            // pumpkins
            event.accept(ThingamajigsBlocks.WHITE_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHT_GRAY_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHT_GRAY_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.GRAY_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.GRAY_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.BROWN_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.BROWN_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.RED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.RED_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.LIME_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.LIME_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.CYAN_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.CYAN_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.MAGENTA_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.MAGENTA_CARVED_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.PINK_PUMPKIN.get().asItem());
            event.accept(ThingamajigsBlocks.PINK_CARVED_PUMPKIN.get().asItem());
            // jack o lanterns
            event.accept(ThingamajigsBlocks.WHITE_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHT_GRAY_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.GRAY_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.BROWN_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.RED_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.LIME_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.CYAN_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHT_BLUE_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.MAGENTA_JOL.get().asItem());
            event.accept(ThingamajigsBlocks.PINK_JOL.get().asItem());
            // interactive items (torch placers and other block items)
            event.accept(ThingamajigsItems.CLEAR_BULB_ITEM);
            event.accept(ThingamajigsItems.FULL_BULB_ITEM);
            event.accept(ThingamajigsItems.CLEAR_LANTERN_ITEM);
            event.accept(ThingamajigsItems.FULL_LANTERN_ITEM);
            event.accept(ThingamajigsItems.PAPER_LANTERN_ITEM);
            event.accept(ThingamajigsItems.RED_LANTERN_ITEM);
            // lanterns
            event.accept(ThingamajigsBlocks.SCULK_LANTERN.get().asItem());
            // start full blocks
            // Mini-City Blocks
            event.accept(ThingamajigsBlocks.MINI_ROAD.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_RAIL.get().asItem());
            event.accept(ThingamajigsBlocks.TINY_CROSSING.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_RED_BUILDING.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_YELLOW_BUILDING.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_TALL_YELLOW_BUILDING.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_GREEN_BUILDING.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_BLUE_BUILDING.get().asItem());
            // Doors
            event.accept(ThingamajigsBlocks.WHITE_WOOD_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.SCREEN_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.FESTIVE_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.SNOWMAN_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.STONE_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.BUBBLE_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.METALLIC_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.ALARMED_DOOR.get().asItem());
            event.accept(ThingamajigsBlocks.LOCKABLE_DOOR.get().asItem());
            // chains
            event.accept(ThingamajigsBlocks.SCULK_CHAIN.get().asItem());
            // Misc. Begin Blocks
            event.accept(ThingamajigsBlocks.FANCY_QUARTZ_PILLAR.get().asItem());
            event.accept(ThingamajigsBlocks.DECORATIVE_PORTAL.get().asItem());
            event.accept(ThingamajigsBlocks.NOT_QUITE_MENGER.get().asItem());
            event.accept(ThingamajigsBlocks.SPOOKY_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEBERRY_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.NETHERISH_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.VOLCANIC_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.CHARGED_VOLCANIC_STONE.get().asItem());
            // Techno Blocks
            event.accept(ThingamajigsBlocks.TECHNO_CORE.get().asItem());
            event.accept(ThingamajigsBlocks.TECHNO_PILLAR.get().asItem());
            event.accept(ThingamajigsBlocks.CHISELED_TECHNO_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.NEON_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.ALT_NEON_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.SWIRLY_TECHNO_BLOCK.get().asItem());
            // other tech blocks
            event.accept(ThingamajigsBlocks.CIRCUITS.get().asItem());
            event.accept(ThingamajigsBlocks.SCRAP_PANELS.get().asItem());
            // stripes and such
            event.accept(ThingamajigsBlocks.RED_CAUTION.get().asItem());
            event.accept(ThingamajigsBlocks.ORANGE_CAUTION.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_CAUTION.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_CAUTION.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHT_BLUE_CAUTION.get().asItem());
            event.accept(ThingamajigsBlocks.ALT_ORANGE_CAUTION.get().asItem());
            event.accept(ThingamajigsBlocks.SCROLLING_YELLOW_CAUTION.get().asItem());
            // Factory Blocks
            event.accept(ThingamajigsBlocks.METAL_SCAFFOLDING.get().asItem());
            event.accept(ThingamajigsBlocks.METAL_VENTS.get().asItem());
            event.accept(ThingamajigsBlocks.OUTLET_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.GEARS_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.MOVING_GEARS_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.FAN_BLOCK_ULTRASONIC.get().asItem());
            event.accept(ThingamajigsBlocks.FAN_BLOCK_FAST.get().asItem());
            event.accept(ThingamajigsBlocks.FAN_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.FAN_BLOCK_SPARK.get().asItem());
            event.accept(ThingamajigsBlocks.FAN_BLOCK_OFF.get().asItem());
            event.accept(ThingamajigsBlocks.TRANSPARENT_FAST_FAN_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.TRANSPARENT_FAN_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.TRANSPARENT_OFF_FAN_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.GRATE.get().asItem());
            // paper walls
            event.accept(ThingamajigsBlocks.PAPER_WALL_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.PAPER_FLOWER_WALL_BLOCK.get().asItem());
            // misc other glass blocks
            event.accept(ThingamajigsBlocks.SCREEN.get().asItem());
            event.accept(ThingamajigsBlocks.COLORED_GLASS.get().asItem());
            event.accept(ThingamajigsBlocks.REINFORCED_GLASS.get().asItem());
            // Fancy Decoration Blocks
            event.accept(ThingamajigsBlocks.MYSTERIOUS_ONE_WOOL.get().asItem());
            event.accept(ThingamajigsBlocks.FIREOUS_GLAZED_TERRACOTTA.get().asItem());
            event.accept(ThingamajigsBlocks.DARK_FIREOUS_GLAZED_TERRACOTTA.get().asItem());
            event.accept(ThingamajigsBlocks.CRYSTAL_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.DARK_CRYSTAL_BLOCK.get().asItem());
            // Laboratory Blocks
            event.accept(ThingamajigsBlocks.GRAY_SCREEN.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_SCREEN.get().asItem());
            // Useful Blocks
            event.accept(ThingamajigsBlocks.NETHER_CHISELED_BOOKSHELF.get().asItem());
            // Railroad or Railway
            // minecarts
            event.accept(ThingamajigsItems.INFIMOVE_MINECART_ITEM);
            // blocks
            event.accept(ThingamajigsBlocks.PURPLE_RAIL.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_POWERED_RAIL.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_DETECTOR_RAIL.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_ACTIVATOR_RAIL.get().asItem());
            // Road Blocks & Items
            event.accept(ThingamajigsItems.PAINT_BRUSH);
            event.accept(ThingamajigsItems.WHITE_PAINT_BRUSH);
            event.accept(ThingamajigsItems.YELLOW_PAINT_BRUSH);
            event.accept(ThingamajigsItems.BLUE_PAINT_BRUSH);
            event.accept(ThingamajigsItems.SCRAPE_TOOL);
            //
            //
            event.accept(ThingamajigsBlocks.ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.ASPHALT_OK.get().asItem());
            event.accept(ThingamajigsBlocks.ASPHALT_MEDIOCRE.get().asItem());
            event.accept(ThingamajigsBlocks.ASPHALT_OLD.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK_CRACKED.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK_SECTIONED.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK_BLOCKED.get().asItem());
            // road slabs
            event.accept(ThingamajigsBlocks.ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.ASPHALT_OK_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.ASPHALT_OLD_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.CRACKED_SIDEWALK_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.SECTIONED_SIDEWALK_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.BLOCKED_SIDEWALK_SLAB.get().asItem());
            // misc roads and sidewalks
            event.accept(ThingamajigsBlocks.SIDEWALK_LAYER.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK_LAYER_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.SIDEWALK_LAYER_RIGHT.get().asItem());
            // painted road full blocks and slabs
            event.accept(ThingamajigsBlocks.DOUBLE_WHITE_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_D_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_D_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_D_OLD_ASPHALT_SLAB.get().asItem());

            event.accept(ThingamajigsBlocks.DOUBLE_YELLOW_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_D_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_D_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get().asItem());

            event.accept(ThingamajigsBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_DT_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_DT_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get().asItem());

            event.accept(ThingamajigsBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_DT_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get().asItem());

            event.accept(ThingamajigsBlocks.WHITE_PARKING_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get().asItem());

            event.accept(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get().asItem());

            event.accept(ThingamajigsBlocks.BLUE_PARKING_ASPHALT.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get().asItem());
            // Poles and Generic Road Side Things
            event.accept(ThingamajigsBlocks.LIGHT_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.HOLDER_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.STRAIGHT_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.STRAIGHT_HORIZONTAL_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.L_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.L_ONLY_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.VERTICAL_AXIS_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.AXIS_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.PLUS_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.THREE_WAY_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.TL_CONNECTOR.get().asItem());
            event.accept(ThingamajigsBlocks.T_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.T_POLE_B.get().asItem());
            event.accept(ThingamajigsBlocks.T_POLE_C.get().asItem());
            event.accept(ThingamajigsBlocks.VERTICAL_T_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.TRI_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.TRI_POLE_B.get().asItem());
            event.accept(ThingamajigsBlocks.T_HORZ_ONLY_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.ALL_WAY_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.VERTICAL_POLE_REDSTONE.get().asItem());
            event.accept(ThingamajigsBlocks.VERTICAL_REDSTONE_SIDEWALK.get().asItem());
            // railroad crossing stuff
            event.accept(ThingamajigsBlocks.RR_CANTILEVER.get().asItem());
            event.accept(ThingamajigsBlocks.RR_CANTILEVER_END.get().asItem());
            event.accept(ThingamajigsBlocks.RR_CANTILEVER_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.RAILROAD_CROSSING.get().asItem());
            event.accept(ThingamajigsBlocks.RAILROAD_CROSSING_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEY_MECHANICAL_BELL.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEY_MECHANICAL_BELL_TWO.get().asItem());
            event.accept(ThingamajigsBlocks.EBELL_ONE.get().asItem());
            event.accept(ThingamajigsBlocks.EBELL_TWO.get().asItem());
            // international railroad crossing stuff
            event.accept(ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.BRITISH_RAILWAY_ALARM.get().asItem());
            event.accept(ThingamajigsBlocks.TRI_RAILWAY_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.DUAL_RAILWAY_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.RAILROAD_CROSSING_BLOCKER.get().asItem());
            // other traffic control things
            event.accept(ThingamajigsBlocks.STOP_GATE.get().asItem());
            event.accept(ThingamajigsBlocks.ARROW_BOARD.get().asItem());
            // traffic control
            event.accept(ThingamajigsBlocks.TRAFFIC_CONTROL_BOX.get().asItem());
            // Traffic Signals
            event.accept(ThingamajigsBlocks.CROSSWALK_BUTTON.get().asItem());
            event.accept(ThingamajigsBlocks.PED_FLASHERS.get().asItem());
            event.accept(ThingamajigsBlocks.HAWK_SIGNAL.get().asItem());
            event.accept(ThingamajigsBlocks.PED_SIGNAL_WORDED.get().asItem());
            event.accept(ThingamajigsBlocks.PED_SIGNAL_SYMBOLS.get().asItem());
            event.accept(ThingamajigsBlocks.PED_SIGNAL_MAN_1.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_1.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_2.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_3.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_4.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_DOGHOUSE_1.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_DOGHOUSE_2.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_SYMBOL_1.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_YELLOW_FLASH.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_RED_FLASH.get().asItem());
            event.accept(ThingamajigsBlocks.HORIZONTAL_TRAFFIC_SIGNAL_1.get().asItem());
            event.accept(ThingamajigsBlocks.HORIZONTAL_TRAFFIC_SIGNAL_3.get().asItem());
            event.accept(ThingamajigsBlocks.HORIZONTAL_TRAFFIC_SIGNAL_2.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_BEACON.get().asItem());
            event.accept(ThingamajigsBlocks.RED_BEACON.get().asItem());
            event.accept(ThingamajigsBlocks.ARROW_BEACON.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_SIGNAL_ALLWAY_STOP_BEACON.get().asItem());
            // Road Construction Blocks
            event.accept(ThingamajigsBlocks.ROAD_PANEL.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_SMALL.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_SMALL_LIGHTED.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_LIGHTED.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_CLOSED.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_THRU_CLOSED.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_BRIDGE_CLOSED.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARRIER_BRIDGE_THRU_CLOSED.get().asItem());
            event.accept(ThingamajigsBlocks.BIG_ROAD_CONE.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_BARREL.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_CHANNELIZER.get().asItem());
            // General Purpose Construction
            event.accept(ThingamajigsBlocks.CONCRETE.get().asItem());
            event.accept(ThingamajigsBlocks.CONCRETE_STAIRS.get().asItem());
            event.accept(ThingamajigsBlocks.CONCRETE_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.CONCRETE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.CONCRETE_BRICKS_STAIRS.get().asItem());
            event.accept(ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.COBBLED_CONCRETE.get().asItem());
            event.accept(ThingamajigsBlocks.COBBLED_CONCRETE_STAIRS.get().asItem());
            event.accept(ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.STEEL.get().asItem());
            // General Purpose Barriers
            event.accept(ThingamajigsBlocks.STEEL_HOARDING.get().asItem());
            event.accept(ThingamajigsBlocks.CONCRETE_BARRIER.get().asItem());
            event.accept(ThingamajigsBlocks.REBAR_CONCRETE_BARRIER.get().asItem());
            event.accept(ThingamajigsBlocks.REINFORCED_CONCRETE_BARRIER.get().asItem());
            event.accept(ThingamajigsBlocks.BRIDGE_BARRIER.get().asItem());
            event.accept(ThingamajigsBlocks.LADDER_RAILING.get().asItem());
            event.accept(ThingamajigsBlocks.CURVED_RAILING.get().asItem());
            // abandonment and other construction things
            event.accept(ThingamajigsBlocks.I_BEAM.get().asItem());
            event.accept(ThingamajigsBlocks.CINDER_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.CINDER_BLOCK_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.CINDER_BLOCK_SMALL.get().asItem());
            event.accept(ThingamajigsBlocks.DOOR_BLOCKADE.get().asItem());
            event.accept(ThingamajigsBlocks.WINDOW_BLOCKADE.get().asItem());
            // Car Shop Related
            event.accept(ThingamajigsBlocks.CAR_WHEEL.get().asItem());
            // Road Covers (sewer or otherwise, service panels, etc.)
            event.accept(ThingamajigsBlocks.ROAD_COVER.get().asItem());
            event.accept(ThingamajigsBlocks.ALT_ROAD_COVER.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_PANEL_COVER.get().asItem());
            event.accept(ThingamajigsBlocks.ALT_ROAD_PANEL_COVER.get().asItem());
            // car wash
            event.accept(ThingamajigsBlocks.CAR_WASH_SIGNAGE.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_SIGNAL.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_TIRE_SCRUBBER.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_MIXED_BRUSH.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_DRIPPER.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_SPRAYER.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_TRIFOAMER.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_SOAPER.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_WAXER.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_YELLOW_BRUSH.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_MITTER_CURTAIN.get().asItem());
            event.accept(ThingamajigsBlocks.CAR_WASH_DRYER.get().asItem());
            // telephone & cell service towers
            event.accept(ThingamajigsBlocks.PHONE_SWITCHER.get().asItem());
            event.accept(ThingamajigsBlocks.CELL_MULTI_ANGLED_TRANSMITTER.get().asItem());
            event.accept(ThingamajigsBlocks.CELL_MULTI_TRANSMITTER.get().asItem());
            event.accept(ThingamajigsBlocks.CELL_TRANSMITTER.get().asItem());
            event.accept(ThingamajigsBlocks.CELL_MICROWAVE_TRANSMITTER.get().asItem());
            event.accept(ThingamajigsBlocks.OLD_MICROWAVE_TRANSMITTER.get().asItem());
            // dark stone
            event.accept(ThingamajigsBlocks.DARKENED_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.GRADIENT_DARKENED_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.DARK_DARKENED_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.DARKENED_STONE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.PANEL_DARKENED_STONE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.CHISELED_PANEL_DARKENED_STONE_BRICKS.get().asItem());
            // other
            event.accept(ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.MYSTERIOUS_PILLAR.get().asItem());
            // runic stone
            event.accept(ThingamajigsBlocks.RUNICSTONE_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.RUNICSTONE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.SUNSTONE_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.MOONSTONE_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.EXPOSED_RUNICSTONE_BLOCK.get().asItem());
            // Tiling and Flooring Blocks
            event.accept(ThingamajigsBlocks.CRYSTALINE_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.INDENTED_STONE.get().asItem());
            event.accept(ThingamajigsBlocks.PANEL_STONE.get().asItem());
            //
            event.accept(ThingamajigsBlocks.PANEL_STONE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.MOSSY_PANEL_STONE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.CRACKED_PANEL_STONE_BRICKS.get().asItem());
            event.accept(ThingamajigsBlocks.CHISELED_PANEL_STONE_BRICKS.get().asItem());
            // connected texture blocks
            event.accept(ThingamajigsBlocks.STONE_PILLAR.get().asItem());
            event.accept(ThingamajigsBlocks.STONE_BRICK_PILLAR.get().asItem());
            event.accept(ThingamajigsBlocks.CHISELED_STONE_BRICK_PILLAR.get().asItem());
            //
            event.accept(ThingamajigsBlocks.BRICK_SIDEWALK.get().asItem());
            event.accept(ThingamajigsBlocks.BRICK_SIDEWALK_HB.get().asItem());
            event.accept(ThingamajigsBlocks.FRENCH_BRICK.get().asItem());
            event.accept(ThingamajigsBlocks.ALT_FRENCH_BRICK.get().asItem());
            event.accept(ThingamajigsBlocks.BASIC_BATHROOM_TILE.get().asItem());
            event.accept(ThingamajigsBlocks.STORE_FLOORING.get().asItem());
            event.accept(ThingamajigsBlocks.BOWLING_FLOORING.get().asItem());
            // lanes
            event.accept(ThingamajigsBlocks.OAK_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.SPRUCE_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.BIRCH_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.JUNGLE_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.ACACIA_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.DARK_OAK_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.MANGROVE_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.CHERRY_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.CRIMSON_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.WARPED_LANE.get().asItem());
            event.accept(ThingamajigsBlocks.RUBBER_LANE.get().asItem());
            // wools
            event.accept(ThingamajigsBlocks.OLD_TEAL_WOOL.get().asItem());
            event.accept(ThingamajigsBlocks.LOVE_SEAT_WOOL.get().asItem());
            event.accept(ThingamajigsBlocks.CHECKBOARD_WOOL.get().asItem());
            // Pathways and Carpets
            event.accept(ThingamajigsBlocks.GRAY_PATHWAY.get().asItem());
            event.accept(ThingamajigsBlocks.BROWN_PATHWAY.get().asItem());
            event.accept(ThingamajigsBlocks.RED_PATHWAY.get().asItem());
            // Bookshelves
            event.accept(ThingamajigsBlocks.BLANK_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.ABANDONED_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.BONE_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.BRICK_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.GLOWSTONE_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.EXPERIENCE_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.HISTORIAN_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.EXPLORER_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.POTION_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.EXPENSIVE_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.SCARY_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.RED_TOME_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_TOME_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_TOME_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_TOME_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.POOPSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.PURIFYING_BOOKSHELF.get().asItem());
            event.accept(ThingamajigsBlocks.ANCIENT_BOOKSHELF.get().asItem());
            // Sports & Games
            // sports balls
            event.accept(ThingamajigsBlocks.TENNIS_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.SOCCER_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.BASKETBALL.get().asItem());
            // sports tools
            event.accept(ThingamajigsBlocks.TENNIS_RACKET.get().asItem());
            // sports outdoor and workout equipment
            event.accept(ThingamajigsBlocks.TENNIS_NET.get().asItem());
            event.accept(ThingamajigsBlocks.GOAL.get().asItem());
            event.accept(ThingamajigsItems.FOOTBALL_GOAL.get().asItem());
            event.accept(ThingamajigsBlocks.PUNCHING_BAG.get().asItem());
            event.accept(ThingamajigsBlocks.BASKETBALL_HOOP.get().asItem());
            // mini golf
            event.accept(ThingamajigsBlocks.MINIGOLF_GRASS_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.MINIGOLF_HOLE.get().asItem());
            event.accept(ThingamajigsBlocks.MINIGOLF_FLAG.get().asItem());
            // bowling pins
            event.accept(ThingamajigsBlocks.BOWLING_PIN.get().asItem());
            event.accept(ThingamajigsBlocks.RED_BOWLING_PIN.get().asItem());
            event.accept(ThingamajigsBlocks.GOLD_BOWLING_PIN.get().asItem());
            event.accept(ThingamajigsBlocks.DIAMOND_BOWLING_PIN.get().asItem());
            // bowling balls
            event.accept(ThingamajigsBlocks.BROWN_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.LIME_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHT_BLUE_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.PURPLE_BOWLING_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.PINK_BOWLING_BALL.get().asItem());
            // bowling technical
            event.accept(ThingamajigsBlocks.BOWLING_ALLEY_OILER.get().asItem());
            event.accept(ThingamajigsBlocks.BOWLING_BALL_RETRIEVER.get().asItem());
            event.accept(ThingamajigsBlocks.BOWLING_GAME_CONTROLLER.get().asItem());
            event.accept(ThingamajigsBlocks.PIN_SETTER.get().asItem());
            // arcade machines
            event.accept(ThingamajigsBlocks.ARCADE_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.ARCADE_MACHINE_OPENABLE.get().asItem());
            event.accept(ThingamajigsBlocks.BASKETBALL_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.PINBALL_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHTUP_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.FOOSBALL_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.CLAW_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.GUMBALL_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.HAMMER_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.WACK_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.AIR_HOCKEY_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.SPHERES_AND_RINGS_MACHINE.get().asItem());
            // Other games and things
            event.accept(ThingamajigsBlocks.POOL_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.METAL_POOL_TABLE.get().asItem());
            // Water Park
            event.accept(ThingamajigsBlocks.WATER_SLIDE.get().asItem());
            event.accept(ThingamajigsBlocks.DIVING_BOARD.get().asItem());
            // Commercial Use
            event.accept(ThingamajigsBlocks.MALE_BATHROOM_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.FEMALE_BATHROOM_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.BOTH_BATHROOM_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.BARBER_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.BARBER_HAIR_DRYER.get().asItem());
            event.accept(ThingamajigsBlocks.CHANGE_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.INSET_ATM.get().asItem());
            event.accept(ThingamajigsBlocks.ATM.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYBOX.get().asItem());
            event.accept(ThingamajigsBlocks.BAR_STOOL.get().asItem());
            event.accept(ThingamajigsBlocks.BARREL_KEG.get().asItem());
            event.accept(ThingamajigsBlocks.REFRESHMENT_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.RED_SODA_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_SODA_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.RED_VENDING_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_VENDING_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.CEILING_FAN.get().asItem());
            event.accept(ThingamajigsBlocks.OPEN_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.OPEN_SIGN_ALT.get().asItem());
            event.accept(ThingamajigsBlocks.OPEN_SIGN_ALT_TWO.get().asItem());
            event.accept(ThingamajigsBlocks.OFFICE_PHONE.get().asItem());
            event.accept(ThingamajigsBlocks.FAX_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.WATER_FOUNTAIN.get().asItem());
            event.accept(ThingamajigsBlocks.ICECREAM_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.FRIER.get().asItem());
            event.accept(ThingamajigsBlocks.ICECREAM_DISPLAY.get().asItem());
            event.accept(ThingamajigsBlocks.COMMERCIAL_UTENCIL_DISPENSER.get().asItem());
            event.accept(ThingamajigsBlocks.COMMERCIAL_CONDIMENT_DISPENSER.get().asItem());
            event.accept(ThingamajigsBlocks.COMMERCIAL_JUICE_DISPENSER.get().asItem());
            event.accept(ThingamajigsBlocks.COMMERCIAL_LIQUID_DISPENSER.get().asItem());
            event.accept(ThingamajigsBlocks.CLOTHES_RACK.get().asItem());
            event.accept(ThingamajigsBlocks.ROUND_CLOTHES_RACK.get().asItem());
            event.accept(ThingamajigsBlocks.CASH_REGISTER.get().asItem());
            event.accept(ThingamajigsBlocks.CARD_READER.get().asItem());
            event.accept(ThingamajigsBlocks.SUPERMARKET_CONVEYOR.get().asItem());
            event.accept(ThingamajigsBlocks.STORE_NUMBER_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.AISLE_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.STORE_STAND.get().asItem());
            event.accept(ThingamajigsBlocks.STORE_SHELF.get().asItem());
            event.accept(ThingamajigsBlocks.STORE_FREEZER.get().asItem());
            event.accept(ThingamajigsBlocks.CONVENIENCE_SHELF.get().asItem());
            event.accept(ThingamajigsBlocks.SHOPPING_CART_MOVER.get().asItem());
            event.accept(ThingamajigsBlocks.SHOPPING_CART.get().asItem());
            event.accept(ThingamajigsBlocks.SHOPPING_BASKET_PILE.get().asItem());
            event.accept(ThingamajigsBlocks.SHOPPING_BASKET.get().asItem());
            event.accept(ThingamajigsBlocks.COMMERCIAL_WASHER.get().asItem());
            event.accept(ThingamajigsBlocks.COMMERCIAL_DRYER.get().asItem());
            event.accept(ThingamajigsBlocks.PARKING_METER.get().asItem());
            event.accept(ThingamajigsBlocks.PAYPHONE.get().asItem());
            event.accept(ThingamajigsBlocks.PAYPHONE_SEETHROUGH.get().asItem());
            event.accept(ThingamajigsBlocks.CAMPING_FUEL_CAN.get().asItem());
            event.accept(ThingamajigsBlocks.GAS_PUMP.get().asItem());
            event.accept(ThingamajigsBlocks.NEWSPAPER_DISPENSER.get().asItem());
            event.accept(ThingamajigsBlocks.RESTAURANT_TRASH_CAN.get().asItem());
            event.accept(ThingamajigsBlocks.DUMPSTER.get().asItem());
            event.accept(ThingamajigsBlocks.TRASH_BAG.get().asItem());
            // theater stuff
            event.accept(ThingamajigsItems.THEATER_PROJECTOR.get().asItem());
            event.accept(ThingamajigsBlocks.THEATER_SEAT.get().asItem());
            event.accept(ThingamajigsBlocks.THEATER_SEAT_CONTINUOUS.get().asItem());
            event.accept(ThingamajigsBlocks.POPCORN_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.COTTON_CANDY_MAKER.get().asItem());
            event.accept(ThingamajigsBlocks.HOTDOG_ROTATOR.get().asItem());
            event.accept(ThingamajigsBlocks.SLUSHY_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.TICKET_TELLER_WINDOW.get().asItem());
            event.accept(ThingamajigsBlocks.VELVET_ROPE_FENCE.get().asItem());
            //
            event.accept(ThingamajigsBlocks.CARNIVAL_AWNING.get().asItem());
            event.accept(ThingamajigsBlocks.PORTA_POTTY.get().asItem());
            event.accept(ThingamajigsBlocks.CATWALK_CENTER.get().asItem());
            event.accept(ThingamajigsBlocks.CATWALK.get().asItem());
            event.accept(ThingamajigsBlocks.CONVEYOR_BELT.get().asItem());
            event.accept(ThingamajigsBlocks.ESCALATOR.get().asItem());
            event.accept(ThingamajigsBlocks.ESCALATOR_DOWN.get().asItem());
            event.accept(ThingamajigsBlocks.TEACHING_BOARD.get().asItem());
            event.accept(ThingamajigsBlocks.CLEVER_BLACKBOARD.get().asItem());
            event.accept(ThingamajigsBlocks.SCHOOL_DESK.get().asItem());
            event.accept(ThingamajigsBlocks.LOCKER.get().asItem());
            event.accept(ThingamajigsBlocks.LIBRARY_STOOL.get().asItem());
            // Electronics
            event.accept(ThingamajigsBlocks.CLASSIC_TV.get().asItem());
            event.accept(ThingamajigsBlocks.TV.get().asItem());
            event.accept(ThingamajigsBlocks.WALL_TV.get().asItem());
            event.accept(ThingamajigsBlocks.BIG_TV.get().asItem());
            event.accept(ThingamajigsBlocks.ULTRA_HD_TV.get().asItem());
            event.accept(ThingamajigsBlocks.OLD_PC_MONITOR.get().asItem());
            event.accept(ThingamajigsBlocks.MODERN_PC_MONITOR.get().asItem());
            event.accept(ThingamajigsBlocks.CURVED_MONITOR.get().asItem());
            event.accept(ThingamajigsBlocks.COMPUTER_CONTROLS.get().asItem());
            event.accept(ThingamajigsBlocks.PC_CONTROLS.get().asItem());
            event.accept(ThingamajigsBlocks.RGB_PC_CONTROLS.get().asItem());
            event.accept(ThingamajigsBlocks.DVD_PLAYER.get().asItem());
            event.accept(ThingamajigsBlocks.VHS_PLAYER.get().asItem());
            event.accept(ThingamajigsBlocks.CORNER_COMPUTER.get().asItem());
            event.accept(ThingamajigsBlocks.CORNER_COMPUTER_WM.get().asItem());
            event.accept(ThingamajigsBlocks.OLD_PC.get().asItem());
            event.accept(ThingamajigsBlocks.BROKEN_COMPUTER.get().asItem());
            event.accept(ThingamajigsBlocks.OLD_FLAT_COMPUTER.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEY_DESKTOP_COMPUTER.get().asItem());
            event.accept(ThingamajigsBlocks.GAMING_PC.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYTOSH_LAPTOP_OLD.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYDOWS_LAPTOP.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYTOSH_LAPTOP.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYTOSH_STUDIO.get().asItem());
            event.accept(ThingamajigsBlocks.FUNDEVICE_GAME_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.RARE_BLUE_GRAY_GAME_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_GAME_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.BOXY_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.ORANGE_BOXY_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.TALL_BOXY_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYCUBE_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEMAN_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.GOLDME_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYSNAP_BASE.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYSNAP_CONSOLE.get().asItem());
            event.accept(ThingamajigsBlocks.ANALOG_CLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.CLOCK_RADIO.get().asItem());
            event.accept(ThingamajigsBlocks.PRINTER.get().asItem());
            event.accept(ThingamajigsBlocks.PROJECTOR.get().asItem());
            event.accept(ThingamajigsBlocks.VIDEO_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.PROFESSIONAL_TV_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.STUDIO_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_TELEPHONE.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_TELEPHONE.get().asItem());
            event.accept(ThingamajigsBlocks.GENERAL_DIGITAL_PHONE.get().asItem());
            event.accept(ThingamajigsBlocks.FEATURED_CORDLESS_PHONE.get().asItem());
            event.accept(ThingamajigsBlocks.MOBILE_PHONE.get().asItem());
            event.accept(ThingamajigsBlocks.SMARTPHONE.get().asItem());
            event.accept(ThingamajigsBlocks.GRAPHICS_CARD.get().asItem());
            event.accept(ThingamajigsBlocks.HARD_DRIVE.get().asItem());
            event.accept(ThingamajigsBlocks.INTERNET_MODEM.get().asItem());
            event.accept(ThingamajigsBlocks.INTERNET_ROUTER.get().asItem());
            event.accept(ThingamajigsBlocks.NEWER_INTERNET_ROUTER.get().asItem());
            event.accept(ThingamajigsBlocks.WIFI_ROUTER.get().asItem());
            // instruments
            event.accept(ThingamajigsBlocks.STRING_BASS.get().asItem());
            event.accept(ThingamajigsBlocks.BONGOS.get().asItem());
            event.accept(ThingamajigsBlocks.CONGAS.get().asItem());
            event.accept(ThingamajigsBlocks.CAJON.get().asItem());
            event.accept(ThingamajigsBlocks.BASS_DRUM.get().asItem());
            event.accept(ThingamajigsBlocks.SNARE_DRUM.get().asItem());
            event.accept(ThingamajigsBlocks.FLOOR_TOM.get().asItem());
            event.accept(ThingamajigsBlocks.RACK_TOM.get().asItem());
            event.accept(ThingamajigsBlocks.HI_HAT.get().asItem());
            event.accept(ThingamajigsBlocks.CYMBAL_CRASH.get().asItem());
            // audio stuff
            event.accept(ThingamajigsBlocks.VOICE_MICROPHONE.get().asItem());
            event.accept(ThingamajigsBlocks.SPEAKER.get().asItem());
            event.accept(ThingamajigsBlocks.AUDIO_MIXER.get().asItem());
            event.accept(ThingamajigsBlocks.AUDIO_CONTROLLER.get().asItem());
            // dj stuffs
            event.accept(ThingamajigsBlocks.DISCO_BALL.get().asItem());
            event.accept(ThingamajigsBlocks.GOBO_LIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.DJ_LASER_LIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.TURNTABLE.get().asItem());
            // Utilities
            event.accept(ThingamajigsBlocks.AC_DUCT.get().asItem());
            event.accept(ThingamajigsBlocks.AC_DUCT_CORNER.get().asItem());
            event.accept(ThingamajigsBlocks.AC_DUCT_ALLWAY.get().asItem());
            event.accept(ThingamajigsBlocks.AIRDUCT_VENT.get().asItem());
            event.accept(ThingamajigsBlocks.PTAC_AC.get().asItem());
            event.accept(ThingamajigsBlocks.AIR_CONDITIONER.get().asItem());
            event.accept(ThingamajigsBlocks.AC_THERMOSTAT.get().asItem());
            event.accept(ThingamajigsBlocks.GAS_HEATER.get().asItem());
            event.accept(ThingamajigsBlocks.WATER_SOFTENER.get().asItem());
            event.accept(ThingamajigsBlocks.SALT_TANK.get().asItem());
            event.accept(ThingamajigsBlocks.SOLAR_PANEL.get().asItem());
            event.accept(ThingamajigsBlocks.SERVER_RACK.get().asItem());
            // power outlets and switches or buttons
            event.accept(ThingamajigsBlocks.HOME_BREAKER.get().asItem());
            event.accept(ThingamajigsBlocks.UNGROUNDED_US_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.US_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.ALT_US_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.T_US_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.INTERNET_JACK_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.USB_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.UK_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.GERMAN_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.AUSTRALIAN_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.BUTTON_SWITCH.get().asItem());
            event.accept(ThingamajigsBlocks.ROCKER_SWITCH.get().asItem());
            // door bells
            event.accept(ThingamajigsBlocks.DOOR_BELL.get().asItem());
            event.accept(ThingamajigsBlocks.METALLIC_DOOR_BELL.get().asItem());
            event.accept(ThingamajigsBlocks.PLUCK_DOOR_BELL.get().asItem());
            event.accept(ThingamajigsBlocks.OLD_DOOR_BELL.get().asItem());
            // fire prevention & protection
            event.accept(ThingamajigsBlocks.FIRE_EXTINGUISHER.get().asItem());
            event.accept(ThingamajigsBlocks.FIRE_DETECTOR.get().asItem());
            event.accept(ThingamajigsBlocks.BEEP_FIRE_ALARM.get().asItem());
            event.accept(ThingamajigsBlocks.HORN_FIRE_ALARM.get().asItem());
            event.accept(ThingamajigsBlocks.LOUD_FIRE_ALARM.get().asItem());
            event.accept(ThingamajigsBlocks.FIRE_ESCAPE_LADDER.get().asItem());
            // security
            event.accept(ThingamajigsBlocks.FILM_SECURITY_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.ROBOT_SECURITY_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.BOX_SECURITY_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.SECURE_SECURITY_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.DOME_SECURITY_CAMERA.get().asItem());
            event.accept(ThingamajigsBlocks.CHAINLINK_FENCE.get().asItem());
            event.accept(ThingamajigsBlocks.SECURITY_METAL_DETECTOR.get().asItem());
            // water management
            event.accept(ThingamajigsBlocks.CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.DIRT_CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.SAND_CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.SANDSTONE_CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.STONE_CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.TERRACOTTA_CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.BRICK_CULVERT.get().asItem());
            event.accept(ThingamajigsBlocks.STONE_BRICK_CULVERT.get().asItem());
            // hydrants
            event.accept(ThingamajigsBlocks.RED_FIRE_HYDRANT.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_FIRE_HYDRANT.get().asItem());
            event.accept(ThingamajigsBlocks.SILVER_FIRE_HYDRANT.get().asItem());
            // hazard signs
            event.accept(ThingamajigsBlocks.BIO_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.BLAST_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.CRYO_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.HARDHAT_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.DEATH_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.FIRE_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.GENERAL_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.NOENTRY_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.FALLING_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.POISON_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.RADIATION_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.SHOCK_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.WORKERS_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.RADIOWAVES_HAZARD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.OXYGEN_HAZARD_SIGN.get().asItem());
            // Home Appliances & Other
            // misc. home things
            event.accept(ThingamajigsBlocks.BUTTER_CHURNER.get().asItem());
            event.accept(ThingamajigsBlocks.GARDEN_GNOME.get().asItem());
            event.accept(ThingamajigsBlocks.GARDEN_HOSE.get().asItem());
            event.accept(ThingamajigsBlocks.PICNIC_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.AQUARIUM.get().asItem());
            event.accept(ThingamajigsBlocks.DOG_HOUSE.get().asItem());
            event.accept(ThingamajigsBlocks.CAT_TREE.get().asItem());
            event.accept(ThingamajigsBlocks.LITTER_BOX.get().asItem());
            event.accept(ThingamajigsBlocks.HOTTUB.get().asItem());
            event.accept(ThingamajigsBlocks.MAILBOX.get().asItem());
            event.accept(ThingamajigsBlocks.GREY_MAILBOX.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_MAILBOX.get().asItem());
            event.accept(ThingamajigsBlocks.LAWN_MOWER.get().asItem());
            event.accept(ThingamajigsBlocks.GENERATOR.get().asItem());
            event.accept(ThingamajigsBlocks.CEILING_FAN.get().asItem());
            event.accept(ThingamajigsBlocks.WHITE_FAN.get().asItem());
            event.accept(ThingamajigsBlocks.GRAY_FAN.get().asItem());
            event.accept(ThingamajigsBlocks.BLACK_FAN.get().asItem());
            event.accept(ThingamajigsBlocks.LAVA_LAMP.get().asItem());
            event.accept(ThingamajigsBlocks.STANDING_VACUUM.get().asItem());
            event.accept(ThingamajigsBlocks.SHOP_VACUUM.get().asItem());
            event.accept(ThingamajigsBlocks.STEAM_CLEANER.get().asItem());
            event.accept(ThingamajigsBlocks.CALENDAR.get().asItem());
            event.accept(ThingamajigsBlocks.CRIB.get().asItem());
            event.accept(ThingamajigsBlocks.BABY_CARRIAGE.get().asItem());
            event.accept(ThingamajigsBlocks.GAS_CAN.get().asItem());
            event.accept(ThingamajigsBlocks.SATELLITE_DISH.get().asItem());
            event.accept(ThingamajigsBlocks.ANTENNA.get().asItem());
            event.accept(ThingamajigsBlocks.OLD_WOODEN_PHONE.get().asItem());
            event.accept(ThingamajigsBlocks.WHEELBARROW.get().asItem());
            event.accept(ThingamajigsBlocks.TRASH_CAN.get().asItem());
            event.accept(ThingamajigsBlocks.RECYCLE_BIN.get().asItem());
            event.accept(ThingamajigsBlocks.LAUNDRY_BASKET.get().asItem());
            event.accept(ThingamajigsBlocks.LAUNDRY_PILE.get().asItem());
            event.accept(ThingamajigsBlocks.THERMOMETER.get().asItem());
            event.accept(ThingamajigsBlocks.AIR_PURIFIER.get().asItem());
            event.accept(ThingamajigsBlocks.SPACE_HEATER.get().asItem());
            event.accept(ThingamajigsBlocks.VHS_COLLECTION.get().asItem());
            event.accept(ThingamajigsBlocks.DVD_COLLECTION.get().asItem());
            event.accept(ThingamajigsBlocks.TALL_LAMP.get().asItem());
            event.accept(ThingamajigsBlocks.DESK_LAMP.get().asItem());
            // eating utensils
            event.accept(ThingamajigsBlocks.PLATE.get().asItem());
            event.accept(ThingamajigsBlocks.CUP.get().asItem());
            event.accept(ThingamajigsBlocks.COOKIE_JAR.get().asItem());
            event.accept(ThingamajigsBlocks.EATING_UTENCILS.get().asItem());
            // furniture
            // tables
            event.accept(ThingamajigsBlocks.STONE_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.QUARTZ_TABLE.get().asItem());
            // copper tables
            event.accept(ThingamajigsBlocks.WAXED_COPPER_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.IRON_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.GOLD_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.DIAMOND_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.NETHER_BRICK_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.PRISMARINE_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.PURPUR_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.SCULK_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.TRIPLE_SHELF.get().asItem());
            // chairs
            event.accept(ThingamajigsBlocks.BARBER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.STONE_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.QUARTZ_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.IRON_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.GOLD_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.DIAMOND_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.NETHER_BRICK_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.PRISMARINE_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.PURPUR_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.SCULK_CHAIR.get().asItem());
            event.accept(ThingamajigsBlocks.POOP_CHAIR.get().asItem());
            // couches and seats
            event.accept(ThingamajigsBlocks.LOVE_SEAT.get().asItem());
            event.accept(ThingamajigsBlocks.LOVE_COUCH.get().asItem());
            event.accept(ThingamajigsBlocks.MYSTERIOUS_ONE_COUCH.get().asItem());
            // appliances
            event.accept(ThingamajigsBlocks.WATER_DISPENSER.get().asItem());
            event.accept(ThingamajigsBlocks.DRYER.get().asItem());
            event.accept(ThingamajigsBlocks.WASHER.get().asItem());
            event.accept(ThingamajigsBlocks.DISHWASHER_WALL.get().asItem());
            event.accept(ThingamajigsBlocks.PORTABLE_DISH_WASHER.get().asItem());
            event.accept(ThingamajigsBlocks.HUMIDIFIER.get().asItem());
            event.accept(ThingamajigsBlocks.DEHUMIDIFIER.get().asItem());
            event.accept(ThingamajigsBlocks.TOOL_STATION.get().asItem());
            event.accept(ThingamajigsBlocks.FREEZER.get().asItem());
            event.accept(ThingamajigsBlocks.FRIDGE.get().asItem());
            event.accept(ThingamajigsBlocks.MINI_FRIDGE.get().asItem());
            event.accept(ThingamajigsBlocks.STOVE_HOOD.get().asItem());
            event.accept(ThingamajigsBlocks.STOVE.get().asItem());
            event.accept(ThingamajigsBlocks.SEWING_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.IRONING_TABLE.get().asItem());
            // Kitchen Stuff
            event.accept(ThingamajigsBlocks.KITCHEN_SINK.get().asItem());
            event.accept(ThingamajigsBlocks.MICROWAVE.get().asItem());
            event.accept(ThingamajigsBlocks.TOASTER_OVEN.get().asItem());
            event.accept(ThingamajigsBlocks.TOASTER.get().asItem());
            event.accept(ThingamajigsBlocks.OVEN.get().asItem());
            event.accept(ThingamajigsBlocks.PAPER_TOWEL.get().asItem());
            event.accept(ThingamajigsBlocks.FOOD_PROCESSOR.get().asItem());
            event.accept(ThingamajigsBlocks.BLENDER.get().asItem());
            event.accept(ThingamajigsBlocks.STAND_MIXER.get().asItem());
            event.accept(ThingamajigsBlocks.JUICER.get().asItem());
            event.accept(ThingamajigsBlocks.RICE_COOKER.get().asItem());
            event.accept(ThingamajigsBlocks.SLOW_COOKER.get().asItem());
            event.accept(ThingamajigsBlocks.INSTANT_POT.get().asItem());
            event.accept(ThingamajigsBlocks.AIR_FRYER.get().asItem());
            event.accept(ThingamajigsBlocks.BREAD_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.WAFFLE_IRON.get().asItem());
            event.accept(ThingamajigsBlocks.PANINI_MAKER.get().asItem());
            event.accept(ThingamajigsBlocks.ICE_CREAM_MAKER.get().asItem());
            event.accept(ThingamajigsBlocks.YOGURT_MAKER.get().asItem());
            event.accept(ThingamajigsBlocks.COFFEE_GRINDER.get().asItem());
            event.accept(ThingamajigsBlocks.FRENCH_PRESS.get().asItem());
            event.accept(ThingamajigsBlocks.COFFEE_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.FOOD_DEHYDRATOR.get().asItem());
            event.accept(ThingamajigsBlocks.SMOKER_GRILL.get().asItem());
            // Bathroom Stuff
            event.accept(ThingamajigsBlocks.MIRROR.get().asItem());
            event.accept(ThingamajigsBlocks.SMALL_SINK.get().asItem());
            event.accept(ThingamajigsBlocks.FANCY_SINK.get().asItem());
            event.accept(ThingamajigsBlocks.TOWEL_STACK.get().asItem());
            event.accept(ThingamajigsBlocks.SHOWER_HANDLES.get().asItem());
            event.accept(ThingamajigsBlocks.SHOWER_HEAD.get().asItem());
            event.accept(ThingamajigsBlocks.BATHTUB_NOZZLE.get().asItem());
            event.accept(ThingamajigsBlocks.PLUNGER.get().asItem());
            event.accept(ThingamajigsBlocks.TOILET.get().asItem());
            event.accept(ThingamajigsBlocks.DENTAL_CUP.get().asItem());
            event.accept(ThingamajigsBlocks.TOILET_PAPER.get().asItem());
            event.accept(ThingamajigsBlocks.TISSUE_BOX.get().asItem());
            // Toys & Kids Stuff
            event.accept(ThingamajigsBlocks.TOY_BOX.get().asItem());
            event.accept(ThingamajigsBlocks.WOOD_DUCK.get().asItem());
            event.accept(ThingamajigsBlocks.WOOD_CAR.get().asItem());
            event.accept(ThingamajigsBlocks.TEDDY_BEAR.get().asItem());
            event.accept(ThingamajigsBlocks.MRPUPPY.get().asItem());
            event.accept(ThingamajigsBlocks.REINDEER_PLUSHY.get().asItem());
            event.accept(ThingamajigsBlocks.SNOWMAN_PLUSHY.get().asItem());
            event.accept(ThingamajigsBlocks.STEVE_PLUSHY.get().asItem());
            event.accept(ThingamajigsBlocks.ZOMBIE_PLUSHIE.get().asItem());
            event.accept(ThingamajigsBlocks.CREEPER_PLUSHY.get().asItem());
            // Hospital and Health
            event.accept(ThingamajigsBlocks.HOSPITAL_COVER.get().asItem());
            event.accept(ThingamajigsBlocks.HOSPITAL_BED.get().asItem());
            event.accept(ThingamajigsBlocks.HOSPITAL_COMPUTER.get().asItem());
            event.accept(ThingamajigsBlocks.HEART_MONITOR.get().asItem());
            event.accept(ThingamajigsBlocks.IV.get().asItem());
            event.accept(ThingamajigsBlocks.OPERATION_TABLE.get().asItem());
            event.accept(ThingamajigsBlocks.OPERATION_TOOLS.get().asItem());
            // Science
            event.accept(ThingamajigsBlocks.MICROSCOPE.get().asItem());
            event.accept(ThingamajigsBlocks.CHEMICAL_TUBE.get().asItem());
            event.accept(ThingamajigsBlocks.BEAKER.get().asItem());
            event.accept(ThingamajigsBlocks.FLASK.get().asItem());
            // Packed & Bulk Items
            event.accept(ThingamajigsBlocks.WATER_BOTTLE_PACK.get().asItem());
            event.accept(ThingamajigsBlocks.BULK_PRODUCT.get().asItem());
            event.accept(ThingamajigsBlocks.CARDBOARD_BOX.get().asItem());
            // Graveyards & Death
            event.accept(ThingamajigsBlocks.COFFIN.get().asItem());
            event.accept(ThingamajigsBlocks.CROSS_GRAVESTONE.get().asItem());
            event.accept(ThingamajigsBlocks.STANDARD_GRAVESTONE.get().asItem());
            event.accept(ThingamajigsBlocks.PLACARD_GRAVESTONE.get().asItem());
            // Other
            event.accept(ThingamajigsItems.UMBRELLA.get().asItem());
            event.accept(ThingamajigsBlocks.CHIMNEY.get().asItem());
            event.accept(ThingamajigsItems.ANIMATED_ICE_RINK.get().asItem());
            event.accept(ThingamajigsBlocks.SNOW_MACHINE.get().asItem());
            event.accept(ThingamajigsBlocks.BALL_PIT.get().asItem());
            // Seasonal (Christmas)
            event.accept(ThingamajigsBlocks.CHRISTMAS_FIREPLACE.get().asItem());
            event.accept(ThingamajigsBlocks.SLEIGH.get().asItem());
            event.accept(ThingamajigsBlocks.GINGERBREAD_HOUSE.get().asItem());
            event.accept(ThingamajigsBlocks.NUTCRACKER.get().asItem());
            event.accept(ThingamajigsBlocks.NORTH_POLE.get().asItem());
            event.accept(ThingamajigsBlocks.SANTA_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.SANTA_INFLATABLE.get().asItem());
            event.accept(ThingamajigsBlocks.SNOWMAN.get().asItem());
            event.accept(ThingamajigsBlocks.SNOWMAN_BLUEMAN_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.CHRISTMAS_WREATH.get().asItem());
            event.accept(ThingamajigsBlocks.SMALL_CHRISTMAS_TREE.get().asItem());
            event.accept(ThingamajigsBlocks.CHRISTMAS_TREE.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHTED_CHRISTMAS_TREE.get().asItem());
            event.accept(ThingamajigsBlocks.PRESENT_PILE.get().asItem());
            event.accept(ThingamajigsBlocks.LIGHTED_DEER.get().asItem());
            event.accept(ThingamajigsItems.ANIMATED_DEER.get().asItem());
            event.accept(ThingamajigsBlocks.CHRISTMAS_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.CHRISTMAS_LIGHTS_ALT.get().asItem());
            event.accept(ThingamajigsBlocks.AMBER_STRING_LIGHTS.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_STRING_LIGHTS.get().asItem());
            // Food Related
            event.accept(ThingamajigsBlocks.FOOD_COOLER.get().asItem());
            event.accept(ThingamajigsBlocks.WINE_BOTTLE.get().asItem());
            event.accept(ThingamajigsBlocks.ROAST_TURKEY.get().asItem());
            event.accept(ThingamajigsBlocks.PIZZA.get().asItem());
            event.accept(ThingamajigsBlocks.PIZZA_BOX.get().asItem());
            event.accept(ThingamajigsBlocks.TENTH_ANNIVERSARY_CAKE.get().asItem());
            event.accept(ThingamajigsBlocks.CHEESE_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.GLOWING_CHEESE_BLOCK.get().asItem());
            // Misc. Junk
            event.accept(ThingamajigsBlocks.TNT_SLAB.get().asItem());
            event.accept(ThingamajigsBlocks.POWDER_KEG.get().asItem());
            event.accept(ThingamajigsBlocks.BIOHAZARD_BIN.get().asItem());
            event.accept(ThingamajigsBlocks.RADIOACTIVE_BARREL.get().asItem());
            event.accept(ThingamajigsBlocks.FIREWORKS_DISPLAY.get().asItem());
            event.accept(ThingamajigsBlocks.ITEM_DISPLAY_BLOCK.get().asItem());

            event.accept(ThingamajigsBlocks.BLUEMAN_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.SPECIAL_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.PARTICULAR_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.FURIOUS_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.CATCHING_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.SORROW_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.BONDING_STATUE.get().asItem());
            event.accept(ThingamajigsItems.STRANGE_STATUE.get());
            event.accept(ThingamajigsBlocks.DUCK_STATUE.get().asItem());
            event.accept(ThingamajigsBlocks.VALIANT_STATUE.get().asItem());
            // put copper statue here
            //
            event.accept(ThingamajigsBlocks.TRI_CANDLE_HOLDER_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.REINDEER_WALL_HEAD.get().asItem());
            event.accept(ThingamajigsBlocks.WARDEN_TROPHY.get().asItem());
            event.accept(ThingamajigsBlocks.HEAD_CANDLE.get().asItem());
            event.accept(ThingamajigsBlocks.POOP.get().asItem());
            event.accept(ThingamajigsBlocks.FULL_POOP_BLOCK.get().asItem());
            event.accept(ThingamajigsBlocks.BYPRODUCT.get().asItem());
            // umbrellas
            // red-stone-ish-y blocks
            event.accept(ThingamajigsBlocks.THINGAMAJIG_STATE_CELL.get().asItem());
            event.accept(ThingamajigsItems.SPRING_BLOCK_ITEM.get());
            // balloon blocks
            event.accept(ThingamajigsItems.BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.LIGHT_GRAY_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.GRAY_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.BLACK_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.BROWN_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.RED_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.ORANGE_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.YELLOW_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.LIME_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.GREEN_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.TEAL_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.CYAN_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.LIGHT_BLUE_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.BLUE_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.PURPLE_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.MAGENTA_BALLOON_BLOCK_ITEM.get());
            event.accept(ThingamajigsItems.PINK_BALLOON_BLOCK_ITEM.get());
            // neon-ish blocks
            event.accept(ThingamajigsBlocks.GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.LIGHT_GRAY_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.GRAY_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.BLACK_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.BROWN_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.RED_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.ORANGE_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.YELLOW_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.LIME_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.GREEN_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.CYAN_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.LIGHT_BLUE_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.BLUE_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.PURPLE_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.MAGENTA_GLOW_BLOCK.get());
            event.accept(ThingamajigsBlocks.PINK_GLOW_BLOCK.get());
        }
        // all road signs tabs (makes it easier to find what you want)
        if(event.getTab() == ThingamajigsCreativeTab.ROAD_SIGNS_TAB.get()){
            // Road Name Signs
            event.accept(ThingamajigsItems.GREEN_ROADWAY_SIGN_ITEM.get().asItem());
            event.accept(ThingamajigsItems.RED_ROADWAY_SIGN_ITEM.get().asItem());
            event.accept(ThingamajigsItems.BLUE_ROADWAY_SIGN_ITEM.get().asItem());
            event.accept(ThingamajigsItems.BROWN_ROADWAY_SIGN_ITEM.get().asItem());
            // hanging road name signs
            event.accept(ThingamajigsItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.get().asItem());
            // security signs
            event.accept(ThingamajigsBlocks.ORANGE_PRIVATE_PROPERTY_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.VIDEO_IN_PROGRESS_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.NO_STARING_PRIVATE_PROPERTY_SIGN.get().asItem());
            // Road Signs
            // railroad crossing related signs
            event.accept(ThingamajigsBlocks.RR_AHEAD_OLD.get().asItem());
            event.accept(ThingamajigsBlocks.RR_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.CATEYE_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.INVERTED_CATEYE_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.GLOWING_INVERTED_CATEYE_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.CROSSBUCK.get().asItem());
            // these signs were added because they were the most tastefully unique... (language correct MADE me add the 'tasteful' part)
            // international rr-related signs
            event.accept(ThingamajigsBlocks.CZECH_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.FINNISH_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.GERMAN_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.AUSTRALIA_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.CANADIAN_CROSSBUCK.get().asItem());
            event.accept(ThingamajigsBlocks.JAPAN_CROSSBUCK.get().asItem());
            // stop signs
            event.accept(ThingamajigsBlocks.STOP_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.YELLOW_STOP_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.GREEN_STOP_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.BLUE_STOP_SIGN.get().asItem());
            // speed limit ahead
            event.accept(ThingamajigsBlocks.SPEED_LIMIT_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.SCHOOL_SPEED_LIMIT_AHEAD.get().asItem());
            // speed limit
            event.accept(ThingamajigsBlocks.SPEED_LIMIT_10.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_LIMIT_20.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_LIMIT_30.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_LIMIT_40.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_LIMIT_50.get().asItem());
            // other signs (sorry too lazy)
            event.accept(ThingamajigsBlocks.NO_PASSING_ZONE_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.YIELD_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.YIELD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.SLOW_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.CROSSING_AHEAD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.CROSSING_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.EXIT_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.EXIT_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.BUMP.get().asItem());
            event.accept(ThingamajigsBlocks.DIP.get().asItem());
            event.accept(ThingamajigsBlocks.DEAD_END.get().asItem());
            event.accept(ThingamajigsBlocks.EMERGENCY_SCENE_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.BOMB_THREAT_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.TNT_THREAT.get().asItem());
            event.accept(ThingamajigsBlocks.PILLAGER_SCENE.get().asItem());
            event.accept(ThingamajigsBlocks.CRIME_SCENE_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.ONLY_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.ONLY_UP.get().asItem());
            event.accept(ThingamajigsBlocks.ONLY_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.HOV_ONLY.get().asItem());
            event.accept(ThingamajigsBlocks.HOV_ENDS.get().asItem());
            event.accept(ThingamajigsBlocks.NO_OUTLET.get().asItem());
            event.accept(ThingamajigsBlocks.NO_LEFT_TURN.get().asItem());
            event.accept(ThingamajigsBlocks.NO_RIGHT_TURN.get().asItem());
            event.accept(ThingamajigsBlocks.NO_STRAIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.NO_U_TURN.get().asItem());
            event.accept(ThingamajigsBlocks.DO_NOT_ENTER.get().asItem());
            event.accept(ThingamajigsBlocks.NO_PARKING.get().asItem());
            event.accept(ThingamajigsBlocks.PARKING_PERMITTED_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.HAZARDOUS_MATERIALS.get().asItem());
            event.accept(ThingamajigsBlocks.NO_HAZARDOUS_MATERIALS.get().asItem());
            event.accept(ThingamajigsBlocks.SPEEDING_WORKERS_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_HUMP_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.TOLL_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.TWAS_DIP_SIGN.get().asItem());
            //
            event.accept(ThingamajigsBlocks.ANGLED_BIG_MERGE_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.ANGLED_BIG_MERGE_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.BIG_LEFT_MERGES.get().asItem());
            event.accept(ThingamajigsBlocks.BIG_RIGHT_MERGES.get().asItem());
            event.accept(ThingamajigsBlocks.BIKE.get().asItem());
            event.accept(ThingamajigsBlocks.CAR.get().asItem());
            event.accept(ThingamajigsBlocks.TRUCK.get().asItem());
            event.accept(ThingamajigsBlocks.FIRE_TRUCK.get().asItem());
            event.accept(ThingamajigsBlocks.BUS_STOP_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.CROSSING_SIGN_F.get().asItem());
            event.accept(ThingamajigsBlocks.CROSSING_AHEAD_F.get().asItem());
            event.accept(ThingamajigsBlocks.DEER.get().asItem());
            event.accept(ThingamajigsBlocks.EQUESTRIAN.get().asItem());
            event.accept(ThingamajigsBlocks.TRAM_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.MINECART_CROSSING.get().asItem());
            event.accept(ThingamajigsBlocks.GOLF_CART_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.FARMER_ORIGINAL.get().asItem());
            event.accept(ThingamajigsBlocks.TRACTOR.get().asItem());
            event.accept(ThingamajigsBlocks.TRUCK_SLOPE.get().asItem());
            event.accept(ThingamajigsBlocks.DIAGONAL_TRACK_LEFTORRIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.DIAGONAL_TRACK_RIGHTORLEFT.get().asItem());
            event.accept(ThingamajigsBlocks.DIVIDER_START.get().asItem());
            event.accept(ThingamajigsBlocks.DIVIDER_ENDS.get().asItem());
            event.accept(ThingamajigsBlocks.FALLING_ROCKS.get().asItem());
            event.accept(ThingamajigsBlocks.FORWARD_OR_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.FORWARD_OR_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.HIGH_TRACK.get().asItem());
            event.accept(ThingamajigsBlocks.HORIZONTAL_TRACK.get().asItem());
            event.accept(ThingamajigsBlocks.LANES.get().asItem());
            event.accept(ThingamajigsBlocks.LEFT_LANE_ENDS.get().asItem());
            event.accept(ThingamajigsBlocks.RIGHT_LANE_ENDS.get().asItem());
            event.accept(ThingamajigsBlocks.LEFT_MERGES.get().asItem());
            event.accept(ThingamajigsBlocks.RIGHT_MERGES.get().asItem());
            event.accept(ThingamajigsBlocks.SINGLE_TO_MULTI.get().asItem());
            event.accept(ThingamajigsBlocks.MULTI_TO_SINGLE.get().asItem());
            event.accept(ThingamajigsBlocks.PAVEMENT_ENDS_OLD.get().asItem());
            event.accept(ThingamajigsBlocks.NARROW_BRIDGE.get().asItem());
            event.accept(ThingamajigsBlocks.PED_DISABLED.get().asItem());
            event.accept(ThingamajigsBlocks.PLANT_ROAD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.PLAYGROUND.get().asItem());
            event.accept(ThingamajigsBlocks.PLUS.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_TWISTY_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_TWISTY_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.ROUNDABOUT_OLD.get().asItem());
            event.accept(ThingamajigsBlocks.ROUNDABOUT.get().asItem());
            event.accept(ThingamajigsBlocks.SEATBELT.get().asItem());
            event.accept(ThingamajigsBlocks.THINK_BEFORE_YOU_THROW.get().asItem());
            event.accept(ThingamajigsBlocks.BIG_ARROW_ROAD_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.ARROW_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.SHOULDER_DROP_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.SLIPPERY_WHEN_WET.get().asItem());
            event.accept(ThingamajigsBlocks.SMALL_TO_BIG_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.SMALL_TO_BIG_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.SMILEY_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.STOP_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.TRACK_LEFT_SIDE.get().asItem());
            event.accept(ThingamajigsBlocks.TRACK_RIGHT_SIDE.get().asItem());
            event.accept(ThingamajigsBlocks.TRAFFIC_LIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.VERTICAL_TRACK.get().asItem());
            event.accept(ThingamajigsBlocks.EFE_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.TFF_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.CHEVRON.get().asItem());
            event.accept(ThingamajigsBlocks.DISABLED_PARKING_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.DISABLED_PARKING_SIGN_ALT.get().asItem());
            event.accept(ThingamajigsBlocks.DISABLED_PARKING_SIGN_ALT_TWO.get().asItem());
            event.accept(ThingamajigsBlocks.RESERVED_DISABLED_PARKING.get().asItem());
            event.accept(ThingamajigsBlocks.RESERVED_DISABLED_PARKING_ALT.get().asItem());
            event.accept(ThingamajigsBlocks.STOP_HERE_ON_RED.get().asItem());
            event.accept(ThingamajigsBlocks.LOW_POWER_LINES.get().asItem());
            event.accept(ThingamajigsBlocks.HAWK_SIGNAL_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.NO_PEDS.get().asItem());
            event.accept(ThingamajigsBlocks.STAY_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.STAY_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.INTERSTATE_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.RECTANGLE_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.BIG_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.BLUEYPASS_ONLY.get().asItem());
            event.accept(ThingamajigsBlocks.DIVIDER.get().asItem());
            event.accept(ThingamajigsBlocks.ONE_WAY_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.ONE_WAY_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.DIVIDED_WAY.get().asItem());
            event.accept(ThingamajigsBlocks.GRID_SIGN.get().asItem());
            event.accept(ThingamajigsBlocks.WRONG_WAY.get().asItem());
            // half signs
            event.accept(ThingamajigsBlocks.AHEAD_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.AHEAD_YELLOW_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.ALL_DAY_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.ALL_WAY_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.FOUR_WAY_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.THREE_WAY_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.TWO_WAY_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.ONE_WAY_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.ENTER_AHEAD_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.EXEMPT_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.EXEMPT_YELLOW_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.MON_FRI_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.SAT_SUN_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.ONLY_THRU_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.ONLY_THRU_RIGHT.get().asItem());
            event.accept(ThingamajigsBlocks.SCHOOL_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.SCHOOL_Y_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.TO_ROUTE_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.TO_TOLL_HALF.get().asItem());
            event.accept(ThingamajigsBlocks.WHEN_FLASHING_HALF.get().asItem());
            // construction signs
            event.accept(ThingamajigsBlocks.ROAD_WORK_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.BRIDGE_OUT_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.CONST_ROAD_CLOSED_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.BLASTING_ZONE_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.FLAGGER_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.WORKERS_PRESENT.get().asItem());
            event.accept(ThingamajigsBlocks.BPTS.get().asItem());
            event.accept(ThingamajigsBlocks.UTIL_WORK_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.DETOUR_ARROW_LEFT.get().asItem());
            event.accept(ThingamajigsBlocks.DETOUR_ARROW_RIGHT.get().asItem());
            // uk signs
            event.accept(ThingamajigsBlocks.UK_STOP_ON_RED.get().asItem());
            event.accept(ThingamajigsBlocks.ASCENT.get().asItem());
            event.accept(ThingamajigsBlocks.DESCENT.get().asItem());
            event.accept(ThingamajigsBlocks.BENDS.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_CROSSES.get().asItem());
            event.accept(ThingamajigsBlocks.MERGES.get().asItem());
            event.accept(ThingamajigsBlocks.CHILDREN.get().asItem());
            event.accept(ThingamajigsBlocks.CROSSING_NO_GATES.get().asItem());
            event.accept(ThingamajigsBlocks.DANGER.get().asItem());
            event.accept(ThingamajigsBlocks.DUAL_ENDS.get().asItem());
            event.accept(ThingamajigsBlocks.ELDER.get().asItem());
            event.accept(ThingamajigsBlocks.GATED_CROSSING.get().asItem());
            event.accept(ThingamajigsBlocks.GIVE_WAY.get().asItem());
            event.accept(ThingamajigsBlocks.HUMP_BRIDGE.get().asItem());
            event.accept(ThingamajigsBlocks.MINECARTS.get().asItem());
            event.accept(ThingamajigsBlocks.NARROW_BOTH.get().asItem());
            event.accept(ThingamajigsBlocks.NARROW_SIDES.get().asItem());
            event.accept(ThingamajigsBlocks.OPEN_BRIDGE.get().asItem());
            event.accept(ThingamajigsBlocks.PEDS_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.RIVERBANK.get().asItem());
            event.accept(ThingamajigsBlocks.ROAD_WORK.get().asItem());
            event.accept(ThingamajigsBlocks.ROUNDABOUT_UK.get().asItem());
            event.accept(ThingamajigsBlocks.SIDE_WIND.get().asItem());
            event.accept(ThingamajigsBlocks.SIGNAL_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.SLIPPERY.get().asItem());
            event.accept(ThingamajigsBlocks.SOFT_VERGE.get().asItem());
            event.accept(ThingamajigsBlocks.STOP_OR_GIVEWAY_AHEAD.get().asItem());
            event.accept(ThingamajigsBlocks.TRY.get().asItem());
            event.accept(ThingamajigsBlocks.TUNNEL.get().asItem());
            event.accept(ThingamajigsBlocks.TWO_WAY.get().asItem());
            event.accept(ThingamajigsBlocks.UNEVEN.get().asItem());
            event.accept(ThingamajigsBlocks.WATER_COURSE.get().asItem());
            event.accept(ThingamajigsBlocks.ZEBRA.get().asItem());
            event.accept(ThingamajigsBlocks.ALL_PROHIBITED.get().asItem());
            event.accept(ThingamajigsBlocks.HORSE_PROHIBITED.get().asItem());
            event.accept(ThingamajigsBlocks.MOTOR_VEHICLES_PROHIBITED.get().asItem());
            event.accept(ThingamajigsBlocks.MOTORCYCLES_PROHIBITED.get().asItem());
            event.accept(ThingamajigsBlocks.NO_OVERTAKING.get().asItem());
            event.accept(ThingamajigsBlocks.NO_STOPPING.get().asItem());
            event.accept(ThingamajigsBlocks.NO_WAITING.get().asItem());
            event.accept(ThingamajigsBlocks.PEDS_PROHIBITED.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_10.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_20.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_30.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_40.get().asItem());
            event.accept(ThingamajigsBlocks.SPEED_50.get().asItem());
            event.accept(ThingamajigsBlocks.CATTLE_GRID.get().asItem());
            //
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ThingamajigsItems.THINGAMAJIG, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            // fixed ordering
            event.getEntries().putBefore(Items.EXPERIENCE_BOTTLE.getDefaultInstance(),ThingamajigsItems.THINGAMAJIG.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ThingamajigsItems.PAINT_BRUSH);
            event.accept(ThingamajigsItems.WHITE_PAINT_BRUSH);
            event.accept(ThingamajigsItems.YELLOW_PAINT_BRUSH);
            event.accept(ThingamajigsItems.BLUE_PAINT_BRUSH);
            event.accept(ThingamajigsItems.SCRAPE_TOOL);
            // fixed ordering
            event.getEntries().putAfter(Items.BRUSH.getDefaultInstance(),
                    ThingamajigsItems.PAINT_BRUSH.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ThingamajigsItems.PAINT_BRUSH.get().getDefaultInstance(),
                    ThingamajigsItems.WHITE_PAINT_BRUSH.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ThingamajigsItems.WHITE_PAINT_BRUSH.get().getDefaultInstance(),
                    ThingamajigsItems.YELLOW_PAINT_BRUSH.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ThingamajigsItems.YELLOW_PAINT_BRUSH.get().getDefaultInstance(),
                    ThingamajigsItems.BLUE_PAINT_BRUSH.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ThingamajigsItems.BLUE_PAINT_BRUSH.get().getDefaultInstance(),
                    ThingamajigsItems.SCRAPE_TOOL.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // sludge converter is a crafter-like block, so it is functional-classed as well
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ThingamajigsBlocks.SLUDGE_CONVERTER.get().asItem(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            // fixed ordering
            event.getEntries().putBefore(Items.CAMPFIRE.getDefaultInstance(),ThingamajigsBlocks.SLUDGE_CONVERTER.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // config option can disable or enable these features in the creative tab (otherwise the give command will work)
        if(ThingamajigsServerConfigs.SERVER.opBlocksEnabled.get()){
            if(event.getTabKey() == CreativeModeTabs.OP_BLOCKS){
                if(event.hasPermissions()){
                    event.accept(ThingamajigsItems.WATER_SOURCE);
                    event.getEntries().putBefore(Items.COMMAND_BLOCK_MINECART.getDefaultInstance(),
                            ThingamajigsItems.WATER_SOURCE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                    event.accept(ThingamajigsItems.NP_PLACEABLE);
                    event.getEntries().putAfter(ThingamajigsItems.WATER_SOURCE.get().getDefaultInstance(),
                            ThingamajigsItems.NP_PLACEABLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                    event.accept(ThingamajigsItems.EP_PLACEABLE);
                    event.getEntries().putAfter(ThingamajigsItems.NP_PLACEABLE.get().getDefaultInstance(),
                            ThingamajigsItems.EP_PLACEABLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                    event.accept(ThingamajigsItems.EG_PLACEABLE);
                    event.getEntries().putAfter(ThingamajigsItems.EP_PLACEABLE.get().getDefaultInstance(),
                            ThingamajigsItems.EG_PLACEABLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                    event.getEntries().putAfter(ThingamajigsItems.EG_PLACEABLE.get().getDefaultInstance(),
                            ThingamajigsBlocks.SUPER_SPONGE.get().asItem().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                    // end
                }
            }
        }
        // end creative mode tab items setup
    }

    @OnlyIn(Dist.CLIENT)
    public static void setRenderTypes(){
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsFluids.SLUDGE.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsFluids.SLUDGE_FLOWING.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsFluids.PURIFYING_WATER.get(),RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsFluids.PURIFYING_WATER_FLOWING.get(),RenderType.translucent());

        // old method stuff
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.WHITE_PUMPKIN_STEM.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.ATTATCHED_WHITE_PUMPKIN_STEM.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.LIGHT_GRAY_PUMPKIN_STEM.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.ATTATCHED_LIGHT_GRAY_PUMPKIN_STEM.get(),RenderType.cutout());
        // 1.8.1 additions
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.ROUND_BUSH.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.BULBLET.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ThingamajigsBlocks.WISPY_WEED.get(),RenderType.cutout());
    }

    public static void setItemColors(RegisterColorHandlersEvent.Item rchEvent){
        // register item colors
        rchEvent.register((itemStack,i) -> {
            BlockState bs = ((BlockItem)itemStack.getItem()).getBlock().defaultBlockState();
            return Minecraft.getInstance().getBlockColors().getColor(bs,null,null,i);
        },
                ThingamajigsBlocks.FLOWERING_LILY_PAD.get(),
                ThingamajigsBlocks.TRIPLE_LILY_PAD.get(),
                ThingamajigsBlocks.COLORED_GLASS.get(),
                ThingamajigsItems.FLOWERING_LILY_PAD_ITEM.get(),
                ThingamajigsItems.TRIPLE_LILY_PAD_ITEM.get(),
                ThingamajigsBlocks.RUBBER_LEAVES.get(),
                ThingamajigsBlocks.ROUND_BUSH.get(),
                ThingamajigsBlocks.BULBLET.get(),
                ThingamajigsBlocks.WISPY_WEED.get()
        );
        // balloon block items
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getTeal(),
                ThingamajigsItems.TEAL_BALLOON_BLOCK_ITEM.get()
        );
        //
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getLightGray(),
                ThingamajigsItems.LIGHT_GRAY_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.LIGHT_GRAY_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getGray(),
                ThingamajigsItems.GRAY_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.GRAY_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getBlack(),
                ThingamajigsItems.BLACK_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.BLACK_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getBrown(),
                ThingamajigsItems.BROWN_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.BROWN_GLOW_BLOCK.get().asItem()
        );
        // colorful balloon block items
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(1),
                ThingamajigsItems.RED_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.RED_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(2),
                ThingamajigsItems.ORANGE_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.ORANGE_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(3),
                ThingamajigsItems.YELLOW_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.YELLOW_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(4),
                ThingamajigsItems.LIME_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.LIME_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(5),
                ThingamajigsItems.GREEN_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.GREEN_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(6),
                ThingamajigsItems.CYAN_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.CYAN_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(7),
                ThingamajigsItems.LIGHT_BLUE_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.LIGHT_BLUE_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(8),
                ThingamajigsItems.BLUE_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.BLUE_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(9),
                ThingamajigsItems.PURPLE_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.PURPLE_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(10),
                ThingamajigsItems.MAGENTA_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.MAGENTA_GLOW_BLOCK.get().asItem()
        );
        rchEvent.register((itemStack,i) -> ThingamajigsColors.getColorFromList(11),
                ThingamajigsItems.PINK_BALLOON_BLOCK_ITEM.get(),
                ThingamajigsBlocks.PINK_GLOW_BLOCK.get().asItem()
        );
    }

    public static void setColors(RegisterColorHandlersEvent.Block rchEvent){
        // register block colors
        rchEvent.register((blockState,tintGetter,blockPos,i) ->
                tintGetter != null && blockPos != null ?
                        BiomeColors.getAverageFoliageColor(tintGetter,blockPos) : FoliageColor.getDefaultColor(),
                ThingamajigsBlocks.FLOWERING_LILY_PAD.get(),
                ThingamajigsBlocks.TRIPLE_LILY_PAD.get(),
                ThingamajigsBlocks.RUBBER_LEAVES.get()
                );
        //
        rchEvent.register((blockState,tintGetter,blockPos,i) ->
                        tintGetter != null && blockPos != null ?
                                BiomeColors.getAverageWaterColor(tintGetter,blockPos) : FoliageColor.getDefaultColor(),
                ThingamajigsBlocks.COLORED_GLASS.get()
        );

        rchEvent.register((blockState,tintGetter,blockPos,i) ->
                        tintGetter != null && blockPos != null ?
                                BiomeColors.getAverageGrassColor(tintGetter,blockPos) : FoliageColor.getMangroveColor(),
                ThingamajigsBlocks.ROUND_BUSH.get(),
                ThingamajigsBlocks.BULBLET.get(),
                ThingamajigsBlocks.WISPY_WEED.get()
        );

        // custom colored blocks
        // darker color balloons
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getTeal(),
                ThingamajigsBlocks.TEAL_BALLOON_BLOCK.get());
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getWhite(),
                ThingamajigsBlocks.ATTATCHED_WHITE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.WHITE_PUMPKIN_STEM.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getLightGray(),
                ThingamajigsBlocks.LIGHT_GRAY_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_LIGHT_GRAY_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.LIGHT_GRAY_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.LIGHT_GRAY_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getGray(),
                ThingamajigsBlocks.GRAY_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_GRAY_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.GRAY_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.GRAY_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getBlack(),
                ThingamajigsBlocks.BLACK_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_BLACK_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.BLACK_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.BLACK_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getBrown(),
                ThingamajigsBlocks.BROWN_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_BROWN_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.BROWN_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.BROWN_GLOW_BLOCK.get()
        );
        // colorful balloons
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(1),
                ThingamajigsBlocks.RED_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_RED_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.RED_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.RED_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(2),
                ThingamajigsBlocks.ORANGE_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ORANGE_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(3),
                ThingamajigsBlocks.YELLOW_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_YELLOW_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.YELLOW_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.YELLOW_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(4),
                ThingamajigsBlocks.LIME_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_LIME_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.LIME_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.LIME_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(5),
                ThingamajigsBlocks.GREEN_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_GREEN_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.GREEN_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.GREEN_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(6),
                ThingamajigsBlocks.CYAN_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_CYAN_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.CYAN_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.CYAN_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(7),
                ThingamajigsBlocks.LIGHT_BLUE_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_LIGHT_BLUE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.LIGHT_BLUE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.LIGHT_BLUE_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(8),
                ThingamajigsBlocks.BLUE_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_BLUE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.BLUE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.BLUE_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(9),
                ThingamajigsBlocks.PURPLE_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_PURPLE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.PURPLE_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.PURPLE_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(10),
                ThingamajigsBlocks.MAGENTA_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_MAGENTA_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.MAGENTA_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.MAGENTA_GLOW_BLOCK.get()
        );
        rchEvent.register((bs, batg, bp, i1) -> ThingamajigsColors.getColorFromList(11),
                ThingamajigsBlocks.PINK_BALLOON_BLOCK.get(),
                ThingamajigsBlocks.ATTATCHED_PINK_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.PINK_PUMPKIN_STEM.get(),
                ThingamajigsBlocks.PINK_GLOW_BLOCK.get()
        );
    }

    private void setupClient(final FMLClientSetupEvent event){
        // set render types for client
        event.enqueueWork(Thingamajigs::setRenderTypes);

        // sheets are not thread safe nor to be used server side

        // workers work for a sheet of cheetah paper
        // set up the wood types for Sheets (the refs for entity model textures, atlases stuff, etc, etc.)
        event.enqueueWork(() -> {
            try{
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_ROAD_WOOD);
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_RED_ROAD_WOOD);
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_BLUE_ROAD_WOOD);
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_BROWN_ROAD_WOOD);
            }
            catch (RuntimeException err){
                LOGGERV2.error("Cannot register sheets. Runtime error.");
            }
            // menus
            try{
                MenuScreens.register(ThingamajigsMenuTypes.MAILBOX_MENU.get(), MailboxScreen::new);
                MenuScreens.register(ThingamajigsMenuTypes.PHONE_MENU.get(), PhoneUIScreen::new);
                MenuScreens.register(ThingamajigsMenuTypes.DJ_BE_MENU.get(), DJLaserLightScreen::new);
                // 1.7.6 added
                MenuScreens.register(ThingamajigsMenuTypes.RAILROAD_CROSSING_MENU.get(), RailroadCrossingArmScreen::new);
            }
            catch(Exception e){
                logErrorInternal(e);
            }
        });

        // try wood type and sign renderer setup
        try{
            WoodType.register(ThingamajigsBlockTypes.GENERIC_ROAD_WOOD);
            WoodType.register(ThingamajigsBlockTypes.GENERIC_RED_ROAD_WOOD);
            WoodType.register(ThingamajigsBlockTypes.GENERIC_BLUE_ROAD_WOOD);
            WoodType.register(ThingamajigsBlockTypes.GENERIC_BROWN_ROAD_WOOD);
        }
        catch (Exception woodTypeError){
            logErrorInternal(woodTypeError);
        }
        //
        try{
            // regular signs
            BlockEntityRenderers.register(ThingamajigsBlockEntities.GREEN_ROADWAY_SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.RED_ROADWAY_SIGN_BE.get(), SignRenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.BLUE_ROADWAY_SIGN_BE.get(), SignRenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.BROWN_ROADWAY_SIGN_BE.get(), SignRenderer::new);
            // hanging signs
            BlockEntityRenderers.register(ThingamajigsBlockEntities.GREEN_HANGING_SIGN.get(), HangingSignRenderer::new);

            BlockEntityRenderers.register(ThingamajigsBlockEntities.ITEM_DISPLAY_BE.get(),ItemDisplayBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.DJ_LASER_LIGHT_BE.get(),DJLaserLightBERenderer::new);

            // 1.7.6
            BlockEntityRenderers.register(ThingamajigsBlockEntities.RAILROAD_CROSSING_ARM_BE.get(),RailroadCrossingArmBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.CURVED_MONITOR_BE.get(),CurvedMonitorBERenderer::new);
            // 1.7.7
            BlockEntityRenderers.register(ThingamajigsBlockEntities.CLEVER_BLACKBOARD_BE.get(),CleverBlackboardBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.UMBRELLA_BE.get(),UmbrellaBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.THEATER_PROJECTOR_BE.get(),TheaterProjectorBERenderer::new);
            // 1.8.1
            BlockEntityRenderers.register(ThingamajigsBlockEntities.ANIMATED_ICE_RINK.get(),AnimatedIceRinkRenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.ANIMATED_DEER_BE.get(),AnimatedDeerBERenderer::new);
            // 1.8.3
            BlockEntityRenderers.register(ThingamajigsBlockEntities.FOOTBALL_GOAL.get(),FootballGoalRenderer::new);
        }
        catch(Exception blockEntityRendererError){
            logErrorInternal(blockEntityRendererError);
        }
        try{
            setupEntityRenderers();
        }
        catch (RuntimeException exc){
            logErrorInternal(new Exception("Thingamajigs runtime exception: " + exc.getMessage()));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void setupEntityRenderers(){
        try{
            // entity without layer
            EntityRenderers.register(ThingamajigsEntities.CHAIR.get(), ChairEntityRenderer::new);
            EntityRenderers.register(ThingamajigsEntities.STOOL.get(), StoolEntityRenderer::new);
            EntityRenderers.register(ThingamajigsEntities.TOILET_ENTITY.get(), ToiletEntityRenderer::new);
            // entity with layer
            EntityRenderers.register(ThingamajigsEntities.INFIMOVE_MINECART.get(),
                    (provider) -> new InfiCartRenderer<>(provider, ModelLayers.MINECART));
        }
        catch (Exception entityRendererError){
            logErrorInternal(entityRendererError);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void layerSetup(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(RRArmModel.LAYER_LOCATION,RRArmModel::createBodyLayer);
        event.registerLayerDefinition(CurvedMonitorModel.LAYER_LOCATION,CurvedMonitorModel::createBodyLayer);
        event.registerLayerDefinition(CleverBlackboardModel.LAYER_LOCATION,CleverBlackboardModel::createBodyLayer);
        event.registerLayerDefinition(UmbrellaModel.LAYER_LOCATION,UmbrellaModel::createBodyLayer);
        event.registerLayerDefinition(TheaterProjectorModel.LAYER_LOCATION,TheaterProjectorModel::createBodyLayer);
        event.registerLayerDefinition(AnimatedIceRinkModel.ICE_RINK_ALL,AnimatedIceRinkModel::createBodyLayer);
        event.registerLayerDefinition(AnimatedDeerModel.LAYER_LOCATION,AnimatedDeerModel::createBodyLayer);
        event.registerLayerDefinition(FootballGoalModel.LAYER_LOCATION,FootballGoalModel::createBodyLayer);
    }



    private void setup(final FMLCommonSetupEvent event) {

    }
}
