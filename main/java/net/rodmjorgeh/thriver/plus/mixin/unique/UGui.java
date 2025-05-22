package net.rodmjorgeh.thriver.plus.mixin.unique;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.plus.EntityAdder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Gui.class)
public class UGui {

    @Unique private static final ResourceLocation DOLLS_EYES_VIGNETTE_LOCATION = ThriverMod.createLoc("textures/misc/dolls_eyes_vignette.png");

    @Inject(method = "renderVignette", at = @At("TAIL"))
    public void renderVignette(GuiGraphics guiGraphics, Entity entity, CallbackInfo ci) {
        this.renderDollsEyeVignette(guiGraphics, entity);
    }

    @Unique
    private void renderDollsEyeVignette(GuiGraphics guiGraphics, Entity entity) {
        if (entity instanceof EntityAdder aEntity) {
            int i = aEntity.getTicksToBlind();
            float f = (i / 100F) * 0.6F;
            int j = ARGB.colorFromFloat(1.0F, f, f, f);

            guiGraphics.blit(
                    RenderType::vignette,
                    DOLLS_EYES_VIGNETTE_LOCATION,
                    0,
                    0,
                    0.0F,
                    0.0F,
                    guiGraphics.guiWidth(),
                    guiGraphics.guiHeight(),
                    guiGraphics.guiWidth(),
                    guiGraphics.guiHeight(),
                    j
            );
        }
    }
}
