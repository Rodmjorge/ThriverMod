package net.rodmjorgeh.thriver.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.tags.BlockTagReg;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;

import java.util.concurrent.CompletableFuture;

public class BlockTagDataGenerator extends BlockTagsProvider {

    public BlockTagDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ThriverMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                BlockReg.COCONUT.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockReg.BEIGE_CONCRETE.get(),
                BlockReg.BEIGE_GLAZED_TERRACOTTA.get(),
                BlockReg.BEIGE_TERRACOTTA.get(),
                BlockReg.CRACKED_MUD_BRICKS.get(),
                BlockReg.CRACKED_RED_SANDSTONE_BRICKS.get(),
                BlockReg.CRACKED_SANDSTONE_BRICKS.get(),
                BlockReg.RED_SANDSTONE_BRICKS.get(),
                BlockReg.RED_SANDSTONE_BRICK_SLAB.get(),
                BlockReg.RED_SANDSTONE_BRICK_STAIRS.get(),
                BlockReg.SANDSTONE_BRICKS.get(),
                BlockReg.SANDSTONE_BRICK_SLAB.get(),
                BlockReg.SANDSTONE_BRICK_STAIRS.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                BlockReg.COIR_MAT.get(),
                BlockReg.SILT_MUD.get()
        );

        this.tag(BlockTags.BANNERS).add(
                BlockReg.BEIGE_BANNER.get(),
                BlockReg.BEIGE_WALL_BANNER.get()
        );
        this.tag(BlockTags.BEDS).add(BlockReg.BEIGE_BED.get());
        this.tag(BlockTags.CANDLES).add(BlockReg.BEIGE_CANDLE.get());
        this.tag(BlockTags.CANDLE_CAKES).add(BlockReg.BEIGE_CANDLE_CAKE.get());
        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(BlockReg.PALM_HANGING_SIGN.get());
        this.tag(BlockTags.CONCRETE_POWDER).add(BlockReg.BEIGE_CONCRETE_POWDER.get());
        this.tag(BlockTags.ENDERMAN_HOLDABLE).add(BlockReg.SILT_MUD.get());
        this.tag(BlockTags.FENCE_GATES).add(BlockReg.PALM_FENCE_GATE.get());
        this.tag(BlockTags.IMPERMEABLE).add(BlockReg.BEIGE_STAINED_GLASS.get());
        this.tag(BlockTags.LEAVES).add(BlockReg.PALM_LEAVES.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(BlockTagReg.PALM_LOGS);
        this.tag(BlockTags.PLANKS).add(BlockReg.PALM_PLANKS.get());
        this.tag(BlockTags.SAPLINGS).add(BlockReg.PALM_SPROUT.get());
        this.tag(BlockTags.SHULKER_BOXES).add(BlockReg.BEIGE_SHULKER_BOX.get());
        this.tag(BlockTags.SLABS).add(
                BlockReg.RED_SANDSTONE_BRICK_SLAB.get(),
                BlockReg.SANDSTONE_BRICK_SLAB.get()
        );
        this.tag(BlockTags.STAIRS).add(
                BlockReg.RED_SANDSTONE_BRICK_STAIRS.get(),
                BlockReg.SANDSTONE_BRICK_STAIRS.get()
        );
        this.tag(BlockTags.STANDING_SIGNS).add(BlockReg.PALM_SIGN.get());
        this.tag(BlockTags.TERRACOTTA).add(BlockReg.BEIGE_TERRACOTTA.get());
        this.tag(BlockTags.WALL_HANGING_SIGNS).add(BlockReg.PALM_WALL_HANGING_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS).add(BlockReg.PALM_WALL_SIGN.get());
        this.tag(BlockTags.WALLS).add(
                BlockReg.RED_SANDSTONE_BRICK_WALL.get(),
                BlockReg.SANDSTONE_BRICK_WALL.get()
        );
        this.tag(BlockTags.WOODEN_BUTTONS).add(BlockReg.PALM_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).add(BlockReg.PALM_DOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(BlockReg.PALM_FENCE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(BlockReg.PALM_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_SLABS).add(BlockReg.PALM_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(BlockReg.PALM_STAIRS.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(BlockReg.PALM_TRAPDOOR.get());
        this.tag(BlockTags.WOOL).add(BlockReg.BEIGE_WOOL.get());
        this.tag(BlockTags.WOOL_CARPETS).add(BlockReg.BEIGE_CARPET.get());

        this.tag(BlockTagReg.MUD).add(
                Blocks.MUD,
                BlockReg.SILT_MUD.get()
        );
        this.tag(BlockTagReg.PALM_LOGS).add(
                BlockReg.PALM_LOG.get(),
                BlockReg.PALM_WOOD.get(),
                BlockReg.STRIPPED_PALM_LOG.get(),
                BlockReg.STRIPPED_PALM_WOOD.get()
        );
    }
}
