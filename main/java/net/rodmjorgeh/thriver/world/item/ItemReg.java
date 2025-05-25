package net.rodmjorgeh.thriver.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;
import net.rodmjorgeh.thriver.util.ResourceMod;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.entity.EntityReg;
import net.rodmjorgeh.thriver.world.item.food.FoodReg;

import java.util.function.Supplier;

public class ItemReg {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, ThriverMod.MOD_ID);

    public static final Supplier<Item> PALM_SIGN = register("palm_sign",
            () -> new SignItem(BlockReg.PALM_SIGN.get(), BlockReg.PALM_WALL_SIGN.get(), new Item.Properties()
                    .stacksTo(16)
                    .setId(createId("palm_sign"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> PALM_HANGING_SIGN = register("palm_hanging_sign",
            () -> new HangingSignItem(BlockReg.PALM_HANGING_SIGN.get(), BlockReg.PALM_WALL_HANGING_SIGN.get(), new Item.Properties()
                    .stacksTo(16)
                    .setId(createId("palm_hanging_sign"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> PALM_BOAT = register("palm_boat",
            () -> new BoatItem(EntityReg.PALM_BOAT.get(), new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("palm_boat"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final Supplier<Item> PALM_CHEST_BOAT = register("palm_chest_boat",
            () -> new BoatItem(EntityReg.PALM_CHEST_BOAT.get(), new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("palm_chest_boat"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static final Supplier<Item> COCONUT = register("coconut",
            () -> new BlockItem(BlockReg.COCONUT.get(), new Item.Properties()
                    .setId(createId("coconut"))),
            CreativeModeTabs.NATURAL_BLOCKS);
    public static final Supplier<Item> COCONUT_BOWL = register("coconut_bowl",
            () -> new Item(new Item.Properties()
                    .setId(createId("coconut_bowl"))),
            CreativeModeTabs.INGREDIENTS);
    public static final Supplier<Item> COCONUT_MUSHROOM_STEW = register("coconut_mushroom_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodReg.COCONUT_MUSHROOM_STEW)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_mushroom_stew"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final Supplier<Item> COCONUT_RABBIT_STEW = register("coconut_rabbit_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodReg.COCONUT_RABBIT_STEW)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_rabbit_stew"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final Supplier<Item> COCONUT_BEETROOT_SOUP = register("coconut_beetroot_soup",
            () -> new Item(new Item.Properties()
                    .food(FoodReg.COCONUT_BEETROOT_SOUP)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_beetroot_soup"))),
            CreativeModeTabs.FOOD_AND_DRINKS);
    public static final Supplier<Item> COCONUT_SUSPICIOUS_STEW = register("coconut_suspicious_stew",
            () -> new Item(new Item.Properties()
                    .food(FoodReg.COCONUT_SUSPICIOUS_STEW)
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY)
                    .usingConvertsTo(COCONUT_BOWL.get())
                    .stacksTo(1)
                    .setId(createId("coconut_suspicious_stew"))));
    public static final Supplier<Item> COCONUT_MILK = register("coconut_milk",
            () -> new Item(new Item.Properties()
                    .craftRemainder(COCONUT_BOWL.get())
                    .food(FoodReg.COCONUT_MILK)
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
            () -> new CoirMatItem(BlockReg.COIR_MAT.get(), new Item.Properties()
                    .setId(createId("coir_mat"))),
            CreativeModeTabs.FUNCTIONAL_BLOCKS);

    public static final Supplier<Item> REED_FLUTE = register("reed_flute",
            () -> new ReedFluteItem(new Item.Properties()
                    .durability(9)
                    .setId(createId("reed_flute"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static final Supplier<Item> DOLLS_EYE_SEEDS = register("dolls_eye_seeds",
            () -> Items.createBlockItemWithCustomItemName(BlockReg.DOLLS_EYES_CROP.get()).apply(
                    new Item.Properties().setId(createId("dolls_eye_seeds"))
            ),
            CreativeModeTabs.NATURAL_BLOCKS);

    public static final Supplier<Item> BEIGE_DYE = register("beige_dye",
            () -> new DyeItem(DyeColorThr.BEIGE, new Item.Properties()
                    .setId(createId("beige_dye"))),
            CreativeModeTabs.INGREDIENTS);
    public static final Supplier<Item> BEIGE_BANNER = register("beige_banner",
            () -> new BannerItem(BlockReg.BEIGE_BANNER.get(), BlockReg.BEIGE_WALL_BANNER.get(), new Item.Properties()
                    .stacksTo(16)
                    .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                    .setId(createId("beige_banner"))),
            CreativeModeTabs.COLORED_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> BEIGE_BED = register("beige_bed",
            () -> new BedItem(BlockReg.BEIGE_BED.get(), new Item.Properties()
                    .stacksTo(1)
                    .setId(createId("beige_bed"))),
            CreativeModeTabs.COLORED_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS);
    public static final Supplier<Item> BEIGE_BUNDLE = register("beige_bundle",
            () -> new BundleItem(new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)
                    .setId(createId("beige_bundle"))),
            CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final Supplier<Item> BEIGE_CARPET = register("beige_carpet",
            () -> new BlockItem(BlockReg.BEIGE_CARPET.get(), new Item.Properties()
                    .component(DataComponents.EQUIPPABLE, Equippable.llamaSwag(DyeColorThr.BEIGE))
                    .setId(createId("beige_carpet"))),
            CreativeModeTabs.COLORED_BLOCKS);
    public static final Supplier<Item> BEIGE_SHULKER_BOX = register("beige_shulker_box",
            () -> new BlockItem(BlockReg.BEIGE_SHULKER_BOX.get(), new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                    .setId(createId("beige_shulker_box"))),
            CreativeModeTabs.COLORED_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS);

    public static void register(IEventBus event) { ITEMS.register(event); }

    private static <T extends Item> Supplier<T> register(String name, Supplier<T> item, ResourceKey<CreativeModeTab>... creativeTabs) {
        Supplier<T> registry = ITEMS.register(name, item);
        CreativeModeTabReg.registerInTab(registry, creativeTabs);

        return registry;
    }

    public static ResourceKey createId(String name) {
        return ResourceMod.createId(name, Registries.ITEM);
    }
}
