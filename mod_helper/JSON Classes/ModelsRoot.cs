using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes
{
    public class ModelsRoot
    {
        public string render_type { get; set; }
        public string parent { get; set; }
        public Dictionary<string, string> textures { get; set; }

        public ModelsRoot(string parent, Dictionary<string, string> textures, string renderType)
        {
            this.parent = parent;
            this.textures = textures;
            this.render_type = renderType;
        }
        public ModelsRoot(string parent, Dictionary<string, string> textures) : this(parent, textures, null) { }

        internal static ModelsRoot Parented(ModelCreator mc, string parentWithoutTex, Dictionary<string, string> textures, bool cutout)
        {
            return new ModelsRoot(!string.IsNullOrEmpty(parentWithoutTex) ? mc.FuncBlockTex(parentWithoutTex, "minecraft") : null, textures, (cutout) ? "minecraft:cutout" : null);
        }


        internal static Dictionary<string, string> DictSingle(string name, string type)
        {
            return new Dictionary<string, string>() {
                { type, name }
            };
        }
        internal static Dictionary<string, string> DictDouble(string sideName, string endName, bool isBottomTop)
        {
            return new Dictionary<string, string>() {
                { isBottomTop ? "bottom" : "end", endName },
                { isBottomTop ? "top" : "side", sideName }
            };
        }
        internal static Dictionary<string, string> DictBottomSideTop(string sideName, string topName, string bottomName)
        {
            return new Dictionary<string, string>() {
                { "bottom", bottomName },
                { "side", sideName },
                { "top", topName }
            };
        }

        public static ModelsRoot ClassicItemRoot(string itemName) => new("minecraft:item/generated", new Dictionary<string, string>() {
            { "layer0", itemName }
        });
    }
}
