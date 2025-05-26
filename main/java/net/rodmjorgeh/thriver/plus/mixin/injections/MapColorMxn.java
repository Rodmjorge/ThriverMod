package net.rodmjorgeh.thriver.plus.mixin.injections;

import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MapColor.class)
public class MapColorMxn {

    /**
     * Removes the maximum value of 64 for map colors and sets it to 256. The good thing is that it's actually
     * registered as an integer, so I could have 2,147,483,647 map colors if I really wanted to.
     *
     * <p>Eh, I'll keep it simple.</p>
     */
    @Shadow @Final @Mutable
    private static final MapColor[] MATERIAL_COLORS = new MapColor[256];

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 63))
    public int constantValue(int i) {
        return MATERIAL_COLORS.length - 1;
    }
}
