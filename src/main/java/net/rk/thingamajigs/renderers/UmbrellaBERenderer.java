package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.CleverBlackboard;
import net.rk.thingamajigs.entity.customblock.UmbrellaBE;
import net.rk.thingamajigs.entity.models.UmbrellaModel;
import org.joml.Quaternionf;

public class UmbrellaBERenderer implements BlockEntityRenderer<UmbrellaBE> {
    private UmbrellaModel model;

    public UmbrellaBERenderer(BlockEntityRendererProvider.Context berp){
        model = new UmbrellaModel(berp.bakeLayer(UmbrellaModel.LAYER_LOCATION));
    }

    @Override
    public boolean shouldRenderOffScreen(UmbrellaBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 72;
    }

    @Override
    public boolean shouldRender(UmbrellaBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.5, 2.5, 2.5)
                .closerThan(vec3.multiply(2.5, 2.5, 2.5), (double)this.getViewDistance());
    }

    @Override
    public void render(UmbrellaBE be, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();
        poseStack.scale(1.0f,1.0f,1.0f);
        if(be.custom){
            poseStack.mulPose(new Quaternionf().rotateY(be.yAngle));
            poseStack.translate(be.base_x, -1.5,be.base_z);
            VertexConsumer consumer = multiBufferSource.getBuffer(RenderType.entityCutout(be.rl()));
            this.model.setupAnim(be);
            this.model.renderToBuffer(poseStack,consumer,
                    i,i1,1.0f,1.0f,1.0f,1.0f);
        }
        else{
            if(be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
                poseStack.translate(-0.5,-1.5,-0.8);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
                poseStack.translate(0.5,-1.5,0.2);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
                poseStack.translate(-0.5,-1.5,0.2);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
                poseStack.translate(0.5,-1.5,-0.8);
            }
            this.model.setupAnim(be);
            switch(be.getColor()){
                case 1 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.ORANGE.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 2 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.MAGENTA.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 3 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.LIGHT_BLUE.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 4 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.YELLOW.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 5 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.LIME.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 6 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.PINK.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 7 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.GRAY.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 8 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.LIGHT_GRAY.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 9 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.CYAN.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 10 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.PURPLE.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 11 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.BLUE.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 12 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.BROWN.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 13 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.GREEN.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 14 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.RED.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                case 15 -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.BLACK.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
                default -> {
                    this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(UmbrellaModel.LAYER_LOCATION.getModel())),
                            i,i1,1.0f,1.0f,1.0f,1.0f);
                }
            }
        }
        poseStack.popPose();
    }
}
