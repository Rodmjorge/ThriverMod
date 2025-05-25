package net.rodmjorgeh.thriver.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.packs.LootData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.entity.EntityReg;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;

import java.util.stream.Stream;

public class EntityLootDataGenerator extends EntityLootSubProvider implements LootDataGeneratorProvider<EntityType<?>> {

    private final LootDataGenerator generator;

    public EntityLootDataGenerator(LootDataGenerator generator, HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
        this.generator = generator;
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        Stream<EntityType<?>> stream = this.registryStream();
        Stream<EntityType<?>> stream1 = BuiltInRegistries.ENTITY_TYPE.stream()
                .filter(x -> x.equals(EntityType.SHEEP));

        return Stream.of(stream, stream1).flatMap(x -> x);
    }

    @Override
    public void generate() {
        this.add(
                EntityType.SHEEP,
                this.generator.getLootTable(this, "sheep", this.registries, 0)
                        .withPool(createSheepDispatchPool(BuiltInLootTables.SHEEP_BY_DYE))
        );
        LootData.WOOL_ITEM_BY_DYE.put(DyeColorThr.BEIGE, BlockReg.BEIGE_WOOL.get());
        LootData.WOOL_ITEM_BY_DYE
                .forEach(
                        (p_367830_, p_367831_) -> this.add(
                                EntityType.SHEEP,
                                BuiltInLootTables.SHEEP_BY_DYE.get(p_367830_),
                                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(p_367831_)))
                        )
                );
    }

    @Override
    public DeferredRegister<EntityType<?>> getRegistry() {
        return EntityReg.ENTITIES;
    }

    @Override
    public String getType() {
        return "entities";
    }
}
