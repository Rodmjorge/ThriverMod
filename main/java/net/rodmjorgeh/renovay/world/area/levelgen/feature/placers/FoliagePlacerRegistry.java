package net.rodmjorgeh.renovay.world.area.levelgen.feature.placers;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;

public class FoliagePlacerRegistry {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, RenovayMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<PalmTreeFoliagePlacer>> PALM_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_tree_foliage_placer",
            () -> new FoliagePlacerType<>(PalmTreeFoliagePlacer.CODEC));


    public static void register(IEventBus bus) { FOLIAGE_PLACERS.register(bus); }
}
