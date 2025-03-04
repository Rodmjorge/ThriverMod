package net.rodmjorgeh.renovay.world.area.areagen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class OasisFeatureConfiguration implements FeatureConfiguration {

    public static final Codec<OasisFeatureConfiguration> CODEC = RecordCodecBuilder.create(
            config -> config.group(
                            BlockStateProvider.CODEC.fieldOf("sand").forGetter(x -> x.sandBlock),
                            BlockStateProvider.CODEC.fieldOf("mud").forGetter(x -> x.mudBlock),
                            PlacedFeature.LIST_CODEC.fieldOf("tree").forGetter(x -> x.treeFeature)
                    )
                    .apply(config, OasisFeatureConfiguration::new)
    );

    public final BlockStateProvider sandBlock;
    public final BlockStateProvider mudBlock;
    public final HolderSet<PlacedFeature> treeFeature;

    public OasisFeatureConfiguration(BlockStateProvider sandBlock,
                                     BlockStateProvider mudBlock,
                                     HolderSet<PlacedFeature> treeFeature
    ) {
        this.sandBlock = sandBlock;
        this.mudBlock = mudBlock;
        this.treeFeature = treeFeature;
    }

    public boolean isValidBlock(BlockState originState, RandomSource random, BlockPos pos) {
        return this.sandBlock.getState(random, pos).is(originState.getBlock());
    }
}
