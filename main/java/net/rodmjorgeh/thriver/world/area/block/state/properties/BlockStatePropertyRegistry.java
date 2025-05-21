package net.rodmjorgeh.thriver.world.area.block.state.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class BlockStatePropertyRegistry {

    public static final BooleanProperty SILTY = BooleanProperty.create("silty");
    public static final EnumProperty<TripleBlockStep> TRIPLE_BLOCK_STEP = EnumProperty.create("step", TripleBlockStep.class);
}
