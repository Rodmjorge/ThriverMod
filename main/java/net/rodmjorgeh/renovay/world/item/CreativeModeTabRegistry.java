package net.rodmjorgeh.renovay.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.rodmjorgeh.renovay.world.item.food.FoodRegistry;

import java.util.*;
import java.util.function.Supplier;

public class CreativeModeTabRegistry {
    private static Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends ItemLike>>> itemsInTab = new HashMap<>();

    public static void registerInTab(Supplier<? extends ItemLike> item, ResourceKey<CreativeModeTab>[] creativeTabs) {
        for (ResourceKey<CreativeModeTab> tab : creativeTabs) {
            if (!itemsInTab.containsKey(tab)) {
                itemsInTab.put(tab, new ArrayList<>());
            }

            itemsInTab.get(tab).add(item);
        }
    }

    /**
     * After being called from the {@code event}, knowing the {@link CreativeModeTab}, it adds all the
     * items inside the map {@code Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends ItemLike>>>} with
     * the same key.
     */
    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> x = event.getTabKey();

        if (itemsInTab.containsKey(x)) {
            for (Supplier<? extends ItemLike> item : itemsInTab.get(x)) {
                event.accept(item.get());
            }
        }

        if (x.equals(CreativeModeTabs.FOOD_AND_DRINKS)) {
            addCoconutSuspiciousStewToCreativeTab(event);
        }
    }

    /**
     * Just like the normal Suspicious Stew, this method will add every effect as a separate Coconut Suspicious Stew
     * item.
     */
    private static void addCoconutSuspiciousStewToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        List<SuspiciousEffectHolder> list = SuspiciousEffectHolder.getAllEffectHolders();
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();

        for (SuspiciousEffectHolder holder : list) {
            ItemStack stack = new ItemStack(ItemRegistry.COCONUT_SUSPICIOUS_STEW.get());
            List<SuspiciousStewEffects.Entry> effects = holder.getSuspiciousEffects().effects();

            if (!effects.isEmpty()) {
                stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, FoodRegistry.forCoconut(effects.getFirst()));
                set.add(stack);
            }
        }

        event.acceptAll(set);
    }
}
