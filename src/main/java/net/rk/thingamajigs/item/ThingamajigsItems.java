package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.TrimPatterns;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.PoopHorn;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.bases.BaseGlob;
import net.rk.thingamajigs.item.bases.ComponentBase;
import net.rk.thingamajigs.item.custom.*;
import net.rk.thingamajigs.legacy.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThingamajigsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Thingamajigs.MOD_ID);


    public static final RegistryObject<Item> THINGAMAJIG = ITEMS.register("thingamajig", () -> new ThingamajigItem(new Item.Properties().rarity(Rarity.EPIC)));

    // Torch-like Block Items
    public static final RegistryObject<Item> CLEAR_BULB_ITEM = ITEMS.register("clear_bulb", () -> new ClearBulb((new Item.Properties())));
    public static final RegistryObject<Item> FULL_BULB_ITEM = ITEMS.register("full_bulb", () -> new FullBulb((new Item.Properties())));
    public static final RegistryObject<Item> CLEAR_LANTERN_ITEM = ITEMS.register("clear_lantern", () -> new ClearLantern((new Item.Properties())));
    public static final RegistryObject<Item> FULL_LANTERN_ITEM = ITEMS.register("full_lantern", () -> new FullLantern((new Item.Properties())));

    public static final RegistryObject<Item> RED_LANTERN_ITEM = ITEMS.register("red_lantern_item",
            () -> new StandingAndWallBlockItem(
                    ThingamajigsBlocks.RED_LANTERN.get(),
                    ThingamajigsBlocks.WALL_RED_LANTERN.get(),
                    new Item.Properties(),
                    Direction.DOWN));

    public static final RegistryObject<Item> PAPER_LANTERN_ITEM = ITEMS.register("paper_lantern_item",
            () -> new StandingAndWallBlockItem(
                    ThingamajigsBlocks.PAPER_LANTERN.get(),
                    ThingamajigsBlocks.WALL_PAPER_LANTERN.get(),
                    new Item.Properties(),
                    Direction.DOWN));


    // Items that place blocks
    public static final RegistryObject<Item> PAINT_BRUSH = ITEMS.register("paint_brush", () -> new BlankPaintBrush(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHITE_PAINT_BRUSH = ITEMS.register("white_paint_brush", () -> new Paintbrush((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> BLUE_PAINT_BRUSH = ITEMS.register("blue_paint_brush", () -> new BluePaintBrush((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> YELLOW_PAINT_BRUSH = ITEMS.register("yellow_paint_brush", () -> new YellowPaintBrush((new Item.Properties()).stacksTo(1)));

    // items that break or change blocks
    public static final RegistryObject<Item> SCRAPE_TOOL = ITEMS.register("scrape_tool",
            () -> new ScrapeTool(new Item.Properties()));

    // instruments
    public static final RegistryObject<Item> POOP_HORN = ITEMS.register("poop_horn", () -> new PoopHorn((new Item.Properties())
            .stacksTo(1)));

    // Other misc. items
    public static final RegistryObject<Item> WATER_SOURCE = ITEMS.register("water_source",
            () -> new BlockItem(Blocks.WATER, new Item.Properties()));
    public static final RegistryObject<Item> NP_PLACEABLE = ITEMS.register("nether_portal_placeable",
            () -> new BlockItem(Blocks.NETHER_PORTAL, new Item.Properties()));
    public static final RegistryObject<Item> EP_PLACEABLE = ITEMS.register("end_portal_placeable",
            () -> new BlockItem(Blocks.END_PORTAL, new Item.Properties()));
    public static final RegistryObject<Item> EG_PLACEABLE = ITEMS.register("end_gateway_placeable",
            () -> new BlockItem(Blocks.END_GATEWAY, new Item.Properties()));


    public static final RegistryObject<Item> SLUDGE_BUCKET = ITEMS.register("sludge_bucket", SludgeBucketItem::new);
    public static final RegistryObject<Item> PURIFYING_WATER_BUCKET = ITEMS.register("purifying_water_bucket", PurifyingWaterBucketItem::new);

    public static final RegistryObject<Item> STRANGE_STATUE = ITEMS.register("strange_statue",
            () -> new BlockItem(ThingamajigsBlocks.STRANGE_STATUE.get(),new Item.Properties().fireResistant()));

    // crafting items
    public static final RegistryObject<Item> THINGAMAJIG_GLOB = ITEMS.register("thingamajig_glob",
            () -> new ThingamajigGlob((new Item.Properties())));
    public static final RegistryObject<Item> SIGN_GLOB = ITEMS.register("sign_glob",
            () -> new SignGlob((new Item.Properties())));
    public static final RegistryObject<Item> DOOR_GLOB = ITEMS.register("door_glob",
            () -> new DoorGlob((new Item.Properties())));
    public static final RegistryObject<Item> GLOB_SANDWICH = ITEMS.register("glob_sandwich",
            () -> new GlobSandwich((new Item.Properties())));

    public static final RegistryObject<Item> CIRCLE_SIGN_GLOB = ITEMS.register("circle_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.circle_sign.desc",false,Rarity.COMMON));
    public static final RegistryObject<Item> SQUARE_SIGN_GLOB = ITEMS.register("square_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.square_sign.desc",false,Rarity.COMMON));
    public static final RegistryObject<Item> TRIANGLE_SIGN_GLOB = ITEMS.register("triangle_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.triangle_sign.desc",false,Rarity.COMMON));
    public static final RegistryObject<Item> MISC_SIGN_GLOB = ITEMS.register("misc_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.misc_sign.desc",false,Rarity.COMMON));

    // sign items
    public static final RegistryObject<Item> GREEN_ROADWAY_SIGN_ITEM = ITEMS.register("green_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    ThingamajigsBlocks.GREEN_STANDING_SIGN.get(),
                    ThingamajigsBlocks.GREEN_WALL_SIGN.get()));

    public static final RegistryObject<Item> RED_ROADWAY_SIGN_ITEM = ITEMS.register("red_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    ThingamajigsBlocks.RED_STANDING_SIGN.get(),
                    ThingamajigsBlocks.RED_WALL_SIGN.get()));

    public static final RegistryObject<Item> BLUE_ROADWAY_SIGN_ITEM = ITEMS.register("blue_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    ThingamajigsBlocks.BLUE_STANDING_SIGN.get(),
                    ThingamajigsBlocks.BLUE_WALL_SIGN.get()));

    public static final RegistryObject<Item> BROWN_ROADWAY_SIGN_ITEM = ITEMS.register("brown_roadway_sign",
            () -> new SignItem((new Item.Properties()
                    .stacksTo(16)),
                    ThingamajigsBlocks.BROWN_STANDING_SIGN.get(),
                    ThingamajigsBlocks.BROWN_WALL_SIGN.get()));

    public static final RegistryObject<Item> GREEN_HANGING_ROADWAY_SIGN_ITEM = ITEMS.register("green_hanging_roadway_sign",
            () -> new HangingSignItem(ThingamajigsBlocks.GREEN_HANGING_SIGN.get(),
                    ThingamajigsBlocks.GREEN_WALL_HANGING_SIGN.get(),
                    (new Item.Properties().stacksTo(16))));

    public static final RegistryObject<Item> FLOWERING_LILY_PAD_ITEM = ITEMS.register("flowering_lily_pad_item",
            () -> new PlaceOnWaterBlockItem(ThingamajigsBlocks.FLOWERING_LILY_PAD.get(),new Item.Properties()));
    public static final RegistryObject<Item> TRIPLE_LILY_PAD_ITEM = ITEMS.register("triple_lily_pad_item",
            () -> new PlaceOnWaterBlockItem(ThingamajigsBlocks.TRIPLE_LILY_PAD.get(),new Item.Properties()));

    // dye colors: white, light_gray, gray, black, brown, red, orange, yellow, lime, green, cyan, light_blue, blue, purple, magenta, pink
    // 16

    public static final RegistryObject<Item> WHITE_PUMPKIN_SEEDS = ITEMS.register("white_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.WHITE_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_GRAY_PUMPKIN_SEEDS = ITEMS.register("light_gray_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.LIGHT_GRAY_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> GRAY_PUMPKIN_SEEDS = ITEMS.register("gray_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.GRAY_PUMPKIN_STEM.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PUMPKIN_SEEDS = ITEMS.register("black_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.BLACK_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> BROWN_PUMPKIN_SEEDS = ITEMS.register("brown_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.BROWN_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> RED_PUMPKIN_SEEDS = ITEMS.register("red_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.RED_PUMPKIN_STEM.get(), new Item.Properties()));

    // NO ORANGE

    public static final RegistryObject<Item> YELLOW_PUMPKIN_SEEDS = ITEMS.register("yellow_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.YELLOW_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> LIME_PUMPKIN_SEEDS = ITEMS.register("lime_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.LIME_PUMPKIN_STEM.get(), new Item.Properties()));
    public static final RegistryObject<Item> GREEN_PUMPKIN_SEEDS = ITEMS.register("green_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.GREEN_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> CYAN_PUMPKIN_SEEDS = ITEMS.register("cyan_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.CYAN_PUMPKIN_STEM.get(), new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_PUMPKIN_SEEDS = ITEMS.register("light_blue_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.LIGHT_BLUE_PUMPKIN_STEM.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLUE_PUMPKIN_SEEDS = ITEMS.register("blue_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.BLUE_PUMPKIN_STEM.get(), new Item.Properties()));

    public static final RegistryObject<Item> PURPLE_PUMPKIN_SEEDS = ITEMS.register("purple_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.PURPLE_PUMPKIN_STEM.get(), new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_PUMPKIN_SEEDS = ITEMS.register("magenta_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.MAGENTA_PUMPKIN_STEM.get(), new Item.Properties()));
    public static final RegistryObject<Item> PINK_PUMPKIN_SEEDS = ITEMS.register("pink_pumpkin_seeds",
            () -> new ItemNameBlockItem(ThingamajigsBlocks.PINK_PUMPKIN_STEM.get(), new Item.Properties()));


    public static final RegistryObject<Item> INFIMOVE_MINECART_ITEM = ITEMS.register("infimove_minecart_item",
            () -> new InfimoveMinecartItem(new Item.Properties()
                    .stacksTo(1).fireResistant()));

    public static final RegistryObject<Item> KEY = ITEMS.register("key",
            () -> new Key(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // component items
    public static final RegistryObject<Item> BASE_COMPONENT = ITEMS.register("base_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.base",
                    false,
                    DecorationCategory.Categories.GENERIC,
                    false));
    public static final RegistryObject<Item> INFRASTRUCTURE_COMPONENT = ITEMS.register("infrastructure_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.infrastructure",
                    false,
                    DecorationCategory.Categories.INFRASTRUCTURE,
                    false));
    public static final RegistryObject<Item> FACTORY_COMPONENT = ITEMS.register("factory_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.factory",
                    false,
                    DecorationCategory.Categories.FACTORY,
                    false));
    public static final RegistryObject<Item> TECHNOLOGY_COMPONENT = ITEMS.register("technology_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.technology",
                    false,
                    DecorationCategory.Categories.TECHNOLOGY,
                    false));
    public static final RegistryObject<Item> SPORTS_COMPONENT = ITEMS.register("sports_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.sports",
                    false,
                    DecorationCategory.Categories.SPORTS,
                    false));

    public static final RegistryObject<Item> FURNITURE_COMPONENT = ITEMS.register("furniture_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.furniture",
                    false,
                    DecorationCategory.Categories.FURNITURE,
                    false));
    public static final RegistryObject<Item> MISC_COMPONENT = ITEMS.register("misc_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.misc",
                    false,
                    DecorationCategory.Categories.MISCELLANEOUS,
                    false));

    public static final RegistryObject<Item> MINI_COMPONENT = ITEMS.register("mini_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.mini",
                    false,
                    DecorationCategory.Categories.MINI_CITY,
                    false));



    // subcategory components
    public static final RegistryObject<Item> CAR_WASH_COMPONENT = ITEMS.register("car_wash_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.car_wash",
                    false,
                    DecorationCategory.Subcategories.CAR_WASH,
                    false));

    public static final RegistryObject<Item> TRAFFIC_SIGNAL_COMPONENT = ITEMS.register("traffic_signal_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.traffic_signal",
                    false,
                    DecorationCategory.Subcategories.TRAFFIC_SIGNALS,
                    false));

    public static final RegistryObject<Item> RAILROAD_COMPONENT = ITEMS.register("railroad_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.railroad",
                    false,
                    DecorationCategory.Subcategories.RAILROAD,
                    false));

    public static final RegistryObject<Item> COMPUTER_COMPONENT = ITEMS.register("computer_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.computer",
                    false,
                    DecorationCategory.Subcategories.COMPUTERS,
                    false));

    public static final RegistryObject<Item> GAME_CONSOLE_COMPONENT = ITEMS.register("game_console_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.game_console",
                    false,
                    DecorationCategory.Subcategories.GAME_CONSOLES,
                    false));

    public static final RegistryObject<Item> CHRISTMAS_COMPONENT = ITEMS.register("christmas_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.christmas",
                    false,
                    DecorationCategory.Subcategories.CHRISTMAS,
                    false));

    public static final RegistryObject<Item> SAFETY_COMPONENT = ITEMS.register("safety_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.safety",
                    false,
                    DecorationCategory.Subcategories.SAFETY,
                    false));

    public static final RegistryObject<Item> ARCADE_COMPONENT = ITEMS.register("arcade_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.arcade",
                    false,
                    DecorationCategory.Subcategories.ARCADE,
                    false));

    public static final RegistryObject<Item> HOME_COMPONENT = ITEMS.register("home_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.home",
                    false,
                    DecorationCategory.Subcategories.HOME,
                    false));

    public static final RegistryObject<Item> APPLIANCE_COMPONENT = ITEMS.register("appliance_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.appliance",
                    false,
                    DecorationCategory.Subcategories.APPLIANCE,
                    false));

    public static final RegistryObject<Item> PHONE_COMPONENT = ITEMS.register("phone_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.phone",
                    false,
                    DecorationCategory.Subcategories.PHONE,
                    false));

    public static final RegistryObject<Item> SCIENCE_COMPONENT = ITEMS.register("science_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.science",
                    false,
                    DecorationCategory.Subcategories.SCIENCE,
                    false));

    public static final RegistryObject<Item> HEALTH_COMPONENT = ITEMS.register("health_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.health",
                    false,
                    DecorationCategory.Subcategories.HEALTH,
                    false));

    public static final RegistryObject<Item> TOY_COMPONENT = ITEMS.register("toy_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.toy",
                    false,
                    DecorationCategory.Subcategories.TOY,
                    false));

    public static final RegistryObject<Item> MUSIC_COMPONENT = ITEMS.register("music_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.music",
                    false,
                    DecorationCategory.Subcategories.MUSIC,
                    false));

    public static final RegistryObject<Item> BALLOON_BLOCK_ITEM = ITEMS.register("balloon_block_item",
            () -> new BalloonBlockItem(new Item.Properties()));

    public static final RegistryObject<Item> RED_BALLOON_BLOCK_ITEM = ITEMS.register("red_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.RED_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> ORANGE_BALLOON_BLOCK_ITEM = ITEMS.register("orange_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.ORANGE_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> YELLOW_BALLOON_BLOCK_ITEM = ITEMS.register("yellow_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.YELLOW_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> LIME_BALLOON_BLOCK_ITEM = ITEMS.register("lime_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.LIME_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> GREEN_BALLOON_BLOCK_ITEM = ITEMS.register("green_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.GREEN_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_BLUE_BALLOON_BLOCK_ITEM = ITEMS.register("light_blue_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.LIGHT_BLUE_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> BLUE_BALLOON_BLOCK_ITEM = ITEMS.register("blue_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.BLUE_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> CYAN_BALLOON_BLOCK_ITEM = ITEMS.register("cyan_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.CYAN_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> PURPLE_BALLOON_BLOCK_ITEM = ITEMS.register("purple_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.PURPLE_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> MAGENTA_BALLOON_BLOCK_ITEM = ITEMS.register("magenta_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.MAGENTA_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> PINK_BALLOON_BLOCK_ITEM = ITEMS.register("pink_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.PINK_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> BROWN_BALLOON_BLOCK_ITEM = ITEMS.register("brown_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.BROWN_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_GRAY_BALLOON_BLOCK_ITEM = ITEMS.register("light_gray_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.LIGHT_GRAY_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> GRAY_BALLOON_BLOCK_ITEM = ITEMS.register("gray_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.GRAY_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> BLACK_BALLOON_BLOCK_ITEM = ITEMS.register("black_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.BLACK_BALLOON_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> TEAL_BALLOON_BLOCK_ITEM = ITEMS.register("teal_balloon_block_item",
            () -> new BalloonBlockItem(ThingamajigsBlocks.TEAL_BALLOON_BLOCK.get(),new Item.Properties()));


    public static final RegistryObject<Item> RUBBER = ITEMS.register("rubber",
            () -> new RubberItem(new Item.Properties()));

    public static final RegistryObject<Item> TREE_RESIN = ITEMS.register("tree_resin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPRING_BLOCK_ITEM = ITEMS.register("spring_block_item",
            () -> new BlockItem(ThingamajigsBlocks.SPRING.get(),new Item.Properties()
                    .fireResistant().stacksTo(64).setNoRepair()));


    // currencies
    public static final RegistryObject<Item> MONEY = ITEMS.register("money",
            () -> new Item(new Item.Properties().stacksTo(64)){
                @Override
                public void appendHoverText(ItemStack is, @Nullable Level lvl, List<Component> tc, TooltipFlag tf) {
                    tc.add(Component.translatable("item.thingamajigs.money.desc").withStyle(ChatFormatting.GRAY));
                }
            });

    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties().stacksTo(64)){
                @Override
                public void appendHoverText(ItemStack is, @Nullable Level lvl, List<Component> tc, TooltipFlag tf) {
                    tc.add(Component.translatable("item.thingamajigs.coin.desc").withStyle(ChatFormatting.GRAY));
                }
            });

    public static final RegistryObject<Item> DEBIT_CARD = ITEMS.register("debit_card",
            () -> new DebitCard(new Item.Properties()));



    // crafting items plus
    public static final RegistryObject<Item> GOLDEN_APPLE_SHARD = ITEMS.register("golden_apple_shard",
                    () -> new GoldenAppleShard(new Item.Properties()));

    public static final RegistryObject<Item> GLOBIZED_GOLDEN_APPLE_SHARD =
            ITEMS.register("globized_golden_apple_shard",
                    () -> new BaseGlob(new Item.Properties(), "thingamajigs.glob.golden_apple_shard.desc",
                            false,Rarity.RARE));

    public static final RegistryObject<Item> PURIFYING_GLOB =
            ITEMS.register("purifying_glob",
                    () -> new BaseGlob(new Item.Properties(), "thingamajigs.glob.purifying.desc",
                            false,Rarity.EPIC));

    public static final RegistryObject<Item> PURIFYING_NUGGET = ITEMS.register("purifying_nugget",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PURIFYING_INGOT = ITEMS.register("purifying_ingot",
            () -> new Item(new Item.Properties()));

    // blocks with custom item renderer
    public static final RegistryObject<Item> FOOTBALL_GOAL = ITEMS.register("football_goal",
            () -> new FootballGoalBlockItem(ThingamajigsBlocks.FOOTBALL_GOAL.get(),new Item.Properties()));
    public static final RegistryObject<Item> CLEVER_BLACKBOARD = ITEMS.register("clever_blackboard",
            () -> new CustomBEWLRBlockItem(ThingamajigsBlocks.CLEVER_BLACKBOARD.get(),new Item.Properties()));
    public static final RegistryObject<Item> ANIMATED_ICE_RINK = ITEMS.register("animated_ice_rink",
            () -> new CustomBEWLRBlockItem(ThingamajigsBlocks.ANIMATED_ICE_RINK.get(),new Item.Properties()));
    public static final RegistryObject<Item> UMBRELLA = ITEMS.register("umbrella",
            () -> new UmbrellaBlockItem(ThingamajigsBlocks.UMBRELLA.get(),new Item.Properties()));
    public static final RegistryObject<Item> CURVED_MONITOR = ITEMS.register("curved_monitor",
            () -> new CustomBEWLRBlockItem(ThingamajigsBlocks.CURVED_MONITOR.get(),new Item.Properties()));
    public static final RegistryObject<Item> THEATER_PROJECTOR = ITEMS.register("theater_projector",
            () -> new CustomBEWLRBlockItem(ThingamajigsBlocks.THEATER_PROJECTOR.get(),new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
