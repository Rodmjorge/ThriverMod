package net.rodmjorgeh.thriver.world.area.maps;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;

public class MapDecorationTypeReg {

    public static final DeferredRegister<MapDecorationType> MAP_DECORATION =
            DeferredRegister.create(BuiltInRegistries.MAP_DECORATION_TYPE, ThriverMod.MOD_ID);

    public static final Holder<MapDecorationType> BEIGE_BANNER = register("banner_beige",
            "beige_banner", true, true);


    private static Holder<MapDecorationType> register(String name, String assetId, boolean showOnItemFrame, boolean trackCount) {
        return register(name, assetId, showOnItemFrame, -1, false, trackCount);
    }
    private static Holder<MapDecorationType> register(String name, String assetId, boolean showOnItemFrame, int mapColor,
                                                      boolean explorationMapElement, boolean trackCount) {
        return MAP_DECORATION.register(name, () -> new MapDecorationType(ThriverMod.createLoc(assetId),
                showOnItemFrame, mapColor, explorationMapElement, trackCount));
    }

    public static void register(IEventBus bus) {
        MAP_DECORATION.register(bus);
    }
}
