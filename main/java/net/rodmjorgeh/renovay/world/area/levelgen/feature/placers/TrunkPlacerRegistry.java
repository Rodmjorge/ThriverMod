package net.rodmjorgeh.renovay.world.area.levelgen.feature.placers;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;

public class TrunkPlacerRegistry {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, RenovayMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<PalmTreeTrunkPlacer>> PALM_TREE_TRUNK_PLACER = TRUNK_PLACERS.register("palm_tree_trunk_placer",
            () -> new TrunkPlacerType<>(PalmTreeTrunkPlacer.CODEC));


    public static void register(IEventBus bus) { TRUNK_PLACERS.register(bus); }
}
