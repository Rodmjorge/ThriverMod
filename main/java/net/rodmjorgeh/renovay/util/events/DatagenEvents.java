package net.rodmjorgeh.renovay.util.events;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.data.BuilderRegistries;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = RenovayMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DatagenEvents {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator dataGen = event.getGenerator();
        PackOutput output = dataGen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        dataGen.addProvider(event.includeServer(), new BuilderRegistries(output, provider));
    }
}
