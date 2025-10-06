package net.rk.thingamajigs.fluid.types;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class SludgeFluidType extends FluidType {
    private final float RED = 130f / 255f;
    private final float GREEN = 85f / 255f;
    private final float BLUE = 15f / 255f;
    private final Vector3f fogColor = new Vector3f(RED,GREEN,BLUE);

    // a sick touch (the basis for the block fluid)
    public SludgeFluidType(Properties properties) {
        super(FluidType.Properties.create()
                .descriptionId("block.thingamajigs.sludge")
                .fallDistanceModifier(0F)
                .canExtinguish(true)
                .supportsBoating(true)
                .canHydrate(false)
                .viscosity(1800)
                .temperature(120)
                .canConvertToSource(false)
                .density(200)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.FLUID_VAPORIZE, ThingamajigsSoundEvents.POOP_BREAK.get())
                .rarity(Rarity.COMMON)
        );
    }

    // init for fluid textures
    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL_TEXTURE = new ResourceLocation("thingamajigs:block/sludge_still");
            private static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("thingamajigs:block/sludge_flow");

            private static final ResourceLocation UNDERWATER_TEXTURE =
                    new ResourceLocation("thingamajigs:textures/misc/sludge_underwater.png");
            private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation("thingamajigs:textures/misc/overlay.png");

            @Override
            public ResourceLocation getStillTexture() {
                return STILL_TEXTURE;
            }
            @Override
            public ResourceLocation getFlowingTexture() {
                return FLOWING_TEXTURE;
            }

            @Override
            public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return UNDERWATER_TEXTURE;
            }
            @Override
            public int getTintColor() {
                return 0xFFFFFFFF;
            }

            @Nullable
            @Override
            public ResourceLocation getOverlayTexture() {
                return OVERLAY_TEXTURE;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return fogColor;
            }
            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(1F);
                RenderSystem.setShaderFogEnd(10F); // fog starts at this distance
            }
        });
    }
}
