package net.rodmjorgeh.renovay.world.item.food;

import net.minecraft.world.food.FoodProperties;

public class FoodRegistry {

    public static final FoodProperties COCONUT = register(3, 0.3F);
    public static final FoodProperties COCONUT_BOWL = register(4, 0.3F);
    public static final FoodProperties COCONUT_MUSHROOM_STEW = registerStew(10);
    public static final FoodProperties COCONUT_RABBIT_STEW = registerStew(14);
    public static final FoodProperties COCONUT_BEETROOT_SOUP = registerStew(10);

    private static FoodProperties register(int nutrition, float saturationMod) {
        return register(nutrition, saturationMod, false);
    }
    private static FoodProperties register(int nutrition, float saturationMod, boolean canAlwaysEat) {
        return (canAlwaysEat ?
                new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationMod).alwaysEdible() :
                new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationMod)).build();
    }

    private static FoodProperties registerStew(int nutrition) {
        return register(nutrition, 0.8F);
    }
}
