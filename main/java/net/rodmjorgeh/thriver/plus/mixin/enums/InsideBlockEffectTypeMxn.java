package net.rodmjorgeh.thriver.plus.mixin.enums;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.plus.EntityAdd;
import net.rodmjorgeh.thriver.world.area.block.DollsEyesBlock;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

@Mixin(InsideBlockEffectType.class)
public class InsideBlockEffectTypeMxn {

    @Shadow @Final @Mutable
    public static InsideBlockEffectType[] $VALUES;

    @Unique private static final InsideBlockEffectType CLOSE_EYES = addInsideBlockEffectType("CLOSE_EYES", 4,
            (entity -> {
                if (entity instanceof EntityAdd aEntity && entity instanceof LivingEntity livingEntity) {
                    aEntity.setIsInDollsEyes(true);
                    int ticksToBlind = DollsEyesBlock.TICKS_TO_BLIND;

                    if (aEntity.getIsInDollsEyes()) {
                        int i = aEntity.getTicksToBlind();
                        aEntity.setTicksToBlind(Math.min(ticksToBlind, i + 1));

                        if (i >= ticksToBlind) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600));
                            aEntity.setIsInDollsEyes(false);
                        }
                    }
                }
            }));


    @Invoker("<init>")
    public static InsideBlockEffectType insideBlockEffectType(String variableName, int id, Consumer<Entity> effect) {
        throw new AssertionError();
    }

    private static InsideBlockEffectType addInsideBlockEffectType(String variableName, int id,
                                                                  Consumer<Entity> effect) {
        ArrayList<InsideBlockEffectType> arr = new ArrayList<>(Arrays.asList($VALUES));

        InsideBlockEffectType insideBlockEffectType = insideBlockEffectType(variableName, id, effect);
        arr.add(insideBlockEffectType);
        $VALUES = arr.toArray(InsideBlockEffectType[]::new);

        return insideBlockEffectType;
    }
}
