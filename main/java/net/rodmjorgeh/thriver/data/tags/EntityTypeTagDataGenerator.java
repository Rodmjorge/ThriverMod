package net.rodmjorgeh.thriver.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.entity.EntityReg;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTagDataGenerator extends EntityTypeTagsProvider {

    public EntityTypeTagDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ThriverMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.BOAT).add(EntityReg.PALM_BOAT.get());
    }
}
