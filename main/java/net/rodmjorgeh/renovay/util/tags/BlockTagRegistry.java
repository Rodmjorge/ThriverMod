package net.rodmjorgeh.renovay.util.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.rodmjorgeh.renovay.RenovayMod;

public class BlockTagRegistry {
    public static final TagKey<Block> PALM_LOGS = register("palm_logs");

    private static TagKey<Block> register(String name) {
        return BlockTags.create(name, RenovayMod.MOD_ID);
    }
}
