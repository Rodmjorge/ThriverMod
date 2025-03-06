package net.rodmjorgeh.renovay.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.util.tags.BlockTagRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.entity.EntityRegistry;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTagDataGenerator extends EntityTypeTagsProvider {

    public EntityTypeTagDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RenovayMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.BOAT).add(EntityRegistry.PALM_BOAT.get());
    }
}
