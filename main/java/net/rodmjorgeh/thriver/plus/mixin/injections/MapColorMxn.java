package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Arrays;

@Mixin(MapColor.class)
public class MapColorMxn {

    /**
     * Removes the maximum value of 64 for map colors and sets it to 128. Unfortunately, even though it's registered as
     * an {@code int}, I can't go higher than 255 since it then gets transformed into a {@code byte}. Not that you need
     * that many colors anyway.
     */
    @Shadow @Final @Mutable
    public static final MapColor[] MATERIAL_COLORS = Arrays.copyOf(MapColor.MATERIAL_COLORS, 128);

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 63))
    public int constantValue(int i) {
        return 127;
    }
}
