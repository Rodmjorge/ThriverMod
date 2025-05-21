package net.rodmjorgeh.thriver.data.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.area.block.CoconutBlock;
import net.rodmjorgeh.thriver.world.item.ItemRegistry;

import java.util.Set;

public class BlockLootDataGenerator extends BlockLootSubProvider {

    public BlockLootDataGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream()
                .map(x -> (Block)x.value())
                .toList();
    }

    @Override
    protected void generate() {
        this.add(BlockRegistry.BEIGE_BANNER.get(), x -> this.createBannerDrop(x));
        this.add(BlockRegistry.BEIGE_BED.get(), x -> this.createSinglePropConditionTable(x, BedBlock.PART, BedPart.HEAD));
        this.add(BlockRegistry.BEIGE_CANDLE.get(), x -> this.createCandleDrops(x));
        this.add(BlockRegistry.BEIGE_CANDLE_CAKE.get(), x -> createCandleCakeDrops(BlockRegistry.BEIGE_CANDLE.get()));
        this.dropSelf(BlockRegistry.BEIGE_CARPET.get());
        this.dropSelf(BlockRegistry.BEIGE_CONCRETE.get());
        this.dropSelf(BlockRegistry.BEIGE_CONCRETE_POWDER.get());
        this.dropSelf(BlockRegistry.BEIGE_GLAZED_TERRACOTTA.get());
        this.add(BlockRegistry.BEIGE_SHULKER_BOX.get(), x -> this.createShulkerBoxDrop(x));
        this.dropWhenSilkTouch(BlockRegistry.BEIGE_STAINED_GLASS.get());
        this.dropWhenSilkTouch(BlockRegistry.BEIGE_STAINED_GLASS_PANE.get());
        this.dropSelf(BlockRegistry.BEIGE_TERRACOTTA.get());
        this.dropSelf(BlockRegistry.BEIGE_WOOL.get());
        this.add(BlockRegistry.COCONUT.get(),
                x -> LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                this.applyExplosionDecay(x,
                                                        LootItem.lootTableItem(ItemRegistry.COCONUT.get())
                                                                .apply(
                                                                        SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))
                                                                                .when(
                                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(x)
                                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoconutBlock.AGE, CoconutBlock.MAX_AGE))
                                                                                )
                                                                )
                                                )
                                        )
                        )
        );
        this.dropSelf(BlockRegistry.COIR_MAT.get());
        this.dropSelf(BlockRegistry.CRACKED_MUD_BRICKS.get());
        this.dropSelf(BlockRegistry.CRACKED_RED_SANDSTONE_BRICKS.get());
        this.dropSelf(BlockRegistry.CRACKED_SANDSTONE_BRICKS.get());
        this.dropSelf(BlockRegistry.DOLLS_EYES.get());
        this.add(BlockRegistry.DOLLS_EYES_CROP.get(),
                this.applyExplosionDecay(BlockRegistry.DOLLS_EYES_CROP.get(),
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ItemRegistry.DOLLS_EYE_SEEDS.get())))
                )
        );
        this.dropPottedContents(BlockRegistry.POTTED_DOLLS_EYES.get());
        this.dropSelf(BlockRegistry.PALM_BUTTON.get());
        this.add(BlockRegistry.PALM_DOOR.get(), x -> this.createDoorTable(x));
        this.dropSelf(BlockRegistry.PALM_FENCE.get());
        this.dropSelf(BlockRegistry.PALM_FENCE_GATE.get());
        this.dropSelf(BlockRegistry.PALM_HANGING_SIGN.get());
        this.add(BlockRegistry.PALM_LEAVES.get(), x -> this.createLeavesDrops(x, BlockRegistry.PALM_SPROUT.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(BlockRegistry.PALM_LOG.get());
        this.dropSelf(BlockRegistry.PALM_PLANKS.get());
        this.dropSelf(BlockRegistry.PALM_PRESSURE_PLATE.get());
        this.dropSelf(BlockRegistry.PALM_SIGN.get());
        this.add(BlockRegistry.PALM_SLAB.get(), x -> this.createSlabItemTable(x));
        this.dropSelf(BlockRegistry.PALM_SPROUT.get());
        this.dropSelf(BlockRegistry.PALM_STAIRS.get());
        this.dropSelf(BlockRegistry.PALM_TRAPDOOR.get());
        this.dropSelf(BlockRegistry.PALM_WOOD.get());
        this.dropPottedContents(BlockRegistry.POTTED_PALM_SPROUT.get());
        this.dropSelf(BlockRegistry.RED_SANDSTONE_BRICKS.get());
        this.add(BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get(), x -> this.createSlabItemTable(x));
        this.dropSelf(BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.RED_SANDSTONE_BRICK_WALL.get());
        this.add(BlockRegistry.REEDS.get(), x -> this.createShearsOnlyDrop(x));
        this.dropSelf(BlockRegistry.SANDSTONE_BRICKS.get());
        this.add(BlockRegistry.SANDSTONE_BRICK_SLAB.get(), x -> this.createSlabItemTable(x));
        this.dropSelf(BlockRegistry.SANDSTONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.SANDSTONE_BRICK_WALL.get());
        this.dropSelf(BlockRegistry.SILT_MUD.get());
        this.dropSelf(BlockRegistry.STRIPPED_PALM_LOG.get());
        this.dropSelf(BlockRegistry.STRIPPED_PALM_WOOD.get());
        this.add(BlockRegistry.TALL_REEDS.get(), this.createDoublePlantShearsDrop(BlockRegistry.REEDS.get()));
    }
}
