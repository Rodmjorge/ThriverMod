using MinecraftModGenerator.JSON_Classes.Loot_Table;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Recipe.Advancements
{
    public class Condition
    {
        public string block { get; set; }
        public double[] chances { get; set; }
        public string condition { get; set; }
        public Condition term { get; set; }
        public List<Condition> terms { get; set; }
        public List<Item> items { get; set; }
        public Predicate predicate { get; set; }
        public string recipe { get; set; }
        public string enchantment { get; set; }
        public Dictionary<string, string> properties { get; set; }
    }
}
