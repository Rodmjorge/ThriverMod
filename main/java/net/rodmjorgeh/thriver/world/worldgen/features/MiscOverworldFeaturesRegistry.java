package net.rodmjorgeh.thriver.world.worldgen.features;

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
import net.rodmjorgeh.thriver.world.area.areagen.feature.FeatureRegistry;
import net.rodmjorgeh.thriver.world.area.areagen.feature.configurations.OasisFeatureConfiguration;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.worldgen.placement.TreePlacementsRegistry;

public class MiscOverworldFeaturesRegistry {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_DESERT = FeaturesRegistry.register("oasis_desert");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_BADLANDS = FeaturesRegistry.register("oasis_badlands");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(context, OASIS_DESERT, FeatureRegistry.OASIS.get(),
                new OasisFeatureConfiguration(
                        BlockStateProvider.simple(Blocks.SAND.defaultBlockState()),
                        BlockStateProvider.simple(BlockRegistry.SILT_MUD.get().defaultBlockState()),
                        HolderSet.direct(placed.getOrThrow(TreePlacementsRegistry.PALM_TREE))
                ));
        FeatureUtils.register(context, OASIS_BADLANDS, FeatureRegistry.OASIS.get(),
                new OasisFeatureConfiguration(
                        BlockStateProvider.simple(Blocks.RED_SAND.defaultBlockState()),
                        BlockStateProvider.simple(Blocks.MUD.defaultBlockState()),
                        HolderSet.direct(placed.getOrThrow(TreePlacementsRegistry.PALM_TREE))
                ));
    }
}
