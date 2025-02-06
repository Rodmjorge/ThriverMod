using MinecraftModGenerator.JSON_Classes.Loot_Table.Conditions_and_Predicates;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Loot_Table
{
    public class Predicate
    {
        public string items { get; set; }
        public Dictionary<string, object> predicates { get; set; }

        public static Predicate Enchantments(string enchantment, int minLevel = default, int maxLevel = default) =>
            new() {
                predicates = new() {
                    { "minecraft:enchantments", new List<Enchantment>() { new(enchantment, minLevel, maxLevel) } }
                }
            };
    }
}
