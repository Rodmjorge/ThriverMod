package net.rodmjorgeh.renovay.util.tags;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.rodmjorgeh.renovay.RenovayMod;

public class ItemTagRegistry {
    public static final TagKey<Item> PALM_LOGS = register("palm_logs");
    public static final TagKey<Item> MILKS = register("milks");

    private static TagKey<Item> register(String name) {
        return ItemTags.create(RenovayMod.createLoc(name));
    }
}
