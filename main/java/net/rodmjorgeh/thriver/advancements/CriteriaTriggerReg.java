package net.rodmjorgeh.thriver.advancements;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.advancements.criterion.CampfireCookedTrigger;
import net.rodmjorgeh.thriver.advancements.criterion.DestroyedBlockTrigger;
import net.rodmjorgeh.thriver.advancements.criterion.PlayedReedFluteTrigger;

import java.util.function.Supplier;

public class CriteriaTriggerReg {

    public static final DeferredRegister<CriterionTrigger<?>> CRITERIA =
            DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, ThriverMod.MOD_ID);

    public static final Supplier<CampfireCookedTrigger> CAMPFIRE_COOKED = CRITERIA.register("campfire_cooked",
            CampfireCookedTrigger::new);
    public static final Supplier<DestroyedBlockTrigger> DESTROYED_BLOCK = CRITERIA.register("destroyed_block",
            DestroyedBlockTrigger::new);
    public static final Supplier<KilledTrigger> KILL_MOB_WHILE_DOLLS_EYES_BLINDED = CRITERIA.register("kill_mob_while_dolls_eyes_blinded",
            KilledTrigger::new);
    public static final Supplier<PlayedReedFluteTrigger> PLAYED_REED_FLUTE = CRITERIA.register("played_reed_flute",
            PlayedReedFluteTrigger::new);

    public static void register(IEventBus bus) {
        CRITERIA.register(bus);
    }
}
