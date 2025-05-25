package net.rodmjorgeh.thriver.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.entity.EntityReg;

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
