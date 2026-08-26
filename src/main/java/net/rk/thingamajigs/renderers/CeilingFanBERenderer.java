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
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.CeilingFan;
import net.rk.thingamajigs.entity.customblock.CeilingFanBE;
import net.rk.thingamajigs.entity.models.CeilingFanBlades;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class CeilingFanBERenderer implements BlockEntityRenderer<CeilingFanBE> {
    public CeilingFanBlades blades;
    public static final ResourceLocation CEILING_FAN_BLADES_ALL =
            new ResourceLocation("thingamajigs:textures/entity/ceiling_fan_blades.png");

    public CeilingFanBERenderer(BlockEntityRendererProvider.Context ctx){
        blades = new CeilingFanBlades(ctx.bakeLayer(CeilingFanBlades.CEILING_FAN_BLADES));
    }

    @Override
    public void render(CeilingFanBE ceilingFanBE, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5D,-1.25D,0.5D);

        //poseStack.rotateAround(Axis.YP.rotationDegrees(Util.getMillis() / 256.0f), 0.5f,0.5f,0.5f);
        if(ceilingFanBE.getBlockState().hasProperty(CeilingFan.LIT)){
            if(ceilingFanBE.getBlockState().getValue(CeilingFan.LIT)){
                if(ceilingFanBE.reversed){
                    blades.fan.yRot = ThingamajigsCalcStuffs.degreesToRadians(Mth.wrapDegrees((Util.getMillis() * 1.57f) / 4.0f)) * -1.0f;
                }
                else{
                    blades.fan.yRot = ThingamajigsCalcStuffs.degreesToRadians(Mth.wrapDegrees((Util.getMillis() * 1.57f) / 4.0f));
                }
            }
            else{
                blades.fan.yRot = 0.0f;
            }
        }

        blades.fan.render(poseStack,
                multiBufferSource.getBuffer(RenderType.entitySolid(CEILING_FAN_BLADES_ALL)),
                packedLight,OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(CeilingFanBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(CeilingFanBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(1.5, 1, 1.5)
                .closerThan(vec3.multiply(1.5, 1.15, 1.5), (double)this.getViewDistance());
    }
}
