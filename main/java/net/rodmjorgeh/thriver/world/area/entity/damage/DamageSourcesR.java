package net.rodmjorgeh.thriver.world.area.entity.damage;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;

public class DamageSourcesR {

    public static DamageSources getDamageSources(Entity entity) {
        return entity.damageSources();
    }

    public static DamageSource coconut(Entity entity) {
        return getDamageSources(entity).source(DamageTypeRegistry.FALLING_COCONUT, entity);
    }
}
