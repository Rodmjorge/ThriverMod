package net.rodmjorgeh.thriver.world.area.block.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockEntityAdderReg {

    public static void bootstrap() {
        register(BlockEntityType.BANNER, BlockReg.BEIGE_BANNER.get(), BlockReg.BEIGE_WALL_BANNER.get());
        register(BlockEntityType.BED, BlockReg.BEIGE_BED.get());
        register(BlockEntityType.HANGING_SIGN, BlockReg.PALM_HANGING_SIGN.get(), BlockReg.PALM_WALL_HANGING_SIGN.get());
        register(BlockEntityType.SHULKER_BOX, BlockReg.BEIGE_SHULKER_BOX.get());
        register(BlockEntityType.SIGN, BlockReg.PALM_SIGN.get(), BlockReg.PALM_WALL_SIGN.get());
    }

    private static <T extends BlockEntity> void register(BlockEntityType<T> type, Block... blocks) {
        List<Block> list = new ArrayList<>(type.validBlocks);
        Collections.addAll(list, blocks);

        type.validBlocks = ImmutableSet.copyOf(list);
    }
}
