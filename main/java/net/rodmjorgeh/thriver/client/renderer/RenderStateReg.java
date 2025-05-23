package net.rodmjorgeh.thriver.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderStateShard;

public class RenderStateReg {

    public static final RenderStateShard.TransparencyStateShard CLOSING_OVERLAY_TRANSPARENCY = new RenderStateShard.TransparencyStateShard(
            "closing_overlay_transparency", () -> {
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GlStateManager.SourceFactor.DST_COLOR, GlStateManager.DestFactor.ZERO);
        }, () -> {
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
        }
    );
}
