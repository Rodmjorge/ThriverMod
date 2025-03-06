package net.rodmjorgeh.renovay;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.rodmjorgeh.renovay.advancements.CriteriaTriggerRegistry;
import net.rodmjorgeh.renovay.client.model.ModelLayersR;
import net.rodmjorgeh.renovay.data.loot.modifiers.LootModifierRegistry;
import net.rodmjorgeh.renovay.sound.SoundEventRegistry;
import net.rodmjorgeh.renovay.util.BusEvents;
import net.rodmjorgeh.renovay.data.DatagenEvents;
import net.rodmjorgeh.renovay.world.area.areagen.feature.FeatureRegistry;
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

    public RenovayMod(IEventBus bus, ModContainer container)
    {
        BlockRegistry.register(bus);
        ItemRegistry.register(bus);
        EntityRegistry.register(bus);
        BlockEntityRegistry.register(bus);
        SoundEventRegistry.register(bus);
        FeatureRegistry.register(bus);
        TrunkPlacerRegistry.register(bus);
        FoliagePlacerRegistry.register(bus);
        TreeDecoratorsRegistry.register(bus);
        LootModifierRegistry.register(bus);
        CriteriaTriggerRegistry.register(bus);

        bus.addListener(CreativeModeTabRegistry::addToCreativeTab);
        bus.addListener(BusEvents::commonSetup);
        bus.addListener(BusEvents::onClientSetup);
        bus.addListener(DatagenEvents::onGatherDataClient);
        bus.addListener(ModelLayersR::registerLayers);
    }

    /**
     * Creates a new {@code ResourceKey} based on the registry type.
     */
    public static <T> ResourceKey<T> createId(String name, ResourceKey<? extends Registry<T>> registryType) {
        return ResourceKey.create(registryType, createLoc(name));
    }

    public static ResourceLocation createLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static String name(String s) {
        return MOD_ID + ":" + s;
    }
}
