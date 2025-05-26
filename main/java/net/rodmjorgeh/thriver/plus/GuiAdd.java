package net.rodmjorgeh.thriver.plus;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.rodmjorgeh.thriver.util.ResourceMod;

public interface GuiAdd {
    static final ResourceLocation CLOSING_OVERLAY_LOCATION = ResourceMod.createLoc("textures/misc/dolls_eyes_closing_overlay.png");
    static final ResourceLocation WHITE_PIXEL_LOCATION = ResourceLocation.withDefaultNamespace("textures/misc/white.png");

    void renderClosingOverlay(GuiGraphics graphics, int ticks);
}
