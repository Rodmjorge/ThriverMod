using MinecraftModGenerator.JSON_Classes;
using MinecraftModGenerator.JSON_Classes.Loot_Table;
using MinecraftModGenerator.JSON_Classes.Loot_Table.Conditions_and_Predicates;
using MinecraftModGenerator.JSON_Classes.Recipe.Advancements;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace MinecraftModGenerator
{
    public class LootTableCreator : Creator
    {
        public LootTableCreator(string path, string modId) : base(path, modId) { }

        public override void Create()
        {
            #region palm tree
            CreateLootTable("palm_log");
            CreateLootTable("palm_wood");
            CreateLootTable("stripped_palm_log");
            CreateLootTable("stripped_palm_wood");
            CreateLootTable("palm_planks");
            CreateLootTable("palm_slab", LootTableType.Slab);
            CreateLootTable("palm_stairs");
            CreateLootTable("palm_fence");
            CreateLootTable("palm_fence_gate");
            CreateLootTable("palm_door", LootTableType.Door);
            CreateLootTable("palm_trapdoor");
            CreateLootTable("palm_pressure_plate");
            CreateLootTable("palm_button");
            CreateLootTable("palm_leaves", LootTableType.Leaves, null, "palm_sprout");
            CreateLootTable("palm_sprout");
            CreateLootTable("palm_sign");
            CreateLootTable("palm_hanging_sign");
            #endregion

            #region sandstone
            CreateLootTable("sandstone_bricks");
            CreateLootTable("sandstone_brick_slab", LootTableType.Slab);
            CreateLootTable("sandstone_brick_stairs");
            CreateLootTable("sandstone_brick_wall");
            CreateLootTable("cracked_sandstone_bricks");

            CreateLootTable("red_sandstone_bricks");
            CreateLootTable("red_sandstone_brick_slab", LootTableType.Slab);
            CreateLootTable("red_sandstone_brick_stairs");
            CreateLootTable("red_sandstone_brick_wall");
            CreateLootTable("cracked_red_sandstone_bricks");

            #region weathered sandstone
            CreateLootTable("weathered_sandstone");
            CreateLootTable("chiseled_weathered_sandstone");
            CreateLootTable("cut_weathered_sandstone");
            CreateLootTable("smooth_weathered_sandstone");
            CreateLootTable("weathered_sandstone_slab", LootTableType.Slab);
            CreateLootTable("weathered_sandstone_stairs");
            CreateLootTable("weathered_sandstone_wall");
            CreateLootTable("cut_weathered_sandstone_slab", LootTableType.Slab);
            CreateLootTable("smooth_weathered_sandstone_slab", LootTableType.Slab);
            CreateLootTable("smooth_weathered_sandstone_stairs");
            CreateLootTable("weathered_sandstone_bricks");
            CreateLootTable("weathered_sandstone_brick_slab", LootTableType.Slab);
            CreateLootTable("weathered_sandstone_brick_stairs");
            CreateLootTable("weathered_sandstone_brick_wall");
            CreateLootTable("cracked_weathered_sandstone_bricks");
            #endregion
            #endregion
        }

        public void CreateLootTable(string name, LootTableType type = LootTableType.Common, LootTableRoot customRoot = null, params string[] nameAdders)
        {
            CreateFile(GetPath(@"loot_table\blocks"), name, Serialize(GetRootFromType(name, type, customRoot, nameAdders.Select(x => FuncTex(x)).ToArray())));
        }

        protected LootTableRoot GetRootFromType(string name, LootTableType type, LootTableRoot customRoot, string[] nameAdders)
        {
            string texName = FuncTex(name);
            string bTexName = FuncBlockTex(name);

            var classicCond = ClassicCondition();
            var classicEntry = ClassicEntry(texName);

            Pool pool;
            List<Pool> pools = new List<Pool>();

            switch (type) {
                default:
                case LootTableType.Common:
                    return new("minecraft:block", new() { new Pool(classicCond, classicEntry) }, bTexName);

                case LootTableType.Slab:
                    SetFunctions(classicEntry, 
                        SetCount(
                            BlockStateProperty(texName, "type", "double"), 
                            2), 
                        new("minecraft:explosion_decay")
                    );

                    pool = new Pool(classicEntry);
                    return new("minecraft:block", new() { pool }, bTexName);

                case LootTableType.Door:
                    SetConditions(classicEntry, 
                        BlockStateProperty(texName, "half", "lower")
                    );

                    pool = new Pool(classicCond, classicEntry);
                    return new("minecraft:block", new() { pool }, bTexName);

                case LootTableType.Leaves:
                    var anyOf = AnyOf(
                                    MatchTool("minecraft:shears"),
                                    MatchTool(Predicate.Enchantments("minecraft:silk_touch", 1))
                                );

                    pools.Add(new(AlternativesEntry(
                        SetConditions(ClassicEntry(texName), anyOf),
                        SetConditions(ClassicEntry(nameAdders[0]),
                            ClassicCondition(),
                            TableBonus("minecraft:fortune", 0.05d, 0.0625d, 0.083333336d, 0.1d)
                        )
                    )));
                    pools.Add(new(InvertedCondition(anyOf), SetFunctions(
                        SetConditions(ClassicEntry("minecraft:stick"),
                            TableBonus("minecraft:fortune", 0.02d, 0.022222223d, 0.025d, 0.033333335d, 0.1d)
                        ),
                        SetCount(null, new MinMax(1, 2)),
                        new("minecraft:explosion_decay")
                    )));

                    return new("minecraft:block", pools, bTexName);

                case LootTableType.Custom:
                    return customRoot;
            }
        }

        private Criteria SetConditions(Criteria c, params Condition[] conditions)
        {
            c.conditionList = conditions.ToList();
            return c;
        }
        private Criteria SetFunctions(Criteria c, params Function[] functions)
        {
            c.functions = functions.ToList();
            return c;
        }
        private Criteria ClassicEntry(string texName) => new Criteria() {
            type = "minecraft:item",
            name = texName
        };
        private Criteria AlternativesEntry(params Criteria[] children) => new Criteria() {
            type = "minecraft:alternatives",
            children = children.ToList()
        };

        private Function SetCount(Condition c, int count, bool add = false) => new Function(c, "minecraft:set_count") {
            add = add,
            countDouble = count
        };
        private Function SetCount(Condition c, MinMax count, bool add = false) {
            count.type = "minecraft:uniform";

            return new Function(c, "minecraft:set_count") {
                add = add,
                countMinMax = count
            };
        }

        private Condition ClassicCondition() => new Condition() { condition = "minecraft:survives_explosion" };
        private Condition InvertedCondition(Condition term) => new Condition() {
            condition = "minecraft:inverted",
            term = term
        };
        private Condition AnyOf(params Condition[] terms) => new Condition() {
            condition = "minecraft:any_of",
            terms = terms.ToList()
        };
        private Condition TableBonus(string enchantment, params double[] chances) => new Condition() {
            condition = "minecraft:table_bonus",
            enchantment = enchantment,
            chances = chances
        };
        private Condition MatchTool(string texName) => new Condition() {
            condition = "minecraft:match_tool",
            predicate = new() { items = texName }
        };
        private Condition MatchTool(Predicate predicate) => new Condition() {
            condition = "minecraft:match_tool",
            predicate = predicate
        };
        private Condition BlockStateProperty(string texName, string propertyType, string property) => new Condition() {
            block = texName,
            condition = "minecraft:block_state_property",
            properties = new() { { propertyType, property } }
        };
    }
}
