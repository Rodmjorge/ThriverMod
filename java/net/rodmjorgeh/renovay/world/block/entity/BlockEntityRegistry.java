package net.rodmjorgeh.renovay.world.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rodmjorgeh.renovay.RenovayMod;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RenovayMod.MOD_ID);

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
