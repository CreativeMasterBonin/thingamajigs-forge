package net.rk.thingamajigs.entity.layers;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rk.thingamajigs.Thingamajigs;

@OnlyIn(Dist.CLIENT)
public class ThingamajigsModelLayers {
    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation INFICART_LAYER = new ModelLayerLocation(
            new ResourceLocation(Thingamajigs.MOD_ID, "inficart_layer"), "main");

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation BRIT_RAILROAD_CROSSING_LAYER = new ModelLayerLocation(
            new ResourceLocation(Thingamajigs.MOD_ID, "brit_railway_crossing"), "main");
}
