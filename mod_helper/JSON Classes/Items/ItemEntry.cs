using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Items
{
    public class ItemEntry
    {
        public ItemModel model { get; set; }
        public double threshold { get; set; }

        public ItemEntry(ItemModel model, double threshold)
        {
            this.model = model;
            this.threshold = threshold;
        }
    }
}
