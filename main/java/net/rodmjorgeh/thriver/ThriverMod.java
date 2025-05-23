package net.rodmjorgeh.thriver;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.rodmjorgeh.thriver.advancements.CriteriaTriggerReg;
import net.rodmjorgeh.thriver.client.model.ModelLayersThr;
import net.rodmjorgeh.thriver.client.renderer.blockentity.SpecialModeRendererReg;
import net.rodmjorgeh.thriver.data.loot.modifiers.LootModifierReg;
import net.rodmjorgeh.thriver.sound.SoundEventReg;
import net.rodmjorgeh.thriver.util.BusEvents;
import net.rodmjorgeh.thriver.data.DatagenEvents;
import net.rodmjorgeh.thriver.world.area.areagen.feature.FeatureReg;
import net.rodmjorgeh.thriver.world.area.areagen.feature.decorators.TreeDecoratorsReg;
import net.rodmjorgeh.thriver.world.area.block.BlockReg;
import net.rodmjorgeh.thriver.world.area.block.entity.BlockEntityReg;
import net.rodmjorgeh.thriver.world.area.entity.EntityReg;
import net.rodmjorgeh.thriver.world.area.areagen.feature.placers.FoliagePlacerReg;
import net.rodmjorgeh.thriver.world.area.areagen.feature.placers.TrunkPlacerReg;
import net.rodmjorgeh.thriver.world.item.CreativeModeTabReg;
import net.rodmjorgeh.thriver.world.item.ItemReg;
import net.rodmjorgeh.thriver.world.area.maps.MapDecorationTypeReg;
import org.slf4j.Logger;

@Mod(ThriverMod.MOD_ID)
public class ThriverMod {

    public static final String MOD_ID = "thriver";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ThriverMod(IEventBus bus, ModContainer container)
    {
        BlockReg.register(bus);
        ItemReg.register(bus);
        EntityReg.register(bus);
        BlockEntityReg.register(bus);
        SoundEventReg.register(bus);
        FeatureReg.register(bus);
        TrunkPlacerReg.register(bus);
        FoliagePlacerReg.register(bus);
        TreeDecoratorsReg.register(bus);
        LootModifierReg.register(bus);
        CriteriaTriggerReg.register(bus);
        MapDecorationTypeReg.register(bus);

        bus.addListener(CreativeModeTabReg::addToCreativeTab);
        bus.addListener(BusEvents::commonSetup);
        bus.addListener(BusEvents::onClientSetup);
        bus.addListener(DatagenEvents::onGatherDataClient);
        bus.addListener(ModelLayersThr::registerLayers);
        bus.addListener(SpecialModeRendererReg::register);
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
