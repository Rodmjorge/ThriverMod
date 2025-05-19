package net.rodmjorgeh.renovay.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.util.tags.BlockTagRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class BlockTagDataGenerator extends BlockTagsProvider {

    public BlockTagDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RenovayMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                BlockRegistry.COCONUT.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockRegistry.BEIGE_CONCRETE.get(),
                BlockRegistry.BEIGE_GLAZED_TERRACOTTA.get(),
                BlockRegistry.BEIGE_TERRACOTTA.get(),
                BlockRegistry.CRACKED_MUD_BRICKS.get(),
                BlockRegistry.CRACKED_RED_SANDSTONE_BRICKS.get(),
                BlockRegistry.CRACKED_SANDSTONE_BRICKS.get(),
                BlockRegistry.RED_SANDSTONE_BRICKS.get(),
                BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get(),
                BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get(),
                BlockRegistry.SANDSTONE_BRICKS.get(),
                BlockRegistry.SANDSTONE_BRICK_SLAB.get(),
                BlockRegistry.SANDSTONE_BRICK_STAIRS.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                BlockRegistry.COIR_MAT.get(),
                BlockRegistry.SILT_MUD.get()
        );

        this.tag(BlockTags.BANNERS).add(
                BlockRegistry.BEIGE_BANNER.get(),
                BlockRegistry.BEIGE_WALL_BANNER.get()
        );
        this.tag(BlockTags.BEDS).add(BlockRegistry.BEIGE_BED.get());
        this.tag(BlockTags.CANDLES).add(BlockRegistry.BEIGE_CANDLE.get());
        this.tag(BlockTags.CANDLE_CAKES).add(BlockRegistry.BEIGE_CANDLE_CAKE.get());
        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(BlockRegistry.PALM_HANGING_SIGN.get());
        this.tag(BlockTags.CONCRETE_POWDER).add(BlockRegistry.BEIGE_CONCRETE_POWDER.get());
        this.tag(BlockTags.ENDERMAN_HOLDABLE).add(BlockRegistry.SILT_MUD.get());
        this.tag(BlockTags.FENCE_GATES).add(BlockRegistry.PALM_FENCE_GATE.get());
        this.tag(BlockTags.IMPERMEABLE).add(BlockRegistry.BEIGE_STAINED_GLASS.get());
        this.tag(BlockTags.LEAVES).add(BlockRegistry.PALM_LEAVES.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(BlockTagRegistry.PALM_LOGS);
        this.tag(BlockTags.PLANKS).add(BlockRegistry.PALM_PLANKS.get());
        this.tag(BlockTags.SAPLINGS).add(BlockRegistry.PALM_SPROUT.get());
        this.tag(BlockTags.SHULKER_BOXES).add(BlockRegistry.BEIGE_SHULKER_BOX.get());
        this.tag(BlockTags.SLABS).add(
                BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get(),
                BlockRegistry.SANDSTONE_BRICK_SLAB.get()
        );
        this.tag(BlockTags.STAIRS).add(
                BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get(),
                BlockRegistry.SANDSTONE_BRICK_STAIRS.get()
        );
        this.tag(BlockTags.STANDING_SIGNS).add(BlockRegistry.PALM_SIGN.get());
        this.tag(BlockTags.TERRACOTTA).add(BlockRegistry.BEIGE_TERRACOTTA.get());
        this.tag(BlockTags.WALL_HANGING_SIGNS).add(BlockRegistry.PALM_WALL_HANGING_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS).add(BlockRegistry.PALM_WALL_SIGN.get());
        this.tag(BlockTags.WALLS).add(
                BlockRegistry.RED_SANDSTONE_BRICK_WALL.get(),
                BlockRegistry.SANDSTONE_BRICK_WALL.get()
        );
        this.tag(BlockTags.WOODEN_BUTTONS).add(BlockRegistry.PALM_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).add(BlockRegistry.PALM_DOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(BlockRegistry.PALM_FENCE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(BlockRegistry.PALM_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_SLABS).add(BlockRegistry.PALM_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(BlockRegistry.PALM_STAIRS.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(BlockRegistry.PALM_TRAPDOOR.get());
        this.tag(BlockTags.WOOL).add(BlockRegistry.BEIGE_WOOL.get());
        this.tag(BlockTags.WOOL_CARPETS).add(BlockRegistry.BEIGE_CARPET.get());

        this.tag(BlockTagRegistry.MUD).add(
                Blocks.MUD,
                BlockRegistry.SILT_MUD.get()
        );
        this.tag(BlockTagRegistry.PALM_LOGS).add(
                BlockRegistry.PALM_LOG.get(),
                BlockRegistry.PALM_WOOD.get(),
                BlockRegistry.STRIPPED_PALM_LOG.get(),
                BlockRegistry.STRIPPED_PALM_WOOD.get()
        );
    }
}
