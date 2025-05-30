package net.rodmjorgeh.thriver.data.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.neoforged.neoforge.common.conditions.WithConditions;
import net.rodmjorgeh.thriver.data.Datagen;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

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
                new SubProviderEntry(this::entityLootSub, LootContextParamSets.ENTITY),
                new SubProviderEntry(this::shearingLootSub, LootContextParamSets.SHEARING)
        );
    }

    /**
     * A bit of a retarded approach, but I couldn't brainstorm a better way of doing this so this bullshittery will
     * suffice, I suppose.
     */
    private LootTableSubProvider blockLootSub(HolderLookup.Provider provider) {
        return new BlockLootDataGenerator(this, provider);
    }
    private LootTableSubProvider entityLootSub(HolderLookup.Provider provider) {
        return new EntityLootDataGenerator(this, provider);
    }
    private LootTableSubProvider shearingLootSub(HolderLookup.Provider provider) {
        return new ShearingLootDataGenerator(this, provider);
    }

    /**
     * @param indexesToAdd What pools to add via its indexes. By having the parameter empty, it'll just automatically
     *                     add every index to it, meaning it adds all pools that the loot table originally had.
     */
    public <T> LootTable.Builder getLootTable(LootDataGeneratorProvider<T> provider, String name, HolderLookup.Provider lookupProvider, int... indexesToAdd) {
        LootTable loot = this.getInfoFromFile(provider.getType(), name, lookupProvider);
        List<LootPool> pools = loot.pools;

        LootTable.Builder builder = LootTable.lootTable();
        if (indexesToAdd.length == 0) {
            int length = pools.size();
            indexesToAdd = IntStream.range(0, length).toArray();
        }
        for (int i : indexesToAdd) {
            LootPool pool = pools.get(i);
            builder.pools.add(pool);
        }

        return builder;
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
