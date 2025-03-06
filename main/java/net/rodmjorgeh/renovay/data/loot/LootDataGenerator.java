package net.rodmjorgeh.renovay.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootDataGenerator extends LootTableProvider {

    public LootDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), getProviderEntries(), registries);
    }

    private static List<SubProviderEntry> getProviderEntries() {
        return List.of(
                new SubProviderEntry(BlockLootDataGenerator::new, LootContextParamSets.BLOCK)
        );
    }
}
