package net.rodmjorgeh.thriver.world.area.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class PalmLeavesBlock extends UntintedParticleLeavesBlock {
    public static final MapCodec<UntintedParticleLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(
            x -> x.group(
                    ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(y -> ((PalmLeavesBlock)y).leafParticleChance),
                    ParticleTypes.CODEC.fieldOf("leaf_particle").forGetter(y -> ((PalmLeavesBlock)y).leafParticle),
                    propertiesCodec()
            ).apply(x, PalmLeavesBlock::new)
    );

    /**
     * Assigns a new {@code DECAY_DISTANCE} of 12 to accommodate the palm tree leaves.
     */
    public static final int DECAY_DISTANCE = 12;

    public PalmLeavesBlock(float leafParticleChance, ParticleOptions leafParticle, BlockBehaviour.Properties properties) {
        super(leafParticleChance, leafParticle, properties);
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(DISTANCE, Integer.valueOf(DECAY_DISTANCE))
                .setValue(PERSISTENT, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public MapCodec<UntintedParticleLeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean decaying(BlockState state) {
        return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == DECAY_DISTANCE;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return decaying(state);
    }
}
