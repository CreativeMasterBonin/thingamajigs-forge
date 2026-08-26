package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.entity.customblock.MitterCurtainBE;
import net.rk.thingamajigs.entity.models.MitterCurtainModel;

public class MitterCurtainBERenderer implements BlockEntityRenderer<MitterCurtainBE> {
    public static final ResourceLocation MITTER_CURTAIN_ALL = new ResourceLocation(
            "thingamajigs:textures/entity/mitter_curtain.png"
    );
    public MitterCurtainModel mitterCurtainModel;

    public MitterCurtainBERenderer(BlockEntityRendererProvider.Context ctx){
        mitterCurtainModel = new MitterCurtainModel(ctx.bakeLayer(MitterCurtainModel.MITTER_CURTAIN));
    }

    @Override
    public void render(MitterCurtainBE mitterCurtainBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.rotateAround(Axis.YP.rotationDegrees(mitterCurtainBE.yAngle),0.5f,0.5f,0.5f);
        if(mitterCurtainBE.horizontal){
            poseStack.translate(0.5D,-0.57D,0.5D);
        }
        else{
            poseStack.translate(0.5D,-0.57D,0.5D);
        }
        // check the state
        if(mitterCurtainBE.getBlockState().hasProperty(BlockStateProperties.LIT)){
            // initial
            makeMitterCurtain(mitterCurtainBE,0f,0f,0f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.0f);
            // closer
            makeMitterCurtain(mitterCurtainBE,-0.17f,0f,0.17f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.3f);
            makeMitterCurtain(mitterCurtainBE,0.17f,0f,-0.17f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.3f);
            makeMitterCurtain(mitterCurtainBE,0.17f,0f,0.17f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.7f);
            makeMitterCurtain(mitterCurtainBE,-0.17f,0f,-0.17f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.7f);
            // further away
            makeMitterCurtain(mitterCurtainBE,-0.23f,0f,0.23f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.4f);
            makeMitterCurtain(mitterCurtainBE,0.23f,0f,-0.23f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.6f);
            makeMitterCurtain(mitterCurtainBE,0.23f,0f,0.23f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.6f);
            makeMitterCurtain(mitterCurtainBE,-0.23f,0f,-0.23f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.4f);
        }
        poseStack.popPose();
    }

    public void makeMitterCurtain(MitterCurtainBE mitter,float xOffset,float yOffset,float zOffset, float partialTick,PoseStack poseStack,MultiBufferSource buffer,int packedLight,float min, float max, float timingOffset){
        poseStack.pushPose();
        poseStack.translate(xOffset,yOffset,zOffset);
        float angle = min + Mth.sin(Util.getMillis() / (297.0f + timingOffset)) * max;

        if(mitter.getBlockState().getValue(BlockStateProperties.LIT)){
            if(mitter.horizontal){
                poseStack.rotateAround(Axis.XP.rotationDegrees(angle),
                        0f,1f,0f);
            }
            else{
                poseStack.rotateAround(Axis.ZP.rotationDegrees(angle),
                        0f,1f,0f);
            }
        }
        mitterCurtainModel.singular_curtain.render(poseStack,
                buffer.getBuffer(RenderType.entitySolid(MITTER_CURTAIN_ALL)),packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(MitterCurtainBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 72;
    }
}
