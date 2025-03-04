package net.rodmjorgeh.renovay.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.data.loot.modifiers.AddLootPoolModifier;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class GlobalLootDataGenerator extends GlobalLootModifierProvider {

    public static final String POOL_NAME = "new_custom_pool";

    public GlobalLootDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, RenovayMod.MOD_ID);
    }

    /**
     * Everytime a new pool gets added, it needs to have an {@link EmptyLootItem}. This is so the items used on these
     * new pools don't always get generated on the loot table.
     */
    @Override
    protected void start() {
        this.add("chest_desert_pyramid",
                new AddLootPoolModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build()
                }, new LootPool[] {
                    LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2.0F, 3.0F))
                            .add(EmptyLootItem.emptyItem().setWeight(3))
                            .add(LootItem.lootTableItem(BlockRegistry.REEDS.get()).setWeight(3)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                            .name(POOL_NAME)
                            .build()
                }));
    }
}
