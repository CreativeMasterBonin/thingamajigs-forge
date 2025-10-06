package net.rk.thingamajigs.particle.types;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ChimneySmoke extends TextureSheetParticle {

    protected ChimneySmoke(ClientLevel cl, double x, double y, double z, SpriteSet ss, double xs, double ys, double zs) {
        super(cl, x, y, z, xs, ys, zs);
        this.scale(2.0F);
        this.friction = 1.0f;
        this.xd = xs;
        this.yd = ys + (double)(this.random.nextFloat() / 500.0f);
        this.zd = zs;

        this.lifetime = 64;
        this.setSpriteFromAge(ss);
        this.gravity = 3.0E-6F;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }

        }
        else {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType pt, ClientLevel lvl, double x, double y, double z, double xs, double ys, double zs) {
            ChimneySmoke cs = new ChimneySmoke(lvl, x, y, z, this.spriteSet, xs, ys, zs);
            cs.alpha = 0.75F;
            return cs;
        }
    }
}
