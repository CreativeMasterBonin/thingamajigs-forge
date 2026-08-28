package net.rk.thingamajigs.renderers;

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
