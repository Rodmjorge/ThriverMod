package net.rodmjorgeh.thriver.plus.mixin.injections;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Mixin(Sheets.class)
public class SheetsMxn {

    /**
     * All this mixin class is doing is making sure that when any colored block uses one of my custom colors, it goes
     * to the correct namespace path, and not {@code :minecraft}.
     */

    @Shadow @Final @Mutable
    public static final Material[] BED_TEXTURES = Arrays.stream(DyeColor.values())
            .sorted(Comparator.comparingInt(DyeColor::getId))
            .map(x -> new Material(Sheets.BED_SHEET, DyeColorThr.isCustomColor(x)
                    ? ResourceMod.createLoc("entity/bed/" + x.getName())
                    : ResourceLocation.withDefaultNamespace("entity/bed/" + x.getName())))
            .toArray(Material[]::new);

    @Shadow @Final @Mutable
    public static final List<Material> SHULKER_TEXTURE_LOCATION = Arrays.stream(DyeColor.values())
            .sorted(Comparator.comparingInt(DyeColor::getId))
            .map(x -> new Material(Sheets.SHULKER_SHEET, DyeColorThr.isCustomColor(x)
                    ? ResourceMod.createLoc("entity/shulker/shulker_" + x.getName())
                    : ResourceLocation.withDefaultNamespace("entity/shulker/shulker_" + x.getName())))
            .collect(ImmutableList.toImmutableList());

    @Inject(method = "colorToResourceMaterial", at = @At("HEAD"), cancellable = true)
    private static void colorToResourceMaterial(DyeColor color, CallbackInfoReturnable<ResourceLocation> cir) {
        if (DyeColorThr.isCustomColor(color)) {
            cir.setReturnValue(ResourceMod.createLoc(color.getName()));
        }
    }

    @Inject(method = "colorToShulkerMaterial", at = @At("HEAD"), cancellable = true)
    private static void colorToShulkerMaterial(DyeColor color, CallbackInfoReturnable<ResourceLocation> cir) {
        if (DyeColorThr.isCustomColor(color)) {
            cir.setReturnValue(ResourceMod.createLoc("shulker_" + color.getName()));
        }
    }
}
