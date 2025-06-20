package net.rodmjorgeh.thriver.world.area.entity.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;

public class DamageTypeReg {

    public static final ResourceKey<DamageType> FALLING_COCONUT = register("falling_coconut");

    public static void bootstrap(BootstrapContext<DamageType> context) {
        register(context, FALLING_COCONUT, "coconut", 0.1F);
    }

    private static ResourceKey<DamageType> register(String name) {
        return ResourceMod.createId(name, Registries.DAMAGE_TYPE);
    }

    private static void register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key, String name, float exhaustion) {
        register(context, key, name, exhaustion, DamageEffects.HURT);
    }
    private static void register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key, String name, float exhaustion, DamageEffects damageEffect) {
        context.register(key, new DamageType(name, exhaustion, damageEffect));
    }
}
