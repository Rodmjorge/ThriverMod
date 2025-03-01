package net.rodmjorgeh.renovay.world.worldgen.placement;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rodmjorgeh.renovay.RenovayMod;

public class PlacementsRegistry {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        MiscOverworldPlacementsRegistry.bootstrap(context);
        TreePlacementsRegistry.bootstrap(context);
        VegetationPlacementsRegistry.bootstrap(context);
    }

    public static ResourceKey<PlacedFeature> register(String name) {
        return RenovayMod.createId(name, Registries.PLACED_FEATURE);
    }
}
