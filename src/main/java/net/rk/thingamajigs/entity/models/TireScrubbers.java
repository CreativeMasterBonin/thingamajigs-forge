package net.rk.thingamajigs.entity.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rk.thingamajigs.entity.customblock.TireScrubberBE;

public class TireScrubbers extends Model {
    public static final ModelLayerLocation TIRE_SCRUBBER = new ModelLayerLocation(
            new ResourceLocation("thingamajigs:textures/entity/tire_scrubber.png"),"main");

    public final ModelPart centerturnpoint;
    public final ModelPart tire_scrubber_blade;
    public final ModelPart tire_scrubber_blade2;
    public final ModelPart tire_scrubber_blade3;
    public final ModelPart tire_scrubber_blade4;
    public final ModelPart tire_scrubber_blade5;
    public final ModelPart tire_scrubber_blade6;
    public final ModelPart tire_scrubber_blade7;
    public final ModelPart tire_scrubber_blade8;
    public final ModelPart tire_scrubber_blade9;
    public final ModelPart tire_scrubber_blade10;
    public final ModelPart tire_scrubber_blade11;


    public TireScrubbers(ModelPart root) {
        super(RenderType::entitySolid);
        this.centerturnpoint = root.getChild("centerturnpoint");
        this.tire_scrubber_blade = centerturnpoint.getChild("tire_scrubber_blade");
        this.tire_scrubber_blade2 = centerturnpoint.getChild("tire_scrubber_blade2");
        this.tire_scrubber_blade3 = centerturnpoint.getChild("tire_scrubber_blade3");
        this.tire_scrubber_blade4 = centerturnpoint.getChild("tire_scrubber_blade4");
        this.tire_scrubber_blade5 = centerturnpoint.getChild("tire_scrubber_blade5");
        this.tire_scrubber_blade6 = centerturnpoint.getChild("tire_scrubber_blade6");
        this.tire_scrubber_blade7 = centerturnpoint.getChild("tire_scrubber_blade7");
        this.tire_scrubber_blade8 = centerturnpoint.getChild("tire_scrubber_blade8");
        this.tire_scrubber_blade9 = centerturnpoint.getChild("tire_scrubber_blade9");
        this.tire_scrubber_blade10 = centerturnpoint.getChild("tire_scrubber_blade10");
        this.tire_scrubber_blade11 = centerturnpoint.getChild("tire_scrubber_blade11");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition centerturnpoint = partdefinition.addOrReplaceChild("centerturnpoint", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 24.0F, 8.0F));

        PartDefinition tire_scrubber_blade = centerturnpoint.addOrReplaceChild("tire_scrubber_blade", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = tire_scrubber_blade.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r2 = tire_scrubber_blade.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade2 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = tire_scrubber_blade2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r4 = tire_scrubber_blade2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade3 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = tire_scrubber_blade3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r6 = tire_scrubber_blade3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade4 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = tire_scrubber_blade4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r8 = tire_scrubber_blade4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade5 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r9 = tire_scrubber_blade5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r10 = tire_scrubber_blade5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade6 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r11 = tire_scrubber_blade6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r12 = tire_scrubber_blade6.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade7 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r13 = tire_scrubber_blade7.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r14 = tire_scrubber_blade7.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade8 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade8", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r15 = tire_scrubber_blade8.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r16 = tire_scrubber_blade8.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade9 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade9", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r17 = tire_scrubber_blade9.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r18 = tire_scrubber_blade9.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade10 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade10", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r19 = tire_scrubber_blade10.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r20 = tire_scrubber_blade10.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition tire_scrubber_blade11 = centerturnpoint.addOrReplaceChild("tire_scrubber_blade11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r21 = tire_scrubber_blade11.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(0.5F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r22 = tire_scrubber_blade11.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 16);
    }

    public void setup(TireScrubberBE be){
        centerturnpoint.skipDraw = true;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        /*tire_scrubber_blade.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tire_scrubber_blade11.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);*/
        centerturnpoint.render(poseStack,vertexConsumer,packedLight,packedOverlay);
    }
}
