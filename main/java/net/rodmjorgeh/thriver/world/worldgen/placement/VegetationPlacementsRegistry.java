package net.rodmjorgeh.thriver.world.worldgen.placement;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.worldgen.features.VegetationFeaturesRegistry;

public class VegetationPlacementsRegistry {
    public static final ResourceKey<PlacedFeature> TREES_BEACH = PlacementsRegistry.register("trees_beach");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> config = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, TREES_BEACH, config.getOrThrow(VegetationFeaturesRegistry.TREES_BEACH),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(4), BlockRegistry.PALM_SPROUT.get()));
    }
}
