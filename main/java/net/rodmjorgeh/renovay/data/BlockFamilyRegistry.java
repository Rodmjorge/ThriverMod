package net.rodmjorgeh.renovay.data;

import com.google.common.collect.Maps;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;

import java.util.Map;
import java.util.stream.Stream;

public class BlockFamilyRegistry {
    private static final Map<Block, BlockFamily> CUSTOM_MAP = Maps.newHashMap();

    public static final BlockFamily PALM_PLANKS = add(new BlockFamily.Builder(
            BlockRegistry.PALM_PLANKS.get())
            .button(BlockRegistry.PALM_BUTTON.get())
            .fence(BlockRegistry.PALM_FENCE.get())
            .fenceGate(BlockRegistry.PALM_FENCE_GATE.get())
            .pressurePlate(BlockRegistry.PALM_PRESSURE_PLATE.get())
            .sign(BlockRegistry.PALM_SIGN.get(), BlockRegistry.PALM_WALL_SIGN.get())
            .slab(BlockRegistry.PALM_SLAB.get())
            .stairs(BlockRegistry.PALM_STAIRS.get())
            .door(BlockRegistry.PALM_DOOR.get())
            .trapdoor(BlockRegistry.PALM_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily());

    public static final BlockFamily SANDSTONE_BRICKS = add(new BlockFamily.Builder(
            BlockRegistry.SANDSTONE_BRICKS.get())
            .wall(BlockRegistry.SANDSTONE_BRICK_WALL.get())
            .slab(BlockRegistry.SANDSTONE_BRICK_SLAB.get())
            .stairs(BlockRegistry.SANDSTONE_BRICK_STAIRS.get())
            .cracked(BlockRegistry.CRACKED_SANDSTONE_BRICKS.get())
            .getFamily());
    public static final BlockFamily RED_SANDSTONE_BRICKS = add(new BlockFamily.Builder(
            BlockRegistry.RED_SANDSTONE_BRICKS.get())
            .wall(BlockRegistry.RED_SANDSTONE_BRICK_WALL.get())
            .slab(BlockRegistry.RED_SANDSTONE_BRICK_SLAB.get())
            .stairs(BlockRegistry.RED_SANDSTONE_BRICK_STAIRS.get())
            .cracked(BlockRegistry.CRACKED_RED_SANDSTONE_BRICKS.get())
            .getFamily());

    private static BlockFamily add(BlockFamily family) {
        CUSTOM_MAP.put(family.getBaseBlock(), family);
        return family;
    }

    public static Stream<BlockFamily> getCustomFamilies() {
        return CUSTOM_MAP.values().stream();
    }
}
