using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Items
{
    public class ItemModel
    {
        public string type { get; set; }

        [JsonProperty("base")]
        public string @base { get; set; }

        [JsonProperty("%modelString%")]
        public string modelString { get; set; }
        [JsonProperty("%modelClass%")]
        public ItemModel modelClass { get; set; }

        public string color { get; set; }
        public List<ItemCase> cases { get; set; }
        public ItemModel fallback { get; set; }
        public string pattern { get; set; }
        public string property { get; set; }
        public double scale { get; set; }
        public string source { get; set; }

        public List<ItemEntry> entries { get; set; }

        public List<ItemTint> tints { get; set; }

        public ItemModel(string modelString)
        {
            this.type = "minecraft:model";
            this.modelString = modelString;
        }
        public ItemModel(string type, string @base, ItemModel modelClass)
        {
            this.type = type;
            this.@base = @base;
            this.modelClass = modelClass;
        }
        public ItemModel(string type, string color)
        {
            this.type = type;
            this.color = color;
        }
        public ItemModel(string type, ItemModel fallback, string pattern, string property, params ItemCase[] cases)
        {
            this.type = type;
            this.fallback = fallback;
            this.pattern = pattern;
            this.property = property;
            this.cases = cases.ToList();
        }
        public ItemModel(string modelString, params ItemTint[] tints) : this(modelString)
        {
            this.tints = tints.ToList();
        }
    }
}
