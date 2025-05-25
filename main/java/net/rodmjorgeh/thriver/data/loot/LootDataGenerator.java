package net.rodmjorgeh.thriver.data.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.rodmjorgeh.thriver.data.Datagen;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class LootDataGenerator extends LootTableProvider implements Datagen<LootTable, LootTableProvider.SubProviderEntry> {

    private PackOutput output;
    private static final String LOOT_TABLE_COPY_FOLDER = "loot_table_copy";

    public LootDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, List<SubProviderEntry> list) {
        super(output, Set.of(), list, registries);
    }

    public static DataProvider createProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return Datagen.create(output, registries, LootDataGenerator::new);
    }

    @Override
    public List<SubProviderEntry> getProviderEntries() {
        return List.of(
                new SubProviderEntry(this::blockLootSub, LootContextParamSets.BLOCK),
                new SubProviderEntry(this::entityLootSub, LootContextParamSets.ENTITY)
        );
    }

    private LootTableSubProvider blockLootSub(HolderLookup.Provider provider) {
        return new BlockLootDataGenerator(this, provider);
    }
    private LootTableSubProvider entityLootSub(HolderLookup.Provider provider) {
        return new EntityLootDataGenerator(this, provider);
    }

    @Override
    public String getCopyFolder() {
        return LOOT_TABLE_COPY_FOLDER;
    }

    @Override
    public Codec<LootTable> getCodec() {
        return LootTable.DIRECT_CODEC;
    }

    @Override
    public PackOutput getOutput() {
        return this.output;
    }
    @Override
    public void setOutput(PackOutput output) {
        this.output = output;
    }
}
