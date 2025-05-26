package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.rodmjorgeh.thriver.plus.EntityAdd;
import net.rodmjorgeh.thriver.world.area.block.DollsEyesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMxn {

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;removeFrost()V"))
    public void aiStep(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        ProfilerFiller profiler = Profiler.get();

        // I have no fucking clue as to what this does.
        profiler.pop();
        profiler.push("blinding");

        if (!entity.level().isClientSide && !entity.isDeadOrDying() && entity instanceof EntityAdd aEntity) {
            int i = aEntity.getTicksToBlind();

            // once you get the blindness from the plant, the closing overlay will disappear faster than if you move away from the plant
            int j = entity.hasEffect(MobEffects.BLINDNESS) ? 15 : 3;

            int ticksToBlind = DollsEyesBlock.TICKS_TO_BLIND;
            boolean isInDollsEyes = aEntity.getIsInDollsEyes();

            aEntity.setTicksToBlind(isInDollsEyes ? Math.min(ticksToBlind, i + 1) : Math.max(0, i - j));
            if (i >= ticksToBlind) {
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600));
                aEntity.setIsInDollsEyes(false);
            }
        }
    }
}
