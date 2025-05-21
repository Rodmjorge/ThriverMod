package net.rodmjorgeh.thriver.client.renderer;

import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.rodmjorgeh.thriver.client.model.ModelLayersR;
import net.rodmjorgeh.thriver.world.area.entity.EntityRegistry;

public class EntityRendererRegistry {

    public static void registerAll() {
        EntityRenderers.register(EntityRegistry.PALM_BOAT.get(), (x) -> new BoatRenderer(x, ModelLayersR.PALM_BOAT));
        EntityRenderers.register(EntityRegistry.PALM_CHEST_BOAT.get(), (x) -> new BoatRenderer(x, ModelLayersR.PALM_CHEST_BOAT));
    }
}
