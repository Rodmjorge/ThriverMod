package net.rodmjorgeh.thriver.client.data.models;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.item.DyeColorR;

import java.util.Map;
import java.util.function.BiConsumer;

public class EquipmentAssetDataGenerator extends EquipmentAssetProvider {

    public EquipmentAssetDataGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        this.register(output);
    }

    public void register(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        //new llama carpets
        for (Map.Entry<DyeColor, ResourceKey<EquipmentAsset>> entry : EquipmentAssets.CARPETS.entrySet()) {
            DyeColor color = entry.getKey();

            if (DyeColorR.isCustomColor(color)) {
                ResourceKey key = ResourceKey.create(EquipmentAssets.ROOT_ID, ThriverMod.createLocFromKey(entry.getValue()));
                output.accept(key,
                        EquipmentClientInfo.builder()
                                .addLayers(EquipmentClientInfo.LayerType.LLAMA_BODY,
                                        new EquipmentClientInfo.Layer(ThriverMod.createLoc(color.getSerializedName()))
                                ).build()
                );
            }
        }
    }
}
