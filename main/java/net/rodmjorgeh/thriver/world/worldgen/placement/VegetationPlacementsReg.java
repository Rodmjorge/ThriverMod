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
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.worldgen.features.VegetationFeaturesReg;

public class VegetationPlacementsReg {
    public static final ResourceKey<PlacedFeature> TREES_BEACH = PlacementsReg.register("trees_beach");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> config = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, TREES_BEACH, config.getOrThrow(VegetationFeaturesReg.TREES_BEACH),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(4), BlockReg.PALM_SPROUT.get()));
    }
}
