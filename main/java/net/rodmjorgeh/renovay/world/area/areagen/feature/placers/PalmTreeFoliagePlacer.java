package net.rodmjorgeh.renovay.world.area.areagen.feature.placers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmTreeFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmTreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            (p) -> foliagePlacerParts(p).apply(p, PalmTreeFoliagePlacer::new)
    );

    public PalmTreeFoliagePlacer(IntProvider i, IntProvider j) {
        super(i, j);
    }

    @Override
    protected FoliagePlacerType<?> type() { return FoliagePlacerRegistry.PALM_TREE_FOLIAGE_PLACER.get(); }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfig,
                                 int maxFreeTreeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos pos = foliageAttachment.pos();

        for (int i = 0; i < 3; i++) {
            this.placeLeavesRow(level, foliageSetter, random, treeConfig, pos, 1 + i, i, false);
        }

        this.placeLeavesRow(level, foliageSetter, random, treeConfig, pos, 4, 1, false);
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration treeConfig) { return 3; }

    /**
     * {@link FoliagePlacer}{@code .shouldSkipLocation()} is a method that, when returned {@code true}, it doesn't add
     * the leaves in that location ({@code x, y, z}). Here is just a bunch of math to make sure it correctly follows the
     * pattern of a palm tree. I did this at 2am and don't remember anything.
     */
    @Override
    protected boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int range, boolean isLarge) {
        if (y > 0) {
            if ((x > 1 || z > 1) && range == 2) {
                if (x == range && z == range) { return true; }

                return random.nextDouble() > 0.6;
            }
            else if (range == 4 && ((x <= 2 && z <= 2) || (x == 4 && z == 4) || (x >= 3 && z == 1) || (x >= 3 && z == 2) || (x == 1 && z >= 3) || (x == 2 && z >= 3))) {
                return true;
            }
        }

        return y > 1 && range == 3 && ((x == 1 && z == 3) || (x == 3 && z == 1) || (x == 1 && z == 0) || (x == 0 && z == 1) || (x == 0 && z == 0));
    }
}
