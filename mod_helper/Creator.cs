using MinecraftModGenerator.JSON_Classes;
using MinecraftModGenerator.JSON_Classes.JSON_Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator
{
    public abstract class Creator
    {
        protected string path;
        protected string modId;

        public Creator(string path, string modId)
        {
            this.path = path;
            this.modId = modId;
        }

        public abstract void Create();

        public string GetPath(string folder, string prefix = null)
        {
            prefix = CleanPrefix(prefix);
            return path + $@"\{prefix}\{folder}";
        }

        protected string Serialize(object? value, bool ignoreDefaultValues = true)
        {
            return JsonConvert.SerializeObject(value, Formatting.Indented, new JsonSerializerSettings() {
                DefaultValueHandling = ignoreDefaultValues ? DefaultValueHandling.Ignore : DefaultValueHandling.Include,
                Converters = { new SingleElementArrayConverter() }
            }).Replace("%modelString%", "model").Replace("%modelClass%", "model")
              .Replace("%countDouble%", "count").Replace("%countMinMax%", "count")
              .Replace("%conditionSingle%", "conditions").Replace("%conditionList%", "conditions");
        }

        protected string CleanPrefix(string prefix)
        {
            if (string.IsNullOrEmpty(prefix))
                prefix = modId;

            return prefix;
        }
        protected static int CleanAngle(int angle)
        {
            if (angle >= 360)
                angle -= 360;

            return angle;
        }

        protected static string AddToName(string name, string addition, bool removeLastSOnStringAdding = true)
        {
            return ((name.EndsWith("s") && removeLastSOnStringAdding) ? name.Remove(name.Length - 1, 1) : name) + addition;
        }
        protected static string NullToAdd(string name, string adder)
        {
            if (string.IsNullOrEmpty(name))
                return adder;

            return name;
        }

        public string FuncBlockTex(string name, string prefix = null, string folder = "block")
        {
            prefix = CleanPrefix(prefix);
            return $"{prefix}:{ (string.IsNullOrEmpty(folder) ? string.Empty : folder + "/") }{name}";
        }
        protected string FuncItemTex(string name, string prefix = null) => FuncBlockTex(name, prefix, "item");
        public virtual string FuncTex(string name, string prefix = null) => FuncBlockTex(name, prefix, "");

        protected static void CreateFile(string path, string filename, string content)
        {
            File.WriteAllText($@"{path}\{filename}.json", content);
        }

        protected static string[] Directions() => new string[] { "north", "east", "south", "west" };
        protected static Dictionary<string, int> DirectionsOrdered(bool add180 = false)
        {
            var dirs = Directions();
            var orderedDirs = dirs.OrderBy(x => x);

            return orderedDirs.Select(n => new { Name = n }).ToDictionary(x => x.Name, y => CleanAngle(Array.IndexOf(dirs, y.Name) * 90 + (add180 ? 180 : 0)));
        } 
    }
}
