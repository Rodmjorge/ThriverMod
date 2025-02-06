using MinecraftModGenerator.JSON_Classes;
using MinecraftModGenerator.JSON_Classes.Blockstate;
using MinecraftModGenerator.JSON_Classes.Items;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Security;
using System.Threading.Tasks;
using System.Transactions;
using System.Xml.Linq;

namespace MinecraftModGenerator
{
    internal class ModelCreator : Creator
    {
        public ModelCreator(string path, string modId) : base(path, modId) { }

        public void FileBlockstate(string name, Dictionary<string, VariantModel> variants) => CreateFile(GetPath("blockstates"), name, Serialize(new BlockstateRoot() {
            variants = variants
        }));
        public void FileBlockstate(string name, List<Multipart> multiparts) => CreateFile(GetPath("blockstates"), name, Serialize(new BlockstateRoot() {
            multipart = multiparts
        }));
        public void FileModel(string name, Dictionary<string, ModelsRoot> blocksParent, bool isAnItem = false, ItemModel? itemModel = null, bool addItem = true) =>
            FileModel(name, blocksParent, null, isAnItem, itemModel, addItem);
        public void FileModel(string name, Dictionary<string, ModelsRoot> blocksParent, ModelsRoot itemParent, bool isAnItem = false, ItemModel? itemModel = null, bool addItem = true)
        {
            if (itemModel is null)
                itemModel = new ItemModel(isAnItem ? FuncItemTex(name) : FuncBlockTex(name));

            if (addItem)
                CreateFile(GetPath("items"), name, Serialize(new ItemsRoot() {
                    model = itemModel
                }));

            if (blocksParent is not null) {
                foreach (var blockName in blocksParent.Keys) {
                    var model = blocksParent[blockName];
                    CreateFile(GetPath(@"models\block"), blockName, Serialize(model));
                }
            }
            if (itemParent is not null)
                CreateFile(GetPath(@"models\item"), name, Serialize(itemParent));
        }

        public override void Create()
        {
            #region palm tree
            FamilyWoodType("palm");
            #endregion

            #region sandstone
            FamilyEndSideBlock("sandstone_bricks", texBlockNameEnd: FuncBlockTex("sandstone_top", "minecraft"));
            CreateBlock("cracked_sandstone_bricks", FuncBlockTex("sandstone_top", "minecraft"));
            FamilyEndSideBlock("red_sandstone_bricks", texBlockNameEnd: FuncBlockTex("red_sandstone_top", "minecraft"));
            CreateBlock("cracked_red_sandstone_bricks", FuncBlockTex("red_sandstone_top", "minecraft"));

            #region weathered sandstone
            var top = FuncBlockTex("weathered_sandstone_top");
            FamilyTopBottomBlock("weathered_sandstone");
            CreateBlock("chiseled_weathered_sandstone", top);
            FamilyEndSideBlock("cut_weathered_sandstone", texBlockNameEnd: top, createWall: false, createStairs: false);
            FamilyBlock("smooth_weathered_sandstone", texBlockName: top, createWall: false);
            FamilyEndSideBlock("weathered_sandstone_bricks", texBlockNameEnd: top);
            CreateBlock("cracked_weathered_sandstone_bricks", top);
            #endregion
            #endregion
        }

