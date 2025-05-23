package net.rodmjorgeh.thriver.data.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
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
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.CoconutBlock;
import net.rodmjorgeh.thriver.world.item.ItemReg;

import java.util.Set;

public class BlockLootDataGenerator extends BlockLootSubProvider {

    public BlockLootDataGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockReg.BLOCKS.getEntries().stream()
                .map(x -> (Block)x.value())
                .toList();
    }

    @Override
    protected void generate() {
        this.add(BlockReg.BEIGE_BANNER.get(), x -> this.createBannerDrop(x));
        this.add(BlockReg.BEIGE_BED.get(), x -> this.createSinglePropConditionTable(x, BedBlock.PART, BedPart.HEAD));
        this.add(BlockReg.BEIGE_CANDLE.get(), x -> this.createCandleDrops(x));
        this.add(BlockReg.BEIGE_CANDLE_CAKE.get(), x -> createCandleCakeDrops(BlockReg.BEIGE_CANDLE.get()));
        this.dropSelf(BlockReg.BEIGE_CARPET.get());
        this.dropSelf(BlockReg.BEIGE_CONCRETE.get());
        this.dropSelf(BlockReg.BEIGE_CONCRETE_POWDER.get());
        this.dropSelf(BlockReg.BEIGE_GLAZED_TERRACOTTA.get());
        this.add(BlockReg.BEIGE_SHULKER_BOX.get(), x -> this.createShulkerBoxDrop(x));
        this.dropWhenSilkTouch(BlockReg.BEIGE_STAINED_GLASS.get());
        this.dropWhenSilkTouch(BlockReg.BEIGE_STAINED_GLASS_PANE.get());
        this.dropSelf(BlockReg.BEIGE_TERRACOTTA.get());
        this.dropSelf(BlockReg.BEIGE_WOOL.get());
        this.add(BlockReg.COCONUT.get(),
                x -> LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                this.applyExplosionDecay(x,
                                                        LootItem.lootTableItem(ItemReg.COCONUT.get())
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
        this.dropSelf(BlockReg.COIR_MAT.get());
        this.dropSelf(BlockReg.CRACKED_MUD_BRICKS.get());
        this.dropSelf(BlockReg.CRACKED_RED_SANDSTONE_BRICKS.get());
        this.dropSelf(BlockReg.CRACKED_SANDSTONE_BRICKS.get());
        this.dropSelf(BlockReg.DOLLS_EYES.get());
        this.add(BlockReg.DOLLS_EYES_CROP.get(),
                this.applyExplosionDecay(BlockReg.DOLLS_EYES_CROP.get(),
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ItemReg.DOLLS_EYE_SEEDS.get())))
                )
        );
        this.dropPottedContents(BlockReg.POTTED_DOLLS_EYES.get());
        this.dropSelf(BlockReg.PALM_BUTTON.get());
        this.add(BlockReg.PALM_DOOR.get(), x -> this.createDoorTable(x));
        this.dropSelf(BlockReg.PALM_FENCE.get());
        this.dropSelf(BlockReg.PALM_FENCE_GATE.get());
        this.dropSelf(BlockReg.PALM_HANGING_SIGN.get());
        this.add(BlockReg.PALM_LEAVES.get(), x -> this.createLeavesDrops(x, BlockReg.PALM_SPROUT.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(BlockReg.PALM_LOG.get());
        this.dropSelf(BlockReg.PALM_PLANKS.get());
        this.dropSelf(BlockReg.PALM_PRESSURE_PLATE.get());
        this.dropSelf(BlockReg.PALM_SIGN.get());
        this.add(BlockReg.PALM_SLAB.get(), x -> this.createSlabItemTable(x));
        this.dropSelf(BlockReg.PALM_SPROUT.get());
        this.dropSelf(BlockReg.PALM_STAIRS.get());
        this.dropSelf(BlockReg.PALM_TRAPDOOR.get());
        this.dropSelf(BlockReg.PALM_WOOD.get());
        this.dropPottedContents(BlockReg.POTTED_PALM_SPROUT.get());
        this.dropSelf(BlockReg.RED_SANDSTONE_BRICKS.get());
        this.add(BlockReg.RED_SANDSTONE_BRICK_SLAB.get(), x -> this.createSlabItemTable(x));
        this.dropSelf(BlockReg.RED_SANDSTONE_BRICK_STAIRS.get());
        this.dropSelf(BlockReg.RED_SANDSTONE_BRICK_WALL.get());
        this.add(BlockReg.REEDS.get(), x -> this.createShearsOnlyDrop(x));
        this.dropSelf(BlockReg.SANDSTONE_BRICKS.get());
        this.add(BlockReg.SANDSTONE_BRICK_SLAB.get(), x -> this.createSlabItemTable(x));
        this.dropSelf(BlockReg.SANDSTONE_BRICK_STAIRS.get());
        this.dropSelf(BlockReg.SANDSTONE_BRICK_WALL.get());
        this.dropSelf(BlockReg.SILT_MUD.get());
        this.dropSelf(BlockReg.STRIPPED_PALM_LOG.get());
        this.dropSelf(BlockReg.STRIPPED_PALM_WOOD.get());
        this.add(BlockReg.TALL_REEDS.get(), this.createDoublePlantShearsDrop(BlockReg.REEDS.get()));
    }
}
