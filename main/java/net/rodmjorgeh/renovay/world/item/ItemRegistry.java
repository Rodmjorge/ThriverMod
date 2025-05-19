package net.rodmjorgeh.renovay.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.entity.EntityRegistry;
import net.rodmjorgeh.renovay.world.item.food.FoodRegistry;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, RenovayMod.MOD_ID);

    public static final Supplier<Item> PALM_SIGN = register("palm_sign",
            () -> new SignItem(BlockRegistry.PALM_SIGN.get(), BlockRegistry.PALM_WALL_SIGN.get(), new Item.Properties()
                    .stacksTo(16)
                    .setId(createId("palm_sign"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> PALM_HANGING_SIGN = register("palm_hanging_sign",
            () -> new HangingSignItem(BlockRegistry.PALM_HANGING_SIGN.get(), BlockRegistry.PALM_WALL_HANGING_SIGN.get(), new Item.Properties()
                    .stacksTo(16)
                    .setId(createId("palm_hanging_sign"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> PALM_BOAT = register("palm_boat",
            () -> new BoatItem(EntityRegistry.PALM_BOAT.get(), new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("palm_boat"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final Supplier<Item> PALM_CHEST_BOAT = register("palm_chest_boat",
            () -> new BoatItem(EntityRegistry.PALM_CHEST_BOAT.get(), new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("palm_chest_boat"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static final Supplier<Item> COCONUT = register("coconut",
            () -> new BlockItem(BlockRegistry.COCONUT.get(), new Item.Properties()
                    .setId(createId("coconut"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Item> COCONUT_BOWL = register("coconut_bowl",
            () -> new Item(new Item.Properties()
                    .setId(createId("coconut_bowl"))),
            CreativeModeTabs.INGREDIENTS);
    public static final Supplier<Item> COCONUT_MUSHROOM_STEW = register("coconut_mushroom_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_MUSHROOM_STEW)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_mushroom_stew"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final Supplier<Item> COCONUT_RABBIT_STEW = register("coconut_rabbit_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_RABBIT_STEW)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_rabbit_stew"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final Supplier<Item> COCONUT_BEETROOT_SOUP = register("coconut_beetroot_soup",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_BEETROOT_SOUP)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_beetroot_soup"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final Supplier<Item> COCONUT_SUSPICIOUS_STEW = register("coconut_suspicious_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodRegistry.COCONUT_SUSPICIOUS_STEW)
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_suspicious_stew"))));
    public static final Supplier<Item> COCONUT_MILK = register("coconut_milk",
            () -> new Item(new Item.Properties()
                    .craftRemainder(COCONUT_BOWL.get())
                    .food(FoodRegistry.COCONUT_MILK)
                    .component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_milk"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES, CreativeModeTabs.FOOD_AND_DRINKS);

    public static final Supplier<Item> COIR = register("coir",
            () -> new Item(new Item.Properties()
                    .setId(createId("coir"))),
            CreativeModeTabs.INGREDIENTS);
    public static final Supplier<Item> COIR_MAT = register("coir_mat",
            () -> new CoirMatItem(BlockRegistry.COIR_MAT.get(), new Item.Properties()
                    .setId(createId("coir_mat"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);

    public static final Supplier<Item> REED_FLUTE = register("reed_flute",
            () -> new ReedFluteItem(new Item.Properties()
                    .durability(9)
                    .setId(createId("reed_flute"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static final Supplier<Item> BEIGE_DYE = register("beige_dye",
            () -> new DyeItem(DyeColorR.BEIGE, new Item.Properties()
                    .setId(createId("beige_dye"))),
            CreativeModeTabs.INGREDIENTS);
    public static final Supplier<Item> BEIGE_BANNER = register("beige_banner",
            () -> new BannerItem(BlockRegistry.BEIGE_BANNER.get(), BlockRegistry.BEIGE_WALL_BANNER.get(), new Item.Properties()
                    .stacksTo(16)
                    .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                    .setId(createId("beige_banner"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> BEIGE_BED = register("beige_bed",
            () -> new BedItem(BlockRegistry.BEIGE_BED.get(), new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("beige_bed"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> BEIGE_BUNDLE = register("beige_bundle",
            () -> new BundleItem(new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("beige_bundle"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final Supplier<Item> BEIGE_CARPET = register("beige_carpet",
            () -> new BlockItem(BlockRegistry.BEIGE_CARPET.get(), new Item.Properties()
                    .component(DataComponents.EQUIPPABLE, Equippable.llamaSwag(DyeColorR.BEIGE))
                    .setId(createId("beige_carpet"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Item> BEIGE_SHULKER_BOX = register("beige_shulker_box",
            () -> new BlockItem(BlockRegistry.BEIGE_SHULKER_BOX.get(), new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                    .setId(createId("beige_shulker_box"))),
            CreativeModeTabs.COLORED_BLOCKS);

    public static void register(IEventBus event) { ITEMS.register(event); }

    private static <T extends Item> Supplier<T> register(String name, Supplier<T> item, ResourceKey<CreativeModeTab>... creativeTabs) {
        Supplier<T> registry = ITEMS.register(name, item);
        CreativeModeTabRegistry.registerInTab(registry, creativeTabs);

        return registry;
    }

    public static ResourceKey createId(String name) {
        return RenovayMod.createId(name, Registries.ITEM);
    }
}