        public void CreateBlock(string blockName, string texBlockNameEnd = null, string texBlockNameBottom = null, string texBlockNameNew = null, BlockType type = BlockType.Normal, bool cutout = false)
        {
            string parent = "cube_all";
            string bsBlockName = FuncBlockTex(blockName);
            string texBlockName = (string.IsNullOrEmpty(texBlockNameNew)) ? bsBlockName : texBlockNameNew;

            Dictionary<string, string> blockModelDict;
            if (!string.IsNullOrEmpty(texBlockNameEnd)) {
                if (!string.IsNullOrEmpty(texBlockNameBottom)) {
                    blockModelDict = ModelsRoot.DictBottomSideTop(texBlockName, texBlockNameEnd, texBlockNameBottom);
                    parent = "cube_bottom_top";
                }
                else {
                    blockModelDict = ModelsRoot.DictDouble(texBlockName, texBlockNameEnd, false);
                    parent = "cube_column";
                }
            }
            else
                blockModelDict = ModelsRoot.DictSingle(texBlockName, "all");

            var normalDict = new Dictionary<string, ModelsRoot>() {
                { blockName, ModelsRoot.Parented(this, parent, blockModelDict, cutout) }
            };

            switch (type) {
                default:
                case BlockType.Normal:
                    FileBlockstate(blockName, new Dictionary<string, VariantModel>() {
                        { "", new(bsBlockName) }
                    });
                    FileModel(blockName, normalDict);

                    break;

                case BlockType.RotatedPillar:
                case BlockType.RotatedPillarFull:
                    bool addHorizontal = (type == BlockType.RotatedPillar);
                    string adder = addHorizontal ? "_horizontal" : string.Empty;

                    if (addHorizontal)
                        normalDict.Add(blockName + adder, ModelsRoot.Parented(this, "cube_column_horizontal", blockModelDict, cutout));

                    FileBlockstate(blockName, new Dictionary<string, VariantModel>() {
                        { "axis=x", new(bsBlockName + adder, false, 90, 90) },
                        { "axis=y", new(bsBlockName) },
                        { "axis=z", new(bsBlockName + adder, false, 90) }
                    });
                    FileModel(blockName, normalDict);

                    break;
            }
        }
        public void CreateItem(string itemName) => FileModel(itemName, null, ModelsRoot.ClassicItemRoot(FuncItemTex(itemName)), true);
        public void CreateSlab(string blockName, string slabName, string texBlockNameTop = null, string texBlockNameBottom = null, string texBlockNameNew = null, bool cutout = false)
        {
            string bsBlockName = FuncBlockTex(blockName);
            string texBlockName = (string.IsNullOrEmpty(texBlockNameNew)) ? bsBlockName : texBlockNameNew;
            string texSlabName = FuncBlockTex(slabName);

            if (string.IsNullOrEmpty(texBlockNameTop)) texBlockNameTop = texBlockName;
            if (string.IsNullOrEmpty(texBlockNameBottom)) texBlockNameBottom = texBlockName;

            var slabModelDict = ModelsRoot.DictBottomSideTop(texBlockName, texBlockNameTop, texBlockNameBottom);
            FileBlockstate(slabName, new Dictionary<string, VariantModel>() {
                { "type=bottom", new(texSlabName) },
                { "type=double", new(bsBlockName) },
                { "type=top", new(texSlabName + "_top") }
            });
            FileModel(slabName, new() {
                { slabName, ModelsRoot.Parented(this, "slab", slabModelDict, cutout) },
                { slabName + "_top", ModelsRoot.Parented(this, "slab_top", slabModelDict, cutout) }
            });
        }
        public void CreateStairs(string blockName, string stairsName, string texBlockNameTop = null, string texBlockNameBottom = null, string texBlockNameNew = null, bool cutout = false)
        {
            var variants = new Dictionary<string, VariantModel>();
            var dirDict = DirectionsOrdered(true);

            string texBlockName = (string.IsNullOrEmpty(texBlockNameNew)) ? FuncBlockTex(blockName) : texBlockNameNew;
            string texStairsName = FuncBlockTex(stairsName);
            string innerT = texStairsName + "_inner";
            string outerT = texStairsName + "_outer";

            foreach (string dir in dirDict.Keys) {
                for (int i = 0; i < 2; i++) {
                    bool isBottom = (i == 0);
                    string half = isBottom ? "bottom" : "top";

                    int angle = CleanAngle(dirDict[dir] + (isBottom ? 0 : 90));
                    int x = isBottom ? 0 : 180;
                    int rightAngle = CleanAngle(angle + 90);

                    string s = $"facing={dir},half={half},shape=";
                    variants.Add($"{s}inner_left", new(innerT, x: x, y: angle));
                    variants.Add($"{s}inner_right", new(innerT, x: x, y: rightAngle));
                    variants.Add($"{s}outer_left", new(outerT, x: x, y: angle));
                    variants.Add($"{s}outer_right", new(outerT, x: x, y: rightAngle));
                    variants.Add($"{s}straight", new(texStairsName, x: x, y: isBottom ? rightAngle : angle));
                }
            }

            if (string.IsNullOrEmpty(texBlockNameTop)) texBlockNameTop = texBlockName;
            if (string.IsNullOrEmpty(texBlockNameBottom)) texBlockNameBottom = texBlockName;

            var stairsModelDict = ModelsRoot.DictBottomSideTop(texBlockName, texBlockNameTop, texBlockNameBottom);
            FileBlockstate(stairsName, variants);
            FileModel(stairsName, new() {
                { stairsName, ModelsRoot.Parented(this, "stairs", stairsModelDict, cutout) },
                { stairsName + "_inner", ModelsRoot.Parented(this, "inner_stairs", stairsModelDict, cutout) },
                { stairsName + "_outer", ModelsRoot.Parented(this, "outer_stairs", stairsModelDict, cutout) }
            });
        }
        public void CreateWall(string blockName, string wallName, string texBlockNameNew = null, bool cutout = false)
        {
            string texBlockName = (string.IsNullOrEmpty(texBlockNameNew)) ? FuncBlockTex(blockName) : texBlockNameNew;
            string texWallName = FuncBlockTex(wallName);

            var multiparts = new List<Multipart>() {
                new("up", "true", texWallName + "_post")
            };

            for (int i = 0; i < 2; i++) {
                bool isLow = (i == 0);
                string when = isLow ? "low" : "tall";
                var dirArr = Directions();

                for (int j = 0; j < dirArr.Length; j++) {
                    var dir = dirArr[j];
                    int angle = CleanAngle(j * 90);

                    multiparts.Add(new(dir, when, texWallName + "_side" + (isLow ? "" : "_tall"), true, y: angle));
                }
            }

            var wallModelDict = ModelsRoot.DictSingle(texBlockName, "wall");
            FileBlockstate(wallName, multiparts);
            FileModel(wallName, new() {
                { wallName + "_inventory", ModelsRoot.Parented(this, "wall_inventory", wallModelDict, cutout) },
                { wallName + "_post", ModelsRoot.Parented(this, "template_wall_post", wallModelDict, cutout) },
                { wallName + "_side", ModelsRoot.Parented(this, "template_wall_side", wallModelDict, cutout) },
                { wallName + "_side_tall", ModelsRoot.Parented(this, "template_wall_side_tall", wallModelDict, cutout) }
            }, itemModel: new ItemModel(texWallName + "_inventory"));
        }
        public void CreateFence(string fenceName, string texBlockName)
        {
            string texfenceName = FuncBlockTex(fenceName);

            var multiparts = new List<Multipart>() { 
                new(texfenceName + "_post")
            };
            var dirArr = Directions();

            for (int i = 0; i < dirArr.Length; i++) {
                var dir = dirArr[i];
                multiparts.Add(new(dir, "true", texfenceName + "_side", true, y: i * 90));
            }

            var fenceModelDict = ModelsRoot.DictSingle(texBlockName, "texture");
            FileBlockstate(fenceName, multiparts);
            FileModel(fenceName, new() {
                { fenceName + "_post", ModelsRoot.Parented(this, "fence_post", fenceModelDict, false) },
                { fenceName + "_side", ModelsRoot.Parented(this, "fence_side", fenceModelDict, false) },
                { fenceName + "_inventory", ModelsRoot.Parented(this, "fence_inventory", fenceModelDict, false) },
            }, itemModel: new ItemModel(texfenceName + "_inventory"));
        }
        public void CreateFenceGate(string fenceGateName, string texBlockName)
        {
            string texFenceGateName = FuncBlockTex(fenceGateName);

            var variants = new Dictionary<string, VariantModel>();
            var dirDict = DirectionsOrdered(true);

            foreach (string dir in dirDict.Keys) {
                int dirAngle = dirDict[dir];

                for (int i = 0; i < 2; i++) {
                    bool inWall = (i != 0);
                    string adder = $"facing={ dir },in_wall={ inWall.ToString().ToLower() },";
                    string texFenceGateNameNew = texFenceGateName + (inWall ? "_wall" : "");

                    variants.Add($"{adder}open=false", new(texFenceGateNameNew, y: dirAngle));
                    variants.Add($"{adder}open=true", new(texFenceGateNameNew + "_open", y: dirAngle));
                }
            }

            var fenceGateModelDict = ModelsRoot.DictSingle(texBlockName, "texture");
            FileBlockstate(fenceGateName, variants);
            FileModel(fenceGateName, new() {
                { fenceGateName, ModelsRoot.Parented(this, "template_fence_gate", fenceGateModelDict, false) },
                { fenceGateName + "_open", ModelsRoot.Parented(this, "template_fence_gate_open", fenceGateModelDict, false) },
                { fenceGateName + "_wall", ModelsRoot.Parented(this, "template_fence_gate_wall", fenceGateModelDict, false) },
                { fenceGateName + "_wall_open", ModelsRoot.Parented(this, "template_fence_gate_wall_open", fenceGateModelDict, false) }
            });
        }
        public void CreateDoor(string doorName)
        {
            string texDoorName = FuncBlockTex(doorName);

            var variants = new Dictionary<string, VariantModel>();
            var models = new Dictionary<string, ModelsRoot>();
            var dirDict = DirectionsOrdered(true);
            var doorModelDict = ModelsRoot.DictDouble(texDoorName + "_top", texDoorName + "_bottom", true);

            foreach (string dir in dirDict.Keys) {
                int dirAngle = dirDict[dir];

                for (int i = 0; i < 2; i++) {
                    bool lower = (i == 0);

                    for (int j = 0; j < 2; j++) {
                        bool hingeLeft = (j == 0);

                        string hingeDir = hingeLeft ? "left" : "right";
                        string bottomOrTop = (lower ? "bottom" : "top");
                        string adder = $"facing={dir},half={ (lower ? "lower" : "upper") },hinge={ hingeDir },";
                        string texDoorNameNew = $"{texDoorName}_{bottomOrTop}_{ hingeDir }";

                        variants.Add($"{adder}open=false", new(texDoorNameNew, false, y: CleanAngle(dirAngle + 90)));
                        variants.Add($"{adder}open=true", new(texDoorNameNew + "_open", false, y: CleanAngle(hingeLeft ? dirAngle + 180 : dirAngle)));

                        if (models.Count < 8) {
                            models.Add($"{doorName}_{bottomOrTop}_{hingeDir}", ModelsRoot.Parented(this, $"door_{bottomOrTop}_{hingeDir}", doorModelDict, false));
                            models.Add($"{doorName}_{bottomOrTop}_{hingeDir}_open", ModelsRoot.Parented(this, $"door_{bottomOrTop}_{hingeDir}_open", doorModelDict, false));
                        }
                    }
                }
            }

            FileBlockstate(doorName, variants);
            FileModel(doorName, models, ModelsRoot.ClassicItemRoot(FuncItemTex(doorName)), true);
        }
        public void CreateButton(string buttonName, string texBlockName)
        {
            string texButtonName = FuncBlockTex(buttonName);

            var variants = new Dictionary<string, VariantModel>();
            var dirDict = DirectionsOrdered(true);
            var typeToXAngle = new Dictionary<string, int>() {
                { "ceiling", 180 },
                { "floor", 0 },
                { "wall", 90 }
            };

            foreach (string type in typeToXAngle.Keys) {
                int typeAngle = typeToXAngle[type];

                foreach (string dir in dirDict.Keys) {
                    int dirAngle = CleanAngle(type == "ceiling" ? dirDict[dir] : dirDict[dir] + 180);
                    string adder = $"face={type},facing={dir},";

                    variants.Add($"{adder}powered=false", new(texButtonName, type == "wall", typeAngle, dirAngle));
                    variants.Add($"{adder}powered=true", new(texButtonName + "_pressed", type == "wall", typeAngle, dirAngle));
                }
            }

            var buttonModelDict = ModelsRoot.DictSingle(texBlockName, "texture");
            FileBlockstate(buttonName, variants);
            FileModel(buttonName, new() {
                { buttonName, ModelsRoot.Parented(this, "button", buttonModelDict, false) },
                { buttonName + "_pressed", ModelsRoot.Parented(this, "button_pressed", buttonModelDict, false) },
                { buttonName + "_inventory", ModelsRoot.Parented(this, "button_inventory", buttonModelDict, false) },
            }, itemModel: new ItemModel(texButtonName + "_inventory"));
        }
        public void CreateLog(string blockName) => CreateBlock(blockName, FuncBlockTex(blockName + "_top"), type: BlockType.RotatedPillar);
        public void CreateWood(string blockName)
        {
            string texBlockName = FuncBlockTex(blockName.Replace("_wood", "_log"));
            CreateBlock(blockName, texBlockNameEnd: texBlockName, texBlockNameNew: texBlockName, type: BlockType.RotatedPillarFull);
        }
        public void CreateCustom(string name, string particleName, string texNameNew = null, bool createModel = true)
        {
            string texName = (string.IsNullOrEmpty(texNameNew)) ? FuncBlockTex(name) : texNameNew;

            FileBlockstate(name, new Dictionary<string, VariantModel>() {
                { "", new(texName) }
            });

            if (createModel)
                FileModel(name, new() {
                    { name, ModelsRoot.Parented(this, null, new() { { "particle", FuncBlockTex(particleName) } }, false) }
                }, ModelsRoot.ClassicItemRoot(FuncItemTex(name)), true);
        }

