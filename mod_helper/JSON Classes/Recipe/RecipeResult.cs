using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Recipe
{
    public class RecipeResult
    {
        public Dictionary<string, object> components { get; set; }
        public int count { get; set; }
        public string id { get; set; }

        public RecipeResult(Dictionary<string, object> components, string id, int count = 1)
        {
            this.components = components;
            this.id = id;
            this.count = count;
        }
        public RecipeResult(string id, int count = 1) : this(null, id, count) { }
    }
}
