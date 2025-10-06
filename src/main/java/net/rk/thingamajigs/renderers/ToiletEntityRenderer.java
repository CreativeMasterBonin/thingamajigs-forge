package net.rk.thingamajigs.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.ToiletEntity;

@OnlyIn(Dist.CLIENT)
public class ToiletEntityRenderer extends EntityRenderer<ToiletEntity> {
    public ToiletEntityRenderer(EntityRendererProvider.Context c) {
        super(c);
    }

    @Override
    public ResourceLocation getTextureLocation(ToiletEntity ce) {
        return new ResourceLocation(Thingamajigs.MOD_ID,"block/placeholder.png");
    }

    @Override
    protected void renderNameTag(ToiletEntity te, Component c, PoseStack ps, MultiBufferSource mbs, int p_114502_) {

    }

    @Override
    public boolean shouldRender(ToiletEntity te, Frustum f, double p_114493_, double p_114494_, double p_114495_) {
        return false; //never!
    }
}
