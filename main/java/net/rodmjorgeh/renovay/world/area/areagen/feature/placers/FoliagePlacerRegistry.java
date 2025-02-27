package net.rodmjorgeh.renovay.world.area.areagen.feature.placers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.renovay.RenovayMod;

import java.util.function.Supplier;

public class FoliagePlacerRegistry {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, RenovayMod.MOD_ID);

    public static final Supplier<FoliagePlacerType<PalmTreeFoliagePlacer>> PALM_TREE_FOLIAGE_PLACER =
            register("palm_tree_foliage_placer", PalmTreeFoliagePlacer.CODEC);

    private static <T extends FoliagePlacer> Supplier<FoliagePlacerType<T>> register(String name, MapCodec<T> codec) {
        return FOLIAGE_PLACERS.register(name, () -> new FoliagePlacerType<>(codec));
    }
    public static void register(IEventBus bus) { FOLIAGE_PLACERS.register(bus); }
}
