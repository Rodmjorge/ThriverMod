package net.rodmjorgeh.thriver.world.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;

public class FeaturesReg {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        MiscOverworldFeaturesReg.bootstrap(context);
        TreeFeaturesReg.bootstrap(context);
        VegetationFeaturesReg.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceMod.createId(name, Registries.CONFIGURED_FEATURE);
    }
}
