package net.rodmjorgeh.thriver.client.renderer;

import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.rodmjorgeh.thriver.client.model.ModelLayersThr;
import net.rodmjorgeh.thriver.world.area.entity.EntityReg;

public class EntityRendererReg {

    public static void registerAll() {
        EntityRenderers.register(EntityReg.PALM_BOAT.get(), (x) -> new BoatRenderer(x, ModelLayersThr.PALM_BOAT));
        EntityRenderers.register(EntityReg.PALM_CHEST_BOAT.get(), (x) -> new BoatRenderer(x, ModelLayersThr.PALM_CHEST_BOAT));
    }
}
