package net.rodmjorgeh.thriver.world.area.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;

import java.util.function.Supplier;

public class ParticleReg {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, ThriverMod.MOD_ID);

    public static final Supplier<SimpleParticleType> PALM_LEAVES = register("palm_leaves", false);


    public static void register(IEventBus bus) { PARTICLES.register(bus); }

    private static Supplier<SimpleParticleType> register(String name, boolean overrideLimiter) {
        return PARTICLES.register(name, () -> new SimpleParticleType(overrideLimiter));
    }

    private static <T extends ParticleOptions> void register(ParticleType<T> particle,
                                                             ParticleEngine.SpriteParticleRegistration<T> factory) {
        Minecraft.getInstance().particleEngine.register(particle, factory);
    }

    public static void registerAll() {
        register(PALM_LEAVES.get(), PalmProvider::new);
    }
}
