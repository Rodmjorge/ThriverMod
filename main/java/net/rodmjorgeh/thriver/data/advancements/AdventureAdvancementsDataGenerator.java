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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.rodmjorgeh.thriver.ThriverMod;
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
        HolderLookup.RegistryLookup<EntityType<?>> entities = provider.lookupOrThrow(Registries.ENTITY_TYPE);

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

        // Organization
        this.generator.getAdvancement(this, "ol_betsy", provider)
                .parent(this.generator.createParent(this, "shoot_arrow"))
                .save(writer, this.getFolderMinecraft("ol_betsy"));

        this.generator.getAdvancement(this, "voluntary_exile", provider)
                .parent(this.generator.createParent(this, "kill_a_mob"))
                .save(writer, this.getFolderMinecraft("voluntary_exile"));

        this.generator.getAdvancement(this, "totem_of_undying", provider)
                .parent(this.generator.createParent(this, "voluntary_exile"))
                .save(writer, this.getFolderMinecraft("totem_of_undying"));

        this.generator.getAdvancement(this, "brush_armadillo", provider)
                .parent(this.generator.createParent(this, "salvage_sherd"))
                .save(writer, this.getFolderMinecraft("brush_armadillo"));

        this.generator.getAdvancement("husbandry", "repair_wolf_armor", provider)
                .parent(this.generator.createParent(this, "brush_armadillo"))
                .save(writer, this.getFolderMinecraft("husbandry", "repair_wolf_armor"));

        this.generator.getAdvancement("husbandry", "remove_wolf_armor", provider)
                .parent(this.generator.createParent(this, "brush_armadillo"))
                .save(writer, this.getFolderMinecraft("husbandry", "remove_wolf_armor"));

        this.generator.getAdvancement("husbandry", "obtain_sniffer_egg", provider)
                .parent(this.generator.createParent(this, "salvage_sherd"))
                .save(writer, this.getFolderMinecraft("husbandry", "obtain_sniffer_egg"));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
