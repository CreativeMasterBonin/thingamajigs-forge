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
import net.rk.thingamajigs.entity.ChairEntity;

@OnlyIn(Dist.CLIENT)
public class ChairEntityRenderer extends EntityRenderer<ChairEntity> {
    public ChairEntityRenderer(EntityRendererProvider.Context c) {
        super(c);
    }

    @Override
    public ResourceLocation getTextureLocation(ChairEntity ce) {
        return new ResourceLocation(Thingamajigs.MOD_ID,"block/placeholder.png");
    }

    @Override
    protected void renderNameTag(ChairEntity ce, Component c, PoseStack ps, MultiBufferSource mbs, int p_114502_) {

    }

    @Override
    public boolean shouldRender(ChairEntity ce, Frustum f, double p_114493_, double p_114494_, double p_114495_) {
        if (ce.isInvisible()){
            return false;
        }
        else if(ce.isNoGravity()){
            return false;
        }
        return false;
    }
}
