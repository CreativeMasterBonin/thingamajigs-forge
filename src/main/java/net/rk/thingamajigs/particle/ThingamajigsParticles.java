package net.rk.thingamajigs.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;

import java.util.function.Supplier;

public class ThingamajigsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Thingamajigs.MOD_ID);

    public static final RegistryObject<SimpleParticleType> CHIMNEY_SMOKE =
            PARTICLE_TYPES.register("chimney_smoke", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> ICY_AIR =
            PARTICLE_TYPES.register("icy_air", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> PLASTIC_BALL =
            PARTICLE_TYPES.register("plastic_ball", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> SOAP =
            PARTICLE_TYPES.register("soap", () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
