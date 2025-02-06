package net.rodmjorgeh.renovay.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.entity.EntityRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RenovayMod.MOD_ID);
    private static Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends ItemLike>>> itemsInTab = new HashMap<>();

    public static final RegistryObject<Item> PALM_BOAT = register("palm_boat",
            () -> new BoatItem(EntityRegistry.PALM_BOAT.get(), new Item.Properties().setId(createId("palm_boat")).stacksTo(1)),
            CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final RegistryObject<Item> PALM_CHEST_BOAT = register("palm_chest_boat",
            () -> new BoatItem(EntityRegistry.PALM_CHEST_BOAT.get(), new Item.Properties().setId(createId("palm_chest_boat")).stacksTo(1)),
            CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item, ResourceKey<CreativeModeTab>... creativeTabs) {
        RegistryObject<T> registry = ITEMS.register(name, item);
        registerInTab(registry, creativeTabs);

        return registry;
    }

    public static void registerInTab(Supplier<? extends ItemLike> item, ResourceKey<CreativeModeTab>[] creativeTabs) {
        for (ResourceKey<CreativeModeTab> tab : creativeTabs) {
            if (!itemsInTab.containsKey(tab)) {
                itemsInTab.put(tab, new ArrayList<>());
            }

            itemsInTab.get(tab).add(item);
        }
    }

    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> x = event.getTabKey();

        if (itemsInTab.containsKey(x)) {
            for (Supplier<? extends ItemLike> item : itemsInTab.get(x)) {
                event.accept(item);
            }
        }
    }

    public static ResourceKey createId(String name) {
        return RenovayMod.createId(name, Registries.ITEM);
    }
}
