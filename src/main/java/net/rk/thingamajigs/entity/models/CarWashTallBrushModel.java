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
import net.rk.thingamajigs.entity.customblock.CarWashBrushBE;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class CarWashTallBrushModel extends Model {
    public static final ModelLayerLocation BLUE_BRUSH =
            new ModelLayerLocation(new ResourceLocation("thingamajigs:textures/entity/blue_car_wash_brush.png"), "main");
    public static final ModelLayerLocation RED_BRUSH =
            new ModelLayerLocation(new ResourceLocation("thingamajigs:textures/entity/red_car_wash_brush.png"), "main");
    public static final ModelLayerLocation YELLOW_BRUSH =
            new ModelLayerLocation(new ResourceLocation("thingamajigs:textures/entity/yellow_car_wash_brush.png"), "main");
    public final ModelPart base;
    public final ModelPart brush_piece;
    public final ModelPart med_brush_piece;
    public final ModelPart small_brush_piece;

    public CarWashTallBrushModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.base = root.getChild("base");
        this.brush_piece = root.getChild("brush_piece");
        this.med_brush_piece = root.getChild("med_brush_piece");
        this.small_brush_piece = root.getChild("small_brush_piece");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(4, 10).addBox(-0.5F, -29.0F, -0.5F, 1.0F, 28.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 0).addBox(-1.0F, -30.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 24.0F, 8.0F));

        PartDefinition brush_piece = partdefinition.addOrReplaceChild("brush_piece", CubeListBuilder.create().texOffs(17, 0).addBox(-3.6667F, -0.3333F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(-3.6667F, 0.6667F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 0).addBox(-4.6667F, 1.1667F, -1.0F, 1.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.3333F, -4.6667F, 8.0F));

        PartDefinition med_brush_piece = partdefinition.addOrReplaceChild("med_brush_piece", CubeListBuilder.create().texOffs(17, 27).addBox(-3.7333F, -0.3333F, -0.9967F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 27).addBox(-3.7333F, 0.6667F, -0.9967F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 27).addBox(-4.5333F, 1.1667F, -1.0067F, 1.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.2667F, 6.3333F, 7.9967F));

        PartDefinition small_brush_piece = partdefinition.addOrReplaceChild("small_brush_piece", CubeListBuilder.create().texOffs(17, 42).addBox(-3.7333F, -0.5F, -0.9667F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 42).addBox(-3.7333F, 0.5F, -0.9667F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 42).addBox(-4.5333F, 1.0F, -1.0667F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.2667F, 14.5F, 7.9667F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setup(CarWashBrushBE brush){
        this.brush_piece.y = 1.0f;
        this.small_brush_piece.y = 1.0f;
        this.med_brush_piece.y = 1.0f;
        this.brush_piece.x = 1.0f + brush.initialxoffset;
        this.brush_piece.z = brush.xoffset + brush.initialzoffset;
        this.med_brush_piece.x = 1.0f + brush.initialxoffset;
        this.med_brush_piece.z = brush.xoffset + brush.initialzoffset;
        this.small_brush_piece.x = 1.0f + brush.initialxoffset;
        this.small_brush_piece.z = brush.xoffset + brush.initialzoffset;
        this.brush_piece.zRot = ThingamajigsCalcStuffs.degreesToRadians(0.0f);
        this.med_brush_piece.zRot = ThingamajigsCalcStuffs.degreesToRadians(0.0f);
        this.small_brush_piece.zRot = ThingamajigsCalcStuffs.degreesToRadians(0.0f);
        this.brush_piece.yRot = ThingamajigsCalcStuffs.degreesToRadians(brush.facingDirectionAngle);
        this.med_brush_piece.yRot = ThingamajigsCalcStuffs.degreesToRadians(brush.facingDirectionAngle);
        this.small_brush_piece.yRot = ThingamajigsCalcStuffs.degreesToRadians(brush.facingDirectionAngle);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
