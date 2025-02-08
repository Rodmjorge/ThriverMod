using MinecraftModGenerator.JSON_Classes;
using MinecraftModGenerator.JSON_Classes.Recipe;
using MinecraftModGenerator.JSON_Classes.Recipe.Advancements;
using MinecraftModGenerator.JSON_Classes.Loot_Table;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MinecraftModGenerator.JSON_Classes.Items;

namespace MinecraftModGenerator
{
    internal class DataCreator : Creator
    {
        public DataCreator(string path, string modId) : base(path, modId) { }

        public override void Create()
        {
            #region recipes
            var i = new int[] { 1 };

            #region palm tree
            CreateRecipe("palm_wood", "#palm_log", "##/##", 3, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, group: "bark");
            CreateRecipe("stripped_palm_wood", "#stripped_palm_log", "##/##", 3, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, group: "bark");
            CreateRecipe("palm_planks", "#palm_logs", "", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, group: "planks");
            CreateRecipe("palm_boat", "#palm_planks", "# #/###", 1, RecipeCategory.Misc, RecipeAdvancementCategory.Transportation, group: "boat", advancementType: RecipeAdvancementType.Boat);
            CreateRecipe("palm_button", "palm_planks", "", 1, RecipeCategory.Redstone, RecipeAdvancementCategory.Redstone, group: "wooden_button");
            CreateRecipe("palm_chest_boat", "palm_boat;:chest", "", 1, RecipeCategory.Misc, RecipeAdvancementCategory.Transportation, group: "chest_boat", ignoreInAdvancement: i);
            CreateRecipe("palm_door", "#palm_planks", "##/##/##", 3, RecipeCategory.Redstone, RecipeAdvancementCategory.Redstone, group: "wooden_door");
            CreateRecipe("palm_fence", "#palm_planks;S:stick", "#S#/#S#", 3, RecipeCategory.Misc, RecipeAdvancementCategory.Decorations, group: "wooden_fence", ignoreInAdvancement: i);
            CreateRecipe("palm_fence_gate", "#palm_planks;S:stick", "S#S/S#S", 1, RecipeCategory.Redstone, RecipeAdvancementCategory.Redstone, group: "wooden_fence_gate", ignoreInAdvancement: i);
            CreateRecipe("palm_hanging_sign", "#stripped_palm_log;C:chain", "C C/###/###", 6, RecipeCategory.Misc, RecipeAdvancementCategory.Decorations, group: "hanging_sign", ignoreInAdvancement: i);
            CreateRecipe("palm_pressure_plate", "#palm_planks", "##", 1, RecipeCategory.Redstone, RecipeAdvancementCategory.Redstone, group: "wooden_pressure_plate");
            CreateRecipe("palm_sign", "#palm_planks;S:stick", "###/###/ S ", 3, RecipeCategory.Misc, RecipeAdvancementCategory.Decorations, group: "wooden_sign");
            CreateRecipe("palm_slab", "#palm_planks", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, group: "wooden_slab");
            CreateRecipe("palm_stairs", "#palm_planks", "#  /## /###", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, group: "wooden_stairs");
            CreateRecipe("palm_trapdoor", "#palm_planks", "###/###", 2, RecipeCategory.Redstone, RecipeAdvancementCategory.Redstone, group: "wooden_trapdoor");

            CreateRecipe("coconut_bowl", "#coconut", "# #/ # ", 3, RecipeCategory.Misc, RecipeAdvancementCategory.Food);
            CreateRecipe("coconut_mushroom_stew", ":brown_mushroom;:red_mushroom;coconut_bowl", "", 1, RecipeCategory.Misc, RecipeAdvancementCategory.Food);
            CreateRecipe("coconut_rabbit_stew_from_brown_mushroom", ":baked_potato;:cooked_rabbit;coconut_bowl;:carrot;:brown_mushroom", "", 1, RecipeCategory.Misc, RecipeAdvancementCategory.Food, group: "coconut_rabbit_stew");
            CreateRecipe("coconut_rabbit_stew_from_red_mushroom", ":baked_potato;:cooked_rabbit;coconut_bowl;:carrot;:red_mushroom", "", 1, RecipeCategory.Misc, RecipeAdvancementCategory.Food, group: "coconut_rabbit_stew");
            CreateRecipe("coconut_beetroot_soup", "coconut_bowl;:beetroot;:beetroot;:beetroot;:beetroot;:beetroot;:beetroot", "", 1, RecipeCategory.Misc, RecipeAdvancementCategory.Food);
            #endregion
            #region sandstone
            CreateRecipe("sandstone_bricks", "#:cut_sandstone", "##/##", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("sandstone_bricks", ":sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_bricks", ":cut_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_slab", "#sandstone_bricks", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("sandstone_brick_slab", ":sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_slab", ":cut_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_slab", "sandstone_bricks", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_stairs", "#sandstone_bricks", "#  /## /###", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("sandstone_brick_stairs", ":sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_stairs", ":cut_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_stairs", "sandstone_bricks", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_wall", "#sandstone_bricks", "###/###", 6, RecipeCategory.Misc, RecipeAdvancementCategory.Decorations);
            CreateRecipe("sandstone_brick_wall", ":sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_wall", ":cut_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("sandstone_brick_wall", "sandstone_bricks", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("cracked_sandstone_bricks", "sandstone_bricks", "", 1, RecipeCategory.Blocks, RecipeAdvancementCategory.Building_Blocks, RecipeType.Smelting);

            CreateRecipe("red_sandstone_bricks", "#:cut_red_sandstone", "##/##", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("red_sandstone_bricks", ":red_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_bricks", ":cut_red_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_slab", "#red_sandstone_bricks", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("red_sandstone_brick_slab", ":red_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_slab", ":cut_red_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_slab", "red_sandstone_bricks", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_stairs", "#red_sandstone_bricks", "#  /## /###", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("red_sandstone_brick_stairs", ":red_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_stairs", ":cut_red_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_stairs", "red_sandstone_bricks", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_wall", "#red_sandstone_bricks", "###/###", 6, RecipeCategory.Misc, RecipeAdvancementCategory.Decorations);
            CreateRecipe("red_sandstone_brick_wall", ":red_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_wall", ":cut_red_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("red_sandstone_brick_wall", "red_sandstone_bricks", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("cracked_red_sandstone_bricks", "red_sandstone_bricks", "", 1, RecipeCategory.Blocks, RecipeAdvancementCategory.Building_Blocks, RecipeType.Smelting);

            #region weathered sandstone
            CreateRecipe("weathered_sandstone", "S:sandstone;C:coarse_dirt", "SC/CS", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("chiseled_weathered_sandstone", "#weathered_sandstone_slab", "#/#", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("chiseled_weathered_sandstone", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("cut_weathered_sandstone", "#weathered_sandstone", "##/##", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("cut_weathered_sandstone", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("smooth_weathered_sandstone", "weathered_sandstone", "", 1, RecipeCategory.Blocks, RecipeAdvancementCategory.Building_Blocks, RecipeType.Smelting);
            CreateRecipe("weathered_sandstone_slab", "#weathered_sandstone", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("weathered_sandstone_slab", "weathered_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_stairs", "#weathered_sandstone", "#  /## /###", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("weathered_sandstone_stairs", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_wall", "#weathered_sandstone", "###/###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Decorations);
            CreateRecipe("weathered_sandstone_wall", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("cut_weathered_sandstone_slab", "#cut_weathered_sandstone", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("cut_weathered_sandstone_slab", "weathered_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("cut_weathered_sandstone_slab", "cut_weathered_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("smooth_weathered_sandstone_slab", "#smooth_weathered_sandstone", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("smooth_weathered_sandstone_slab", "smooth_weathered_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("smooth_weathered_sandstone_stairs", "#smooth_weathered_sandstone", "#  /## /###", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("smooth_weathered_sandstone_stairs", "smooth_weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);

            CreateRecipe("weathered_sandstone_bricks", "#cut_weathered_sandstone", "##/##", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("weathered_sandstone_bricks", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_bricks", "cut_weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_slab", "#weathered_sandstone_bricks", "###", 6, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("weathered_sandstone_brick_slab", "weathered_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_slab", "cut_weathered_sandstone", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_slab", "weathered_sandstone_bricks", "", 2, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_stairs", "#weathered_sandstone_bricks", "#  /## /###", 4, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks);
            CreateRecipe("weathered_sandstone_brick_stairs", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_stairs", "cut_weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_stairs", "weathered_sandstone_bricks", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Building_Blocks, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_wall", "#weathered_sandstone_bricks", "###/###", 6, RecipeCategory.Misc, RecipeAdvancementCategory.Decorations);
            CreateRecipe("weathered_sandstone_brick_wall", "weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_wall", "cut_weathered_sandstone", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("weathered_sandstone_brick_wall", "weathered_sandstone_bricks", "", 1, RecipeCategory.Building, RecipeAdvancementCategory.Decorations, RecipeType.Stonecutting);
            CreateRecipe("cracked_weathered_sandstone_bricks", "weathered_sandstone_bricks", "", 1, RecipeCategory.Blocks, RecipeAdvancementCategory.Building_Blocks, RecipeType.Smelting);
            #endregion
            #endregion
            #endregion

            #region tags
            CreateTag("palm_logs", TagType.Block, true, "palm_log", "palm_wood", "stripped_palm_log", "stripped_palm_wood");
            CreateTag(":ceiling_hanging_signs", TagType.Block, false, "palm_hanging_sign");
            CreateTag(":fence_gates", TagType.Block, true, "palm_fence_gate");
            CreateTag(":leaves", TagType.Block, true, "palm_leaves");
            CreateTag(":logs_that_burn", TagType.Block, true, "#palm_logs");
            CreateTag(":planks", TagType.Block, true, "palm_planks");
            CreateTag(":saplings", TagType.Block, true, "palm_sprout");
            CreateTag(":slabs", TagType.Block, true, "sandstone_brick_slab", "red_sandstone_brick_slab", "weathered_sandstone_slab", "cut_weathered_sandstone_slab",
                "smooth_weathered_sandstone_slab", "weathered_sandstone_brick_slab");
            CreateTag(":stairs", TagType.Block, true, "sandstone_brick_stairs", "red_sandstone_brick_stairs", "weathered_sandstone_stairs", "smooth_weathered_sandstone_stairs", 
                "weathered_sandstone_brick_stairs");
            CreateTag(":standing_signs", TagType.Block, false, "palm_sign");
            CreateTag(":wall_hanging_signs", TagType.Block, false, "palm_wall_hanging_sign");
            CreateTag(":wall_signs", TagType.Block, false, "palm_wall_sign");
            CreateTag(":walls", TagType.Block, true, "sandstone_brick_wall", "red_sandstone_brick_wall", "weathered_sandstone_wall", "weathered_sandstone_brick_wall");
            CreateTag(":wooden_button", TagType.Block, true, "palm_button");
            CreateTag(":wooden_doors", TagType.Block, true, "palm_door");
            CreateTag(":wooden_fences", TagType.Block, true, "palm_fence");
            CreateTag(":wooden_pressure_plate", TagType.Block, true, "palm_pressure_plate");
            CreateTag(":wooden_slabs", TagType.Block, true, "palm_slab");
            CreateTag(":wooden_stairs", TagType.Block, true, "palm_stairs");
            CreateTag(":wooden_trapdoors", TagType.Block, true, "palm_trapdoor");

            CreateTag(":boat", TagType.Entity_Type, false, "palm_boat");

            CreateTag(":boats", TagType.Item, false, "palm_boat");
            CreateTag(":chest_boats", TagType.Item, false, "palm_chest_boat");
            CreateTag(":hanging_signs", TagType.Item, false, "palm_hanging_sign");
            CreateTag(":signs", TagType.Item, false, "palm_sign");
            #endregion
        }

        public void CreateRecipe(string name, string ingredients, string pattern, int count, RecipeCategory category, RecipeAdvancementCategory advancementCategory,
            RecipeType type = RecipeType.Crafting, string group = null, string resultNew = null, RecipeAdvancementType advancementType = RecipeAdvancementType.Classic, 
            Dictionary<string, Criteria> customCriteria = null, int[] ignoreInAdvancement = null)
        {
            string result = (string.IsNullOrEmpty(resultNew)) ? name : resultNew;
            result = FuncTex(result);

            var keys = new Dictionary<string, List<string>>();
            var ingredientArr = ingredients.Split(';');
            var patternArr = pattern.Split("/");
            bool isShapeless = string.IsNullOrEmpty(pattern);

            if (!isShapeless) {
                foreach (var i in ingredientArr) {
                    string subI = FuncTex(i.Substring(1));
                    string charKey = i[0].ToString();

                    if (keys.ContainsKey(charKey))
                        keys[charKey].Add(subI);
                    else
                        keys.Add(charKey, new List<string>() { subI });
                }
            }
            ingredientArr = ingredientArr.Select(x => FuncTex(x)).ToArray();

            RecipeRoot root;
            RecipeResult recipeResult = new RecipeResult(result, count);
            var ing = ingredientArr[0];
            string fromName = $"_from_{ CleanIng(ing) }_";
            string categoryS = category.ToString().ToLower();

            switch (type) {

                default:
                case RecipeType.Crafting:
                    root = new RecipeRoot(isShapeless ? "minecraft:crafting_shapeless" : "minecraft:crafting_shaped",
                        categoryS,
                        isShapeless ? null : keys.ToDictionary(x => x.Key, y => y.Value.ToArray()),
                        isShapeless ? ingredientArr.ToList() : patternArr.ToList(),
                        recipeResult);
                    break;

                case RecipeType.Smelting:
                    name += fromName + "smelting";
                    root = new RecipeRoot("minecraft:smelting", categoryS, ing, recipeResult);
                    root.cookingTime = 200;
                    root.experience = 0.1d;

                    break;

                case RecipeType.Stonecutting:
                    name += fromName + "stonecutting";
                    root = new RecipeRoot("minecraft:stonecutting", null, ing, recipeResult);

                    break;
            }
            root.group = group;

            var keyValues = new List<string>();
            foreach (var valueDict in keys.Values) {
                foreach (var valueList in valueDict.ToArray())
                    keyValues.Add(valueList);
            }

            CreateRecipeAdvancement(name, isShapeless ? ingredientArr : keyValues.ToArray(), advancementCategory, advancementType, customCriteria, ignoreInAdvancement);
            CreateFile(GetPath("recipe"), name, Serialize(root));
        }

        public void CreateRecipeAdvancement(string name, string[] ingredients, RecipeAdvancementCategory advancementCategory, 
            RecipeAdvancementType advancementType = RecipeAdvancementType.Classic, Dictionary<string, Criteria> customCriteria = null, int[] ignoreInAdvancement = null)
        {
            RecipeAdvancementRoot root;

            string nameTex = FuncTex(name);
            var criteriaDict = new Dictionary<string, Criteria>();

            if (ignoreInAdvancement is not null)
                ingredients = ingredients.Select((n, i) => new { Name = n, Index = i })
                                         .Where(x => !ignoreInAdvancement.Contains(x.Index))
                                         .Select(x => x.Name).ToArray();

            switch (advancementType) {
                default:
                case RecipeAdvancementType.Classic:
                    foreach (var ing in ingredients) {
                        var item = new Item(ing);
                        var cond = new Condition() { items = new() { item } };
                        string criteriaName = "has_" + CleanIng(ing);

                        criteriaDict.TryAdd(criteriaName, new Criteria(cond, "minecraft:inventory_changed", false));
                    }

                    break;

                case RecipeAdvancementType.Boat:
                    criteriaDict.TryAdd("in_water", new Criteria(new Condition() { block = "minecraft:water" }, "minecraft:enter_block", false));
                    break;

                case RecipeAdvancementType.Custom:
                    criteriaDict = customCriteria;
                    break;
            }

            criteriaDict.Add("has_the_recipe", new Criteria(new Condition() { recipe = nameTex }, "minecraft:recipe_unlocked", false));

            var req = criteriaDict.Keys.ToArray();
            var reward = new Rewards() { recipes = new() { nameTex } };
            root = new RecipeAdvancementRoot("minecraft:recipes/root", criteriaDict, new() { req }, reward);

            CreateFile(GetPath($@"advancement\recipes\{ advancementCategory.ToString().ToLower() }"), name, Serialize(root));
        }

        public void CreateTag(string filename, TagType type, bool copyToItem, params string[] values)
        {
            bool isMinecraft = filename.StartsWith(':');
            filename = (isMinecraft) ? filename.Remove(0, 1) : filename;

            var root = new TagRoot(values.Select(x => FuncTex(x)).ToArray());
            var serialized = Serialize(root);
            string prefix = isMinecraft ? "minecraft" : null;

            CreateFile(GetPath($@"tags\{ type.ToString().ToLower() }", prefix), filename, serialized);
            if (copyToItem)
                CreateFile(GetPath(@"tags\item", prefix), filename, serialized);
        }

        public override string FuncTex(string name, string prefix = null)
        {
            bool hasMinecraft = name.Contains(":");
            name = hasMinecraft ? name.Split(':')[1] : name;

            string addCardinal = (name.Contains("#")) ? "#" : string.Empty;
            name = name.Replace("#", "");

            return base.FuncTex(name, addCardinal + (hasMinecraft ? "minecraft" : modId));
        }

        private static string CleanIng(string ing) => ing.Split(':')[1];
    }
}
