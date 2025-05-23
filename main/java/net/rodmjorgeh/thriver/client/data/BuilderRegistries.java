package net.rodmjorgeh.thriver.client.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.entity.damage.DamageTypeReg;
import net.rodmjorgeh.thriver.world.worldgen.biome.BiomeModifierReg;
import net.rodmjorgeh.thriver.world.worldgen.features.FeaturesReg;
import net.rodmjorgeh.thriver.world.worldgen.placement.PlacementsReg;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BuilderRegistries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, FeaturesReg::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacementsReg::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifierReg::bootstrap)
            .add(Registries.DAMAGE_TYPE, DamageTypeReg::bootstrap);

    public BuilderRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ThriverMod.MOD_ID));
    }
}
