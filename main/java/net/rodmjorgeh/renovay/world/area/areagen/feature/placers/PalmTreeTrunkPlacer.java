package net.rodmjorgeh.renovay.world.area.areagen.feature.placers;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class PalmTreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<PalmTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            (p) -> trunkPlacerParts(p).apply(p, PalmTreeTrunkPlacer::new)
    );

    public PalmTreeTrunkPlacer(int baseHeight, int heightRandomA, int heightRandomB) {
        super(baseHeight, heightRandomA, heightRandomB);
    }

    @Override
    protected TrunkPlacerType<?> type() { return TrunkPlacerRegistry.PALM_TREE_TRUNK_PLACER.get(); }

    /**
     * Method that places the log structure, simple as that. The parameter {@code pos} is the position of the sapling
     * block.
     * <p>Inside the function, the variable {@code k} tells the amount of blocks the palm tree will shift along a
     * random direction, given by {@code randomDir}, and the shifting is stored in {@code shiftX, shiftZ}.
     */
    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter,
                                                            RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration treeConfig) {
        List<FoliagePlacer.FoliageAttachment> list = new ArrayList<>();

        Direction randomDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();

        int minHeightToShift = 3 - random.nextInt(2);
        int shiftX = 0;
        int shiftZ = 0;
        int k = random.nextBoolean() ? 2 : 1;
        int seperateI = 0;

        for (int i = 0; i < freeTreeHeight; i++) {
            if (seperateI > minHeightToShift && k > 0) {
                shiftX += randomDir.getStepX();
                shiftZ += randomDir.getStepZ();

                k--;
                seperateI -= random.nextInt(2, 3);
            }

            BlockPos addedPos = new BlockPos(posX + shiftX, posY + i, posZ + shiftZ);
            if (!TreeFeature.isAirOrLeaves(level, addedPos)) {
                continue;
            }

            boolean lastItem = (i + 1 >= freeTreeHeight);
            if (i == 0 || lastItem) {
                ArrayList<BlockPos> logBase = Lists.newArrayList(addedPos.east(), addedPos.north(), addedPos.south(), addedPos.west());
                if (random.nextDouble() > 0.2 * (lastItem ? 2.5 : 1)) {
                    logBase.remove(random.nextInt(logBase.size()));
                }

                for (int j = 0; j < logBase.size(); j++) {
                    this.placeLog(level, blockSetter, random, logBase.get(j), treeConfig);
                }

                if (lastItem) {
                    list.add(new FoliagePlacer.FoliageAttachment(addedPos, 0, false));
                }
            }

            this.placeLog(level, blockSetter, random, addedPos, treeConfig);
            seperateI++;
        }

        return list;
    }
}
