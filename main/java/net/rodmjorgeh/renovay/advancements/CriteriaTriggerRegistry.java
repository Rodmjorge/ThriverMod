package net.rodmjorgeh.renovay.advancements;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.advancements.criterion.PlayedReedFluteTrigger;

import java.util.function.Supplier;

public class CriteriaTriggerRegistry {

    public static final DeferredRegister<CriterionTrigger<?>> CRITERIA =
            DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, RenovayMod.MOD_ID);

    public static final Supplier<PlayedReedFluteTrigger> PLAYED_REED_FLUTE = CRITERIA.register("played_reed_flute",
            PlayedReedFluteTrigger::new);

    public static void register(IEventBus bus) {
        CRITERIA.register(bus);
    }
}
