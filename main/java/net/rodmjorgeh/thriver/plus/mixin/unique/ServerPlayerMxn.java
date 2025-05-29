package net.rodmjorgeh.thriver.plus.mixin.unique;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;
import net.rodmjorgeh.thriver.plus.EntityAdd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(ServerPlayer.class)
public class ServerPlayerMxn {

    /**
     * Used for the "An Eye for an Eye" advancement, to know if the player got the Blindness effect from the Doll's Eyes
     * and not from another source, which doesn't count for the advancement.
     */
    @Unique private boolean blindedFromDollsEyes;
    @Unique private static final String COMPOUND_BLINDED_KEY = "blinded_from_dolls_eyes";

    @Inject(method = "awardKillScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/KilledTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)V"))
    public void awardKillScore(Entity entity, DamageSource damageSource, CallbackInfo ci) {
        ServerPlayer serverPlayer = (ServerPlayer)(Object)this;

        if (serverPlayer.hasEffect(MobEffects.BLINDNESS) && this.blindedFromDollsEyes) {
            CriteriaTriggerReg.KILL_MOB_WHILE_DOLLS_EYES_BLINDED.get().trigger(serverPlayer, entity, damageSource);
        }
    }

    /**
     * Makes sure the effect you got was from the Doll's Eyes.
     */
    @Inject(method = "onEffectAdded", at = @At("TAIL"))
    protected void onEffectAdded(MobEffectInstance effect, Entity entity, CallbackInfo ci) {
        ServerPlayer serverPlayer = (ServerPlayer)(Object)this;
        if (serverPlayer instanceof EntityAdd aEntity) {
            if (effect.is(MobEffects.BLINDNESS) && aEntity.getIsInDollsEyes()) {
                this.blindedFromDollsEyes = true;
            }
        }
    }

    @Inject(method = "onEffectsRemoved", at = @At("TAIL"))
    protected void onEffectsRemoved(Collection<MobEffectInstance> effects, CallbackInfo ci) {
        for (MobEffectInstance effect : effects) {
            this.blindedFromDollsEyes = !effect.is(MobEffects.BLINDNESS) && this.blindedFromDollsEyes;
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.blindedFromDollsEyes = compound.getBooleanOr(COMPOUND_BLINDED_KEY, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean(COMPOUND_BLINDED_KEY, this.blindedFromDollsEyes);
    }
}
