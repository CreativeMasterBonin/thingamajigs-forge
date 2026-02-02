package net.rk.thingamajigs;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.ThingamajigsBlockEntities;
import net.rk.thingamajigs.entity.ThingamajigsEntities;
import net.rk.thingamajigs.entity.models.*;
import net.rk.thingamajigs.fluid.ThingamajigsFluids;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.misc.ThingamajigsBlockTypes;
import net.rk.thingamajigs.misc.ThingamajigsColors;
import net.rk.thingamajigs.renderers.*;
import net.rk.thingamajigs.screen.*;
import org.slf4j.Logger;

@SuppressWarnings("deprecated")
public class ThingamajigsClient {
    private static final Logger LOGGERV3 = LogUtils.getLogger();
    private static void logErrorInternal(Exception e){
        LOGGERV3.error("Thingamajigs encountered and error: Exception goes as follows: {}", e.getMessage());
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

    public static void setupClient(final FMLClientSetupEvent event){
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((mc,prevScreen) -> new ThingamajigsConfigScreen(mc,prevScreen,
                        Component.translatable("title.screen.thingamajigs.thingamajigs_config")){})
        );

        ItemBlockRenderTypes.setRenderLayer(ThingamajigsFluids.SLUDGE.get(), RenderType.cutout());
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

        // sheets are not thread safe
        // set up the wood types for Sheets (the refs for entity model textures, atlases stuff, etc, etc.)
        // NOTE: WoodTypes are not used in Thingamajigs for anything more than holding data for the road signs, no wood family blocks are to be made for these WoodTypes
        event.enqueueWork(() -> {
            try{
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_ROAD_WOOD);
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_RED_ROAD_WOOD);
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_BLUE_ROAD_WOOD);
                Sheets.addWoodType(ThingamajigsBlockTypes.GENERIC_BROWN_ROAD_WOOD);
            }
            catch (RuntimeException err){
                LOGGERV3.error("Cannot register sheets. Runtime error.");
            }
            // menus
            try{
                // original mod menus
                MenuScreens.register(ThingamajigsMenuTypes.MAILBOX_MENU.get(), MailboxScreen::new);
                MenuScreens.register(ThingamajigsMenuTypes.PHONE_MENU.get(), PhoneUIScreen::new);
                MenuScreens.register(ThingamajigsMenuTypes.DJ_BE_MENU.get(), DJLaserLightScreen::new);
                // 1.7.6
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
            //
            BlockEntityRenderers.register(ThingamajigsBlockEntities.ITEM_DISPLAY_BE.get(), ItemDisplayBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.DJ_LASER_LIGHT_BE.get(), DJLaserLightBERenderer::new);
            // 1.7.6
            BlockEntityRenderers.register(ThingamajigsBlockEntities.RAILROAD_CROSSING_ARM_BE.get(), RailroadCrossingArmBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.CURVED_MONITOR_BE.get(), CurvedMonitorBERenderer::new);
            // 1.7.7
            BlockEntityRenderers.register(ThingamajigsBlockEntities.CLEVER_BLACKBOARD_BE.get(), CleverBlackboardBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.UMBRELLA_BE.get(),UmbrellaBERenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.THEATER_PROJECTOR_BE.get(),TheaterProjectorBERenderer::new);
            // 1.8.1
            BlockEntityRenderers.register(ThingamajigsBlockEntities.ANIMATED_ICE_RINK.get(),AnimatedIceRinkRenderer::new);
            BlockEntityRenderers.register(ThingamajigsBlockEntities.ANIMATED_DEER_BE.get(),AnimatedDeerBERenderer::new);
            // 1.8.3
            BlockEntityRenderers.register(ThingamajigsBlockEntities.FOOTBALL_GOAL.get(),FootballGoalRenderer::new);
            //
        }
        catch(Exception blockEntityRendererError){
            logErrorInternal(blockEntityRendererError);
        }
    }

    public static void layerSetup(EntityRenderersEvent.RegisterLayerDefinitions event){
        // model layers for non-entity models
        event.registerLayerDefinition(RRArmModel.LAYER_LOCATION,RRArmModel::createBodyLayer);
        event.registerLayerDefinition(CurvedMonitorModel.LAYER_LOCATION,CurvedMonitorModel::createBodyLayer);
        event.registerLayerDefinition(CleverBlackboardModel.LAYER_LOCATION,CleverBlackboardModel::createBodyLayer);
        event.registerLayerDefinition(UmbrellaModel.LAYER_LOCATION,UmbrellaModel::createBodyLayer);
        event.registerLayerDefinition(TheaterProjectorModel.LAYER_LOCATION,TheaterProjectorModel::createBodyLayer);
        event.registerLayerDefinition(AnimatedIceRinkModel.ICE_RINK_ALL,AnimatedIceRinkModel::createBodyLayer);
        event.registerLayerDefinition(AnimatedDeerModel.LAYER_LOCATION,AnimatedDeerModel::createBodyLayer);
        event.registerLayerDefinition(FootballGoalModel.LAYER_LOCATION,FootballGoalModel::createBodyLayer);
        // model layers for entity models
        try{
            // entity without layer (should not be textured)
            EntityRenderers.register(ThingamajigsEntities.CHAIR.get(), ChairEntityRenderer::new);
            EntityRenderers.register(ThingamajigsEntities.STOOL.get(), StoolEntityRenderer::new);
            EntityRenderers.register(ThingamajigsEntities.TOILET_ENTITY.get(), ToiletEntityRenderer::new);
            // entity with layer (should be textured)
            EntityRenderers.register(ThingamajigsEntities.INFIMOVE_MINECART.get(),
                    (provider) -> new InfiCartRenderer<>(provider, ModelLayers.MINECART));
        }
        catch (Exception entityRendererError){
            logErrorInternal(entityRendererError);
        }
    }
}
