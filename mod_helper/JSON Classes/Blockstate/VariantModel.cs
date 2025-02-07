using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.Blockstate
{
    public class VariantModel
    {
        public string model { get; set; }
        public bool uvlock { get; set; }
        public int x { get; set; }
        public int y { get; set; }

        public VariantModel(string model, bool? uvlock = default, int x = default, int y = default)
        {
            this.model = model;
            this.x = x;
            this.y = y;

            if (uvlock is null)
                uvlock = (x != default || y != default);
            this.uvlock = uvlock.Value;
        }
    }
}
