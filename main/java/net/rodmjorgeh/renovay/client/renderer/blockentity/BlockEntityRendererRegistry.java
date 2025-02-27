package net.rodmjorgeh.renovay.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.rodmjorgeh.renovay.world.area.block.entity.BlockEntityRegistry;

public class BlockEntityRendererRegistry {

    public static void registerAll() {
        BlockEntityRenderers.register(BlockEntityRegistry.COIR_MAT.get(), CoirMatRenderer::new);
    }
}
