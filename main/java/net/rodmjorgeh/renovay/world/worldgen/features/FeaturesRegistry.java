package net.rodmjorgeh.renovay.world.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.rodmjorgeh.renovay.RenovayMod;

public class FeaturesRegistry {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        MiscOverworldFeaturesRegistry.bootstrap(context);
        TreeFeaturesRegistry.bootstrap(context);
        VegetationFeaturesRegistry.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return RenovayMod.createId(name, Registries.CONFIGURED_FEATURE);
    }
}
