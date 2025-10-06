package net.rk.thingamajigs.particle;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.particle.types.BallPitParticleType;
import net.rk.thingamajigs.particle.types.ChimneySmoke;
import net.rk.thingamajigs.particle.types.IcyAirParticleType;
import net.rk.thingamajigs.particle.types.SoapParticleType;

@Mod.EventBusSubscriber(modid = Thingamajigs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryRegistry {
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ThingamajigsParticles.CHIMNEY_SMOKE.get(), ChimneySmoke.Provider::new);
        event.registerSpriteSet(ThingamajigsParticles.ICY_AIR.get(), IcyAirParticleType.IcyAirParticleFactory::new);
        event.registerSpriteSet(ThingamajigsParticles.PLASTIC_BALL.get(), BallPitParticleType.BallPitParticleFactory::new);
        event.registerSpriteSet(ThingamajigsParticles.SOAP.get(), SoapParticleType.SoapParticleFactory::new);
    }
}
