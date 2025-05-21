package net.rodmjorgeh.thriver.world.area.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class PalmLeavesBlock extends LeavesBlock {

    public static final MapCodec<PalmLeavesBlock> CODEC = simpleCodec(PalmLeavesBlock::new);
    /**
     * Assigns a new {@code DECAY_DISTANCE} of 12 to accommodate the palm tree leaves.
     */
    public static final int DECAY_DISTANCE = 12;

    public PalmLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(DISTANCE, Integer.valueOf(DECAY_DISTANCE))
                .setValue(PERSISTENT, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public MapCodec<? extends PalmLeavesBlock> codec() { return CODEC; }

    @Override
    protected boolean decaying(BlockState state) {
        return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == DECAY_DISTANCE;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return decaying(state);
    }
}
