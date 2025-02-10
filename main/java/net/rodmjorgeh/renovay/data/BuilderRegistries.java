package net.rodmjorgeh.renovay.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.entity.damage.DamageTypeRegistry;
import net.rodmjorgeh.renovay.world.worldgen.features.ConfiguredFeaturesRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BuilderRegistries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeaturesRegistry::bootstrap)
            .add(Registries.DAMAGE_TYPE, DamageTypeRegistry::bootstrap);

    public BuilderRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(RenovayMod.MOD_ID));
    }
}
