package net.rodmjorgeh.renovay.world.area.areagen.feature;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.renovay.RenovayMod;

import java.util.function.Supplier;

public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(BuiltInRegistries.FEATURE, RenovayMod.MOD_ID);

    public static final Supplier<Feature<OasisFeature.Configuration>> OASIS = FEATURES.register("oasis",
            () -> new OasisFeature(OasisFeature.Configuration.CODEC));


    public static void register(IEventBus event) { FEATURES.register(event); }
}
