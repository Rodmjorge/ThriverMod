using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Loot_Table.Conditions_and_Predicates
{
    public class Enchantment
    {
        public string enchantment { get; set; }
        public MinMax levels { get; set; }

        public Enchantment(string enchantment, int minLevel, int maxLevel) 
        { 
            this.enchantment = enchantment;
            this.levels = new MinMax(minLevel, maxLevel);
        }
    }
}
