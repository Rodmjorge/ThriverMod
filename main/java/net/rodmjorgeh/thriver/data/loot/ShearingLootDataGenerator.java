package net.rodmjorgeh.thriver.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiConsumer;

public class ShearingLootDataGenerator implements LootTableSubProvider, LootDataGeneratorProvider<LootTable> {

    private final LootDataGenerator generator;
    private final HolderLookup.Provider registries;

    public ShearingLootDataGenerator(LootDataGenerator generator, HolderLookup.Provider registries) {
        this.generator = generator;
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {

    }

    @Override
    public DeferredRegister<LootTable> getRegistry() {
        return null;
    }

    @Override
    public String getType() {
        return "shearing";
    }
}
