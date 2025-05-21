package net.rodmjorgeh.thriver.world.area.areagen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.rodmjorgeh.thriver.world.area.areagen.feature.configurations.OasisFeatureConfiguration;
import org.joml.Vector2L;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OasisFeature extends Feature<OasisFeatureConfiguration> {

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

        final int maxWidth = 16;
        final int maxLength = 16;
        final int maxHeight = 8;
        boolean[] blocks = new boolean[maxWidth * maxLength * maxHeight];
        int iters = random.nextInt(3) + 2;

        int highestX = -1;
        int highestY = -1;
        int highestZ = -1;

        for (int i = 0; i < iters; i++) {
            double widthRnd = random.nextDouble() * 3.0 + 2.0;
            double lengthRnd = random.nextDouble() * 2.5 + 1.5;
            double heightRnd = random.nextDouble() * 1.5 + 1.0;
            int newWidth = (int)(maxWidth - widthRnd) + 1;
            int newLength = (int)(maxLength - lengthRnd) + 2;
            int newHeight = (int)(maxHeight - heightRnd);

            for (int xBool = 0; xBool < newWidth; xBool++) {
                for (int zBool = 0; zBool < newLength; zBool++) {
                    for (int yBool = 0; yBool < newHeight; yBool++) {
                        double xCreate = (xBool - newWidth / 2.0) / (widthRnd * 1.5);
                        double yCreate = (yBool - newHeight) / (heightRnd * 1.5);
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

            highestX = Math.max(highestX, newWidth);
            highestY = Math.max(highestY, newHeight);
            highestZ = Math.max(highestZ, newLength);
        }

        origin = origin.below(highestY - 1);

        BlockPos featureCenter = origin.offset(highestX / 2, 0, highestZ / 2);
        List<BlockPos> treeSpawnPos = this.posOutsideOfArea(random, featureCenter, 7);

        for (int xPlace = 0; xPlace < maxWidth - 1; xPlace++) {
            for (int zPlace = 0; zPlace < maxLength - 1; zPlace++) {
                for (int yPlace = 0; yPlace < maxHeight - 1; yPlace++) {
                    boolean highestPoint = (yPlace + 1 == highestY);

                    if (blocks[this.getPosIndex(xPlace, yPlace, zPlace, maxWidth, maxHeight)]) {
                        BlockPos offset = origin.offset(xPlace, yPlace, zPlace);
                        Block placeBlock = highestPoint ? Blocks.GRASS_BLOCK : Blocks.DIRT;
                        this.setBlock(level, offset, placeBlock.defaultBlockState());

                        if (highestPoint && treeSpawnPos.stream().anyMatch(
                                tsp -> tsp.getX() == offset.getX() && tsp.getZ() == offset.getZ()
                        )) {
                            int i = random.nextInt(config.treeFeature.size());
                            config.treeFeature.get(i).value().place(level, context.chunkGenerator(), random, offset.above());
                        }
                    }
                }
            }
        }

        return true;
    }

    private List<BlockPos> posOutsideOfArea(RandomSource random, BlockPos center, int radius) {
        int numPos = random.nextInt(3) + 5;
        List<BlockPos> list = new ArrayList<BlockPos>();
        final int minDist = 2;

        int i = 0;
        while (list.size() < numPos) {
            double angleRnd = random.nextDouble() * 2 * Mth.PI;
            double dist = radius + random.nextInt(minDist, 6);
            BlockPos pos = new BlockPos(
                    (int)(center.getX() + dist * Math.cos(angleRnd)),
                    center.getY(),
                    (int)(center.getZ() + dist * Math.sin(angleRnd)));

            Stream<BlockPos> stream = list.stream();
            if (stream.noneMatch(bp -> distance(pos, bp) < Math.pow(minDist, 2))) {
                list.add(pos);
            }

            i++;
            if (i > (int)(numPos * (Math.pow(minDist, 2) / 1.5F))) {
                break;
            }
        }

        return list;
    }

    private static int getPosIndex(int x, int y, int z, int maxWidth, int maxHeight) {
        return (x * maxWidth + z) * maxHeight + y;
    }

    private static double distance(BlockPos pos, BlockPos pos2) {
        return Vector2L.distance(pos.getX(), pos.getZ(), pos2.getX(), pos2.getZ());
    }
}
