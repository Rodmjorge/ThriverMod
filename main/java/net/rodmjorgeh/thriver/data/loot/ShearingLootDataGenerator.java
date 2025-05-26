package net.rodmjorgeh.thriver.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;

import javax.swing.text.html.parser.Entity;
import java.util.function.BiConsumer;

public class ShearingLootDataGenerator implements LootTableSubProvider, LootDataGeneratorProvider<LootTable> {

    private final LootDataGenerator generator;
    private final HolderLookup.Provider registries;

    public ShearingLootDataGenerator(LootDataGenerator generator, HolderLookup.Provider registries) {
        this.generator = generator;
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(
                BuiltInLootTables.SHEAR_SHEEP,
                LootTable.lootTable().withPool(EntityLootDataGenerator.createSheepDispatchPool(BuiltInLootTables.SHEAR_SHEEP_BY_DYE))
        );
        this.addShearWoolItem(DyeColorThr.BEIGE, BlockReg.BEIGE_WOOL.get(), consumer);
    }

    private void addShearWoolItem(DyeColor color, Block wool, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        EntityLootDataGenerator.addWoolItemLoot(color, wool, BuiltInLootTables.SHEAR_SHEEP_BY_DYE,
                (key, item) -> consumer.accept(key,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F)).add(LootItem.lootTableItem(item)))));
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
