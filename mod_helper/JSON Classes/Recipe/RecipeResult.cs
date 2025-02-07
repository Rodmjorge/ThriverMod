using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Recipe
{
    public class RecipeResult
    {
        public int count { get; set; }
        public string id { get; set; }

        public RecipeResult(string id, int count = 1)
        {
            this.id = id;
            this.count = count;
        }
    }
}
