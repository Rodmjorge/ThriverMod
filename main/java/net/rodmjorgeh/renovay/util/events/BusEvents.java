package net.rodmjorgeh.renovay.util.events;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rodmjorgeh.renovay.RenovayMod;
import net.rodmjorgeh.renovay.client.renderer.EntityRendererRegistry;
import net.rodmjorgeh.renovay.data.BuilderRegistries;
import net.rodmjorgeh.renovay.world.area.block.BlockRegistry;
import net.rodmjorgeh.renovay.world.area.block.state.WoodTypeR;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = RenovayMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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

            ComposterBlock.add(0.3F, BlockRegistry.PALM_LEAVES.get().asItem());
            ComposterBlock.add(0.3F, BlockRegistry.PALM_SPROUT.get().asItem());
            ComposterBlock.add(0.65F, BlockRegistry.COCONUT.get().asItem());
        });
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRendererRegistry.registerAll();

            Sheets.addWoodType(WoodTypeR.PALM);
        });
    }
}
