package net.rodmjorgeh.thriver.data.advancements;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.rodmjorgeh.thriver.ThriverMod;

public interface AdvancementDataGeneratorProvider {

    String getType();

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
        return ThriverMod.createLoc(this.getType() + "/" + name);
    }
}
