package net.rodmjorgeh.thriver.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.rodmjorgeh.thriver.world.area.block.entity.BlockEntityRegistry;

public class BlockEntityRendererRegistry {

    public static void registerAll() {
        BlockEntityRenderers.register(BlockEntityRegistry.COIR_MAT.get(), CoirMatRenderer::new);
    }
}
