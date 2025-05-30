package net.rodmjorgeh.thriver.advancements.criterion;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;

import java.util.Optional;

public class KilledTriggerThr {
    public KilledTriggerThr() { }

    public static class TriggerInstance {
        public TriggerInstance() {}

        public static Criterion<KilledTrigger.TriggerInstance> playerKilledEntityWhileDollsEyesBlinded(
                EntityPredicate.Builder entityPredicate) {

            return CriteriaTriggerReg.KILL_MOB_WHILE_DOLLS_EYES_BLINDED.get().createCriterion(
                    new KilledTrigger.TriggerInstance(
                            Optional.empty(),
                            Optional.of(EntityPredicate.wrap(entityPredicate)),
                            Optional.empty()
                    )
            );
        }
    }
}
