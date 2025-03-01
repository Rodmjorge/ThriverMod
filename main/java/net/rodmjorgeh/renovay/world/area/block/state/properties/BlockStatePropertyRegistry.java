package net.rodmjorgeh.renovay.world.area.block.state.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class BlockStatePropertyRegistry {

    public static final EnumProperty<TripleBlockStep> TRIPLE_BLOCK_STEP = EnumProperty.create("step", TripleBlockStep.class);
    public static final BooleanProperty SILTY = BooleanProperty.create("silty");
}
