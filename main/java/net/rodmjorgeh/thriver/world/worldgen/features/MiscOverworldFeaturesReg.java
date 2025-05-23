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
import net.rodmjorgeh.thriver.world.area.areagen.feature.FeatureReg;
import net.rodmjorgeh.thriver.world.area.areagen.feature.configurations.OasisFeatureConfiguration;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.worldgen.placement.TreePlacementsReg;

public class MiscOverworldFeaturesReg {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_DESERT = FeaturesReg.register("oasis_desert");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_BADLANDS = FeaturesReg.register("oasis_badlands");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(context, OASIS_DESERT, FeatureReg.OASIS.get(),
                new OasisFeatureConfiguration(
                        BlockStateProvider.simple(Blocks.SAND.defaultBlockState()),
                        BlockStateProvider.simple(BlockReg.SILT_MUD.get().defaultBlockState()),
                        HolderSet.direct(placed.getOrThrow(TreePlacementsReg.PALM_TREE))
                ));
        FeatureUtils.register(context, OASIS_BADLANDS, FeatureReg.OASIS.get(),
                new OasisFeatureConfiguration(
                        BlockStateProvider.simple(Blocks.RED_SAND.defaultBlockState()),
                        BlockStateProvider.simple(Blocks.MUD.defaultBlockState()),
                        HolderSet.direct(placed.getOrThrow(TreePlacementsReg.PALM_TREE))
                ));
    }
}
