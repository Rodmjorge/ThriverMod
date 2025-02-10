package net.rodmjorgeh.renovay.world.area.areagen.feature.placers;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;

public class FoliagePlacerRegistry {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, RenovayMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<PalmTreeFoliagePlacer>> PALM_TREE_FOLIAGE_PLACER =
            register("palm_tree_foliage_placer", PalmTreeFoliagePlacer.CODEC);

    private static <T extends FoliagePlacer> RegistryObject<FoliagePlacerType<T>> register(String name, MapCodec<T> codec) {
        return FOLIAGE_PLACERS.register(name, () -> new FoliagePlacerType<>(codec));
    }
    public static void register(IEventBus bus) { FOLIAGE_PLACERS.register(bus); }
}
