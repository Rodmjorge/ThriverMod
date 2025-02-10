package net.rodmjorgeh.renovay.world.area.areagen.feature.decorators;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.world.area.areagen.feature.placers.PalmTreeTrunkPlacer;

public class TreeDecoratorsRegistry {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, RenovayMod.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<CoconutDecorator>> COCONUT =
            register("coconut", CoconutDecorator.CODEC);


    private static <T extends TreeDecorator> RegistryObject<TreeDecoratorType<T>> register(String name, MapCodec<T> codec) {
        return TREE_DECORATORS.register(name, () -> new TreeDecoratorType<>(codec));
    }
    public static void register(IEventBus bus) { TREE_DECORATORS.register(bus); }
}
