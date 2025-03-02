package net.rodmjorgeh.renovay.world.area.areagen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.rodmjorgeh.renovay.util.events.ChunkLoadEvent;

public abstract class BigFeature<FC extends FeatureConfiguration> extends Feature<FC> {

    public BigFeature(Codec<FC> codec) {
        super(codec);
    }

    @Override
    protected void setBlock(LevelWriter level, BlockPos pos, BlockState state) {
        ChunkPos chunk = new ChunkPos(pos);
        ChunkLoadEvent.add(chunk, pos, state);
    }
}
