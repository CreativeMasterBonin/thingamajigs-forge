package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.customblock.AnimatedIceRinkBE;
import net.rk.thingamajigs.entity.customblock.CleverBlackboardBE;
import net.rk.thingamajigs.entity.customblock.FootballGoalBE;
import net.rk.thingamajigs.entity.customblock.UmbrellaBE;
import net.rk.thingamajigs.entity.models.AnimatedIceRinkModel;
import net.rk.thingamajigs.entity.models.CleverBlackboardModel;
import net.rk.thingamajigs.entity.models.FootballGoalModel;
import net.rk.thingamajigs.entity.models.UmbrellaModel;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class ThingamajigsBEWLR extends BlockEntityWithoutLevelRenderer {
    //
    private FootballGoalBE footballGoalBE = new FootballGoalBE(
            new BlockPos(0,0,0),
            ThingamajigsBlocks.FOOTBALL_GOAL.get().defaultBlockState());

    private CleverBlackboardBE cleverBlackboard = new CleverBlackboardBE(
            new BlockPos(0,0,0),
            ThingamajigsBlocks.CLEVER_BLACKBOARD.get().defaultBlockState()
    );
    private AnimatedIceRinkBE iceRinkBE = new AnimatedIceRinkBE(
            new BlockPos(0,0,0),
            ThingamajigsBlocks.ANIMATED_ICE_RINK.get().defaultBlockState()
    );
    private UmbrellaBE umbrellaBE = new UmbrellaBE(
            new BlockPos(0,0,0),
            ThingamajigsBlocks.UMBRELLA.get().defaultBlockState()
    );
    // model instances
    private FootballGoalModel footballGoalModel;
    private CleverBlackboardModel cleverBlackboardModel;
    private AnimatedIceRinkModel iceRinkModel;
    private UmbrellaModel umbrellaModel;


    private EntityModelSet set = Minecraft.getInstance().getEntityModels();

    // standalone model layer locations
    public static final ModelLayerLocation FOOTBALL_GOAL_LOC = new ModelLayerLocation(
            new ResourceLocation("thingamajigs:textures/entity/football_goal.png"), "main");
    public static final ModelLayerLocation CLEVERBLACKBOARD_LOC = new ModelLayerLocation(
            new ResourceLocation("thingamajigs:textures/entity/clever_blackboard.png"), "main");
    public static final ModelLayerLocation ICE_RINK_LOC =
            new ModelLayerLocation(new ResourceLocation("thingamajigs:textures/entity/animated_snow_rink.png"), "main");
    public static final ModelLayerLocation UMBRELLA_LOC =
            new ModelLayerLocation(new ResourceLocation("thingamajigs:textures/entity/animated_snow_rink.png"), "main");


    public ThingamajigsBEWLR(BlockEntityRenderDispatcher dispatcher, EntityModelSet set){
        super(dispatcher,set);
        this.footballGoalModel = new FootballGoalModel(this.set.bakeLayer(FOOTBALL_GOAL_LOC));
        this.cleverBlackboardModel = new CleverBlackboardModel(this.set.bakeLayer(CLEVERBLACKBOARD_LOC));
        this.iceRinkModel = new AnimatedIceRinkModel(this.set.bakeLayer(ICE_RINK_LOC));
        this.umbrellaModel = new UmbrellaModel(this.set.bakeLayer(UMBRELLA_LOC));
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        this.footballGoalModel = new FootballGoalModel(this.set.bakeLayer(FOOTBALL_GOAL_LOC));
        this.cleverBlackboardModel = new CleverBlackboardModel(this.set.bakeLayer(CLEVERBLACKBOARD_LOC));
        this.iceRinkModel = new AnimatedIceRinkModel(this.set.bakeLayer(ICE_RINK_LOC));
        this.umbrellaModel = new UmbrellaModel(this.set.bakeLayer(UMBRELLA_LOC));
    }

    // item form context rendering
    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource mbs, int i, int i1){
        float tick = Minecraft.getInstance().getPartialTick();
        // football goal
        if(itemStack.is(ThingamajigsItems.FOOTBALL_GOAL.get())){
            poseStack.pushPose();
            poseStack.scale(0.25f,0.25f,0.25f);
            poseStack.translate(0.0,0.0,0.0);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(FOOTBALL_GOAL_LOC.getModel()));
            this.footballGoalModel.setupAnim(footballGoalBE);
            this.footballGoalModel.renderToBuffer(poseStack,
                    vc,
                    i,i1,1.0f,1.0f,1.0f,1.0f);
            poseStack.popPose();
        } // clever blackboard
        else if(itemStack.is(ThingamajigsItems.CLEVER_BLACKBOARD.get())){
            poseStack.pushPose();
            poseStack.scale(0.25f,0.25f,0.25f);
            poseStack.translate(0.0,0.0,0.0);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(CLEVERBLACKBOARD_LOC.getModel()));
            this.cleverBlackboardModel.setupAnim(cleverBlackboard);
            this.cleverBlackboardModel.renderToBuffer(poseStack,
                    vc,
                    i,i1,1.0f,1.0f,1.0f,1.0f);
            poseStack.popPose();
        } // animated ice rink
        else if(itemStack.is(ThingamajigsItems.ANIMATED_ICE_RINK.get())){
            poseStack.pushPose();
            poseStack.scale(1f,1f,1f);
            poseStack.translate(0.5,-1.35,0.5);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(ICE_RINK_LOC.getModel()));
            float rinkAngle = Mth.lerp(tick / 132.0f,0.0f,1.0f);
            float ferrisAngle = Mth.lerp(tick / 72.0f,0.0f,1.0f);
            this.iceRinkModel.setupAnimNoBE(rinkAngle,ferrisAngle);
            this.iceRinkModel.main.render(poseStack,vc,i,i1);
            poseStack.popPose();
        }
        else if(itemStack.is(ThingamajigsItems.UMBRELLA.get())){
            poseStack.pushPose();
            poseStack.scale(1.0f,1.0f,1.0f);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(UMBRELLA_LOC.getModel()));
            this.umbrellaModel.setupAnim(umbrellaBE);
            this.umbrellaModel.renderToBuffer(poseStack,vc,
                    i,i1,1.0f,1.0f,1.0f,1.0f);
            poseStack.popPose();
        }
        else{
            super.renderByItem(itemStack,context,poseStack,mbs,i,i1);
        }
    }
}
