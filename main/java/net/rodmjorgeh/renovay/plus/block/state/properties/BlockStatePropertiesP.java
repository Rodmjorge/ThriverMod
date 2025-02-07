package net.rodmjorgeh.renovay.plus.block.state.properties;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockStateProperties.class)
public class BlockStatePropertiesP {

    /**
     * Changes the maximum integer of {@code DISTANCE} to 12. This is used for the {@code PalmLeavesBlock}, since they
     * need a bigger number than the original 7. However, the normal {@code LeavesBlock} stay intact.
     */
    @Shadow @Final @Mutable
    private static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 12);
}
