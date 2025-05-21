package net.rodmjorgeh.thriver.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.entity.damage.DamageTypeRegistry;
import net.rodmjorgeh.thriver.world.worldgen.biome.BiomeModifierRegistry;
import net.rodmjorgeh.thriver.world.worldgen.features.FeaturesRegistry;
import net.rodmjorgeh.thriver.world.worldgen.placement.PlacementsRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BuilderRegistries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, FeaturesRegistry::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacementsRegistry::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifierRegistry::bootstrap)
            .add(Registries.DAMAGE_TYPE, DamageTypeRegistry::bootstrap);

    public BuilderRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ThriverMod.MOD_ID));
    }
}
