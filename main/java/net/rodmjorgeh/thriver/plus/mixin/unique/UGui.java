package net.rodmjorgeh.thriver.plus.mixin.unique;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.client.renderer.RenderTypeReg;
import net.rodmjorgeh.thriver.plus.EntityAdder;
import net.rodmjorgeh.thriver.plus.GuiAdder;
import net.rodmjorgeh.thriver.plus.GuiGraphicsAdder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class UGui implements GuiAdder {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderCameraOverlays", at = @At("HEAD"))
    public void renderCameraOverlays(GuiGraphics graphics, DeltaTracker tracker, CallbackInfo ci) {
        if (this.minecraft.options.getCameraType().isFirstPerson()) {
            Entity entity = this.minecraft.getCameraEntity();

            if (entity instanceof EntityAdder aEntity) {
                int i = aEntity.getTicksToBlind();
                if (i > 0) {
                    this.renderClosingOverlay(graphics, i);
                }
            }
        }
    }

    @Override
    public void renderClosingOverlay(GuiGraphics graphics, int ticks) {
        GuiGraphicsAdder aGraphics = (GuiGraphicsAdder)graphics;

        int col = ARGB.white(1.0F);
        int height = graphics.guiHeight();
        double heightMult = 0.5F + 1.5F / Math.sqrt(ticks);
        int textureHeight = (int)Math.ceil(height * heightMult);

        double d1 = Math.log(heightMult) / Math.log(2);
        double d2 = Math.abs(d1);
        double d3 = Math.pow(2, (d1 > 0) ? 1 : d2 + 1);
        double d4 = Math.pow(2, d2) - 1;
        double d5 = d4 / d3 * Math.signum(d1);
        double yOffset = d5 * -height;

        aGraphics.blitInfDilation(
                RenderTypeReg::closingOverlay,
                CLOSING_OVERLAY_LOCATION,
                0, (int)yOffset,
                0.0F, 0.0F,
                graphics.guiWidth(), textureHeight,
                graphics.guiWidth(), textureHeight,
                col
        );
    }
}
