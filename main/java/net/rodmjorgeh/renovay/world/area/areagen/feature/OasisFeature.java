package net.rodmjorgeh.renovay.world.area.areagen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OasisFeature extends Feature<OasisFeature.Configuration> {

    public OasisFeature(Codec<OasisFeature.Configuration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OasisFeature.Configuration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        OasisFeature.Configuration config = context.config();

        if (origin.getY() <= level.getMinY() + 6) {
            return false;
        }

        origin = origin.below(6);
        BlockState originState = level.getBlockState(origin);

        /*if (!isValidBlocks(originState)) {
            return false;
        }*/

        final int maxWidth = 36;
        final int maxLength = 36;
        final int maxHeight = 8;
        boolean[] blocks = new boolean[maxWidth * maxLength * maxHeight];
        int iters = random.nextInt(3) + 2;
        int highestY = -1;

        List<BlockPos> treeGrowPos = new ArrayList<BlockPos>();
        for (int i = 0; i < iters; i++) {
            double widthRnd = random.nextDouble() * 5.0 + 3.0;
            double lengthRnd = random.nextDouble() * 5.0 + 5.0;
            double heightRnd = random.nextDouble() * 1.5 + 1.0;
            int newWidth = (int)(maxWidth - widthRnd) + 2;
            int newLength = (int)(maxLength - lengthRnd) + 4;
            int newHeight = (int)(maxHeight - heightRnd);

            for (int xBool = 0; xBool < newWidth; xBool++) {
                for (int zBool = 0; zBool < newLength; zBool++) {
                    for (int yBool = 0; yBool < newHeight; yBool++) {
                        double xCreate = (xBool - newWidth / 2.0) / (widthRnd * 1.5);
                        double yCreate = (yBool - newHeight) / (heightRnd * 2.0);
                        double zCreate = (zBool - newLength / 2.0) / (lengthRnd * 1.5);
                        double doesCreate =
                                Math.abs(Math.pow(xCreate, 3)) +
                                Math.pow(yCreate, 2) +
                                Math.abs(Math.pow(zCreate, 3));

                        if (doesCreate < 1.0) {
                            blocks[this.getPosIndex(xBool, yBool, zBool, maxWidth, maxHeight)] = true;
                        }
                    }

                    highestY = Math.max(highestY, newHeight);
                }
            }
        }

        for (int xPlace = 0; xPlace < maxWidth - 1; xPlace++) {
            for (int zPlace = 0; zPlace < maxLength - 1; zPlace++) {
                for (int yPlace = 0; yPlace < maxHeight - 1; yPlace++) {
                    if (blocks[this.getPosIndex(xPlace, yPlace, zPlace, maxWidth, maxHeight)]) {
                        BlockPos offset = origin.offset(xPlace, yPlace, zPlace);
                        Block placeBlock = (yPlace == highestY) ? Blocks.GRASS_BLOCK : Blocks.DIRT;

                        this.setBlock(level, offset, placeBlock.defaultBlockState());
                    }
                }
            }
        }

        return true;
    }

    private static boolean isValidBlocks(BlockState originState) {
        return originState.is(Blocks.SAND) || originState.is(Blocks.RED_SAND);
    }

    private static int getPosIndex(int x, int y, int z, int maxWidth, int maxHeight) {
        return (x * maxWidth + z) * maxHeight + y;
    }

    public static record Configuration(BlockStateProvider mud, Holder<PlacedFeature> treeFeature) implements FeatureConfiguration {
        public static final Codec<OasisFeature.Configuration> CODEC = RecordCodecBuilder.create(
                config -> config.group(
                                BlockStateProvider.CODEC.fieldOf("mud").forGetter(OasisFeature.Configuration::mud),
                                PlacedFeature.CODEC.fieldOf("tree").forGetter(OasisFeature.Configuration::treeFeature)
                        )
                        .apply(config, OasisFeature.Configuration::new)
        );
    }
}
