package net.rodmjorgeh.thriver.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.rodmjorgeh.thriver.ThriverMod;

public class ResourceMod {

    private static final String MOD_ID = ThriverMod.MOD_ID;

    /**
     * Creates a new {@code ResourceKey} based on the registry type.
     */
    public static <T> ResourceKey<T> createId(String name, ResourceKey<? extends Registry<T>> registryType) {
        return ResourceKey.create(registryType, createLoc(name));
    }

    public static ResourceLocation createLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static <T> ResourceLocation createLocFromKey(ResourceKey<T> key) {
        return createLoc(key.location().getPath());
    }

    public static String createStringLoc(String name) {
        return MOD_ID + ":" + name;
    }


    public static String mainFromGenerated(String folder) {
        return folder.replace("generated", "main");
    }
}
