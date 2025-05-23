package net.rodmjorgeh.thriver.world.area.entity.damage;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;

public class DamageSourcesThr {

    public static DamageSources getDamageSources(Entity entity) {
        return entity.damageSources();
    }

    public static DamageSource coconut(Entity entity) {
        return getDamageSources(entity).source(DamageTypeReg.FALLING_COCONUT, entity);
    }
}
