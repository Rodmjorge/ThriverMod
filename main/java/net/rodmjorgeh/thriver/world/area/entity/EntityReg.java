package net.rodmjorgeh.thriver.world.area.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.world.item.ItemReg;

import java.util.function.Supplier;

public class EntityReg {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ThriverMod.MOD_ID);

    public static final Supplier<EntityType<Boat>> PALM_BOAT = ENTITIES.register("palm_boat",
            () -> EntityType.Builder.of(EntityType.boatFactory(() -> ItemReg.PALM_BOAT.get()), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
                    .build(createId("palm_boat")));
    public static final Supplier<EntityType<ChestBoat>> PALM_CHEST_BOAT = ENTITIES.register("palm_chest_boat",
            () -> EntityType.Builder.of(EntityType.chestBoatFactory(() -> ItemReg.PALM_CHEST_BOAT.get()), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
                    .build(createId("palm_chest_boat")));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }

    private static ResourceKey createId(String name) {
        return ResourceMod.createId(name, Registries.ENTITY_TYPE);
    }
}
