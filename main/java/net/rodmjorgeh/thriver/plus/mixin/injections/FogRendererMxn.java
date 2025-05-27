package net.rodmjorgeh.thriver.plus.mixin.injections;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import net.neoforged.neoforge.client.ClientHooks;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.plus.EntityAdd;
import net.rodmjorgeh.thriver.world.area.block.DollsEyesBlock;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public class FogRendererMxn {

    @Inject(method = "setupFog", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, Vector4f fogColor, float renderDistance, boolean isFoggy, float partialTick,
                                 CallbackInfoReturnable<FogParameters> cir, FogType fogType, Entity entity, FogRenderer.FogData fogData) {
        if (entity instanceof EntityAdd aEntity) {
            int i = aEntity.getTicksToBlind();
            if (aEntity.getIsInDollsEyes() && i > 0) {
                final int j = 10;
                final double maxTick = DollsEyesBlock.TICKS_TO_BLIND;

                ThriverMod.LOGGER.debug("Starting End: " + fogData.end);
                ThriverMod.LOGGER.debug("Tick: " + i);
                final double distance = fogData.end;

                double end = (distance * (maxTick / 100.0F)) / Math.pow(i, 0.35F);
                double end1 = (Math.pow(maxTick - i, 2.0F) * distance) / (200.0F * maxTick) + 5.0F;
                double end2 = Mth.lerp(i / maxTick, end, end1);
                double end3 = (i > j) ? end1 : end2;

                fogData.start = 0.0F;
                fogData.end = (float)Math.max(end3, 5.0F);
                fogData.shape = FogShape.SPHERE;

                float fogColorMax = (j - i) / (float)j;
                float r = fogColor.x * Math.max(0.0F, fogColorMax);
                float g = fogColor.y * Math.max(0.0F, fogColorMax);
                float b = fogColor.z * Math.max(0.0F, fogColorMax);
                float w = fogColor.w;

                ThriverMod.LOGGER.debug("Start: " + fogData.start);
                ThriverMod.LOGGER.debug("End: " + fogData.end);

                cir.setReturnValue(ClientHooks.onFogRender(fogMode, fogType, camera, partialTick, renderDistance,
                        new FogParameters(fogData.start, fogData.end, fogData.shape, r, g, b, w)));
            }
        }
    }
}
