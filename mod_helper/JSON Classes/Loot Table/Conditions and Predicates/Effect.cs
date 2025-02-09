using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Loot_Table.Conditions_and_Predicates
{
    public class Effect
    {
        public int duration { get; set; }
        public string id { get; set; }

        public Effect(string id, int duration) 
        {
            this.id = id;
            this.duration = duration;
        }
    }
}
