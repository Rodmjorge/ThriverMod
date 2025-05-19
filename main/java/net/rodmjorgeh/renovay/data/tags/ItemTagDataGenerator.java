package net.rodmjorgeh.renovay.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.util.tags.BlockTagRegistry;
import net.rodmjorgeh.renovay.util.tags.ItemTagRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class ItemTagDataGenerator extends ItemTagsProvider {

    public ItemTagDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, RenovayMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.BANNERS).add(ItemRegistry.BEIGE_BANNER.get());
        this.tag(ItemTags.BOATS).add(ItemRegistry.PALM_BOAT.get());
        this.tag(ItemTags.BUNDLES).add(ItemRegistry.BEIGE_BUNDLE.get());
        this.tag(ItemTags.CHEST_BOATS).add(ItemRegistry.PALM_CHEST_BOAT.get());

        this.tag(ItemTagRegistry.MILK).add(
                ItemRegistry.COCONUT_MILK.get(),
                Items.MILK_BUCKET
        );

        this.copy(BlockTags.BEDS, ItemTags.BEDS);
        this.copy(BlockTags.CANDLES, ItemTags.CANDLES);
        this.copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
        this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.SHULKER_BOXES, ItemTags.SHULKER_BOXES);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        this.copy(BlockTags.TERRACOTTA, ItemTags.TERRACOTTA);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.WOOL, ItemTags.WOOL);
        this.copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);

        this.copy(BlockTagRegistry.MUD, ItemTagRegistry.MUD);
        this.copy(BlockTagRegistry.PALM_LOGS, ItemTagRegistry.PALM_LOGS);
    }
}
