package net.rodmjorgeh.thriver;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerRegistry;
import net.rodmjorgeh.thriver.client.model.ModelLayersR;
import net.rodmjorgeh.thriver.client.renderer.blockentity.SpecialModeRendererRegistry;
import net.rodmjorgeh.thriver.data.loot.modifiers.LootModifierRegistry;
import net.rodmjorgeh.thriver.sound.SoundEventRegistry;
import net.rodmjorgeh.thriver.util.BusEvents;
import net.rodmjorgeh.thriver.data.DatagenEvents;
import net.rodmjorgeh.thriver.world.area.areagen.feature.FeatureRegistry;
import net.rodmjorgeh.thriver.world.area.areagen.feature.decorators.TreeDecoratorsRegistry;
import net.rodmjorgeh.thriver.world.area.block.BlockRegistry;
import net.rodmjorgeh.thriver.world.area.block.entity.BlockEntityRegistry;
import net.rodmjorgeh.thriver.world.area.entity.EntityRegistry;
import net.rodmjorgeh.thriver.world.area.areagen.feature.placers.FoliagePlacerRegistry;
import net.rodmjorgeh.thriver.world.area.areagen.feature.placers.TrunkPlacerRegistry;
import net.rodmjorgeh.thriver.world.item.CreativeModeTabRegistry;
import net.rodmjorgeh.thriver.world.item.ItemRegistry;
import net.rodmjorgeh.thriver.world.area.maps.MapDecorationTypeRegistry;
import org.slf4j.Logger;

@Mod(ThriverMod.MOD_ID)
public class ThriverMod {

    public static final String MOD_ID = "thriver";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ThriverMod(IEventBus bus, ModContainer container)
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
        MapDecorationTypeRegistry.register(bus);

        bus.addListener(CreativeModeTabRegistry::addToCreativeTab);
        bus.addListener(BusEvents::commonSetup);
        bus.addListener(BusEvents::onClientSetup);
        bus.addListener(DatagenEvents::onGatherDataClient);
        bus.addListener(ModelLayersR::registerLayers);
        bus.addListener(SpecialModeRendererRegistry::register);
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
    public static <T> ResourceLocation createLocFromKey(ResourceKey<T> key) {
        return createLoc(key.location().getPath());
    }
    public static String createStringLoc(String name) {
        return MOD_ID + ":" + name;
    }

    public static String name(String s) {
        return MOD_ID + ":" + s;
    }
}
