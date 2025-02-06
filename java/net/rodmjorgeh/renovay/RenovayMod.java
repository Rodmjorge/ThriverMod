package net.rodmjorgeh.renovay;

import com.mojang.logging.LogUtils;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.BlockStateData;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rodmjorgeh.renovay.client.model.ModelLayersR;
import net.rodmjorgeh.renovay.client.renderer.EntityRendererRegistry;
import net.rodmjorgeh.renovay.util.events.BusEvents;
import net.rodmjorgeh.renovay.world.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.entity.EntityRegistry;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;
import org.slf4j.Logger;

@Mod(RenovayMod.MOD_ID)
public class RenovayMod {

    public static final String MOD_ID = "renovay";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RenovayMod()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.register(bus);
        ItemRegistry.register(bus);
        EntityRegistry.register(bus);

        bus.addListener(ItemRegistry::addToCreativeTab);
        bus.addListener(BusEvents::commonSetup);
        bus.addListener(BusEvents::onClientSetup);
        bus.addListener(ModelLayersR::registerLayers);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(BusEvents.class);
    }

    public static <T> ResourceKey<T> createId(String name, ResourceKey<? extends Registry<T>> registryType) {
        return ResourceKey.create(registryType, ResourceLocation.fromNamespaceAndPath(RenovayMod.MOD_ID, name));
    }

    public static String name(String s) {
        return MOD_ID + ":" + s;
    }
}
