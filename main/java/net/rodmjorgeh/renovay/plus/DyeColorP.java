package net.rodmjorgeh.renovay.plus;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import net.rodmjorgeh.renovay.world.area.maps.MapColorR;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

@Mixin(DyeColor.class)
public class DyeColorP {

    @Shadow @Final @Mutable
    public static DyeColor[] $VALUES;

    private static final DyeColor BEIGE = addDyeColor("BEIGE", 16,
            16, "beige", 14724731, MapColorR.COLOR_BEIGE, 14923919, 13412734);

    @Invoker("<init>")
    public static DyeColor dyeColor(String variableName, int variableId,
                                    int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor) {
        throw new AssertionError();
    }

    private static DyeColor addDyeColor(String variableName, int variableId,
                                        int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor) {
        ArrayList<DyeColor> arr = new ArrayList<>(Arrays.asList($VALUES));

        DyeColor dyeColor = dyeColor(variableName, variableId, id, name, textureDefuseColor, mapColor, fireworkColor, textColor);
        arr.add(dyeColor);
        $VALUES = arr.toArray(new DyeColor[0]);

        return dyeColor;
    }


    @Shadow @Final @Mutable
    private static final IntFunction<DyeColor> BY_ID = ByIdMap.continuous(DyeColor::getId, $VALUES, ByIdMap.OutOfBoundsStrategy.ZERO);

    @Shadow @Final @Mutable
    private static final Int2ObjectOpenHashMap<DyeColor> BY_FIREWORK_COLOR = new Int2ObjectOpenHashMap<>(
            Arrays.stream($VALUES).collect(Collectors.toMap(x -> x.getFireworkColor(), x -> (DyeColor)x))
    );

    @Shadow @Final @Mutable
    public static final StringRepresentable.EnumCodec<DyeColor> CODEC = StringRepresentable.fromEnum(DyeColor::values);
}
