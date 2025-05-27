package net.rodmjorgeh.thriver.plus.mixin.injections;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public class FogRendererMxn {

    @Unique private static int FOG_CHANGE_TICK = 10;

    @Inject(method = "computeFogColor", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void computeFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount,
                                        CallbackInfoReturnable<Vector4f> cir, FogType fogType, Entity entity, float r, float g, float b) {
        if (entity instanceof EntityAdd aEntity) {
            int i = aEntity.getTicksToBlind();
            if (i > 0) {
                float fogColorMax = (FOG_CHANGE_TICK - i) / (float)FOG_CHANGE_TICK;
                r = r * Math.max(0.0F, fogColorMax);
                g = g * Math.max(0.0F, fogColorMax);
                b = b * Math.max(0.0F, fogColorMax);

                cir.setReturnValue(ClientHooks.getFogColor(camera, partialTick, level, renderDistance, darkenWorldAmount, r, g, b));
            }
        }
    }

    @Inject(method = "setupFog", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, Vector4f fogColor, float renderDistance, boolean isFoggy, float partialTick,
                                 CallbackInfoReturnable<FogParameters> cir, FogType fogType, Entity entity, FogRenderer.FogData fogData) {
        if (entity instanceof EntityAdd aEntity) {
            int i = aEntity.getTicksToBlind();
            if (i > 0) {

                final double minEnd = 5.0F; // The fog end for the blindness effect
                final double maxTick = DollsEyesBlock.TICKS_TO_BLIND;
                final double distance = fogData.end;

                /* Special thanks to Desmos (yes, the online calculator) for graph visualization, so I can see what
                   equations need to be done in order for this to be smooth.

                   You can check the 2 equations from "end" and "end1" by replacing distance for the average render
                   distance, which gives a value of 192, and maxTick for 100. Then, have x = i.*/
                double end = (distance * (maxTick / 100.0F)) / Math.pow(i, 0.35F);
                double end1 = (Math.pow(maxTick - i, 2.0F) * distance) / (200.0F * maxTick) + minEnd;
                double end2 = Mth.lerp((float)i / FOG_CHANGE_TICK, end, end1);
                double end3 = (i > FOG_CHANGE_TICK) ? end1 : end2;

                fogData.start = 0.0F;
                fogData.end = (float)Math.max(end3, minEnd);
                fogData.shape = FogShape.SPHERE;

                cir.setReturnValue(ClientHooks.onFogRender(fogMode, fogType, camera, partialTick, renderDistance,
                        new FogParameters(fogData.start, fogData.end, fogData.shape, fogColor.x, fogColor.y, fogColor.z, fogColor.w)));
            }
        }
    }
}
