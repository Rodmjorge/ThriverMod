package net.rodmjorgeh.thriver.world.worldgen.placement;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rodmjorgeh.thriver.world.worldgen.features.MiscOverworldFeaturesReg;

public class MiscOverworldPlacementsReg {
    public static final ResourceKey<PlacedFeature> OASIS_DESERT = PlacementsReg.register("oasis_desert");
    public static final ResourceKey<PlacedFeature> OASIS_BADLANDS = PlacementsReg.register("oasis_badlands");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> config = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, OASIS_DESERT, config.getOrThrow(MiscOverworldFeaturesReg.OASIS_DESERT),
                BiomeFilter.biome());
        PlacementUtils.register(context, OASIS_BADLANDS, config.getOrThrow(MiscOverworldFeaturesReg.OASIS_BADLANDS),
                BiomeFilter.biome());
    }
}
