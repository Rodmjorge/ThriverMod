package net.rodmjorgeh.renovay.util.events;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.client.renderer.EntityRendererRegistry;
import net.rodmjorgeh.renovay.client.renderer.blockentity.BlockEntityRendererRegistry;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.block.state.properties.WoodTypeR;
import net.rodmjorgeh.renovay.world.item.ItemRegistry;

public class BusEvents {

    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(BlockRegistry.PALM_LOG.get(), BlockRegistry.STRIPPED_PALM_LOG.get())
                    .put(BlockRegistry.PALM_WOOD.get(), BlockRegistry.STRIPPED_PALM_WOOD.get())
                    .build();

            FireBlock fb = (FireBlock)Blocks.FIRE;
            fb.setFlammable(BlockRegistry.PALM_LOG.get(), 5, 5);
            fb.setFlammable(BlockRegistry.PALM_WOOD.get(), 5, 5);
            fb.setFlammable(BlockRegistry.STRIPPED_PALM_LOG.get(), 5, 5);
            fb.setFlammable(BlockRegistry.STRIPPED_PALM_WOOD.get(), 5, 5);
            fb.setFlammable(BlockRegistry.PALM_PLANKS.get(), 5, 20);
            fb.setFlammable(BlockRegistry.PALM_SLAB.get(), 5, 20);
            fb.setFlammable(BlockRegistry.PALM_STAIRS.get(), 5, 20);
            fb.setFlammable(BlockRegistry.PALM_FENCE.get(), 5, 20);
            fb.setFlammable(BlockRegistry.PALM_FENCE_GATE.get(), 5, 20);
            fb.setFlammable(BlockRegistry.PALM_LEAVES.get(), 30, 60);
        });
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRendererRegistry.registerAll();
            BlockEntityRendererRegistry.registerAll();

            Sheets.addWoodType(WoodTypeR.PALM);
        });
    }
}
