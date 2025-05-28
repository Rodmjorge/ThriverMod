package net.rodmjorgeh.thriver.client.renderer;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import net.minecraft.client.renderer.RenderPipelines;

public class RenderPipelineReg {

    /**
     * Creates a render pipeline for the closing eye overlay when inside a Doll's Eyes. It basically inverts a mask,
     * where the white part of the texture is transparent and the black part is coated in some color (which ends up just
     * being black anyway).
     *
     * <p>Essentially how this works is that it's tricking you into thinking there's actually an image overlay, but
     * the reality is that the screen is being warped into an oval instead. The black you see is from nothing being
     * rendered as opposed to an image on top.</p>
     */
    public static final RenderPipeline CLOSING_OVERLAY = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
                    .withLocation("pipeline/closing_overlay")
                    .withBlend(new BlendFunction(SourceFactor.DST_COLOR, DestFactor.ZERO))
                    .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
                    .withDepthWrite(false)
                    .build()
    );
}
