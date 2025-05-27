package net.rodmjorgeh.thriver.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderStateShard;

public class RenderStateReg {

    /**
     * Creates a render state for the closing eye overlay when inside a Doll's Eyes. It basically inverts a mask, where
     * the white part of the texture is transparent and the black part is coated in some color (which ends up just
     * being black anyway).
     *
     * <p>Essentially how this works is that it's tricking you into thinking there's actually an image overlay, but
     * the reality is that the screen is being warped into an oval instead. The black you see is from nothing being
     * rendered as opposed to an image on top.</p>
     */
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
