package net.rodmjorgeh.renovay.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.entity.EntityRegistry;
import net.rodmjorgeh.renovay.world.item.food.FoodRegistry;

import java.util.*;
import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RenovayMod.MOD_ID);

    public static final RegistryObject<Item> PALM_SIGN = register("palm_sign",
            () -> new SignItem(BlockRegistry.PALM_SIGN.get(), BlockRegistry.PALM_WALL_SIGN.get(), new Item.Properties().setId(createId("palm_sign")).stacksTo(16)),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final RegistryObject<Item> PALM_HANGING_SIGN = register("palm_hanging_sign",
            () -> new HangingSignItem(BlockRegistry.PALM_HANGING_SIGN.get(), BlockRegistry.PALM_WALL_HANGING_SIGN.get(), new Item.Properties().setId(createId("palm_hanging_sign")).stacksTo(16)),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final RegistryObject<Item> PALM_BOAT = register("palm_boat",
            () -> new BoatItem(EntityRegistry.PALM_BOAT.get(), new Item.Properties().setId(createId("palm_boat")).stacksTo(1)),
            CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final RegistryObject<Item> PALM_CHEST_BOAT = register("palm_chest_boat",
            () -> new BoatItem(EntityRegistry.PALM_CHEST_BOAT.get(), new Item.Properties().setId(createId("palm_chest_boat")).stacksTo(1)),
            CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static final RegistryObject<Item> COCONUT = register("coconut",
            () -> new BlockItem(BlockRegistry.COCONUT.get(), new Item.Properties()
                    .food(FoodRegistry.COCONUT)
                    .setId(createId("coconut"))),
            CreativeModeTabs.NATURAL_BLOCKS, CreativeModeTabs.FOOD_AND_DRINKS);
    public static final RegistryObject<Item> COCONUT_BOWL = register("coconut_bowl",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_BOWL)
                    .setId(createId("coconut_bowl"))),
            CreativeModeTabs.FOOD_AND_DRINKS, CreativeModeTabs.INGREDIENTS);
    public static final RegistryObject<Item> COCONUT_MUSHROOM_STEW = register("coconut_mushroom_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_MUSHROOM_STEW)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_mushroom_stew"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final RegistryObject<Item> COCONUT_RABBIT_STEW = register("coconut_rabbit_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_RABBIT_STEW)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_rabbit_stew"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final RegistryObject<Item> COCONUT_BEETROOT_SOUP = register("coconut_beetroot_soup",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_BEETROOT_SOUP)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_beetroot_soup"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final RegistryObject<Item> COCONUT_SUSPICIOUS_STEW = register("coconut_suspicious_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_SUSPICIOUS_STEW)
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_suspicious_stew"))));

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item, ResourceKey<CreativeModeTab>... creativeTabs) {
        RegistryObject<T> registry = ITEMS.register(name, item);
        CreativeModeTabRegistry.registerInTab(registry, creativeTabs);

        return registry;
    }

    public static ResourceKey createId(String name) {
        return RenovayMod.createId(name, Registries.ITEM);
    }
}
