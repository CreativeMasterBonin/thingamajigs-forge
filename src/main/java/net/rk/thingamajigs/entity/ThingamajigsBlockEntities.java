package net.rk.thingamajigs.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.customblock.*;
import net.rk.thingamajigs.entity.customblock.chests.StorageDecorationBE;
import net.rk.thingamajigs.entity.roadsigns.BlueRSBE;
import net.rk.thingamajigs.entity.roadsigns.BrownRSBE;
import net.rk.thingamajigs.entity.roadsigns.RedRoadSignBlockEntity;
import net.rk.thingamajigs.entity.roadsigns.hanging.GreenHangingRSBE;

import java.util.List;
import java.util.function.Supplier;

public class ThingamajigsBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Thingamajigs.MOD_ID);

    // register mailbox and attach it to Mailbox block
    public static final RegistryObject<BlockEntityType<MailboxBlockEntity>> MAILBOX_BLOCK_ENTITY =
            BLOCK_ENTITIES.register(
            "mailbox", () -> BlockEntityType.Builder.of(
                    MailboxBlockEntity::new, ThingamajigsBlocks.MAILBOX.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<RoadwaySignBlockEntity>> GREEN_ROADWAY_SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("green_roadway_sign_block_entity",
                    () -> BlockEntityType.Builder.of(RoadwaySignBlockEntity::new,
                            ThingamajigsBlocks.GREEN_WALL_SIGN.get(),ThingamajigsBlocks.GREEN_STANDING_SIGN.get()
                    ).build(null));


    public static final RegistryObject<BlockEntityType<RedRoadSignBlockEntity>> RED_ROADWAY_SIGN_BE =
            BLOCK_ENTITIES.register("red_roadway_sign_be",
                    () -> BlockEntityType.Builder.of(RedRoadSignBlockEntity::new,
                            ThingamajigsBlocks.RED_WALL_SIGN.get(),ThingamajigsBlocks.RED_STANDING_SIGN.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<BlueRSBE>> BLUE_ROADWAY_SIGN_BE =
            BLOCK_ENTITIES.register("blue_roadway_sign_be",
                    () -> BlockEntityType.Builder.of(BlueRSBE::new,
                            ThingamajigsBlocks.BLUE_WALL_SIGN.get(),ThingamajigsBlocks.BLUE_STANDING_SIGN.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<BrownRSBE>> BROWN_ROADWAY_SIGN_BE =
            BLOCK_ENTITIES.register("brown_roadway_sign_be",
                    () -> BlockEntityType.Builder.of(BrownRSBE::new,
                            ThingamajigsBlocks.BROWN_WALL_SIGN.get(),ThingamajigsBlocks.BROWN_STANDING_SIGN.get()
                    ).build(null));

    // hanging signs
    public static final RegistryObject<BlockEntityType<GreenHangingRSBE>> GREEN_HANGING_SIGN =
            BLOCK_ENTITIES.register("green_hanging_sign",
                    () -> BlockEntityType.Builder.of(GreenHangingRSBE::new,
                            ThingamajigsBlocks.GREEN_WALL_HANGING_SIGN.get(),ThingamajigsBlocks.GREEN_HANGING_SIGN.get()
                    ).build(null));

    // bes with renderers
    public static final RegistryObject<BlockEntityType<PlateBE>> PLATE_BE =
            BLOCK_ENTITIES.register("plate_be", () ->
                    BlockEntityType.Builder.of(PlateBE::new,
                            ThingamajigsBlocks.PLATE.get()).build(null));

    public static final RegistryObject<BlockEntityType<FridgeBlockEntity>> FRIDGE_BE =
            BLOCK_ENTITIES.register("fridge_be", () ->
                    BlockEntityType.Builder.of(FridgeBlockEntity::new,
                            ThingamajigsBlocks.FRIDGE.get()).build(null));

    public static final Supplier<BlockEntityType<ItemDisplayBE>> ITEM_DISPLAY_BE = BLOCK_ENTITIES.register(
            "item_display_be",() -> BlockEntityType.Builder.of(ItemDisplayBE::new,ThingamajigsBlocks.ITEM_DISPLAY_BLOCK.get())
                    .build(null));

    public static final Supplier<BlockEntityType<DJLaserLightBE>> DJ_LASER_LIGHT_BE = BLOCK_ENTITIES.register(
            "laser_light_be",() -> BlockEntityType.Builder.of(DJLaserLightBE::new,ThingamajigsBlocks.DJ_LASER_LIGHT.get())
                    .build(null));

    // 1.7.6
    public static final Supplier<BlockEntityType<RailroadCrossingBE>> RAILROAD_CROSSING_ARM_BE = BLOCK_ENTITIES.register(
            "railroad_crossing_arm_be",() -> BlockEntityType.Builder.of(RailroadCrossingBE::new,ThingamajigsBlocks.RAILROAD_CROSSING.get())
                    .build(null));

    public static final Supplier<BlockEntityType<CurvedMonitorBE>> CURVED_MONITOR_BE = BLOCK_ENTITIES.register(
            "curved_monitor_be",() -> BlockEntityType.Builder.of(CurvedMonitorBE::new,ThingamajigsBlocks.CURVED_MONITOR.get())
                    .build(null));

    // 1.7.7
    public static final Supplier<BlockEntityType<CleverBlackboardBE>> CLEVER_BLACKBOARD_BE = BLOCK_ENTITIES.register(
            "clever_blackboard_be",() -> BlockEntityType.Builder.of(CleverBlackboardBE::new,ThingamajigsBlocks.CLEVER_BLACKBOARD.get())
                    .build(null));

    public static final Supplier<BlockEntityType<UmbrellaBE>> UMBRELLA_BE = BLOCK_ENTITIES.register(
            "umbrella_be",() -> BlockEntityType.Builder.of(UmbrellaBE::new,ThingamajigsBlocks.UMBRELLA.get())
                    .build(null));

    public static final Supplier<BlockEntityType<TheaterProjectorBE>> THEATER_PROJECTOR_BE = BLOCK_ENTITIES.register(
            "theater_projector_be",() -> BlockEntityType.Builder.of(TheaterProjectorBE::new,ThingamajigsBlocks.THEATER_PROJECTOR.get())
                    .build(null));

    public static final Supplier<BlockEntityType<StorageDecorationBE>> STORAGE_DECORATION_BE = BLOCK_ENTITIES.register(
            "storage_decoration_be",() -> BlockEntityType.Builder.of(StorageDecorationBE::new,
ThingamajigsBlocks.TRIPLE_SHELF.get(),ThingamajigsBlocks.TOY_BOX.get()
                            )
                    .build(null));

    public static final Supplier<BlockEntityType<AnimatedIceRinkBE>> ANIMATED_ICE_RINK = BLOCK_ENTITIES.register(
            "animated_ice_rink_be",() -> BlockEntityType.Builder.of(AnimatedIceRinkBE::new,ThingamajigsBlocks.ANIMATED_ICE_RINK.get())
                    .build(null));

    public static final RegistryObject<BlockEntityType<AnimatedDeerBE>> ANIMATED_DEER_BE =
            BLOCK_ENTITIES.register("animated_deer_be",() ->
                    BlockEntityType.Builder.of(AnimatedDeerBE::new,ThingamajigsBlocks.ANIMATED_DEER.get())
                            .build(null));

    // general purpose block entities (add blocks here so they are supported when needed)
    public static final Supplier<BlockEntityType<OpenableContainer>> OPENABLE_CONTAINER = BLOCK_ENTITIES.register(
            "openable_container_be",() -> BlockEntityType.Builder.of(OpenableContainer::new,
                            ThingamajigsBlocks.FOOD_COOLER.get()
                    )
                    .build(null));

    public static final Supplier<BlockEntityType<FootballGoalBE>> FOOTBALL_GOAL = BLOCK_ENTITIES.register(
            "football_goal_be",() -> BlockEntityType.Builder.of(FootballGoalBE::new,
                            ThingamajigsBlocks.FOOTBALL_GOAL.get()
                    )
                    .build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
