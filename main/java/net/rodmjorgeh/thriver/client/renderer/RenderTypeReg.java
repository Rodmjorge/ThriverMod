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
                    DefaultVertexFormat.POSITION_TEX_COLOR,
                    VertexFormat.Mode.QUADS,
                    786432,
                    RenderType.CompositeState.builder()
                            .setTextureState(new RenderStateShard.TextureStateShard(x, TriState.TRUE, false))
                            .setShaderState(RenderStateShard.POSITION_TEXTURE_COLOR_SHADER /*add your own shader here for masks*/)
                            .setTransparencyState(RenderStateReg.CLOSING_OVERLAY_TRANSPARENCY)
                            .setDepthTestState(RenderStateShard.NO_DEPTH_TEST)
                            .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                            .createCompositeState(false)
            )
    );

    public static RenderType closingOverlay(ResourceLocation location) {
        return CLOSING_OVERLAY.apply(location);
    }
}
