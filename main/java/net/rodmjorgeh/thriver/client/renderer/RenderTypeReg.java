package net.rodmjorgeh.thriver.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.TriState;

import java.util.function.Function;

public class RenderTypeReg {

    public static final Function<ResourceLocation, RenderType> CLOSING_OVERLAY = Util.memoize(
            x -> RenderType.create(
                    "closing_display",
                    786432,
                    RenderPipelineReg.CLOSING_OVERLAY,
                    RenderType.CompositeState.builder()
                            .setTextureState(new RenderStateShard.TextureStateShard(x, TriState.TRUE, false))
                            .createCompositeState(false)
            )
    );

    public static RenderType closingOverlay(ResourceLocation location) {
        return CLOSING_OVERLAY.apply(location);
    }
}
