package net.rk.thingamajigs.entity.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CeilingFanBlades extends Model {
    public static final ModelLayerLocation CEILING_FAN_BLADES = new ModelLayerLocation(
            new ResourceLocation("thingamajigs:textures/entity/ceiling_fan_blades.png"),"main"
    );

    public final ModelPart fan;

    public CeilingFanBlades(ModelPart root) {
        super(RenderType::entitySolid);
        fan = root.getChild("fan");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition fan = partdefinition.addOrReplaceChild("fan", CubeListBuilder.create(), PartPose.offset(0.0F, 23.5F, 0.0F));

        PartDefinition fanblade_r1 = fan.addOrReplaceChild("fanblade_r1", CubeListBuilder.create().texOffs(0, 6).addBox(2.0F, -0.5F, -2.0F, 20.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition fanblade_r2 = fan.addOrReplaceChild("fanblade_r2", CubeListBuilder.create().texOffs(0, 43).addBox(-2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition fanblade_r3 = fan.addOrReplaceChild("fanblade_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, -0.5F, -2.0F, 20.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition fanblade_r4 = fan.addOrReplaceChild("fanblade_r4", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -0.5F, -22.0F, 4.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
