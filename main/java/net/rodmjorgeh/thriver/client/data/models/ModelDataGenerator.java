package net.rodmjorgeh.thriver.client.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;

public class ModelDataGenerator extends ModelProvider {

    public ModelDataGenerator(PackOutput output) {
        super(output, ThriverMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        new BlockModelDataGenerator(blockModels).register();
        new ItemModelDataGenerator(itemModels).register();
    }

    public static ResourceLocation useVanillaTextureWithNamespace(Block block, String suffix) {
        ResourceLocation blockLoc = BuiltInRegistries.BLOCK.getKey(block);
        return ResourceMod.createLoc(blockLoc.getPath() + suffix).withPrefix("block/");
    }

    public static ResourceLocation decorateItemModelLocation(String name) {
        return ResourceMod.createLoc(name).withPrefix("item/");
    }
}
