using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes
{
    public class TagRoot
    {
        public List<string> values { get; set; }

        public TagRoot(string[] values) 
        {
            this.values = values.ToList();
        }
    }
}
