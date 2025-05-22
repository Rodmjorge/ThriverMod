package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.rodmjorgeh.thriver.world.area.block.PalmLeavesBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockStateProperties.class)
public class IBlockStateProperties {

    /**
     * Changes the maximum integer of {@code DISTANCE} to 12. This is used for the {@link PalmLeavesBlock}, since they
     * need a bigger number than the original 7. However, the normal {@link LeavesBlock} stay intact.
     */
    @Shadow @Final @Mutable
    private static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 12);
}
