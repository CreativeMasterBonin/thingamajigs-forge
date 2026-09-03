package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.customblock.CustomizableCopyingDecoBE;

import java.util.Objects;

public class CustomizableCopyingDecoRenderer implements BlockEntityRenderer<CustomizableCopyingDecoBE> {
    private final Minecraft mc;
    private final BlockRenderDispatcher dispatcher;
    private final ModelBlockRenderer blockRenderer;
    private final ModelManager manager;
    private final BlockModelShaper blockModelShaper;

    public CustomizableCopyingDecoRenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = mc.getBlockRenderer();
        blockRenderer = dispatcher.getModelRenderer();
        manager = dispatcher.getBlockModelShaper().getModelManager();
        blockModelShaper = dispatcher.getBlockModelShaper();
    }

    @Override
    public void render(CustomizableCopyingDecoBE customizableCopyingDecoBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        // don't allow air, liquids or blockentity blocks to be rendered
        if(!(customizableCopyingDecoBE.blockTypeToCopy.getBlock() instanceof LiquidBlock) && !(customizableCopyingDecoBE.blockTypeToCopy.getBlock() instanceof EntityBlock)){
            // modify the model translations and such
            // scale the model
            poseStack.scale(Mth.clamp((float)customizableCopyingDecoBE.modelScale.x,0.01f,32.0f),
                    Mth.clamp((float)customizableCopyingDecoBE.modelScale.y,0.01f,32.0f),
                    Mth.clamp((float)customizableCopyingDecoBE.modelScale.z,0.01f,32.0f));
            // rotate the model
            poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.wrapDegrees((float)customizableCopyingDecoBE.modelRotations.x)),0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.wrapDegrees((float)customizableCopyingDecoBE.modelRotations.y)),0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.wrapDegrees((float)customizableCopyingDecoBE.modelRotations.z)),0.5f,0.5f,0.5f);
            // translate the model
            // offset based on scale as scale will reposition visually where the model is
            double offsetX2 = customizableCopyingDecoBE.modelOffsets.x;
            double offsetY2 = customizableCopyingDecoBE.modelOffsets.y;
            double offsetZ2 = customizableCopyingDecoBE.modelOffsets.z;
            poseStack.translate(offsetX2,offsetY2,offsetZ2);

            BakedModel model;
            // get model and render it
            if(customizableCopyingDecoBE.blockTypeToCopy.isAir()){
                model = blockModelShaper.getBlockModel(ThingamajigsBlocks.CUSTOMIZABLE_COPYING_DECO.get().defaultBlockState());
            }
            else{
                model = blockModelShaper.getBlockModel(customizableCopyingDecoBE.blockTypeToCopy);
            }

            // render in proper mode
            switch (customizableCopyingDecoBE.renderingMode) {
                case "solid" -> {
                    blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),
                            null,
                            model,
                            1.0f, 1.0f, 1.0f,
                            packedLight, packedOverlay, ModelData.EMPTY, RenderType.solid());
                    break;
                }
                case "cutout" -> {
                    blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.cutoutBlockSheet()),
                            null,
                            model,
                            1.0f, 1.0f, 1.0f,
                            packedLight, packedOverlay, ModelData.EMPTY, RenderType.cutout());
                    break;
                }
                case "translucent" -> {
                    blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.translucentCullBlockSheet()),
                            null,
                            model,
                            1.0f, 1.0f, 1.0f,
                            packedLight, packedOverlay, ModelData.EMPTY, RenderType.translucent());
                    break;
                }
                default -> {
                    // fail-safe render
                    blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),
                            null,
                            model,
                            1.0f, 1.0f, 1.0f,
                            packedLight, packedOverlay, ModelData.EMPTY, RenderType.solid());
                }
            }
        }
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(CustomizableCopyingDecoBE customDeco, Vec3 vec3) {
        return Vec3.atCenterOf(customDeco.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public boolean shouldRenderOffScreen(CustomizableCopyingDecoBE customDeco) {
        return true;
    }
}
