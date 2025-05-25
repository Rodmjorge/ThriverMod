package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.data.DataGeneratorProvider;
import net.rodmjorgeh.thriver.util.ResourceMod;

public interface AdvancementDataGeneratorProvider extends DataGeneratorProvider {
    default MutableComponent createTitle(String name) {
        return this.createTranslatableComponent(name, "title");
    }

    default MutableComponent createDescription(String name) {
        return this.createTranslatableComponent(name, "description");
    }

    private MutableComponent createTranslatableComponent(String name, String componentType) {
        return Component.translatable("advancements." + this.getType() + "." + name + "." + componentType);
    }

    default ResourceLocation getFolder(String name) {
        return ResourceMod.createLoc(this.getType() + "/" + name);
    }
    default ResourceLocation getFolderMinecraft(String name) {
        return ResourceLocation.withDefaultNamespace(this.getType() + "/" + name);
    }


    default HolderLookup.RegistryLookup<Item> items(HolderLookup.Provider registries) {
        return registries.lookupOrThrow(Registries.ITEM);
    }
}
