package net.rodmjorgeh.thriver.plus;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.item.DyeColorR;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Map;

@Mixin(EquipmentAssets.class)
public interface EquipmentAssetsP {

    /**
     * When you add a new llama carpet texture, the location is automatically assigned to the minecraft namespace,
     * regardless of the carpet itself not being from that namespace. This just shows how good the code in Minecraft is.
     */
    @Inject(method = "createId", at = @At("HEAD"), cancellable = true)
    private static void createId(String name, CallbackInfoReturnable<ResourceKey<EquipmentAsset>> cir) {
        if (name.endsWith("_carpet")) {

            String dyeName = name.replace("_carpet", "");
            if (Arrays.stream(DyeColor.values()).anyMatch(x -> x.getSerializedName().equals(dyeName) && DyeColorR.isCustomColor(x))) {
                cir.setReturnValue(ResourceKey.create(EquipmentAssets.ROOT_ID, ThriverMod.createLoc(name)));
            }
        }
    }
}
