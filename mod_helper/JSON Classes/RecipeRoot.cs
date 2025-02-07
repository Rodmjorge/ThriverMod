using MinecraftModGenerator.JSON_Classes.Recipe;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes
{
    public class RecipeRoot
    {
        public string type { get; set; }
        public string category { get; set; }
        public string group { get; set; }
        public Dictionary<string, string[]> key { get; set; }
        public List<string> pattern { get; set; }
        public int cookingTime { get; set; }
        public double experience { get; set; }
        public string ingredient { get; set; }
        public List<string> ingredients { get; set; }
        public RecipeResult result { get; set; }

        public RecipeRoot(string type, string category, Dictionary<string, string[]> key, List<string> patternOrIngredients, RecipeResult result)
        {
            this.type = type;
            this.category = category;
            this.key = key;

            if (key is null)
                this.ingredients = patternOrIngredients;
            else
                this.pattern = patternOrIngredients;

            this.result = result;
        }
        public RecipeRoot(string type, string category, List<string> ingredients, RecipeResult result) : this(type, category, null, ingredients, result) { }
        public RecipeRoot(string type, string category, string ingredient, RecipeResult result)
        {
            this.type = type;
            this.category = category;
            this.ingredient = ingredient;
            this.result = result;
        }
    }
}
