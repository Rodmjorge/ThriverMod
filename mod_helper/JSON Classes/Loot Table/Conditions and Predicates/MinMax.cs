using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Loot_Table.Conditions_and_Predicates
{
    public class MinMax
    {
        public string type { get; set; }
        public double max { get; set; }
        public double min { get; set; }

        public MinMax(double min, double max)
        {
            this.min = min;
            this.max = max;
        }
    }
}
