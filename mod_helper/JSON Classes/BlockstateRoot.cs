using MinecraftModGenerator.JSON_Classes.Blockstate;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes
{
    public class BlockstateRoot
    {
        public Dictionary<string, VariantModel> variants { get; set; }
        public List<Multipart> multipart { get; set; }
    }
}
