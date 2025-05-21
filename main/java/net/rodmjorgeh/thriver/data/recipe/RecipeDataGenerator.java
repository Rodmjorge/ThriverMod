package net.rodmjorgeh.thriver.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.data.BlockFamilyRegistry;
import net.rodmjorgeh.thriver.util.tags.ItemTagRegistry;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.item.DyeColorR;
import net.rodmjorgeh.thriver.world.item.ItemRegistry;
import net.rodmjorgeh.thriver.world.item.food.FoodRegistry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeDataGenerator extends RecipeProvider {

    public RecipeDataGenerator(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override
    protected void buildRecipes() {
        BlockFamilyRegistry.getCustomFamilies()
                .filter(BlockFamily::shouldGenerateRecipe)
                .forEach(x -> this.generateRecipes(x, FeatureFlagSet.of(FeatureFlags.VANILLA)));


        List<Item> dyeList = List.of(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE,
                ItemRegistry.BEIGE_DYE.get());
        List<Item> woolList = List.of(Items.BLACK_WOOL, Items.BLUE_WOOL, Items.BROWN_WOOL, Items.CYAN_WOOL, Items.GRAY_WOOL, Items.GREEN_WOOL, Items.LIGHT_BLUE_WOOL, Items.LIGHT_GRAY_WOOL, Items.LIME_WOOL, Items.MAGENTA_WOOL, Items.ORANGE_WOOL, Items.PINK_WOOL, Items.PURPLE_WOOL, Items.RED_WOOL, Items.YELLOW_WOOL, Items.WHITE_WOOL,
                BlockRegistry.BEIGE_WOOL.get().asItem());
        List<Item> bedList = List.of(Items.BLACK_BED, Items.BLUE_BED, Items.BROWN_BED, Items.CYAN_BED, Items.GRAY_BED, Items.GREEN_BED, Items.LIGHT_BLUE_BED, Items.LIGHT_GRAY_BED, Items.LIME_BED, Items.MAGENTA_BED, Items.ORANGE_BED, Items.PINK_BED, Items.PURPLE_BED, Items.RED_BED, Items.YELLOW_BED, Items.WHITE_BED,
                ItemRegistry.BEIGE_BED.get());
        List<Item> carpetList = List.of(Items.BLACK_CARPET, Items.BLUE_CARPET, Items.BROWN_CARPET, Items.CYAN_CARPET, Items.GRAY_CARPET, Items.GREEN_CARPET, Items.LIGHT_BLUE_CARPET, Items.LIGHT_GRAY_CARPET, Items.LIME_CARPET, Items.MAGENTA_CARPET, Items.ORANGE_CARPET, Items.PINK_CARPET, Items.PURPLE_CARPET, Items.RED_CARPET, Items.YELLOW_CARPET, Items.WHITE_CARPET,
                ItemRegistry.BEIGE_CARPET.get());
        this.colorBlockWithDye(dyeList, woolList, "wool");
        this.colorBlockWithDye(dyeList, bedList, "bed");
        this.colorBlockWithDye(dyeList, carpetList, "carpet");
        this.dyeBlocksAndItems(ItemRegistry.BEIGE_DYE.get(), BlockRegistry.BEIGE_WOOL.get(),
                ItemRegistry.BEIGE_BANNER.get(),
                ItemRegistry.BEIGE_BED.get(),
                BlockRegistry.BEIGE_CANDLE.get(),
                ItemRegistry.BEIGE_CARPET.get(),
                BlockRegistry.BEIGE_CONCRETE_POWDER.get(),
                BlockRegistry.BEIGE_GLAZED_TERRACOTTA.get(),
                BlockRegistry.BEIGE_SHULKER_BOX.get(),
                BlockRegistry.BEIGE_STAINED_GLASS.get(),
                BlockRegistry.BEIGE_STAINED_GLASS_PANE.get(),
                BlockRegistry.BEIGE_TERRACOTTA.get());
        this.shapeless(RecipeCategory.FOOD, ItemRegistry.COCONUT_BEETROOT_SOUP.get())
                .requires(ItemRegistry.COCONUT_BOWL.get())
                .requires(Items.BEETROOT, 6)
                .unlockedBy("has_beetroot", this.has(Items.BEETROOT))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ItemRegistry.COCONUT_BOWL.get(), 3)
                .define('#', ItemRegistry.COCONUT.get())
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_coconut", this.has(ItemRegistry.COCONUT.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.FOOD, ItemRegistry.COCONUT_MILK.get())
                .requires(ItemRegistry.COCONUT_BOWL.get())
                .requires(ItemRegistry.COCONUT.get())
                .unlockedBy("has_coconut_bowl", this.has(ItemRegistry.COCONUT_BOWL.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.FOOD, ItemRegistry.COCONUT_MUSHROOM_STEW.get())
                .requires(Blocks.BROWN_MUSHROOM)
                .requires(Blocks.RED_MUSHROOM)
                .requires(ItemRegistry.COCONUT_BOWL.get())
                .unlockedBy("has_mushroom_stew", this.has(ItemRegistry.COCONUT_MUSHROOM_STEW.get()))
                .unlockedBy("has_bowl", this.has(ItemRegistry.COCONUT_BOWL.get()))
                .unlockedBy("has_brown_mushroom", this.has(Blocks.BROWN_MUSHROOM))
                .unlockedBy("has_red_mushroom", this.has(Blocks.RED_MUSHROOM))
                .save(this.output);
        this.shapeless(RecipeCategory.FOOD, ItemRegistry.COCONUT_RABBIT_STEW.get())
                .requires(Items.BAKED_POTATO)
                .requires(Items.COOKED_RABBIT)
                .requires(ItemRegistry.COCONUT_BOWL.get())
                .requires(Items.CARROT)
                .requires(Blocks.BROWN_MUSHROOM)
                .group("coconut_rabbit_stew")
                .unlockedBy("has_cooked_rabbit", this.has(Items.COOKED_RABBIT))
                .save(this.output, getConversionRecipeName(ItemRegistry.COCONUT_RABBIT_STEW.get(), Items.BROWN_MUSHROOM));
        this.shapeless(RecipeCategory.FOOD, ItemRegistry.COCONUT_RABBIT_STEW.get())
                .requires(Items.BAKED_POTATO)
                .requires(Items.COOKED_RABBIT)
                .requires(ItemRegistry.COCONUT_BOWL.get())
                .requires(Items.CARROT)
                .requires(Blocks.RED_MUSHROOM)
                .group("coconut_rabbit_stew")
                .unlockedBy("has_cooked_rabbit", this.has(Items.COOKED_RABBIT))
                .save(this.output, getConversionRecipeName(ItemRegistry.COCONUT_RABBIT_STEW.get(), Items.RED_MUSHROOM));
        BuiltInRegistries.ITEM.stream().forEach(x -> {
            SuspiciousEffectHolder effect = SuspiciousEffectHolder.tryGet(x);
            if (effect != null) {
                this.coconutSuspiciousStew(x, effect);
            }
        });
        this.shaped(RecipeCategory.MISC, ItemRegistry.COIR.get(), 3)
                .define('C', ItemRegistry.COCONUT.get())
                .define('S', Items.STRING)
                .pattern("SCS")
                .pattern(" S ")
                .unlockedBy("has_coconut", this.has(ItemRegistry.COCONUT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, BlockRegistry.COIR_MAT.get())
                .define('#', ItemRegistry.COIR.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_coir", this.has(ItemRegistry.COIR.get()))
                .save(this.output);
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(Blocks.MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockRegistry.CRACKED_MUD_BRICKS.get(), 0.1F, 200)
                .unlockedBy("has_mud_bricks", this.has(Blocks.MUD_BRICKS))
                .save(this.output);
        this.oneToOneConversionRecipe(ItemRegistry.BEIGE_DYE.get(), BlockRegistry.DOLLS_EYES.get(), "beige_dye", 2);
        this.woodenBoat(ItemRegistry.PALM_BOAT.get(), BlockRegistry.PALM_PLANKS.get());
        this.chestBoat(ItemRegistry.PALM_CHEST_BOAT.get(), ItemRegistry.PALM_BOAT.get());
        this.hangingSign(ItemRegistry.PALM_HANGING_SIGN.get(), BlockRegistry.STRIPPED_PALM_LOG.get());
        this.planksFromLogs(BlockRegistry.PALM_PLANKS.get(), ItemTagRegistry.PALM_LOGS, 4);
        this.woodFromLogs(BlockRegistry.PALM_WOOD.get(), BlockRegistry.PALM_LOG.get());
        this.cut(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICKS.get(), Blocks.CUT_RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICKS.get(), Blocks.RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICKS.get(), Blocks.CUT_RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get(), Blocks.RED_SANDSTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get(), Blocks.CUT_RED_SANDSTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get(), BlockRegistry.RED_SANDSTONE_BRICKS.get(), 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get(), Blocks.RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get(), Blocks.CUT_RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get(), BlockRegistry.RED_SANDSTONE_BRICKS.get());
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockRegistry.RED_SANDSTONE_BRICK_WALL.get(), Blocks.RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockRegistry.RED_SANDSTONE_BRICK_WALL.get(), Blocks.CUT_RED_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockRegistry.RED_SANDSTONE_BRICK_WALL.get(), BlockRegistry.RED_SANDSTONE_BRICKS.get());
        this.shaped(RecipeCategory.TOOLS, ItemRegistry.REED_FLUTE.get())
                .define('#', BlockRegistry.REEDS.get())
                .define('S', Items.STRING)
                .define('T', Items.STICK)
                .pattern("  #")
                .pattern(" #S")
                .pattern("T  ")
                .unlockedBy("has_reeds", this.has(BlockRegistry.REEDS.get()))
                .save(this.output);
        this.cut(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICKS.get(), Blocks.CUT_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICKS.get(), Blocks.SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICKS.get(), Blocks.CUT_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICK_SLAB.get(), Blocks.SANDSTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICK_SLAB.get(), Blocks.CUT_SANDSTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICK_SLAB.get(), BlockRegistry.SANDSTONE_BRICKS.get(), 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICK_STAIRS.get(), Blocks.SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICK_STAIRS.get(), Blocks.CUT_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SANDSTONE_BRICK_STAIRS.get(), BlockRegistry.SANDSTONE_BRICKS.get());
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockRegistry.SANDSTONE_BRICK_WALL.get(), Blocks.SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockRegistry.SANDSTONE_BRICK_WALL.get(), Blocks.CUT_SANDSTONE);
        this.stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockRegistry.SANDSTONE_BRICK_WALL.get(), BlockRegistry.SANDSTONE_BRICKS.get());
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.SILT_MUD.get(), 2)
                .requires(Blocks.MUD)
                .requires(ItemTags.SAND)
                .unlockedBy("has_mud", this.has(Blocks.MUD))
                .unlockedBy("has_sand", this.has(ItemTags.SAND))
                .save(this.output);
        this.woodFromLogs(BlockRegistry.STRIPPED_PALM_WOOD.get(), BlockRegistry.STRIPPED_PALM_LOG.get());


        this.shaped(RecipeCategory.FOOD, Blocks.CAKE)
                .define('M', ItemTagRegistry.MILK)
                .define('S', Items.SUGAR)
                .define('W', Items.WHEAT)
                .define('E', Items.EGG)
                .pattern("MMM")
                .pattern("SES")
                .pattern("WWW")
                .unlockedBy("has_egg", this.has(Items.EGG))
                .save(this.output);
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.PACKED_MUD)
                .requires(ItemTagRegistry.MUD)
                .requires(Items.WHEAT)
                .unlockedBy("has_mud", this.has(ItemTagRegistry.MUD))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, Items.PAPER, 3)
                .define('#', BlockRegistry.REEDS.get())
                .pattern("###")
                .unlockedBy("has_reeds", this.has(BlockRegistry.REEDS.get()))
                .save(this.output, ThriverMod.createStringLoc("paper_from_reeds"));
    }


    private void coconutSuspiciousStew(Item flowerItem, SuspiciousEffectHolder effect) {
        ItemStack itemstack = new ItemStack(
                ItemRegistry.COCONUT_SUSPICIOUS_STEW.get().builtInRegistryHolder(),
                1,
                DataComponentPatch.builder().set(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                        FoodRegistry.forCoconut(effect.getSuspiciousEffects().effects().getFirst())).build()
        );
        this.shapeless(RecipeCategory.FOOD, itemstack)
                .requires(ItemRegistry.COCONUT_BOWL.get())
                .requires(Items.BROWN_MUSHROOM)
                .requires(Items.RED_MUSHROOM)
                .requires(flowerItem)
                .group("coconut_suspicious_stew")
                .unlockedBy(getHasName(flowerItem), this.has(flowerItem))
                .save(this.output, getItemName(itemstack.getItem()) + "_from_" + getItemName(flowerItem));
    }

    private void dyeBlocksAndItems(ItemLike dyeItem, ItemLike woolItem,
                                   ItemLike bannerItem,
                                   ItemLike bedItem,
                                   ItemLike candleItem,
                                   ItemLike carpetItem,
                                   ItemLike concretePowderItem,
                                   ItemLike glazedTerracottaItem,
                                   ItemLike shulkerBoxItem,
                                   ItemLike stainedGlassItem,
                                   ItemLike stainedGlassPaneItem,
                                   ItemLike terracottaItem) {
        String dyeName = DyeColor.getColor(dyeItem.asItem().getDefaultInstance()).getSerializedName();

        this.banner(bannerItem, woolItem);
        this.bedFromPlanksAndWool(bedItem, woolItem);
        this.candle(candleItem, dyeItem);
        this.carpet(carpetItem, woolItem);
        this.concretePowder(concretePowderItem, dyeItem);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(terracottaItem), RecipeCategory.DECORATIONS,
                glazedTerracottaItem, 0.1F, 200)
                .unlockedBy("has_" + dyeName + "_terracotta", this.has(terracottaItem))
                .save(this.output);
        TransmuteRecipeBuilder.transmute(RecipeCategory.DECORATIONS, this.tag(ItemTags.SHULKER_BOXES),
                        Ingredient.of(dyeItem), shulkerBoxItem.asItem())
                .group("shulker_box_dye")
                .unlockedBy("has_shulker_box", this.has(ItemTags.SHULKER_BOXES))
                .save(this.output);
        this.stainedGlassFromGlassAndDye(stainedGlassItem, dyeItem);
        this.stainedGlassPaneFromStainedGlass(stainedGlassPaneItem, stainedGlassItem);
        this.stainedGlassPaneFromGlassPaneAndDye(stainedGlassPaneItem, dyeItem);
        this.coloredTerracottaFromTerracottaAndDye(terracottaItem, dyeItem);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new RecipeDataGenerator(provider, output);
        }

        @Override
        public String getName() {
            return ThriverMod.MOD_ID + " recipes";
        }
    }
}
