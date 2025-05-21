package net.rodmjorgeh.thriver.plus;

import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MapColor.class)
public class MapColorP {

    @Shadow @Final @Mutable
    private static final MapColor[] MATERIAL_COLORS = new MapColor[256];

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 63))
    public int constantValue(int i) {
        return MATERIAL_COLORS.length - 1;
    }
}
