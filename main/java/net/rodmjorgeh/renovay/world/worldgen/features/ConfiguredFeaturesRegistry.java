package net.rodmjorgeh.renovay.world.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.levelgen.feature.placers.PalmTreeFoliagePlacer;
import net.rodmjorgeh.renovay.world.area.levelgen.feature.placers.PalmTreeTrunkPlacer;

public class ConfiguredFeaturesRegistry {

    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE = register("palm_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, PALM_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BlockRegistry.PALM_LOG.get()),
                        new PalmTreeTrunkPlacer(8, 1, 4),
                        BlockStateProvider.simple(BlockRegistry.PALM_LEAVES.get()),
                        new PalmTreeFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 3)
                ).build());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return RenovayMod.createId(name, Registries.CONFIGURED_FEATURE);
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                      ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC featureConfig) {
        context.register(key, new ConfiguredFeature<>(feature, featureConfig));
    }
}
