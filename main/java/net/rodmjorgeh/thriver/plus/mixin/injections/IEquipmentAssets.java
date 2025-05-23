package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(EquipmentAssets.class)
public interface IEquipmentAssets {

    /**
     * When you add a new llama carpet texture, the location is automatically assigned to the minecraft namespace,
     * regardless of the carpet itself not being from that namespace. This just shows how good the code in Minecraft is.
     */
    @Inject(method = "createId", at = @At("HEAD"), cancellable = true)
    private static void createId(String name, CallbackInfoReturnable<ResourceKey<EquipmentAsset>> cir) {
        if (name.endsWith("_carpet")) {

            String dyeName = name.replace("_carpet", "");
            if (Arrays.stream(DyeColor.values()).anyMatch(x -> x.getSerializedName().equals(dyeName) && DyeColorThr.isCustomColor(x))) {
                cir.setReturnValue(ResourceKey.create(EquipmentAssets.ROOT_ID, ThriverMod.createLoc(name)));
            }
        }
    }
}
