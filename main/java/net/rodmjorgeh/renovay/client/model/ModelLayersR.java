package net.rodmjorgeh.renovay.client.model;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.rodmjorgeh.renovay.RenovayMod;

@OnlyIn(Dist.CLIENT)
public class ModelLayersR {

    public static final ModelLayerLocation PALM_BOAT = register("boat/palm");
    public static final ModelLayerLocation PALM_CHEST_BOAT = register("chest_boat/palm");

    private static ModelLayerLocation register(String path) {
        ModelLayerLocation mll = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RenovayMod.MOD_ID, path), "main");
        ModelLayers.ALL_MODELS.add(mll);

        return mll;
    }

    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PALM_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(PALM_CHEST_BOAT, BoatModel::createChestBoatModel);
    }
}
