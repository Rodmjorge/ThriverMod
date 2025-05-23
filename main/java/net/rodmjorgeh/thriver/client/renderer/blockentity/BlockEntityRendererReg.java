package net.rodmjorgeh.thriver.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.rodmjorgeh.thriver.world.area.block.entity.BlockEntityReg;

public class BlockEntityRendererReg {

    public static void registerAll() {
        BlockEntityRenderers.register(BlockEntityReg.COIR_MAT.get(), CoirMatRenderer::new);
    }
}
