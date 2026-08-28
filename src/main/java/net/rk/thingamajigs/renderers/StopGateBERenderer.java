package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.rk.thingamajigs.block.custom.blocks.StopGate;
import net.rk.thingamajigs.entity.customblock.StopGateBE;
import net.rk.thingamajigs.entity.models.GateArmModel;

@SuppressWarnings("deprecated,unused")
public class StopGateBERenderer implements BlockEntityRenderer<StopGateBE>{
    public GateArmModel gateArmModel;
    public static final ResourceLocation GATE_ARM_ALL = new ResourceLocation("thingamajigs:textures/entity/gate_arm.png");

    public StopGateBERenderer(BlockEntityRendererProvider.Context ctx){
        gateArmModel = new GateArmModel(ctx.bakeLayer(GateArmModel.GATE_ARM));
    }

    @Override
    public void render(StopGateBE stopGateBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        // rotate arm up-down degrees
        // rotate arm around based on direction
        if(stopGateBE.getBlockState().hasProperty(StopGate.FACING)){
            switch(stopGateBE.getBlockState().getValue(StopGate.FACING)){
                case NORTH->{
                    poseStack.rotateAround(Axis.ZP.rotationDegrees(stopGateBE.gateAngle * -1.0f),
                            stopGateBE.northXRot,stopGateBE.northYRot,stopGateBE.northZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
                }
                case SOUTH->{
                    poseStack.rotateAround(Axis.ZP.rotationDegrees(stopGateBE.gateAngle),stopGateBE.southXRot,stopGateBE.southYRot,stopGateBE.southZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
                }
                case EAST->{
                    poseStack.rotateAround(Axis.XP.rotationDegrees(stopGateBE.gateAngle),stopGateBE.eastXRot,stopGateBE.eastYRot,stopGateBE.eastZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(-90),0.5f,0.5f,0.5f);
                }
                case WEST->{
                    poseStack.rotateAround(Axis.XP.rotationDegrees(stopGateBE.gateAngle * -1.0f),stopGateBE.westXRot,stopGateBE.westYRot,stopGateBE.westZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
                }
            }
        }
        // move arm to position
        poseStack.translate(stopGateBE.offsetX,stopGateBE.offsetY,stopGateBE.offsetZ);
        // render the arm model
        gateArmModel.gateArm.render(poseStack,buffer.getBuffer(RenderType.entitySolid(
                GATE_ARM_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRenderOffScreen(StopGateBE stopGate) {
        return true;
    }
}
