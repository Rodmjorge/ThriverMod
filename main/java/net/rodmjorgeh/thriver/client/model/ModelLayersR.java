package net.rodmjorgeh.thriver.client.model;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.rodmjorgeh.thriver.ThriverMod;

@OnlyIn(Dist.CLIENT)
public class ModelLayersR {

    public static final ModelLayerLocation PALM_BOAT = register("boat/palm");
    public static final ModelLayerLocation PALM_CHEST_BOAT = register("chest_boat/palm");

    private static ModelLayerLocation register(String path) {
        ModelLayerLocation mll = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ThriverMod.MOD_ID, path), "main");
        ModelLayers.ALL_MODELS.add(mll);

        return mll;
    }

    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PALM_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(PALM_CHEST_BOAT, BoatModel::createChestBoatModel);
    }
}
