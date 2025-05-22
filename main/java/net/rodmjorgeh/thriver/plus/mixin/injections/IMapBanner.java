package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.saveddata.maps.MapBanner;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.rodmjorgeh.thriver.world.area.maps.MapDecorationTypeRegistry;
import net.rodmjorgeh.thriver.world.item.DyeColorR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapBanner.class)
public class IMapBanner {

    @Inject(method = "getDecoration", at = @At("HEAD"), cancellable = true)
    public void getDecoration(CallbackInfoReturnable<Holder<MapDecorationType>> cir) {
        MapBanner instance = (MapBanner)(Object)this;
        DyeColor color = instance.color();

        if (DyeColorR.isCustomColor(color)) {
            if (color.equals(DyeColorR.BEIGE)) { cir.setReturnValue(MapDecorationTypeRegistry.BEIGE_BANNER); }
        }
    }
}
