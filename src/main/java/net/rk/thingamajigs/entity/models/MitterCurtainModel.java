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

public class MitterCurtainModel extends Model {
    public static final ModelLayerLocation MITTER_CURTAIN = new ModelLayerLocation(
            new ResourceLocation("thingamajigs:textures/entity/mitter_curtain.png"),"main"
    );
    public final ModelPart singular_curtain;

    public MitterCurtainModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.singular_curtain = root.getChild("singular_curtain");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition singular_curtain = partdefinition.addOrReplaceChild("singular_curtain", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -48.0F, -0.5F, 5.0F, 48.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float v2, float v3) {
        singular_curtain.render(poseStack,vertexConsumer,i,i1);
    }
}
