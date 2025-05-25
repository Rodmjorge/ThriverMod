package net.rodmjorgeh.thriver.world.worldgen.placement;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;

public class PlacementsReg {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        MiscOverworldPlacementsReg.bootstrap(context);
        TreePlacementsReg.bootstrap(context);
        VegetationPlacementsReg.bootstrap(context);
    }

    public static ResourceKey<PlacedFeature> register(String name) {
        return ResourceMod.createId(name, Registries.PLACED_FEATURE);
    }
}
