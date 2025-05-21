package net.rodmjorgeh.thriver.world.area.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;

import java.util.Set;
import java.util.function.Supplier;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ThriverMod.MOD_ID);

    public static final Supplier<BlockEntityType<CoirMatBlockEntity>> COIR_MAT = BLOCK_ENTITIES.register("coir_mat",
            () -> create(CoirMatBlockEntity::new, BlockRegistry.COIR_MAT.get()));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(BlockEntityType.BlockEntitySupplier<? extends T> sup, Block... validBlocks) {
        return new BlockEntityType<T>(sup, Set.of(validBlocks));
    }
}
