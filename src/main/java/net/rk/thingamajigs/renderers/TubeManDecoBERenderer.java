package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
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
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.TubeManDeco;
import net.rk.thingamajigs.entity.customblock.TubeManDecoBE;

import java.util.Objects;

public class TubeManDecoBERenderer implements BlockEntityRenderer<TubeManDecoBE> {
    private final Minecraft mc;
    private BlockRenderDispatcher dispatcher;
    private ModelBlockRenderer blockRenderer;
    private ModelManager manager;
    private final BlockModelShaper blockModelShaper;

    public TubeManDecoBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = mc.getBlockRenderer().getModelRenderer();
        manager = Objects.requireNonNull(dispatcher.getBlockModelShaper().getModelManager());
        blockModelShaper = mc.getBlockRenderer().getBlockModelShaper();
    }

    @Override
    public void render(TubeManDecoBE tubeManDecoBE, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        BakedModel base = blockModelShaper.getBlockModel(ThingamajigsBlocks.TUBE_MAN_DECO.get().defaultBlockState().setValue(TubeManDeco.TOGGLED,true));
        BakedModel baseCompressed = blockModelShaper.getBlockModel(ThingamajigsBlocks.TUBE_MAN_DECO.get().defaultBlockState());
        BakedModel coloredBody; // assigned below
        BakedModel head = blockModelShaper.getBlockModel(Blocks.OBSERVER.defaultBlockState());

        if (Objects.requireNonNull(tubeManDecoBE.color) == DyeColor.ORANGE) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.ORANGE_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.MAGENTA) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.MAGENTA_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.LIGHT_BLUE) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.LIGHT_BLUE_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.YELLOW) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.YELLOW_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.LIME) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.LIME_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.PINK) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.PINK_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.GRAY) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.GRAY_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.LIGHT_GRAY) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.LIGHT_GRAY_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.CYAN) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.CYAN_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.PURPLE) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.PURPLE_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.BLUE) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.BLUE_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.BROWN) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.BROWN_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.GREEN) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.GREEN_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.RED) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.RED_WOOL.defaultBlockState());
        } else if (tubeManDecoBE.color == DyeColor.BLACK) {
            coloredBody = blockModelShaper.getBlockModel(Blocks.BLACK_WOOL.defaultBlockState());
        } else {
            coloredBody = blockModelShaper.getBlockModel(Blocks.WHITE_WOOL.defaultBlockState());
        }

        poseStack.pushPose();
        poseStack.rotateAround(Axis.YP.rotationDegrees(tubeManDecoBE.yAngle),0.5f,0.5f,0.5f);

        if(tubeManDecoBE.getBlockState().getValue(TubeManDeco.TOGGLED)){
            this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                    base,
                    1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            poseStack.translate(0,0.25,0);

            poseStack.scale(0.65f,1.0f,0.65f); // start resize
            poseStack.translate(0.28f,0.0f,0.28f);

            poseStack.rotateAround(Axis.ZP.rotationDegrees((-4.0f - Mth.sin(Util.getMillis() / 320f) + 4.0f) * (3.0f * tubeManDecoBE.getRandomOffset() - 1.0f)),0.5f,-0.2f,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees((-4.0f - Mth.sin(Util.getMillis() / 625f) + 4.0f) * (2.0f * tubeManDecoBE.getRandomOffset() - 2.0f)),0.5f,-0.2f,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }

            float clampedCosMid = (-3.0f - Mth.cos(Util.getMillis() / 495f) + 3.0f) * (3.0f + tubeManDecoBE.getRandomOffset() * 2.1f);

            poseStack.translate(0,1,0);
            poseStack.rotateAround(Axis.ZP.rotationDegrees((-2.0f - Mth.sin(Util.getMillis() / 475f) + 2.0f) * (4.0f + tubeManDecoBE.getRandomOffset() * 3.0f)),0.5f,0,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.clamp(clampedCosMid,-15.0f,15.0f)),0.5f,0,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }

            poseStack.scale(1.0f,1.0f,1.0f); // end resize

            poseStack.translate(0,1,0);
            poseStack.rotateAround(Axis.ZP.rotationDegrees((-4.0f - Mth.sin(Util.getMillis() / 210f)) + 4.0f),0.5f,0,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees((-5.0f - Mth.sin(Util.getMillis() / 202f) + 5.0f) * 3.0f),0.5f,0,0.5f);

            float clampedCosHeadSelectionZ = (-tubeManDecoBE.getRandomOffset() * 32.0f)
                    - Mth.cos(Util.getMillis() / 100f)
                    + (tubeManDecoBE.getRandomOffset() * 32.0f);

            float clampedCosHeadSelectionX = (-tubeManDecoBE.getRandomOffset() * 32.0f)
                    - Mth.cos(Util.getMillis() / 100f)
                    + (tubeManDecoBE.getRandomOffset() * 32.0f);

            poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.clamp(clampedCosHeadSelectionZ,-15.0f,15.0f)),
                    0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.clamp(clampedCosHeadSelectionX,-15.0f,15.0f)),
                    0.5f,0.5f,0.5f);

            poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);

            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        head,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        head,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }

            poseStack.rotateAround(Axis.YP.rotationDegrees(-180),0.5f,0.5f,0.5f);

            poseStack.translate(1,-0.5,0.25);
            poseStack.rotateAround(Axis.ZP.rotationDegrees(90),0.5f,0.5f,0.5f);
            poseStack.scale(0.5f,1.75f,0.5f);
            poseStack.translate(0f,-0.35f,0f); // correct arm pos
            poseStack.rotateAround(Axis.XP.rotationDegrees((-13.0f - Mth.sin(Util.getMillis() / 300f) + 13.0f) * (9.0f + tubeManDecoBE.getRandomOffset())),0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.ZP.rotationDegrees((-13.0f - Mth.sin(Util.getMillis() / 370f) + 13.0f) * (5.0f - tubeManDecoBE.getRandomOffset())),0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.YP.rotationDegrees((-5.0f - Mth.sin(Util.getMillis() / 190f) + 5.0f) * (4.0f + tubeManDecoBE.getRandomOffset())),0.5f,0.5f,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }

            poseStack.translate(0,1.5,0);
            poseStack.rotateAround(Axis.YP.rotationDegrees((-5.0f - Mth.sin(Util.getMillis() / 170f) + 5.0f) * 8.0f),0.5f,0.5f,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        coloredBody,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
        }
        else{
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        baseCompressed,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        baseCompressed,
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.solid());
            }
        }

        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 89;
    }

    @Override
    public boolean shouldRenderOffScreen(TubeManDecoBE be) {
        return true;
    }

    @Override
    public boolean shouldRender(TubeManDecoBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 3.0, 2.0)
                .closerThan(vec3.multiply(2.0, 3.0, 2.0), (double)this.getViewDistance());
    }
}
