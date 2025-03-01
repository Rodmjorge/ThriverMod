package net.rodmjorgeh.renovay.util.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.rodmjorgeh.renovay.RenovayMod;

public class BlockTagRegistry {
    public static final TagKey<Block> PALM_LOGS = register("palm_logs");
    public static final TagKey<Block> MUD = register("mud");

    private static TagKey<Block> register(String name) {
        return BlockTags.create(RenovayMod.createLoc(name));
    }
}
