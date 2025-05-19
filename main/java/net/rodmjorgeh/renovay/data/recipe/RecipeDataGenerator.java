package net.rodmjorgeh.renovay.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.data.BlockFamilyRegistry;
import net.rodmjorgeh.renovay.util.tags.ItemTagRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;
import net.rodmjorgeh.renovay.world.item.food.FoodRegistry;

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
                .requires(Blocks.SAND)
                .unlockedBy("has_mud", this.has(Blocks.MUD))
                .unlockedBy("has_sand", this.has(Blocks.SAND))
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
            return RenovayMod.MOD_ID + " recipes";
        }
    }
}
