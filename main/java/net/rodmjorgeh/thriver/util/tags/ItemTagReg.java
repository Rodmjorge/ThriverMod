package net.rodmjorgeh.thriver.util.tags;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.rodmjorgeh.thriver.ThriverMod;

public class ItemTagReg {
    public static final TagKey<Item> PALM_LOGS = register("palm_logs");
    public static final TagKey<Item> MILK = register("milk");
    public static final TagKey<Item> MUD = register("mud");

    private static TagKey<Item> register(String name) {
        return ItemTags.create(ThriverMod.createLoc(name));
    }
}
