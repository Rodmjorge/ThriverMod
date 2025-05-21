package net.rodmjorgeh.thriver.world.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.worldgen.placement.MiscOverworldPlacementsRegistry;
import net.rodmjorgeh.thriver.world.worldgen.placement.VegetationPlacementsRegistry;

public class BiomeModifierRegistry {
    public static final ResourceKey<BiomeModifier> TREES_BEACH = register("trees_beach");
    public static final ResourceKey<BiomeModifier> OASIS_DESERT = register("oasis_desert");
    public static final ResourceKey<BiomeModifier> OASIS_BADLANDS = register("oasis_badlands");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> feature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(TREES_BEACH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BEACH)),
                HolderSet.direct(feature.getOrThrow(VegetationPlacementsRegistry.TREES_BEACH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(OASIS_DESERT, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                HolderSet.direct(feature.getOrThrow(MiscOverworldPlacementsRegistry.OASIS_DESERT)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));
        context.register(OASIS_BADLANDS, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BADLANDS)),
                HolderSet.direct(feature.getOrThrow(MiscOverworldPlacementsRegistry.OASIS_BADLANDS)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));
    }

    private static ResourceKey<BiomeModifier> register(String name) {
        return ThriverMod.createId(name, NeoForgeRegistries.Keys.BIOME_MODIFIERS);
    }
}
