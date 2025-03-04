package net.rodmjorgeh.renovay.client.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.rodmjorgeh.renovay.RenovayMod;

public class ModelDataGenerator extends ModelProvider {

    public ModelDataGenerator(PackOutput output) {
        super(output, RenovayMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        new BlockModelDataGenerator(blockModels).register();
        new ItemModelDataGenerator(itemModels).register();
    }

    public static ResourceLocation decorateItemModelLocation(String name) {
        return RenovayMod.createLoc(name).withPrefix("item/");
    }
}
