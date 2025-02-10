package net.rodmjorgeh.renovay;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rodmjorgeh.renovay.client.model.ModelLayersR;
import net.rodmjorgeh.renovay.util.events.BusEvents;
import net.rodmjorgeh.renovay.util.events.DatagenEvents;
import net.rodmjorgeh.renovay.world.area.areagen.feature.decorators.TreeDecoratorsRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.block.entity.BlockEntityRegistry;
import net.rodmjorgeh.renovay.world.area.entity.EntityRegistry;
import net.rodmjorgeh.renovay.world.area.areagen.feature.placers.FoliagePlacerRegistry;
import net.rodmjorgeh.renovay.world.area.areagen.feature.placers.TrunkPlacerRegistry;
import net.rodmjorgeh.renovay.world.item.CreativeModeTabRegistry;
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
        BlockEntityRegistry.register(bus);
        TrunkPlacerRegistry.register(bus);
        FoliagePlacerRegistry.register(bus);
        TreeDecoratorsRegistry.register(bus);

        bus.addListener(CreativeModeTabRegistry::addToCreativeTab);
        bus.addListener(BusEvents::commonSetup);
        bus.addListener(BusEvents::onClientSetup);
        bus.addListener(ModelLayersR::registerLayers);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(BusEvents.class);
        MinecraftForge.EVENT_BUS.register(DatagenEvents.class);
    }

    /**
     * Creates a new {@code ResourceKey} based on the registry type.
     */
    public static <T> ResourceKey<T> createId(String name, ResourceKey<? extends Registry<T>> registryType) {
        return ResourceKey.create(registryType, ResourceLocation.fromNamespaceAndPath(RenovayMod.MOD_ID, name));
    }

    public static String name(String s) {
        return MOD_ID + ":" + s;
    }
}
