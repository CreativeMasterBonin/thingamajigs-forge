package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.rk.thingamajigs.entity.customblock.DecorationalBucketBE;

public class DecorationalBucketBERenderer implements BlockEntityRenderer<DecorationalBucketBE> {
    public DecorationalBucketBERenderer(BlockEntityRendererProvider.Context ctx){
    }
    @Override
    public void render(DecorationalBucketBE decorationalBucketBE, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i1) {
        poseStack.pushPose();
        poseStack.translate(0,0,0);
        poseStack.popPose();

        if(!decorationalBucketBE.fakeTank.getFluid().isEmpty()){
            if(decorationalBucketBE.fakeTank.getFluid().getFluid() != Fluids.EMPTY){

                ResourceLocation texture = IClientFluidTypeExtensions.of(decorationalBucketBE.fakeTank.getFluid().getFluid()).getStillTexture();
                ResourceLocation textureWithPng;

                int color = IClientFluidTypeExtensions.of(decorationalBucketBE.fakeTank.getFluid().getFluid()).getTintColor();
                if(texture == null){
                    texture = MissingTextureAtlasSprite.getLocation();
                    textureWithPng = texture;
                }
                else{
                    textureWithPng = new ResourceLocation(texture.getNamespace(),"textures/" + texture.getPath() + ".png");
                }

                boolean isGlowingOrLava = decorationalBucketBE.fakeTank.getFluid().getRawFluid().getFluidType().getLightLevel() >= 1 || decorationalBucketBE.fakeTank.getFluid().getFluid() == Fluids.LAVA;

                // if a glowing liquid, use an emissive rendertype and light value
                if(isGlowingOrLava){
                    renderPart(decorationalBucketBE,
                            poseStack,
                            buffer,
                            buffer.getBuffer(RenderType.beaconBeam(textureWithPng,false)),
                            color,
                            16777215);
                }
                else{
                    // try to get the biome water color and grab the tint
                    int color2 = color;
                    try{
                        color2 = decorationalBucketBE.getLevel().getBiome(decorationalBucketBE.getBlockPos()).value().getWaterColor();
                    }
                    catch (Exception e){

                    }

                    // check if this is water, then tint it according to the color
                    if(decorationalBucketBE.fakeTank.getFluid().getFluid() == Fluids.WATER){
                        renderPart(decorationalBucketBE,
                                poseStack,
                                buffer,
                                buffer.getBuffer(RenderType.entitySolid(textureWithPng)),
                                color2,
                                16777215);
                    }
                    else{
                        renderPart(decorationalBucketBE,
                                poseStack,
                                buffer,
                                buffer.getBuffer(RenderType.entitySolid(textureWithPng)),
                                color,
                                decorationalBucketBE.fakeTank.getFluid().getRawFluid().getFluidType().getLightLevel() >= 1 ? 16777215 : packedLight);
                    }
                }
            }
        }
    }

    public static void renderPart(DecorationalBucketBE bucketBE, PoseStack poseStack,MultiBufferSource buffer,VertexConsumer consumer, int color, int packedLight){
        poseStack.pushPose();
        poseStack.rotateAround(Axis.ZP.rotationDegrees(90),0.5f,0.5f,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(0),0.5f,0.5f,0.5f);
        poseStack.rotateAround(Axis.YP.rotationDegrees(43),0.5f,0.5f,0.5f);
        poseStack.translate(0.5,0.25,0.45);
        poseStack.scale(0.3f,0.5f,0.26f);

        poseStack.translate(-0.25,0.0,-0.2);

        // we'll use the DJLaserLight rendering here
        if(bucketBE.fakeTank.getFluid().getRawFluid() != Fluids.EMPTY){
            if(bucketBE.fakeTank.getFluid().getRawFluid() == Fluids.WATER || bucketBE.fakeTank.getFluid().getRawFluid() == Fluids.FLOWING_WATER){
                DJLaserLightBERenderer.renderBeamPart(poseStack,consumer,
                        0.0f,0.27f,1.0f,0.95f,0,1,
                        0.0f,1.0f,1.0f,0.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f,0.2f,
                        0.0f,0.1f);
            }
            else{
                DJLaserLightBERenderer.renderBeamPart(poseStack,consumer,
                        1.0f,1.0f,1.0f,1.0f,0,1,
                        0.0f,1.0f,1.0f,0.0f,-1.0f,0.0f,0.0f,-1.0f,0.0f,0.2f,
                        0.0f,0.1f);
            }
        }
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(DecorationalBucketBE be) {
        return false;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(DecorationalBucketBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }
}
