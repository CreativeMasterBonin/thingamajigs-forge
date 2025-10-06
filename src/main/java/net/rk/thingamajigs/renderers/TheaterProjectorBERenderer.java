package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.CleverBlackboard;
import net.rk.thingamajigs.entity.customblock.TheaterProjectorBE;
import net.rk.thingamajigs.entity.models.TheaterProjectorModel;
import org.joml.Quaternionf;

public class TheaterProjectorBERenderer implements BlockEntityRenderer<TheaterProjectorBE> {
    private TheaterProjectorModel model;

    public TheaterProjectorBERenderer(BlockEntityRendererProvider.Context berp){
        model = new TheaterProjectorModel(berp.bakeLayer(TheaterProjectorModel.LAYER_LOCATION));
    }

    @Override
    public boolean shouldRenderOffScreen(TheaterProjectorBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 68;
    }

    @Override
    public boolean shouldRender(TheaterProjectorBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.5, 2.5, 2.5)
                .closerThan(vec3.multiply(2.5, 2.5, 2.5), (double)this.getViewDistance());
    }

    @Override
    public void render(TheaterProjectorBE be, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();
        poseStack.scale(1.0f,1.0f,1.0f);
        VertexConsumer consumer = multiBufferSource.getBuffer(RenderType.entitySolid(TheaterProjectorModel.LAYER_LOCATION.getModel()));
        if(be.custom){
            poseStack.mulPose(new Quaternionf().rotateY(be.yAngle));
            poseStack.translate(be.x_xtra, 1.25f, be.z_xtra);
        }
        else{
            if(be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
                poseStack.translate(-0.5,1.25f,-0.8);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
                poseStack.translate(0.5,1.25f,0.2);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
                poseStack.translate(-0.5,1.25f,0.2);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
                poseStack.translate(0.5,1.25f,-0.8);
            }
        }
        this.model.projectorTop.xRot = be.projector_image_angle;
        this.model.setupAnim(be);
        this.model.renderToBuffer(poseStack,consumer,
                i,i1,1.0f,1.0f,1.0f,1.0f);
        poseStack.popPose();
    }
}
