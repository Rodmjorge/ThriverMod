package net.rodmjorgeh.thriver.world.worldgen.placement;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.worldgen.features.TreeFeaturesRegistry;

public class TreePlacementsRegistry {
    public static final ResourceKey<PlacedFeature> PALM_TREE = PlacementsRegistry.register("palm_tree");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> config = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, PALM_TREE, config.getOrThrow(TreeFeaturesRegistry.PALM_TREE),
                PlacementUtils.filteredByBlockSurvival(BlockRegistry.PALM_SPROUT.get()));
    }
}
