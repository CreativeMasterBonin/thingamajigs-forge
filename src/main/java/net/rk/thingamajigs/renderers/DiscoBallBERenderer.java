package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rk.thingamajigs.entity.customblock.DiscoBallBE;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.util.logging.Logger;


@SuppressWarnings("deprecated")
public class DiscoBallBERenderer {
    /*
    //public static final Material RL = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation("block/animated/disco_ball"));
    private static final ResourceLocation BALL_TEXTURE = new ResourceLocation("thingamajigs","textures/block/animated/disco_ball.png");

    public DiscoBallBERenderer(BlockEntityRendererProvider.Context ctx){}

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition main = partdefinition.addOrReplaceChild("main",
                CubeListBuilder.create().texOffs(0, 36)
                        .addBox(-14.0F, -14.0F, 0.0F, 14.0F, 14.0F, 14.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(DiscoBallBE dbbe, float ptick, PoseStack pose, MultiBufferSource mbs, int light, int overlay) {
        try{
            long j = dbbe.getLevel().getGameTime();
            float f = (float)Math.floorMod(j, 180) + ptick;
            float f1 = -f;
            float f2 = Mth.frac(f1 * 0.2F - (float)Mth.floor(f1 * 0.1F));
            float f16 = -0.5f;
            float radius = 0.5f;

            pose.pushPose();

            pose.translate(dbbe.getBlockPos().getX(),dbbe.getBlockPos().getY(),dbbe.getBlockPos().getZ());

            renderBeamPart(pose,mbs.getBuffer(RenderType.entitySolid(BALL_TEXTURE)), 1.0f, 1.0f, 1.0f,
                    1.0f, 0, 1,
                    0.0f, radius, radius,
                    0.0f, -radius, 0.0F, 0.0F, -radius, 0.0F, 1.0F, f16, 0.0f
            );

            pose.popPose();
        }
        catch(Exception e){
            Logger.getAnonymousLogger().warning("Thingamajigs' DiscoBallBERenderer is broken!" + e.getMessage());
        }
    }

*/
}
