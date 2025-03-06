package net.rodmjorgeh.renovay.data.advancements;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AdvancementDataGenerator extends AdvancementProvider {

    public AdvancementDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, getProviderEntries());
    }

    private static List<AdvancementSubProvider> getProviderEntries() {
        return List.of(
            new AdventureAdvancementsDataGenerator("adventure")
        );
    }

    protected static AdvancementHolder createParent(String advancementLocation) {
        return AdvancementSubProvider.createPlaceholder("minecraft:" + advancementLocation);
    }
}
