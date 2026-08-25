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
import net.rk.thingamajigs.block.ShortCarWashBrush;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.customblock.CarWashBrushBE;
import net.rk.thingamajigs.entity.models.CarWashMixedBrushModel;
import net.rk.thingamajigs.entity.models.CarWashTallBrushModel;

import static net.rk.thingamajigs.entity.models.CarWashMixedBrushModel.MIXED_BRUSH;
import static net.rk.thingamajigs.entity.models.CarWashTallBrushModel.*;

public class CarWashBrushBERenderer implements BlockEntityRenderer<CarWashBrushBE> {
    public CarWashTallBrushModel tallBlueBrushModel;
    public CarWashTallBrushModel tallRedBrushModel;
    public CarWashTallBrushModel tallYellowBrushModel;
    public CarWashMixedBrushModel mixedBrushModel;
    public static final ResourceLocation BLUE_BRUSH_ALL =
            new ResourceLocation("thingamajigs:textures/entity/blue_car_wash_brush.png");
    public static final ResourceLocation RED_BRUSH_ALL =
            new ResourceLocation("thingamajigs:textures/entity/red_car_wash_brush.png");
    public static final ResourceLocation YELLOW_BRUSH_ALL =
            new ResourceLocation("thingamajigs:textures/entity/yellow_car_wash_brush.png");
    public static final ResourceLocation MIXED_BRUSH_ALL =
            new ResourceLocation("thingamajigs:textures/entity/mixed_car_wash_brush.png");

    public CarWashBrushBERenderer(BlockEntityRendererProvider.Context ctx){
        tallBlueBrushModel = new CarWashTallBrushModel(ctx.bakeLayer(BLUE_BRUSH));
        tallRedBrushModel = new CarWashTallBrushModel(ctx.bakeLayer(RED_BRUSH));
        tallYellowBrushModel = new CarWashTallBrushModel(ctx.bakeLayer(YELLOW_BRUSH));
        mixedBrushModel = new CarWashMixedBrushModel(ctx.bakeLayer(MIXED_BRUSH));
    }

    @Override
    public void render(CarWashBrushBE carWashBrushBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        // render all sections
        poseStack.pushPose();
        if(carWashBrushBE.getBlockState().getBlock() instanceof ShortCarWashBrush){
            for(int brush=0;brush<33;brush++){
                renderOneShortCarBrushBladeForShortBrushVariant(carWashBrushBE,partialTick,brush * 11.0f,poseStack,buffer,packedLight);
            }
        }
        else{
            float milliTick = partialTick * (Util.getMillis() / 36000.0f);
            float smoothedYExtensionOffset = carWashBrushBE.yExtensionOffset * (Util.getMillis() / 326000.0f);

            poseStack.rotateAround(Axis.ZP.rotationDegrees(180.0f),0.0f,0.0f,0.0f);
            poseStack.translate(-1.0f,-4.0f,0.0f);
            poseStack.scale(0.75f,0.95f,0.75f);
            poseStack.translate(0.16f,0.35f - smoothedYExtensionOffset,0.15f);
            // bottom brushes
            for(int brush =0;brush<33;brush++) {
                renderOneCarBrushBlade(carWashBrushBE,milliTick,brush * 11.0f,poseStack,buffer,packedLight);
                renderOneMedCarBrushBlade(carWashBrushBE,milliTick,brush * 11.0f,poseStack,buffer,packedLight);
                renderOneShortCarBrushBlade(carWashBrushBE,milliTick,brush * 11.0f,poseStack,buffer,packedLight);
            }
            // top brushes
            poseStack.translate(0,-0.53f - smoothedYExtensionOffset,0);
            for(int brush2=0;brush2<33;brush2++){
                renderOneCarBrushBlade(carWashBrushBE,milliTick,brush2 * 11.0f,poseStack,buffer,packedLight);
                renderOneMedCarBrushBlade(carWashBrushBE,milliTick,brush2 * 11.0f,poseStack,buffer,packedLight);
                renderOneShortCarBrushBlade(carWashBrushBE,milliTick,brush2 * 11.0f,poseStack,buffer,packedLight);
            }
        }
        poseStack.popPose();
    }


    public void renderOneCarBrushBlade(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.translate(0f, CarWashBrushBE.yOffset + 0.25f, 0f);
        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 7.0f;

        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,carWashBrushBE.ymod,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,carWashBrushBE.ymod,0.5f);

        if (carWashBrushBE.getBlockState().getBlock().equals(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get())) {
            tallBlueBrushModel.setup(carWashBrushBE);
            tallBlueBrushModel.brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(BLUE_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        else if(carWashBrushBE.getBlockState().getBlock().equals(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get())){
            tallRedBrushModel.setup(carWashBrushBE);
            tallRedBrushModel.brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(RED_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        else{
            tallYellowBrushModel.setup(carWashBrushBE);
            tallYellowBrushModel.brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(YELLOW_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
    }

    public void renderOneMedCarBrushBlade(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.translate(0,CarWashBrushBE.yOffset + 0.5f,0);
        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 6.0f;
        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,carWashBrushBE.ymod,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,carWashBrushBE.ymod,0.5f);

        if (carWashBrushBE.getBlockState().getBlock().equals(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get())) {
            tallBlueBrushModel.setup(carWashBrushBE);
            tallBlueBrushModel.med_brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(BLUE_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        else if(carWashBrushBE.getBlockState().getBlock().equals(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get())){
            tallRedBrushModel.setup(carWashBrushBE);
            tallRedBrushModel.med_brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(RED_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        else{
            tallYellowBrushModel.setup(carWashBrushBE);
            tallYellowBrushModel.med_brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(YELLOW_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
    }

    public void renderOneShortCarBrushBlade(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.translate(0,CarWashBrushBE.yOffset + 0.75f,0);

        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 5.0f;
        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,carWashBrushBE.ymod,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,carWashBrushBE.ymod,0.5f);

        if (carWashBrushBE.getBlockState().getBlock().equals(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get())) {
            tallBlueBrushModel.setup(carWashBrushBE);
            tallBlueBrushModel.small_brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(BLUE_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        else if(carWashBrushBE.getBlockState().getBlock().equals(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get())){
            tallRedBrushModel.setup(carWashBrushBE);
            tallRedBrushModel.small_brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(RED_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        else{
            tallYellowBrushModel.setup(carWashBrushBE);
            tallYellowBrushModel.small_brush_piece.render(poseStack,buffer.getBuffer(RenderType.entitySolid(YELLOW_BRUSH_ALL)),packedLight,OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
    }

    public void renderOneShortCarBrushBladeForShortBrushVariant(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.scale(0.7f,0.7f,0.7f);
        poseStack.translate(
                0.2,
                Mth.clamp(1.0 + (carWashBrushBE.extensionAngle / 45),0.0,1.45),
                0.2);
        poseStack.rotateAround(Axis.ZP.rotationDegrees(0),0.5f,0.5f,0.5f);

        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 5.0f;
        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,carWashBrushBE.ymod,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle * -1.0f),0.5f,carWashBrushBE.ymod,0.5f);

        mixedBrushModel.setup();
        mixedBrushModel.mixed_brush_blade.render(poseStack,buffer.getBuffer(RenderType.entitySolid(MIXED_BRUSH_ALL)),
                packedLight,OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 84;
    }

    @Override
    public boolean shouldRenderOffScreen(CarWashBrushBE blockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(CarWashBrushBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }
}
