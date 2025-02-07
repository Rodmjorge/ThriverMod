using MinecraftModGenerator.JSON_Classes.Recipe.Advancements;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes
{
    public class RecipeAdvancementRoot
    {
        public string parent { get; set; }
        public Dictionary<string, Criteria> criteria { get; set; }
        public List<string[]> requirements { get; set; }
        public Rewards rewards { get; set; }

        public RecipeAdvancementRoot(string parent, Dictionary<string, Criteria> criteria, List<string[]> requirements, Rewards rewards)
        {
            this.parent = parent;
            this.criteria = criteria;
            this.requirements = requirements;
            this.rewards = rewards;
        }
    }
}
