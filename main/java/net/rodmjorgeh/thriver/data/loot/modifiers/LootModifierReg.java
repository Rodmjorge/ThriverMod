package net.rodmjorgeh.thriver.data.loot.modifiers;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rodmjorgeh.thriver.ThriverMod;

import java.util.function.Supplier;

public class LootModifierReg {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ThriverMod.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_LOOT_POOL = LOOT_MODIFIERS.register("add_loot_pool",
            () -> AddLootPoolModifier.CODEC);


    public static void register(IEventBus bus) {
        LOOT_MODIFIERS.register(bus);
    }
}
