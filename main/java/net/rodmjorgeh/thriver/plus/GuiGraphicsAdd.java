package net.rodmjorgeh.thriver.plus;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public interface GuiGraphicsAdd {

    void blitInfDilation(Function<ResourceLocation, RenderType> renderTypeGetter, ResourceLocation atlasLocation,
                    int x, int y, float uOffset, float vOffset,
                    int uWidth, int vHeight, int textureWidth, int textureHeight,
                    int color);

    void blitInfDilation(Function<ResourceLocation, RenderType> renderTypeGetter, ResourceLocation atlasLocation,
                    int x, int y, float uOffset, float vOffset,
                    int uWidth, int vHeight, int width, int height, int textureWidth, int textureHeight,
                    int color);
}
