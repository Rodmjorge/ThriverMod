package net.rodmjorgeh.renovay.client.data.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;

public class ItemModelDataGenerator {

    private final ItemModelGenerators generator;

    public ItemModelDataGenerator(ItemModelGenerators generator) {
        this.generator = generator;
    }

    public void register() {
        this.generator.generateBundleModels(ItemRegistry.BEIGE_BUNDLE.get());
        this.generator.generateFlatItem(ItemRegistry.BEIGE_DYE.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COCONUT_BEETROOT_SOUP.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COCONUT_BOWL.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COCONUT_MILK.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COCONUT_MUSHROOM_STEW.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COCONUT_RABBIT_STEW.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COCONUT_SUSPICIOUS_STEW.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.COIR.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.PALM_BOAT.get(), ModelTemplates.FLAT_ITEM);
        this.generator.generateFlatItem(ItemRegistry.PALM_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
        this.generateReedFlute(ItemRegistry.REED_FLUTE.get(), "playing");
    }

    private void generateReedFlute(Item item, String adder) {
        String playingItemName = BuiltInRegistries.ITEM.getKey(item).getPath() + "_" + adder;

        ItemModel.Unbaked model = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item));
        ItemModel.Unbaked modelPlaying = ItemModelUtils.plainModel(ModelDataGenerator.decorateItemModelLocation(playingItemName));
        this.generator.generateBooleanDispatch(item, ItemModelUtils.isUsingItem(), modelPlaying, model);
    }
}
