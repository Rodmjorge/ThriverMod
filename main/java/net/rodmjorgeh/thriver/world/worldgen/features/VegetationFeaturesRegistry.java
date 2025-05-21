package net.rodmjorgeh.thriver.world.worldgen.features;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rodmjorgeh.thriver.world.worldgen.placement.TreePlacementsRegistry;

public class VegetationFeaturesRegistry {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_BEACH = FeaturesRegistry.register("trees_beach");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(context, TREES_BEACH, Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(placed.getOrThrow(TreePlacementsRegistry.PALM_TREE))));
    }
}
