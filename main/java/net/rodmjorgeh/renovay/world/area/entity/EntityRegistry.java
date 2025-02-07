package net.rodmjorgeh.renovay.world.area.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RenovayMod.MOD_ID);

    public static final RegistryObject<EntityType<Boat>> PALM_BOAT = ENTITIES.register("palm_boat",
            () -> EntityType.Builder.of(EntityType.boatFactory(() -> ItemRegistry.PALM_BOAT.get()), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
                    .build(createId("palm_boat")));
    public static final RegistryObject<EntityType<ChestBoat>> PALM_CHEST_BOAT = ENTITIES.register("palm_chest_boat",
            () -> EntityType.Builder.of(EntityType.chestBoatFactory(() -> ItemRegistry.PALM_CHEST_BOAT.get()), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
                    .build(createId("palm_chest_boat")));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }

    private static ResourceKey createId(String name) {
        return RenovayMod.createId(name, Registries.ENTITY_TYPE);
    }
}
