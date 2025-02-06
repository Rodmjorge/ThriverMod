using MinecraftModGenerator.JSON_Classes.Loot_Table;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes
{
    public class LootTableRoot
    {
        public string type { get; set; }
        public List<Pool> pools { get; set; }
        public string random_sequence { get; set; }

        public LootTableRoot(string type, List<Pool> pools, string randomSequence)
        {
            this.type = type;
            this.pools = pools;
            this.random_sequence = randomSequence;
        }
    }
}
