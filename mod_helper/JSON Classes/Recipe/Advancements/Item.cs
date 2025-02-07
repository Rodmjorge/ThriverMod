using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Recipe.Advancements
{
    public class Item
    {
        public string items { get; set; }

        public Item(string items)
        {
            this.items = items;
        }
    }
}
