using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Blockstate
{
    public class Multipart
    {
        public VariantModel apply { get; set; }
        public Dictionary<string, string> when { get; set; }

        public Multipart(VariantModel apply, Dictionary<string, string> when)
        {
            this.apply = apply;
            this.when = when;
        }
        public Multipart(VariantModel apply, string whenDir, string whenType) : this(apply, new Dictionary<string, string>() { { whenDir, whenType } }) { }
        public Multipart(string whenDir, string whenType, string model, bool? uvlock = default, int x = default, int y = default) : this(new VariantModel(model, uvlock, x, y), whenDir, whenType) { }
        public Multipart(string model, bool? uvlock = default, int x = default, int y = default) : this(new VariantModel(model, uvlock, x, y), null) { }
    }
}
