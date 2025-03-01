package net.rodmjorgeh.renovay.world.worldgen.features;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rodmjorgeh.renovay.world.area.areagen.feature.FeatureRegistry;
import net.rodmjorgeh.renovay.world.area.areagen.feature.OasisFeature;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.worldgen.placement.TreePlacementsRegistry;

public class MiscOverworldFeaturesRegistry {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_DESERT = FeaturesRegistry.register("oasis_desert");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_BADLANDS = FeaturesRegistry.register("oasis_badlands");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(context, OASIS_DESERT, FeatureRegistry.OASIS.get(),
                new OasisFeature.Configuration(
                        BlockStateProvider.simple(BlockRegistry.SILT_MUD.get().defaultBlockState()),
                        placed.getOrThrow(TreePlacementsRegistry.PALM_TREE)
                ));
        FeatureUtils.register(context, OASIS_BADLANDS, FeatureRegistry.OASIS.get(),
                new OasisFeature.Configuration(
                        BlockStateProvider.simple(Blocks.MUD.defaultBlockState()),
                        placed.getOrThrow(TreePlacementsRegistry.PALM_TREE)
                ));
    }
}
