using MinecraftModGenerator.JSON_Classes.Recipe.Advancements;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Loot_Table
{
    public class Pool
    {
        public double bonus_rolls { get; set; }
        public List<Condition> conditions { get; set; }
        public List<Criteria> entries { get; set; }
        public double rolls { get; set; }

        public Pool(List<Criteria> entries, double rolls = 1.0d)
        {
            this.entries = entries;
            this.rolls = rolls;
        }
        public Pool(List<Condition> conditions, List<Criteria> entries, double rolls = 1.0d)
        {
            this.conditions = conditions;
            this.entries = entries;
            this.rolls = rolls;
        }
        public Pool(Criteria entry, double rolls = 1.0d) : this(new List<Criteria>() { entry }, rolls) { }
        public Pool(Condition condition, Criteria entry, double rolls = 1.0d) : this (new List<Condition>() { condition }, new List<Criteria>() { entry }, rolls) { }
    }
}
