package net.rodmjorgeh.thriver.world.area.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class PalmProvider implements ParticleProvider<SimpleParticleType> {
    private SpriteSet sprites;

    public PalmProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType particle, ClientLevel level,
                                             double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new FallingLeavesParticle(level, x, y, z, this.sprites,
                0.07F, 10.0F, true, false, 3.5F, 0.021F);
    }
}
