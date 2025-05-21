package net.rodmjorgeh.thriver.world.item;

import net.minecraft.world.item.DyeColor;

public class DyeColorR {
    public static final DyeColor BEIGE = DyeColor.valueOf("BEIGE");

    public static boolean isCustomColor(DyeColor color) {
        return color.equals(BEIGE);
    }
}
