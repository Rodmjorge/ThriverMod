package net.rodmjorgeh.thriver.data.advancements;

import com.mojang.serialization.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.Criterion;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.DataFixTypes;
import net.neoforged.neoforge.common.conditions.WithConditions;
import net.rodmjorgeh.thriver.data.Datagen;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AdvancementDataGenerator extends AdvancementProvider implements Datagen<Advancement, AdvancementSubProvider> {

    private PackOutput output;
    private static final String ADVANCEMENT_COPY_FOLDER = "advancement_copy";

    public AdvancementDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, List<AdvancementSubProvider> list) {
        super(output, registries, list);
    }

    public static DataProvider createProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return Datagen.create(output, registries, AdvancementDataGenerator::new);
    }

    @Override
    public List<AdvancementSubProvider> getProviderEntries() {
        return List.of(
                new AdventureAdvancementsDataGenerator(this, "adventure"),
                new HusbandryAdvancementsDataGenerator(this, "husbandry"),
                new MiningAdvancementsDataGenerator(this, "mining")
        );
    }

    public Advancement.Builder getAdvancement(AdvancementDataGeneratorProvider provider, String name,
                                                     HolderLookup.Provider lookupProvider) {
        return getAdvancement(provider.getType(), name, lookupProvider);
    }
    public Advancement.Builder getAdvancement(String type, String name,
                                              HolderLookup.Provider lookupProvider) {
        return getAdvancement(type, name, lookupProvider, true);
    }
    public Advancement.Builder getAdvancement(String type, String name,
                                                     HolderLookup.Provider lookupProvider, boolean addCriteria) {
        Advancement advancement = this.getInfoFromFile(type, name, lookupProvider);
        Advancement.Builder builder = Advancement.Builder.advancement()
                                        .display(advancement.display().get())
                                        .rewards(advancement.rewards());

        Optional<ResourceLocation> parent = advancement.parent();
        if (parent.isPresent()) {
            builder.parent(parent.get());
        }

        if (addCriteria) {
            Map<String, Criterion<?>> criteria = advancement.criteria();

            for (String key : criteria.keySet()) {
                Criterion<?> criterion = criteria.get(key);
                builder.addCriterion(key, criterion);
            }
        }
        if (advancement.sendsTelemetryEvent()) {
            builder.sendsTelemetryEvent();
        }

        return builder;
    }

    @Override
    public String getCopyFolder() {
        return ADVANCEMENT_COPY_FOLDER;
    }

    @Override
    public Codec<Advancement> getCodec() {
        return Advancement.CODEC;
    }

    @Override
    public PackOutput getOutput() {
        return this.output;
    }

    @Override
    public void setOutput(PackOutput output) {
        this.output = output;
    }

    public AdvancementHolder createParent(AdvancementDataGeneratorProvider provider, String advancementLocation) {
        return createParent(provider, advancementLocation, "minecraft");
    }
    public AdvancementHolder createParent(AdvancementDataGeneratorProvider provider, String advancementLocation, String namespace) {
        String type = provider.getType();
        return AdvancementSubProvider.createPlaceholder(namespace + ":" + type + "/" + advancementLocation);
    }
}
