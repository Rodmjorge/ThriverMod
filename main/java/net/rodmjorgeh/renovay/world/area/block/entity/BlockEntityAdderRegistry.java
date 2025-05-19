package net.rodmjorgeh.renovay.world.area.block.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockEntityAdderRegistry {

    public static void bootstrap() {
        register(BlockEntityType.BANNER, BlockRegistry.BEIGE_BANNER.get(), BlockRegistry.BEIGE_WALL_BANNER.get());
        register(BlockEntityType.BED, BlockRegistry.BEIGE_BED.get());
        register(BlockEntityType.HANGING_SIGN, BlockRegistry.PALM_HANGING_SIGN.get(), BlockRegistry.PALM_WALL_HANGING_SIGN.get());
        register(BlockEntityType.SHULKER_BOX, BlockRegistry.BEIGE_SHULKER_BOX.get());
        register(BlockEntityType.SIGN, BlockRegistry.PALM_SIGN.get(), BlockRegistry.PALM_WALL_SIGN.get());
    }

    private static <T extends BlockEntity> void register(BlockEntityType<T> type, Block... blocks) {
        List<Block> list = new ArrayList<>(type.validBlocks);
        Collections.addAll(list, blocks);

        type.validBlocks = ImmutableSet.copyOf(list);
    }
}
