package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.AnimatedDeer;
import net.rk.thingamajigs.entity.customblock.AnimatedDeerBE;
import net.rk.thingamajigs.entity.models.AnimatedDeerModel;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import net.rk.thingamajigs.xtrablock.DJLaserLight;
import org.joml.Quaternionf;

public class AnimatedDeerBERenderer implements BlockEntityRenderer<AnimatedDeerBE> {
    public AnimatedDeerModel model;
    public AnimatedDeerModel modelB;

    public AnimatedDeerBERenderer(BlockEntityRendererProvider.Context ctx){
        this.model = new AnimatedDeerModel(ctx.bakeLayer(AnimatedDeerModel.LAYER_LOCATION));
        this.modelB = new AnimatedDeerModel(ctx.bakeLayer(AnimatedDeerModel.LAYER_LOCATION));
    }

    @Override
    public void render(AnimatedDeerBE blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.scale(1f,1f,1f);
        poseStack.translate(0.5,0.0,0.5);
        VertexConsumer vc = bufferSource.getBuffer(RenderType.entityCutout(
                new ResourceLocation("thingamajigs:textures/entity/animated_deer.png")));
        if(!blockEntity.getBlockState().getValue(AnimatedDeer.ENABLED)){
            vc = bufferSource.getBuffer(RenderType.entityCutout(
                    new ResourceLocation("thingamajigs:textures/entity/animated_deer_off.png")));
        }

        if(blockEntity.custom){
            poseStack.mulPose(new Quaternionf().rotateY(blockEntity.yAngle));
        }
        else{
            if(blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
            }
            else if (blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
            }
            else if (blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
            }
            else if (blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
            }
        }

        // 1.8.9 - calculate angles using better methods
        double minMaxSineSideToSide = -13.0 + Mth.sin(Util.getMillis() / Mth.clamp(blockEntity.getPartialTickDivider(),1.0f,Float.MAX_VALUE)) * 17.0; // an angle from min to max swinging animation
        double minMaxSineUpDown = -12.0 + Mth.sin(Util.getMillis() / Mth.clamp(blockEntity.getPartialTickDivider(),1.0f,Float.MAX_VALUE)) * 18.0; // an angle from min to max swinging animation
        float gearRotation = (Util.getMillis() / Mth.clamp(blockEntity.getPartialTickDivider(),1.0f,Float.MAX_VALUE)); // the higher the right number, the slower it turns

        float angleHorizontal = (ThingamajigsCalcStuffs.degreesToRadians((float)minMaxSineSideToSide + blockEntity.offsetAngle) + 0.35f) * -1.0f;
        float angleVertical = (ThingamajigsCalcStuffs.degreesToRadians((float)minMaxSineUpDown + blockEntity.offsetAngle) + 0.35f) * -1.0f;
        if(!blockEntity.getBlockState().getValue(AnimatedDeer.ENABLED)){
            angleHorizontal = 0.0f;
            angleVertical = 0.0f;
        }

        if(blockEntity.alternateMovement){
            this.modelB.neck.xRot = 0.5f;
            this.modelB.head.xRot = -0.275f;
            this.modelB.neck.yRot = angleHorizontal;
            this.modelB.motor.zRot = 1.57f;

            this.modelB.antlers.visible = blockEntity.showAntlers;
            this.modelB.main.xRot = 0.0f;
            this.modelB.gear.xRot = angleHorizontal == 0.0f ? 0.0f : gearRotation;
            this.modelB.rod.xRot = angleHorizontal / 4.3571f * -1.0f;
            this.modelB.main.render(poseStack,vc,packedLight,packedOverlay);
        }
        else{
            this.model.antlers.visible = blockEntity.showAntlers;
            this.model.setupAnim(blockEntity,angleVertical);
            this.model.main.xRot = 0.0f;
            this.model.gear.xRot = angleVertical == 0.0f ? 0.0f : gearRotation;
            this.model.rod.xRot = angleVertical / 4.3571f * -1.0f;
            this.model.main.render(poseStack,vc,packedLight,packedOverlay);
        }
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(AnimatedDeerBE deer) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 70;
    }

    @Override
    public boolean shouldRender(AnimatedDeerBE blockEntity, Vec3 cameraPos) {
        return Vec3.atCenterOf(blockEntity.getBlockPos()).multiply(2, 2, 2)
                .closerThan(cameraPos.multiply(2, 2, 2), (double)this.getViewDistance());
    }
}
