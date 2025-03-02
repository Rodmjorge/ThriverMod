package net.rodmjorgeh.renovay.world.area.areagen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;

public class OasisFeature extends BigFeature<OasisFeatureConfiguration> {

    public OasisFeature(Codec<OasisFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OasisFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        OasisFeatureConfiguration config = context.config();

        if (origin.getY() <= level.getMinY() + 6) {
            return false;
        }

        origin = origin.below(1);
        BlockState originState = level.getBlockState(origin);

        if (!config.isValidBlock(originState, random, origin)) {
            return false;
        }

        final int maxWidth = 32;
        final int maxLength = 32;
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
                }
            }

            highestY = Math.max(highestY, newHeight);
        }

        origin = origin.below(highestY - 1);

        for (int xPlace = 0; xPlace < maxWidth - 1; xPlace++) {
            for (int zPlace = 0; zPlace < maxLength - 1; zPlace++) {
                for (int yPlace = 0; yPlace < maxHeight - 1; yPlace++) {
                    if (blocks[this.getPosIndex(xPlace, yPlace, zPlace, maxWidth, maxHeight)]) {
                        BlockPos offset = origin.offset(xPlace, yPlace, zPlace);
                        Block placeBlock = (yPlace + 1 == highestY) ? Blocks.GRASS_BLOCK : Blocks.DIRT;
                        this.setBlock(level, offset, placeBlock.defaultBlockState());
                    }
                }
            }
        }

        return true;
    }

    private static int getPosIndex(int x, int y, int z, int maxWidth, int maxHeight) {
        return (x * maxWidth + z) * maxHeight + y;
    }
}
