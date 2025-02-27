package net.rodmjorgeh.renovay.world.item.food;

import net.minecraft.world.food.FoodProperties;

public class FoodRegistry {

    public static final FoodProperties COCONUT_MUSHROOM_STEW = registerStew(10, false);
    public static final FoodProperties COCONUT_RABBIT_STEW = registerStew(14, false);
    public static final FoodProperties COCONUT_BEETROOT_SOUP = registerStew(10, false);
    public static final FoodProperties COCONUT_SUSPICIOUS_STEW = registerStew(10, true);
    public static final FoodProperties COCONUT_MILK = register(4, 0.5F, true);

    private static FoodProperties register(int nutrition, float saturationMod) {
        return register(nutrition, saturationMod, false);
    }
    private static FoodProperties register(int nutrition, float saturationMod, boolean canAlwaysEat) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationMod);
        return (canAlwaysEat ? builder.alwaysEdible() : builder).build();
    }

    private static FoodProperties registerStew(int nutrition, boolean canAlwaysEat) {
        return register(nutrition, 0.8F, canAlwaysEat);
    }
}
