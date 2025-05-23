package net.rodmjorgeh.thriver.world.worldgen.features;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.rodmjorgeh.thriver.world.area.areagen.feature.decorators.CoconutDecorator;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.areagen.feature.placers.PalmTreeFoliagePlacer;
import net.rodmjorgeh.thriver.world.area.areagen.feature.placers.PalmTreeTrunkPlacer;

import java.util.List;

public class TreeFeaturesReg {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE = FeaturesReg.register("palm_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, PALM_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BlockReg.PALM_LOG.get()),
                        new PalmTreeTrunkPlacer(6, 1, 4),
                        BlockStateProvider.simple(BlockReg.PALM_LEAVES.get()),
                        new PalmTreeFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 3)
                ).decorators(List.of(new CoconutDecorator(0.95F))).build());
    }
}
