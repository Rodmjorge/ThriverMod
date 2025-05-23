package net.rodmjorgeh.thriver.world.area.areagen.feature;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.areagen.feature.configurations.OasisFeatureConfiguration;

import java.util.function.Supplier;

public class FeatureReg {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(BuiltInRegistries.FEATURE, ThriverMod.MOD_ID);

    public static final Supplier<Feature<OasisFeatureConfiguration>> OASIS = FEATURES.register("oasis",
            () -> new OasisFeature(OasisFeatureConfiguration.CODEC));


    public static void register(IEventBus event) { FEATURES.register(event); }
}
