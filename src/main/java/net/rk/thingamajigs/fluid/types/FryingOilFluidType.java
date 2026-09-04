package net.rk.thingamajigs.fluid.types;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.rk.thingamajigs.misc.ThingamajigsColors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

@SuppressWarnings("deprecated,removal")
public class FryingOilFluidType extends FluidType {
    private final Vector3f fogColor = new Vector3f(213f/255f,198f/255f,92f/255f);

    public FryingOilFluidType(Properties properties) {
        super(FluidType.Properties.create()
                .descriptionId("block.thingamajigs.frying_oil")
                .fallDistanceModifier(0f)
                .canExtinguish(false)
                .supportsBoating(true)
                .canHydrate(false)
                .pathType(BlockPathTypes.DANGER_FIRE)
                .adjacentPathType(BlockPathTypes.BLOCKED)
                .viscosity(750)
                .temperature(700)
                .canConvertToSource(false)
                .density(100)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.GENERIC_SPLASH)
                .rarity(Rarity.COMMON)
        );
    }

    @Override
    public boolean canSwim(Entity entity) {
        return true;
    }

    @Override
    public double motionScale(Entity entity) {
        return 0.005D; // it is slow to walk through burning oil
    }

    @Override
    public boolean canPushEntity(Entity entity) {
        return true;
    }

    @Override
    public boolean canRideVehicleUnder(Entity vehicle, Entity rider) {
        return true;
    }

    @Override
    public void setItemMovement(ItemEntity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        entity.setDeltaMovement(vec3.x * (double)0.78F, vec3.y + (double)(vec3.y < (double)0.05F ? 5.0E-4F : 0.0F), vec3.z * (double)0.78F);
    }

    // init for fluid textures
    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL_TEXTURE = new ResourceLocation("thingamajigs:block/animated/frying_oil_still");
            private static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("thingamajigs:block/animated/frying_oil_flowing");
            private static final ResourceLocation UNDERWATER_TEXTURE = new ResourceLocation("thingamajigs:textures/misc/frying_oil_inside.png");
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
            public int getTintColor(FluidStack stack) {
                return ThingamajigsColors.getWhite();
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(1F);
                RenderSystem.setShaderFogEnd(15F); // fog starts at this distance
            }
        });
    }
}
