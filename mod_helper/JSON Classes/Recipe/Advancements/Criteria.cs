using MinecraftModGenerator.JSON_Classes.Loot_Table;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Recipe.Advancements
{
    public class Criteria
    {
        public string type { get; set; }
        public List<Criteria> children { get; set; }

        [JsonProperty("%conditionSingle%")]
        public Condition conditionSingle { get; set; }
        [JsonProperty("%conditionList%")]
        public List<Condition> conditionList { get; set; }
        public List<Function> functions { get; set; }
        public string name { get; set; }
        public string trigger { get; set; }

        public Criteria() { }
        public Criteria(List<Condition> conditions, string typeOrTrigger, bool isType, bool ignoreForSingle = false)
        {
            if (conditions is not null) {
                if (conditions.Count == 1 && !ignoreForSingle)
                    this.conditionSingle = conditions[0];
                else
                    this.conditionList = conditions;
            }

            if (isType)
                this.type = typeOrTrigger;
            else
                this.trigger = typeOrTrigger;
        }
        public Criteria(Condition condition, string typeOrTrigger, bool isType, bool ignoreForSingle = false) : 
            this(new List<Condition>() { condition }, typeOrTrigger, isType, ignoreForSingle) { }

        public Criteria(List<Condition> conditions, bool ignoreForSingle = false) : this(conditions, null, false, ignoreForSingle) { }
        public Criteria(Condition condition, bool ignoreForSingle = false) : this(condition, null, false, ignoreForSingle) { }
    }
}
