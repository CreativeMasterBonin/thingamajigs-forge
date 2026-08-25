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
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class CarWashMixedBrushModel extends Model {
    public static final ModelLayerLocation MIXED_BRUSH = new ModelLayerLocation(
            new ResourceLocation("thingamajigs:textures/entity/mixed_car_wash_brush.png"),"main");

    public final ModelPart mixed_brush_blade;

    public CarWashMixedBrushModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.mixed_brush_blade = root.getChild("mixed_brush_blade");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mixed_brush_blade = partdefinition.addOrReplaceChild("mixed_brush_blade", CubeListBuilder.create().texOffs(20, 4).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-1.0F, -5.0F, 1.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -4.0F));

        PartDefinition bottom_r1 = mixed_brush_blade.addOrReplaceChild("bottom_r1", CubeListBuilder.create().texOffs(20, 20).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public void setup(){
        //this.mixed_brush_blade.xRot = ThingamajigsCalcStuffs.degreesToRadians(180.0f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
