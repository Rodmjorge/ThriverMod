package net.rodmjorgeh.thriver.data;

import com.google.common.collect.Maps;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;

import java.util.Map;
import java.util.stream.Stream;

public class BlockFamilyReg {
    private static final Map<Block, BlockFamily> CUSTOM_MAP = Maps.newHashMap();

    public static final BlockFamily PALM_PLANKS = add(new BlockFamily.Builder(
            BlockReg.PALM_PLANKS.get())
            .button(BlockReg.PALM_BUTTON.get())
            .fence(BlockReg.PALM_FENCE.get())
            .fenceGate(BlockReg.PALM_FENCE_GATE.get())
            .pressurePlate(BlockReg.PALM_PRESSURE_PLATE.get())
            .sign(BlockReg.PALM_SIGN.get(), BlockReg.PALM_WALL_SIGN.get())
            .slab(BlockReg.PALM_SLAB.get())
            .stairs(BlockReg.PALM_STAIRS.get())
            .door(BlockReg.PALM_DOOR.get())
            .trapdoor(BlockReg.PALM_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily());

    public static final BlockFamily SANDSTONE_BRICKS = add(new BlockFamily.Builder(
            BlockReg.SANDSTONE_BRICKS.get())
            .wall(BlockReg.SANDSTONE_BRICK_WALL.get())
            .slab(BlockReg.SANDSTONE_BRICK_SLAB.get())
            .stairs(BlockReg.SANDSTONE_BRICK_STAIRS.get())
            .cracked(BlockReg.CRACKED_SANDSTONE_BRICKS.get())
            .getFamily());
    public static final BlockFamily RED_SANDSTONE_BRICKS = add(new BlockFamily.Builder(
            BlockReg.RED_SANDSTONE_BRICKS.get())
            .wall(BlockReg.RED_SANDSTONE_BRICK_WALL.get())
            .slab(BlockReg.RED_SANDSTONE_BRICK_SLAB.get())
            .stairs(BlockReg.RED_SANDSTONE_BRICK_STAIRS.get())
            .cracked(BlockReg.CRACKED_RED_SANDSTONE_BRICKS.get())
            .getFamily());

    private static BlockFamily add(BlockFamily family) {
        CUSTOM_MAP.put(family.getBaseBlock(), family);
        return family;
    }

    public static Stream<BlockFamily> getCustomFamilies() {
        return CUSTOM_MAP.values().stream();
    }
}
