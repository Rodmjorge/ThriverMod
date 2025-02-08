package net.rodmjorgeh.renovay.world.material;

import net.minecraft.world.level.material.MapColor;

public class MapColorR {

    private static int ID_START = 62;
    public static final MapColor PALM_TREE = create(13067354);

    /**
     * Creates a new {@link MapColor}, knowing the leftover IDs start at 62.
     */
    private static MapColor create(int color) {
        MapColor mapColor = new MapColor(ID_START, color);
        ID_START++;

        return mapColor;
    }
}
