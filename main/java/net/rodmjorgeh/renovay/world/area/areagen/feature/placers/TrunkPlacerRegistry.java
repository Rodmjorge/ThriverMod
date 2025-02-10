package net.rodmjorgeh.renovay.world.area.areagen.feature.placers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;

public class TrunkPlacerRegistry {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, RenovayMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<PalmTreeTrunkPlacer>> PALM_TREE_TRUNK_PLACER =
            register("palm_tree_trunk_placer", PalmTreeTrunkPlacer.CODEC);

    private static <T extends TrunkPlacer> RegistryObject<TrunkPlacerType<T>> register(String name, MapCodec<T> codec) {
        return TRUNK_PLACERS.register(name, () -> new TrunkPlacerType<>(codec));
    }
    public static void register(IEventBus bus) { TRUNK_PLACERS.register(bus); }
}