        public void FamilyBlock(string blockName, bool removeLastSOnStringAdding = true, string slabName = null, string stairsName = null, string wallName = null, string texBlockName = null, bool createWall = true, bool createStairs = true)
        {
            slabName = NullToAdd(slabName, AddToName(blockName, "_slab", removeLastSOnStringAdding));
            stairsName = NullToAdd(stairsName, AddToName(blockName, "_stairs", removeLastSOnStringAdding));
            wallName = NullToAdd(wallName, AddToName(blockName, "_wall", removeLastSOnStringAdding));

            CreateBlock(blockName, texBlockNameNew: texBlockName);
            CreateSlab(blockName, slabName, texBlockNameNew: texBlockName);
            if (createStairs) CreateStairs(blockName, stairsName, texBlockNameNew: texBlockName);
            if (createWall) CreateWall(blockName, wallName, texBlockName);
        }
        public void FamilyEndSideBlock(string blockName, bool removeLastSOnStringAdding = true, string slabName = null, string stairsName = null, string wallName = null, string texBlockNameEnd = null, bool createWall = true, bool createStairs = true)
        {
            slabName = NullToAdd(slabName, AddToName(blockName, "_slab", removeLastSOnStringAdding));
            stairsName = NullToAdd(stairsName, AddToName(blockName, "_stairs", removeLastSOnStringAdding));
            wallName = NullToAdd(wallName, AddToName(blockName, "_wall", removeLastSOnStringAdding));

            texBlockNameEnd = NullToAdd(texBlockNameEnd, FuncBlockTex(AddToName(blockName, "_top", false)));

            CreateBlock(blockName, texBlockNameEnd);
            CreateSlab(blockName, slabName, texBlockNameEnd, texBlockNameEnd);
            if (createStairs) CreateStairs(blockName, stairsName, texBlockNameEnd, texBlockNameEnd);
            if (createWall) CreateWall(blockName, wallName);
        }
        public void FamilyTopBottomBlock(string blockName, bool removeLastSOnStringAdding = true, string slabName = null, string stairsName = null, string wallName = null, string texBlockNameTop = null, string texBlockNameBottom = null, bool createWall = true, bool createStairs = true)
        {
            slabName = NullToAdd(slabName, AddToName(blockName, "_slab", removeLastSOnStringAdding));
            stairsName = NullToAdd(stairsName, AddToName(blockName, "_stairs", removeLastSOnStringAdding));
            wallName = NullToAdd(wallName, AddToName(blockName, "_wall", removeLastSOnStringAdding));

            texBlockNameTop = NullToAdd(texBlockNameTop, FuncBlockTex(AddToName(blockName, "_top", false)));
            texBlockNameBottom = NullToAdd(texBlockNameBottom, FuncBlockTex(AddToName(blockName, "_bottom", false)));

            CreateBlock(blockName, texBlockNameTop, texBlockNameBottom);
            CreateSlab(blockName, slabName, texBlockNameTop, texBlockNameBottom);
            if (createStairs) CreateStairs(blockName, stairsName, texBlockNameTop, texBlockNameBottom);
            if (createWall) CreateWall(blockName, wallName);
        }
        public void FamilySign(string typeName, string particleName)
        {
            string sign = $"{typeName}_sign";
            string hangingSign = $"{typeName}_hanging_sign";

            CreateCustom(sign, particleName);
            CreateCustom($"{typeName}_wall_sign", particleName, FuncBlockTex(sign), false);
            CreateCustom(hangingSign, particleName);
            CreateCustom($"{typeName}_wall_hanging_sign", particleName, FuncBlockTex(hangingSign), false);
        }

        public void FamilyWoodType(string woodTypeName)
        {
            string plank = $"{woodTypeName}_planks";
            string texPlank = FuncBlockTex(plank);

            CreateLog($"{woodTypeName}_log");
            CreateWood($"{woodTypeName}_wood");
            CreateLog($"stripped_{woodTypeName}_log");
            CreateWood($"{woodTypeName}_wood");
            FamilyBlock(plank, slabName: $"{woodTypeName}_slab", stairsName: $"{woodTypeName}_stairs", createWall: false);
            CreateBlock($"{woodTypeName}_leaves", cutout: true);
            CreateFence($"{woodTypeName}_fence", texPlank);
            CreateFenceGate($"{woodTypeName}_fence_gate", texPlank);
            CreateDoor($"{woodTypeName}_door");
            CreateButton($"{woodTypeName}_button", texPlank);
            FamilySign(woodTypeName, plank);
            CreateItem($"{woodTypeName}_boat");
            CreateItem($"{woodTypeName}_chest_boat");
        }

        public enum BlockType
        {
            Normal,
            RotatedPillar,
            RotatedPillarFull
        }
    }
}
