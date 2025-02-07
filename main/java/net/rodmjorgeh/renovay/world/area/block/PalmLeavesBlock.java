package net.rodmjorgeh.renovay.world.area.block;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.rodmjorgeh.renovay.RenovayMod;

public class PalmLeavesBlock extends LeavesBlock {

    // assigns new decay distance of 12 to accommodate the palm tree leaves
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
    protected boolean decaying(BlockState state) {
        return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == DECAY_DISTANCE;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return decaying(state);
    }
}
