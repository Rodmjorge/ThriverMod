package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.world.item.DyeColorThr;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(BuiltInLootTables.class)
public abstract class BuiltInLootTablesMxn {

    /**
     * Sets the loot tables that contain my dye colors to go to the thriver folder instead of the minecraft one.
     */
    @Inject(method = "makeDyeKeyMap", at = @At("HEAD"), cancellable = true)
    private static void makeDyeKeyMap(String path, CallbackInfoReturnable<Map<DyeColor, ResourceKey<LootTable>>> cir) {
        cir.setReturnValue(Util.makeEnumMap(DyeColor.class, (x -> {
            String loc = path + "/" + x.getName();

            return DyeColorThr.isCustomColor(x)
                    ? BuiltInLootTables.register(ResourceKey.create(Registries.LOOT_TABLE, ResourceMod.createLoc(loc)))
                    : BuiltInLootTables.register(loc);
        })));
    }
}
