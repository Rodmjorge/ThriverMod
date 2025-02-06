using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Items
{
    public class ItemTint
    {
        public string type { get; set; }
        public long value { get; set; }

        public ItemTint(string type, int value)
        {
            this.type = type;
            this.value = value;
        }
    }
}
