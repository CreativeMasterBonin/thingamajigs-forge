package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.InfiMoveCart;
import net.rk.thingamajigs.entity.models.InfiCartModel;

@OnlyIn(Dist.CLIENT)
public class InfiCartRenderer<T extends AbstractMinecart> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Thingamajigs.MOD_ID, "textures/entity/infi_minecart.png");
    protected final EntityModel<T> model;
    private final BlockRenderDispatcher blockRenderer;

    public InfiCartRenderer(EntityRendererProvider.Context c, ModelLayerLocation mll) {
        super(c);
        this.shadowRadius = 0.7F; // the circle that shows under the entity (if enabled)
        this.model = new InfiCartModel<>(c.bakeLayer(mll));
        this.blockRenderer = c.getBlockRenderDispatcher();
    }

    @Override
    public ResourceLocation getTextureLocation(T defObj) {
        return TEXTURE;
    }

    // from MinecartRenderer, modified for customization in infirenderer, with hints, 2023 ver
    public void render(T tObj1, float paramF1, float paramF2, PoseStack poseStack1, MultiBufferSource multiBufSrc1, int ir3) {
        super.render(tObj1, paramF1, paramF2, poseStack1, multiBufSrc1, ir3);
        poseStack1.pushPose();
        // setup
        long i = (long)tObj1.getId() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        poseStack1.translate(f, f1, f2);

        // old stuff
        double d0 = Mth.lerp((double)paramF2, tObj1.xOld, tObj1.getX());
        double d1 = Mth.lerp((double)paramF2, tObj1.yOld, tObj1.getY());
        double d2 = Mth.lerp((double)paramF2, tObj1.zOld, tObj1.getZ());
        double d3 = (double)0.3F;
        Vec3 vec3 = tObj1.getPos(d0, d1, d2);
        float f3 = Mth.lerp(paramF2, tObj1.xRotO, tObj1.getXRot());
        if (vec3 != null) {
            Vec3 vec31 = tObj1.getPosOffs(d0, d1, d2, (double)0.3F);
            Vec3 vec32 = tObj1.getPosOffs(d0, d1, d2, (double)-0.3F);
            if (vec31 == null) {
                vec31 = vec3;
            }

            if (vec32 == null) {
                vec32 = vec3;
            }

            poseStack1.translate(vec3.x - d0, (vec31.y + vec32.y) / 2.0D - d1, vec3.z - d2);
            Vec3 vec33 = vec32.add(-vec31.x, -vec31.y, -vec31.z);
            if (vec33.length() != 0.0D) {
                vec33 = vec33.normalize();
                paramF1 = (float)(Math.atan2(vec33.z, vec33.x) * 180.0D / Math.PI);
                f3 = (float)(Math.atan(vec33.y) * 73.0D);
            }
        }

        // posing the model
        poseStack1.translate(0.0F, 0.375F, 0.0F);
        poseStack1.mulPose(Axis.YP.rotationDegrees(180.0F - paramF1));
        poseStack1.mulPose(Axis.ZP.rotationDegrees(-f3));
        float f5 = (float)tObj1.getHurtTime() - paramF2;
        float f6 = tObj1.getDamage() - paramF2;
        if (f6 < 0.0F) {
            f6 = 0.0F;
        }

        if (f5 > 0.0F) {
            poseStack1.mulPose(Axis.XP.rotationDegrees(Mth.sin(f5) * f5 * f6 / 10.0F * (float)tObj1.getHurtDir()));
        }

        int j = tObj1.getDisplayOffset();
        BlockState blockstate = tObj1.getDisplayBlockState();
        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
            poseStack1.pushPose();
            float f4 = 0.75F;
            poseStack1.scale(0.75F, 0.75F, 0.75F);
            poseStack1.translate(-0.5F, (float)(j - 8) / 16.0F, 0.5F);
            poseStack1.mulPose(Axis.YP.rotationDegrees(90.0F));
            this.renderMinecartContents(tObj1, paramF2, blockstate, poseStack1, multiBufSrc1, ir3);
            poseStack1.popPose();
        }

        if(tObj1.getDeltaMovement().y != 0 && !tObj1.isOnRails()){
            poseStack1.mulPose(Axis.ZN.rotationDegrees(
                    (float)(
                            (Mth.clamp(tObj1.getDeltaMovement().y,-75.0,75.0) * 180.0f) / Mth.PI)
            ));
        }

        // animations
        poseStack1.scale(-1.0F, -1.0F, 1.0F);
        this.model.setupAnim(tObj1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = multiBufSrc1.getBuffer(this.model.renderType(this.getTextureLocation(tObj1)));
        this.model.renderToBuffer(poseStack1, vertexconsumer, ir3, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack1.popPose();
    }

    // may want overlay in future for special stuff
    protected void renderMinecartContents(T tObj, float f1, BlockState bs, PoseStack ps, MultiBufferSource mbs, int i1) {
        this.blockRenderer.renderSingleBlock(bs, ps, mbs, i1, OverlayTexture.NO_OVERLAY);
    }
}
