package net.rodmjorgeh.thriver.plus.mixin.enums;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import net.rodmjorgeh.thriver.world.area.maps.MapColorThr;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

@Mixin(DyeColor.class)
public class DyeColorMxn {

    @Shadow @Final @Mutable
    public static DyeColor[] $VALUES;

    @Unique private static final DyeColor BEIGE = addDyeColor("BEIGE",
            16, "beige", 14724731, MapColorThr.COLOR_BEIGE, 14923919, 13412734);

    @Invoker("<init>")
    public static DyeColor dyeColor(String variableName, int variableId,
                                    int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor) {
        throw new AssertionError();
    }

    private static DyeColor addDyeColor(String variableName,
                                        int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor) {
        ArrayList<DyeColor> arr = new ArrayList<>(Arrays.asList($VALUES));

        DyeColor dyeColor = dyeColor(variableName, id, id, name, textureDefuseColor, mapColor, fireworkColor, textColor);
        arr.add(dyeColor);
        $VALUES = arr.toArray(DyeColor[]::new);

        return dyeColor;
    }

    /**
     * Whatever variables call the values from the {@link DyeColor}, it's not gonna have my custom values, so I need to
     * pretty much copy the same thing and apply to here. Kinda annoying.
     */
    @Shadow @Final @Mutable
    private static final IntFunction<DyeColor> BY_ID = ByIdMap.continuous(DyeColor::getId, DyeColor.values(), ByIdMap.OutOfBoundsStrategy.ZERO);

    @Shadow @Final @Mutable
    private static final Int2ObjectOpenHashMap<DyeColor> BY_FIREWORK_COLOR = new Int2ObjectOpenHashMap<>(
            Arrays.stream(DyeColor.values()).collect(Collectors.toMap(x -> x.getFireworkColor(), x -> (DyeColor)x))
    );

    @Shadow @Final @Mutable
    public static final StringRepresentable.EnumCodec<DyeColor> CODEC = StringRepresentable.fromEnum(DyeColor::values);

    @Shadow @Final @Mutable
    public static final StreamCodec<ByteBuf, DyeColor> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, DyeColor::getId);
}
