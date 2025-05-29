package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.DataComponentPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.rodmjorgeh.thriver.advancements.criterion.KilledTriggerThr;
import net.rodmjorgeh.thriver.advancements.criterion.PlayedReedFluteTrigger;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.item.ItemReg;

import java.util.function.Consumer;

public class AdventureAdvancementsDataGenerator implements AdvancementSubProvider, AdvancementDataGeneratorProvider {

    private final AdvancementDataGenerator generator;
    private final String type;

    public AdventureAdvancementsDataGenerator(AdvancementDataGenerator generator, String type) {
        this.generator = generator;
        this.type = type;
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> writer) {
        HolderGetter<EntityType<?>> entities = provider.lookupOrThrow(Registries.ENTITY_TYPE);

        Advancement.Builder.advancement()
                .parent(this.generator.createParent(this, "kill_a_mob"))
                .display(
                        ItemReg.REED_FLUTE.get(),
                        this.createTitle("play_reed_flute"),
                        this.createDescription("play_reed_flute"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("play_reed_flute",
                        PlayedReedFluteTrigger.TriggerInstance.playedReedFlute(this.items(provider), ItemReg.REED_FLUTE.get(), MinMaxBounds.Ints.atLeast(6)))
                .save(writer, this.getFolder("play_reed_flute"));

        Advancement.Builder.advancement()
                .parent(this.generator.createParent(this, "avoid_vibration"))
                .display(
                        BlockReg.DOLLS_EYES.get(),
                        this.createTitle("kill_warden_while_blind"),
                        this.createDescription("kill_warden_while_blind"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("kill_warden_while_blind",
                        KilledTriggerThr.TriggerInstance.playerKilledEntityWhileDollsEyesBlinded(
                                EntityPredicate.Builder.entity().of(entities, EntityType.WARDEN)
                        ))
                .save(writer, this.getFolder("kill_warden_while_blind"));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
