using MinecraftModGenerator.JSON_Classes.Loot_Table.Conditions_and_Predicates;
using MinecraftModGenerator.JSON_Classes.Recipe.Advancements;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Loot_Table
{
    public class Function
    {
        public bool? add { get; set; }
        public List<Condition> conditions { get; set; }

        [JsonProperty("%countDouble%")]
        public double? countDouble { get; set; }
        [JsonProperty("%countMinMax%")]
        public MinMax countMinMax { get; set; }
        public string function { get; set; }

        public Function(string function)
        {
            this.function = function;
        }
        public Function(List<Condition> conditions, string function)
        {
            if (conditions.Contains(null))
                conditions = null;

            this.conditions = conditions;
            this.function = function;
        }
        public Function(Condition condition, string function) : this(new List<Condition>() { condition }, function) { }
    }
}
