package net.rodmjorgeh.renovay.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.entity.damage.DamageTypeRegistry;
import net.rodmjorgeh.renovay.world.worldgen.biome.BiomeModifierRegistry;
import net.rodmjorgeh.renovay.world.worldgen.features.FeaturesRegistry;
import net.rodmjorgeh.renovay.world.worldgen.features.MiscOverworldFeaturesRegistry;
import net.rodmjorgeh.renovay.world.worldgen.features.TreeFeaturesRegistry;
import net.rodmjorgeh.renovay.world.worldgen.features.VegetationFeaturesRegistry;
import net.rodmjorgeh.renovay.world.worldgen.placement.PlacementsRegistry;
import net.rodmjorgeh.renovay.world.worldgen.placement.TreePlacementsRegistry;
import net.rodmjorgeh.renovay.world.worldgen.placement.VegetationPlacementsRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BuilderRegistries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, FeaturesRegistry::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacementsRegistry::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifierRegistry::bootstrap)
            .add(Registries.DAMAGE_TYPE, DamageTypeRegistry::bootstrap);

    public BuilderRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(RenovayMod.MOD_ID));
    }
}
