package net.rodmjorgeh.renovay.world.area.levelgen.feature.placers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration treeConfig) { return 2; }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}
