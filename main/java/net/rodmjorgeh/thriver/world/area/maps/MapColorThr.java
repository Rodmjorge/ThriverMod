package net.rodmjorgeh.thriver.world.area.maps;

import net.minecraft.world.level.material.MapColor;

public class MapColorThr {

    public static int ID_START = 62;

    public static final MapColor COLOR_BEIGE = create(14724731);
    public static final MapColor TERRACOTTA_BEIGE = create(8941660);

    /**
     * Creates a new {@link MapColor}, knowing the leftover IDs start at 62.
     */
    private static MapColor create(int color) {
        MapColor mapColor = new MapColor(ID_START, color);
        ID_START++;

        return mapColor;
    }
}
