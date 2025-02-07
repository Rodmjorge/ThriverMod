using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Items
{
    public class ItemCase
    {
        public ItemModel model { get; set; }
        public List<string> when { get; set; }

        public ItemCase(ItemModel model, params string[] whens)
        {
            this.model = model;
            this.when = whens.ToList();
        }
    }
}
