package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.world.item.Item;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.advancements.criterion.PlayedReedFluteTrigger;
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
    }

    @Override
    public String getType() {
        return this.type;
    }
}
