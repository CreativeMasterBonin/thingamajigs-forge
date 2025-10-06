package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rk.thingamajigs.entity.customblock.GravestoneBE;
import org.joml.Matrix4f;

//@OnlyIn(Dist.CLIENT)
public class GravestoneBERenderer {
    /*
    private final Font font;

    public GravestoneBERenderer(BlockEntityRendererProvider.Context ctx){
        this.font = ctx.getFont();
    }

    @Override
    public void render(GravestoneBE be, float ptick, PoseStack pose, MultiBufferSource mbs, int light, int overlay) {
        pose.pushPose();
        if(!be.hidePose){
            if(be.angle == 90) {
                // east
                pose.translate(-0.7, 0.9, 0.35);
            }
            else if(be.angle == 270) {
                // west
                pose.translate(0.7, 0.9, 0.75);
            }
            else if(be.angle == 0) {
                // south
                pose.translate(0.35, 0.9, -0.7);
            }
            else if(be.angle == 180) {
                // north
                pose.translate(0.3, 0.9, 0.7);
            }

            pose.mulPose(Axis.XP.rotationDegrees(180));
            pose.mulPose(Axis.ZP.rotationDegrees(180));
            pose.mulPose(Axis.YP.rotationDegrees(be.angle + 180));

            float f = 0.0096f;
            pose.scale(f, -f, f);

            this.font.drawInBatch(be.line, (float)1, (float)1, 1, false, pose.last().pose(), mbs, Font.DisplayMode.POLYGON_OFFSET, 0, 0);
        }
        pose.popPose();
    }

     */
}
