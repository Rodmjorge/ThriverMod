package net.rodmjorgeh.renovay.world.worldgen.placement;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.rodmjorgeh.renovay.world.worldgen.features.MiscOverworldFeaturesRegistry;

public class MiscOverworldPlacementsRegistry {
    public static final ResourceKey<PlacedFeature> OASIS_DESERT = PlacementsRegistry.register("oasis_desert");
    public static final ResourceKey<PlacedFeature> OASIS_BADLANDS = PlacementsRegistry.register("oasis_badlands");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> config = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, OASIS_DESERT, config.getOrThrow(MiscOverworldFeaturesRegistry.OASIS_DESERT),
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        PlacementUtils.register(context, OASIS_BADLANDS, config.getOrThrow(MiscOverworldFeaturesRegistry.OASIS_BADLANDS),
                RarityFilter.onAverageOnceEvery(85),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
    }
}
