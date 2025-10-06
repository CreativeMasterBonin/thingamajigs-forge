package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.entity.customblock.RailroadCrossingBE;
import net.rk.thingamajigs.entity.models.RRArmModel;

public class RailroadCrossingArmBERenderer implements BlockEntityRenderer<RailroadCrossingBE>{
    public RailroadCrossingArmBERenderer(BlockEntityRendererProvider.Context berpContext){
        model = new RRArmModel(berpContext.bakeLayer(RRArmModel.LAYER_LOCATION));
    }

    private RRArmModel model;

    @Override
    public void render(RailroadCrossingBE railroadCrossingBE, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();
        poseStack.scale(1.0f,1.0f,1.0f);
        poseStack.translate(0.5f,-0.5f,0.5f);

        this.model.setupAnim(railroadCrossingBE);
        this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityTranslucent(RRArmModel.LAYER_LOCATION.getModel())),i,i1,1.0f,1.0f,1.0f,1.0f);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 54;
    }

    @Override
    public boolean shouldRenderOffScreen(RailroadCrossingBE rrbe) {
        return true;
    }

    @Override
    public boolean shouldRender(RailroadCrossingBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }
}
