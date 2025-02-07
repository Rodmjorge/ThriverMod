using System;

namespace MinecraftModGenerator
{
    internal class Program
    {
        protected static string internalPath = @"C:\Users\rodmj\OneDrive\Documents\Modding\Minecraft\Renovay\src\main\resources";

        public static void Main()
        {
            new ModelCreator($@"{internalPath}\assets", "renovay").Create();
            new DataCreator($@"{internalPath}\data", "renovay").Create();
            new LootTableCreator($@"{internalPath}\data", "renovay").Create();
        }
    }
}