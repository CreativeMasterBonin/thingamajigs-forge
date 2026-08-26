package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.TireScrubber;
import net.rk.thingamajigs.entity.customblock.TireScrubberBE;
import net.rk.thingamajigs.entity.models.TireScrubbers;
import net.rk.thingamajigs.xtrablock.CurvedMonitor;

public class TireScrubberBERenderer implements BlockEntityRenderer<TireScrubberBE> {
    public TireScrubbers tireScrubbers;
    public static final ResourceLocation TIRE_SCRUBBER_ALL = new ResourceLocation(
            "thingamajigs:textures/entity/tire_scrubber.png"
    );

    public TireScrubberBERenderer(BlockEntityRendererProvider.Context ctx){
        tireScrubbers = new TireScrubbers(ctx.bakeLayer(TireScrubbers.TIRE_SCRUBBER));
    }

    @Override
    public void render(TireScrubberBE tireScrubberBE, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        if(tireScrubberBE.getBlockState().getBlock() instanceof TireScrubber){
            if(tireScrubberBE.getBlockState().hasProperty(TireScrubber.LIT) && tireScrubberBE.getBlockState().hasProperty(TireScrubber.FACING)){
                if(tireScrubberBE.getBlockState().getValue(TireScrubber.LIT)){
                    // properly rotate per direction
                    if(tireScrubberBE.getBlockState().getValue(CurvedMonitor.FACING) == Direction.NORTH){
                        poseStack.translate(-1.0f,0f,0.0f);
                        poseStack.rotateAround(Axis.YP.rotationDegrees(180.0f),0.5f,0.5f,0.5f);
                    }
                    else if (tireScrubberBE.getBlockState().getValue(CurvedMonitor.FACING) == Direction.SOUTH) {
                        poseStack.translate(1.0f,0f,0.0f);
                        poseStack.rotateAround(Axis.YP.rotationDegrees(0.0f),0.5f,0.5f,0.5f);
                    }
                    else if (tireScrubberBE.getBlockState().getValue(CurvedMonitor.FACING) == Direction.EAST) {
                        poseStack.translate(0.0f,0f,-1.0f);
                        poseStack.rotateAround(Axis.YP.rotationDegrees(90.0f),0.5f,0.5f,0.5f);
                    }
                    else if (tireScrubberBE.getBlockState().getValue(CurvedMonitor.FACING) == Direction.WEST){
                        poseStack.translate(0.0f,0f,1.0f);
                        poseStack.rotateAround(Axis.YP.rotationDegrees(-90.0f),0.5f,0.5f,0.5f);
                    }
                    tireScrubbers.setup(tireScrubberBE);
                    // calculate desired speed of brush
                    tireScrubbers.centerturnpoint.xRot = (Util.getMillis() / 120.0f);
                    // correct y pos as it is offset normally
                    poseStack.translate(0D,-1.17D,0D);
                    // render everything
                    tireScrubbers.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entitySolid(TIRE_SCRUBBER_ALL)),
                            packedLight,packedOverlay,1.0f,1.0f,1.0f,1.0f);
                }
                else{
                    poseStack.translate(0D,-1.15D,0D);
                }
            }
        }
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(TireScrubberBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(TireScrubberBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(1.5, 1, 1.5)
                .closerThan(vec3.multiply(1.5, 1.15, 1.5), (double)this.getViewDistance());
    }
}
