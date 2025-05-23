package net.rodmjorgeh.thriver.world.area.areagen.feature.placers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;

import java.util.function.Supplier;

public class TrunkPlacerReg {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE, ThriverMod.MOD_ID);

    public static final Supplier<TrunkPlacerType<PalmTreeTrunkPlacer>> PALM_TREE_TRUNK_PLACER =
            register("palm_tree_trunk_placer", PalmTreeTrunkPlacer.CODEC);

    private static <T extends TrunkPlacer> Supplier<TrunkPlacerType<T>> register(String name, MapCodec<T> codec) {
        return TRUNK_PLACERS.register(name, () -> new TrunkPlacerType<>(codec));
    }
    public static void register(IEventBus bus) { TRUNK_PLACERS.register(bus); }
}
