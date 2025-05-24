package net.rodmjorgeh.thriver.plus.mixin.unique;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.rodmjorgeh.thriver.plus.GuiAdder;
import net.rodmjorgeh.thriver.plus.GuiGraphicsAdder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Function;

@Mixin(GuiGraphics.class)
public abstract class UGuiGraphics implements GuiGraphicsAdder {

    @Shadow protected abstract void innerBlit(Function<ResourceLocation, RenderType> renderTypeGetter, ResourceLocation atlasLocation,
                                              int x1, int x2, int y1, int y2,
                                              float minU, float maxU, float minV, float maxV,
                                              int color);

    /**
     * This method uses infinite dilation when drawing anything. If you don't know what that is, it's when the pixels at
     * the borders of the image stretch infinitely using that same pixel color. This is useful for the eye closing
     * animation for the Doll's Eyes plant because it can automatically fill the outside of the image with just pure
     * black.
     */
    @Override
    public void blitInfDilation(Function<ResourceLocation, RenderType> renderTypeGetter, ResourceLocation atlasLocation,
                           int x, int y, float uOffset, float vOffset,
                           int uWidth, int vHeight, int textureWidth, int textureHeight,
                           int color) {
        this.blitInfDilation(renderTypeGetter, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight, color);
    }

    @Override
    public void blitInfDilation(Function<ResourceLocation, RenderType> renderTypeGetter, ResourceLocation atlasLocation,
                           int x, int y, float uOffset, float vOffset,
                           int uWidth, int vHeight, int width, int height, int textureWidth, int textureHeight,
                           int color) {
        GuiGraphics graphics = (GuiGraphics)(Object)this;
        int guiWidth = graphics.guiWidth();
        int guiHeight = graphics.guiHeight();
        int blackCol = ARGB.colorFromFloat(1.0F, 0.0F, 0.0F, 0.0F);

        if (y > 0) {
            graphics.blit(
                    RenderType::guiTextured,
                    GuiAdder.WHITE_PIXEL_LOCATION,
                    0, 0,
                    0, 0,
                    guiWidth, y,
                    guiWidth, guiHeight,
                    blackCol);
        }
        if (vHeight + y < guiHeight) {
            graphics.blit(
                    RenderType::guiTextured,
                    GuiAdder.WHITE_PIXEL_LOCATION,
                    0, vHeight + y,
                    0, 0,
                    guiWidth, guiHeight - vHeight - y,
                    guiWidth, guiHeight,
                    blackCol
            );
        }

        this.innerBlit(renderTypeGetter, atlasLocation,
                x, x + uWidth, y, y + vHeight,
                uOffset / (float)textureWidth, (uOffset + (float)width) / (float)textureWidth,
                vOffset / (float)textureHeight, (vOffset + (float)height) / (float)textureHeight,
                color);
    }
}
