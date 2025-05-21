package net.rodmjorgeh.thriver.world.area.areagen.feature.decorators;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.ThriverMod;

import java.util.function.Supplier;

public class TreeDecoratorsRegistry {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS =
            DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, ThriverMod.MOD_ID);

    public static final Supplier<TreeDecoratorType<CoconutDecorator>> COCONUT =
            register("coconut", CoconutDecorator.CODEC);


    private static <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> register(String name, MapCodec<T> codec) {
        return TREE_DECORATORS.register(name, () -> new TreeDecoratorType<>(codec));
    }
    public static void register(IEventBus bus) { TREE_DECORATORS.register(bus); }
}
